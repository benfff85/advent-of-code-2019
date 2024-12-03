package com.adventofcode.year2024;


import com.adventofcode.common.DailyAnswer;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@DisplayName("2024")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AdventOfCodeTests {


    private final com.adventofcode.year2024.day1.Controller day1Controller;
    private final com.adventofcode.year2024.day2.Controller day2Controller;
    private final com.adventofcode.year2024.day3.Controller day3Controller;


    @Test
    void testDay1() {
        DailyAnswer answer = day1Controller.execute();
        assertEquals(1660292, answer.getPart1());
        assertEquals(22776016, answer.getPart2());
    }

    @Test
    void testDay2() {
        DailyAnswer answer = day2Controller.execute();
        assertEquals(463L, answer.getPart1());
        assertEquals(514L, answer.getPart2());
    }

    @Test
    void testDay3() {
        DailyAnswer answer = day3Controller.execute();
        assertEquals(163931492, answer.getPart1());
        assertEquals(76911921, answer.getPart2());
    }

}
