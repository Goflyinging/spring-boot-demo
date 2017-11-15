package com.lxing.demo.service;

import com.lxing.demo.domain.Book;


/***
 * Created on 2017/11/8 <br>
 * Description: <br>
 * @author lxing
 */
public interface BookService {
    int insert(Book book);

    Book selectById(int id);

    int updateById(Book book);

    int deleteById(int id);
}
