package com.lxing.helloWorld.demo.dao;

import com.lxing.helloWorld.demo.domain.entity.Demo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/***
 * Created on 2017/10/10 <br>
 * Description: [DemoMapper]<br>
 * @author lxing
 * @version 1.0
 */
@Mapper
public interface DemoMapper {

  @Insert("insert into demo (name,number) values(#{demo.name},#{demo.number})")
  int insert(@Param("demo") Demo demo);

  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "name"),
      @Result(property = "number", column = "number")
  })
  @Select("SELECT * FROM demo WHERE number = #{number} LIMIT 1")
  Demo select(@Param("number") int number);
}
