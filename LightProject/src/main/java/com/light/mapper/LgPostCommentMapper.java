package com.light.mapper;

import com.light.pojo.LgPostComment;
import com.light.pojo.LgPostCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgPostCommentMapper {
    int countByExample(LgPostCommentExample example);

    int deleteByExample(LgPostCommentExample example);

    int deleteByPrimaryKey(Integer pcid);

    int insert(LgPostComment record);

    int insertSelective(LgPostComment record);

    List<LgPostComment> selectByExample(LgPostCommentExample example);

    LgPostComment selectByPrimaryKey(Integer pcid);

    int updateByExampleSelective(@Param("record") LgPostComment record, @Param("example") LgPostCommentExample example);

    int updateByExample(@Param("record") LgPostComment record, @Param("example") LgPostCommentExample example);

    int updateByPrimaryKeySelective(LgPostComment record);

    int updateByPrimaryKey(LgPostComment record);
}