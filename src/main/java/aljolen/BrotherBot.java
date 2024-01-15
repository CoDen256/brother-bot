package aljolen;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import static org.telegram.abilitybots.api.util.AbilityUtils.getChatId;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.abilitybots.api.toggle.BareboneToggle;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.InputStream;

public class BrotherBot extends AbilityBot {


    private final BotSettings settings;

    public BrotherBot(BotSettings settings) {
        super(settings.getToken(), settings.getUsername(), new BareboneToggle());
        this.settings = settings;
    }

    /** Defines the actions of the bot to perform as a response to a certain type of commands
     */
    public Ability sayHelloWorldOnStart() {
        return Ability
                .builder()
                .name("start")      // the name of the command
                .locality(USER)     // Type of chats, where this command applies (USER, GROUP etc.)
                .privacy(PUBLIC)    // who can invoke this command (PUBLIC=anybody, ADMIN=only admin)
                .action(ctx -> sendHelloWorld(ctx.update()))    // action to perform on the invocation of the command
                .build();
    }

    /** Defines an action to perform on reply to certain kind of Update (new message, new pin, change of wallpaper etc.).
     * Specifies filter, that defines what kind of messages this reply should be applied to.
     * */
    public Reply sayCringeOnSticker() {
        return Reply.of(
                (bot, upd) -> sendCringe(upd),   // Action on reply
                upd -> isSticker(upd)            // What to reply to? Filter the message only with a sticker
        );
    }

    /** Filters messages that have sticker in it
     * @return true, if the update has message and has sticker in it, otherwise false
     * */
    private boolean isSticker(Update update){
        return update.hasMessage() && update.getMessage().hasSticker();
    }

    private void sendHelloWorld(Update upd) {
        silent.send("Hello world!", getChatId(upd));
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
