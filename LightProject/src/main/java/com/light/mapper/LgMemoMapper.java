package com.light.mapper;

import com.light.pojo.LgMemo;
import com.light.pojo.LgMemoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgMemoMapper {
    int countByExample(LgMemoExample example);

    int deleteByExample(LgMemoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgMemo record);

    int insertSelective(LgMemo record);

    List<LgMemo> selectByExample(LgMemoExample example);

    LgMemo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgMemo record, @Param("example") LgMemoExample example);

    int updateByExample(@Param("record") LgMemo record, @Param("example") LgMemoExample example);

    int updateByPrimaryKeySelective(LgMemo record);

    int updateByPrimaryKey(LgMemo record);
}