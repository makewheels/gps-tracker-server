package com.example.gpstrackerserver;

import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;

/**
 * 客户端线程
 * 每当有客户端连接的时候，新建客户端线程
 */
@Data
public class ClientThread extends Thread {
    private boolean flag;
    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (flag) {
            String ip = socket.getInetAddress().getHostAddress();
            try {
                InputStream inputStream = socket.getInputStream();
                List<String> lines = IOUtils.readLines(inputStream, "utf-8");
                handleClientMessage(lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删掉结尾的回车符
     *
     * @param string
     */
    private void deleteReturnCrlfAtTheEnd(String string) {
        if (string.endsWith("\r")) {
            string = StringUtils.removeEnd(string, "\r");
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
            if (lines.get(i).startsWith("#####HEAD-BODY-SPLIT#####")) {
                headBodySplitIndex = i;
                break;
            }
        }
        //在前面的是head部分
        for (int i = 0; i < headBodySplitIndex - 1; i++) {
            String line = lines.get(i);

        }
    }
}
