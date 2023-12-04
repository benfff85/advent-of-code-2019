package com.adventofcode.year2023.day4;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Data
public class Card {

    private Set<Integer> winningNumbers = new HashSet<>();
    private Set<Integer> numbers = new HashSet<>();
    private Integer countOfIntersectingNumbers;
    private Integer score;

    public Card(String input) {
        Arrays.stream(input.split("\\|")[0].split(":")[1].split(" ")).filter(s -> !isEmpty(s)).mapToInt(Integer::parseInt).forEach(winningNumbers::add);
        Arrays.stream(input.split("\\|")[1].split(" ")).filter(s -> !isEmpty(s)).mapToInt(Integer::parseInt).forEach(numbers::add);
        countOfIntersectingNumbers = CollectionUtils.intersection(winningNumbers, numbers).size();
        score = countOfIntersectingNumbers == 0 ? 0 : (int) Math.pow(2, (countOfIntersectingNumbers - 1));
    }

}
