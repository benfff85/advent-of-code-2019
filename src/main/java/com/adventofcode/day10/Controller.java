package com.adventofcode.day10;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;


@Slf4j
@Component("controller-day-10")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/day-10.txt");
    }

    public DailyAnswer execute() {

        List<Asteroid> asteroids = parseAsteroidList();

        for(Asteroid asteroidA : asteroids) {
            for(Asteroid asteroidB : asteroids) {
                if(!asteroidA.equals(asteroidB)) {
                    assessVisibility(asteroidA, asteroidB);
                }
            }
        }

        int maxVisible = 0;
        for(Asteroid asteroid : asteroids) {
            if(asteroid.getVisibleAsteroids().size() > maxVisible) {
                maxVisible = asteroid.getVisibleAsteroids().size();
            }
        }
        answer.setPart1(maxVisible);
        log.info("Max Visible Asteroids: {}", answer.getPart1());

        return answer;
    }



    private List<Asteroid> parseAsteroidList() {
        List<Asteroid> asteroids = new ArrayList<>();
        for(int i = 0; i < puzzleInput.size(); i++) {
            for (int j = 0; j < puzzleInput.get(i).length(); j++) {
                if (puzzleInput.get(i).charAt(j) == '#') {
                    asteroids.add(new Asteroid(j, i));
                }
            }
        }
        return asteroids;
    }

    private void assessVisibility(Asteroid asteroidA, Asteroid asteroidB) {
        Integer distance = Math.abs(asteroidA.getXCord() - asteroidB.getXCord()) + Math.abs(asteroidA.getYCord() - asteroidB.getYCord());

        Integer run = asteroidB.getXCord() - asteroidA.getXCord();
        Integer rise = asteroidA.getYCord() - asteroidB.getYCord();
        Integer gcd = getGreatestCommonDenominator(rise, run);
        Vector vector = Vector.builder().reducedRise(rise / gcd).reducedRun(run / gcd).build();

        AsteroidRelationship asteroidRelationship = asteroidA.getVisibleAsteroid(vector);
        if (isNull(asteroidRelationship) || asteroidRelationship.getDistance() > distance) {
            asteroidA.addVisibleAsteroid(vector, AsteroidRelationship.builder().asteroid(asteroidB).distance(distance).build());
        }
    }

    private Integer getGreatestCommonDenominator(Integer a, Integer b) {
        return BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
    }

}
