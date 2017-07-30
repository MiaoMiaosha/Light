package com.light.mapper;

import com.light.pojo.LgGoodsImage;
import com.light.pojo.LgGoodsImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgGoodsImageMapper {
    int countByExample(LgGoodsImageExample example);

    int deleteByExample(LgGoodsImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgGoodsImage record);

    int insertSelective(LgGoodsImage record);

    List<LgGoodsImage> selectByExample(LgGoodsImageExample example);

    LgGoodsImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgGoodsImage record, @Param("example") LgGoodsImageExample example);

    int updateByExample(@Param("record") LgGoodsImage record, @Param("example") LgGoodsImageExample example);

    int updateByPrimaryKeySelective(LgGoodsImage record);

    int updateByPrimaryKey(LgGoodsImage record);
}