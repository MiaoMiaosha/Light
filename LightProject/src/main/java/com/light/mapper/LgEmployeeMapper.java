package com.light.mapper;

import com.light.pojo.LgEmployee;
import com.light.pojo.LgEmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgEmployeeMapper {
    int countByExample(LgEmployeeExample example);

    int deleteByExample(LgEmployeeExample example);

    int deleteByPrimaryKey(Integer eid);

    int insert(LgEmployee record);

    int insertSelective(LgEmployee record);

    List<LgEmployee> selectByExample(LgEmployeeExample example);

    LgEmployee selectByPrimaryKey(Integer eid);

    int updateByExampleSelective(@Param("record") LgEmployee record, @Param("example") LgEmployeeExample example);

    int updateByExample(@Param("record") LgEmployee record, @Param("example") LgEmployeeExample example);

    int updateByPrimaryKeySelective(LgEmployee record);

    int updateByPrimaryKey(LgEmployee record);
}