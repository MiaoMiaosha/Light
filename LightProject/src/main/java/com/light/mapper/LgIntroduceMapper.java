package com.light.mapper;

import com.light.pojo.LgIntroduce;
import com.light.pojo.LgIntroduceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgIntroduceMapper {
    int countByExample(LgIntroduceExample example);

    int deleteByExample(LgIntroduceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgIntroduce record);

    int insertSelective(LgIntroduce record);

    List<LgIntroduce> selectByExample(LgIntroduceExample example);

    LgIntroduce selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgIntroduce record, @Param("example") LgIntroduceExample example);

    int updateByExample(@Param("record") LgIntroduce record, @Param("example") LgIntroduceExample example);

    int updateByPrimaryKeySelective(LgIntroduce record);

    int updateByPrimaryKey(LgIntroduce record);
}