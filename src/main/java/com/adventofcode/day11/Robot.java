package com.adventofcode.day11;

import com.adventofcode.day3.Direction;
import com.adventofcode.day5.IntComputer;
import com.adventofcode.day5.IntComputerContext;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.adventofcode.day11.TurnDirection.CCW90;
import static com.adventofcode.day11.TurnDirection.CW90;
import static com.adventofcode.day3.Direction.*;
import static java.util.Objects.requireNonNull;

@Slf4j
public class Robot {

    private final Map<Point, Panel> panels = new HashMap<>();
    private final IntComputer intComputer = new IntComputer();
    private Point position = new Point(0, 0);
    private Direction direction = U;

    public Map<Point, Panel> process(List<BigInteger> instructions, Color startingColor) {
        IntComputerContext context = IntComputerContext.builder()
                .instructions(new LinkedList<>(instructions))
                .instructionIndex(0)
                .inputs(new LinkedList<>())
                .outputs(new LinkedList<>())
                .relativeBase(0)
                .isRunning(true)
                .build();

        panels.put(position, new Panel(position, startingColor));

        Panel panel;
        while (context.isRunning()) {
            panel = getPanel(position);
            context.getInputs().add(BigInteger.valueOf(panel.getColor().getValue()));
            context = intComputer.process(context);
            panel.paint(requireNonNull(Color.getFromValue(requireNonNull(context.getOutputs().poll()).intValue())));
            move(TurnDirection.getFromValue(requireNonNull(context.getOutputs().poll()).intValue()));
        }

        return panels;
    }

    private Panel getPanel(Point point) {
        return panels.computeIfAbsent(point, Panel::new);
    }

    private void move(TurnDirection turnDirection) {
        determineNewDirection(turnDirection);
        position =  switch (direction) {
            case U -> new Point(position.x, position.y + 1);
            case D -> new Point(position.x, position.y - 1);
            case L -> new Point(position.x - 1, position.y);
            case R -> new Point(position.x + 1, position.y);
        };

    }

    // TODO would be nice to move this to soem type of cyclic collection that just rotates
    private void determineNewDirection(TurnDirection turnDirection) {
        if (CW90.equals(turnDirection)) {
            if (U.equals(direction)) {
                direction = R;
            } else if (R.equals(direction)) {
                direction = D;
            } else if (D.equals(direction)) {
                direction = L;
            } else {
                direction = U;
            }
        } else if (CCW90.equals(turnDirection)) {
            if (U.equals(direction)) {
                direction = L;
            } else if (L.equals(direction)) {
                direction = D;
            } else if (D.equals(direction)) {
                direction = R;
            } else {
                direction = U;
            }
        }
    }

}
