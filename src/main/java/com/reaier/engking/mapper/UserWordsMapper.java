package com.reaier.engking.mapper;

import com.reaier.engking.bean.UserWords;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserWordsMapper {
    @Delete({
        "delete from user_words",
        "where user_word_id = #{userWordId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userWordId);

    @Insert({
        "insert into user_words (user_word_id, source_id, ",
        "user_id, word_id, ",
        "translation_id, insert_time)",
        "values (#{userWordId,jdbcType=INTEGER}, #{sourceId,jdbcType=INTEGER}, ",
        "#{userId,jdbcType=INTEGER}, #{wordId,jdbcType=INTEGER}, ",
        "#{translationId,jdbcType=INTEGER}, #{insertTime,jdbcType=TIMESTAMP})"
    })
    int insert(UserWords record);

    @Select({
        "select",
        "user_word_id, source_id, user_id, word_id, translation_id, insert_time",
        "from user_words",
        "where user_word_id = #{userWordId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_word_id", property="userWordId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="source_id", property="sourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="word_id", property="wordId", jdbcType=JdbcType.INTEGER),
        @Result(column="translation_id", property="translationId", jdbcType=JdbcType.INTEGER),
        @Result(column="insert_time", property="insertTime", jdbcType=JdbcType.TIMESTAMP)
    })
    UserWords selectByPrimaryKey(Integer userWordId);

    @Select({
        "select",
        "user_word_id, source_id, user_id, word_id, translation_id, insert_time",
        "from user_words"
    })
    @Results({
        @Result(column="user_word_id", property="userWordId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="source_id", property="sourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="word_id", property="wordId", jdbcType=JdbcType.INTEGER),
        @Result(column="translation_id", property="translationId", jdbcType=JdbcType.INTEGER),
        @Result(column="insert_time", property="insertTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UserWords> selectAll();

    @Update({
        "update user_words",
        "set source_id = #{sourceId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "word_id = #{wordId,jdbcType=INTEGER},",
          "translation_id = #{translationId,jdbcType=INTEGER},",
          "insert_time = #{insertTime,jdbcType=TIMESTAMP}",
        "where user_word_id = #{userWordId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserWords record);
}