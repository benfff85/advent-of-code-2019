package com.adventofcode.year2020;


import com.adventofcode.common.DailyAnswer;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@DisplayName("2020")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AdventOfCodeTests {


    private final com.adventofcode.year2020.day1.Controller day1Controller;
    private final com.adventofcode.year2020.day2.Controller day2Controller;

    @Test
    void testDay1() {
        DailyAnswer answer = day1Controller.execute();
        assertEquals(381699, answer.getPart1());
        assertEquals(111605670, answer.getPart2());
    }

    @Test
    void testDay2() {
        DailyAnswer answer = day2Controller.execute();
        assertEquals(586L, answer.getPart1());
        assertEquals(352L, answer.getPart2());
    }

}
