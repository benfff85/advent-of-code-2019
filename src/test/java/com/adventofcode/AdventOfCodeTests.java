package com.adventofcode;


import com.adventofcode.common.DailyAnswer;
import com.adventofcode.day8.Layer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

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
    @Autowired
    private com.adventofcode.day8.Controller day8Controller;
    @Autowired
    private com.adventofcode.day9.Controller day9Controller;
    @Autowired
    private com.adventofcode.day10.Controller day10Controller;
    @Autowired
    private com.adventofcode.day11.Controller day11Controller;
    @Autowired
    private com.adventofcode.day12.Controller day12Controller;
    @Autowired
    private com.adventofcode.day13.Controller day13Controller;
    @Autowired
    private com.adventofcode.day14.Controller day14Controller;
    @Autowired
    private com.adventofcode.day15.Controller day15Controller;
    @Autowired
    private com.adventofcode.day16.Controller day16Controller;
    @Autowired
    private com.adventofcode.day17.Controller day17Controller;

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
        assertEquals(BigInteger.valueOf(15426686), answer.getPart1());
        assertEquals(BigInteger.valueOf(11430197), answer.getPart2());
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
        assertEquals(BigInteger.valueOf(92663), answer.getPart1());
        assertEquals(BigInteger.valueOf(14365052), answer.getPart2());
    }

    @Test
    void testDay8() {
        DailyAnswer answer = day8Controller.execute();
        assertEquals(1677, answer.getPart1());
        assertEquals(new Layer(6, 25, Arrays.stream("100101110010010111101110010010100101001010000100101001011100100101110010010100101001010010100001110010010100101001010000100000110011100011001000010000".split("")).map(Integer::parseInt).toList()), answer.getPart2());
    }

    @Test
    void testDay9() {
        DailyAnswer answer = day9Controller.execute();
        assertEquals(new BigInteger("3512778005"), answer.getPart1());
        assertEquals(new BigInteger("35920"), answer.getPart2());
    }

    @Test
    void testDay10() {
        DailyAnswer answer = day10Controller.execute();
        assertEquals(319, answer.getPart1());
        assertEquals(517, answer.getPart2());
    }

    @Test
    void testDay11() {
        DailyAnswer answer = day11Controller.execute();
        assertEquals(2056L, answer.getPart1());
        assertEquals("""


                          XX  X    XXX  XXXX XXX    XX XXXX XXX   \s
                         X  X X    X  X X    X  X    X    X X  X  \s
                         X    X    XXX  XXX  X  X    X   X  X  X  \s
                         X XX X    X  X X    XXX     X  X   XXX   \s
                         X  X X    X  X X    X    X  X X    X     \s
                          XXX XXXX XXX  XXXX X     XX  XXXX X     \s
                        """
                , answer.getPart2());
    }

    @Test
    void testDay12() {
        DailyAnswer answer = day12Controller.execute();
        assertEquals(7202, answer.getPart1());
        assertEquals(537881600740876L, answer.getPart2());
    }

    @Test
    void testDay13() {
        DailyAnswer answer = day13Controller.execute();
        assertEquals(265L, answer.getPart1());
        assertEquals(13331L, answer.getPart2());
    }

    // Commented as it's not currently optimized and can take 90s+
    @Test
    void testDay14() {
        DailyAnswer answer = day14Controller.execute();
        assertEquals(522031L, answer.getPart1());
        assertEquals(3566577L, answer.getPart2());
    }

    @Test
    void testDay15() {
        DailyAnswer answer = day15Controller.execute();
        assertEquals(412, answer.getPart1());
        assertEquals(418, answer.getPart2());
    }

//    @Test
//    void testDay16() {
//        DailyAnswer answer = day16Controller.execute();
//        assertEquals(List.of(6, 8, 3, 1, 7, 9, 8, 8), answer.getPart1());
////        assertEquals(0, answer.getPart2());
//    }

    @Test
    void testDay17() {
        DailyAnswer answer = day17Controller.execute();
        assertEquals(8084, answer.getPart1());
//        assertEquals(0, answer.getPart2());
    }

}
