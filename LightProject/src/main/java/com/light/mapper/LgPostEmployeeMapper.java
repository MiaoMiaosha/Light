package com.light.mapper;

import com.light.pojo.LgPostEmployee;
import com.light.pojo.LgPostEmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgPostEmployeeMapper {
    int countByExample(LgPostEmployeeExample example);

    int deleteByExample(LgPostEmployeeExample example);

    int deleteByPrimaryKey(Integer peid);

    int insert(LgPostEmployee record);

    int insertSelective(LgPostEmployee record);

    List<LgPostEmployee> selectByExample(LgPostEmployeeExample example);

    LgPostEmployee selectByPrimaryKey(Integer peid);

    int updateByExampleSelective(@Param("record") LgPostEmployee record, @Param("example") LgPostEmployeeExample example);

    int updateByExample(@Param("record") LgPostEmployee record, @Param("example") LgPostEmployeeExample example);

    int updateByPrimaryKeySelective(LgPostEmployee record);

    int updateByPrimaryKey(LgPostEmployee record);
}