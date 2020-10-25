package eu.getmangos.entities;

public enum Locale {
    ENGLISH(0),
    KOREAN(1),
    FRENCH(2),
    GERMAN(3),
    CHINESE(4),
    TAIWANESE(5),
    SPANISH_SPAIN(6),
    SPANISH_LATIN_AMERICA(7),
    RUSSIAN(8);

    public final int loc;

    private Locale(int loc){
        this.loc = loc;
    }

}
