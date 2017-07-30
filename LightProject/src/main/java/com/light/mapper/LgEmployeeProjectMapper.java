package com.light.mapper;

import com.light.pojo.LgEmployeeProject;
import com.light.pojo.LgEmployeeProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgEmployeeProjectMapper {
    int countByExample(LgEmployeeProjectExample example);

    int deleteByExample(LgEmployeeProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgEmployeeProject record);

    int insertSelective(LgEmployeeProject record);

    List<LgEmployeeProject> selectByExample(LgEmployeeProjectExample example);

    LgEmployeeProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgEmployeeProject record, @Param("example") LgEmployeeProjectExample example);

    int updateByExample(@Param("record") LgEmployeeProject record, @Param("example") LgEmployeeProjectExample example);

    int updateByPrimaryKeySelective(LgEmployeeProject record);

    int updateByPrimaryKey(LgEmployeeProject record);
}