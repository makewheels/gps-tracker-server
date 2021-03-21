package com.example.gpstrackerserver.aliyun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author makewheels
 * @Time 2021.03.14 22:28:45
 */
@Controller
public class AliyunController {
    @Resource
    private AliyunAmqpService aliyunAmqpService;
    @Resource
    private AliyunMnsService aliyunMnsService;

    @RequestMapping("aliyun")
    @ResponseBody
    public String init() {
        new Thread(() -> {
            try {
                aliyunAmqpService.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

//            aliyunMnsService.receive();
        return "sssss";
    }
}
