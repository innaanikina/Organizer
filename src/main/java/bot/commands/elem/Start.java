package bot.commands.elem;

import bot.LogicBot;

public class Start {
    public static String start(LogicBot bot, String command) {
        return "Привет, я бот!\n Напиши 'помощь', и я расскажу, что умею :)";
    }

    public static String help(LogicBot bot, String command) {
        bot.statusActive = "MENU";
        bot.userService.updateStatusActiveByUserId(bot.id, "MENU");
        return "Давай посмотрим, что я умею:\n\n" +
                " * помощь - список команд\n" +
                " * учеба - информация для учебы\n" +
                " * книги - информация для чтения\n" +
                " * авторы - те, кто создал меня";
    }
}
