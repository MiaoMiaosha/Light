package com.light.mapper;

import com.light.pojo.LgNewsComment;
import com.light.pojo.LgNewsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgNewsCommentMapper {
    int countByExample(LgNewsCommentExample example);

    int deleteByExample(LgNewsCommentExample example);

    int deleteByPrimaryKey(Integer ncid);

    int insert(LgNewsComment record);

    int insertSelective(LgNewsComment record);

    List<LgNewsComment> selectByExample(LgNewsCommentExample example);

    LgNewsComment selectByPrimaryKey(Integer ncid);

    int updateByExampleSelective(@Param("record") LgNewsComment record, @Param("example") LgNewsCommentExample example);

    int updateByExample(@Param("record") LgNewsComment record, @Param("example") LgNewsCommentExample example);

    int updateByPrimaryKeySelective(LgNewsComment record);

    int updateByPrimaryKey(LgNewsComment record);
}