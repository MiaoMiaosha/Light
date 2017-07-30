package com.light.mapper;

import com.light.pojo.LgFloor;
import com.light.pojo.LgFloorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgFloorMapper {
    int countByExample(LgFloorExample example);

    int deleteByExample(LgFloorExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(LgFloor record);

    int insertSelective(LgFloor record);

    List<LgFloor> selectByExample(LgFloorExample example);

    LgFloor selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") LgFloor record, @Param("example") LgFloorExample example);

    int updateByExample(@Param("record") LgFloor record, @Param("example") LgFloorExample example);

    int updateByPrimaryKeySelective(LgFloor record);

    int updateByPrimaryKey(LgFloor record);
}