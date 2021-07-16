package me.ayushdev.iiitmbot.commands;

import me.ayushdev.iiitmbot.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.Arrays;

public class YearCommand implements CommandExecutor {

    @Override
    public String getUsage() {
        return ".year";
    }

    @Override
    public void execute(String[] args, Member sender, Guild guild,  MessageChannel channel) {
        StringBuilder builder = new StringBuilder("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n\n**React with an emoji to get the respective role of the year you are currently studying in:**\n\n");

        for (String role : Main.YEAR_MAP.getRoles()) {
            builder.append(Main.YEAR_MAP.getEmojiFromRole(role) + ' ' +
                    guild.getRolesByName(role, true).get(0).getAsMention() + "\n");
        }

        builder.append("\n▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        channel.sendMessage(builder.toString()).queue(message -> {
            Arrays.stream(Main.YEAR_MAP.getEmojis()).forEach(emoji -> {
                message.addReaction(emoji).queue();
            });
        });
    }

}
