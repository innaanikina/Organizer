package bot;

import commands.*;
import commands.organizer.Organizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.BiFunction;

class DictCommands {
    private HashMap<String, BiFunction<BotLogic, String, String>> dictStart = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictMenu = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictStudy = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictClasses = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictBooks = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictOrganizer = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictOrganizerAdd = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictOrganizerDel = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictOrganizerDeadline1 = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictOrganizerDeadline2 = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictOrganizerEdit = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictOrganizerEditChoice = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictOrganizerEditTime = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictOrganizerEditTask = new HashMap<>();
    private HashMap<String, BiFunction<BotLogic, String, String>> dictOrganizerComplete = new HashMap<>();

    private HashSet<String> hiddenCommands;

    private transient HashMap<State, HashMap<String, BiFunction<BotLogic, String, String>>> commands = new HashMap<>();

    DictCommands(){
        setDictStart();
        setDictMenu();
        setDictStudy();
        setDictClasses();
        setDictBooks();
        setDictOrganizer();
        setDictOrganizerAdd();
        setDictOrganizerDel();
        setDictDeadline1();
        setDictDeadline2();
        setDictOrganizerEdit();
        setDictOrganizerEditChoice();
        setDictOrganizerEditTime();
        setDictOrganizerEditTask();
        setDictOrganizerComplete();

        setCommands();

        setHiddenCommands();
    }

    private void setCommands(){
        commands.put(State.START, dictStart);
        commands.put(State.MENU, dictMenu);
        commands.put(State.STUDY, dictStudy);
        commands.put(State.CLASSES, dictClasses);
        commands.put(State.BOOKS, dictBooks);
        commands.put(State.ORGANIZER, dictOrganizer);
        commands.put(State.ORGANIZER_ADD, dictOrganizerAdd);
        commands.put(State.ORGANIZER_DELETE, dictOrganizerDel);
        commands.put(State.ORGANIZER_DEADLINE1, dictOrganizerDeadline1);
        commands.put(State.ORGANIZER_DEADLINE2, dictOrganizerDeadline2);
        commands.put(State.ORGANIZER_EDIT, dictOrganizerEdit);
        commands.put(State.ORGANIZER_EDIT_CHOICE, dictOrganizerEditChoice);
        commands.put(State.ORGANIZER_EDIT_TIME, dictOrganizerEditTime);
        commands.put(State.ORGANIZER_EDIT_TASK, dictOrganizerEditTask);
        commands.put(State.ORGANIZER_COMPLETE, dictOrganizerComplete);
    }

    private void setDictStart(){
        dictStart.put("help", Start::help);
        dictStart.put("????????????", Start::help);
        dictStart.put("default", Start::start);
    }

    private void setDictMenu(){
        dictMenu.put("help", Menu::help);
        dictMenu.put("????????????", Menu::help);
        dictMenu.put("????????????", Owners::owners);
        dictMenu.put("default", NotUnderstand::notUnderstand);
        dictMenu.put("??????????", Study::mainMenu);
        dictMenu.put("??????????", Books::quitToBookMenu);
        dictMenu.put("????????????????????", Organizer::startOrganizer);
    }

    private void setDictStudy(){
        dictStudy.put("????????", Study::startClasses);
        dictStudy.put("help", Study::studyHelp);
        dictStudy.put("????????????", Study::studyHelp);
        dictStudy.put("default", NotUnderstand::notUnderstand);
        dictStudy.put("????????", Menu::quitToMenu);
    }

    private void setDictClasses(){
        dictClasses.put("default", Study::getClasses);
        dictClasses.put("????????", Menu::quitToMenu);
        dictClasses.put("help", Study::classesHelp);
        dictClasses.put("????????????", Study::classesHelp);
    }

    private void setDictBooks(){
        dictBooks.put("????????????", Books::getBooksList);
        dictBooks.put("????????", Menu::quitToMenu);
        dictBooks.put("help", Books::booksHelp);
        dictBooks.put("????????????", Books::booksHelp);
        dictBooks.put("default", Study::getClasses);
    }

    private void setDictOrganizer() {
        dictOrganizer.put("????????????", Organizer::organizerHelp);
        dictOrganizer.put("default", NotUnderstand::notUnderstand);
        dictOrganizer.put("????????", Organizer::quit);
        dictOrganizer.put("????????????????", Organizer::add);
        dictOrganizer.put("?????? ??????????????", Organizer::all);
        dictOrganizer.put("?????????????? ??????????????", Organizer::startDelete);
        dictOrganizer.put("???????????????? ??????????????", Organizer::startDeadline);
        dictOrganizer.put("??????????????????????????", Organizer::startEdit);
        dictOrganizer.put("??????????????", Organizer::startComplete);
        dictOrganizer.put("deadlines", Organizer::showDeadlines);
    }

    private void setDictOrganizerAdd() {
        dictOrganizerAdd.put("default", Organizer::pushTask);
        dictOrganizerAdd.put("??????????", Organizer::back);
    }

    private void setDictOrganizerDel() {
        dictOrganizerDel.put("default", Organizer::delete);
        dictOrganizerDel.put("??????????", Organizer::back);
    }

    private void setDictDeadline1() {
        dictOrganizerDeadline1.put("default", Organizer::startDeadlineDate);
        dictOrganizerDeadline1.put("??????????", Organizer::back);
    }

    private void setDictDeadline2() {
        dictOrganizerDeadline2.put("default", Organizer::pushDeadline);
        dictOrganizerDeadline2.put("??????????", Organizer::back);
    }

    private void setDictOrganizerEdit() {
        dictOrganizerEdit.put("default", Organizer::editChoice);
        dictOrganizerEdit.put("??????????", Organizer::back);
    }

    private void setDictOrganizerEditChoice() {
        dictOrganizerEditChoice.put("????????", Organizer::editTime);
        dictOrganizerEditChoice.put("????????????", Organizer::editTask);
        dictOrganizerEditChoice.put("??????????", Organizer::back);
        dictOrganizerEditChoice.put("default", NotUnderstand::notUnderstand);
    }

    private void setDictOrganizerEditTime() {
        dictOrganizerEditTime.put("default", Organizer::pushEditTime);
        dictOrganizerEditTime.put("??????????", Organizer::back);
    }

    private void setDictOrganizerEditTask() {
        dictOrganizerEditTask.put("default", Organizer::pushEditTask);
        dictOrganizerEditTask.put("??????????", Organizer::back);
    }

    private void setDictOrganizerComplete() {
        dictOrganizerComplete.put("default", Organizer::completed);
        dictOrganizerComplete.put("??????????", Organizer::back);
    }

    private void setHiddenCommands(){
        var reader = new Reader();
        hiddenCommands = reader.readFileArray("src/main/resources/hiddenCommand.txt");
    }

    HashMap<State, HashMap<String, BiFunction<BotLogic, String, String>>> getCommands(){
        return commands;
    }

    HashSet<String> getHiddenCommands() {
        return hiddenCommands;
    }
}
