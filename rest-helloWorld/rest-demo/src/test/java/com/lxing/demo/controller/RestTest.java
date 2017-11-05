package com.lxing.demo.controller;

import com.lxing.demo.domain.Book;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/***
 * Created on 2017/11/3 <br>
 * Description: <br>
 * @author 01369533
 */
public class RestTest {
    @Test
    public void whenGetBookSuccess() {
        RestTemplate restTemplate = new RestTemplate();
        String id = "1";
        ResponseEntity<Book> entity = restTemplate.getForEntity("http://127.0.0.1:8060/books/{id}", Book.class, id);
        System.out.println(entity.getStatusCode());
    }

    @Test
    public void whenPostBookSuccess() {
        RestTemplate restTemplate = new RestTemplate();
        Book book = new Book();
        book.setId("22");

        ResponseEntity<String> entity = restTemplate.postForEntity("http://127.0.0.1:8060/books", book, String.class);
        System.out.println(entity.getStatusCode());
        System.out.println(entity.hasBody());
        System.out.println(entity.getBody());
        System.out.println(entity.toString());

    }
}
