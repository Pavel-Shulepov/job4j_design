package ru.job4j.io;

import io.Search;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    List<String> result = List.of("1.java", "2.java");

    @Before
    public void setUp() throws IOException {
        folder.newFile("1.txt");
        folder.newFile("1.xml");
        folder.newFile("1.java");
        folder.newFile("2.java");
    }

    @Test
    public void searchTest() throws IOException {
        List<Path> paths = Search.search(folder.getRoot().toPath(), "java");
        assertThat(paths.stream().map(p -> p.getFileName().toString()).collect(Collectors.toList()), is(result));
    }

}
