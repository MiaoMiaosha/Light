package com.light.mapper;

import com.light.pojo.LgNewsApply;
import com.light.pojo.LgNewsApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgNewsApplyMapper {
    int countByExample(LgNewsApplyExample example);

    int deleteByExample(LgNewsApplyExample example);

    int deleteByPrimaryKey(Integer naid);

    int insert(LgNewsApply record);

    int insertSelective(LgNewsApply record);

    List<LgNewsApply> selectByExample(LgNewsApplyExample example);

    LgNewsApply selectByPrimaryKey(Integer naid);

    int updateByExampleSelective(@Param("record") LgNewsApply record, @Param("example") LgNewsApplyExample example);

    int updateByExample(@Param("record") LgNewsApply record, @Param("example") LgNewsApplyExample example);

    int updateByPrimaryKeySelective(LgNewsApply record);

    int updateByPrimaryKey(LgNewsApply record);
}