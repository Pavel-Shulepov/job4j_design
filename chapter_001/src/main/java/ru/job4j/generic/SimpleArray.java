package ru.job4j.generic;

import java.util.*;

public class SimpleArray<T> {
    private final Object[] data;
    private int pointer = 0;
    private int size = 0;

    public SimpleArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("The size of the array cannot be less than 0: " + capacity);
        }
        data = new Object[capacity];
    }

    public void add(T model) {
        Objects.checkIndex(pointer, data.length);
        size++;
        data[pointer++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        data[index] = model;
    }

    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removed = get(index);
        shiftData(index);
        pointer--;
        size--;
        return removed;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) data[index];
    }

    private void shiftData(int index) {
        Object[] temp = Arrays.copyOf(data, index);
        System.arraycopy(data, index, data, 0, data.length - index);
        System.arraycopy(temp, 0, data, data.length - index, index);
        for (int i = index; i < data.length; i++) {
            data[index] = data[index + 1];
        }
        data[data.length - 1] = null;
    }

    public Iterator<T> iterator() {
        return new SimpleIt();
    }

    private class SimpleIt implements Iterator<T> {
        Object[] data = SimpleArray.this.data;
        private int cursor = 0;

        SimpleIt() {
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) data[cursor++];
        }
    }
}
