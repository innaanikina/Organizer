package bot.command.elem;

import bot.BotLogic;
import bot.State;

public class Start {
    public static String start(BotLogic bot, String command) {
        return "Привет, я бот!\n Напиши 'помощь', и я расскажу, что умею :)";
    }

    public static String help(BotLogic bot, String command) {
        bot.statusActive = State.MENU;
        return "Давай посмотрим, что я умею:\n\n" +
                " * помощь - список команд\n" +
                " * учеба - информация для учебы\n" +
                " * книги - информация для чтения\n" +
                " * авторы - те, кто создал меня";
    }
}
