package eu.getmangos.dto;

public enum RealmType {
    NORMAL(0),
    PVP(1),
    NORMAL_UNUSED(2),
    RP_PVE(6),
    RP_PVP(8);

    public final int type;

    private RealmType(int type){
        this.type = type;
    }

    public static RealmType convert(int type) {
        for(RealmType t : values()) {
            if(t.type == type) {
                return t;
            }
        }
        return null;
    }
}
