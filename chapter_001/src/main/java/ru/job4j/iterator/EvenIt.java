package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] data;
    private int nextEven = 0;

    public EvenIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (isEven(data[nextEven])) {
            return true;
        }
        while (nextEven < data.length - 1) {
            if (isEven(data[nextEven])) {
                break;
            } else {
                nextEven++;
            }
        }
        return isEven(data[nextEven]);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[nextEven++];
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
