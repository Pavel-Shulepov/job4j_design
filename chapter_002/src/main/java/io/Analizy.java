package io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {

    public static void unavailable(String source, String target) {
        List<String> log = readFile(source);
        List<String> unavailable = new ArrayList<>();
        String start = null;
        String end;
        for (String l : log) {
            if (start == null && (l.contains("400") || l.contains("500"))) {
                start = l.replaceFirst("\\d{3}\\s*", "");
            }
            if (start != null && (!l.contains("400") && !l.contains("500"))) {
                end = l.replaceFirst("\\d{3}\\s*", "");
                unavailable.add(String.format("%s;%s", start, end));
                start = null;
            }
        }
        writeResult(target, unavailable);
    }

    private static List<String> readFile(String path) {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            return in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static void writeResult(String path, List<String> info) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(path))) {
            info.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
