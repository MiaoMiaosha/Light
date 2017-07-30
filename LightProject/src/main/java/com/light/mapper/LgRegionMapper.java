package com.light.mapper;

import com.light.pojo.LgRegion;
import com.light.pojo.LgRegionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgRegionMapper {
    int countByExample(LgRegionExample example);

    int deleteByExample(LgRegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgRegion record);

    int insertSelective(LgRegion record);

    List<LgRegion> selectByExample(LgRegionExample example);

    LgRegion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgRegion record, @Param("example") LgRegionExample example);

    int updateByExample(@Param("record") LgRegion record, @Param("example") LgRegionExample example);

    int updateByPrimaryKeySelective(LgRegion record);

    int updateByPrimaryKey(LgRegion record);
}