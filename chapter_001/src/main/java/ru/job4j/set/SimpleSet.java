package ru.job4j.set;

import ru.job4j.list.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> {

    private final SimpleArray<T> simpleArray = new SimpleArray<>();

    public void add(T model) {
        if (contains(model)) {
            throw new IllegalArgumentException("Список уже содержит элемент:" + model);
        }
        simpleArray.add(model);
    }

    public boolean contains(T value) {
        boolean contain = false;
        for (T el : simpleArray) {
            if (Objects.equals(el, value)) {
                contain = true;
                break;
            }
        }
        return contain;
    }

    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }
}
