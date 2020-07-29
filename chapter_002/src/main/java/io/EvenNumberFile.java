package io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {
        readFile("even.txt");
    }

    public static void readFile(String fileName) {
        try (FileInputStream in = new FileInputStream(fileName)) {

            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            printResult(text.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResult(String text) {
        String[] lines = text.split(System.lineSeparator());
        for (String line : lines) {
            line = isEven(Integer.parseInt(line)) ? line + " - четное " : line;
            System.out.println(line);
        }
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

}
