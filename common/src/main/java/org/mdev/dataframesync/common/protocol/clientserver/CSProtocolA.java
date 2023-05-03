package org.mdev.dataframesync.common.protocol.clientserver;

public class CSProtocolA {

    private final int value;

    public CSProtocolA(int value) {
        this.value = value;
    }

    @SuppressWarnings("unused")
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CSProtocolA{" +
                "value=" + value +
                '}';
    }
}
