package com.adventofcode.day11;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;


@Slf4j
@Component("controller-day-11")
public class Controller extends SolutionController {

    private final Robot robot;

    public Controller(InputHelper inputHelper, Robot robot) {
        super(inputHelper, "puzzle-input/day-11.txt");
        this.robot = robot;
    }

    public DailyAnswer execute() {
        List<BigInteger> input = inputHelper.parseStringToBigIntList(puzzleInput.get(0));
        robot.loadInstructions(input);
        answer.setPart1(robot.process());


        return answer;

    }



}
