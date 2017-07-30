package com.light.mapper;

import com.light.pojo.LgProject;
import com.light.pojo.LgProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgProjectMapper {
    int countByExample(LgProjectExample example);

    int deleteByExample(LgProjectExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(LgProject record);

    int insertSelective(LgProject record);

    List<LgProject> selectByExample(LgProjectExample example);

    LgProject selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") LgProject record, @Param("example") LgProjectExample example);

    int updateByExample(@Param("record") LgProject record, @Param("example") LgProjectExample example);

    int updateByPrimaryKeySelective(LgProject record);

    int updateByPrimaryKey(LgProject record);
}