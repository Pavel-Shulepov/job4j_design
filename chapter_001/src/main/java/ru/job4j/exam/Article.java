package ru.job4j.exam;

import java.util.HashSet;
import java.util.List;

public class Article {

    // Сложность new HashSet<>(textToWordArray(origin)) - О(n) - n кол-во слов.
    // цикл в java.util.AbstractCollection.addAll

    // Сложность containsAll O(n) - n кол-слов в новом тексте
    // java.util.AbstractCollection.containsAll
    public static boolean generateBy(String origin, String line) {
        return new HashSet<>(textToWordArray(origin)).containsAll(new HashSet<>(textToWordArray(line)));
    }

    // Сложность метода replaceAll О(n) - где n - количество слов в тексте. Причина - цикл поиска в методе
    // Matcher.replaceAll(java.lang.String)
    //
    // Сложность метода split О(n) - где n - количество слов в тексте. Причина - цикл поиска в методе
    // java.util.regex.Pattern.split(java.lang.CharSequence, int)
    public static List<String> textToWordArray(String text) {
        return List.of(text.replaceAll("[^\\p{L}\\p{Nd}]+", " ").split("\\s+"));
    }

}
