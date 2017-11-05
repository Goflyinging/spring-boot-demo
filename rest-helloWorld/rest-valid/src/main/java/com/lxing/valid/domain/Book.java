package com.lxing.valid.domain;


import org.hibernate.validator.constraints.NotEmpty;

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
    @NotEmpty(message = "id不能为空", groups = BookValidGroupB.class)
    private String id;
    /**
     * 书名
     **/
    @NotEmpty(message = "书名不能为空", groups = BookValidGroupA.class)
    private String bookName;
    /**
     * 作者
     **/
    //@Forbidden(words = {"lu", "xing"}, message = "作者不能包含特殊字符", groups = BookValidGroupA.class)
//    @Forbidden.List({
//            @Forbidden(),
//            @Forbidden()
//    })
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

    /**
     * 校验分组
     */
    public interface BookValidGroupA {
        //新增校验
    }

    public interface BookValidGroupB extends BookValidGroupA {
        //更新校验
    }

}
