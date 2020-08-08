package io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleChat {

    public static void main(String[] args) {
        Params params = new Params(args);
        doChat(getPhrases(params.phrases), params.log);
    }

    private static List<String> getPhrases(String file) {
        try (BufferedReader phrases = new BufferedReader(new FileReader(file))) {
            return phrases.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static void doChat(List<String> phraseList, String logFile) {
        List<String> log = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        String action = "";
        boolean chatAnswers = true;
        Random random = new Random();
        while (!"закончить".equals(action.toLowerCase())) {
            action = scanner.nextLine();
            log.add(action);
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
                        log.add(answer);
                        System.out.println(answer);
                    }
                    break;
            }
        }
        System.out.println("До свидания!");
        saveLog(log, logFile);
    }

    private static void saveLog(List<String> log, String logFile) {
        try (PrintWriter logger = new PrintWriter(new BufferedOutputStream(new FileOutputStream(logFile)))) {
            log.forEach(logger::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class Params {
        private String phrases;
        private String log;

        private Params(String[] args) {
            for (String arg : args) {
                var keyValue = arg.split("=");
                if (keyValue.length == 2) {
                    if ("-p".equals(keyValue[0])) {
                        phrases = keyValue[1];
                    } else if ("-l".equals(keyValue[0])) {
                        log = keyValue[1];
                    }
                } else {
                    throw new IllegalArgumentException("Неверный параметр: " + arg);
                }
            }
            if (phrases == null) {
                throw new IllegalArgumentException("Не хватает параметра: -p=[путь] - файл с фразами");
            }
            if (log == null) {
                throw new IllegalArgumentException("Не хватает параметра: -l=[путь] - лог-файл");
            }
        }
    }

}
