package com.adventofcode.day15;

import com.adventofcode.common.grid.GridPrinter;
import com.adventofcode.common.grid.PrintableGridElement;
import com.adventofcode.common.grid.SimplePrintableGridElement;
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

    private final Map<Point, PrintableGridElement> grid = new HashMap<>();
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
        Map<Point, PrintableGridElement> printingOverrides = Map.of(new Point(0, 0), SimplePrintableGridElement.of("X"));
        PrintableGridElement defaultPrintingElement = SimplePrintableGridElement.of(" ");

        while (intComputerContext.isRunning()) {
            Direction direction = gatherInput();
            applyDirectionToIntComputerContext(direction, intComputerContext.getInputs());
            targetPosition = calculateTargetPosition(direction);

            intComputer.process(intComputerContext);
            Integer output = intComputerContext.getOutputs().pop().intValue();
            calculateNewPosition(output);

            applyOutputToGrid(output);

            log.info("{}", GridPrinter.print(grid, printingOverrides, defaultPrintingElement));

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
        grid.put(targetPosition, GridElement.getFromValue(output));
    }

}
