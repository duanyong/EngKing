package com.reaier.engking.mapper;

import com.reaier.engking.bean.User;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Insert({
        "insert into user (username, `password`, ",
        "is_login, `time`)",
        "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=CHAR}, ",
        "#{isLogin,jdbcType=BIT}, #{time,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(User record);

    @Select({
        "select",
        "id, username, `password`, is_login, `time`",
        "from user"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.CHAR),
        @Result(column="is_login", property="isLogin", jdbcType=JdbcType.BIT),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> selectAll();
}