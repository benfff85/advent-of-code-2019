package com.adventofcode.day16;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;


@Slf4j
@Component("controller-day-16")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/day-16.txt");
    }

    public DailyAnswer execute() {
        List<Byte> inputSignal = inputHelper.parseStringToSingleDigitIntList(puzzleInput.get(0));
        List<Byte> output = processPhases(inputSignal, 100);
        answer.setPart1(output.subList(0, 8));
        log.info("Part 1: {}", answer.getPart1());

        List<Byte> inputSignalPart2 = new ArrayList<>(inputSignal.size() * 10000);
        for (int i = 0; i < 10000; i++) {
            inputSignalPart2.addAll(inputSignal);
        }
        List<Byte> outputPart2 = processPhases(inputSignalPart2, 100);

        return answer;
    }

    private List<Byte> processPhases(List<Byte> input, Integer phaseCount) {

        int signalSize = input.size();
        List<Byte> inputSignal = new ArrayList<>(signalSize);
//        List<Byte> outputSignal = new ArrayList<>(signalSize);
        List<Byte> outputSignal = new LinkedList<>();
        inputSignal.addAll(input);

        long start = System.currentTimeMillis();

        // Loop through phases
        for (int phase = 0; phase < phaseCount; phase++) {
            outputSignal.clear();

            // Loop through digits in input signal
            for (int e = 0; e < signalSize; e++) {
                start = processElement(signalSize, inputSignal, outputSignal, start, phase, e);
            }
            log.info("Done Phase: {}", phase);

            inputSignal.clear();
            inputSignal.addAll(outputSignal);
        }

        return outputSignal;
    }

    private long processElement(int signalSize, List<Byte> inputSignal, List<Byte> outputSignal, long start, int phase, int e) {
        int blockGroupSize;
        int blockSize;
        int halfBlockGroupSize;
        int sum;

        //                start = System.currentTimeMillis();
        blockSize = e + 1;
        blockGroupSize = (4 * blockSize);
        halfBlockGroupSize = blockGroupSize / 2;
        sum = 0;
        int startingIndexOfNegOnes;

        // Loop through blocks starting points
        for (int startingIndexOfOnes = e; startingIndexOfOnes < signalSize; startingIndexOfOnes += blockGroupSize) {

            sum = processBlockGroup(signalSize, inputSignal, blockSize, halfBlockGroupSize, sum, startingIndexOfOnes);

        }

        addCalculatedElement(outputSignal, sum, e);

        start = printPerf(start, phase, e);
        return start;
    }

    private int processBlockGroup(int signalSize, List<Byte> inputSignal, int blockSize, int halfBlockGroupSize, int sum, int startingIndexOfOnes) {
        int startingIndexOfNegOnes;
        startingIndexOfNegOnes = startingIndexOfOnes + halfBlockGroupSize;

        sum = processOnesBlock(signalSize, inputSignal, blockSize, sum, startingIndexOfOnes);
        sum = processNegOnesBlock(signalSize, inputSignal, blockSize, sum, startingIndexOfNegOnes);

        return sum;
    }

    private static int processNegOnesBlock(int signalSize, List<Byte> inputSignal, int blockSize, int sum, int startingIndexOfNegOnes) {
        // Loop through block for negative ones
        for (int j = 0; j < blockSize && (startingIndexOfNegOnes + j) < signalSize; j++) {
            sum -= inputSignal.get(startingIndexOfNegOnes + j);
        }
        return sum;
    }

    private int processOnesBlock(int signalSize, List<Byte> inputSignal, int blockSize, int sum, int startingIndexOfOnes) {
        // Loop through block for ones
        for (int j = 0; j < blockSize && (startingIndexOfOnes + j) < signalSize; j++) {
            sum += inputSignal.get(startingIndexOfOnes + j);
        }
        return sum;
    }

    private void addCalculatedElement(List<Byte> outputSignal, int sum, int e) {
        outputSignal.add(getaByte(sum));
    }

    private byte getaByte(int sum) {
        return (byte) getAbs(sum);
    }

    private int getAbs(int sum) {
        return abs(calculateMod10(sum));
    }

    private int calculateMod10(int sum) {
        return sum % 10;
    }


    private static long printPerf(long start, int phase, int e) {
        if(e % 10000 == 0) {
            log.info("Calculation of one digit Runtime: {} | {} | {}", System.currentTimeMillis() - start, phase, e);
            start = System.currentTimeMillis();
        }
        return start;
    }

}
