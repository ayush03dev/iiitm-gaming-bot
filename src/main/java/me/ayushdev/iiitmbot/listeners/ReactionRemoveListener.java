package me.ayushdev.iiitmbot.listeners;

import me.ayushdev.iiitmbot.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionRemoveListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent e) {
        e.retrieveMessage().queue();
        e.retrieveUser().queue();
        e.retrieveMember().queue(member -> {
            Guild guild = e.getGuild();
            TextChannel channel = e.getChannel();
            channel.retrieveMessageById(e.getMessageId()).queue(message -> {
                if (message.getAuthor().isBot()) {
                    if (message.getContentDisplay().contains("games") || message.getContentDisplay().contains("year")) {
                        String emoji = e.getReaction().getReactionEmote().getEmoji();
                        Role role;

                        if (message.getContentDisplay().contains("games")) {
                            role = guild.getRolesByName(Main.GAME_MAP.getRoleFromEmoji(emoji), true).get(0);
                        } else {
                            role = guild.getRolesByName(Main.YEAR_MAP.getRoleFromEmoji(emoji), true).get(0);
                        }
                        if (role == null) return;
                        guild.removeRoleFromMember(member, role).queue();
                    }
                }
            });
        });

    }

}
