package com.light.mapper;

import com.light.pojo.LgPost;
import com.light.pojo.LgPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgPostMapper {
    int countByExample(LgPostExample example);

    int deleteByExample(LgPostExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(LgPost record);

    int insertSelective(LgPost record);

    List<LgPost> selectByExample(LgPostExample example);

    LgPost selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") LgPost record, @Param("example") LgPostExample example);

    int updateByExample(@Param("record") LgPost record, @Param("example") LgPostExample example);

    int updateByPrimaryKeySelective(LgPost record);

    int updateByPrimaryKey(LgPost record);
}