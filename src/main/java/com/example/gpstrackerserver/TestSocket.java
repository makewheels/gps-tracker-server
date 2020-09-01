package com.example.gpstrackerserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TestSocket {
    public static void main(String[] args) throws IOException {
//        Socket socket = new Socket("127.0.0.1", Contants.SOCKET_PORT);
//        Socket socket = new Socket("106.12.57.49", 8500);
        Socket socket = new Socket("c19758058n.imwork.net", 40324);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("agwegew".getBytes());
        outputStream.write("wagh32gg32g32g3".getBytes());
//        List<String> lines = new ArrayList<>();
//        lines.add("a=1");
//        lines.add("b=2");
//        lines.add("c=33");
//        lines.add("#####HEAD-BODY-SPLIT#####");
//        lines.add("bbbbbody=12424");
//        lines.add("wagegawe=4444");
//
//        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//        for (String line : lines) {
//            String send = line + "\n";
//            dataOutputStream.writeUTF(send);
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//        }

        System.in.read();
    }
}
