package ru.job4j.io;

import io.Config;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithComment() {
        String path = "./../app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Pavel Shulepov"));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("#login"), isEmptyOrNullString());
        assertThat(config.value("spring"), isEmptyOrNullString());
        assertThat(config.value("param"), isEmptyOrNullString());
    }
}
