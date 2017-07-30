package com.light.mapper;

import com.light.pojo.LgGoods;
import com.light.pojo.LgGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgGoodsMapper {
    int countByExample(LgGoodsExample example);

    int deleteByExample(LgGoodsExample example);

    int deleteByPrimaryKey(Integer goodsId);

    int insert(LgGoods record);

    int insertSelective(LgGoods record);

    List<LgGoods> selectByExample(LgGoodsExample example);

    LgGoods selectByPrimaryKey(Integer goodsId);

    int updateByExampleSelective(@Param("record") LgGoods record, @Param("example") LgGoodsExample example);

    int updateByExample(@Param("record") LgGoods record, @Param("example") LgGoodsExample example);

    int updateByPrimaryKeySelective(LgGoods record);

    int updateByPrimaryKey(LgGoods record);
}