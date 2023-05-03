package org.mdev.dataframesync.common.protocol.serverclient;

public class SCProtocolC {

    private final int value;

    public SCProtocolC(int value) {
        this.value = value;
    }

    @SuppressWarnings("unused")
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SCProtocolC{" +
                "value=" + value +
                '}';
    }

}
