package com.adventofcode.day5;

public enum OperationType {

    ADD(1), MULTIPLY(2), INPUT(3), OUTPUT(4), JUMP_IF_TRUE(5), JUMP_IF_FALSE(6), LESS_THAN(7), EQUALS(8), TERMINATE(99);

    private final Integer value;

    OperationType(Integer value) {
        this.value = value;
    }

    public static OperationType getFromValue(Integer value) {
        OperationType[] operationTypes = OperationType.values();
        for (OperationType operationType : operationTypes) {
            if (operationType.value.equals(value)) {
                return operationType;
            }
        }
        return null;
    }

}
