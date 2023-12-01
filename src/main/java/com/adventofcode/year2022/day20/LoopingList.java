package com.adventofcode.year2022.day20;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class LoopingList {

    private final List<Node> list = new LinkedList<>();
    private final Integer listSize;

    public LoopingList(List<Node> inputNodes) {
        list.addAll(inputNodes);
        listSize = list.size();
    }

    public void slide(Node node, Integer value) {
        int startingIndex = list.indexOf(node);
        list.remove(startingIndex);
        list.add(calculateShiftedIndex(startingIndex, value), node);
    }


    private int calculateShiftedIndex(int startingIndex, Integer value) {
        int shiftedIndex = startingIndex + (value % (listSize - 1));
        if (shiftedIndex >= listSize) {
            shiftedIndex = (shiftedIndex % listSize) + 1;
        } else if (shiftedIndex < 0) {
            shiftedIndex = (listSize - 1) + (shiftedIndex % listSize);
        }
        return shiftedIndex;
    }

    public Node getNodeGivenOffset(Integer value, Integer offset) {

        int startingIndex = list.indexOf(list.stream().filter(n -> n.getValue() == value).findFirst().orElseThrow());

        int shiftedIndex = startingIndex + offset;
        if (shiftedIndex >= listSize) {
            shiftedIndex = (shiftedIndex % listSize);
        } else if (shiftedIndex < 0) {
            shiftedIndex = listSize + (shiftedIndex % listSize);
        }

        return list.get(shiftedIndex);
    }
}
