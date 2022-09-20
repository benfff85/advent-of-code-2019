package com.adventofcode.day13;

import com.adventofcode.day5.IntComputer;
import com.adventofcode.day5.IntComputerContext;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.math.BigInteger;
import java.util.List;
import java.util.*;

import static java.lang.Boolean.TRUE;

@Slf4j
public class Arcade {

    private final IntComputer intComputer = new IntComputer();
    private final IntComputerContext intComputerContext;

    private final Map<Point, Integer> grid = new HashMap<>();

    public Arcade(List<BigInteger> instructions) {
        intComputerContext = generateIntComputerContext(instructions);
    }

    private IntComputerContext generateIntComputerContext(List<BigInteger> instructions) {
        return IntComputerContext.builder()
                .instructions(new ArrayList<>(instructions))
                .instructionIndex(0)
                .inputs(new LinkedList<>())
                .outputs(new LinkedList<>())
                .isRunning(TRUE)
                .relativeBase(0)
                .build();
    }

    public void play() {
        intComputer.process(intComputerContext);
        Deque<BigInteger> output = intComputerContext.getOutputs();
        while (!output.isEmpty()) {
            grid.put(new Point(output.pop().intValue(), output.pop().intValue()), output.pop().intValue());
        }
    }

    public long getCountOfBlocks() {
        return grid.values().stream().filter(t -> t.equals(2)).count();
    }


}
