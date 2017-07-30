package com.light.mapper;

import com.light.pojo.LgTotalCount;
import com.light.pojo.LgTotalCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgTotalCountMapper {
    int countByExample(LgTotalCountExample example);

    int deleteByExample(LgTotalCountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgTotalCount record);

    int insertSelective(LgTotalCount record);

    List<LgTotalCount> selectByExample(LgTotalCountExample example);

    LgTotalCount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgTotalCount record, @Param("example") LgTotalCountExample example);

    int updateByExample(@Param("record") LgTotalCount record, @Param("example") LgTotalCountExample example);

    int updateByPrimaryKeySelective(LgTotalCount record);

    int updateByPrimaryKey(LgTotalCount record);
}