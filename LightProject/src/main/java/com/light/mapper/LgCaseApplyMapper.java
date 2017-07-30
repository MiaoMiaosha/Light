package com.light.mapper;

import com.light.pojo.LgCaseApply;
import com.light.pojo.LgCaseApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgCaseApplyMapper {
    int countByExample(LgCaseApplyExample example);

    int deleteByExample(LgCaseApplyExample example);

    int deleteByPrimaryKey(Integer caid);

    int insert(LgCaseApply record);

    int insertSelective(LgCaseApply record);

    List<LgCaseApply> selectByExample(LgCaseApplyExample example);

    LgCaseApply selectByPrimaryKey(Integer caid);

    int updateByExampleSelective(@Param("record") LgCaseApply record, @Param("example") LgCaseApplyExample example);

    int updateByExample(@Param("record") LgCaseApply record, @Param("example") LgCaseApplyExample example);

    int updateByPrimaryKeySelective(LgCaseApply record);

    int updateByPrimaryKey(LgCaseApply record);
}