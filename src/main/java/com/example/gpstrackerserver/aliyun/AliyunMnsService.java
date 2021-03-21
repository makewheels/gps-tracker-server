package com.example.gpstrackerserver.aliyun;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AliyunMnsService {
    @Value("${aliyun.iot.accessKey}")
    private String accessKey;

    @Value("${aliyun.iot.accessSecret}")
    private String accessSecret;

    @Value("${aliyun.iot.region}")
    private String region;

    @Value("${aliyun.iot.productKey}")
    private String productKey;

    public void receive() {
        CloudAccount account = new CloudAccount(accessKey, accessSecret,
                "http://1618784280874658.mns.cn-shanghai.aliyuncs.com/");
        MNSClient client = account.getMNSClient();
        CloudQueue queue = client.getQueueRef("test");

        while (true) {
            Message popMsg = queue.popMessage(10);
            if (popMsg != null) {
                System.out.println("PopMessage Body: " + popMsg.getMessageBodyAsRawString()); //获取原始消息。
                queue.deleteMessage(popMsg.getReceiptHandle()); //从队列中删除消息。
            } else {
                System.out.println("Continuing");
            }
        }
    }

}
