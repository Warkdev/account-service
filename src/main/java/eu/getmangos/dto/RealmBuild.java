package eu.getmangos.dto;

public enum RealmBuild {
    MANGOS_ZERO("5875 6005 6141"),
    MANGOS_ONE("8606"),
    MANGOS_TWO("12340"),
    MANGOS_THREE("15595"),
    MANGOS_FOUR("18414");

    public final String build;

    private RealmBuild(String build){
        this.build = build;
    }

    public static RealmBuild convert(String build) {
        for(RealmBuild b : values()) {
            if(b.build.equals(build)) {
                return b;
            }
        }
        return null;
    }
}
