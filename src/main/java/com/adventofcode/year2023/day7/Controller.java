package com.adventofcode.year2023.day7;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;


@Slf4j
@Component("controller-2023-7")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2023/day-7.txt");
    }

    public DailyAnswer execute() {

        answer.setPart1(process(false));
        log.info("Part 1: {}", answer.getPart1());

        answer.setPart2(process(true));
        log.info("Part 2: {}", answer.getPart2());

        return answer;

    }

    private int process(boolean isJokerWild) {
        final List<Hand> hands = new java.util.LinkedList<>(puzzleInput.stream().map(s -> new Hand(s, isJokerWild)).sorted().toList());
        return IntStream.range(0, hands.size()).map(i -> hands.get(i).getBid() * (i + 1)).sum();
    }

}