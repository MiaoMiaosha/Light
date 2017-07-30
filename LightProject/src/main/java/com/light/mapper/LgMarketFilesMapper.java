package com.light.mapper;

import com.light.pojo.LgMarketFiles;
import com.light.pojo.LgMarketFilesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgMarketFilesMapper {
    int countByExample(LgMarketFilesExample example);

    int deleteByExample(LgMarketFilesExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(LgMarketFiles record);

    int insertSelective(LgMarketFiles record);

    List<LgMarketFiles> selectByExample(LgMarketFilesExample example);

    LgMarketFiles selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") LgMarketFiles record, @Param("example") LgMarketFilesExample example);

    int updateByExample(@Param("record") LgMarketFiles record, @Param("example") LgMarketFilesExample example);

    int updateByPrimaryKeySelective(LgMarketFiles record);

    int updateByPrimaryKey(LgMarketFiles record);
}