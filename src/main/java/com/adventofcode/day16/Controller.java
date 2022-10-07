package com.adventofcode.day16;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import com.google.common.collect.Streams;
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
        List<Integer> inputSignal = inputHelper.parseStringToSingleDigitIntList(puzzleInput.get(0));
        List<Integer> output = processPhases(inputSignal, 100);
        answer.setPart1(output.subList(0, 8));

        List<Integer> inputSignalPart2 = new ArrayList<>(inputSignal.size() * 10000);
        for (int i = 0; i < 10000; i++) {
            inputSignalPart2.addAll(inputSignal);
        }
        List<Integer> outputPart2 = processPhases(inputSignalPart2, 100);

        return answer;
    }

    private List<Integer> processPhases(List<Integer> input, Integer phaseCount) {
        List<Integer> pattern;
        List<Integer> inputSignal = new ArrayList<>(input.size());
        List<Integer> outputSignal = new ArrayList<>(input.size());
        inputSignal.addAll(input);

        for (int phase = 0; phase < phaseCount; phase++) {
            outputSignal.clear();
            for (int i = 0; i < inputSignal.size(); i++) {
                pattern = getPattern(i + 1, inputSignal.size());
                outputSignal.add(i, abs(Streams.zip(inputSignal.stream(), pattern.stream(), (a, b) -> a * b).mapToInt(Integer::intValue).sum() % 10));
            }
            inputSignal.clear();
            inputSignal.addAll(outputSignal);
        }

        return outputSignal;
    }

    // Element number is index + 1
    private List<Integer> getPattern(Integer elementNumber, Integer totalElementCount) {
        List<Integer> pattern = new ArrayList<>();
        for (Integer integer : List.of(0, 1, 0, -1)) {
            for (int i = 0; i < elementNumber; i++) {
                pattern.add(integer);
            }
        }

        List<Integer> pattern2 = new ArrayList<>();
        while (pattern2.size() <= totalElementCount) {
            pattern2.addAll(pattern);
        }

        return pattern2.subList(1, totalElementCount + 1);
    }

}
