package org.mdev.dataframesync.demo;


import org.mdev.dataframesync.client.Client;
import org.mdev.dataframesync.server.Server;

public class Demo {

    public static void main(String[] args) {
        new Server().start();
        new Client().start();
    }

}
