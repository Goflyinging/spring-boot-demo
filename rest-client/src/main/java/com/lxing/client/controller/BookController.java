package com.lxing.client.controller;


import com.lxing.client.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
  RestTemplate restTemplate;

  private static final Logger logger = LoggerFactory.getLogger(BookController.class);


  @GetMapping("/{id:\\d+}")
  public Book getInfo(@PathVariable("id") String id) {
    ResponseEntity<Book> entity = restTemplate
        .getForEntity("http://192.168.39.1:8060/books/{id}", Book.class, id);
    Book body = entity.getBody();
    return body;
  }

  @GetMapping("/2/{id:\\d+}")
  public Book getInfo2(@PathVariable("id") String id) {
    ResponseEntity<Book> entity = restTemplate
        .getForEntity("http://192.168.39.1:8062/books/{id}", Book.class, id);
    Book body = entity.getBody();
    return body;
  }

  @GetMapping("/baidu")
  public String getbaidu() {
    ResponseEntity<String> entity = restTemplate
        .getForEntity("http://www.baidu.com", String.class);
    String body = entity.getBody();
    return body;
  }

}
