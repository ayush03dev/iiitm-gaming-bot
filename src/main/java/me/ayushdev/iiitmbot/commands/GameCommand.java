package me.ayushdev.iiitmbot.commands;

import me.ayushdev.iiitmbot.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.Arrays;

public class GameCommand implements CommandExecutor {
    @Override
    public String getUsage() {
        return ".game";
    }

    @Override
    public void execute(String[] args, Member sender, Guild guild, MessageChannel channel) {
        StringBuilder builder = new StringBuilder("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n\n**React with emojis to get the respective roles of the games you actively play:**\n\n");

        for (String role : Main.GAME_MAP.getRoles()) {

            builder.append(Main.GAME_MAP.getEmojiFromRole(role) + ' ' +
                    guild.getRolesByName(role, true).get(0).getAsMention() + "**\n");
        }

        builder.append("\n▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        channel.sendMessage(builder.toString()).queue(message -> {
            Arrays.stream(Main.GAME_MAP.getEmojis()).forEach(emoji -> {
                message.addReaction(emoji).queue();
            });
        });
    }
}
