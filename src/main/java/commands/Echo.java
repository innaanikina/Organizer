package commands;

import bot.Bot;

import java.util.regex.Pattern;

public class Echo {
    public static String echo(Bot bot, String command) {
        if (Pattern.matches("эхо\\s*", command)) {
            return "Все молчат, а ты купи слона.";
        }
        return "Все говорят: \"" + command.substring(4).trim() + "\", а ты купи слона.";
    }
}
