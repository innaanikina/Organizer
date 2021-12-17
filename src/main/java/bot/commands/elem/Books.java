package bot.commands.elem;

import bot.LogicBot;
import bot.services.UserService;

public class Books {
    public static String booksHelp(LogicBot bot, String command){
        return "Доступные действия:\n\n* список - список книг\n* меню - выход в меню";
    }

    public static String getBooksList(LogicBot bot, String command){
        // todo чтение списка книг из файла
        return "Красная шапочка \nЗолушка";
    }

    public static String quitToBookMenu(LogicBot bot, String command) {
        bot.statusActive = "BOOKS";
        UserService.updateStatusActiveByUserId(bot.id, "BOOK");
        //менять команды одновременно?
        return "Вы в меню книг";
    }
}
