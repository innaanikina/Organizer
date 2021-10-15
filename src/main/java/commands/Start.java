package commands;
import bot.BotLogic;
import bot.State;

public class Start {
    public static String start(BotLogic bot, String command) {
        return "Привет, я бот!\n Напиши 'помощь', и я расскажу, что умею :)";
    }

    public static String help(BotLogic bot, String command) {
        bot.statusActive = State.state.MENU;
        return Menu.help(bot, command);
    }
}
