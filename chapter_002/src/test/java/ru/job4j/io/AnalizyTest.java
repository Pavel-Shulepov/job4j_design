package ru.job4j.io;

import io.Analizy;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void whenWriteLogThenRead() throws IOException {
        String logPath = "./../server.log";
        List<String> unavailableList = List.of("10:57:01;10:59:01", "11:01:02;11:02:02");
        Analizy.unavailable(logPath, "unavailable.csv");
        assertThat(FileUtils.readLines(new File("unavailable.csv")), is(unavailableList));
    }

}
