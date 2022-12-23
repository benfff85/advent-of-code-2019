package com.adventofcode.year2022.day15;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import com.adventofcode.common.grid.GridUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.adventofcode.year2022.day15.GridElement.COVERED;
import static com.adventofcode.year2022.day15.GridElement.SENSOR;


@Slf4j
@Component("controller-2022-15")
public class Controller extends SolutionController {


    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2022/day-15.txt");
    }

    public DailyAnswer execute() {


        List<Sensor> sensors = puzzleInput.stream().map(Sensor::new).toList();
        Map<Point, GridElement> grid = initGrid(sensors);

        answer.setPart1(GridUtility.getElementsOnRow(grid, 2000000).stream().filter(e -> List.of(COVERED, SENSOR).contains(e.getValue())).count());
        log.info("P1: {}", answer.getPart1());


        Set<Point> coveredPoints = new HashSet<>();
        for(int y = 0; y <= 4000000; y ++) {

            coveredPoints.clear();
            for(Sensor sensor : sensors) {
                sensor.getCoveredPointsOnRow(y,coveredPoints);
            }

            for (int x = 0; x <= 4000000; x++) {
                if(!grid.keySet().contains(new Point(x,y)) && !coveredPoints.contains(new Point(x,y))) {
                    log.info("{},{}", x,y);
                }
            }
            if(y % 1000 == 0) {
                log.info("y={}", y);
            }

        }


        return answer;

    }

    private Map<Point, GridElement> initGrid(List<Sensor> sensors) {
        Map<Point, GridElement> grid = new HashMap<>();

        // Add all covered points
        sensors.stream()
                .map(s -> s.getCoveredPointsOnRow(2000000).stream().collect(Collectors.toMap(Point::new, x -> COVERED)))
                .forEach(grid::putAll);

        // Add all Sensors
        sensors.forEach(s -> grid.put(s.getSensorPoint(), SENSOR));

        // Add all Beacons
        sensors.forEach(s -> grid.put(s.getBeaconPoint(), GridElement.BEACON));

        return grid;

    }
}
