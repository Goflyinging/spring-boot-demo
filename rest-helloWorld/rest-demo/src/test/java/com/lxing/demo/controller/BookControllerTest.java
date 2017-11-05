package com.lxing.demo.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/***
 * Created on 2017/11/3 <br>
 * Description: <br>
 * @author 01369533
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;//虚拟出mvc环境

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @After
    public void after() throws Exception {

    }


    @Test
    public void whenCreateUserSuccess() throws Exception {
        String content = "{\"id\": \"1\",\"bookName\":\"spring\",\"author\":\"spring\",\"price\": 50}";
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON_UTF8)//请求头
                .content(content))//请求体
                .andExpect(MockMvcResultMatchers.status().isOk())//200
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));//返回json串
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        String content = "{\"id\": \"1\",\"bookName\":\"spring\",\"author\":\"spring\",\"price\": 50}";
        mockMvc.perform(MockMvcRequestBuilders.put("/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }


}