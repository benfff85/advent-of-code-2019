package com.adventofcode.year2020;


import com.adventofcode.common.DailyAnswer;
import com.adventofcode.year2019.day8.Layer;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@DisplayName("2020")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AdventOfCodeTests {


    private final com.adventofcode.year2020.day1.Controller day1Controller;

    @Test
    void testDay1() {
        DailyAnswer answer = day1Controller.execute();
        assertEquals(381699, answer.getPart1());
        assertEquals(111605670, answer.getPart2());
    }


}
