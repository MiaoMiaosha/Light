package com.light.mapper;

import com.light.pojo.LgTrainSignupApply;
import com.light.pojo.LgTrainSignupApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgTrainSignupApplyMapper {
    int countByExample(LgTrainSignupApplyExample example);

    int deleteByExample(LgTrainSignupApplyExample example);

    int deleteByPrimaryKey(Integer suid);

    int insert(LgTrainSignupApply record);

    int insertSelective(LgTrainSignupApply record);

    List<LgTrainSignupApply> selectByExample(LgTrainSignupApplyExample example);

    LgTrainSignupApply selectByPrimaryKey(Integer suid);

    int updateByExampleSelective(@Param("record") LgTrainSignupApply record, @Param("example") LgTrainSignupApplyExample example);

    int updateByExample(@Param("record") LgTrainSignupApply record, @Param("example") LgTrainSignupApplyExample example);

    int updateByPrimaryKeySelective(LgTrainSignupApply record);

    int updateByPrimaryKey(LgTrainSignupApply record);
}