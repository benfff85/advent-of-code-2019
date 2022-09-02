package com.adventofcode.day3;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.adventofcode.day3.Direction.*;
import static java.util.stream.Collectors.toList;

@Getter
public class Wire {

    private final List<Instruction> instructionList;
    private List<Coordinate> path;

    public Wire(List<Instruction> instructionList) {
        this.instructionList = instructionList;
        path = calculatePath(instructionList);
    }

    private List<Coordinate> calculatePath(List<Instruction> instructionList) {

        path = new ArrayList<>(List.of(new Coordinate(0, 0)));
        for(Instruction instruction : instructionList) {
            processInstruction(instruction, path);
        }
        return path;

    }

    private void processInstruction(Instruction instruction, List<Coordinate> path) {
        Integer x = path.get(path.size() -1).x();
        Integer y = path.get(path.size() -1).y();
        for(int i=0; i<instruction.getMagnitude();i++) {
            if(L.equals(instruction.getDirection())) {
                path.add(new Coordinate(--x,y));
            } else if(R.equals(instruction.getDirection())) {
                path.add(new Coordinate(++x,y));
            } else if(D.equals(instruction.getDirection())) {
                path.add(new Coordinate(x,--y));
            }else if(U.equals(instruction.getDirection())) {
                path.add(new Coordinate(x,++y));
            }
        }
    }

}
