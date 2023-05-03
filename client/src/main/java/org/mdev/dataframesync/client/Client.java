package org.mdev.dataframesync.client;

import org.mdev.dataframesync.common.Message;
import org.mdev.dataframesync.common.protocol.ProtocolType;
import org.mdev.dataframesync.common.protocol.clientserver.CSProtocolA;
import org.mdev.dataframesync.common.protocol.clientserver.CSProtocolB;
import org.mdev.dataframesync.common.protocol.clientserver.CSProtocolC;
import org.mdev.dataframesync.common.protocol.serverclient.SCProtocolFrame;

import java.util.Random;

public class Client {

    @SuppressWarnings("BusyWait")
    public void start() {
        ClientNetwork network = new ClientNetwork();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                network.update();
                network.send(new Message(ProtocolType.CS_Frame.getId(), new SCProtocolFrame()));
            }
        }).start();

        new Thread(() -> {
            Message[] messages = new Message[] {
                    new Message(ProtocolType.CS_A.getId(), new CSProtocolA(1)),
                    new Message(ProtocolType.CS_A.getId(), new CSProtocolA(2)),
                    new Message(ProtocolType.CS_A.getId(), new CSProtocolA(3)),
            };

            Random random = new Random();
            for (Message message : messages) {
                try {
                    Thread.sleep(1 +random.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                network.send(message);
            }
        }).start();

        new Thread(() -> {
            Message[] messages = new Message[] {
                    new Message(ProtocolType.CS_B.getId(), new CSProtocolB(1)),
                    new Message(ProtocolType.CS_B.getId(), new CSProtocolB(2)),
                    new Message(ProtocolType.CS_B.getId(), new CSProtocolB(3)),
            };

            Random random = new Random();
            for (Message message : messages) {
                try {
                    Thread.sleep(1 +random.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                network.send(message);
            }
        }).start();

        new Thread(() -> {
            Message[] messages = new Message[] {
                    new Message(ProtocolType.CS_C.getId(), new CSProtocolC(1)),
                    new Message(ProtocolType.CS_C.getId(), new CSProtocolC(2)),
                    new Message(ProtocolType.CS_C.getId(), new CSProtocolC(3)),
            };

            Random random = new Random();
            for (Message message : messages) {
                try {
                    Thread.sleep(1 +random.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                network.send(message);
            }
        }).start();
    }

}
