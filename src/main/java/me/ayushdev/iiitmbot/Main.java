package me.ayushdev.iiitmbot;

import me.ayushdev.iiitmbot.commands.CommandExecutor;
import me.ayushdev.iiitmbot.commands.GameCommand;
import me.ayushdev.iiitmbot.commands.YearCommand;
import me.ayushdev.iiitmbot.listeners.MessageReceivedListener;
import me.ayushdev.iiitmbot.listeners.ReactionAddListener;
import me.ayushdev.iiitmbot.listeners.ReactionRemoveListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static JDA jda;
    public static Map<String, CommandExecutor> commands;

    public static RoleEmojiMap YEAR_MAP;
    public static RoleEmojiMap GAME_MAP;

    public static void main(String[] args) throws LoginException {
        YEAR_MAP = new RoleEmojiMap(RoleList.getInstance().getYearRoles());
        GAME_MAP = new RoleEmojiMap(RoleList.getInstance().getGameRoles());

        try {
            jda = JDABuilder.createDefault("INSERT TOKEN HERE").addEventListeners(
                    new MessageReceivedListener(), new ReactionAddListener(), new ReactionRemoveListener()
            ).build();
            jda.awaitReady();
            System.out.println("Ready!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        commands = new HashMap<>();
        commands.put("year", new YearCommand());
        commands.put("role", new YearCommand());
        commands.put("game", new GameCommand());

    }
}
