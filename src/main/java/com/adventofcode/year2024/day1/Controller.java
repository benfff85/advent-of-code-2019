package com.adventofcode.year2024.day1;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component("controller-2024-1")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2024/day-1.txt");
    }

    public DailyAnswer execute() {

        List<Integer> locationList1 = puzzleInput.stream().map(StringUtils::normalizeSpace).map(line -> line.split(" ")[0]).map(Integer::parseInt).sorted().toList();
        List<Integer> locationList2 = puzzleInput.stream().map(StringUtils::normalizeSpace).map(line -> line.split(" ")[1]).map(Integer::parseInt).sorted().toList();

        int sum = 0;
        for(int i=0; i<locationList1.size(); i++) {
            sum += Math.abs(locationList1.get(i) - locationList2.get(i));
        }
        answer.setPart1(sum);
        log.info("Part 1: {}", answer.getPart1());

        int simScore = 0;
        for(Integer i : locationList1) {
            simScore += (int) (i * locationList2.stream().filter(i::equals).count());
        }
        answer.setPart2(simScore);
        log.info("Part 2: {}", answer.getPart2());

        return answer;
    }

}
