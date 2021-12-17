package bot.commands.elem;

import bot.LogicBot;
import bot.commands.schedule.Schedule;
import bot.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class Study {

    private static transient List<String> weekDays = new ArrayList<String>() {{
        add("пн");
        add("вт");
        add("ср");
        add("чт");
        add("пт");
        add("сб");

    }};

    public static String getSchedule(String day) {
        Schedule daySchedule = new Schedule(day);
        return daySchedule.getDaySchedule();
    }
    public static String startClasses(LogicBot bot, String command) {
        bot.statusActive = "CLASSES";
        UserService.updateStatusActiveByUserId(bot.id, "CLASSES");
        return "Введите день недели \n(в формате: пн, вт, ср, чт, пт, сб)";
    }

    public static String mainMenu(LogicBot bot, String command) {
        bot.statusActive = "STUDY";
        UserService.updateStatusActiveByUserId(bot.id, "STUDY");
        return "Вы в разделе учёбы";
    }

    public static String getClasses(LogicBot bot, String command) {
        if (weekDays.contains(command.toLowerCase())) {
            return getSchedule(command.toLowerCase());
        } else
            return NotUnderstand.notUnderstand(bot, command);
    }

    public static String studyHelp(LogicBot bot, String command) {
        return "Доступные действия:\n\n* пары - посмотреть расписание\n* меню - выход в меню";
    }

    public static String classesHelp(LogicBot bot, String command) {
        return "Доступные действия:\n\n* чтобы посмотреть расписание на другой день, выбери день недели\n" +
                "* меню - вернуться в главное меню";
    }

    public static List<String> getWeekDays() {
        return weekDays;
    }
}
