package com.lxing.swagger.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * Created on 2017/11/3 <br>
 * Description: [Book bean]<br>
 * @author lxing
 * @version 1.0
 */
@ApiModel(value = "book", description = "书籍")
public class Book {
    /**
     * 编号
     **/
    @ApiModelProperty(value = "书籍编号")
    private String id;
    /**
     * 书名
     **/
    @ApiModelProperty(value = "书名", required = true)
    private String bookName;
    /**
     * 作者
     **/
    private String author;
    /**
     * 价格
     **/
    private int price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
