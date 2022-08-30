package com.adventofcode.day7;

import com.adventofcode.common.InputReader;
import com.adventofcode.day5.IntComputer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component("controller-day-7")
public class Controller {

    public Controller(InputReader inputReader, IntComputer intComputer) {
        List<Integer> input = Arrays.stream(inputReader.read("puzzle-input/day-7.txt").get(0).split(",")).map(Integer::parseInt).toList();

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
                            log.info("{}:{}:{}:{}:{}", a,b,c,d,e);
                            if(eOut > maxSignal) {
                                log.info("{}:{}:{}:{}:{}", aOut,bOut,cOut,dOut,eOut);
                                maxSignal = eOut;
                            }


                        }
                    }
                }
            }
        }

        log.info("Max signal {}", maxSignal);


    }

    private List<Integer> getPhaseSettingList(Integer... usedValues) {
        ArrayList<Integer> values = new ArrayList<>(List.of(0, 1, 2, 3, 4));
        values.removeAll(Arrays.asList(usedValues));
        return values;
    }
}
