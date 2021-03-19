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
@RequestMapping("aliyun")
public class AliyunController {
    @Resource
    private AliyunAmqpService aliyunAmqpService;
    @Resource
    private AliyunMnsService aliyunMnsService;

    @RequestMapping("init")
    @ResponseBody
    public String init() {
        try {
            aliyunAmqpService.init();
            aliyunMnsService.receive();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sssss";
    }
}
