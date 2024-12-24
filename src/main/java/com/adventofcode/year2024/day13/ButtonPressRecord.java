package com.adventofcode.year2024.day13;

import lombok.Data;

@Data
public class ButtonPressRecord {

    private Long aPresses;
    private Long bPresses;
    private Long tokenCost;

    public ButtonPressRecord(Long aPresses, Long bPresses) {
        this.aPresses = aPresses;
        this.bPresses = bPresses;
        this.tokenCost = (aPresses * 3) + bPresses;
    }

}
