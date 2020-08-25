package com.example.gpstrackerserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHandler {
    private static ServerSocket serverSocket;

    public static void init() {
        try {
            serverSocket = new ServerSocket(16500);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    InetAddress inetAddress = socket.getInetAddress();

                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
