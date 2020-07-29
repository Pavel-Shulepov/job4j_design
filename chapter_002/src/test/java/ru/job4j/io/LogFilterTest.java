package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import io.LogFilter;
import org.junit.Test;

import java.util.List;

public class LogFilterTest {

    private final List<String> resultList = List.of("0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\" 404 1113",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /TrackStudio/ HTTP/1.1\" 404 1110",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] \"GET /TrackStudioNew/ HTTP/1.1\" 404 -");

    @Test
    public void testReadAndFilterFileLines() {
        assertThat(LogFilter.filter("log.txt"), is(resultList));
    }

}
