package com.adventofcode.year2019.day5;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component("controller-day-5")
public class Controller extends SolutionController {

    private final IntComputer intComputer;

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2019/day-5.txt");
        this.intComputer = new IntComputer();
    }

    public DailyAnswer execute() {
        List<BigInteger> input = inputHelper.parseStringToBigIntList(puzzleInput.get(0));

        List<BigInteger> instructionList = new ArrayList<>(input);
        LinkedList<BigInteger> intComputerInputs = new LinkedList<>();
        intComputerInputs.add(BigInteger.valueOf(1));
        answer.setPart1(intComputer.process(instructionList, intComputerInputs));
        log.info("Part 1: {}", answer.getPart1());

        instructionList = new ArrayList<>(input);
        intComputerInputs.clear();
        intComputerInputs.add(BigInteger.valueOf(5));
        answer.setPart2(intComputer.process(instructionList, intComputerInputs));
        log.info("Part 2: {}", answer.getPart2());

        return answer;
    }
}
