package ru.job4j.io;

import io.SearchDuplicates;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchDuplicatesTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    public File file;

    @Before
    public void setUp() throws IOException {
        folder.newFile("1.txt");
        folder.newFile("1.xml");
        folder.newFile("1.java");
        folder.newFile("2.java");
        folder.newFolder("folder");
        file = folder.newFile("folder\\1.java");
    }

    @Test
    public void searchDuplicatesTest() throws IOException {
        assertThat(SearchDuplicates.search(folder.getRoot().toPath()).size() > 0, is(true));
    }

    @Test
    public void whenNotDuplicatesTest() throws IOException {
        file.delete();
        assertThat(SearchDuplicates.search(folder.getRoot().toPath()).size() > 0, is(false));
    }

}
