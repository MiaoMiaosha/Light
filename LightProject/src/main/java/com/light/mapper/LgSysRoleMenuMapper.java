package com.light.mapper;

import com.light.pojo.LgSysRoleMenu;
import com.light.pojo.LgSysRoleMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgSysRoleMenuMapper {
    int countByExample(LgSysRoleMenuExample example);

    int deleteByExample(LgSysRoleMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgSysRoleMenu record);

    int insertSelective(LgSysRoleMenu record);

    List<LgSysRoleMenu> selectByExample(LgSysRoleMenuExample example);

    LgSysRoleMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgSysRoleMenu record, @Param("example") LgSysRoleMenuExample example);

    int updateByExample(@Param("record") LgSysRoleMenu record, @Param("example") LgSysRoleMenuExample example);

    int updateByPrimaryKeySelective(LgSysRoleMenu record);

    int updateByPrimaryKey(LgSysRoleMenu record);
}