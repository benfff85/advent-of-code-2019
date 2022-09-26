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
        reactionRegistry.executeReaction(chemicalInventory.getChemical("FUEL"), 1);
        answer.setPart1(1000000 - chemicalInventory.getChemicalQuantity(chemicalInventory.getChemical("ORE")));
        return answer;
    }

}
