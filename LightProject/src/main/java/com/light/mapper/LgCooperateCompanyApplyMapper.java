package com.light.mapper;

import com.light.pojo.LgCooperateCompanyApply;
import com.light.pojo.LgCooperateCompanyApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgCooperateCompanyApplyMapper {
    int countByExample(LgCooperateCompanyApplyExample example);

    int deleteByExample(LgCooperateCompanyApplyExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(LgCooperateCompanyApply record);

    int insertSelective(LgCooperateCompanyApply record);

    List<LgCooperateCompanyApply> selectByExample(LgCooperateCompanyApplyExample example);

    LgCooperateCompanyApply selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") LgCooperateCompanyApply record, @Param("example") LgCooperateCompanyApplyExample example);

    int updateByExample(@Param("record") LgCooperateCompanyApply record, @Param("example") LgCooperateCompanyApplyExample example);

    int updateByPrimaryKeySelective(LgCooperateCompanyApply record);

    int updateByPrimaryKey(LgCooperateCompanyApply record);
}