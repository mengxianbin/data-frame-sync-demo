package org.mdev.dataframesync.common.protocol.clientserver;

public class CSProtocolB {

    private final int value;

    public CSProtocolB(int value) {
        this.value = value;
    }

    @SuppressWarnings("unused")
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CSProtocolB{" +
                "value=" + value +
                '}';
    }

}
