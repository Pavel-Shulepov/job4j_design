package ru.job4j.list;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    Object[] container;
    private int size;
    private int modCount = 0;

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    public static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;


    public SimpleArray() {
        container = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        modCount++;
        add(model, container, size);
    }

    private void add(T e, Object[] container, int s) {
        if (s == container.length) {
            container = grow(size + 1);
        }
        container[s] = e;
        size = s + 1;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = container.length;
        if (oldCapacity > 0 || container != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = newLength(oldCapacity,
                    minCapacity - oldCapacity,
                    oldCapacity >> 1);
            container = Arrays.copyOf(container, newCapacity);
        } else {
            container = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
        return container;
    }

    private static int newLength(int oldLength, int minGrowth, int prefGrowth) {
        int newLength = Math.max(minGrowth, prefGrowth) + oldLength;
        if (newLength - MAX_ARRAY_LENGTH <= 0) {
            return newLength;
        }
        return hugeLength(oldLength, minGrowth);
    }

    private static int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) {
            throw new OutOfMemoryError("Required array length too large");
        }
        if (minLength <= MAX_ARRAY_LENGTH) {
            return MAX_ARRAY_LENGTH;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleItr();
    }

    private class SimpleItr implements Iterator<T> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        SimpleItr() {

        }

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            checkForComodification();
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] container = SimpleArray.this.container;
            if (i >= container.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            lastRet = i;
            return (T) container[lastRet];
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}