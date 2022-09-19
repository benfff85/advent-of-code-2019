package com.adventofcode.day12;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Math.abs;


@Slf4j
@Component("controller-day-12")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/day-12.txt");
    }

    public DailyAnswer execute() {

        List<Moon> moons = puzzleInput.stream().map(Moon::new).toList();
        // Loop through velocity
        for (int i = 0; i < 1000; i++) {
            updateVelocities(moons);
            applyVelocity(moons);
        }
        answer.setPart1(getTotalEnergy(moons));
        return answer;

    }



    private void updateVelocities(List<Moon> moons) {

        Moon moon1;
        Moon moon2;
        for (int i = 0; i < moons.size(); i++) {
            moon1 = moons.get(i);
            for (int j = i + 1; j < moons.size(); j++) {
                moon2 = moons.get(j);
                if(moon1.getCurrentCoordinate().getX() < moon2.getCurrentCoordinate().getX()) {
                    moon1.getCurrentVelocity().setX(moon1.getCurrentVelocity().getX() + 1);
                    moon2.getCurrentVelocity().setX(moon2.getCurrentVelocity().getX() - 1);
                } else if (moon1.getCurrentCoordinate().getX() > moon2.getCurrentCoordinate().getX()) {
                    moon1.getCurrentVelocity().setX(moon1.getCurrentVelocity().getX() - 1);
                    moon2.getCurrentVelocity().setX(moon2.getCurrentVelocity().getX() + 1);
                }

                if(moon1.getCurrentCoordinate().getY() < moon2.getCurrentCoordinate().getY()) {
                    moon1.getCurrentVelocity().setY(moon1.getCurrentVelocity().getY() + 1);
                    moon2.getCurrentVelocity().setY(moon2.getCurrentVelocity().getY() - 1);
                } else if (moon1.getCurrentCoordinate().getY() > moon2.getCurrentCoordinate().getY()) {
                    moon1.getCurrentVelocity().setY(moon1.getCurrentVelocity().getY() - 1);
                    moon2.getCurrentVelocity().setY(moon2.getCurrentVelocity().getY() + 1);
                }

                if(moon1.getCurrentCoordinate().getZ() < moon2.getCurrentCoordinate().getZ()) {
                    moon1.getCurrentVelocity().setZ(moon1.getCurrentVelocity().getZ() + 1);
                    moon2.getCurrentVelocity().setZ(moon2.getCurrentVelocity().getZ() - 1);
                } else if (moon1.getCurrentCoordinate().getZ() > moon2.getCurrentCoordinate().getZ()) {
                    moon1.getCurrentVelocity().setZ(moon1.getCurrentVelocity().getZ() - 1);
                    moon2.getCurrentVelocity().setZ(moon2.getCurrentVelocity().getZ() + 1);
                }

            }
        }

    }

    private void applyVelocity(List<Moon> moons) {
        Coordinate coordinate;
        Velocity velocity;
        for(Moon moon : moons) {
            coordinate = moon.getCurrentCoordinate();
            velocity = moon.getCurrentVelocity();
            coordinate.setX(coordinate.getX() + velocity.getX());
            coordinate.setY(coordinate.getY() + velocity.getY());
            coordinate.setZ(coordinate.getZ() + velocity.getZ());
        }
    }

    private Integer getTotalEnergy(List<Moon> moons) {
        int totalEnergy = 0;
        Coordinate coordinate;
        Velocity velocity;
        for(Moon moon : moons) {
            coordinate = moon.getCurrentCoordinate();
            velocity = moon.getCurrentVelocity();
            totalEnergy += ((abs(coordinate.getX()) + abs(coordinate.getY()) +abs(coordinate.getZ())) *  (abs(velocity.getX()) + abs(velocity.getY()) +abs(velocity.getZ())));
        }
        return totalEnergy;
    }

}
