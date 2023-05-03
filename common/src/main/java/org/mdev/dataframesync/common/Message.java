package org.mdev.dataframesync.common;

public class Message {

    private final int protocolType;
    private final Object protocolBody;

    public Message(int protocolType, Object protocolBody) {
        this.protocolType = protocolType;
        this.protocolBody = protocolBody;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public Object getProtocolBody() {
        return protocolBody;
    }

}
