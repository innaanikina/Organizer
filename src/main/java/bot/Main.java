package bot;

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
    private BotLogic botLogic;
    private static Reader reader = new Reader();

    private static ConcurrentHashMap<Long, BotLogic> users = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException, TelegramApiException, ClassNotFoundException {
       try {
            Main.setBotToken(Key.getToken());
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            bot = new Main();
            restore();

            telegramBotsApi.registerBot(bot);

        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            try {
                processingMessage(message);
            } catch (TelegramApiException | NullPointerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /// Message ///
    public void processingMessage(Message message) throws IOException, TelegramApiException {
        var chatId = message.getChatId();

        if (!(users.containsKey(chatId))) {
            users.put(chatId, new BotLogic());
        }
        botLogic = users.get(chatId);
        var result = botLogic.getAnswer(message.getText());
        result = EmojiParser.parseToUnicode(result);
        botLogic.updateStatusActive(botLogic);

        sendMsg(message, result);
        save();
    }

    private void sendMsg(Message message, String text) {
        if (text.equals(""))
            return;

        SendMessage sendMessage = getSendMessage(message, text);
        sendMessage.enableMarkdown(false);

        try {
            addButtons(sendMessage, message);
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
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
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        int count = 0;

        for (String command : currentCommands) {
            if (command != null){
                keyboardFirstRow.add(new KeyboardButton(command));
                count++;
            }

            if (count == 3) {
                keyboardRowList.add(keyboardFirstRow);
                keyboardFirstRow = new KeyboardRow();
                count = 0;
            }
        } // внешний вид

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }
}
