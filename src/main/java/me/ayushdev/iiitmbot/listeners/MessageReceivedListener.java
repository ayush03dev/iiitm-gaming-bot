package me.ayushdev.iiitmbot.listeners;

import me.ayushdev.iiitmbot.Main;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceivedListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        User author = e.getAuthor();
        Message message = e.getMessage();
        MessageChannel channel = e.getChannel();
        Guild guild = e.getGuild();


        if (channel.getType() != ChannelType.TEXT) return;
        if (author.isBot()) return;

        guild.retrieveMember(author).queue(member -> {
            boolean isAdmin = false;
            for (Role role : member.getRoles()) {
                if (role.getName().equalsIgnoreCase("Admin")) {
                    isAdmin = true;
                    break;
                }
            }

            String msgContent = message.getContentDisplay();
            String[] split = msgContent.split(" ");

            String cmd = split[0];
            if (!cmd.startsWith(".")) return;
            cmd = cmd.replace(".", "");

            String[] args = new String[split.length - 1];

            for (int i = 1; i < split.length; i++) {
                args[i-1] = split[i];
            }

            if (Main.commands.containsKey(cmd)) {
                if (!isAdmin || !channel.getName().equalsIgnoreCase("roles")) {
                    String rolesChannel = guild.getTextChannelsByName("roles", true).get(0).getAsMention();
                    channel.sendMessage("Command is no longer supported. Please get your role from the " + rolesChannel + " channel!")
                            .queue();
                    return;
                }
                Main.commands.get(cmd).execute(args, e.getMember(), e.getGuild(), channel);
            }
        });
    }
}
