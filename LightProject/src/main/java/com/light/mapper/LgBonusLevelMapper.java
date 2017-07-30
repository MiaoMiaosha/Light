package com.light.mapper;

import com.light.pojo.LgBonusLevel;
import com.light.pojo.LgBonusLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgBonusLevelMapper {
    int countByExample(LgBonusLevelExample example);

    int deleteByExample(LgBonusLevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgBonusLevel record);

    int insertSelective(LgBonusLevel record);

    List<LgBonusLevel> selectByExample(LgBonusLevelExample example);

    LgBonusLevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgBonusLevel record, @Param("example") LgBonusLevelExample example);

    int updateByExample(@Param("record") LgBonusLevel record, @Param("example") LgBonusLevelExample example);

    int updateByPrimaryKeySelective(LgBonusLevel record);

    int updateByPrimaryKey(LgBonusLevel record);
}