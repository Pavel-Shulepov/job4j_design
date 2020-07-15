package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements Iterable<V> {

    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final float LOAD_FACTOR = 0.75f;

    private Element<K, V>[] table;
    private int size;
    private int modCount;
    private int threshold;

    public SimpleHashMap() {
    }

    public int size() {
        return size;
    }

    public boolean insert(K key, V value) {
        return putVal(hash(key), key, value);
    }

    public V get(K key) {
        Element<K, V> e = table[(table.length - 1) & hash(key)];
        return e == null ? null : e.value;
    }

    boolean delete(K key) {
        return remove(hash(key), key);
    }

    private boolean remove(int hash, Object key) {
        int i = (table.length - 1) & hash;
        if (table[i] != null && table[i].key.equals(key)) {
            table[i] = null;
            ++modCount;
            --size;
            return true;
        } else {
            return false;
        }
    }

    private boolean putVal(int hash, K key, V value) {
        Element<K, V>[] tab;
        int n, i;
        if ((tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        if ((tab[i = (n - 1) & hash]) == null) {
            tab[i] = new Element<>(hash, key, value);
        } else {
            return false;
        }
        ++modCount;
        if (++size > threshold) {
            resize();
        }
        return true;
    }

    final Element<K, V>[] resize() {
        Element<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY
                    && oldCap >= DEFAULT_INITIAL_CAPACITY) {
                newThr = oldThr << 1;
            }
        } else if (oldThr > 0) {
            newCap = oldThr;
        } else {
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float) newCap * LOAD_FACTOR;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY
                    ? (int) ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"unchecked"})
        Element<K, V>[] newTab = (Element<K, V>[]) new Element[newCap];
        table = newTab;
        if (oldTab != null) {
            System.arraycopy(oldTab, 0, newTab, 0, oldTab.length);
        }
        return newTab;
    }

    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private static class Element<K, V> {
        final int hash;
        final K key;
        V value;

        private Element(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public final K getKey() {
            return key;
        }
        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Element) {
                Element<?, ?> e = (Element<?, ?>) o;
                return Objects.equals(key, e.getKey())
                        && Objects.equals(value, e.getValue());
            }
            return false;
        }
    }

    public Iterator<V> iterator() {
        return new HashIt();
    }

    private class HashIt implements Iterator<V> {
        Element<K, V>[] container = SimpleHashMap.this.table;
        private int cursor = 0;
        private final int expectedModCount = modCount;

        public boolean hasNext() {
            checkForComodification();
            return cursor < container.length && hasNextNotNullValue();
        }

        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return container[cursor++].value;
        }

        private boolean hasNextNotNullValue() {
            var has = false;
            for (int i = cursor; i < container.length; i++) {
                if (container[i] != null) {
                    has = true;
                    cursor = i;
                    break;
                }
            }
            return has;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
