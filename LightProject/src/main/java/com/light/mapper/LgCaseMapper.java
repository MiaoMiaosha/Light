package com.light.mapper;

import com.light.pojo.LgCase;
import com.light.pojo.LgCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgCaseMapper {
    int countByExample(LgCaseExample example);

    int deleteByExample(LgCaseExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(LgCase record);

    int insertSelective(LgCase record);

    List<LgCase> selectByExample(LgCaseExample example);

    LgCase selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") LgCase record, @Param("example") LgCaseExample example);

    int updateByExample(@Param("record") LgCase record, @Param("example") LgCaseExample example);

    int updateByPrimaryKeySelective(LgCase record);

    int updateByPrimaryKey(LgCase record);
}