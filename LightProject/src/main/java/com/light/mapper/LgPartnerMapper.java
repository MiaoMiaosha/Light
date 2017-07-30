package com.light.mapper;

import com.light.pojo.LgPartner;
import com.light.pojo.LgPartnerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgPartnerMapper {
    int countByExample(LgPartnerExample example);

    int deleteByExample(LgPartnerExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(LgPartner record);

    int insertSelective(LgPartner record);

    List<LgPartner> selectByExample(LgPartnerExample example);

    LgPartner selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") LgPartner record, @Param("example") LgPartnerExample example);

    int updateByExample(@Param("record") LgPartner record, @Param("example") LgPartnerExample example);

    int updateByPrimaryKeySelective(LgPartner record);

    int updateByPrimaryKey(LgPartner record);
}