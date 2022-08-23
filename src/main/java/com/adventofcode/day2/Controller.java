package com.adventofcode.day2;

import com.adventofcode.common.InputReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component("controller-day-2")
public class Controller {

    public Controller(InputReader inputReader) {
        List<Integer> input = Arrays.stream(inputReader.read("puzzle-input/day-2.txt").get(0).split(",")).map(Integer::parseInt).toList();

        List<Integer> list = new ArrayList<>(input);
        process(12, 2, list);
        log.info("First element value: {}", list.get(0));

        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                list = new ArrayList<>(input);
                process(i, j, list);
                if (list.get(0) == 19690720) {
                    log.info("Input pair {}:{}::{}", i, j, (100 * i) + j);
                    break;
                }
            }
        }

    }

    private void process(int elementA, int elementB, List<Integer> list) {
        list.set(1, elementA);
        list.set(2, elementB);

        for (int i = 0; i < list.size(); i += 4) {

            if (list.get(i) == 1) {
                processAdd(list, i);
            } else if (list.get(i) == 2) {
                processMultiply(list, i);
            } else if (list.get(i) == 99) {
                return;
            } else {
                log.error("Unexpected operator {} at index {}", list.get(i), i);
            }
        }
    }

    private void processAdd(List<Integer> input, int i) {
        input.set(input.get(i + 3), input.get(input.get(i + 1)) + input.get(input.get(i + 2)));
    }

    private void processMultiply(List<Integer> input, int i) {
        input.set(input.get(i + 3), input.get(input.get(i + 1)) * input.get(input.get(i + 2)));
    }

}
