package com.light.mapper.custom;

import java.util.List;
import java.util.Map;

/**
 * 分红自定义查询
 * @ClassName: LgBonusCustomMapper 
 * @author TobyHan
 * @date 2017年2月22日 上午9:24:20 
 *
 */
public interface LgBonusCustomMapper {

	/**
	 * 获取用户所有工资/分红级别
	 * @Title: getUserTotalSalaryById 
	 * @author TobyHan
	 * Date : 2017年2月22日 上午9:25:18
	 */
	Integer getUserPersonSalaryById(Integer employeeId,Integer startTime,Integer endTime);
	/**
	 * 根据分红id，获取所有相关员工id
	 * @Title: getBonusRelateEmployeeId 
	 * @author TobyHan
	 * Date : 2017年2月22日 下午9:11:37
	 */
	List<Integer> getBonusRelateEmployeeId(Integer bonusId);
	/**
	 * 根据分红id，获取该记录分红金额
	 * @Title: getBonusMoneyByBid 
	 * @author TobyHan
	 * Date : 2017年2月22日 下午9:27:18
	 */
	Integer getBonusMoneyByBid(Integer bonusId);
	/**
	 * 根据分红id，获取该记录的个人业绩、分红金额
	 * @Title: getBonusMapByBid 
	 * @author TobyHan
	 * Date : 2017年2月23日 上午11:45:11
	 */
	Map<String, Integer> getBonusMapByBid(Integer id);
	
	/**
	 * 根据id获得该分红类型拥有记录条数
	 * @Title: getCountByBonusTypeId 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午10:33:05
	 */
	Integer getCountByBonusTypeId(Integer id);
}
