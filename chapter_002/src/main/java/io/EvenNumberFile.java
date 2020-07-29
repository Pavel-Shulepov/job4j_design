package io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {
        readFile("even.txt");
    }

    public static void readFile(String fileName) {
        try (FileInputStream in = new FileInputStream(fileName)) {
            printResult(in.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResult(byte[] buff) {
        String[] lines = new String(buff).split(System.lineSeparator());
        for (String line : lines) {
            line = isEven(Integer.parseInt(line)) ? line + " - четное " : line;
            System.out.println(line);
        }
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

}
