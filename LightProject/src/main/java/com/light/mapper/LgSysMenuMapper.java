package com.light.mapper;

import com.light.pojo.LgSysMenu;
import com.light.pojo.LgSysMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgSysMenuMapper {
    int countByExample(LgSysMenuExample example);

    int deleteByExample(LgSysMenuExample example);

    int deleteByPrimaryKey(Integer menuId);

    int insert(LgSysMenu record);

    int insertSelective(LgSysMenu record);

    List<LgSysMenu> selectByExample(LgSysMenuExample example);

    LgSysMenu selectByPrimaryKey(Integer menuId);

    int updateByExampleSelective(@Param("record") LgSysMenu record, @Param("example") LgSysMenuExample example);

    int updateByExample(@Param("record") LgSysMenu record, @Param("example") LgSysMenuExample example);

    int updateByPrimaryKeySelective(LgSysMenu record);

    int updateByPrimaryKey(LgSysMenu record);
}