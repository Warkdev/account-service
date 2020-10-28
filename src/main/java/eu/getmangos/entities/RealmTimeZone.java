package eu.getmangos.entities;

public enum RealmTimeZone {
    DEVELOPMENT(1),
    US(2),
    OCEANIC(3),
    LATIN_AMERICA(4),
    TOURNAMENT(5),
    KOREA(6),
    TOURNAMENT_2(7),
    ENGLISH(8),
    GERMAN(9),
    FRENCH(10),
    SPANISH(11),
    RUSSIAN(12),
    TOURNAMENT_3(13),
    TAIWAN(14),
    TOURNAMENT_4(15),
    CHINA(16),
    CN1(17),
    CN2(18),
    CN3(19),
    CN4(20),
    CN5(21),
    CN6(22),
    CN7(23),
    CN8(24),
    TOURNAMENT_5(25),
    TEST_SERVER(26),
    TOURNAMENT_6(27),
    QA_SERVER(28),
    CN9(29);

    public final int zone;

    private RealmTimeZone(int zone){
        this.zone = zone;
    }
}
