package com.adventofcode.year2020.day3;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component("controller-2020-3")
public class Controller extends SolutionController {

    private List<Row> treeMap;

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2020/day-3.txt");
    }

    public DailyAnswer execute() {
        treeMap = puzzleInput.stream().map(Row::new).collect(toList());

        answer.setPart1(process(1, 3));
        log.info("Part 1: {}", answer.getPart1());

        answer.setPart2(process(1, 1) * process(1, 3) * process(1, 5) * process(1, 7) * process(2, 1));
        log.info("Part 2: {}", answer.getPart2());

        return answer;
    }

    private Long process(int verticalSpeed, int horizontalSpeed) {
        long treeCount = 0;
        int horizontalIndex = 0;

        for (int verticalIndex = 0; verticalIndex < treeMap.size(); verticalIndex += verticalSpeed) {
            if (treeMap.get(verticalIndex).isTree(horizontalIndex)) {
                treeCount++;
            }
            horizontalIndex += horizontalSpeed;
        }
        log.info("Tree Count: {}", treeCount);
        return treeCount;
    }

}
