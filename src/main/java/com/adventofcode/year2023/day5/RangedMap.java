package com.adventofcode.year2023.day5;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class RangedMap {

    private final Map<Pair<Long, Long>, Long> map = new HashMap<>();

    public void put(Long sourceStart, Long destinationStart, Long rangeLength) {
        map.put(Pair.of(sourceStart, (sourceStart + rangeLength) - 1), destinationStart);
    }

    public int size() {
        return map.size();
    }

    public Long get(Long source) {
        for (Map.Entry<Pair<Long, Long>, Long> entry : map.entrySet()) {
            if (isBetween(source, entry.getKey())) {
                return entry.getValue() + (source - entry.getKey().getLeft());
            }
        }
        return source;
    }

    private boolean isBetween(Long source, Pair<Long, Long> range) {
        return source >= range.getLeft() && source <= range.getRight();
    }
}
