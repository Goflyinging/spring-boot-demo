package com.lxing.demo.controller;

import com.lxing.demo.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/***
 * Created on 2017/10/10 <br>
 * Description: [UserController]<br>
 * @author lxing
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
@Api(value = "测试Swagger2", description = "简单的API")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping
    @ApiOperation(value = "创建用户")
    @ApiImplicitParam(dataType = "User", name = "user", value = "用户信息", required = true)
    @ApiResponses({
            @ApiResponse(code = 500, message = "接口异常"),
    })
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation(value = "删除用户")
    public void delete(@PathVariable String id) {
        logger.info("删除用户：id{}", id);
    }


    @PutMapping("/{id:\\d+}")
    @ApiOperation(value = "更新用户")
    public User update(@RequestBody User user) {
        user.setId("1");
        return user;
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "获取用户")
    public User getInfo(@PathVariable("id") String id) {
        User user = new User();
        user.setId(id);
        user.setUsername("tom");
        return user;
    }
}
