package com.adventofcode;


import com.adventofcode.common.DailyAnswer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class AdventOfCodeTests {

    @Autowired
    private com.adventofcode.day1.Controller day1Controller;
    @Autowired
    private com.adventofcode.day2.Controller day2Controller;
    @Autowired
    private com.adventofcode.day3.Controller day3Controller;
    @Autowired
    private com.adventofcode.day4.Controller day4Controller;
    @Autowired
    private com.adventofcode.day5.Controller day5Controller;
    @Autowired
    private com.adventofcode.day6.Controller day6Controller;
    @Autowired
    private com.adventofcode.day7.Controller day7Controller;

    @Test
    void testDay1() {
        DailyAnswer answer = day1Controller.execute();
        assertEquals(3231941, answer.getPart1());
        assertEquals(4845049, answer.getPart2());
    }

    @Test
    void testDay2() {
        DailyAnswer answer = day2Controller.execute();
        assertEquals(4570637, answer.getPart1());
        assertEquals(5485, answer.getPart2());
    }

    @Test
    void testDay3() {
        DailyAnswer answer = day3Controller.execute();
        assertEquals(2129, answer.getPart1());
        assertEquals(134662, answer.getPart2());
    }

    @Test
    void testDay4() {
        DailyAnswer answer = day4Controller.execute();
        assertEquals(466, answer.getPart1());
        assertEquals(292, answer.getPart2());
    }

    @Test
    void testDay5() {
        DailyAnswer answer = day5Controller.execute();
        assertEquals(15426686, answer.getPart1());
        assertEquals(11430197, answer.getPart2());
    }

    @Test
    void testDay6() {
        DailyAnswer answer = day6Controller.execute();
        assertEquals(315757, answer.getPart1());
        assertEquals(481, answer.getPart2());
    }

    @Test
    void testDay7() {
        DailyAnswer answer = day7Controller.execute();
        assertEquals(92663, answer.getPart1());
        assertEquals(14365052, answer.getPart2());
    }

}
