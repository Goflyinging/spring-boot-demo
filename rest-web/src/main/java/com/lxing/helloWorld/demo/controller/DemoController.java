package com.lxing.helloWorld.demo.controller;

import com.lxing.helloWorld.demo.domain.entity.Demo;
import com.lxing.helloWorld.demo.service.DemoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/***
 * Created on 2017/10/10 <br>
 * Description: [DemoController]<br>
 * @author lxing
 * @version 1.0
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    DemoService demoService;

    @PostMapping
    public int insert(@RequestBody Demo demo) {
        int i = 0;
        try {
            i = demoService.insertDemo(demo);
        } catch (IOException e) {
            logger.error("insert demo error", e);
        }
        return i;
    }


    @GetMapping("/{number:\\d+}")
    public Demo find(@PathVariable("number") int number) {
        Demo demo = null;
        try {
            demo = demoService.findDemo(number);
        } catch (IOException e) {
            logger.error("insert demo error", e);
        }
        return demo;
    }
}
