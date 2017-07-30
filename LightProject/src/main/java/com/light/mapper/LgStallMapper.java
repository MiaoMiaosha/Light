package com.light.mapper;

import com.light.pojo.LgStall;
import com.light.pojo.LgStallExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgStallMapper {
    int countByExample(LgStallExample example);

    int deleteByExample(LgStallExample example);

    int deleteByPrimaryKey(Integer sid);

    int insert(LgStall record);

    int insertSelective(LgStall record);

    List<LgStall> selectByExample(LgStallExample example);

    LgStall selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") LgStall record, @Param("example") LgStallExample example);

    int updateByExample(@Param("record") LgStall record, @Param("example") LgStallExample example);

    int updateByPrimaryKeySelective(LgStall record);

    int updateByPrimaryKey(LgStall record);
}