package com.light.mapper;

import com.light.pojo.LgReimburseExtra;
import com.light.pojo.LgReimburseExtraExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgReimburseExtraMapper {
    int countByExample(LgReimburseExtraExample example);

    int deleteByExample(LgReimburseExtraExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgReimburseExtra record);

    int insertSelective(LgReimburseExtra record);

    List<LgReimburseExtra> selectByExample(LgReimburseExtraExample example);

    LgReimburseExtra selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgReimburseExtra record, @Param("example") LgReimburseExtraExample example);

    int updateByExample(@Param("record") LgReimburseExtra record, @Param("example") LgReimburseExtraExample example);

    int updateByPrimaryKeySelective(LgReimburseExtra record);

    int updateByPrimaryKey(LgReimburseExtra record);
}