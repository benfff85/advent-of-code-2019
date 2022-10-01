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
        execute();
    }

    public DailyAnswer execute() {
        List<BigInteger> input = inputHelper.parseStringToBigIntList(puzzleInput.get(0));

        RepairDroid repairDroid = new RepairDroid(input);
        repairDroid.scan();

        return answer;
    }

}
