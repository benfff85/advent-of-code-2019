package com.adventofcode.year2022.day20;


import lombok.Getter;

@Getter
public class Node {

    private final Long value;

    public Node(String input, Long multiplier) {
        value = Integer.parseInt(input) * multiplier;
    }

    public String toString() {
        return value.toString();
    }

}
