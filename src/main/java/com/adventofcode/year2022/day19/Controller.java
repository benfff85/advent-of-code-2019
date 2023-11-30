package com.adventofcode.year2022.day19;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.LRUMap;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Objects.nonNull;


@Slf4j
@EnableAsync
@Component("controller-2022-19")
public class Controller extends SolutionController {

    private final List<Blueprint> blueprints = puzzleInput.stream().map(Blueprint::new).toList();
    private Blueprint activeBlueprint;
    private int maxGeode;
    private Map<CacheKey, Integer> cache;
    private final List<Integer> maxPossibleGeodesFromYetToBeCreatedRobots = new ArrayList<>();

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2022/day-19.txt");

        for (int i = 0; i < 24; i++) {
            maxPossibleGeodesFromYetToBeCreatedRobots.add(IntStream.range(0, i).sum());
        }
    }


    public DailyAnswer execute() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Map<RobotType, Integer> activeRobots;
        Map<RobotType, Integer> resourceInventory;
        int totalQualityLevel = 0;

        log.info("Beginning processing");
        for (int i = 0; i < blueprints.size(); i++) {
            activeBlueprint = blueprints.get(i);
            maxGeode = 0;
            activeRobots = new EnumMap<>(Map.of(RobotType.ORE, 1, RobotType.CLAY, 0, RobotType.OBSIDIAN, 0, RobotType.GEODE, 0));
            resourceInventory = new EnumMap<>(Map.of(RobotType.ORE, 0, RobotType.CLAY, 0, RobotType.OBSIDIAN, 0, RobotType.GEODE, 0));
            cache = new LRUMap<>(2500000);
            processStep(1, activeRobots, resourceInventory, null);
            totalQualityLevel += (maxGeode * (i + 1));
            log.info("Blueprint {}, Max {}", i + 1, maxGeode);
        }

        answer.setPart1(totalQualityLevel);
        log.info("P1: {}", answer.getPart1());
        stopWatch.stop();
        log.info("Time taken: {}s", stopWatch.getTotalTimeSeconds());

        return answer;
    }


    private void processStep(int step, Map<RobotType, Integer> activeRobots, Map<RobotType, Integer> resourceInventory, RobotType robotTypeToBuild) {

        CacheKey cacheKey = null;
        if (step <= 19) {
            cacheKey = new CacheKey(step, activeRobots, resourceInventory, robotTypeToBuild);
            if (cache.containsKey(cacheKey)) {
                return;
            }
        }


        // Try to build robot, if unable to afford just return
        if (nonNull(robotTypeToBuild) && (!purchaseRobot(resourceInventory, robotTypeToBuild))) {
            return;
        }

        // Allow robots to generate resources
        processResourceCreation(activeRobots, resourceInventory);

        // If we hit last step check if new max and return
        if (step == 24) {
            maxGeode = Math.max(maxGeode, resourceInventory.get(RobotType.GEODE));
            return;
        }

        // Add newly build robot to inventory
        if (nonNull(robotTypeToBuild)) {
            activeRobots.put(robotTypeToBuild, activeRobots.get(robotTypeToBuild) + 1);
        }

        if (earlyTermination(step, activeRobots.get(RobotType.GEODE), resourceInventory.get(RobotType.GEODE), maxGeode)) {
            return;
        }

        // Otherwise check possible next steps
        processStep(step + 1, new EnumMap<>(activeRobots), new EnumMap<>(resourceInventory), RobotType.GEODE);
        processStep(step + 1, new EnumMap<>(activeRobots), new EnumMap<>(resourceInventory), RobotType.OBSIDIAN);
        processStep(step + 1, new EnumMap<>(activeRobots), new EnumMap<>(resourceInventory), RobotType.CLAY);
        processStep(step + 1, new EnumMap<>(activeRobots), new EnumMap<>(resourceInventory), RobotType.ORE);
        processStep(step + 1, new EnumMap<>(activeRobots), new EnumMap<>(resourceInventory), null);

        if (nonNull(cacheKey)) {
            cache.put(cacheKey, 0);
        }

    }

    private boolean earlyTermination(int step, Integer geodeRobotCount, Integer currentGeodes, int maxGeode) {
        if (maxGeode == 0) {
            return false;
        }

        int remainingSteps = (24 - step);
        int maxPossibleGeodes = maxPossibleGeodesFromYetToBeCreatedRobots.get(remainingSteps) + // Geodes produced by yet-to-be-created robots
                currentGeodes + // Current geodes
                (geodeRobotCount * remainingSteps); // Geodes produced by existing robots
        return maxPossibleGeodes <= maxGeode;
    }

    private boolean purchaseRobot(Map<RobotType, Integer> resourceInventory, RobotType robotTypeToBuild) {
        Map<RobotType, Integer> robotTypeManifest = activeBlueprint.getRobotManifests().get(robotTypeToBuild);
        for (Map.Entry<RobotType, Integer> requirement : robotTypeManifest.entrySet()) {
            if (requirement.getValue() > resourceInventory.get(requirement.getKey())) {
                return false;
            }
            resourceInventory.put(requirement.getKey(), resourceInventory.get(requirement.getKey()) - requirement.getValue());
        }
        return true;
    }

    private void processResourceCreation(Map<RobotType, Integer> activeRobots, Map<RobotType, Integer> resourceInventory) {
        resourceInventory.replaceAll((t, v) -> resourceInventory.get(t) + activeRobots.get(t));
    }

}