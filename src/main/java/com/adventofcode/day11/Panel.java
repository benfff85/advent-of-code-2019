package com.adventofcode.day11;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;

import static com.adventofcode.day11.Color.BLACK;

@Getter
@RequiredArgsConstructor
public class Panel {

    private final Point coordinate;
    private Color color = BLACK;
    private boolean hasBeenPainted = false;

    public void paint(Color colorToPaint) {
        if (!colorToPaint.equals(color)) {
            color = colorToPaint;
            hasBeenPainted = true;
        }
    }

}
