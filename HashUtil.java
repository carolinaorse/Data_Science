public final class HashUtil {
    private HashUtil() {}
    public static final int TABLE_SIZE = 10;
    public static int hash(int x) {
        int h = x % TABLE_SIZE;
        return (h < 0) ? h + TABLE_SIZE : h;
    }
}
