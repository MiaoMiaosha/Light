package com.light.mapper;

import com.light.pojo.LgLike;
import com.light.pojo.LgLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgLikeMapper {
    int countByExample(LgLikeExample example);

    int deleteByExample(LgLikeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgLike record);

    int insertSelective(LgLike record);

    List<LgLike> selectByExample(LgLikeExample example);

    LgLike selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgLike record, @Param("example") LgLikeExample example);

    int updateByExample(@Param("record") LgLike record, @Param("example") LgLikeExample example);

    int updateByPrimaryKeySelective(LgLike record);

    int updateByPrimaryKey(LgLike record);
}