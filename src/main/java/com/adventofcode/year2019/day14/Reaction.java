package com.adventofcode.year2019.day14;


import java.util.Map;

public record Reaction(Map<String, Integer> inputChemicals, String outputChemical, Long outputChemicalQuantity) {

}
