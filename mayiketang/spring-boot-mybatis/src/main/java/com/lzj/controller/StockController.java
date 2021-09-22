package com.lzj.controller;

import com.lzj.entities.User;
import com.lzj.service.StockService;
import com.lzj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Created by luzhenjiang
 * @date 2021/9/10 19:44
 * @description
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/deduction/{id}")
    public String deduction(@PathVariable("id") Integer id) throws InterruptedException {
        AtomicInteger failTime = new AtomicInteger(0);
        AtomicInteger successTime = new AtomicInteger(0);
        Thread thread1 = startTask(id, failTime, successTime);
        Thread thread2 = startTask(id, failTime, successTime);
        thread1.join();
        thread2.join();
        System.out.println("成功次数：" + successTime.get() + "，失败次数：" + failTime.get());
        return "并发测试完成";
    }

    private Thread startTask(Integer id, AtomicInteger failTime, AtomicInteger successTime) {
        long endTime = System.currentTimeMillis() + 5000L;
        Thread thread = new Thread(() -> {
            while (true) {
                if (System.currentTimeMillis() - endTime > 0) {
                    break;
                }
                try {
                    int flg = stockService.deduction(id, 1L);
                    successTime.incrementAndGet();
                } catch (Exception e) {
                    failTime.incrementAndGet();
                }
            }
        });
        thread.start();
        return thread;
    }
}
