package com.adventofcode.day16;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        long start;

        int signalSize = input.size();
        List<Byte> inputSignal = new ArrayList<>(signalSize);
        List<Byte> outputSignal = new ArrayList<>(signalSize);
        inputSignal.addAll(input);

        int sum;
        int blockSize;
int blockGroupSize;
        int halfBlockGroupSize;


        // Loop through phases
        for (int phase = 0; phase < phaseCount; phase++) {
            outputSignal.clear();

            // Loop through digits in input signal
            for (int e = 0; e < signalSize; e++) {
                start = System.currentTimeMillis();
                blockSize = e + 1;
                blockGroupSize = (4 * blockSize);
                halfBlockGroupSize = blockGroupSize / 2;
                sum = 0;

                // Loop through blocks starting points
                for (int startingIndexOfOnes = e; startingIndexOfOnes < signalSize; startingIndexOfOnes += blockGroupSize) {

                    int startingIndexOfNegOnes = startingIndexOfOnes + halfBlockGroupSize;

                    // Loop through block for ones
                    for (int j = 0; j < blockSize && (startingIndexOfOnes + j) < signalSize; j++) {
                        sum += inputSignal.get(startingIndexOfOnes + j);
                    }

                    // Loop through block for negative ones
                    for (int j = 0; j < blockSize && (startingIndexOfNegOnes + j) < signalSize; j++) {
                        sum -= inputSignal.get(startingIndexOfNegOnes + j);
                    }

                }

                outputSignal.add(e, (byte) abs((sum) % 10));
                log.info("Calculation of one digit Runtime: {} | {} | {}", System.currentTimeMillis() - start, phase, e);

            }

            inputSignal.clear();
            inputSignal.addAll(outputSignal);
        }

        return outputSignal;
    }

}
