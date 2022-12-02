package com.adventofcode.year2022.day2;

import lombok.Data;

@Data
public class Round {

    private Shape opponentShape;
    private Shape personalShape;

    public Round(String opponentShapeString, String personalShapeString, String strategy) {
        if ("A".equals(strategy)) {
            opponentShape = Shape.mapFromOpponentKey(opponentShapeString);
            personalShape = Shape.mapFromPersonalKey(personalShapeString);
        } else if ("B".equals(strategy)) {
            opponentShape = Shape.mapFromOpponentKey(opponentShapeString);
            personalShape = getPersonalShapeBasedOnWinStatus(opponentShape, personalShapeString);
        }
    }

    private Shape getPersonalShapeBasedOnWinStatus(Shape opponentShape, String personalShapeString) {
        return switch (personalShapeString) {
            case "X" -> Shape.getShapeWhichWillLoseToOpponent(opponentShape);
            case "Y" -> opponentShape;
            case "Z" -> Shape.getShapeWhichWillBeatOpponent(opponentShape);
            default -> null;
        };
    }

    public Integer getRoundScore() {
        return personalShape.getValue() + getScoreFromWinStatus();
    }

    private Integer getScoreFromWinStatus() {
        if (personalShape.equals(Shape.getShapeWhichWillLoseToOpponent(opponentShape))) {
            return 0;
        } else if (opponentShape.equals(personalShape)) {
            return 3;
        } else {
            return 6;
        }
    }

}
