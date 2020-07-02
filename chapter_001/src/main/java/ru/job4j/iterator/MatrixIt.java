package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return (row < data.length) && (column < data[row].length || row != data.length - 1);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column >= data[row].length) {
            row++;
            column = 0;
        }
        while (data[row].length == 0) {
            row++;
            column = 0;
        }
        return data[row][column++];
    }
}
