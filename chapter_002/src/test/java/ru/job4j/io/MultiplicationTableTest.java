package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import io.MultiplicationTable;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class MultiplicationTableTest {

    final static String TABLE = "1\t2\t3\t4\t5\t6\t7\t8\t9\t\n"
            + "2\t4\t6\t8\t10\t12\t14\t16\t18\t\n"
            + "3\t6\t9\t12\t15\t18\t21\t24\t27\t\n"
            + "4\t8\t12\t16\t20\t24\t28\t32\t36\t\n"
            + "5\t10\t15\t20\t25\t30\t35\t40\t45\t\n"
            + "6\t12\t18\t24\t30\t36\t42\t48\t54\t\n"
            + "7\t14\t21\t28\t35\t42\t49\t56\t63\t\n"
            + "8\t16\t24\t32\t40\t48\t56\t64\t72\t\n"
            + "9\t18\t27\t36\t45\t54\t63\t72\t81\t\n";

    @Test
    public void whenSaveTableThenReadFromFile() throws IOException {
        var fileName = "multiTable.txt";
        MultiplicationTable.saveToFile(fileName);
        assertThat(FileUtils.readFileToString(new File(fileName)), is(TABLE));
    }

}
