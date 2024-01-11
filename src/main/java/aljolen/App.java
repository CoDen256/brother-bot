package aljolen;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            BotSettings settings = loadSettings("app.config");
            telegramBotsApi.registerBot(new BrotherBot(settings));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static BotSettings loadSettings(String filename){
        Properties properties = loadProperties(filename);
        return new BotSettings(properties);
    }
    public static Properties loadProperties(String filename){
        Properties properties = new Properties();
        try (FileInputStream stream = new FileInputStream(filename)) {
            properties.load(stream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
