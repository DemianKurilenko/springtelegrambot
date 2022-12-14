package com.demiank.telegram.model;

import com.vdurmont.emoji.EmojiParser;
import lombok.Getter;

@Getter
public class TelegramMessageCommand {
    private String cmd;
    private String argument;
    private boolean command = false;

    public TelegramMessageCommand(String msg) {
        if (msg != null && (msg.startsWith("/"))) {
            this.command = true;
            int idx = msg.indexOf(" ");
            this.cmd = msg.substring(0, (idx != -1) ? idx:msg.length());
            if (idx != -1) {
                this.argument = msg.substring(idx + 1);
            }
        }
        else if(msg != null && EmojiParser.extractEmojis(msg).size() > 0) {
            this.command = true;
            cmd = msg;
        }
        else {
            this.argument = msg;
        }
    }

    public TelegramMessageCommand(String command, String argument) {
        this.cmd = command;
        this.argument = argument;
    }
}
