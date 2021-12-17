package bot.commands.elem;

import bot.LogicBot;
import bot.services.UserService;

public class Menu {
    public static String help(LogicBot bot, String command) {
        return "Давай посмотрим, что я умею:\n\n" +
                " * помощь - список команд\n" +
                " * учеба - информация для учебы\n" +
                " * книги - информация для чтения\n" +
                " * авторы - те, кто создал меня";
    }

    public static String quitToMenu(LogicBot bot, String command) {
        bot.statusActive = "MENU";
        UserService.updateStatusActiveByUserId(bot.id, "MENU");
        return "Вы в главном меню";
    }
}
