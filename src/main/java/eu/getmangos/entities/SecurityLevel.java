package eu.getmangos.entities;

public enum SecurityLevel {
    PLAYER(0),
    MODERATOR(1),
    GAMEMASTER(2),
    ADMINISTRATOR(3),
    CONSOLE_ONLY(4);

    public final int level;

    private SecurityLevel(int level){
        this.level = level;
    }
}
