package com.adventofcode.year2022.day16;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;


@Slf4j
@Component("controller-2022-16")
public class Controller extends SolutionController {

    private Map<String, Valve> valves;
    private Integer maxPressureReleased = 0;

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2022/day-16.txt");
    }

    public DailyAnswer execute() {

        valves = puzzleInput.stream().map(Valve::new).collect(Collectors.toMap(Valve::getName, valve -> valve));
        valves.values().forEach(v -> v.linkValves(valves));

        processNext(1, valves.get("AA"), 0);


        return answer;
    }



    private void processNext(int minute, Valve valve, int totalPressureReleased) {

        if(minute > 30) {
            if(maxPressureReleased < totalPressureReleased) {
               maxPressureReleased = totalPressureReleased;
            }
            return;
        }

        totalPressureReleased += valves.values().stream().filter(Valve::isOpen).map(Valve::getFlowRate).mapToInt(Integer::intValue).sum();

        if(!valve.isOpen() && valve.getFlowRate() > 0) {
            valve.open();
            processNext(minute + 1, valve, totalPressureReleased);
            valve.close();
        }

        for(Valve nextValve : valve.getConnectedValves()) {
            processNext(minute + 1, nextValve, totalPressureReleased);
        }


    }

}
