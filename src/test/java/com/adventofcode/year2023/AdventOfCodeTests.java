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
//        assertEquals(108956227, answer.getPart2());
    }

}