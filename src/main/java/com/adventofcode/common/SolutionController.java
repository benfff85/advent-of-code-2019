package com.adventofcode.common;


import java.util.List;

public abstract class SolutionController {
    protected InputHelper inputHelper;
    protected List<String> puzzleInput;
    protected DailyAnswer answer = new DailyAnswer();

    protected SolutionController(InputHelper inputHelper, String puzzleInputFileName) {
        this.inputHelper = inputHelper;
        puzzleInput = inputHelper.read(puzzleInputFileName).stream().toList();
    }

    public abstract DailyAnswer execute();

}
