package org.mdev.dataframesync.common.protocol.clientserver;

public class CSProtocolC {

    private final int value;

    public CSProtocolC(int value) {
        this.value = value;
    }

    @SuppressWarnings("unused")
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CSProtocolC{" +
                "value=" + value +
                '}';
    }

}
