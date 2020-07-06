package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {

    @Test
    public void whenAddThenGet() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();
        linkedList.add("first");
        String rsl = linkedList.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();
        linkedList.add("first");
        String rsl = linkedList.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();
        linkedList.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();
        linkedList.add("first");
        linkedList.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();
        linkedList.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();
        linkedList.add("first");
        Iterator<String> it = linkedList.iterator();
        linkedList.add("second");
        it.next();
    }
    
}
