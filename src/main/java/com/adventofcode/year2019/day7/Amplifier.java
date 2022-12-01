package com.adventofcode.year2019.day7;

import com.adventofcode.year2019.day5.IntComputer;
import com.adventofcode.year2019.day5.IntComputerContext;
import lombok.Getter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Boolean.TRUE;

public class Amplifier {

    @Getter
    private final String name;
    private final IntComputer intComputer = new IntComputer();
    private final IntComputerContext intComputerContext;


    public Amplifier(String name, List<BigInteger> instructions) {
        this.name = name;
        intComputerContext = generateIntComputerContext(instructions);
    }

    public IntComputerContext run(Queue<BigInteger> intComputerInputs) {
        intComputerContext.getInputs().addAll(intComputerInputs);
        return intComputer.process(intComputerContext);
    }

    private IntComputerContext generateIntComputerContext(List<BigInteger> instructions) {
        return IntComputerContext.builder()
                .instructions(new ArrayList<>(instructions))
                .instructionIndex(0)
                .inputs(new LinkedList<>())
                .outputs(new LinkedList<>())
                .isRunning(TRUE)
                .build();
    }

}
