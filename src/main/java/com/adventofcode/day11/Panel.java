package com.adventofcode.day11;

import com.adventofcode.common.grid.PrintableGridElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;

import static com.adventofcode.day11.Color.BLACK;

@Getter
@RequiredArgsConstructor
public class Panel implements PrintableGridElement {

    private final Point coordinate;
    private Color color = BLACK;
    private boolean hasBeenPainted = false;

    public Panel(Point point, Color color) {
        this.coordinate = point;
        this.color = color;
    }

    public void paint(Color colorToPaint) {
        if (!colorToPaint.equals(color)) {
            color = colorToPaint;
            hasBeenPainted = true;
        }
    }

    @Override
    public String print() {
        return color.print();
    }
}
