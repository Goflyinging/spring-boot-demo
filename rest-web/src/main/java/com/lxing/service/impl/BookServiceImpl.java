package com.lxing.service.impl;


import com.lxing.dao.BookMapper;
import com.lxing.domain.Book;
import com.lxing.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * Created on 2017/11/8 <br>
 * Description: <br>
 * @author lxing
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public int insert(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    public Book selectById(int id) {
        return bookMapper.selectById(id);
    }

    @Override
    public int updateById(Book book) {
        return bookMapper.updateById(book);
    }

    @Override
    public int deleteById(int id) {
        return bookMapper.deleteById(id);
    }
}
