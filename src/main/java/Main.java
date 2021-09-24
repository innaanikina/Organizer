import bot.Bot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vdurmont.emoji.EmojiParser;
import javassist.Modifier;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;

public class Main extends TelegramLongPollingBot {
    private static final String BOT_NAME = "Organizer Bot";
    private static String BOT_TOKEN = "";

    private static ConcurrentHashMap<Long, Bot> users = new ConcurrentHashMap<>();

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public static Main bot;

    public static void main(String[] args) throws IOException, TelegramApiException, ClassNotFoundException {
        try {
            BOT_TOKEN = Key.get_token();
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
        update.getUpdateId();
        Long chatId = update.getMessage().getChatId();
        String message = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), "");
        String result = "";

        try {
            if (!(users.containsKey(chatId))) {
                users.put(chatId, new Bot());
            }
            result = users.get(chatId).getAnswer(message);
            result = EmojiParser.parseToUnicode(result);
            sendMessage.setText(result);
            sendMessage.setParseMode(ParseMode.HTML);
            bot.execute(sendMessage);
            try {
                save();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        } catch (TelegramApiException | NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void save() throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.substring(0, filePath.length() - 6);
        FileWriter writer = new FileWriter(filePath + "src/main/resources/users.out");
        writer.write(gson.toJson(users));
        writer.close();
    }

    public static void restore() throws IOException, ClassNotFoundException {
        Gson gson = new Gson();
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.substring(0, filePath.length() - 6);
        FileReader reader = new FileReader(filePath + "src/main/resources/users.out");
        StringBuilder json = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            json.append((char) c);
        }
        Type collectionType = new TypeToken<ConcurrentHashMap<Long, Bot>>() {
        }.getType();
        if (!json.toString().equals("")) {
            users = gson.fromJson(json.toString(), collectionType);
        }
        reader.close();
    }
}
