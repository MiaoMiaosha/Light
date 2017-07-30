package com.light.mapper;

import com.light.pojo.LgLoginCuslist;
import com.light.pojo.LgLoginCuslistExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgLoginCuslistMapper {
    int countByExample(LgLoginCuslistExample example);

    int deleteByExample(LgLoginCuslistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgLoginCuslist record);

    int insertSelective(LgLoginCuslist record);

    List<LgLoginCuslist> selectByExample(LgLoginCuslistExample example);

    LgLoginCuslist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgLoginCuslist record, @Param("example") LgLoginCuslistExample example);

    int updateByExample(@Param("record") LgLoginCuslist record, @Param("example") LgLoginCuslistExample example);

    int updateByPrimaryKeySelective(LgLoginCuslist record);

    int updateByPrimaryKey(LgLoginCuslist record);
}