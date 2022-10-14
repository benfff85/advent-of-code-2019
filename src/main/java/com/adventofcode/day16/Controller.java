package com.adventofcode.day16;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import com.google.common.collect.Streams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

        List<Byte> inputSignal = new ArrayList<>(input.size());
        List<Byte> outputSignal = new ArrayList<>(input.size());
        inputSignal.addAll(input);

        int sum = 0;

        // Loop through phases
        for (int phase = 0; phase < phaseCount; phase++) {
            outputSignal.clear();

            // Loop through digits in input signal
            for (int e = 0; e < inputSignal.size(); e++) {
                start = System.currentTimeMillis();
                sum = 0;

                // Loop through blocks starting points
                for (int i = e; i < inputSignal.size(); i += (4*(e+1))) {

                    int startingIndexOfOnes = i;
                    int startingIndexOfNegOnes = i + (2*(e+1));

                    // Loop through block for ones
                    for (int j = 0; j < (e + 1) && (startingIndexOfOnes+j) < inputSignal.size(); j++) {
                        sum += inputSignal.get(startingIndexOfOnes+j);
                    }

                    // Loop through block for negative ones
                    for (int j = 0; j < (e + 1) && (startingIndexOfNegOnes+j) < inputSignal.size(); j++) {
                        sum -= inputSignal.get(startingIndexOfNegOnes+j);
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

    private List<Byte> processPhases2(List<Byte> input, Integer phaseCount) {
        List<Byte> inputSignal = new ArrayList<>(input.size());
        List<Byte> outputSignal = new ArrayList<>(input.size());
        inputSignal.addAll(input);
        long start;

        for (int phase = 0; phase < phaseCount; phase++) {
            outputSignal.clear();
            for (int i = 0; i < inputSignal.size(); i++) {

                start = System.currentTimeMillis();
                outputSignal.add(i, (byte) abs(Streams.zip(inputSignal.stream(), getPattern(i + 1, inputSignal.size()), (a, b) ->  a * b).reduce(0, Integer::sum) % 10));
                log.info("Calculation of one digit Runtime: {}", System.currentTimeMillis() - start);

            }
            inputSignal.clear();
            inputSignal.addAll(outputSignal);
        }

        return outputSignal;
    }


    // Element number is index + 1
    private Stream<Byte> getPattern(Integer elementNumber, Integer totalElementCount) {
        long start = System.currentTimeMillis();

        Byte[] pattern = new Byte[4 * elementNumber];
        byte index = (byte) 0;
        for (Byte b : List.of((byte) 0, (byte) 1, (byte) 0, (byte) -1)) {
            for (int i = 0; i < elementNumber; i++) {
                pattern[(index * elementNumber) + i] = b;
            }
            index++;
        }

        Stream<Byte> s = Arrays.stream(repeat(pattern, totalElementCount + 1)).skip(1);
        log.info("Get Pattern Runtime: {}", System.currentTimeMillis() - start);

        return s;
    }

    public static <T> T[] repeat(T[] arr, int newLength) {
        T[] dup = Arrays.copyOf(arr, newLength);
        for (int last = arr.length; last != 0 && last < newLength; last <<= 1) {
            System.arraycopy(dup, 0, dup, last, Math.min(last << 1, newLength) - last);
        }
        return dup;
    }

}
