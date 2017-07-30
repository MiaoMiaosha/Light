package com.light.mapper;

import com.light.pojo.LgToken;
import com.light.pojo.LgTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgTokenMapper {
    int countByExample(LgTokenExample example);

    int deleteByExample(LgTokenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgToken record);

    int insertSelective(LgToken record);

    List<LgToken> selectByExample(LgTokenExample example);

    LgToken selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgToken record, @Param("example") LgTokenExample example);

    int updateByExample(@Param("record") LgToken record, @Param("example") LgTokenExample example);

    int updateByPrimaryKeySelective(LgToken record);

    int updateByPrimaryKey(LgToken record);
}