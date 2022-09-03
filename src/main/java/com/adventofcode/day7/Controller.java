package com.adventofcode.day7;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import com.adventofcode.day5.IntComputerContext;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.lang.Boolean.FALSE;

@Slf4j
@Component("controller-day-7")
public class Controller extends SolutionController {


    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/day-7.txt");
    }

    public DailyAnswer execute() {
        List<Integer> input = inputHelper.parseStringToIntList(puzzleInput.get(0));

        Integer maxSignal = 0;
        List<Amplifier> amplifiers;
        Collection<List<Integer>> phaseSettingPermutations = Collections2.permutations(List.of(0, 1, 2, 3, 4));
        Integer output;
        for(List<Integer> phaseSetting : phaseSettingPermutations) {
            amplifiers = initAmplifiers(input);
            output = amplifiers.get(0).run(new LinkedList<>(List.of(phaseSetting.get(0), 0))).getOutputs().pop();
            output = amplifiers.get(1).run(new LinkedList<>(List.of(phaseSetting.get(1), output))).getOutputs().pop();
            output = amplifiers.get(2).run(new LinkedList<>(List.of(phaseSetting.get(2), output))).getOutputs().pop();
            output = amplifiers.get(3).run(new LinkedList<>(List.of(phaseSetting.get(3), output))).getOutputs().pop();
            output = amplifiers.get(4).run(new LinkedList<>(List.of(phaseSetting.get(4), output))).getOutputs().pop();

            if (output > maxSignal) {
                maxSignal = output;
            }
        }
        answer.setPart1(maxSignal);
        log.info("Max signal {}", answer.getPart1());


        Integer signal;
        maxSignal = 0;
        phaseSettingPermutations = Collections2.permutations(List.of(5, 6, 7, 8, 9));
        IntComputerContext outputContext;
        for(List<Integer> phaseSetting : phaseSettingPermutations) {
            amplifiers = initAmplifiers(input);

            // Initial loop iteration with phase inputs
            outputContext = amplifiers.get(0).run(new LinkedList<>(List.of(phaseSetting.get(0), 0)));
            outputContext = amplifiers.get(1).run(generateQueueOfPhaseAndPriorOutputs(phaseSetting.get(1), outputContext.getOutputs()));
            outputContext = amplifiers.get(2).run(generateQueueOfPhaseAndPriorOutputs(phaseSetting.get(2), outputContext.getOutputs()));
            outputContext = amplifiers.get(3).run(generateQueueOfPhaseAndPriorOutputs(phaseSetting.get(3), outputContext.getOutputs()));
            outputContext = amplifiers.get(4).run(generateQueueOfPhaseAndPriorOutputs(phaseSetting.get(4), outputContext.getOutputs()));

            // It doesn't seem to be the case, but we must be careful amplifier E has not already stopped
            for (Amplifier amplifier : Iterables.cycle(amplifiers)) {
                outputContext = amplifier.run(generateQueueOfPriorOutputs(outputContext.getOutputs()));
                if ("e".equals(amplifier.getName()) && FALSE.equals(outputContext.getIsRunning())) {
                    signal = outputContext.getOutputs().removeLast();
                    if (signal > maxSignal) {
                        maxSignal = signal;
                    }
                    break;
                }
            }
        }
        answer.setPart2(maxSignal);
        log.info("Max signal {}", answer.getPart2());

        return answer;
    }

    private void addOutputsToQueue(Queue<Integer> inputs, Deque<Integer> outputs) {
        while(!outputs.isEmpty()) {
            inputs.add(outputs.remove());
        }
    }

    private Queue<Integer> generateQueueOfPriorOutputs(Deque<Integer> outputs) {
        Queue<Integer> queue = new LinkedList<>();
        addOutputsToQueue(queue, outputs);
        return queue;
    }

    private Queue<Integer> generateQueueOfPhaseAndPriorOutputs(Integer phase, Deque<Integer> outputs) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(phase);
        addOutputsToQueue(queue, outputs);
        return queue;
    }

    private List<Amplifier> initAmplifiers(List<Integer> instructions) {
        List<Amplifier> amplifiers = new LinkedList<>();
        amplifiers.add(new Amplifier("a", instructions));
        amplifiers.add(new Amplifier("b", instructions));
        amplifiers.add(new Amplifier("c", instructions));
        amplifiers.add(new Amplifier("d", instructions));
        amplifiers.add(new Amplifier("e", instructions));
        return amplifiers;
    }



}
