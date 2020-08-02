package io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    public static void unavailable(String source, String target) {
        String start = null;
        String end;
        List<String> unavailable = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            while (true) {
                String log = in.readLine();
                if (log == null) {
                    break;
                }
                if (start == null && (log.contains("400") || log.contains("500"))) {
                    start = log.replaceFirst("\\d{3}\\s*", "");
                }
                if (start != null && (!log.contains("400") && !log.contains("500"))) {
                    end = log.replaceFirst("\\d{3}\\s*", "");
                    unavailable.add(String.format("%s;%s", start, end));
                    start = null;
                }
            }
            writeResult(target, unavailable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeResult(String path, List<String> info) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(path))) {
            info.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
