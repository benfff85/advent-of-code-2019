package com.adventofcode.year2020.day2;

import static org.springframework.util.StringUtils.countOccurrencesOf;

public class PasswordPolicyA implements PasswordPolicy {

    private final Integer minOccurrences;
    private final Integer maxOccurrences;
    private final String character;
    private final String password;

    public PasswordPolicyA(String input) {
        minOccurrences = Integer.parseInt(input.split("-")[0]);
        maxOccurrences = Integer.parseInt(input.split("-")[1].split(" ")[0]);
        character = input.split(" ")[1].substring(0, 1);
        password = input.split(" ")[2];
    }

    public boolean isValid() {
        int count = countOccurrencesOf(password, character);
        return count >= minOccurrences && count <= maxOccurrences;
    }

}
