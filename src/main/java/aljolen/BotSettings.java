package aljolen;

import java.util.Properties;

public class BotSettings extends Properties {

    private final String token = getProperty("token");
    private final String username = getProperty("username");
    private final long creator = Long.parseLong(getProperty("creator"));

    public BotSettings(Properties properties) {
        super(properties);
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public long getCreator() {
        return creator;
    }
}
