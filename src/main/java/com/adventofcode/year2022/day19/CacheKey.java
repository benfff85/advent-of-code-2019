package com.adventofcode.year2022.day19;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
public class CacheKey {

    private List<Integer> value = new LinkedList<>();
    private RobotType robotType;

    public CacheKey(Integer step, Map<RobotType, Integer> activeRobots, Map<RobotType, Integer> resourceInventory, RobotType robotTypeToBuild) {
        value.add(step);
        value.add(activeRobots.get(RobotType.ORE));
        value.add(activeRobots.get(RobotType.CLAY));
        value.add(activeRobots.get(RobotType.OBSIDIAN));
        value.add(activeRobots.get(RobotType.GEODE));
        value.add(resourceInventory.get(RobotType.ORE));
        value.add(resourceInventory.get(RobotType.CLAY));
        value.add(resourceInventory.get(RobotType.OBSIDIAN));
        value.add(resourceInventory.get(RobotType.GEODE));
        this.robotType = robotTypeToBuild;
    }

}
