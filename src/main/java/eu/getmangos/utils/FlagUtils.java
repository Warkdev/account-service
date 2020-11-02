package eu.getmangos.utils;

/**
 * This class must help to manage flags that are using bits inside integer
 * E.g: 2 = 0010, meaning that the second flag is set and all the others are not.
 * @author GetMangos
 */
public class FlagUtils {

    /**
     * Provides the byte value at the position X, starting from the right.
     * E.g: The short 260 is represented as 00000000 00010100 in memory, 00010100 must be returned.
     * @param flags The flags value from which we need to extract the byte.
     * @param pos The byte position to be extracted. This value must be included between 1 and 2.
     * @return The corresponding byte value (as a short) or -1 if the position provided is incorrect.
     */
    public static short getByte(short flags, int pos) {
        if(pos < 1 || pos > 2) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 2");
        }
        return (short) (flags >> (8 * (pos-1)) & 0xFF);
    }

    /**
     * Set the byte value at the given position.
     * @param field The original value.
     * @param pos The position of the byte to be updated.
     * @param value The new value for the given byte.
     * @return A short (2-bytes size) primitive updated with the provided value
     */
    public static short setByte(short field, int pos, short value) {
        if(pos < 1 || pos > 2) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 2");
        }

        switch(pos) {
            case 1:
                field = (short) (field & 0xFF00);
                break;
            case 2:
                field = (short) (field & 0x00FF);
                break;
        }

        return (short) (field | (value << (8* (pos-1))));
    }

    /**
     * Provides the byte value at the position X, starting from the right.
     * E.g: The int 260 is represented as 00000000 00000000 00000000 00010100 in memory, 00010100 must be returned.
     * @param flags The flags value from which we need to extract the byte.
     * @param pos The byte position to be extracted. This value must be included between 1 and 4.
     * @return The corresponding byte value (as a short) or -1 if the position provided is incorrect.
     */
    public static short getByte(int flags, int pos) {
        if(pos < 1 || pos > 4) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 4");
        }
        return (short) (flags >> (8 * (pos-1)) & 0xFF);
    }

    /**
     * Set the byte value at the given position.
     * @param field The original value.
     * @param pos The position of the byte to be updated.
     * @param value The new value for the given byte.
     * @return An integer (4-bytes size) primitive updated with the provided value
     */
    public static int setByte(int field, int pos, short value) {
        if(pos < 1 || pos > 4) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 4");
        }

        switch(pos) {
            case 1:
                field = field & 0xFFFFFF00;
                break;
            case 2:
                field = field & 0xFFFF00FF;
                break;
            case 3:
                field = field & 0xFF00FFFF;
                break;
            case 4:
                field = field & 0x00FFFFFF;
                break;
        }

        return (field | (value << (8* (pos-1))));
    }

    /**
     * Provides the byte value at the position X, starting from the right.
     * @param flags The flags value from which we need to extract the byte.
     * @param pos The byte position to be extracted. This value must be included between 1 and 8.
     * @return The corresponding byte value (as a short) or -1 if the position provided is incorrect.
     */
    public static short getByte(long flags, int pos) {
        if(pos < 1 || pos > 8) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 8");
        }
        return (short) (flags >> (8 * (pos-1)) & 0xFF);
    }

    /**
     * Set the byte value at the given position.
     * @param field The original value.
     * @param pos The position of the byte to be updated.
     * @param value The new value for the given byte.
     * @return A long (8-bytes size) primitive updated with the provided value
     */
    public static long setByte(long field, int pos, short value) {
        if(pos < 1 || pos > 8) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 8");
        }

        switch(pos) {
            case 1:
                field = field & 0xFFFFFFFFFFFFFF00l;
                break;
            case 2:
                field = field & 0xFFFFFFFFFFFF00FFl;
                break;
            case 3:
                field = field & 0xFFFFFFFFFF00FFFFl;
                break;
            case 4:
                field = field & 0xFFFFFFFF00FFFFFFl;
                break;
            case 5:
                field = field & 0xFFFFFF00FFFFFFFFl;
                break;
            case 6:
                field = field & 0xFFFF00FFFFFFFFFFl;
                break;
            case 7:
                field = field & 0xFF00FFFFFFFFFFFFl;
                break;
            case 8:
                field = field & 0x00FFFFFFFFFFFFFFl;
                break;
        }

        return (field | (((long) value) << (8* (pos-1))));
    }

    /**
     * Indicates whether the field has the given flag.
     * E.g: The byte 00000100 has the 3rd flag set, which means that the flag value should be 8.
     * @param field The byte value, represented as short for signed reasons, for which the flag needs to be compared. This value is truncated, which means that only the first byte is taken into account.
     * @param flag The flag to be found.
     * @return
     */
    public static boolean hasFlag(byte field, byte flag) {
        return ((field & flag) == flag);
    }

    /**
     * Indicates whether the field has the given flag.
     * E.g: The byte 00000100 has the 3rd flag set, which means that the flag value should be 8.
     * @param field The byte value, represented as short for signed reasons, for which the flag needs to be compared. This value is truncated, which means that only the first byte is taken into account.
     * @param flag The flag to be found.
     * @return
     */
    public static boolean hasFlag(short field, short flag) {
        return ((field & flag) == flag);
    }

    /**
     * Indicates whether the field has the given flag.
     * E.g: The byte 00000100 has the 3rd flag set, which means that the flag value should be 8.
     * @param field The byte value, represented as short for signed reasons, for which the flag needs to be compared. This value is truncated, which means that only the first byte is taken into account.
     * @param flag The flag to be found.
     * @return
     */
    public static boolean hasFlag(int field, int flag) {
        return ((field & flag) == flag);
    }

    /**
     * Indicates whether the field has the given flag.
     * E.g: The byte 00000100 has the 3rd flag set, which means that the flag value should be 8.
     * @param field The byte value, represented as short for signed reasons, for which the flag needs to be compared. This value is truncated, which means that only the first byte is taken into account.
     * @param flag The flag to be found.
     * @return
     */
    public static boolean hasFlag(long field, long flag) {
        return ((field & flag) == flag);
    }

    /**
     * Indicates whether the field has the given flag set.
     * E.g: The byte 00000100 has the 3rd flag set.
     * @param field The byte value, represented as short for signed reasons, for which the flag needs to be compared. This value is truncated, which means that only the first byte is taken into account.
     * @param pos The position of the flag that has to be set.
     * @return
     */
    public static boolean isSet(byte field, int pos) {
        if(pos < 1 || pos > 8) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 8");
        }
        int flag = (int) Math.pow(2, (pos-1));
        return hasFlag(field, flag);
    }

    /**
     * Indicates whether the field has the given flag set.
     * E.g: The byte 00000100 has the 3rd flag set.
     * @param field The byte value, represented as short for signed reasons, for which the flag needs to be compared. This value is truncated, which means that only the first byte is taken into account.
     * @param pos The position of the flag that has to be set.
     * @return
     */
    public static boolean isSet(short field, int pos) {
        if(pos < 1 || pos > 16) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 16");
        }
        int flag = (int) Math.pow(2, (pos-1));
        return hasFlag(field, flag);
    }

    /**
     * Indicates whether the field has the given flag set.
     * E.g: The byte 00000100 has the 3rd flag set.
     * @param field The byte value, represented as short for signed reasons, for which the flag needs to be compared. This value is truncated, which means that only the first byte is taken into account.
     * @param pos The position of the flag that has to be set.
     * @return
     */
    public static boolean isSet(int field, int pos) {
        if(pos < 1 || pos > 32) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 32");
        }
        long flag = (long) Math.pow(2, (pos-1));
        return hasFlag(field, flag);
    }

    /**
     * Indicates whether the field has the given flag set.
     * E.g: The byte 00000100 has the 3rd flag set.
     * @param field The byte value, represented as short for signed reasons, for which the flag needs to be compared. This value is truncated, which means that only the first byte is taken into account.
     * @param pos The position of the flag that has to be set.
     * @return
     */
    public static boolean isSet(long field, int pos) {
        if(pos < 1 || pos > 64) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 64");
        }
        long flag = (long) Math.pow(2, (pos-1));
        return hasFlag(field, flag);
    }

    /**
     * Returns the value of the field with the flag at the position 'pos' set at the value 'value'.
     * @param field The byte value for which the flag needs to be set/unset. This value is truncated, which means that only the first byte is taken into account.
     * @param pos The position of the flag to be set/unset. This value must be included between 1 or 8.
     * @param value The boolean flag indicating whether the flag must be set (=true) or unset (=false)
     * @return The byte value, converted as short for signed reason, with the flag set/unset. -1 is returned if the position is incorrect.
     */
    public static byte setFlag(byte field, int pos, boolean value) {
        if(pos < 1 || pos > 8) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 8");
        }

        short flag = (short) Math.pow(2, (pos-1));

        if(value) {
            field |= flag;
        } else {
            field &= ~flag;
        }
        return field;
    }

    /**
     * Returns the value of the field with the flag at the position 'pos' set at the value 'value'.
     * @param field The byte value for which the flag needs to be set/unset. This value is truncated, which means that only the first byte is taken into account.
     * @param pos The position of the flag to be set/unset. This value must be included between 1 or 8.
     * @param value The boolean flag indicating whether the flag must be set (=true) or unset (=false)
     * @return The byte value, converted as short for signed reason, with the flag set/unset. -1 is returned if the position is incorrect.
     */
    public static short setFlag(short field, int pos, boolean value) {
        if(pos < 1 || pos > 16) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 16");
        }

        int flag = (int) Math.pow(2, (pos-1));

        if(value) {
            field |= flag;
        } else {
            field &= ~flag;
        }
        return field;
    }

    /**
     * Returns the value of the field with the flag at the position 'pos' set at the value 'value'.
     * @param field The byte value for which the flag needs to be set/unset. This value is truncated, which means that only the first byte is taken into account.
     * @param pos The position of the flag to be set/unset. This value must be included between 1 or 16.
     * @param value The boolean flag indicating whether the flag must be set (=true) or unset (=false)
     * @return The byte value, converted as short for signed reason, with the flag set/unset. -1 is returned if the position is incorrect.
     */
    public static int setFlag(int field, int pos, boolean value) {
        if(pos < 1 || pos > 32) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 32");
        }

        long flag = (long) Math.pow(2, (pos-1));

        if(value) {
            field |= flag;
        } else {
            field &= ~flag;
        }
        return field;
    }

    /**
     * Returns the value of the field with the flag at the position 'pos' set at the value 'value'.
     * @param field The byte value for which the flag needs to be set/unset. This value is truncated, which means that only the first byte is taken into account.
     * @param pos The position of the flag to be set/unset. This value must be included between 1 or 8.
     * @param value The boolean flag indicating whether the flag must be set (=true) or unset (=false)
     * @return The byte value, converted as short for signed reason, with the flag set/unset. -1 is returned if the position is incorrect.
     */
    public static long setFlag(long field, int pos, boolean value) {
        if(pos < 1 || pos > 64) {
            throw new IllegalArgumentException("Pos value is not defined between 1 and 64");
        }

        long flag = (long) Math.pow(2, (pos-1));

        if(value) {
            field |= flag;
        } else {
            field &= ~flag;
        }
        return field;
    }
}