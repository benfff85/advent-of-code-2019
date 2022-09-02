package com.adventofcode.day5;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

@Slf4j
@Component("controller-day-5")
public class Controller extends SolutionController {

    private final IntComputer intComputer;

    public Controller(InputHelper inputHelper, IntComputer intComputer) {
        super(inputHelper, "puzzle-input/day-5.txt");
        this.intComputer = intComputer;
    }

    public DailyAnswer execute() {
        List<Integer> input = inputHelper.parseStringToIntList(puzzleInput.get(0));

        List<Integer> instructionList = new ArrayList<>(input);
        LinkedList<Integer> intComputerInputs = new LinkedList<>();
        intComputerInputs.add(1);
        answer.setPart1(intComputer.process(instructionList, intComputerInputs));
        log.info("Part 1: {}", answer.getPart1());

        instructionList = new ArrayList<>(input);
        intComputerInputs.clear();
        intComputerInputs.add(5);
        answer.setPart2( intComputer.process(instructionList, intComputerInputs));
        log.info("Part 2: {}", answer.getPart2());

        return answer;
    }
}
