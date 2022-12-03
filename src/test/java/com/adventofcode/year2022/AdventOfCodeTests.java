package com.adventofcode.year2022;


import com.adventofcode.common.DailyAnswer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class AdventOfCodeTests {

    @Autowired
    private com.adventofcode.year2022.day1.Controller day1Controller;
    @Autowired
    private com.adventofcode.year2022.day2.Controller day2Controller;
    @Autowired
    private com.adventofcode.year2022.day3.Controller day3Controller;

    @Test
    void testDay1() {
        DailyAnswer answer = day1Controller.execute();
        assertEquals(69206, answer.getPart1());
        assertEquals(197400, answer.getPart2());
    }

    @Test
    void testDay2() {
        DailyAnswer answer = day2Controller.execute();
        assertEquals(14827, answer.getPart1());
        assertEquals(13889, answer.getPart2());
    }

    @Test
    void testDay3() {
        DailyAnswer answer = day3Controller.execute();
        assertEquals(7967, answer.getPart1());
        assertEquals(2716, answer.getPart2());
    }

}