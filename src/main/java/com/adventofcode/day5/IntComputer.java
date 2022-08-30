package com.adventofcode.day5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Queue;

import static com.adventofcode.day5.OperationType.*;
import static com.adventofcode.day5.ParameterMode.POSITION;

@Slf4j
@Component
public class IntComputer {


    public Integer process(List<Integer> instructions, Queue<Integer> input) {

        int latestOutput = -999;

        Opcode opcode;
        int i = 0;
        while (i < instructions.size()) {

            opcode = new Opcode(instructions.get(i));

            if (ADD.equals(opcode.getOperationType())) {
                processAdd(instructions, i, opcode);
                i += 4;
            } else if (MULTIPLY.equals(opcode.getOperationType())) {
                processMultiply(instructions, i, opcode);
                i += 4;
            } else if (INPUT.equals(opcode.getOperationType())) {
                processInput(instructions, i, input.poll());
                i += 2;
            } else if (OUTPUT.equals(opcode.getOperationType())) {
                latestOutput = processOutput(instructions, i);
                i += 2;
            } else if (JUMP_IF_TRUE.equals(opcode.getOperationType())) {
                // If first param is non-zero set i to second param value
                i = processJumpIfTrue(instructions, i, opcode);
            } else if (JUMP_IF_FALSE.equals(opcode.getOperationType())) {
                // If first param is zero set i to second param value
                i = processJumpIfFalse(instructions, i, opcode);
            } else if (LESS_THAN.equals(opcode.getOperationType())) {
                // If the first param is less than the second parameter, it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
                processLessThan(instructions, i, opcode);
                i += 4;
            } else if (EQUALS.equals(opcode.getOperationType())) {
                // If the first param is equal to the second parameter, it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
                processEquals(instructions, i, opcode);
                i += 4;
            } else if (TERMINATE.equals(opcode.getOperationType())) {
                return latestOutput;
            } else {
                log.error("Unexpected operator {} at index {}", instructions.get(i), i);
            }
        }
        return 0;
    }


    private void processAdd(List<Integer> input, int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(input, opcode.getParameterModes().get(0), indexOfOpcode + 1);
        Integer b = getParameterValue(input, opcode.getParameterModes().get(1), indexOfOpcode + 2);
        writeValueToIndex(input, input.get(indexOfOpcode + 3), a + b);
    }

    private void processMultiply(List<Integer> input, int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(input, opcode.getParameterModes().get(0), indexOfOpcode + 1);
        Integer b = getParameterValue(input, opcode.getParameterModes().get(1), indexOfOpcode + 2);
        writeValueToIndex(input, input.get(indexOfOpcode + 3), a * b);
    }

    private void processInput(List<Integer> input, int indexOfOpcode, Integer value) {
        writeValueToIndex(input, input.get(indexOfOpcode + 1), value);
    }

    private Integer processOutput(List<Integer> input, int indexOfOpcode) {
        Integer a = input.get(indexOfOpcode + 1);
        return input.get(a);
    }

    private int processJumpIfTrue(List<Integer> input, int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(input, opcode.getParameterModes().get(0), indexOfOpcode + 1);
        if (a != 0) {
            return getParameterValue(input, opcode.getParameterModes().get(1), indexOfOpcode + 2);
        }
        return indexOfOpcode + 3;
    }

    private int processJumpIfFalse(List<Integer> input, int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(input, opcode.getParameterModes().get(0), indexOfOpcode + 1);
        if (a == 0) {
            return getParameterValue(input, opcode.getParameterModes().get(1), indexOfOpcode + 2);
        }
        return indexOfOpcode + 3;
    }

    private void processLessThan(List<Integer> input, int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(input, opcode.getParameterModes().get(0), indexOfOpcode + 1);
        Integer b = getParameterValue(input, opcode.getParameterModes().get(1), indexOfOpcode + 2);
        writeValueToIndex(input, input.get(indexOfOpcode + 3), a < b ? 1 : 0);
    }

    private void processEquals(List<Integer> input, int indexOfOpcode, Opcode opcode) {
        Integer a = getParameterValue(input, opcode.getParameterModes().get(0), indexOfOpcode + 1);
        Integer b = getParameterValue(input, opcode.getParameterModes().get(1), indexOfOpcode + 2);
        writeValueToIndex(input, input.get(indexOfOpcode + 3), Objects.equals(a, b) ? 1 : 0);
    }

    private Integer getParameterValue(List<Integer> input, ParameterMode parameterMode, Integer indexOfParameter) {
        Integer value = input.get(indexOfParameter);
        if (POSITION.equals(parameterMode)) {
            return input.get(value);
        } else {
            return value;
        }
    }

    private void writeValueToIndex(List<Integer> input, int indexToWriteTo, int value) {
        input.set(indexToWriteTo, value);
    }

}
