package com.light.mapper.custom;

import java.util.List;
import java.util.Map;

import com.light.dto.InputMybatis;
import com.light.dto.StaffSalaryCountBean;
import com.light.dto.TotalCountUserListBean;

public interface LgTotalCountCustomMapper {

	/**
	 * 根据**获取用户列表，desc
	 * @Title: getUserListDesc 
	 * @author TobyHan
	 * Date : 2017年2月28日 下午3:33:53
	 */
	List<TotalCountUserListBean> getUserListDesc(InputMybatis obj);
	/**
	 * 获取所有dateNo list
	 * @Title: getTimeInterval 
	 * @author TobyHan
	 * Date : 2017年2月28日 下午5:23:43
	 */
	List<Integer> getTimeInterval();
	/**
	 * 获取具体eid 具体dateNo 信息
	 * @Title: specDataWithEidDateNo 
	 * @author TobyHan
	 * Date : 2017年2月28日 下午5:25:16
	 */
	List<Integer> specDataWithEidDateNo(InputMybatis obj);
	/**
	 * 获取设计师工资
	 * @Title: getSumSalary 
	 * @author TobyHan
	 * Date : 2017年3月18日 下午6:11:41
	 */
	List<StaffSalaryCountBean> getSumSalary(Map paramMap);
	
	Integer getSumReim(Map paramMap);
	/**
	 * 获取合同人分红
	 * @Title: getSumBonus 
	 * @author TobyHan
	 * Date : 2017年3月18日 下午6:12:13
	 */
	List<StaffSalaryCountBean> getSumBonus(Map paramMap);
	
	Integer getSumNotAppSalary(Map paramMap);
	Integer getSumNotAppBonus(Map paramMap);
	/**
	 * 获得个人业绩收入
	 * @Title: getPersonBusSalary 
	 * @author TobyHan
	 * Date : 2017年3月27日 下午5:21:53
	 */
	Integer getPersonBusSalary(Map paramMap);
	
}
