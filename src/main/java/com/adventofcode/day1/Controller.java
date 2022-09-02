package com.adventofcode.day1;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component("controller-day-1")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/day-1.txt");
    }

    public DailyAnswer execute() {
        List<Integer> input = inputHelper.parseStringListToIntList(puzzleInput);

        List<Integer> fuelForModules = calculateFuelRequiredForModules(input);
        answer.setPart1(fuelForModules.stream().mapToInt(Integer::intValue).sum());
        log.info("Total fuel required for modules: {}", answer.getPart1());

        List<Integer> totalFuelForModules = calculateTotalFuelRequired(fuelForModules);
        answer.setPart2(totalFuelForModules.stream().mapToInt(Integer::intValue).sum());
        log.info("Total fuel required including fuel for fuel: {}", answer.getPart2());

        return answer;
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
