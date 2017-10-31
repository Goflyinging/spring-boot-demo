package com.lxing.demo.service;

import com.lxing.demo.domain.entity.Demo;

import java.io.IOException;


/***
 * Created on 2017/10/10 <br>
 * Description: [demoService]<br>
 * @author lxing
 * @version 1.0
 */
public interface DemoService {


    int insertDemo(Demo demo) throws IOException;

    Demo findDemo(int number) throws IOException;
}
