package com.light.mapper;

import com.light.pojo.LgComment;
import com.light.pojo.LgCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgCommentMapper {
    int countByExample(LgCommentExample example);

    int deleteByExample(LgCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgComment record);

    int insertSelective(LgComment record);

    List<LgComment> selectByExample(LgCommentExample example);

    LgComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgComment record, @Param("example") LgCommentExample example);

    int updateByExample(@Param("record") LgComment record, @Param("example") LgCommentExample example);

    int updateByPrimaryKeySelective(LgComment record);

    int updateByPrimaryKey(LgComment record);
}