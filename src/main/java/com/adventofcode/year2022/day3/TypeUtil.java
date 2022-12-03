package com.adventofcode.year2022.day3;

import java.util.List;

public class TypeUtil {

    private TypeUtil() {
        // Masking Default Constructor
    }

    private static final List<String> priorities = List.of("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));

    public static Integer getPriority(String type) {
        return priorities.indexOf(type) + 1;
    }

}
