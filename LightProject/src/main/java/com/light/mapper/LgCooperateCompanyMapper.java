package com.light.mapper;

import com.light.pojo.LgCooperateCompany;
import com.light.pojo.LgCooperateCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgCooperateCompanyMapper {
    int countByExample(LgCooperateCompanyExample example);

    int deleteByExample(LgCooperateCompanyExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(LgCooperateCompany record);

    int insertSelective(LgCooperateCompany record);

    List<LgCooperateCompany> selectByExample(LgCooperateCompanyExample example);

    LgCooperateCompany selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") LgCooperateCompany record, @Param("example") LgCooperateCompanyExample example);

    int updateByExample(@Param("record") LgCooperateCompany record, @Param("example") LgCooperateCompanyExample example);

    int updateByPrimaryKeySelective(LgCooperateCompany record);

    int updateByPrimaryKey(LgCooperateCompany record);
}