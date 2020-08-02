package ru.job4j.io;

import io.Analizy;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenWriteLogThenRead() throws IOException {
        String logPath = "./../server.log";
        List<String> unavailableList = List.of("10:57:01;10:59:01", "11:01:02;11:02:02");
        File unFile = folder.newFile("unavailable.csv");
        Analizy.unavailable(logPath, unFile.getAbsolutePath());
        assertThat(FileUtils.readLines(unFile), is(unavailableList));
    }

}
