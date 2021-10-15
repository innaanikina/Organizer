package bot;

import commands.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.BiFunction;

class DictsCommands {
    private HashMap<String, BiFunction<BotLogic, String, String>> dictStart = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictMenu = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictStudy = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictClasses = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictBooks = new HashMap<>();

    private HashSet<String> hiddenCommands;

    private transient HashMap<State.state, HashMap<String, BiFunction<BotLogic, String, String>>> commands = new HashMap<>();

    DictsCommands(){
        setDictStart();
        setDictMenu();
        setDictStudy();
        setDictClasses();
        setDictBooks();

        setCommands();

        setHiddenCommands();
    }

    private void setCommands(){
        commands.put(State.state.START, dictStart);
        commands.put(State.state.MENU, dictMenu);
        commands.put(State.state.STUDY, dictStudy);
        commands.put(State.state.CLASSES, dictClasses);
        commands.put(State.state.BOOKS, dictBooks);
    }

    private void setDictStart(){
        dictStart.put("help", Start::help);
        dictStart.put("помощь", Start::help);
        dictStart.put("default", Start::start);
    }

    private void setDictMenu(){
        dictMenu.put("help", Menu::help);
        dictMenu.put("помощь", Menu::help);
        dictMenu.put("авторы", Owners::owners);
        dictMenu.put("default", NotUnderstand::notUnderstand);
        dictMenu.put("учеба", Study::mainMenu);
        dictMenu.put("книги", Books::quitToBookMenu);
    }

    private void setDictStudy(){
        dictStudy.put("пары", Study::startClasses);
        dictStudy.put("help", Study::studyHelp);
        dictStudy.put("помощь", Study::studyHelp);
        dictStudy.put("default", NotUnderstand::notUnderstand);
        dictStudy.put("меню", Menu::quitToMenu);
    }

    private void setDictClasses(){
        dictClasses.put("default", Study::getClasses);
        dictClasses.put("меню", Menu::quitToMenu);
        dictClasses.put("help", Study::classesHelp);
        dictClasses.put("помощь", Study::classesHelp);
    }

    private void setDictBooks(){
        dictBooks.put("список", Books::getBooksList);
        dictBooks.put("меню", Menu::quitToMenu);
        dictBooks.put("help", Books::booksHelp);
        dictBooks.put("помощь", Books::booksHelp);
        dictBooks.put("default", Study::getClasses);
    }

    private void setHiddenCommands(){
        var reader = new Reader();
        hiddenCommands = reader.readFileArray("src/main/resources/hiddenCommand.txt");
    }

    HashMap<State.state, HashMap<String, BiFunction<BotLogic, String, String>>> getCommands(){
        return commands;
    }

    HashSet<String> getHiddenCommands() {
        return hiddenCommands;
    }
}
