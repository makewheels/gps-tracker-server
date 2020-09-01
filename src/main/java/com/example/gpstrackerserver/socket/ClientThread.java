package com.example.gpstrackerserver.socket;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**
 * 客户端线程
 * 每当有客户端连接的时候，新建客户端线程
 */
@Data
public class ClientThread extends Thread {
    private boolean flag = true;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientThread(Socket socket) {
        this.socket = socket;
        try {
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (flag) {
            String ip = socket.getInetAddress().getHostAddress();
            System.out.println(ip);
            try {
                byte[] bytes = new byte[2048];
                int length = inputStream.read(bytes);
                if (length == -1) {
                    flag = false;
                }
                System.out.print("length = " + length);
                String str = new String(bytes, 0, length);
                System.out.println(str);
                if (str.equals("hello")) {
                    outputStream.write("hi".getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理客户端信息
     *
     * @param lines
     */
    private void handleClientMessage(List<String> lines) {
        //先找到head和body的分界线
        int headBodySplitIndex = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.equals("#####HEAD-BODY-SPLIT#####")) {
                headBodySplitIndex = i;
                break;
            }
        }
        //在前面的是head部分
        for (int i = 0; i < headBodySplitIndex - 1; i++) {
            String line = lines.get(i);
            String[] split = line.split("=");
            String key = split[0];
            String value = split[1];
            System.out.println(key + " = " + value + "  ,   length = " + value.length());
        }
    }
}
