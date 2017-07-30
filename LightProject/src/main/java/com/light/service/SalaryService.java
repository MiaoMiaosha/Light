package com.light.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.exception.ServiceException;
import com.light.pojo.LgSalary;
import com.light.pojo.LgSalaryExtra;

/**
 * 收入相关服务
 * @ClassName: SalaryService 
 * @author TobyHan
 * @date 2017年2月15日 下午4:30:49 
 *
 */
public interface SalaryService {

	/**
	 * 提交收入申请
	 * @Title: commitSalary 
	 * @author TobyHan
	 * Date : 2017年2月15日 下午4:32:30
	 */
	Map commitSalary(LgSalary salary, Integer userId) throws ServiceException;
	/**
	 * 获取收入类型说明
	 * @Title: getSalaryExtra 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午6:38:04
	 */
	List getSalaryExtra(LgSalaryExtra lgSalaryExtra) throws ServiceException;
	/**
	 * 获取收入列表
	 * @Title: getSalaryList 
	 * @author TobyHan
	 * Date : 2017年2月15日 下午4:39:32
	 */
	PageInfo<?> getSalaryList(LgSalary salary,Integer page, Integer rows,
			Integer startTime, Integer endTime,String marketName) throws ServiceException;
	/**
	 * 修改具体某条收入记录
	 * @Title: modifySalaryById 
	 * @author TobyHan
	 * Date : 2017年2月15日 下午4:41:18
	 */
	Map modifySalaryById(LgSalary salary) throws ServiceException;
	/**
	 * 删除某条收入记录
	 * @Title: deleteSalaryById 
	 * @author TobyHan
	 * Date : 2017年2月15日 下午4:42:57
	 */
	Map deleteSalaryById(Integer sid) throws ServiceException;
	/**
	 * 审核通过
	 * @Title: setSuccessSalary 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午8:31:09
	 */
	Map setSuccessSalary(Integer sid) throws ServiceException;
	
	/**
	 * 编辑工资类型
	 * @Title: editSalaryExtra 
	 * @author TobyHan
	 * Date : 2017年3月23日 下午4:12:57
	 */
	Map editSalaryExtra(LgSalaryExtra extra) throws ServiceException;
	/**
	 * 删除工资类型
	 * @Title: deleteSalaryExtra 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午10:00:11
	 */
	Map deleteSalaryExtra(Integer tid) throws ServiceException;
	/**
	 * 增加工资类型
	 * @Title: addSalaryExtra 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午10:56:16
	 */
	Map addSalaryExtra(LgSalaryExtra extra) throws ServiceException;
	
}
