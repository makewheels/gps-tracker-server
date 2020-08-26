package com.example.gpstrackerserver;

import lombok.Data;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP长连接socket总处理器
 */
@Data
public class SocketHandler {
    private static ServerSocket serverSocket;
    private static boolean flag = true;

    public static void init() {
        try {
            if (serverSocket == null) {
                serverSocket = new ServerSocket(Contants.SOCKET_PORT);
            }
            //一直不停的循环接收客户端连接
            while (flag) {
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
