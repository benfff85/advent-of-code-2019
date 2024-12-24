package com.adventofcode.year2024.day13;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
@Component("controller-2024-13")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2024/day-13.txt");
    }

    public DailyAnswer execute() {

        int groupSize = 4;
        List<ClawMachine> arcade = IntStream.range(0, (puzzleInput.size() + groupSize - 1) / groupSize)
                .mapToObj(i -> puzzleInput.subList(i * groupSize, Math.min((i + 1) * groupSize, puzzleInput.size())))
                .map(ClawMachine::new)
                .toList();

        answer.setPart1(arcade.stream()
                .filter(clawMachine -> !clawMachine.getButtonPressOptions().isEmpty())
                .mapToLong(clawMachine -> clawMachine.getButtonPressOptions().stream().mapToLong(ButtonPressRecord::getTokenCost).min().getAsLong())
                .sum());
        log.info("Part 1: {}", answer.getPart1());

        answer.setPart2(arcade.stream()
                .filter(clawMachine -> !clawMachine.getButtonPressOptionsPart2().isEmpty())
                .mapToLong(clawMachine -> clawMachine.getButtonPressOptionsPart2().stream().mapToLong(ButtonPressRecord::getTokenCost).min().getAsLong())
                .sum());
        log.info("Part 2: {}", answer.getPart2());

        return answer;
    }

}
