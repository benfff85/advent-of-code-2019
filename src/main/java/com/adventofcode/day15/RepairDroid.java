package com.adventofcode.day15;

import com.adventofcode.day3.Direction;
import com.adventofcode.day5.IntComputer;
import com.adventofcode.day5.IntComputerContext;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Queue;
import java.util.*;

import static com.adventofcode.day3.Direction.*;
import static java.lang.Boolean.TRUE;

@Slf4j
public class RepairDroid {

    private final IntComputer intComputer = new IntComputer();
    private final IntComputerContext intComputerContext;

    private final Map<Point, String> grid = new HashMap<>();
    private final Random random = new Random();
    private Point currentPosition = new Point(0, 0);
    private Point targetPosition;


    public RepairDroid(List<BigInteger> instructions) {
        intComputerContext = generateIntComputerContext(instructions);
    }

    private IntComputerContext generateIntComputerContext(List<BigInteger> instructions) {
        return IntComputerContext.builder()
                .instructions(new ArrayList<>(instructions))
                .instructionIndex(0)
                .inputs(new LinkedList<>())
                .outputs(new LinkedList<>())
                .isRunning(TRUE)
                .relativeBase(0)
                .build();
    }


    public void scan() {

        while (intComputerContext.isRunning()) {
            Direction direction = gatherInput();
            applyDirectionToIntComputerContext(direction, intComputerContext.getInputs());
            targetPosition = calculateTargetPosition(direction);

            intComputer.process(intComputerContext);
            Integer output = intComputerContext.getOutputs().pop().intValue();
            calculateNewPosition(output);

            applyOutputToGrid(output);

            log.info("{}", print());

        }

    }


    private Direction gatherInput() {
        return List.of(U, D, L, R).get(random.nextInt(4));
    }

    private void applyDirectionToIntComputerContext(Direction direction, Queue<BigInteger> inputs) {
        inputs.add(BigInteger.valueOf(direction.getValue()));
    }

    private Point calculateTargetPosition(Direction direction) {
        return switch (direction) {
            case U -> new Point(currentPosition.x, currentPosition.y + 1);
            case D -> new Point(currentPosition.x, currentPosition.y - 1);
            case L -> new Point(currentPosition.x - 1, currentPosition.y);
            case R -> new Point(currentPosition.x + 1, currentPosition.y);
        };
    }

    private void calculateNewPosition(Integer output) {
        if (output == 1 || output == 2) {
            currentPosition = targetPosition;
        }
    }

    private void applyOutputToGrid(Integer output) {
        if (output == 0) {
            grid.put(targetPosition, "#");
        } else if (output == 1) {
            grid.put(targetPosition, ".");
        } else {
            grid.put(targetPosition, "*");
        }
    }

    // TODO Create grid printer class
    private String print() {
        int minX = grid.keySet().stream().map(p -> p.x).reduce(Integer::min).orElseThrow();
        int maxX = grid.keySet().stream().map(p -> p.x).reduce(Integer::max).orElseThrow();
        int minY = grid.keySet().stream().map(p -> p.y).reduce(Integer::min).orElseThrow();
        int maxY = grid.keySet().stream().map(p -> p.y).reduce(Integer::max).orElseThrow();

        StringBuilder sb = new StringBuilder();
        sb.append("\n\n");
        for (int i = maxY; i >= minY; i--) {
            for (int j = minX; j <= maxX; j++) {
                if (i == 0 && j == 0) {
                    sb.append("X");
                } else {
                    sb.append(Optional.ofNullable(grid.get(new Point(j, i))).orElse(" "));
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
