package com.adventofcode.year2022.day18;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

@Slf4j
public class LavaDroplet {

    private static final int SIZE = 20;
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
        droplet.get(cube.getX()).get(cube.getY()).set(cube.getZ(), cube);
        allCubes.add(cube);
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
        try {
            return droplet.get(x).get(y).get(z);
        } catch (Exception e) {
            log.error("Error for {} {} {}", x, y, z);
            return null;
        }
    }

}
