package com.adventofcode.year2023;


import com.adventofcode.common.DailyAnswer;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@DisplayName("2023")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AdventOfCodeTests {

    private final com.adventofcode.year2023.day1.Controller day1Controller;
    private final com.adventofcode.year2023.day2.Controller day2Controller;
    private final com.adventofcode.year2023.day3.Controller day3Controller;
    private final com.adventofcode.year2023.day4.Controller day4Controller;
    private final com.adventofcode.year2023.day5.Controller day5Controller;
    private final com.adventofcode.year2023.day6.Controller day6Controller;
    private final com.adventofcode.year2023.day7.Controller day7Controller;
    private final com.adventofcode.year2023.day8.Controller day8Controller;
    private final com.adventofcode.year2023.day9.Controller day9Controller;
    private final com.adventofcode.year2023.day10.Controller day10Controller;

    @Test
    void testDay1() {
        DailyAnswer answer = day1Controller.execute();
        assertEquals(54916, answer.getPart1());
        assertEquals(54728, answer.getPart2());
    }

    @Test
    void testDay2() {
        DailyAnswer answer = day2Controller.execute();
        assertEquals(2256, answer.getPart1());
        assertEquals(74229, answer.getPart2());
    }

    @Test
    void testDay3() {
        DailyAnswer answer = day3Controller.execute();
        assertEquals(539713, answer.getPart1());
        assertEquals(84159075, answer.getPart2());
    }

    @Test
    void testDay4() {
        DailyAnswer answer = day4Controller.execute();
        assertEquals(23847, answer.getPart1());
        assertEquals(8570000, answer.getPart2());
    }

    @Test
    void testDay5() {
        DailyAnswer answer = day5Controller.execute();
        assertEquals(322500873L, answer.getPart1());
        assertEquals(108956227, answer.getPart2());
    }

    @Test
    void testDay6() {
        DailyAnswer answer = day6Controller.execute();
        assertEquals(252000, answer.getPart1());
        assertEquals(36992486, answer.getPart2());
    }

    @Test
    void testDay7() {
        DailyAnswer answer = day7Controller.execute();
        assertEquals(253313241, answer.getPart1());
        assertEquals(253362743, answer.getPart2());
    }

    @Test
    void testDay8() {
        DailyAnswer answer = day8Controller.execute();
        assertEquals(16897L, answer.getPart1());
        assertEquals(16563603485021L, answer.getPart2());
    }

    @Test
    void testDay9() {
        DailyAnswer answer = day9Controller.execute();
        assertEquals(1581679977, answer.getPart1());
        assertEquals(889, answer.getPart2());
    }

    @Test
    void testDay10() {
        DailyAnswer answer = day10Controller.execute();
        assertEquals(7030, answer.getPart1());
        assertEquals(0, answer.getPart2());
    }

}