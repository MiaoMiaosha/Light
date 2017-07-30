package com.light.mapper;

import com.light.pojo.LgRecord;
import com.light.pojo.LgRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgRecordMapper {
    int countByExample(LgRecordExample example);

    int deleteByExample(LgRecordExample example);

    int deleteByPrimaryKey(Integer rid);

    int insert(LgRecord record);

    int insertSelective(LgRecord record);

    List<LgRecord> selectByExample(LgRecordExample example);

    LgRecord selectByPrimaryKey(Integer rid);

    int updateByExampleSelective(@Param("record") LgRecord record, @Param("example") LgRecordExample example);

    int updateByExample(@Param("record") LgRecord record, @Param("example") LgRecordExample example);

    int updateByPrimaryKeySelective(LgRecord record);

    int updateByPrimaryKey(LgRecord record);
}