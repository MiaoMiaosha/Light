package com.light.mapper;

import com.light.pojo.LgUser;
import com.light.pojo.LgUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgUserMapper {
    int countByExample(LgUserExample example);

    int deleteByExample(LgUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(LgUser record);

    int insertSelective(LgUser record);

    List<LgUser> selectByExampleWithBLOBs(LgUserExample example);

    List<LgUser> selectByExample(LgUserExample example);

    LgUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") LgUser record, @Param("example") LgUserExample example);

    int updateByExampleWithBLOBs(@Param("record") LgUser record, @Param("example") LgUserExample example);

    int updateByExample(@Param("record") LgUser record, @Param("example") LgUserExample example);

    int updateByPrimaryKeySelective(LgUser record);

    int updateByPrimaryKeyWithBLOBs(LgUser record);

    int updateByPrimaryKey(LgUser record);
}