package com.adventofcode.day14;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ReactionRegistry {

    private final Map<Chemical, Reaction> reactions = new HashMap<>();
    private final ChemicalInventory chemicalInventory;

    public ReactionRegistry(ChemicalInventory chemicalInventory) {
        this.chemicalInventory = chemicalInventory;
    }

    public void init(List<String> input) {

        Chemical outputChemical;
        int outputChemicalQuantity;
        for (String s : input) {
            outputChemical = chemicalInventory.getChemical(s.split(" => ")[1].split(" ")[1]);
            outputChemicalQuantity = Integer.parseInt(s.split(" => ")[1].split(" ")[0]);

            Map<Chemical, Integer> inputChemicals = new HashMap<>();
            String[] inputChemStrings = s.split(" => ")[0].split(",");
            for (String inputChemString : inputChemStrings) {
                inputChemicals.put(chemicalInventory.getChemical(inputChemString.trim().split(" ")[1]), Integer.parseInt(inputChemString.trim().split(" ")[0]));
            }
            reactions.put(outputChemical, new Reaction(inputChemicals, outputChemical, outputChemicalQuantity));
        }

        chemicalInventory.addChemical(chemicalInventory.getChemical("ORE"), 1000000);
        log.info("X");


    }

    public void executeReaction(Chemical desiredChemical, Integer desiredAmount) {
        log.info("I want {} {}", desiredAmount, desiredChemical);

        // If we already have what we want just return
        int amountOfChemicalNeeded = desiredAmount - chemicalInventory.getChemicalQuantity(desiredChemical);
        if (amountOfChemicalNeeded <= 0) {
            log.info("I already have {} {}, returning", desiredAmount, desiredChemical);
            return;
        }

        // If we don't have the desired amount, check how many times we need to execute the reaction to get it
        Reaction r = reactions.get(desiredChemical);
        int numberOfReactionsNeeded = Math.ceilDiv(amountOfChemicalNeeded, r.getOutputChemicalQuantity());
        log.info("Attempting to make {} more {} by executing {} {}x", amountOfChemicalNeeded, desiredChemical, r, numberOfReactionsNeeded);

        // Get required chemicals
        Map<Chemical, Integer> inputs = r.getInputChemicals();
        for (Map.Entry<Chemical, Integer> input : inputs.entrySet()) {
            executeReaction(input.getKey(), input.getValue() * numberOfReactionsNeeded);
            log.info("Using {}/{} {}", input.getValue() * numberOfReactionsNeeded, chemicalInventory.getChemicalQuantity(input.getKey()), input.getKey());
            chemicalInventory.useChemical(input.getKey(), input.getValue() * numberOfReactionsNeeded);
        }

        chemicalInventory.addChemical(desiredChemical, r.getOutputChemicalQuantity() * numberOfReactionsNeeded);


    }


}
