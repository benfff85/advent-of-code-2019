package com.adventofcode.year2022;


import com.adventofcode.common.DailyAnswer;
import com.adventofcode.year2022.day1.Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class AdventOfCodeTests {

    @Autowired
    private Controller day1Controller;


    @Test
    void testDay1() {
        DailyAnswer answer = day1Controller.execute();
        assertEquals(69206, answer.getPart1());
        assertEquals(197400, answer.getPart2());
    }

}