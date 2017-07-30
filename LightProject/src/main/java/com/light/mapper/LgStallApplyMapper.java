package com.light.mapper;

import com.light.pojo.LgStallApply;
import com.light.pojo.LgStallApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgStallApplyMapper {
    int countByExample(LgStallApplyExample example);

    int deleteByExample(LgStallApplyExample example);

    int deleteByPrimaryKey(Integer said);

    int insert(LgStallApply record);

    int insertSelective(LgStallApply record);

    List<LgStallApply> selectByExample(LgStallApplyExample example);

    LgStallApply selectByPrimaryKey(Integer said);

    int updateByExampleSelective(@Param("record") LgStallApply record, @Param("example") LgStallApplyExample example);

    int updateByExample(@Param("record") LgStallApply record, @Param("example") LgStallApplyExample example);

    int updateByPrimaryKeySelective(LgStallApply record);

    int updateByPrimaryKey(LgStallApply record);
}