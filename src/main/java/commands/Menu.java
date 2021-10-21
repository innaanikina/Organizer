package commands;

import bot.BotLogic;
import bot.State;

public class Menu {
    public static String help(BotLogic bot, String command) {
        return "Давай посмотрим, что я умею:\n\n" +
                " * помощь - список команд\n" +
                " * учеба - информация для учебы\n" +
                " * книги - информация для чтения\n" +
                " * авторы - те, кто создал меня";
    }

    public static String quitToMenu(BotLogic bot, String command) {
        bot.statusActive = State.MENU;
        return "Вы в главном меню";
    }
}
