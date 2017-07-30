package com.light.mapper;

import com.light.pojo.LgCustomer;
import com.light.pojo.LgCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LgCustomerMapper {
    int countByExample(LgCustomerExample example);

    int deleteByExample(LgCustomerExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(LgCustomer record);

    int insertSelective(LgCustomer record);

    List<LgCustomer> selectByExample(LgCustomerExample example);

    LgCustomer selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") LgCustomer record, @Param("example") LgCustomerExample example);

    int updateByExample(@Param("record") LgCustomer record, @Param("example") LgCustomerExample example);

    int updateByPrimaryKeySelective(LgCustomer record);

    int updateByPrimaryKey(LgCustomer record);
}