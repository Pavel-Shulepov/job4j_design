package ru.job4j.list;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinkedTest {

    @Test
    public void whenAddThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        linked.add(5);
        linked.add(6);
        linked.add(7);
        linked.add(8);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNullLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.revert();
    }

    @Test
    public void whenRevertOnceElement() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
    }
}
