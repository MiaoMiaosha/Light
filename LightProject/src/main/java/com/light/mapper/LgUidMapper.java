package com.light.mapper;

import com.light.pojo.LgUid;
import com.light.pojo.LgUidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgUidMapper {
    int countByExample(LgUidExample example);

    int deleteByExample(LgUidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgUid record);

    int insertSelective(LgUid record);

    List<LgUid> selectByExample(LgUidExample example);

    LgUid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgUid record, @Param("example") LgUidExample example);

    int updateByExample(@Param("record") LgUid record, @Param("example") LgUidExample example);

    int updateByPrimaryKeySelective(LgUid record);

    int updateByPrimaryKey(LgUid record);
}