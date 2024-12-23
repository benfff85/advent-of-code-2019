package com.adventofcode.year2024.day11;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component("controller-2024-11")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2024/day-11.txt");
    }

    public DailyAnswer execute() {

        List<Long> numberList = new ArrayList<>(Arrays.stream(puzzleInput.getFirst().split(" ")).map(Long::parseLong).toList());

        for(int step = 0; step < 25; step++) {
            int indexEnd = numberList.size();
            for(int i = 0; i < indexEnd ; i++) {
                long num = numberList.get(i);
                if(num == 0) {
                    numberList.set(i, 1L);
                } else if(String.valueOf(num).length() % 2 == 0) {
                    int length = String.valueOf(num).length();
                    numberList.set(i, Long.parseLong(String.valueOf(num).substring(0, length / 2)));
                    numberList.add(i + 1, Long.parseLong(String.valueOf(num).substring(length / 2)));
                    indexEnd++;
                    i++;
                } else {
                    numberList.set(i, num * 2024);
                }

            }
        }

        answer.setPart1(numberList.size());
        log.info("Part 1: {}", answer.getPart1());

        answer.setPart2(0);
        log.info("Part 2: {}", answer.getPart2());

        return answer;
    }

}
