package com.adventofcode.year2024.day13;

import lombok.Data;

@Data
public class ButtonPressRecord {

    private Integer aPresses;
    private Integer bPresses;
    private Integer tokenCost;

    public ButtonPressRecord(Integer aPresses, Integer bPresses) {
        this.aPresses = aPresses;
        this.bPresses = bPresses;
        this.tokenCost = (aPresses * 3) + bPresses;
    }

}
