package com.adventofcode.day15;

import com.adventofcode.common.grid.GridPrinter;
import com.adventofcode.common.grid.PrintableGridElement;
import com.adventofcode.common.grid.SimplePrintableGridElement;
import com.adventofcode.day3.Direction;
import com.adventofcode.day5.IntComputer;
import com.adventofcode.day5.IntComputerContext;
import lombok.Getter;
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
    private final Map<Point, PrintableGridElement> grid = new HashMap<>();
    private final Map<Point, PrintableGridElement> printingOverrides = Map.of(new Point(0, 0), SimplePrintableGridElement.of("X"));
    private final PrintableGridElement defaultPrintingElement = SimplePrintableGridElement.of(" ");
    private Point currentPosition = new Point(0, 0);
    private Point targetPosition;
    @Getter
    private Integer pathLength;

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

        Deque<Point> path = new ArrayDeque<>();
        for (Direction direction : List.of(U, D, L, R)) {
            processMove(direction, path);
        }

    }

    // Kinda wierd mix of recursion and modifying globals
    public void processMove(Direction direction, Deque<Point> path) {

        applyDirectionToIntComputerContext(direction, intComputerContext.getInputs());
        targetPosition = calculateTargetPosition(direction);
        intComputer.process(intComputerContext);
        int output = intComputerContext.getOutputs().pop().intValue();
        applyOutputToGrid(output);
        log.debug("{}", GridPrinter.print(grid, printingOverrides, defaultPrintingElement));

        // Hit a wall, return
        if (output == 0) {
            return;
        }

        currentPosition = targetPosition;
        path.push(currentPosition);

        // Found the oxygen, record the distance of the path
        if (output == 2) {
            pathLength = path.size();
        }

        // Try next moves, do not double back by moving the opposite direction that got us here
        List<Direction> nextDirections = new ArrayList<>(List.of(U, D, L, R));
        nextDirections.remove(direction.opposite());
        for (Direction nextDirection : nextDirections) {
            processMove(nextDirection, path);
        }

        // Rollback position
        applyDirectionToIntComputerContext(direction.opposite(), intComputerContext.getInputs());
        targetPosition = calculateTargetPosition(direction.opposite());
        intComputer.process(intComputerContext);
        intComputerContext.getOutputs().pop();
        currentPosition = targetPosition;
        path.poll();

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

    private void applyOutputToGrid(Integer output) {
        grid.put(targetPosition, GridElement.getFromValue(output));
    }

}
