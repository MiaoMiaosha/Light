package com.light.mapper;

import com.light.pojo.LgMarketApply;
import com.light.pojo.LgMarketApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgMarketApplyMapper {
    int countByExample(LgMarketApplyExample example);

    int deleteByExample(LgMarketApplyExample example);

    int deleteByPrimaryKey(Integer maid);

    int insert(LgMarketApply record);

    int insertSelective(LgMarketApply record);

    List<LgMarketApply> selectByExample(LgMarketApplyExample example);

    LgMarketApply selectByPrimaryKey(Integer maid);

    int updateByExampleSelective(@Param("record") LgMarketApply record, @Param("example") LgMarketApplyExample example);

    int updateByExample(@Param("record") LgMarketApply record, @Param("example") LgMarketApplyExample example);

    int updateByPrimaryKeySelective(LgMarketApply record);

    int updateByPrimaryKey(LgMarketApply record);
}