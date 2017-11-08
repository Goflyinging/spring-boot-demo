package com.lxing.demo.dao;

import com.lxing.demo.domain.Book;

import org.apache.ibatis.annotations.Mapper;


/***
 * Created on 2017/10/10 <br>
 * Description: [DemoMapper]<br>
 * @author lxing
 * @version 1.0
 */
@Mapper
public interface BookMapper {

    int insert(Book book);

    Book selectById(int id);

    int updateById(Book book);

    int deleteById(int id);
}
