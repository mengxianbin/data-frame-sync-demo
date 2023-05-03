package org.mdev.dataframesync.common.protocol.serverclient;

public class SCProtocolA {

    private final int value;

    public SCProtocolA(int value) {
        this.value = value;
    }

    @SuppressWarnings("unused")
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SCProtocolA{" +
                "value=" + value +
                '}';
    }

}
