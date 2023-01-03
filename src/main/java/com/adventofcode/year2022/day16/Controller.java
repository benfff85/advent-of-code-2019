package com.adventofcode.year2022.day16;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;


@Slf4j
@Component("controller-2022-16")
public class Controller extends SolutionController {

    private final Map<CacheKey, Integer> cache = new HashMap<>();
    private Map<String, Valve> valves;

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2022/day-16.txt");
    }

    public DailyAnswer execute() {

        valves = puzzleInput.stream().map(Valve::new).collect(Collectors.toMap(Valve::getName, valve -> valve));
        valves.values().forEach(v -> v.linkValves(valves));

        answer.setPart1(getMax(30, valves.get("AA")));

        cache.clear();

        answer.setPart2(getMax(26, valves.get("AA"), valves.get("AA")));

        return answer;
    }


    private Integer getMax(int minutesRemaining, Valve currentValve) {

        // Terminal condition
        if (minutesRemaining == 0) {
            return 0;
        }

        // Check if in cache and if so return value
        CacheKey cacheKey = CacheKey.builder()
                .minutesRemaining(minutesRemaining)
                .currentValveA(currentValve)
                .openValves(valves.values().stream().filter(Valve::isOpen).collect(Collectors.toSet()))
                .build();
        Integer cacheResult = cache.get(cacheKey);
        if (nonNull(cacheResult)) {
            return cacheResult;
        }

        // check other options, add the max to the cache and return it
        int maxPressureReleased = 0;

        if (!currentValve.isOpen() && currentValve.getFlowRate() > 0) {
            currentValve.open();
            maxPressureReleased = (currentValve.getFlowRate() * (minutesRemaining - 1)) + getMax(minutesRemaining - 1, currentValve);
            currentValve.close();
        }

        for (Valve nextValve : currentValve.getConnectedValves()) {
            maxPressureReleased = Math.max(maxPressureReleased, getMax(minutesRemaining - 1, nextValve));
        }

        cache.put(cacheKey, maxPressureReleased);
        return maxPressureReleased;

    }

    private Integer getMax(int minutesRemaining, Valve currentValveA, Valve currentValveB) {

        // Terminal condition
        if (minutesRemaining == 0) {
            return 0;
        }

        // Check if in cache and if so return value
        CacheKey cacheKey = CacheKey.builder()
                .minutesRemaining(minutesRemaining)
                .currentValveA(currentValveA)
                .currentValveB(currentValveB)
                .openValves(valves.values().stream().filter(Valve::isOpen).collect(Collectors.toSet()))
                .build();
        Integer cacheResult = cache.get(cacheKey);
        if (nonNull(cacheResult)) {
            return cacheResult;
        }

        // check other options, add the max to the cache and return it
        int maxPressureReleased = 0;

        // Both Open
        if (!currentValveA.isOpen() && currentValveA.getFlowRate() > 0 && !currentValveB.isOpen() && currentValveB.getFlowRate() > 0 && !currentValveA.equals(currentValveB)) {
            currentValveA.open();
            currentValveB.open();
            maxPressureReleased = (currentValveA.getFlowRate() * (minutesRemaining - 1)) + (currentValveB.getFlowRate() * (minutesRemaining - 1)) + getMax(minutesRemaining - 1, currentValveA, currentValveB);
            currentValveA.close();
            currentValveB.close();
        }

        // A Open, B Move
        if (!currentValveA.isOpen() && currentValveA.getFlowRate() > 0) {
            currentValveA.open();
            for (Valve nextValveB : currentValveB.getConnectedValves()) {
                maxPressureReleased = Math.max(maxPressureReleased, (currentValveA.getFlowRate() * (minutesRemaining - 1)) + getMax(minutesRemaining - 1, currentValveA, nextValveB));
            }
            currentValveA.close();
        }

        // B Open, A Move
        if (!currentValveB.isOpen() && currentValveB.getFlowRate() > 0) {
            currentValveB.open();
            for (Valve nextValveA : currentValveA.getConnectedValves()) {
                maxPressureReleased = Math.max(maxPressureReleased, (currentValveB.getFlowRate() * (minutesRemaining - 1)) + getMax(minutesRemaining - 1, nextValveA, currentValveB));
            }
            currentValveB.close();
        }

        // Both Move
        for (Valve nextValveA : currentValveA.getConnectedValves()) {
            for (Valve nextValveB : currentValveB.getConnectedValves()) {
                maxPressureReleased = Math.max(maxPressureReleased, getMax(minutesRemaining - 1, nextValveA, nextValveB));
            }
        }

        cache.put(cacheKey, maxPressureReleased);
        return maxPressureReleased;
    }

}
