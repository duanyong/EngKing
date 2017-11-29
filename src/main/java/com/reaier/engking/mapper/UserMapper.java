package com.reaier.engking.mapper;

import com.reaier.engking.bean.User;
import java.util.List;

import org.apache.ibatis.annotations.*;
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


    ////////////////////////////////////////////////////////////////////////////////////////
    // 自定义DAO
    //

    //按页返回用户的列表
    @Select({
        "select",
        "id, username, `password`, is_login, `time`",
        "from user",
        "where `department_id`=#{departId,jdbcType=INTEGER} and `status`=0"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.CHAR),
        @Result(column="is_login", property="isLogin", jdbcType=JdbcType.BIT),
        @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> getListByPage(@Param("page")Integer page, @Param("size")Integer size);

    @Select({
            "select * from department_staff",
            "where `department_id`=#{departId,jdbcType=INTEGER} and `status`=0"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="department_id", property="departmentId", jdbcType=JdbcType.INTEGER),
            @Result(column="title_id", property="titleId", jdbcType=JdbcType.INTEGER),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="time", property="time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    List<User> listStaffByDepartmentId(@Param("departId")Integer departId);

    //
    // 自定义DAO
    ////////////////////////////////////////////////////////////////////////////////////////

}