package bot;

import commands.*;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class Bot {
    public Status statusActive = Status.START;
    private transient HashMap<Status, HashMap<String, BiFunction<Bot, String, String>>> commands = new HashMap<>();
    private transient Study study;

    public Bot() {
        study = new Study();
        initCommands();
    }

    private void initCommands() {
        HashMap<String, BiFunction<Bot, String, String>> dictionaryStart = new HashMap<>();
        dictionaryStart.put("help", Start::help);
        dictionaryStart.put("помощь", Start::help);
        dictionaryStart.put("default", Start::start);

        commands.put(Status.START, dictionaryStart);

        HashMap<String, BiFunction<Bot, String, String>> dictionaryMenu = new HashMap<>();
        dictionaryMenu.put("help", Help::help);
        dictionaryMenu.put("помощь", Help::help);
        dictionaryMenu.put("авторы", Owners::owners);
        dictionaryMenu.put("эхо", Echo::echo);
        dictionaryMenu.put("default", NotUnderstand::notUnderstand);
        dictionaryMenu.put("учеба", study::mainMenu);

        commands.put(Status.MENU, dictionaryMenu);

        HashMap<String, BiFunction<Bot, String, String>> dictStudy = new HashMap<>();
        dictStudy.put("пары", study::startClasses);
        dictStudy.put("help", study::studyHelp);
        dictStudy.put("помощь", study::studyHelp);
        dictStudy.put("default", study::def);
        dictStudy.put("меню", study::quitToMenu);

        commands.put(Status.STUDY, dictStudy);

        HashMap<String, BiFunction<Bot, String, String>> dictClasses = new HashMap<>();
        dictClasses.put("default", study::getClasses);
        dictClasses.put("меню", study::quitToMenu);
        dictClasses.put("help", study::classesHelp);
        dictClasses.put("помощь", study::classesHelp);

        commands.put(Status.CLASSES, dictClasses);

        //HashMap<String, BiFunction<Bot, String, String>> dictBooks = new HashMap<>();
        //здесь надо добавить команды, связанные с книгами, и расскомментировать строки выше и ниже

        //commands.put(Status.BOOKS, dictBooks);
    }

    public String getAnswer(String line) {
        String command = line;
        if (!Pattern.matches(" +", line)) {
            command = line.split(" ")[0].toLowerCase();
        }
        return commands.get(statusActive)
                .getOrDefault(command, commands.get(statusActive).get("default"))
                .apply(this, line);
    }
}
