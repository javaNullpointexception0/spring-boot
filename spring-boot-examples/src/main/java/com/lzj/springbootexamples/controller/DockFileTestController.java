package com.lzj.springbootexamples.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dockFileTest")
@Slf4j
public class DockFileTestController {

    @RequestMapping("/sayHello")
    public String sayHello(String name) {
        String msg = "hello, " + name;
        log.info(msg);
        return msg;
    }
}
