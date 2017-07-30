package com.light.mapper;

import com.light.pojo.LgBonus;
import com.light.pojo.LgBonusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgBonusMapper {
    int countByExample(LgBonusExample example);

    int deleteByExample(LgBonusExample example);

    int deleteByPrimaryKey(Integer bid);

    int insert(LgBonus record);

    int insertSelective(LgBonus record);

    List<LgBonus> selectByExample(LgBonusExample example);

    LgBonus selectByPrimaryKey(Integer bid);

    int updateByExampleSelective(@Param("record") LgBonus record, @Param("example") LgBonusExample example);

    int updateByExample(@Param("record") LgBonus record, @Param("example") LgBonusExample example);

    int updateByPrimaryKeySelective(LgBonus record);

    int updateByPrimaryKey(LgBonus record);
}