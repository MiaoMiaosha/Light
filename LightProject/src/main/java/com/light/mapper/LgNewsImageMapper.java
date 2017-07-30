package com.light.mapper;

import com.light.pojo.LgNewsImage;
import com.light.pojo.LgNewsImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgNewsImageMapper {
    int countByExample(LgNewsImageExample example);

    int deleteByExample(LgNewsImageExample example);

    int deleteByPrimaryKey(Integer niId);

    int insert(LgNewsImage record);

    int insertSelective(LgNewsImage record);

    List<LgNewsImage> selectByExample(LgNewsImageExample example);

    LgNewsImage selectByPrimaryKey(Integer niId);

    int updateByExampleSelective(@Param("record") LgNewsImage record, @Param("example") LgNewsImageExample example);

    int updateByExample(@Param("record") LgNewsImage record, @Param("example") LgNewsImageExample example);

    int updateByPrimaryKeySelective(LgNewsImage record);

    int updateByPrimaryKey(LgNewsImage record);
}