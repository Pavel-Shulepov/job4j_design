package ru.job4j.exam;

import java.util.*;

public class FreezeStr {

    // Сложность O(n) - n кол-во букв
    public static boolean eq(String left, String right) {
        var leftA = left.split("");
        var rightA = right.split("");
        Map<String, Integer> leftMap = new HashMap<>();
        for (String letter : leftA) {
            leftMap.merge(letter, 1, (key, count) -> ++count);
        }
        Map<String, Integer> rightMap = new HashMap<>();
        for (String letter : rightA) {
            rightMap.merge(letter, 1, (key, count) -> ++count);
        }
        return leftMap.equals(rightMap);
    }
}
