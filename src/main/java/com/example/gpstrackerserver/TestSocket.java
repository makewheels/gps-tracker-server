package com.example.gpstrackerserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TestSocket {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", Contants.SOCKET_PORT);
        OutputStream outputStream = socket.getOutputStream();
        List<String> lines = new ArrayList<>();
        lines.add("a=1");
        lines.add("b=2");
        lines.add("c=33");
        lines.add("#####HEAD-BODY-SPLIT#####");
        lines.add("bbbbbody=12424");
        lines.add("wagegawe=4444");

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        for (String line : lines) {
            String send = line + "\n";
            dataOutputStream.writeUTF(send);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        System.in.read();
    }
}
