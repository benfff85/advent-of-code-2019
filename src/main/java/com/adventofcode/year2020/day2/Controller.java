package com.adventofcode.year2020.day2;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;


@Slf4j
@Component("controller-2020-2")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2020/day-2.txt");
    }


    public DailyAnswer execute() {

        process(puzzleInput.stream().map(PasswordPolicyB::new));

        answer.setPart1(process(puzzleInput.stream().map(PasswordPolicyA::new)));
        log.info("Part 1: {}", answer.getPart1());

        answer.setPart2(process(puzzleInput.stream().map(PasswordPolicyB::new)));
        log.info("Part 2: {}", answer.getPart2());

        return answer;
    }

    private long process(Stream<PasswordPolicy> policies) {
        return policies.filter(PasswordPolicy::isValid).count();
    }

}
