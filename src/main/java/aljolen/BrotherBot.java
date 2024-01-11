package aljolen;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import static org.telegram.abilitybots.api.util.AbilityUtils.getChatId;

import org.apache.commons.io.IOUtils;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BrotherBot extends AbilityBot {


    private final BotSettings settings;

    public BrotherBot(BotSettings settings) {
        super(settings.getToken(), settings.getUsername());
        this.settings = settings;
    }

    public Ability sayHelloWorld() {
        return Ability
                .builder()
                .name("hello")
                .info("says hello world!")
                .locality(USER)
                .privacy(PUBLIC)
                .action(ctx -> silent.send("Hello world!", ctx.chatId()))
                .build();
    }

    public Reply sayYuckOnImage() {
        BiConsumer<BaseAbilityBot, Update> action = (bot, upd) -> {
//            upd.getMessage().getDocument().
            GetFile getFile = new GetFile();
            getFile.setFileId(upd.getMessage().getDocument().getFileId());

            try {
                File filePath = execute(getFile);
                InputStream file = downloadFileAsStream(filePath);
                FileOutputStream fileOutputStream = new FileOutputStream(upd.getMessage().getDocument().getFileName());
                IOUtils.copy(file, fileOutputStream);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            silent.send("Yuck", getChatId(upd));
        };

        return Reply.of(action, Flag.DOCUMENT);
    }

    @Override
    public long creatorId() {
        return settings.getCreator();
    }
}
