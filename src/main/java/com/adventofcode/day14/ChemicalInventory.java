package com.adventofcode.day14;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class ChemicalInventory {

    private final Map<Chemical, Integer> chemicals = new HashMap<>();

    public void addChemical(Chemical chemical, Integer quantity) {
        chemicals.put(chemical, chemicals.get(chemical) + quantity);
    }

    public void useChemical(Chemical chemical, Integer quantity) {
        chemicals.put(chemical, chemicals.get(chemical) - quantity);
    }

    public Integer getChemicalQuantity(Chemical chemical) {
        return chemicals.get(chemical);
    }

    public Chemical getChemical(String s) {
        Optional<Chemical> optionalChem = chemicals.keySet().stream().filter(c -> c.getName().equals(s)).findFirst();
        if (optionalChem.isPresent()) {
            return optionalChem.get();
        }
        Chemical chemical = new Chemical(s);
        chemicals.put(chemical, 0);
        return chemical;
    }
}
