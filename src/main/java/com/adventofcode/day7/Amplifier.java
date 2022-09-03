package com.adventofcode.day7;

import com.adventofcode.day5.IntComputer;
import com.adventofcode.day5.IntComputerContext;
import lombok.Getter;

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


    public Amplifier(String name, List<Integer> instructions) {
        this.name = name;
        intComputerContext = generateIntComputerContext(instructions);
    }

    public IntComputerContext run(Queue<Integer> intComputerInputs) {
        intComputerContext.getInputs().addAll(intComputerInputs);
        return intComputer.process(intComputerContext);
    }

    private IntComputerContext generateIntComputerContext(List<Integer> instructions) {
        return IntComputerContext.builder()
                .instructions(new ArrayList<>(instructions))
                .instructionIndex(0)
                .inputs(new LinkedList<>())
                .outputs(new LinkedList<>())
                .isRunning(TRUE)
                .build();
    }

}
