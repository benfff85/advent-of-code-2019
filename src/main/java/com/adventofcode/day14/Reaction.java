package com.adventofcode.day14;

import lombok.Data;

import java.util.Map;

@Data
public class Reaction {

    private final Map<Chemical, Integer> inputChemicals;
    private final Chemical outputChemical;
    private final Integer outputChemicalQuantity;

}
