package com.light.mapper;

import com.light.pojo.LgMainBusiness;
import com.light.pojo.LgMainBusinessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgMainBusinessMapper {
    int countByExample(LgMainBusinessExample example);

    int deleteByExample(LgMainBusinessExample example);

    int deleteByPrimaryKey(Integer mbid);

    int insert(LgMainBusiness record);

    int insertSelective(LgMainBusiness record);

    List<LgMainBusiness> selectByExample(LgMainBusinessExample example);

    LgMainBusiness selectByPrimaryKey(Integer mbid);

    int updateByExampleSelective(@Param("record") LgMainBusiness record, @Param("example") LgMainBusinessExample example);

    int updateByExample(@Param("record") LgMainBusiness record, @Param("example") LgMainBusinessExample example);

    int updateByPrimaryKeySelective(LgMainBusiness record);

    int updateByPrimaryKey(LgMainBusiness record);
}