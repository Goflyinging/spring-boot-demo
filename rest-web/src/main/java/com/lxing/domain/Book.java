package com.lxing.domain;


/***
 * Created on 2017/11/3 <br>
 * Description: [Book bean]<br>
 * @author lxing
 * @version 1.0
 */
public class Book {
    /**
     * 编号
     **/
    private int id;
    /**
     * 书名
     **/
    private String bookName;
    /**
     * 作者
     **/
    private String author;
    /**
     * 价格
     **/
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


}
