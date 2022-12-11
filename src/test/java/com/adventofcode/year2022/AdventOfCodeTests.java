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
    @Autowired
    private com.adventofcode.year2022.day4.Controller day4Controller;
    @Autowired
    private com.adventofcode.year2022.day5.Controller day5Controller;
    @Autowired
    private com.adventofcode.year2022.day6.Controller day6Controller;
    @Autowired
    private com.adventofcode.year2022.day7.Controller day7Controller;
    @Autowired
    private com.adventofcode.year2022.day8.Controller day8Controller;
    @Autowired
    private com.adventofcode.year2022.day9.Controller day9Controller;

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

    @Test
    void testDay4() {
        DailyAnswer answer = day4Controller.execute();
        assertEquals(526L, answer.getPart1());
        assertEquals(886L, answer.getPart2());
    }

    @Test
    void testDay5() {
        DailyAnswer answer = day5Controller.execute();
        assertEquals("GRTSWNJHH", answer.getPart1());
        assertEquals("QLFQDBBHM", answer.getPart2());
    }

    @Test
    void testDay6() {
        DailyAnswer answer = day6Controller.execute();
        assertEquals(1198, answer.getPart1());
        assertEquals(3120, answer.getPart2());
    }

    @Test
    void testDay7() {
        DailyAnswer answer = day7Controller.execute();
        assertEquals(1501149L, answer.getPart1());
        assertEquals(10096985L, answer.getPart2());
    }

    @Test
    void testDay8() {
        DailyAnswer answer = day8Controller.execute();
        assertEquals(1560, answer.getPart1());
        assertEquals(252000, answer.getPart2());
    }

    @Test
    void testDay9() {
        DailyAnswer answer = day9Controller.execute();
        assertEquals(6190, answer.getPart1());
        assertEquals(2516, answer.getPart2());
    }

}