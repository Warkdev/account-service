package eu.getmangos.dto;

public enum Expansion {
    VANILLA(0),
    BURNING_CRUSADE(1),
    WOTLK(2),
    CATACLYSM(3),
    MOP(4),
    WOD(5),
    LEGION(6),
    BFA(7);

    public final int exp;

    private Expansion(int exp) {
        this.exp = exp;
    }

    public static Expansion convert(int expansion) {
        for(Expansion ex : values()) {
            if(ex.exp == expansion) {
                return ex;
            }
        }
        return null;
    }
}
