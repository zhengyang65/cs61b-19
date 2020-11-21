
import java.util.*;

public class MyHashMap<K, V>  implements Map61B<K, V>  {

    private int M;        //散列表的大小
    private int N;             //键值对总数
    private double loadFactor;
    private SequentialSearchST1<K, V>[] st;
    private Set<K> hashset;

    /** Initialization */
    public MyHashMap() {
        this(16, 0.75);
    }
    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }
    public MyHashMap(int initialSize, double factor) {
        this.M = initialSize;
        this.loadFactor = factor;
        st = (SequentialSearchST1<K, V>[]) new SequentialSearchST1[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST1();
        }
    }

    /** Hashcode. */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        for (int i = 0; i < M; i++) {
            for (K key : st[i].keys()) {
                st[i].put(key, null);
            }
        }
        N = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        int i = hash(key);
        return st[i].get(key);
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return N;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        // double table size if average length of list >= 10
        if (0.75 * N >= M) {resize(2*M);}

        int i = hash(key);
        if (!st[i].contains(key)) {N++;}
        st[i].put(key, value);
    }

    private void resize(int chains) {
        MyHashMap<K, V> temp = new MyHashMap<>(chains);
        for (int i = 0; i < M; i++) {
            for (K key : st[i].keys()) {
                temp.put(key, (V) st[i].get(key));
            }
        }
        this.M  = temp.M;
        this.N  = temp.N;
        this.st = temp.st;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        hashset = new HashSet<>();
        for (int i = 0; i < M; i++) {
            for (K key : st[i].keys()) {
                hashset.add(key);
            }
        }
        return hashset;
    }
    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIter();
    }
    /** An iterator that iterates over the keys of the dictionary. */
    private class MyHashMapIter implements Iterator<K> {

        /**
         * Create a new ULLMapIter by setting cur to the first node in the
         * linked list that stores the key-value pairs.
         */

        public ArrayList<K> cur;
        public int i = 0;
        MyHashMapIter() {
            cur = (ArrayList) hashset;
        }

        @Override
        public boolean hasNext() {
            return i < cur.size() ;
        }
        @Override
        public K next() {
            K result = cur.get(i);
            i += 1;
            return result;
        }
    }
    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
