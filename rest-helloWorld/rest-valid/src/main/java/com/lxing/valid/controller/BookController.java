package com.lxing.valid.controller;

import com.lxing.valid.domain.Book;
import com.lxing.valid.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody @Validated(Book.BookValidGroupA.class) Book book, BindingResult bindingResult) {
        int isSuccess = bookService.insert(book);
        if (isSuccess == 0) {
            return new ResponseEntity<>(book, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
        int isSuccess = bookService.deleteById(id);
        if (isSuccess == 0) {
            return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Book> update(@RequestBody @Validated(Book.BookValidGroupB.class) Book book, BindingResult bindingResult) {
        int isSuccess = bookService.updateById(book);
        if (isSuccess == 0) {
            return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Book> getInfo(@PathVariable("id") int id) {
        Book book = bookService.selectById(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
