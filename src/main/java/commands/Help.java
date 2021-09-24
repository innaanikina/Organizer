package commands;

import bot.Bot;

public class Help {
    public static String help(Bot bot, String command) {
        return "Давай посмотрим, что я умею:\n" +
                " * помощь - список возможностей\n" +
                " * авторы - те, кто создал меня\n" +
                " * эхо - повторю за тобой\n" +
                " * учеба - информация для учебы";
    }
}
