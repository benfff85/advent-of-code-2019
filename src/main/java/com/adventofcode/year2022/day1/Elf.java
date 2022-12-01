package com.adventofcode.year2022.day1;

import java.util.ArrayList;
import java.util.List;

public class Elf {

    private final List<Snack> snacks = new ArrayList<>();

    public void addSnack(Integer calories) {
        snacks.add(Snack.builder().calories(calories).build());
    }

    public Integer getTotalCalories() {
        return snacks.stream().mapToInt(Snack::getCalories).sum();
    }

}
