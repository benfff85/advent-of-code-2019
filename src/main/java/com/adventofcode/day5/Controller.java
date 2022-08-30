package com.adventofcode.day5;

import com.adventofcode.common.InputReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

@Slf4j
@Component("controller-day-5")
public class Controller {

    public Controller(InputReader inputReader, IntComputer intComputer) {
        List<Integer> input = Arrays.stream(inputReader.read("puzzle-input/day-5.txt").get(0).split(",")).map(Integer::parseInt).toList();

        List<Integer> instructionList = new ArrayList<>(input);
        PriorityQueue<Integer> intComputerInputs = new PriorityQueue<>();
        intComputerInputs.add(1);
        log.info("Part 1: {}", intComputer.process(instructionList, intComputerInputs));

        instructionList = new ArrayList<>(input);
        intComputerInputs.clear();
        intComputerInputs.add(5);
        log.info("Part 2: {}", intComputer.process(instructionList, intComputerInputs));

    }
}
