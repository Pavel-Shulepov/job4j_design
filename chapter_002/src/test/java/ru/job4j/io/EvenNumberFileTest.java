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

    private final String out = "1\n"
            + "5\n"
            + "15\n"
            + "8 - четное \n"
            + "10 - четное \n"
            + "16 - четное \n"
            + "17\n"
            + "13\n"
            + "11\n"
            + "19\n"
            + "20 - четное \n";

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
