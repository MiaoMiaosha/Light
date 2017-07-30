package com.light.mapper;

import com.light.pojo.LgTrainSoftware;
import com.light.pojo.LgTrainSoftwareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgTrainSoftwareMapper {
    int countByExample(LgTrainSoftwareExample example);

    int deleteByExample(LgTrainSoftwareExample example);

    int deleteByPrimaryKey(Integer sid);

    int insert(LgTrainSoftware record);

    int insertSelective(LgTrainSoftware record);

    List<LgTrainSoftware> selectByExample(LgTrainSoftwareExample example);

    LgTrainSoftware selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") LgTrainSoftware record, @Param("example") LgTrainSoftwareExample example);

    int updateByExample(@Param("record") LgTrainSoftware record, @Param("example") LgTrainSoftwareExample example);

    int updateByPrimaryKeySelective(LgTrainSoftware record);

    int updateByPrimaryKey(LgTrainSoftware record);
}