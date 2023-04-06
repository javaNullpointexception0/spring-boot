package com.lzj.apollo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${name:test-name}")
    private String name;

    @Value("${cluster-namespace}")
    private String clusterNamespace;

    @Autowired
    private Environment environment;

    @RequestMapping("/getName")
    public String getName() {
        System.out.println("name=" + this.name);
        System.out.println("environment.name=" + environment.getProperty("name"));
        return "name:" + name;
    }

    @RequestMapping("/getClusterNamespace")
    public String getClusterNamespace() {
        return clusterNamespace;
    }

}
