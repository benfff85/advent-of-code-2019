package com.adventofcode.year2024.day3;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component("controller-2024-3")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2024/day-3.txt");
    }

    public DailyAnswer execute() {

        List<Pair<Integer, String>> matches = parse("mul\\((\\d+),(\\d+)\\)", puzzleInput);
        answer.setPart1(matches.stream()
                .map(s -> new MultiplyCommand(s.getRight()))
                .map(MultiplyCommand::getProduct)
                .mapToLong(Long::valueOf)
                .sum());
        log.info("Part 1: {}", answer.getPart1());

        List<Pair<Integer, String>> doIndices = parse("do\\(\\)", puzzleInput);
        List<Pair<Integer, String>> dontIndices = parse("don't\\(\\)", puzzleInput);
        answer.setPart2(matches.stream().
                filter(p -> !isCorrupt(p.getLeft(), doIndices, dontIndices))
                .map(s -> new MultiplyCommand(s.getRight()))
                .map(MultiplyCommand::getProduct)
                .mapToLong(Long::valueOf)
                .sum());
        log.info("Part 2: {}", answer.getPart2());

        return answer;
    }

    private boolean isCorrupt(Integer left, List<Pair<Integer, String>> doIndices, List<Pair<Integer, String>> dontIndices) {
        Integer closestDo = doIndices.stream()
                .map(Pair::getLeft)
                .filter(num -> num < left) // Keep only numbers less than the target
                .max(Integer::compareTo) // Find the largest number among them
                .orElse(Integer.MIN_VALUE); // Return null if no such number exists
        Integer closestDont = dontIndices.stream()
                .map(Pair::getLeft)
                .filter(num -> num < left) // Keep only numbers less than the target
                .max(Integer::compareTo) // Find the largest number among them
                .orElse(Integer.MIN_VALUE); // Return null if no such number exists

        return closestDont > closestDo;

    }

    private List<Pair<Integer, String>> parse(String regex, List<String> puzzleInput) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(puzzleInput.stream().reduce((String::concat)).orElseThrow());
        List<Pair<Integer, String>> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(Pair.of(matcher.start(), matcher.group()));
        }
        return matches;

    }

}
