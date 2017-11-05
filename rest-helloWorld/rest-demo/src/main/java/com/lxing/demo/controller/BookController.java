package com.lxing.demo.controller;


import com.lxing.demo.domain.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * Created on 2017/11/3 <br>
 * Description: [BookController]<br>
 * @author lxing
 * @version 1.0
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    @PostMapping
    public Book create(@RequestBody Book book) {
        book.setId("1");
        return book;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable("id") String id) {
        logger.info("删除用户：id{}", id);
    }


    @PutMapping("/{id:\\d+}")
    public Book update(@RequestBody Book book) {
        book.setId("1");
        return book;
    }

    @GetMapping("/{id:\\d+}")
    public Book getInfo(@PathVariable("id") String id) {
        Book book = new Book();
        book.setId(id);

        return book;
    }

}
