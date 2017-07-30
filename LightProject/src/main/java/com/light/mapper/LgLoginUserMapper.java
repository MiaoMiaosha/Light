package com.light.mapper;

import com.light.pojo.LgLoginUser;
import com.light.pojo.LgLoginUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgLoginUserMapper {
    int countByExample(LgLoginUserExample example);

    int deleteByExample(LgLoginUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgLoginUser record);

    int insertSelective(LgLoginUser record);

    List<LgLoginUser> selectByExample(LgLoginUserExample example);

    LgLoginUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgLoginUser record, @Param("example") LgLoginUserExample example);

    int updateByExample(@Param("record") LgLoginUser record, @Param("example") LgLoginUserExample example);

    int updateByPrimaryKeySelective(LgLoginUser record);

    int updateByPrimaryKey(LgLoginUser record);
}