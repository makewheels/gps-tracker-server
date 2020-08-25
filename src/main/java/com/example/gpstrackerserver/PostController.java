package com.example.gpstrackerserver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @ResponseBody
    public String uploadData(String data) {

        return "ok";
    }
}
