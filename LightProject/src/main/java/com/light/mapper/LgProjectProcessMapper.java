package com.light.mapper;

import com.light.pojo.LgProjectProcess;
import com.light.pojo.LgProjectProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgProjectProcessMapper {
    int countByExample(LgProjectProcessExample example);

    int deleteByExample(LgProjectProcessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgProjectProcess record);

    int insertSelective(LgProjectProcess record);

    List<LgProjectProcess> selectByExample(LgProjectProcessExample example);

    LgProjectProcess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgProjectProcess record, @Param("example") LgProjectProcessExample example);

    int updateByExample(@Param("record") LgProjectProcess record, @Param("example") LgProjectProcessExample example);

    int updateByPrimaryKeySelective(LgProjectProcess record);

    int updateByPrimaryKey(LgProjectProcess record);
}