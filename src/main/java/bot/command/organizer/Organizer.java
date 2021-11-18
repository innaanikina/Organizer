package bot.command.organizer;

import bot.BotLogic;
import bot.State;
import bot.command.organizer.OrganizerElement;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Organizer implements Serializable {

    public static String organizerHelp(BotLogic bot, String command) {
        return "Справка по пользованию Time-Manager'ом:\n" +
                "\nФлаги для заданий с дедлайнами:\n" +
                Flag.COMPLETED.getEmoji() + " - Выполнено\n" +
                Flag.IN_PROGRESS.getEmoji() + " - В процессе\n" +
                Flag.DEADLINE_IS_COMING.getEmoji() + " - Скоро дедлайн\n" +
                Flag.FAILED.getEmoji() + " - Потрачено\n" +
                "\nКоманды:\n" +
                "\\* добавить - добавить задачу, \n" +
                "\\* все задания - показать все задачи, \n" +
                "\\* редактировать - редактировать,\n" +
                "\\* дедлайн - установить дедлайн для задачи (будут отправляться напоминания) \n" +
                "\\* сделано - отметить задачу как выполненную,\n" +
                "\\* удалить задание - удалить таск,\n" +
                "\\* меню - выход в меню";
    }

    public static String startOrganizer(BotLogic bot, String command) {
        bot.statusActive = State.ORGANIZER;
        return "Приветствую, я твой личный Time-Manager.";
    }

    public static String all(BotLogic bot, String command) {
        StringBuilder tasks = new StringBuilder("*Все задачи:*\n");
        int number = 1;
        for (OrganizerElement e : bot.organizer) {
            if (e.needsFlagUpdate()) {
                e.updateFlag();
            }
            tasks.append("\n")
                    .append(number++)
                    .append(".\t")
                    .append(e.flag.getEmoji())
                    .append("\t\t\t")
                    .append(e.task);
        }
        if (bot.organizer.isEmpty())
            return "Задач пока нет";
        return tasks.toString();
    }

    public static String checkDeadlines(BotLogic bot) {
        var output = new StringBuilder();
        var timeNow = new GregorianCalendar();
        for (var e : bot.organizer) {
            if (e.flag != Flag.NO_CHECK) {
                var key = e.getDeadlineName();
                updateValues(key, bot);

                if (timeNow.compareTo(e.date) <= -1 && e.flag == Flag.DEADLINE_IS_COMING) {
                    var timeToSend = checkIfSend(bot, key, e);
                    if (timeToSend != -1) {
                        output.append(getDeadlineMessage(timeToSend)).append(e.task).append("\n");
                    }
                } else if (e.flag == Flag.COMPLETED || e.flag == Flag.FAILED) {
                    bot.deadlines.remove(key);
                }
            }
        }
        return output.toString();
    }

    private static Integer checkIfSend(BotLogic bot, String key, OrganizerElement e) {
        GregorianCalendar now = new GregorianCalendar();
        Long curTimeMs = now.getTimeInMillis();

        Long taskTimeMs = e.date.getTimeInMillis();
        int eps = 10000;
        for (Integer time : bot.deadlines.get(key).keySet()) {
            boolean shouldSend = Math.abs(taskTimeMs - curTimeMs - time) < eps;
            if (!bot.deadlines.get(key).get(time) && shouldSend) {
                bot.deadlines.get(key).put(time, true);
                return time;
            }
        }
        return -1;
    }

    public static String add(BotLogic bot, String command) {
        bot.statusActive = State.ORGANIZER_ADD;
        return "Введи новую задачу:";
    }

    public static String pushTask(BotLogic bot, String command) {
        bot.organizer.add(new OrganizerElement(command));
        bot.statusActive = State.ORGANIZER;
        return "Задача добавлена";
    }

    public static String startComplete(BotLogic bot, String command) {
        bot.statusActive = State.ORGANIZER_COMPLETE;
        return "Введи номер задачи";
    }

    public static String completed(BotLogic bot, String command) {
        try {
            bot.organizer.get(Integer.parseInt(command) - 1).flag = Flag.COMPLETED;
            bot.statusActive = State.ORGANIZER;
            return "Выполнено";
        } catch (IndexOutOfBoundsException e) {
            return "Неверный номер задания";
        } catch (Exception e) {
            return "Неправильный ввод";
        }
    }

    public static String startDelete(BotLogic bot, String command) {
        bot.statusActive = State.ORGANIZER_DELETE;
        return "Введи номер задачи, которую нужно удалить";
    }

    public static String delete(BotLogic bot, String command) {
        try {
            var taskNum = Integer.parseInt(command) - 1;
            var task = bot.organizer.get(taskNum);
            bot.deadlines.remove(task.getDeadlineName());
            bot.organizer.remove(taskNum);
            return "Удалено, текущий список задач:\n\n" + all(bot, command);
        } catch (IndexOutOfBoundsException e) {
            return "Неверный номер задачи";
        } catch (Exception e) {
            return "Неправильный ввод :( \nВведи номер задачи";
        }
    }

    public static String startDeadline(BotLogic bot, String command) {
        if (bot.organizer.size() == 0) {
            return "Задач пока нет";
        }
        bot.statusActive = State.ORGANIZER_DEADLINE1;
        return "Введи номер задачи:";
    }

    public static String startDeadlineDate(BotLogic bot, String command) {
        try {
            var deadlineTaskNum = Integer.parseInt(command) - 1;
            var task = bot.organizer.get(deadlineTaskNum);
            bot.edits.put("deadline", deadlineTaskNum);
        } catch (Exception e) {
            return "Неправильный ввод";
        }
        bot.statusActive = State.ORGANIZER_DEADLINE2;
        return "Введи дату и время в формате ДД.ММ.ГГ ЧЧ:ММ:СС";
    }

    public static String pushDeadline(BotLogic bot, String command) {
        GregorianCalendar date = parseDate(bot, command);
        if (date == null) {
            bot.statusActive = State.ORGANIZER;
            return "Ошибка ввода";
        }
        OrganizerElement task = bot.organizer.get(bot.edits.get("deadline"));
        task.flag = Flag.IN_PROGRESS;
        task.date = date;
        bot.edits.remove("deadline");
        String taskName = task.getDeadlineName(date);
        updateValues(taskName, bot);
        bot.statusActive = State.ORGANIZER;
        return "Дедлайн установлен";
    }

    private static void updateValues(String key, BotLogic bot) {
        if (!bot.deadlines.containsKey(key)) {
            HashMap<Integer, Boolean> times = new HashMap<>();
            times.put(86400000, false);
            times.put(7200000, false);
            times.put(300000, false);
            times.put(0, false);
            bot.deadlines.put(key, times);
        }
    }

    private static String getDeadlineMessage(Integer key) {
        HashMap<Integer, String> answers = new HashMap<>();
        answers.put(86400000, "До дедлайна 24 часа! Не забудь про задание ");
        answers.put(7200000, "Осталось 2 часа, чтобы выполнить ");
        answers.put(300000, "Всего 5 минут до дедлайна по задаче ");
        answers.put(0, "Задача просрочена :( ");
        return answers.get(key);
    }

    private static void updateDeadline(BotLogic bot, OrganizerElement task, GregorianCalendar newDate) {
        bot.deadlines.remove(task.getDeadlineName());
        var newTaskName = task.getDeadlineName(newDate);
        updateValues(newTaskName, bot);
    }

    private static void updateDeadline(BotLogic bot, OrganizerElement task, String newTask) {
        bot.deadlines.remove(task.getDeadlineName());
        System.out.println(task.getDeadlineName());
        var newTaskName = task.getDeadlineName(newTask);
        updateValues(newTaskName, bot);
    }


    private static GregorianCalendar parseDate(BotLogic bot, String command) {
        var day = new SimpleDateFormat("dd.MM.yy", new Locale("ru"));
        var dayTime = new SimpleDateFormat("dd.MM.yy HH:mm:ss", new Locale("ru"));
        day.setLenient(false);
        dayTime.setLenient(false);
        Date date;
        try {
            date = dayTime.parse(command.split(" ")[0]
                    + " " + command.split(" ")[1]);
            GregorianCalendar deadline = new GregorianCalendar();
            deadline.setTime(date);
            return deadline;
        } catch (ParseException e1) {
            e1.printStackTrace();
            try {
                date = day.parse(command);
                GregorianCalendar deadline = new GregorianCalendar();
                deadline.setTime(date);
                return deadline;
            } catch (ParseException e2) {
                System.out.println("can't parse time");
                return null;
            }
        }
    }

    public static String showDeadlines(BotLogic bot, String command) {
        if (bot.deadlines.isEmpty()) {
            return "Дедлайны не установлены";
        }
        StringBuilder tasks = new StringBuilder();
        tasks.append("Дедлайны:\n");
        for (var o : bot.deadlines.keySet()) {
            tasks.append(o).append("\n");
        }
        return tasks.toString();
    }

    public static String back(BotLogic bot, String command) {
        bot.statusActive = State.ORGANIZER;
        bot.edits.remove("deadline");
        bot.edits.remove("edit");
        return "Меню органайзера";
    }

    public static String quit(BotLogic bot, String command) {
        bot.statusActive = State.MENU;
        return "Выход в главное меню";
    }

    public static String startEdit(BotLogic bot, String command) {
        if (bot.organizer.size() == 0) {
            return "Нет заданий для редактирования";
        }
        bot.statusActive = State.ORGANIZER_EDIT;
        return "Введи номер задания, которое надо изменить";
    }

    public static String editChoice(BotLogic bot, String command) {
        try {
            var editTaskNum = Integer.parseInt(command) - 1;
            OrganizerElement task = bot.organizer.get(editTaskNum);
            bot.edits.put("edit", editTaskNum);
            bot.statusActive = State.ORGANIZER_EDIT_CHOICE;
            return "Выбери параметр редактирования";
        } catch (Exception e) {
            bot.statusActive = State.ORGANIZER;
            return "Неверный ввод";
        }
    }

    public static String editTask(BotLogic bot, String command) {
        bot.statusActive = State.ORGANIZER_EDIT_TASK;
        return "Введи новый текст задания";
    }

    public static String editTime(BotLogic bot, String command) {
        try {
            var editTaskNum = bot.edits.get("edit");
            OrganizerElement task = bot.organizer.get(editTaskNum);
            if (task.flag == Flag.NO_CHECK) {
                bot.statusActive = State.ORGANIZER;
                return "У этого задания не задано время выполнения";
            } else {
                bot.statusActive = State.ORGANIZER_EDIT_TIME;
                return "Введи новое время выполнения";
            }
        } catch (Exception e) {
            bot.statusActive = State.ORGANIZER;
            return "Ошибка редактирования";
        }
    }

    public static String pushEditTask(BotLogic bot, String command) {
        var editTaskNum = bot.edits.get("edit");
        var task = bot.organizer.get(editTaskNum);
        bot.edits.remove("edit");
        if (task.flag != Flag.NO_CHECK) {
            updateDeadline(bot, task, command);
        }
        task.changeTask(command);
        bot.statusActive = State.ORGANIZER;
        return "Задание успешно изменено";
    }

    public static String pushEditTime(BotLogic bot, String command) {
        var editTaskNum = bot.edits.get("edit");
        var task = bot.organizer.get(editTaskNum);
        var date = parseDate(bot, command);
        if (date == null) {
            return "Ошибка ввода";
        } else {
            updateDeadline(bot, task, date);
            task.changeDate(date);
            task.updateFlag();
            bot.statusActive = State.ORGANIZER;
            return "Дата выполнения изменена";
        }
    }
}
