package ru.job4j.generic;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Iterator;

public class SimpleArrayTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenCapacityIsLessThanZero() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(-10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddItemOverTheLimit() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(1);
        stringSimpleArray.add("1");
        stringSimpleArray.add("2");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetItemOverTheLimit() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(2);
        stringSimpleArray.add("1");
        stringSimpleArray.add("2");
        stringSimpleArray.set(2, "3");
    }

    @Test
    public void whenDeletedItemMatchesIndex() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(2);
        stringSimpleArray.add("1");
        stringSimpleArray.add("2");
        String deleted = stringSimpleArray.remove(0);
        assertThat(deleted, is("1"));
    }

    @Test
    public void whenAfterRemovalTheItemIsAddedToTheEndOfTheList() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(4);
        stringSimpleArray.add("1");
        stringSimpleArray.add("2");
        stringSimpleArray.add("3");
        stringSimpleArray.add("4");
        String deleted = stringSimpleArray.remove(0);
        stringSimpleArray.add("5");
        String added = stringSimpleArray.get(3);
        assertThat(added, is("5"));
    }

    @Test
    public void whenReturnItemCorrespondsToStored() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(4);
        stringSimpleArray.add("1");
        stringSimpleArray.add("2");
        stringSimpleArray.add("3");
        stringSimpleArray.add("4");
        assertThat(stringSimpleArray.get(1), is("2"));
    }

    @Test
    public void whenTheIteratorIterates() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(4);
        stringSimpleArray.add("1");
        stringSimpleArray.add("2");
        stringSimpleArray.add("3");
        stringSimpleArray.add("4");
        Iterator<String> iterator = stringSimpleArray.iterator();
        if (iterator.hasNext()) {
            String byIterator = iterator.next();
            assertThat(byIterator, is("1"));
        }
        if (iterator.hasNext()) {
            String byIterator = iterator.next();
            assertThat(byIterator, is("2"));
        }
    }

    @Test
    public void whenIteratorHasReachedEnd() {
        SimpleArray<String> stringSimpleArray = new SimpleArray<>(4);
        stringSimpleArray.add("1");
        stringSimpleArray.add("2");
        stringSimpleArray.add("3");
        stringSimpleArray.add("4");
        String deleted = stringSimpleArray.remove(1);
        Iterator<String> iterator = stringSimpleArray.iterator();
        int size = 0;
        while (iterator.hasNext()) {
            iterator.next();
            size++;
        }
        assertThat(size, is(3));
    }

}
