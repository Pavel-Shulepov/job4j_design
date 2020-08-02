package io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {

    public static void unavailable(String source, String target) {
        String start = null;
        String end;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
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
                    out.println(String.format("%s;%s", start, end));
                    start = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
