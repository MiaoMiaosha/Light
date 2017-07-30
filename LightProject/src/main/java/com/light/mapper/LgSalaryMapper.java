package com.light.mapper;

import com.light.pojo.LgSalary;
import com.light.pojo.LgSalaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgSalaryMapper {
    int countByExample(LgSalaryExample example);

    int deleteByExample(LgSalaryExample example);

    int deleteByPrimaryKey(Integer sid);

    int insert(LgSalary record);

    int insertSelective(LgSalary record);

    List<LgSalary> selectByExample(LgSalaryExample example);

    LgSalary selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") LgSalary record, @Param("example") LgSalaryExample example);

    int updateByExample(@Param("record") LgSalary record, @Param("example") LgSalaryExample example);

    int updateByPrimaryKeySelective(LgSalary record);

    int updateByPrimaryKey(LgSalary record);
}