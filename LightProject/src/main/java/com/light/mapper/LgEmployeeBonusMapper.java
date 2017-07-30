package com.light.mapper;

import com.light.pojo.LgEmployeeBonus;
import com.light.pojo.LgEmployeeBonusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgEmployeeBonusMapper {
    int countByExample(LgEmployeeBonusExample example);

    int deleteByExample(LgEmployeeBonusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LgEmployeeBonus record);

    int insertSelective(LgEmployeeBonus record);

    List<LgEmployeeBonus> selectByExample(LgEmployeeBonusExample example);

    LgEmployeeBonus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LgEmployeeBonus record, @Param("example") LgEmployeeBonusExample example);

    int updateByExample(@Param("record") LgEmployeeBonus record, @Param("example") LgEmployeeBonusExample example);

    int updateByPrimaryKeySelective(LgEmployeeBonus record);

    int updateByPrimaryKey(LgEmployeeBonus record);
}