package com.adventofcode.day5;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import static com.adventofcode.day5.OperationType.*;
import static com.adventofcode.day5.ParameterMode.POSITION;
import static com.adventofcode.day5.ParameterMode.RELATIVE;
import static java.lang.Boolean.FALSE;

@Slf4j
public class IntComputer {

    IntComputerContext context;

    public Integer process(List<Integer> instructions, Queue<Integer> input) {
        return process(IntComputerContext.builder()
                .instructions(instructions)
                .instructionIndex(0)
                .inputs(input)
                .outputs(new LinkedList<>())
                .relativeBase(0)
                .build())
                .getOutputs().pollLast();
    }

    public IntComputerContext process(IntComputerContext inputContext) {
        this.context = inputContext;
        List<Integer> instructions = context.getInstructions();

        Opcode opcode;
        int i = context.getInstructionIndex();
        while (i < instructions.size()) {
            log.debug("{}", instructions);
            opcode = new Opcode(instructions.get(i));

            if (ADD.equals(opcode.getOperationType())) {
                processAdd(i, opcode);
                i += 4;
            } else if (MULTIPLY.equals(opcode.getOperationType())) {
                processMultiply(i, opcode);
                i += 4;
            } else if (INPUT.equals(opcode.getOperationType())) {
                // If we need input but don't have any return and get some input later from the amplifier feedback loop
                if (context.getInputs().isEmpty()) {
                    context.setInstructionIndex(i);
                    return context;
                }
                processInput(i);
                i += 2;
            } else if (OUTPUT.equals(opcode.getOperationType())) {
                processOutput(i);
                i += 2;
            } else if (JUMP_IF_TRUE.equals(opcode.getOperationType())) {
                // If first param is non-zero set i to second param value
                i = processJumpIfTrue(i, opcode);
            } else if (JUMP_IF_FALSE.equals(opcode.getOperationType())) {
                // If first param is zero set i to second param value
                i = processJumpIfFalse(i, opcode);
            } else if (LESS_THAN.equals(opcode.getOperationType())) {
                // If the first param is less than the second parameter, it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
                processLessThan(i, opcode);
                i += 4;
            } else if (EQUALS.equals(opcode.getOperationType())) {
                // If the first param is equal to the second parameter, it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
                processEquals(i, opcode);
                i += 4;
            } else if (RELATIVE_BASE_OFFSET.equals(opcode.getOperationType())) {
                processRelativeBaseOffset(i, opcode);
                i += 2;
            } else if (TERMINATE.equals(opcode.getOperationType())) {
                context.setIsRunning(FALSE);
                context.setInstructionIndex(i);
                return context;
            } else {
                log.error("Unexpected operator {} at index {}", instructions.get(i), i);
            }
        }
        return null;
    }


    private void processAdd(int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(opcode.getParameterModes().get(0), indexOfOpcode + 1);
        Integer b = getParameterValue(opcode.getParameterModes().get(1), indexOfOpcode + 2);
        Integer c = context.getInstructions().get(indexOfOpcode + 3);
        log.debug("Adding {} + {} and storing at index {}", a, b, c);
        writeValueToIndex(c, a + b);
    }

    private void processMultiply(int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(opcode.getParameterModes().get(0), indexOfOpcode + 1);
        Integer b = getParameterValue(opcode.getParameterModes().get(1), indexOfOpcode + 2);
        Integer c = context.getInstructions().get(indexOfOpcode + 3);
        log.debug("Multiplying {} * {} and storing at index {}", a, b, c);
        writeValueToIndex(c, a * b);
    }

    private void processInput(int indexOfOpcode) {
        Integer value = context.getInputs().poll();
        Integer a = context.getInstructions().get(indexOfOpcode + 1);
        log.debug("Adding input value of {} to index {}", value, a);
        writeValueToIndex(a, value);
    }

    private void processOutput(int indexOfOpcode) {
        Integer a = context.getInstructions().get(context.getInstructions().get(indexOfOpcode + 1));
        context.getOutputs().add(a);
        log.debug("Output: {}", a);
    }

    private int processJumpIfTrue(int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(opcode.getParameterModes().get(0), indexOfOpcode + 1);
        if (a != 0) {
            return getParameterValue(opcode.getParameterModes().get(1), indexOfOpcode + 2);
        }
        return indexOfOpcode + 3;
    }

    private int processJumpIfFalse(int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(opcode.getParameterModes().get(0), indexOfOpcode + 1);
        if (a == 0) {
            return getParameterValue(opcode.getParameterModes().get(1), indexOfOpcode + 2);
        }
        return indexOfOpcode + 3;
    }

    private void processLessThan(int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(opcode.getParameterModes().get(0), indexOfOpcode + 1);
        Integer b = getParameterValue(opcode.getParameterModes().get(1), indexOfOpcode + 2);
        writeValueToIndex(context.getInstructions().get(indexOfOpcode + 3), a < b ? 1 : 0);
    }

    private void processEquals(int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(opcode.getParameterModes().get(0), indexOfOpcode + 1);
        Integer b = getParameterValue(opcode.getParameterModes().get(1), indexOfOpcode + 2);
        writeValueToIndex(context.getInstructions().get(indexOfOpcode + 3), Objects.equals(a, b) ? 1 : 0);
    }

    private void processRelativeBaseOffset(int indexOfOpcode, Opcode opcode) {
        context.setRelativeBase(context.getRelativeBase() + getParameterValue(opcode.getParameterModes().get(0), indexOfOpcode + 1));
    }

    private Integer getParameterValue(ParameterMode parameterMode, Integer indexOfParameter) {
        Integer value = context.getInstructions().get(indexOfParameter);
        if (POSITION.equals(parameterMode)) {
            return context.getInstructions().get(value);
        } else if(RELATIVE.equals(parameterMode)) {
            return context.getInstructions().get(context.getRelativeBase() + value);
        } else {
            return value;
        }
    }

    private void writeValueToIndex(Integer indexToWriteTo, Integer value) {
        context.getInstructions().set(indexToWriteTo, value);
    }

}
