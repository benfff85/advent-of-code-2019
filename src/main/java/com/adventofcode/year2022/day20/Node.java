package com.adventofcode.year2022.day20;


import lombok.Getter;

public class Node {

    @Getter
    private final Integer value;

    public Node(String input) {
        value = Integer.parseInt(input);
    }

    public String toString() {
        return value.toString();
    }

}
