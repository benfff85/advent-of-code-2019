package com.adventofcode.day7;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import com.adventofcode.day5.IntComputer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component("controller-day-7")
public class Controller extends SolutionController {

    private final IntComputer intComputer;

    public Controller(InputHelper inputHelper, IntComputer intComputer) {
        super(inputHelper, "puzzle-input/day-7.txt");
        this.intComputer = intComputer;
    }

    public DailyAnswer execute() {
        List<Integer> input = inputHelper.parseStringToIntList(puzzleInput.get(0));

        Queue<Integer> intComputerInputs = new LinkedList<>();
        Integer maxSignal = 0;

        for(Integer a : getPhaseSettingList()) {
            intComputerInputs.add(a);
            intComputerInputs.add(0);
            Integer aOut = intComputer.process(new ArrayList<>(input), intComputerInputs);

            for(Integer b : getPhaseSettingList(a)) {
                intComputerInputs.add(b);
                intComputerInputs.add(aOut);
                Integer bOut = intComputer.process(new ArrayList<>(input), intComputerInputs);


                for(Integer c : getPhaseSettingList(a, b)) {
                    intComputerInputs.add(c);
                    intComputerInputs.add(bOut);
                    Integer cOut = intComputer.process(new ArrayList<>(input), intComputerInputs);


                    for(Integer d : getPhaseSettingList(a, b, c)) {
                        intComputerInputs.add(d);
                        intComputerInputs.add(cOut);
                        Integer dOut = intComputer.process(new ArrayList<>(input), intComputerInputs);


                        for(Integer e : getPhaseSettingList(a, b, c, d)) {
                            intComputerInputs.add(e);
                            intComputerInputs.add(dOut);
                            Integer eOut = intComputer.process(new ArrayList<>(input), intComputerInputs);
                            if(eOut > maxSignal) {
                                maxSignal = eOut;
                            }


                        }
                    }
                }
            }
        }

        answer.setPart1(maxSignal);
        log.info("Max signal {}", answer.getPart1());

        return answer;
    }

    private List<Integer> getPhaseSettingList(Integer... usedValues) {
        ArrayList<Integer> values = new ArrayList<>(List.of(0, 1, 2, 3, 4));
        values.removeAll(Arrays.asList(usedValues));
        return values;
    }
}
