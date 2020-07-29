package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import io.EvenNumberFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class EvenNumberFileTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private final String out = "1\r\n"
            + "5\r\n"
            + "15\r\n"
            + "8 - четное \r\n"
            + "10 - четное \r\n"
            + "16 - четное \r\n"
            + "17\r\n"
            + "13\r\n"
            + "11\r\n"
            + "19\r\n"
            + "20 - четное \r\n";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void whenReadFileThenConsoleOut() {
        EvenNumberFile.readFile("even.txt");
        assertThat(out, is(outContent.toString()));
    }

}
