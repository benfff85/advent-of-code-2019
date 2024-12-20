package com.adventofcode.year2024.day6;

import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.util.Set;

@Data
@Builder
public class GuardAnalysisResult {

    private Set<Point> visitedSpaces;
    private boolean isCyclic;

}
