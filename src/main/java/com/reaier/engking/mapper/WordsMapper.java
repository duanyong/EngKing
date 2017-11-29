package com.reaier.engking.mapper;

import com.reaier.engking.bean.Words;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface WordsMapper {
    @Delete({
        "delete from words",
        "where word_id = #{wordId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer wordId);

    @Insert({
        "insert into words (word_id, word, ",
        "grade, complete_status)",
        "values (#{wordId,jdbcType=INTEGER}, #{word,jdbcType=VARCHAR}, ",
        "#{grade,jdbcType=TINYINT}, #{completeStatus,jdbcType=BIT})"
    })
    int insert(Words record);

    @Select({
        "select",
        "word_id, word, grade, complete_status",
        "from words",
        "where word_id = #{wordId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="word_id", property="wordId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="word", property="word", jdbcType=JdbcType.VARCHAR),
        @Result(column="grade", property="grade", jdbcType=JdbcType.TINYINT),
        @Result(column="complete_status", property="completeStatus", jdbcType=JdbcType.BIT)
    })
    Words selectByPrimaryKey(Integer wordId);

    @Select({
        "select",
        "word_id, word, grade, complete_status",
        "from words"
    })
    @Results({
        @Result(column="word_id", property="wordId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="word", property="word", jdbcType=JdbcType.VARCHAR),
        @Result(column="grade", property="grade", jdbcType=JdbcType.TINYINT),
        @Result(column="complete_status", property="completeStatus", jdbcType=JdbcType.BIT)
    })
    List<Words> selectAll();

    @Update({
        "update words",
        "set word = #{word,jdbcType=VARCHAR},",
          "grade = #{grade,jdbcType=TINYINT},",
          "complete_status = #{completeStatus,jdbcType=BIT}",
        "where word_id = #{wordId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Words record);
}