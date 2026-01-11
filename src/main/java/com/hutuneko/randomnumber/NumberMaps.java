package com.hutuneko.randomnumber;

import java.util.*;

public class NumberMaps {
    private static final Map<Character, Character> NUMBER_MAP = new HashMap<>();
    private static final Map<Integer, Integer> NUMBER_MAP2 = new HashMap<>();

    public static Map<Character, Character> getNumberMap() {
        if (NUMBER_MAP.isEmpty()) reset();
        return NUMBER_MAP;
    }

    public static Map<Integer, Integer> getNumberMap2() {
        if (NUMBER_MAP2.isEmpty()) reset();
        return NUMBER_MAP2;
    }

    public static synchronized void reset() {
        NUMBER_MAP.clear();
        NUMBER_MAP2.clear();

        List<Character> digits = new ArrayList<>(List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        List<Character> shuffled = new ArrayList<>(digits);
        Collections.shuffle(shuffled);

        for (int i = 0; i < 10; i++) {
            char originalChar = digits.get(i);
            char targetChar = shuffled.get(i);

            NUMBER_MAP.put(originalChar, targetChar);
            NUMBER_MAP2.put((int) originalChar, (int) targetChar);
        }
    }
}