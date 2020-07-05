package ru.job4j.generic;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.hamcrest.core.IsNull;

import org.junit.Test;

public class StoreTest {

    @Test(expected = IllegalArgumentException.class)
    public void userStoreTest() {
        UserStore userStore = new UserStore();
        userStore.add(new User("1", "User_1"));
        userStore.add(new User("2", "User_2"));
        userStore.add(new User("3", "User_3"));
        userStore.add(new User("4", "User_4"));
        userStore.add(new User("5", "User_5"));
        assertThat(userStore.findById("3").getName(), is("User_3"));
        userStore.replace("4", new User("6", "User_6"));
        assertThat(userStore.findById("4"), is(IsNull.nullValue()));
        assertThat(userStore.findById("6").getName(), is("User_6"));
        userStore.delete("2");
        assertThat(userStore.findById("2"), is(IsNull.nullValue()));
        userStore.replace("1", new User("1", "User_1_1"));
        assertThat(userStore.findById("1").getName(), is("User_1_1"));
        userStore.add(new User("3", "User_3_1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void roleStoreTest() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Role_1"));
        roleStore.add(new Role("2", "Role_2"));
        roleStore.add(new Role("3", "Role_3"));
        roleStore.add(new Role("4", "Role_4"));
        roleStore.add(new Role("5", "Role_5"));
        assertThat(roleStore.findById("3").getName(), is("Role_3"));
        roleStore.replace("4", new Role("6", "Role_6"));
        assertThat(roleStore.findById("4"), is(IsNull.nullValue()));
        assertThat(roleStore.findById("6").getName(), is("Role_6"));
        roleStore.delete("2");
        assertThat(roleStore.findById("2"), is(IsNull.nullValue()));
        roleStore.replace("1", new Role("1", "Role_1_1"));
        assertThat(roleStore.findById("1").getName(), is("Role_1_1"));
        roleStore.add(new Role("3", "Role_3_1"));
    }

}
