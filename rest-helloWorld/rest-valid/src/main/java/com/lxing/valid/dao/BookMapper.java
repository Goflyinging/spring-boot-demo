package com.lxing.valid.dao;


import com.lxing.valid.domain.Book;

import org.apache.ibatis.annotations.Mapper;


/***
 * Created on 2017/10/10 <br>
 * Description: [DemoMapper
 * mybatis通过JDK的动态代理方式，在启动加载配置文件时，根据配置mapper的xml去生成]<br>
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
