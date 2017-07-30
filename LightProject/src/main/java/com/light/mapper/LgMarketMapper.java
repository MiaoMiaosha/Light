package com.light.mapper;

import com.light.pojo.LgMarket;
import com.light.pojo.LgMarketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgMarketMapper {
    int countByExample(LgMarketExample example);

    int deleteByExample(LgMarketExample example);

    int deleteByPrimaryKey(Integer mid);

    int insert(LgMarket record);

    int insertSelective(LgMarket record);

    List<LgMarket> selectByExample(LgMarketExample example);

    LgMarket selectByPrimaryKey(Integer mid);

    int updateByExampleSelective(@Param("record") LgMarket record, @Param("example") LgMarketExample example);

    int updateByExample(@Param("record") LgMarket record, @Param("example") LgMarketExample example);

    int updateByPrimaryKeySelective(LgMarket record);

    int updateByPrimaryKey(LgMarket record);
}