package com.lxing.swagger.controller;


import com.lxing.swagger.domain.Book;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/***
 * Created on 2017/11/3 <br>
 * Description: [BookController]<br>
 * @author lxing
 * @version 1.0
 */
@RestController
@RequestMapping("/books")
@Api(value = "/books", description = "书籍API")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    @PostMapping
    @ApiOperation(value = "新增书籍")
    @ApiImplicitParam(dataType = "Book", name = "book", value = "书籍信息", required = true)
    @ApiResponses({
            @ApiResponse(code = 500, message = "接口异常"),
    })
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
