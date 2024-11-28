package com.adventofcode.year2020.day2;

public class PasswordPolicyB implements PasswordPolicy {

    private final Integer positionA;
    private final Integer positionB;
    private final char character;
    private final String password;

    public PasswordPolicyB(String input) {
        positionA = Integer.parseInt(input.split("-")[0]) - 1;
        positionB = Integer.parseInt(input.split("-")[1].split(" ")[0]) - 1;
        character = input.split(" ")[1].substring(0, 1).toCharArray()[0];
        password = input.split(" ")[2];
    }

    public boolean isValid() {
        return password.charAt(positionA) == character ^ password.charAt(positionB) == character;
    }

}
