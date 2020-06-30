package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (point == data.length) return false;
        if (isEven(data[point])) {
            return true;
        } else {
            point++;
            return hasNext();
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
