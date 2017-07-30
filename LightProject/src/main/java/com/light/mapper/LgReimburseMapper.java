package com.light.mapper;

import com.light.pojo.LgReimburse;
import com.light.pojo.LgReimburseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgReimburseMapper {
    int countByExample(LgReimburseExample example);

    int deleteByExample(LgReimburseExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(LgReimburse record);

    int insertSelective(LgReimburse record);

    List<LgReimburse> selectByExample(LgReimburseExample example);

    LgReimburse selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") LgReimburse record, @Param("example") LgReimburseExample example);

    int updateByExample(@Param("record") LgReimburse record, @Param("example") LgReimburseExample example);

    int updateByPrimaryKeySelective(LgReimburse record);

    int updateByPrimaryKey(LgReimburse record);
}