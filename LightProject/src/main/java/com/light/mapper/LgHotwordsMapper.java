package com.light.mapper;

import com.light.pojo.LgHotwords;
import com.light.pojo.LgHotwordsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgHotwordsMapper {
    int countByExample(LgHotwordsExample example);

    int deleteByExample(LgHotwordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgHotwords record);

    int insertSelective(LgHotwords record);

    List<LgHotwords> selectByExample(LgHotwordsExample example);

    LgHotwords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgHotwords record, @Param("example") LgHotwordsExample example);

    int updateByExample(@Param("record") LgHotwords record, @Param("example") LgHotwordsExample example);

    int updateByPrimaryKeySelective(LgHotwords record);

    int updateByPrimaryKey(LgHotwords record);
}