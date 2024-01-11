package aljolen;

import java.util.Properties;

public class BotSettings extends Properties {

    private final String token = getProperty("token");
    private final String username = getProperty("username");

    public BotSettings(Properties properties) {
        super(properties);
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}
