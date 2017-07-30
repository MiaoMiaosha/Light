package com.light.mapper;

import com.light.pojo.LgAccounting;
import com.light.pojo.LgAccountingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgAccountingMapper {
    int countByExample(LgAccountingExample example);

    int deleteByExample(LgAccountingExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(LgAccounting record);

    int insertSelective(LgAccounting record);

    List<LgAccounting> selectByExample(LgAccountingExample example);

    LgAccounting selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") LgAccounting record, @Param("example") LgAccountingExample example);

    int updateByExample(@Param("record") LgAccounting record, @Param("example") LgAccountingExample example);

    int updateByPrimaryKeySelective(LgAccounting record);

    int updateByPrimaryKey(LgAccounting record);
}