package com.adventofcode.year2022.day13;

import lombok.Getter;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public record PacketPair(@Getter Packet leftPacket, @Getter Packet rightPacket) {

    public Boolean isInCorrectOrder() {
        return leftPacket.compare(rightPacket) > 0 ? FALSE : TRUE;
    }

}
