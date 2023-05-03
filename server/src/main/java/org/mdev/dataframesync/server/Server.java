package org.mdev.dataframesync.server;

import org.mdev.dataframesync.common.Message;
import org.mdev.dataframesync.common.protocol.ProtocolType;
import org.mdev.dataframesync.common.protocol.serverclient.SCProtocolA;
import org.mdev.dataframesync.common.protocol.serverclient.SCProtocolB;
import org.mdev.dataframesync.common.protocol.serverclient.SCProtocolC;
import org.mdev.dataframesync.common.protocol.serverclient.SCProtocolFrame;

import java.util.Random;

public class Server {

    @SuppressWarnings("BusyWait")
    public void start() {
        ServerNetwork network = new ServerNetwork();

        new Thread(() -> {
            while (true) {
                System.out.println();

                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                network.update();
                network.send(new Message(ProtocolType.SC_Frame.getId(), new SCProtocolFrame()));

                System.out.println();
            }
        }).start();

        new Thread(() -> {
            Message[] messages = new Message[] {
                    new Message(ProtocolType.SC_A.getId(), new SCProtocolA(1)),
                    new Message(ProtocolType.SC_A.getId(), new SCProtocolA(2)),
                    new Message(ProtocolType.SC_A.getId(), new SCProtocolA(3)),
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
                    new Message(ProtocolType.SC_B.getId(), new SCProtocolB(1)),
                    new Message(ProtocolType.SC_B.getId(), new SCProtocolB(2)),
                    new Message(ProtocolType.SC_B.getId(), new SCProtocolB(3)),
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
                    new Message(ProtocolType.SC_C.getId(), new SCProtocolC(1)),
                    new Message(ProtocolType.SC_C.getId(), new SCProtocolC(2)),
                    new Message(ProtocolType.SC_C.getId(), new SCProtocolC(3)),
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
