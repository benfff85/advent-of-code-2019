package com.adventofcode.day5;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

@Data
@Builder
public class IntComputerContext {

    private List<BigInteger> instructions;
    private Queue<BigInteger> inputs;
    private Deque<BigInteger> outputs;
    private Integer instructionIndex;
    private boolean isRunning;
    private Integer relativeBase;

}
