package com.light.mapper;

import com.light.pojo.LgTrainTeacher;
import com.light.pojo.LgTrainTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgTrainTeacherMapper {
    int countByExample(LgTrainTeacherExample example);

    int deleteByExample(LgTrainTeacherExample example);

    int deleteByPrimaryKey(Integer tid);

    int insert(LgTrainTeacher record);

    int insertSelective(LgTrainTeacher record);

    List<LgTrainTeacher> selectByExample(LgTrainTeacherExample example);

    LgTrainTeacher selectByPrimaryKey(Integer tid);

    int updateByExampleSelective(@Param("record") LgTrainTeacher record, @Param("example") LgTrainTeacherExample example);

    int updateByExample(@Param("record") LgTrainTeacher record, @Param("example") LgTrainTeacherExample example);

    int updateByPrimaryKeySelective(LgTrainTeacher record);

    int updateByPrimaryKey(LgTrainTeacher record);
}