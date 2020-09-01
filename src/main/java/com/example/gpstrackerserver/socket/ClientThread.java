package com.example.gpstrackerserver.socket;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.DataInputStream;
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
//                List<String> lines = IOUtils.readLines(inputStream, Contants.CHARSET);
//                System.out.println(lines);
//                handleClientMessage(lines);


//                InputStream inputStream = socket.getInputStream();
//                byte[] bytes = new byte[1024];
//                int len;
//                StringBuilder sb = new StringBuilder();
//                while ((len = inputStream.read(bytes)) != -1) {
//                    //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//                    sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
//                }
//                System.out.println("get message from client: " + sb);


                DataInputStream dataInputStream = new DataInputStream(inputStream);
                String s = dataInputStream.readUTF();
                System.out.println(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删掉结尾的回车符
     *
     * @param string
     * @return
     */
    private String deleteReturnCrlfAtTheEnd(String string) {
        for (int i = 0; i < 2; i++) {
            if (string.endsWith("\n")) {
                string = StringUtils.removeEnd(string, "\n");
            }
            if (string.endsWith("\r")) {
                string = StringUtils.removeEnd(string, "\r");
            }
        }
        return string;
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
