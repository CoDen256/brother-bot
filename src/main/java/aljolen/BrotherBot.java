package aljolen;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;

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

    @Override
    public long creatorId() {
        return settings.getCreator();
    }
}
