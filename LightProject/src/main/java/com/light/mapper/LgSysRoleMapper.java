package com.light.mapper;

import com.light.pojo.LgSysRole;
import com.light.pojo.LgSysRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgSysRoleMapper {
    int countByExample(LgSysRoleExample example);

    int deleteByExample(LgSysRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(LgSysRole record);

    int insertSelective(LgSysRole record);

    List<LgSysRole> selectByExample(LgSysRoleExample example);

    LgSysRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") LgSysRole record, @Param("example") LgSysRoleExample example);

    int updateByExample(@Param("record") LgSysRole record, @Param("example") LgSysRoleExample example);

    int updateByPrimaryKeySelective(LgSysRole record);

    int updateByPrimaryKey(LgSysRole record);
}