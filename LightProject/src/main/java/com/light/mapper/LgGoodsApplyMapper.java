package com.light.mapper;

import com.light.pojo.LgGoodsApply;
import com.light.pojo.LgGoodsApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgGoodsApplyMapper {
    int countByExample(LgGoodsApplyExample example);

    int deleteByExample(LgGoodsApplyExample example);

    int deleteByPrimaryKey(Integer gaid);

    int insert(LgGoodsApply record);

    int insertSelective(LgGoodsApply record);

    List<LgGoodsApply> selectByExample(LgGoodsApplyExample example);

    LgGoodsApply selectByPrimaryKey(Integer gaid);

    int updateByExampleSelective(@Param("record") LgGoodsApply record, @Param("example") LgGoodsApplyExample example);

    int updateByExample(@Param("record") LgGoodsApply record, @Param("example") LgGoodsApplyExample example);

    int updateByPrimaryKeySelective(LgGoodsApply record);

    int updateByPrimaryKey(LgGoodsApply record);
}