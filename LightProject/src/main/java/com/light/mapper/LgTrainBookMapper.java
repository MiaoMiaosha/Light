package com.light.mapper;

import com.light.pojo.LgTrainBook;
import com.light.pojo.LgTrainBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgTrainBookMapper {
    int countByExample(LgTrainBookExample example);

    int deleteByExample(LgTrainBookExample example);

    int deleteByPrimaryKey(Integer bid);

    int insert(LgTrainBook record);

    int insertSelective(LgTrainBook record);

    List<LgTrainBook> selectByExample(LgTrainBookExample example);

    LgTrainBook selectByPrimaryKey(Integer bid);

    int updateByExampleSelective(@Param("record") LgTrainBook record, @Param("example") LgTrainBookExample example);

    int updateByExample(@Param("record") LgTrainBook record, @Param("example") LgTrainBookExample example);

    int updateByPrimaryKeySelective(LgTrainBook record);

    int updateByPrimaryKey(LgTrainBook record);
}