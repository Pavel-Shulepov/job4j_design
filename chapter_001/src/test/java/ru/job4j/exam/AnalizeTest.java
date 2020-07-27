package ru.job4j.exam;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.job4j.exam.Analize.User;
import static ru.job4j.exam.Analize.Info;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AnalizeTest {

    @Test
    public void whenAddedDeletedChangedThenTest() {
        List<User> prevList = new ArrayList<>();
        prevList.add(new User(1, "User_1"));
        prevList.add(new User(2, "User_2"));
        prevList.add(new User(3, "User_3"));
        prevList.add(new User(4, "User_4"));
        prevList.add(new User(5, "User_5"));
        prevList.add(new User(6, "User_6"));
        prevList.add(new User(7, "User_7"));
        prevList.add(new User(8, "User_8"));

        List<User> newList = new ArrayList<>();
        newList.add(new User(1, "User_1"));
        newList.add(new User(2, "User_2"));
        newList.add(new User(3, "Change_User_3"));
        newList.add(new User(4, "Change_User_4"));
        newList.add(new User(9, "User_9"));
        newList.add(new User(10, "User_10"));
        newList.add(new User(11, "User_11"));

        Info info = Analize.diff(prevList, newList);

        assertThat(info.getAdded(), is(3));
        assertThat(info.getChanged(), is(2));
        assertThat(info.getDeleted(), is(4));
    }

}
