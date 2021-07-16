package me.ayushdev.iiitmbot.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;

public interface CommandExecutor {

    String getUsage();

    void execute(String[] args, Member sender, Guild guild, MessageChannel channel);
}
