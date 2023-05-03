package org.mdev.dataframesync.common.protocol.serverclient;

public class SCProtocolB {

    private final int value;

    public SCProtocolB(int value) {
        this.value = value;
    }

    @SuppressWarnings("unused")
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SCProtocolB{" +
                "value=" + value +
                '}';
    }

}
