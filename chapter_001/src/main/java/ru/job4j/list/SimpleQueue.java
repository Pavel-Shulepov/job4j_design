package ru.job4j.list;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    //FIX-ME Непонятно по заданию для чего второй стэк?
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        return in.poll();
    }

    public void push(T value) {
        in.push(value);
    }
}
