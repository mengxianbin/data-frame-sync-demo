package org.mdev.dataframesync.server;

import org.mdev.dataframesync.common.Message;
import org.mdev.dataframesync.common.VirtualNetwork;
import org.mdev.dataframesync.common.protocol.ProtocolType;

import java.util.LinkedList;
import java.util.Queue;

public class ServerNetwork {

    public void send(Message message) {
        VirtualNetwork.SERVER_CLIENT_MESSAGE_QUEUE.add(message);
    }

    public void update() {
        Message message;
        while ((message = VirtualNetwork.CLIENT_SERVER_MESSAGE_QUEUE.poll()) != null) {
            receive(message);
        }
    }

    void receive(Message message) {
        ProtocolType type = ProtocolType.getType(message.getProtocolType());
        switch (type) {
            case CS_A:
                aQueue.add(message);
                break;

            case CS_B:
            case CS_C:
                xQueue.add(message);
                break;

            case CS_Frame: {
                Message m;
                while ((m = aQueue.poll()) != null) {
                    System.out.println(m.getProtocolBody());
                }
                while ((m = xQueue.poll()) != null) {
                    System.out.println(m.getProtocolBody());
                }
                break;
            }

        }
    }

    private final Queue<Message> aQueue = new LinkedList<>();
    private final Queue<Message> xQueue = new LinkedList<>();

}
