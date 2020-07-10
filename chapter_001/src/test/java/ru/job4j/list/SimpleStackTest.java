package ru.job4j.list;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.NoSuchElementException;

public class SimpleStackTest {

    @Test
    public void whenPushThenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertThat(stack.pop(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void test() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.deleteLast(), is(3));
        assertThat(list.deleteLast(), is(2));
        assertThat(list.deleteLast(), is(1));
        list.deleteFirst();
    }
}
