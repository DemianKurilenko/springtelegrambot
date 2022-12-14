package com.demiank.telegram.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BotBuilder {

    public enum BotType {
        LONG_POLLING, WEBHOOK
    }

    private BotType type = BotType.LONG_POLLING;

    private String username;
    private String token;
    private String path;
    private int maxThreads = 100;

    public BotBuilder(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public BotBuilder username(String username) {
        this.username = username;
        return this;
    }

    public BotBuilder token(String token) {
        this.token = token;
        return this;
    }

    public BotBuilder path(String path) {
        this.path = path;
        return this;
    }

    public BotBuilder maxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
        return this;
    }

    public BotBuilder path(BotType type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            "type=" + type +
            ", username='" + username + '\'' +
            ", token='" + token + '\'' +
            ", path='" + path + '\'' +
            ", maxThreads=" + maxThreads +
            '}';
    }
}
