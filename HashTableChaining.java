import java.util.LinkedList;

public class HashTableChaining implements HashTable {

    private final LinkedList<Integer>[] buckets;

    @SuppressWarnings("unchecked")
    public HashTableChaining() {
        buckets = new LinkedList[HashUtil.TABLE_SIZE];
        for (int i = 0; i < buckets.length; i++) buckets[i] = new LinkedList<>();
    }

    @Override
    public boolean insert(int key) {
        int h = HashUtil.hash(key);
        LinkedList<Integer> list = buckets[h];
        if (list.contains(key)) return false;
        list.addFirst(key);
        return true;
    }

    @Override
    public boolean contains(int key) {
        int h = HashUtil.hash(key);
        return buckets[h].contains(key);
    }

    @Override
    public boolean remove(int key) {
        int h = HashUtil.hash(key);
        return buckets[h].remove((Integer) key);
    }

    @Override
    public void clear() {
        for (LinkedList<Integer> b : buckets) b.clear();
    }

    @Override
    public void printTable() {
        System.out.println("Tabla (Chaining):");
        for (int i = 0; i < buckets.length; i++) {
            System.out.printf("%2d -> %s%n", i, buckets[i].toString());
        }
        System.out.println();
    }
