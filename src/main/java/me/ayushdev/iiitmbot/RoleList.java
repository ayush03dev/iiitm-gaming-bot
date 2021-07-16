package me.ayushdev.iiitmbot;

public class RoleList {

    private static final RoleList instance = new RoleList();

    public static RoleList getInstance() {
        return instance;
    }

    public String[] getYearRoles() {
        return new String[]{"First Year", "Second Year", "Third Year", "Fourth Year", "Fifth Year"};
    }

    public String[] getGameRoles() {
        return new String[]{"Valorant", "CS:GO", "BGMI", "GTA 5", "PUBG", "Rocket League", "Rainbow Six Siege", "COD Mobile"};
    }
}
