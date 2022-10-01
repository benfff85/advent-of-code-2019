package com.adventofcode.day14;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component("controller-day-14")
public class Controller extends SolutionController {

    private final ReactionRegistry reactionRegistry;
    private final ChemicalInventory chemicalInventory;

    public Controller(InputHelper inputHelper, ReactionRegistry reactionRegistry, ChemicalInventory chemicalInventory) {
        super(inputHelper, "puzzle-input/day-14.txt");
        this.reactionRegistry = reactionRegistry;
        this.chemicalInventory = chemicalInventory;
    }

    public DailyAnswer execute() {

        reactionRegistry.init(puzzleInput);
        long fuel = 0;

        try {
            // Some arbitrary large number that will cause ORE to be exhausted and an exception thrown
            while (fuel <= 1000000000) {
                reactionRegistry.executeReaction("FUEL", 1L);
                fuel++;
                chemicalInventory.useChemical("FUEL", 1L);

                if (fuel == 1) {
                    answer.setPart1(Config.INITIAL_ORE - chemicalInventory.getChemicalQuantity("ORE"));
                    log.info("Ore required to generate one fuel: {}", answer.getPart1());
                }

            }
        } catch (Exception e) {
            answer.setPart2(fuel);
            log.info("Total fuel created with one trillion ORE: {}", answer.getPart2());
        }

        return answer;
    }

}
