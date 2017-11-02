package com.lxing.helloWorld.demo.service.impl;

import com.lxing.helloWorld.demo.dao.DemoMapper;
import com.lxing.helloWorld.demo.domain.entity.Demo;
import com.lxing.helloWorld.demo.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


/***
 * Created on 2017/10/10 <br>
 * Description: [demo  service]<br>
 * @author lxing
 * @version 1.0
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    DemoMapper demoMapper;


    @Override
    public int insertDemo(Demo demo) throws IOException {
        System.out.println("2222222222");
        return demoMapper.insert(demo);
    }

    @Override
    public Demo findDemo(int number) throws IOException {
        System.out.println("3333333333--findDemo");
        return demoMapper.select(number);
    }


}
