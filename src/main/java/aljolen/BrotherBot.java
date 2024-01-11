package aljolen;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

public class BrotherBot implements LongPollingBot {


    private final BotSettings settings;

    public BrotherBot(BotSettings settings) {
        this.settings = settings;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public BotOptions getOptions() {
        return null;
    }

    @Override
    public void clearWebhook() throws TelegramApiRequestException {

    }

    @Override
    public String getBotUsername() {
        return settings.getUsername();
    }

    @Override
    public String getBotToken() {
        return settings.getToken();
    }
}
