package com.lxing.demo.controller;


import com.lxing.demo.domain.Book;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  Map<String, Book> bookRepository = new ConcurrentHashMap<>();
  public static AtomicInteger atomicInteger = new AtomicInteger(0);


  @PostMapping
  public ResponseEntity<Book> create(@RequestBody Book book) {
    //生成id
    String id = String.valueOf(atomicInteger.incrementAndGet());
    book.setId(id);
    bookRepository.put(id, book);
    //返回处理结果
    return new ResponseEntity<>(book, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id:\\d+}")
  public ResponseEntity<Book> delete(@PathVariable("id") String id) {
    Book book = bookRepository.remove(id);
    if (book == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);//请求收到但返回结果为空
  }


  @PutMapping("/{id:\\d+}")
  public ResponseEntity<Book> update(@RequestBody Book book) {
    Book originalBook = bookRepository.get(book.getId());
    if (originalBook == null) {
      return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
    }
    originalBook.setAuthor(book.getAuthor());
    originalBook.setBookName(book.getBookName());
    originalBook.setPrice(book.getPrice());
    bookRepository.put(originalBook.getId(), originalBook);
    return new ResponseEntity<>(originalBook, HttpStatus.OK);
  }

  @GetMapping("/{id:\\d+}")
  public ResponseEntity<Book> getInfo(@PathVariable("id") String id) {
    Book book = bookRepository.get(id);
    if (book == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(book, HttpStatus.OK);
  }

}
