package com.light.mapper;

import com.light.pojo.LgWxUser;
import com.light.pojo.LgWxUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgWxUserMapper {
    int countByExample(LgWxUserExample example);

    int deleteByExample(LgWxUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgWxUser record);

    int insertSelective(LgWxUser record);

    List<LgWxUser> selectByExample(LgWxUserExample example);

    LgWxUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgWxUser record, @Param("example") LgWxUserExample example);

    int updateByExample(@Param("record") LgWxUser record, @Param("example") LgWxUserExample example);

    int updateByPrimaryKeySelective(LgWxUser record);

    int updateByPrimaryKey(LgWxUser record);
}