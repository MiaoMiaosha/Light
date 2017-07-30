package com.light.mapper;

import com.light.pojo.LgConfig;
import com.light.pojo.LgConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgConfigMapper {
    int countByExample(LgConfigExample example);

    int deleteByExample(LgConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgConfig record);

    int insertSelective(LgConfig record);

    List<LgConfig> selectByExample(LgConfigExample example);

    LgConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgConfig record, @Param("example") LgConfigExample example);

    int updateByExample(@Param("record") LgConfig record, @Param("example") LgConfigExample example);

    int updateByPrimaryKeySelective(LgConfig record);

    int updateByPrimaryKey(LgConfig record);
}