package com.adventofcode.day1;

import com.adventofcode.common.InputReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component("controller-day-1")
public class Controller {

    public Controller(InputReader inputReader) {
        List<Integer> input = inputReader.read("puzzle-input/day-1.txt").stream().map(Integer::parseInt).toList();
        List<Integer> fuelForModules = calculateFuelRequiredForModules(input);
        log.info("Total fuel required for modules: {}", fuelForModules.stream().mapToInt(Integer::intValue).sum());
        List<Integer> totalFuelForModules = calculateTotalFuelRequired(fuelForModules);
        log.info("Total fuel required including fuel for fuel: {}", totalFuelForModules.stream().mapToInt(Integer::intValue).sum());
    }

    private List<Integer> calculateFuelRequiredForModules(List<Integer> moduleMassList) {
        return moduleMassList.stream().map(this::calculateFuelRequiredForMass).toList();
    }

    private List<Integer> calculateTotalFuelRequired(List<Integer> moduleFuelList) {
        return moduleFuelList.stream().map(this::recursiveCalculateFuelNeededForFuel).toList();
    }

    private Integer recursiveCalculateFuelNeededForFuel(Integer mass) {
        return mass <= 0 ? 0 : mass + recursiveCalculateFuelNeededForFuel(calculateFuelRequiredForMass(mass));
    }

    private Integer calculateFuelRequiredForMass(Integer mass) {
        return (mass / 3) - 2;
    }
}
