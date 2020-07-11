package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UserWithOutEqualsHashTest {

    private static final Object object = new Object();

    @Test
    public void whenUserWithOutEqualsHash() {
        Map<User, Object> map = new HashMap<>();
        Calendar date = Calendar.getInstance();
        date.set(1984, Calendar.JANUARY, 10);
        User userOne = new User("Pavel", 2, date);
        User userTwo = new User("Pavel", 2, date);
        map.put(userOne, object);
        map.put(userTwo, object);
        map.entrySet().forEach(System.out::println);
    }

}
