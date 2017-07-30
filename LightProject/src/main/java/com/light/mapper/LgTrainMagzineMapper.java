package com.light.mapper;

import com.light.pojo.LgTrainMagzine;
import com.light.pojo.LgTrainMagzineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgTrainMagzineMapper {
    int countByExample(LgTrainMagzineExample example);

    int deleteByExample(LgTrainMagzineExample example);

    int deleteByPrimaryKey(Integer mid);

    int insert(LgTrainMagzine record);

    int insertSelective(LgTrainMagzine record);

    List<LgTrainMagzine> selectByExample(LgTrainMagzineExample example);

    LgTrainMagzine selectByPrimaryKey(Integer mid);

    int updateByExampleSelective(@Param("record") LgTrainMagzine record, @Param("example") LgTrainMagzineExample example);

    int updateByExample(@Param("record") LgTrainMagzine record, @Param("example") LgTrainMagzineExample example);

    int updateByPrimaryKeySelective(LgTrainMagzine record);

    int updateByPrimaryKey(LgTrainMagzine record);
}