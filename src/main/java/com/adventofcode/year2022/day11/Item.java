package com.adventofcode.year2022.day11;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class Item {

    private BigInteger worryLevel;

    private Integer monkeyTarget;

}
