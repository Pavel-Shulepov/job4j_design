package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        checkId(model);
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = getIndex(id);
        if (index == -1) {
            return false;
        } else {
            mem.set(index, model);
            return true;
        }
    }

    @Override
    public boolean delete(String id) {
        int index = getIndex(id);
        if (index == -1) {
            return false;
        } else {
            return mem.remove(mem.get(index));
        }
    }

    @Override
    public T findById(String id) {
        return mem.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private int getIndex(String id) {
        for (int i = 0; i < mem.size() - 1; i++) {
            if (mem.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private void checkId(T model) {
        if (findById(model.getId()) != null) {
            throw new IllegalArgumentException("Сущность с таким ИД существует!: " + model.getId());
        }
    }
}
