package com.adventofcode.day6;

import com.adventofcode.common.InputReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("controller-day-6")
public class Controller {

    public Controller(InputReader inputReader) {
        List<String> input = inputReader.read("puzzle-input/day-6.txt");
        Map<String, SpaceObject> spaceObjects = new HashMap<>();

        for (String orbitMapEntry : input) {
            String[] inputRow = orbitMapEntry.split("\\)");
            SpaceObject orbitedObject = createOrFetchSpaceObject(spaceObjects, inputRow[0]);
            SpaceObject orbitingObject = createOrFetchSpaceObject(spaceObjects, inputRow[1]);
            linkParentAndChildSpaceObjects(orbitedObject, orbitingObject);
        }


        int directOrbitCount = 0;
        int indirectOrbitCount = 0;
        int stepsToCenterOfMass;
        for (SpaceObject spaceObject : spaceObjects.values()) {
            stepsToCenterOfMass = getStepsToCenterOfMass(spaceObject);
            if (stepsToCenterOfMass > 0) {
                directOrbitCount++;
                indirectOrbitCount += stepsToCenterOfMass - 1;
            }
        }

        log.info("Total Orbits: {}", indirectOrbitCount + directOrbitCount);


        List<SpaceObject> a = getSpaceObjectsToCenterOfMass(spaceObjects.get("YOU"));
        List<SpaceObject> b = getSpaceObjectsToCenterOfMass(spaceObjects.get("SAN"));
        for (SpaceObject spaceObject : a) {
            if (b.contains(spaceObject)) {
                log.info("Orbital transfers required: {}", a.indexOf(spaceObject) + b.indexOf(spaceObject));
                break;
            }
        }

    }


    private List<SpaceObject> getSpaceObjectsToCenterOfMass(SpaceObject spaceObject) {
        List<SpaceObject> spaceObjects = new ArrayList<>();
        while (!"COM".equals(spaceObject.getName())) {
            spaceObject = spaceObject.getParent();
            spaceObjects.add(spaceObject);
        }
        return spaceObjects;
    }

    private int getStepsToCenterOfMass(SpaceObject spaceObject) {
        int stepCount = 0;
        while (!"COM".equals(spaceObject.getName())) {
            stepCount++;
            spaceObject = spaceObject.getParent();
        }
        return stepCount;
    }

    private SpaceObject createOrFetchSpaceObject(Map<String, SpaceObject> spaceObjects, String name) {
        if (spaceObjects.containsKey(name)) {
            return spaceObjects.get(name);
        }
        SpaceObject spaceObject = new SpaceObject(name);
        spaceObjects.put(name, spaceObject);
        return spaceObject;
    }

    private void linkParentAndChildSpaceObjects(SpaceObject parent, SpaceObject child) {
        child.setParent(parent);
        parent.addChild(child);
    }

}
