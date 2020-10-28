package eu.getmangos.entities;

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
}
