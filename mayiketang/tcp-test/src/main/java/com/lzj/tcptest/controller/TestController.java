package com.lzj.tcptest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/randomMill")
    public String randomMill() {
        try {
            Random random = new Random();
            int mill = random.nextInt(1000);
            Thread.sleep(mill);
            return "SUCCESS-" + mill;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }
}
