package com.adventofcode.day15;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;


@Slf4j
@Component("controller-day-15")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/day-15.txt");
    }

    public DailyAnswer execute() {
        List<BigInteger> input = inputHelper.parseStringToBigIntList(puzzleInput.get(0));

        RepairDroid repairDroid = new RepairDroid(input);
        repairDroid.scan();
        answer.setPart1(repairDroid.getPathLength());
        log.info("Distance of path to oxygen is: {}", answer.getPart1());

        OxygenSimulator oxygenSimulator = new OxygenSimulator();
        answer.setPart2(oxygenSimulator.run(repairDroid.getGrid()));
        log.info("Minutes till capsule is full of oxygen: {}", answer.getPart2());

        return answer;
    }

}
