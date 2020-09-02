package com.example.gpstrackerserver.socket;

import com.example.gpstrackerserver.bean.UploadData;
import com.example.gpstrackerserver.bean.UploadDataDao;
import com.example.gpstrackerserver.util.Constants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * 客户端线程
 * 每当有客户端连接的时候，新建客户端线程
 */
@Data
public class ClientThread extends Thread {
    @Autowired
    private UploadDataDao uploadDataDao;

    private boolean flag = true;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    //记录最后一次客户端上传数据的时间，如果超时，那就关闭这个线程
    private long lastSubmitTime;

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
                //记录时间
                long currentTimeMillis = System.currentTimeMillis();
                //比较时间，如果距离上次传数据已经超时，则结束本线程
                if (currentTimeMillis - lastSubmitTime > Constants.SOCKET_TIME_OUT_MILLIS) {
                    flag = false;
                    return;
                }
                lastSubmitTime = currentTimeMillis;
                if (length == -1) {
                    flag = false;
                }
                String str = new String(bytes, 0, length);
                System.out.println(str);
                handleClientMessage(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理客户端信息
     *
     * @param message
     */
    private void handleClientMessage(String message) {
        UploadData uploadData = new UploadData();
        uploadData.setClientIp(socket.getInetAddress().getHostAddress());
        uploadData.setCreateTime(new Date());
        String[] split = message.split("&");
        for (String kv : split) {
            String[] kvArr = kv.split("=");
            String key = kvArr[0];
            String value = kvArr[1];
            if (key.equals("fileId")) {
                uploadData.setFileId(Long.parseLong(value));
            } else if (key.equals("GNRMC")) {
                uploadData.setData_GNRMC(value);
            }
        }
        uploadDataDao.save(uploadData);
    }
}
