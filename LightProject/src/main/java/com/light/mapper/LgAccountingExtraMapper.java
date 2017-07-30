package com.light.mapper;

import com.light.pojo.LgAccountingExtra;
import com.light.pojo.LgAccountingExtraExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgAccountingExtraMapper {
    int countByExample(LgAccountingExtraExample example);

    int deleteByExample(LgAccountingExtraExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgAccountingExtra record);

    int insertSelective(LgAccountingExtra record);

    List<LgAccountingExtra> selectByExample(LgAccountingExtraExample example);

    LgAccountingExtra selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgAccountingExtra record, @Param("example") LgAccountingExtraExample example);

    int updateByExample(@Param("record") LgAccountingExtra record, @Param("example") LgAccountingExtraExample example);

    int updateByPrimaryKeySelective(LgAccountingExtra record);

    int updateByPrimaryKey(LgAccountingExtra record);
}