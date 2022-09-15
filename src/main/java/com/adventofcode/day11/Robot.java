package com.adventofcode.day11;

import com.adventofcode.day3.Direction;
import com.adventofcode.day5.IntComputer;
import com.adventofcode.day5.IntComputerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.adventofcode.day11.Color.BLACK;
import static com.adventofcode.day11.Color.WHITE;
import static com.adventofcode.day11.TurnDirection.CCW90;
import static com.adventofcode.day11.TurnDirection.CW90;
import static com.adventofcode.day3.Direction.*;
import static java.lang.Boolean.TRUE;

@Slf4j
@Component
public class Robot {

    private final Map<Point, Panel> panels = new HashMap<>();
    private final IntComputer intComputer = new IntComputer();
    private IntComputerContext context;
    private Point position = new Point(0,0);
    private Direction direction = U;

    public void loadInstructions(List<BigInteger> instructions) {
        context = IntComputerContext.builder()
                .instructions(new LinkedList<>(instructions))
                .instructionIndex(0)
                .inputs(new LinkedList<>())
                .outputs(new LinkedList<>())
                .relativeBase(0)
                .isRunning(true)
                .build();
    }

    public long process() {

        Panel panel;
        while(TRUE.equals(context.getIsRunning())) {
            panel = getPanel(position);
            context.getInputs().add(BigInteger.valueOf(getColorInt(panel.getColor())));
            context = intComputer.process(context);
            panel.paint(getColor(context.getOutputs().poll().intValue()));
            move(getTurnDirection(context.getOutputs().poll().intValue()));
        }

        return panels.values().stream().filter(Panel::isHasBeenPainted).count();
    }

    // TODO Move to Enum
    private TurnDirection getTurnDirection(int i) {
        if(0 == i) {
            return CCW90;
        }
        return CW90;
    }

    // TODO Move to Enum
    private Color getColor(int i) {
        if(0 == i) {
            return BLACK;
        }
        return WHITE;
    }

    // TODO Move to Enum
    private int getColorInt(Color color) {
        if(BLACK.equals(color)) {
            return 0;
        }
        return 1;
    }

    private Panel getPanel(Point point) {
        return panels.computeIfAbsent(point, Panel::new);
    }

    private void move(TurnDirection turnDirection){
        determineNewDirection(turnDirection);
        if(U.equals(direction)) {
            position = new Point(position.x, position.y + 1);
        } else if(R.equals(direction)) {
            position = new Point(position.x + 1, position.y);
        } else if (D.equals(direction)) {
            position = new Point(position.x, position.y - 1);
        } else {
            position = new Point(position.x - 1, position.y);
        }
    }

    // TODO would be nice to move this to soem type of cyclic collection that just rotates
    private void determineNewDirection(TurnDirection turnDirection) {
        if(CW90.equals(turnDirection)) {
            if(U.equals(direction)) {
                direction = R;
            } else if(R.equals(direction)) {
                direction = D;
            } else if (D.equals(direction)) {
                direction = L;
            } else {
                direction = U;
            }
        } else if (CCW90.equals(turnDirection)) {
            if(U.equals(direction)) {
                direction = L;
            } else if(L.equals(direction)) {
                direction = D;
            } else if (D.equals(direction)) {
                direction = R;
            } else {
                direction = U;
            }
        }
    }

}
