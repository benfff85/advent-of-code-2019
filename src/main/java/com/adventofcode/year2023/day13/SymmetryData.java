package com.adventofcode.year2023.day13;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SymmetryData {

    private Integer symmetryValue;
    private Integer symmetryIndex;
    private SymmetryType symmetryType;

}
