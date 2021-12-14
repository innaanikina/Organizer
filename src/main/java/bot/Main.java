package bot;

import bot.command.organizer.Organizer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vdurmont.emoji.EmojiParser;
import javassist.Modifier;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Main extends BotPrimitive {

    public static Main bot;
    private LogicBot botLogic;
    private static final Reader reader = new Reader();

    private static ConcurrentHashMap<Long, LogicBot> users = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException, TelegramApiException {
        try {
            Main.setBotToken(Key.getToken());
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            bot = new Main();
           // restore();

            telegramBotsApi.registerBot(bot);
            Thread t = new CheckDeadlines();
            t.start();

        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public static class CheckDeadlines extends Thread {
        @Override
        public void run() {
            while (true) {
                for (Long a : users.keySet()) {
                    String res = Organizer.checkDeadlines(users.get(a));
                    if (!res.equals("")) {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(a.toString());
                        sendMessage.setText(res);
                        try {
                            bot.execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            try {
                processingMessage(message);
            } catch (TelegramApiException | NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /// Message ///
    public void processingMessage(Message message) throws IOException, TelegramApiException {
        Long chatId = message.getChatId();

        if (!(users.containsKey(chatId))) {
            users.put(chatId, new LogicBot());
        }
        botLogic = users.get(chatId);
        String result = botLogic.getAnswer(message.getText());
        result = EmojiParser.parseToUnicode(result);
        botLogic.updateStatusActive(botLogic);

        sendMsg(message, result);
        save();
    }

    private void sendMsg(Message message, String text) {
        if (text.equals(""))
            return;

        SendMessage sendMessage = getSendMessage(message, text);
        //sendMessage.enableMarkdown(false);

        try {
            addButtons(sendMessage, message);
            bot.execute(sendMessage);
        } catch (TelegramApiException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /// Store Info ///
    public static void save() throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        FileWriter writer = reader.getFileWriter("/src/main/resources/users.out");

        writer.write(gson.toJson(users));
        writer.close();
    }

    public static void restore() {
        Gson gson = new Gson();
        var json = reader.readFile("/src/main/resources/users.out");

        Type collectionType = new TypeToken<ConcurrentHashMap<Long, BotLogic>>() {
        }.getType();

        if (!json.equals("")) {
            users = gson.fromJson(json, collectionType);
        }
    }

    /// Keyboard ///
    private void addButtons(SendMessage sendMessage, Message message) {
        var currentCommands = botLogic.getCurrentCommands();
        var replyKeyboardMarkup = getReplyKeyboardMarkup();//инициализация клавиатуры

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        int count = 0;

        for (String command : currentCommands) {
            if (command != null) {
                keyboardRow.add(new KeyboardButton(command));
                count++;
            }

            if (count == 3) {
                keyboardRowList.add(keyboardRow);
                keyboardRow = new KeyboardRow();
                count = 0;
            }
        } // внешний вид

        keyboardRowList.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }
}
