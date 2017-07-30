package com.light.mapper;

import com.light.pojo.LgNews;
import com.light.pojo.LgNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgNewsMapper {
    int countByExample(LgNewsExample example);

    int deleteByExample(LgNewsExample example);

    int deleteByPrimaryKey(Integer nid);

    int insert(LgNews record);

    int insertSelective(LgNews record);

    List<LgNews> selectByExample(LgNewsExample example);

    LgNews selectByPrimaryKey(Integer nid);

    int updateByExampleSelective(@Param("record") LgNews record, @Param("example") LgNewsExample example);

    int updateByExample(@Param("record") LgNews record, @Param("example") LgNewsExample example);

    int updateByPrimaryKeySelective(LgNews record);

    int updateByPrimaryKey(LgNews record);
}