package bot;

import bot.commands.elem.*;
import bot.commands.organizer.Organizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

class DictCommands {
    private HashMap<String, BiFunction<LogicBot, String, String>> dictStart = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictMenu = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictStudy = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictClasses = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictBooks = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictOrganizer = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictOrganizerAdd = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictOrganizerDel = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictOrganizerDeadline1 = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictOrganizerDeadline2 = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictOrganizerEdit = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictOrganizerEditChoice = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictOrganizerEditTime = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictOrganizerEditTask = new HashMap<>();
    private HashMap<String, BiFunction<LogicBot, String, String>> dictOrganizerComplete = new HashMap<>();

    private HashSet<String> hiddenCommands;

    private transient HashMap<String, HashMap<String, BiFunction<LogicBot, String, String>>> commands = new HashMap<>();

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
    public void getCurrentCommands(String statusActive){
        Set<String> result = commands.get(statusActive).keySet();
    }

    private void setCommands(){
        commands.put("START", dictStart);
        commands.put("MENU", dictMenu);
        commands.put("STUDY", dictStudy);
        commands.put("CLASSES", dictClasses);
        commands.put("BOOKS", dictBooks);
        commands.put("ORGANIZER", dictOrganizer);
        commands.put("ORGANIZER_ADD", dictOrganizerAdd);
        commands.put("ORGANIZER_DELETE", dictOrganizerDel);
        commands.put("ORGANIZER_DEADLINE1", dictOrganizerDeadline1);
        commands.put("ORGANIZER_DEADLINE2", dictOrganizerDeadline2);
        commands.put("ORGANIZER_EDIT", dictOrganizerEdit);
        commands.put("ORGANIZER_EDIT_CHOICE", dictOrganizerEditChoice);
        commands.put("ORGANIZER_EDIT_TIME", dictOrganizerEditTime);
        commands.put("ORGANIZER_EDIT_TASK", dictOrganizerEditTask);
        commands.put("ORGANIZER_COMPLETE", dictOrganizerComplete);
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
        dictMenu.put("органайзер", Organizer::startOrganizer);
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

    private void setDictOrganizer() {
        dictOrganizer.put("помощь", Organizer::organizerHelp);
        dictOrganizer.put("default", NotUnderstand::notUnderstand);
        dictOrganizer.put("меню", Organizer::quit);
        dictOrganizer.put("добавить", Organizer::add);
        dictOrganizer.put("все задания", Organizer::all);
        dictOrganizer.put("удалить задание", Organizer::startDelete);
        dictOrganizer.put("добавить дедлайн", Organizer::startDeadline);
        dictOrganizer.put("редактировать", Organizer::startEdit);
        dictOrganizer.put("сделано", Organizer::startComplete);
        dictOrganizer.put("deadlines", Organizer::showDeadlines);
    }

    private void setDictOrganizerAdd() {
        dictOrganizerAdd.put("default", Organizer::pushTask);
        dictOrganizerAdd.put("назад", Organizer::back);
    }

    private void setDictOrganizerDel() {
        dictOrganizerDel.put("default", Organizer::delete);
        dictOrganizerDel.put("назад", Organizer::back);
    }

    private void setDictDeadline1() {
        dictOrganizerDeadline1.put("default", Organizer::startDeadlineDate);
        dictOrganizerDeadline1.put("назад", Organizer::back);
    }

    private void setDictDeadline2() {
        dictOrganizerDeadline2.put("default", Organizer::pushDeadline);
        dictOrganizerDeadline2.put("назад", Organizer::back);
    }

    private void setDictOrganizerEdit() {
        dictOrganizerEdit.put("default", Organizer::editChoice);
        dictOrganizerEdit.put("назад", Organizer::back);
    }

    private void setDictOrganizerEditChoice() {
        dictOrganizerEditChoice.put("дата", Organizer::editTime);
        dictOrganizerEditChoice.put("задача", Organizer::editTask);
        dictOrganizerEditChoice.put("назад", Organizer::back);
        dictOrganizerEditChoice.put("default", NotUnderstand::notUnderstand);
    }

    private void setDictOrganizerEditTime() {
        dictOrganizerEditTime.put("default", Organizer::pushEditTime);
        dictOrganizerEditTime.put("назад", Organizer::back);
    }

    private void setDictOrganizerEditTask() {
        dictOrganizerEditTask.put("default", Organizer::pushEditTask);
        dictOrganizerEditTask.put("назад", Organizer::back);
    }

    private void setDictOrganizerComplete() {
        dictOrganizerComplete.put("default", Organizer::completed);
        dictOrganizerComplete.put("назад", Organizer::back);
    }

    private void setHiddenCommands(){
        Reader reader = new Reader();
        hiddenCommands = reader.readFileArray("src/main/resources/hiddenCommand.txt");
    }

    HashMap<String, HashMap<String, BiFunction<LogicBot, String, String>>> getCommands(){
        return commands;
    }

    HashSet<String> getHiddenCommands() {
        return hiddenCommands;
    }
}