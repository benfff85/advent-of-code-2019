package com.adventofcode.day7;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import com.adventofcode.day5.IntComputer;
import com.adventofcode.day5.IntComputerContext;
import com.google.common.collect.Collections2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component("controller-day-7")
public class Controller extends SolutionController {

    private final IntComputer intComputer;

    public Controller(InputHelper inputHelper, IntComputer intComputer) {
        super(inputHelper, "puzzle-input/day-7.txt");
        this.intComputer = intComputer;
    }

    public DailyAnswer execute() {
        List<Integer> input = inputHelper.parseStringToIntList(puzzleInput.get(0));

        Integer maxSignal = 0;
        Collection<List<Integer>> phaseSettingPermutations = Collections2.permutations(List.of(0, 1, 2, 3, 4));
        for(List<Integer> phaseSetting : phaseSettingPermutations) {
            Integer aOut = intComputer.process(generateIntComputerContext(phaseSetting.get(0), 0, input)).getOutputs().pop();
            Integer bOut = intComputer.process(generateIntComputerContext(phaseSetting.get(1), aOut, input)).getOutputs().pop();
            Integer cOut = intComputer.process(generateIntComputerContext(phaseSetting.get(2), bOut, input)).getOutputs().pop();
            Integer dOut = intComputer.process(generateIntComputerContext(phaseSetting.get(3), cOut, input)).getOutputs().pop();
            Integer eOut = intComputer.process(generateIntComputerContext(phaseSetting.get(4), dOut, input)).getOutputs().pop();

            if (eOut > maxSignal) {
                maxSignal = eOut;
            }
        }
        answer.setPart1(maxSignal);
        log.info("Max signal {}", answer.getPart1());

        return answer;
    }

    private IntComputerContext generateIntComputerContext(Integer param1, Integer param2, List<Integer> instructions) {
        return IntComputerContext.builder()
                .instructions(new ArrayList<>(instructions))
                .instructionIndex(0)
                .inputs(new LinkedList<>(List.of(param1, param2)))
                .outputs(new LinkedList<>())
                .build();
    }

}
