package com.adventofcode.year2022.day18;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component("controller-2022-18")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2022/day-18.txt");
    }


    public DailyAnswer execute() {

        LavaDroplet droplet = new LavaDroplet();
        puzzleInput.stream().map(Cube::new).forEach(droplet::addCube);

        answer.setPart1(droplet.calculateSurfaceArea());
        log.info("P1: {}", answer.getPart1());

        answer.setPart2(droplet.calculateContiguousSurfaceArea());
        log.info("P2: {}", answer.getPart2());

        return answer;
    }

}