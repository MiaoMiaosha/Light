package com.light.mapper;

import com.light.pojo.LgBonusExtra;
import com.light.pojo.LgBonusExtraExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgBonusExtraMapper {
    int countByExample(LgBonusExtraExample example);

    int deleteByExample(LgBonusExtraExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgBonusExtra record);

    int insertSelective(LgBonusExtra record);

    List<LgBonusExtra> selectByExample(LgBonusExtraExample example);

    LgBonusExtra selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgBonusExtra record, @Param("example") LgBonusExtraExample example);

    int updateByExample(@Param("record") LgBonusExtra record, @Param("example") LgBonusExtraExample example);

    int updateByPrimaryKeySelective(LgBonusExtra record);

    int updateByPrimaryKey(LgBonusExtra record);
}