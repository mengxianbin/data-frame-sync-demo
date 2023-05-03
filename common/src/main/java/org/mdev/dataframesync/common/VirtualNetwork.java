package org.mdev.dataframesync.common;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class VirtualNetwork {

    public static final Queue<Message> CLIENT_SERVER_MESSAGE_QUEUE = new ConcurrentLinkedQueue<>();
    public static final Queue<Message> SERVER_CLIENT_MESSAGE_QUEUE = new ConcurrentLinkedQueue<>();

}
