package com.lzj;

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 自定义端点
 */
@Endpoint(id = "selfEndpoint")
@Component
public class SelfEndpoint {

    private String name;

    @ReadOperation
    private String getName() {
        //spring http 端点格式
        return "{\"name\": " + name + "}";
    }

    @DeleteOperation
    private void deleteName() {
        this.name = "";
    }

    @WriteOperation
    private String setName(@Selector String name) {
        String oldName = this.name;
        this.name = name;
        return oldName;
    }
}
