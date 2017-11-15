package com.lxing.demo.controller;

import com.lxing.demo.domain.Book;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;

/***
 * Created on 2017/11/3 <br>
 * Description: <br>
 * @author lxing
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
        book.setId(22);
        ResponseEntity<String> entity = restTemplate.postForEntity("http://127.0.0.1:8060/books", book, String.class);
        System.out.println(entity.getStatusCode());
        System.out.println(entity.hasBody());
        System.out.println(entity.getBody());
        System.out.println(entity.toString());

    }

    @Test
    public void annotationTest() {
//        @Documented –注解是否将包含在JavaDoc中
//        @Retention –什么时候使用该注解
//        @Target? –注解用于什么地方
//        @Inherited – 是否允许被该注解注解的父类的子类继承该注解
        Class bookControllerClass = BookController.class;
        System.out.println("-------->1"+bookControllerClass.getAnnotation(ResponseBody.class));
        System.out.println("-------->2"+bookControllerClass.getAnnotationsByType(ResponseBody.class));
        Annotation[] annotations = bookControllerClass.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("-------->3"+annotation);
            if (annotation instanceof RestController) {
                RestController restController = (RestController) annotation;
                System.out.println(restController.value());

            }
            if (annotation instanceof RequestMapping) {
                RequestMapping requestMapping = (RequestMapping) annotation;

                String[] value = requestMapping.value();
                for (String string : value) {
                    System.out.println(string);
                }
            }
        }
//        Method[] methods = bookControllerClass.getMethods();
//        for (Method method : methods) {
//            if (method.isAnnotationPresent(GetMapping.class)) {
//                GetMapping getMapping = method.getAnnotation(GetMapping.class);
//                String[] value = getMapping.value();
//                for (String string : value) {
//                    System.out.println(string);
//                }
//                if (GetMapping.class.isAnnotationPresent(RequestMapping.class)) {
//                    RequestMapping requestMapping = GetMapping.class.getAnnotation(RequestMapping.class);
//                    RequestMethod[] requestMethods = requestMapping.method();
//                    for (RequestMethod requestMethod : requestMethods) {
//                        System.out.println(requestMethod.name());
//                    }
//                }
//
//            }
//        }


    }
}
