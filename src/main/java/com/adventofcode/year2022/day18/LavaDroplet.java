package com.adventofcode.year2022.day18;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
public class LavaDroplet {

    private static final int SIZE = 25;
    private final List<List<List<Cube>>> droplet = new ArrayList<>(SIZE);
    private final Set<Cube> allCubes = new HashSet<>();


    public LavaDroplet() {

        for (int x = 0; x < SIZE; x++) {
            // For every x add a list for y
            droplet.add(x, new ArrayList<>(SIZE));
            for (int y = 0; y < SIZE; y++) {
                // For every y add a list for z
                droplet.get(x).add(y, new ArrayList<>(SIZE));
                for (int z = 0; z < SIZE; z++) {
                    droplet.get(x).get(y).add(null);
                }
            }
        }
    }

    public void addCube(Cube cube) {
        allCubes.add(cube);
        setCube(cube);
    }

    public int calculateSurfaceArea() {
        int surfaceArea = 0;

        for (Cube cube : allCubes) {
            if (isNull(getCube(cube.getX() - 1, cube.getY(), cube.getZ()))) {
                surfaceArea++;
            }
            if (isNull(getCube(cube.getX() + 1, cube.getY(), cube.getZ()))) {
                surfaceArea++;
            }
            if (isNull(getCube(cube.getX(), cube.getY() - 1, cube.getZ()))) {
                surfaceArea++;
            }
            if (isNull(getCube(cube.getX(), cube.getY() + 1, cube.getZ()))) {
                surfaceArea++;
            }
            if (isNull(getCube(cube.getX(), cube.getY(), cube.getZ() - 1))) {
                surfaceArea++;
            }
            if (isNull(getCube(cube.getX(), cube.getY(), cube.getZ() + 1))) {
                surfaceArea++;
            }
        }

        return surfaceArea;

    }

    public Cube getCube(int x, int y, int z) {
        return droplet.get(x + 1).get(y + 1).get(z + 1);
//        return droplet.get(x).get(y).get(z);

    }

    public void setCube(Cube cube) {
        // Adding 1 to pad lava droplet away from the edge
        droplet.get(cube.getX() + 1).get(cube.getY() + 1).set(cube.getZ() + 1, cube);
    }

    // TODO move this all somewhere
    private int contactPointCount = 0;
    private final Set<Cube> processedCubes = new HashSet<>();
    private final Deque<Cube> cubesToProcess = new LinkedList<>();

    public int calculateContiguousSurfaceArea() {
        cubesToProcess.push(new Cube(0, 0, 0));
        getContactPointsCount();
        return contactPointCount;
    }

    private void getContactPointsCount() {
        Cube cube;

        while (!cubesToProcess.isEmpty()) {
            cube = cubesToProcess.pop();
            processedCubes.add(cube);

            Cube adjecentCube;

            // Check left
            if (cube.getX() >= 0) {
                // If it exists as a cube (lava) add it as a contact point
                if (nonNull(getCube(cube.getX() - 1, cube.getY(), cube.getZ()))) {
                    contactPointCount++;
                }
                // Otherwise check if not already processed, process it
                else {
                    adjecentCube = new Cube(cube.getX() - 1, cube.getY(), cube.getZ());
                    if (!processedCubes.contains(adjecentCube) && !cubesToProcess.contains(adjecentCube)) {
                        cubesToProcess.push(adjecentCube);
                    }
                }
            }

            // Check right
            if (cube.getX() < SIZE - 2) {
                // If it exists as a cube (lava) add it as a contact point
                if (nonNull(getCube(cube.getX() + 1, cube.getY(), cube.getZ()))) {
                    contactPointCount++;
                }
                // Otherwise check if not already processed, process it
                else {
                    adjecentCube = new Cube(cube.getX() + 1, cube.getY(), cube.getZ());
                    if (!processedCubes.contains(adjecentCube) && !cubesToProcess.contains(adjecentCube)) {
                        cubesToProcess.push(adjecentCube);
                    }
                }
            }

            // Check down
            if (cube.getY() >= 0) {
                // If it exists as a cube (lava) add it as a contact point
                if (nonNull(getCube(cube.getX(), cube.getY() - 1, cube.getZ()))) {
                    contactPointCount++;
                }
                // Otherwise check if not already processed, process it
                else {
                    adjecentCube = new Cube(cube.getX(), cube.getY() - 1, cube.getZ());
                    if (!processedCubes.contains(adjecentCube) && !cubesToProcess.contains(adjecentCube)) {
                        cubesToProcess.push(adjecentCube);
                    }
                }
            }

            // Check up
            if (cube.getY() < SIZE - 2) {
                // If it exists as a cube (lava) add it as a contact point
                if (nonNull(getCube(cube.getX(), cube.getY() + 1, cube.getZ()))) {
                    contactPointCount++;
                }
                // Otherwise check if not already processed, process it
                else {
                    adjecentCube = new Cube(cube.getX(), cube.getY() + 1, cube.getZ());
                    if (!processedCubes.contains(adjecentCube) && !cubesToProcess.contains(adjecentCube)) {
                        cubesToProcess.push(adjecentCube);
                    }
                }
            }

            // Check forward
            if (cube.getZ() >= 0) {
                // If it exists as a cube (lava) add it as a contact point
                if (nonNull(getCube(cube.getX(), cube.getY(), cube.getZ() - 1))) {
                    contactPointCount++;
                }
                // Otherwise check if not already processed, process it
                else {
                    adjecentCube = new Cube(cube.getX(), cube.getY(), cube.getZ() - 1);
                    if (!processedCubes.contains(adjecentCube) && !cubesToProcess.contains(adjecentCube)) {
                        cubesToProcess.push(adjecentCube);
                    }
                }
            }

            // Check backward
            if (cube.getZ() < SIZE - 2) {
                // If it exists as a cube (lava) add it as a contact point
                if (nonNull(getCube(cube.getX(), cube.getY(), cube.getZ() + 1))) {
                    contactPointCount++;
                }
                // Otherwise check if not already processed, process it
                else {
                    adjecentCube = new Cube(cube.getX(), cube.getY(), cube.getZ() + 1);
                    if (!processedCubes.contains(adjecentCube) && !cubesToProcess.contains(adjecentCube)) {
                        cubesToProcess.push(adjecentCube);
                    }
                }
            }
        }

    }

}
