package com.example.gpstrackerserver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("request")
public class GetRequestHandler {

    @RequestMapping("gnrmc")
    public String request(String gnrmc) {
        System.out.println(gnrmc);
        return "yes";
    }


}
