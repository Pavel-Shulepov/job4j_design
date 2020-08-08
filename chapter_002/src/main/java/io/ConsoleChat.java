package io;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {

    private static final String PHRASES_FILE = "phrases.txt";
    private static final String LOG_FILE = "chatLog.txt";

    public static void main(String[] args) {
        doChat();
    }

    private static void doChat() {
        Scanner scanner = new Scanner(System.in);
        String action = "";
        boolean chatAnswers = true;

        try (BufferedReader phrases = new BufferedReader(new FileReader(PHRASES_FILE))) {
            List<String> phraseList = phrases.lines().collect(Collectors.toList());
            Random random = new Random();
            try (PrintWriter logger = new PrintWriter(new BufferedOutputStream(new FileOutputStream(LOG_FILE)))) {
                while (!"закончить".equals(action.toLowerCase())) {
                    action = scanner.nextLine();
                    logger.println(action);
                    switch (action.toLowerCase()) {
                        case "стоп":
                            chatAnswers = false;
                            break;
                        case "продолжить":
                            chatAnswers = true;
                            break;
                        default:
                            if (chatAnswers && !"закончить".equals(action.toLowerCase())) {
                                String answer = phraseList.get(random.nextInt(phraseList.size()));
                                logger.println(answer);
                                System.out.println(answer);
                            }
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("До свидания!");
    }

}
