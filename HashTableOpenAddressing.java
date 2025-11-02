public class HashTableOpenAddressing implements HashTable {

    private final OpenAddressEntry[] table;
    private final CollisionStrategy strategy; // LINEAR o QUADRATIC

    // idx = h + c1*i + c2*i^2  (para cuadrática)
    private final int c1 = 1, c2 = 1;

    public HashTableOpenAddressing(CollisionStrategy strategy) {
        if (strategy == CollisionStrategy.CHAINING) {
            throw new IllegalArgumentException("Para CHAINING usar HashTableChaining");
        }
        this.strategy = strategy;
        this.table = new OpenAddressEntry[HashUtil.TABLE_SIZE];
        for (int i = 0; i < table.length; i++) table[i] = new OpenAddressEntry();
    }

    @Override
    public boolean insert(int key) {
        int h = HashUtil.hash(key);
        for (int i = 0; i < HashUtil.TABLE_SIZE; i++) {
            int idx = probeIndex(h, i);
            OpenAddressEntry e = table[idx];
            if (e.key == null || e.deleted) {
                e.key = key;
                e.deleted = false;
                return true;
            }
            if (!e.deleted && e.key != null && e.key == key) return false; // ya estaba
        }
        return false; // llena
    }

    @Override
    public boolean contains(int key) {
        int h = HashUtil.hash(key);
        for (int i = 0; i < HashUtil.TABLE_SIZE; i++) {
            int idx = probeIndex(h, i);
            OpenAddressEntry e = table[idx];
            if (e.key == null && !e.deleted) return false; // celda virgen
            if (!e.deleted && e.key != null && e.key == key) return true;
        }
        return false;
    }

    @Override
    public boolean remove(int key) {
        int h = HashUtil.hash(key);
        for (int i = 0; i < HashUtil.TABLE_SIZE; i++) {
            int idx = probeIndex(h, i);
            OpenAddressEntry e = table[idx];
            if (e.key == null && !e.deleted) return false;
            if (!e.deleted && e.key != null && e.key == key) {
                e.key = null;
                e.deleted = true; // tombstone
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (OpenAddressEntry e : table) { e.key = null; e.deleted = false; }
    }

    @Override
    public void printTable() {
        System.out.println("Tabla (OpenAddressing - " + strategy + "):");
        for (int i = 0; i < table.length; i++) {
            OpenAddressEntry e = table[i];
            String cell = (e.key == null) ? (e.deleted ? "[DEL]" : "[]") : "[" + e.key + "]";
            System.out.printf("%2d -> %s%n", i, cell);
        }
        System.out.println();
    }

    private int probeIndex(int h, int i) {
        switch (strategy) {
            case LINEAR:    return (h + i) % HashUtil.TABLE_SIZE;
            case QUADRATIC: return (h + c1*i + c2*i*i) % HashUtil.TABLE_SIZE;
            default:        return h;
        }
    }
}
