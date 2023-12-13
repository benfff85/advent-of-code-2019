package com.adventofcode.year2023.day12;

import lombok.Data;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.math3.util.Combinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Data
public class SpringRow {

    private List<SpringState> springs = new ArrayList<>();
    private List<Integer> knownDamagedSpringGroupings = new ArrayList<>();

    private List<Integer> indexesOfUnknownSprings = new ArrayList<>();
    private int unknownCount;
    private int brokenCount;
    private int knownTotalBrokenCount;
    private int unknownThatMustBeBrokenCount;

    private List<List<Integer>> possibleCombinations;
    private List<List<Integer>> combinations = new ArrayList<>();


    public SpringRow(String input) {
        Arrays.stream(input.split(" ")[0].split("")).map(SpringState::fromString).forEach(springs::add);
        Arrays.stream(input.split(" ")[1].split(",")).map(Integer::parseInt).forEach(knownDamagedSpringGroupings::add);

        IntStream.range(0, springs.size()).filter(i -> springs.get(i).equals(SpringState.UNKNOWN)).forEach(indexesOfUnknownSprings::add);

        unknownCount = indexesOfUnknownSprings.size();
        brokenCount = (int) springs.stream().filter(springState -> springState.equals(SpringState.BROKEN)).count();

        knownTotalBrokenCount = (int) knownDamagedSpringGroupings.stream().mapToLong(Integer::longValue).sum();
        unknownThatMustBeBrokenCount = knownTotalBrokenCount - brokenCount;


        possibleCombinations = IteratorUtils.toList(new Combinations(unknownCount, unknownThatMustBeBrokenCount).iterator())
                .stream()
                .map(i -> Arrays.stream(i).boxed().toList())
                .toList();

        for (List<Integer> combination : possibleCombinations) {
            // Apply combination
            for (int i = 0; i < combination.size(); i++) {
                springs.set(indexesOfUnknownSprings.get(combination.get(i)), SpringState.BROKEN);
            }
            // Check if it matches the knownDamagedSpringGroupings
            if (calculateDamagedSpringGroupings().equals(knownDamagedSpringGroupings)) {
                // If it does, add to combination
                combinations.add(combination);
            }

            // Revert
            for (int i = 0; i < combination.size(); i++) {
                springs.set(indexesOfUnknownSprings.get(combination.get(i)), SpringState.UNKNOWN);
            }
        }

    }

    private List<Integer> calculateDamagedSpringGroupings() {

        List<Integer> damagedSpringGroupings = new ArrayList<>();
        int count = 0;
        for (SpringState springState : springs) {
            if (springState.equals(SpringState.BROKEN)) {
                count++;
            } else {
                if (count > 0) {
                    damagedSpringGroupings.add(count);
                    count = 0;
                }
            }
        }
        if (count > 0) {
            damagedSpringGroupings.add(count);
        }
        return damagedSpringGroupings;

    }

}
