package org.mdev.dataframesync.client;

import org.mdev.dataframesync.common.Message;
import org.mdev.dataframesync.common.protocol.ProtocolType;
import org.mdev.dataframesync.common.protocol.clientserver.CSProtocolA;
import org.mdev.dataframesync.common.protocol.clientserver.CSProtocolB;
import org.mdev.dataframesync.common.protocol.clientserver.CSProtocolC;
import org.mdev.dataframesync.common.protocol.serverclient.SCProtocolFrame;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {

    @SuppressWarnings({"BusyWait", "DuplicatedCode"})
    public void start() {
        ClientNetwork network = new ClientNetwork();

        Semaphore lockA = new Semaphore(1);
        Semaphore lockB = new Semaphore(1);
        Semaphore lockC = new Semaphore(1);

        AtomicInteger frameBase = new AtomicInteger();

        // UPDATE

        new Thread(() -> {
            while (true) {
                System.out.println();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                network.update();
                network.send(new Message(ProtocolType.CS_Frame.getId(), new SCProtocolFrame()));

                System.out.println();

                frameBase.incrementAndGet();

                lockA.release();
                lockB.release();
                lockC.release();
            }
        }).start();



        // THREAD A

        new Thread(() -> {
            Random random = new Random();

            while (true) {
                try {
                    lockA.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Message[] messages = new Message[] {
                        new Message(ProtocolType.CS_A.getId(), new CSProtocolA(frameBase.get() * 10 + 1)),
                        new Message(ProtocolType.CS_A.getId(), new CSProtocolA(frameBase.get() * 10 + 2)),
                        new Message(ProtocolType.CS_A.getId(), new CSProtocolA(frameBase.get() * 10 + 3)),
                };

                for (Message message : messages) {
                    try {
                        Thread.sleep(1 +random.nextInt(300));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    network.send(message);
                }
            }
        }).start();

        // THREAD B

        new Thread(() -> {
            Random random = new Random();

            while (true) {
                try {
                    lockB.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Message[] messages = new Message[] {
                        new Message(ProtocolType.CS_B.getId(), new CSProtocolB(frameBase.get() * 10 + 1)),
                        new Message(ProtocolType.CS_B.getId(), new CSProtocolB(frameBase.get() * 10 + 2)),
                        new Message(ProtocolType.CS_B.getId(), new CSProtocolB(frameBase.get() * 10 + 3)),
                };

                for (Message message : messages) {
                    try {
                        Thread.sleep(1 +random.nextInt(300));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    network.send(message);
                }
            }
        }).start();

        // THREAD C

        new Thread(() -> {
            Random random = new Random();

            while (true) {
                try {
                    lockC.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Message[] messages = new Message[] {
                        new Message(ProtocolType.CS_C.getId(), new CSProtocolC(frameBase.get() * 10 + 1)),
                        new Message(ProtocolType.CS_C.getId(), new CSProtocolC(frameBase.get() * 10 + 2)),
                        new Message(ProtocolType.CS_C.getId(), new CSProtocolC(frameBase.get() * 10 + 3)),
                };

                for (Message message : messages) {
                    try {
                        Thread.sleep(1 +random.nextInt(300));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    network.send(message);
                }
            }
        }).start();
    }

}
