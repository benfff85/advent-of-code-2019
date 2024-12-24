package com.adventofcode.year2024.day13;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class ClawMachine {

    private Integer axMagnitude;
    private Integer ayMagnitude;
    private Integer bxMagnitude;
    private Integer byMagnitude;
    private Point prizeLocation;
    private List<ButtonPressRecord> buttonPressOptions = new ArrayList<>();

    public ClawMachine(List<String> stringList) {
        axMagnitude = Integer.parseInt(stringList.getFirst().split(",")[0].split("\\+")[1]);
        ayMagnitude = Integer.parseInt(stringList.getFirst().split("\\+")[2]);
        bxMagnitude = Integer.parseInt(stringList.get(1).split(",")[0].split("\\+")[1]);
        byMagnitude = Integer.parseInt(stringList.get(1).split("\\+")[2]);
        prizeLocation = new Point(Integer.parseInt(stringList.get(2).split(",")[0].split("=")[1]), Integer.parseInt(stringList.get(2).split("=")[2]));
        calculateButtonPressOptions();
    }

    public void calculateButtonPressOptions() {

        for(int aCount = prizeLocation.x / axMagnitude; aCount >= 0; aCount--) {
            for(int bCount = 0; (aCount * axMagnitude) + (bCount * bxMagnitude) <= prizeLocation.x; bCount++) {
                if(((aCount * axMagnitude) + (bCount * bxMagnitude) == prizeLocation.x)
                    && ((aCount * ayMagnitude) + (bCount * byMagnitude) == prizeLocation.y)) {
                    buttonPressOptions.add(new ButtonPressRecord(aCount, bCount));
                }

            }

        }

        log.info("{}", buttonPressOptions);


    }

}
