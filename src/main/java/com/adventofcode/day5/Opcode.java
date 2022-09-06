package com.adventofcode.day5;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.adventofcode.day5.OperationType.*;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static org.apache.commons.lang3.StringUtils.leftPad;

@Getter
public class Opcode {

    private final OperationType operationType;
    private final List<ParameterMode> parameterModes;

    public Opcode(Integer input) {
        operationType = OperationType.getFromValue(input % 100);

        parameterModes = new ArrayList<>();
        if (ADD.equals(operationType) || MULTIPLY.equals(operationType) || LESS_THAN.equals(operationType) || EQUALS.equals(operationType)) {
            String paddedInput = leftPad((input / 100) + "", 3, "0");
            parameterModes.add(ParameterMode.getFromValue(parseInt(valueOf(paddedInput.charAt(2)))));
            parameterModes.add(ParameterMode.getFromValue(parseInt(valueOf(paddedInput.charAt(1)))));
            parameterModes.add(ParameterMode.getFromValue(parseInt(valueOf(paddedInput.charAt(0)))));
        }
        if (JUMP_IF_TRUE.equals(operationType) || JUMP_IF_FALSE.equals(operationType)) {
            String paddedInput = leftPad((input / 100) + "", 2, "0");
            parameterModes.add(ParameterMode.getFromValue(parseInt(valueOf(paddedInput.charAt(1)))));
            parameterModes.add(ParameterMode.getFromValue(parseInt(valueOf(paddedInput.charAt(0)))));
        }
        if (INPUT.equals(operationType) || OUTPUT.equals(operationType) || RELATIVE_BASE_OFFSET.equals(operationType)) {
            String paddedInput = leftPad((input / 100) + "", 1, "0");
            parameterModes.add(ParameterMode.getFromValue(parseInt(valueOf(paddedInput.charAt(0)))));
        }


    }

}
