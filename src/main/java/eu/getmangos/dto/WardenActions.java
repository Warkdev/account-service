package eu.getmangos.dto;

public enum WardenActions {
    LOG(0),
    KICK(1),
    BAN(2);

    public final int code;

    private WardenActions(int code) {
        this.code = code;
    }

    public static WardenActions convert(int code) {
        for(WardenActions action : values()) {
            if(action.code == code) {
                return action;
            }
        }

        return null;
    }
}
