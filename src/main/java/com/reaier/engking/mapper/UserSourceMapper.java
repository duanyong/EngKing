package com.reaier.engking.mapper;

import com.reaier.engking.bean.UserSource;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserSourceMapper {
    @Delete({
        "delete from user_source",
        "where source_id = #{sourceId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer sourceId);

    @Insert({
        "insert into user_source (source_id, user_id, ",
        "insert_time, insert_text, ",
        "insert_type)",
        "values (#{sourceId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{insertTime,jdbcType=TIMESTAMP}, #{insertText,jdbcType=VARCHAR}, ",
        "#{insertType,jdbcType=BIT})"
    })
    int insert(UserSource record);

    @Select({
        "select",
        "source_id, user_id, insert_time, insert_text, insert_type",
        "from user_source",
        "where source_id = #{sourceId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="source_id", property="sourceId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="insert_time", property="insertTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="insert_text", property="insertText", jdbcType=JdbcType.VARCHAR),
        @Result(column="insert_type", property="insertType", jdbcType=JdbcType.BIT)
    })
    UserSource selectByPrimaryKey(Integer sourceId);

    @Select({
        "select",
        "source_id, user_id, insert_time, insert_text, insert_type",
        "from user_source"
    })
    @Results({
        @Result(column="source_id", property="sourceId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="insert_time", property="insertTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="insert_text", property="insertText", jdbcType=JdbcType.VARCHAR),
        @Result(column="insert_type", property="insertType", jdbcType=JdbcType.BIT)
    })
    List<UserSource> selectAll();

    @Update({
        "update user_source",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "insert_time = #{insertTime,jdbcType=TIMESTAMP},",
          "insert_text = #{insertText,jdbcType=VARCHAR},",
          "insert_type = #{insertType,jdbcType=BIT}",
        "where source_id = #{sourceId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserSource record);
}