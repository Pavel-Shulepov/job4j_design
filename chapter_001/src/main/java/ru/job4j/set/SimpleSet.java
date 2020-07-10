package ru.job4j.set;

import ru.job4j.list.SimpleArray;

public class SimpleSet<T> extends SimpleArray<T> {

    @Override
    public void add(T model) {
        if (super.contains(model)) {
            throw new IllegalArgumentException("Список уже содержит элмент:" + model);
        }
        super.add(model);
    }

}
