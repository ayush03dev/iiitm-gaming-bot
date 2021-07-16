package me.ayushdev.iiitmbot.listeners;

import me.ayushdev.iiitmbot.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionAddListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e) {
        JDA jda = e.getJDA();
        e.retrieveMessage().queue();
        e.retrieveMember().queue();
        e.retrieveUser().queue();
        if (e.getUser().isBot()) return;
        Member member = e.getMember();
        Guild guild = e.getGuild();


        e.getChannel().retrieveMessageById(e.getMessageId()).queue(message -> {
            if (message.getContentDisplay().contains("year")) {

                for (Role role : member.getRoles()) {
                    if (role.getName().endsWith(" Year")) {
                        guild.removeRoleFromMember(member, role).queue();
                    }
                }

                String emoji = e.getReaction().getReactionEmote().getEmoji();
                Role role = guild.getRolesByName(Main.YEAR_MAP.getRoleFromEmoji(emoji), true).get(0);
                if (role != null) {
                    guild.addRoleToMember(member, role).queue();
                }

            } else if (message.getContentDisplay().contains("game")) {
                String emoji = e.getReaction().getReactionEmote().getEmoji();
                Role role = guild.getRolesByName(Main.GAME_MAP.getRoleFromEmoji(emoji), true).get(0);
                if (role != null) {
                    guild.addRoleToMember(member, role).queue();
                }
            }
        });
    }

}
