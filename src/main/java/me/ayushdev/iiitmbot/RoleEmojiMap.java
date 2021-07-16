package me.ayushdev.iiitmbot;

import com.vdurmont.emoji.EmojiParser;

import java.util.Map;
import java.util.TreeMap;

public class RoleEmojiMap {

    private String[] roles;
    private Map<String, String> map;

    public RoleEmojiMap(String[] roles) {
        this.roles = roles;
        this.map = new TreeMap<>();

        int i = 97; // ASCII code for small a

        for (String role : roles) {
            String toParse = ":regional_indicator_symbol_" + (char) i + ':';
            String emoji = EmojiParser.parseToUnicode(toParse);
            map.put(emoji, role);
            i++;
        }
    }

    public String[] getRoles() {
        return roles;
    }

    public String[] getEmojis() {
        return map.keySet().toArray(new String[0]);
    }

    public String getRoleFromEmoji(String unicode) {
        if (!map.containsKey(unicode)) return null;
        return map.get(unicode);
    }

    public String getEmojiFromRole(String role) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(role)) return entry.getKey();
        }

        return null;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
