package com.adventofcode.day5;

import lombok.Builder;
import lombok.Data;

import java.util.Deque;
import java.util.List;
import java.util.Queue;

@Data
@Builder
public class IntComputerContext {

    private List<Integer> instructions;
    private Queue<Integer> inputs;
    private Deque<Integer> outputs;
    private Integer instructionIndex;
    private Boolean isRunning;

}
