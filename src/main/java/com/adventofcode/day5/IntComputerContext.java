package com.adventofcode.day5;

import lombok.Data;

import java.util.List;
import java.util.Queue;

@Data
public class IntComputerContext {

    private List<Integer> instructions;
    private Queue<Integer> inputs;
    private Queue<Integer> outputs;
    private Integer instructionIndex;
    private Boolean isRunning;

}
