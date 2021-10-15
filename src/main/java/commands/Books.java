package commands;

import bot.BotLogic;
import bot.State;

public class Books {
    public static String booksHelp(BotLogic bot, String command){
        return "Доступные действия:\n\n* список - список книг\n* меню - выход в меню";
    }

    public static String getBooksList(BotLogic bot, String command){
        // todo чтение списка книг из файла
        return "Красная шапочка \nЗолушка";
    }

    public static String quitToBookMenu(BotLogic bot, String command) {
        bot.statusActive = State.state.BOOKS;
        return "Вы в меню книг";
    }
}
