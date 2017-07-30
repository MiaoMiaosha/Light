package com.light.mapper;

import com.light.pojo.LgSalaryExtra;
import com.light.pojo.LgSalaryExtraExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgSalaryExtraMapper {
    int countByExample(LgSalaryExtraExample example);

    int deleteByExample(LgSalaryExtraExample example);

    int deleteByPrimaryKey(Integer tid);

    int insert(LgSalaryExtra record);

    int insertSelective(LgSalaryExtra record);

    List<LgSalaryExtra> selectByExample(LgSalaryExtraExample example);

    LgSalaryExtra selectByPrimaryKey(Integer tid);

    int updateByExampleSelective(@Param("record") LgSalaryExtra record, @Param("example") LgSalaryExtraExample example);

    int updateByExample(@Param("record") LgSalaryExtra record, @Param("example") LgSalaryExtraExample example);

    int updateByPrimaryKeySelective(LgSalaryExtra record);

    int updateByPrimaryKey(LgSalaryExtra record);
}