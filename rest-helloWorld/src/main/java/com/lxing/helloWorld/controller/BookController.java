package com.lxing.helloWorld.controller;

import com.lxing.helloWorld.domain.Book;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
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
  @ApiOperation(value = "创建用户")
  @ApiImplicitParam(dataType = "User", name = "user", value = "用户信息", required = true)
  @ApiResponses({
      @ApiResponse(code = 500, message = "接口异常"),
  })
  public Book create(@Valid @RequestBody Book book, BindingResult errors) {
    book.setId("1");
    return book;
  }

  @DeleteMapping("/{id:\\d+}")
  @ApiOperation(value = "删除用户")
  public void delete(@PathVariable String id) {
    logger.info("删除用户：id{}", id);
  }


  @PutMapping("/{id:\\d+}")
  @ApiOperation(value = "更新用户")
  public Book update(@RequestBody Book book) {
    book.setId("1");
    return book;
  }

  @GetMapping("/{id:\\d+}")
  @ApiOperation(value = "获取用户")
  public Book getInfo(@PathVariable("id") String id) {
    Book book = new Book();
    book.setId(id);
    return book;
  }

}
