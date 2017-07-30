package com.light.mapper;

import com.light.pojo.LgOtherApply;
import com.light.pojo.LgOtherApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgOtherApplyMapper {
    int countByExample(LgOtherApplyExample example);

    int deleteByExample(LgOtherApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgOtherApply record);

    int insertSelective(LgOtherApply record);

    List<LgOtherApply> selectByExample(LgOtherApplyExample example);

    LgOtherApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgOtherApply record, @Param("example") LgOtherApplyExample example);

    int updateByExample(@Param("record") LgOtherApply record, @Param("example") LgOtherApplyExample example);

    int updateByPrimaryKeySelective(LgOtherApply record);

    int updateByPrimaryKey(LgOtherApply record);
}