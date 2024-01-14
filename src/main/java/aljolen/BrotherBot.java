package aljolen;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import static org.telegram.abilitybots.api.util.AbilityUtils.getChatId;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.abilitybots.api.toggle.BareboneToggle;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.BiConsumer;

public class BrotherBot extends AbilityBot {


    private final BotSettings settings;

    public BrotherBot(BotSettings settings) {
        super(settings.getToken(), settings.getUsername(), new BareboneToggle());
        this.settings = settings;
    }

    public Ability sayHelloWorldOnStart() {
        return Ability
                .builder()
                .name("start")
                .locality(USER)
                .privacy(PUBLIC)
                .action(ctx -> sendHelloWorld(ctx))
                .build();
    }

    public Reply sayCringeOnLocation() {
        BiConsumer<BaseAbilityBot, Update> action = (bot, upd) -> {
            sendCringe(upd);
        };
        return Reply.of(action, Flag.LOCATION);
    }

    private void sendHelloWorld(MessageContext ctx) {
        silent.send("Hello world!", ctx.chatId());

    }

    private void sendCringe(Update upd) {
        silent.send("Cringe", getChatId(upd));
    }

    @Override
    public long creatorId() {
        return settings.getCreator();
    }


    private InputStream readImage(String fileId){
        GetFile getFileRequest = GetFile
                .builder()
                .fileId(fileId)
                .build();
        try {
            File filePath = execute(getFileRequest);
            return downloadFileAsStream(filePath);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream readDocument(Document document){
        GetFile getFileRequest = GetFile
                .builder()
                .fileId(document.getFileId())
                .build();
        try {
            File filePath = execute(getFileRequest);
            return downloadFileAsStream(filePath);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
