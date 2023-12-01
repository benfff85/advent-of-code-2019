package com.adventofcode.year2022.day20;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component("controller-2022-20")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2022/day-20.txt");
    }

    public DailyAnswer execute() {

        List<Node> inputNodes = puzzleInput.stream().map(Node::new).toList();
        LoopingList loopingList = new LoopingList(inputNodes);

        for (Node node : inputNodes) {
            loopingList.slide(node, node.getValue());
        }

        answer.setPart1(loopingList.getNodeGivenOffset(0, 1000).getValue() +
                loopingList.getNodeGivenOffset(0, 2000).getValue() +
                loopingList.getNodeGivenOffset(0, 3000).getValue());

        log.info("Part 1: {}", answer.getPart1());

        return answer;
    }

}