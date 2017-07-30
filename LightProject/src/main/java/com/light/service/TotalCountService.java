package com.light.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;

/**
 * 统计表
 * @ClassName: TotalCountService 
 * @author TobyHan
 * @date 2017年2月23日 上午10:54:51 
 *
 */
public interface TotalCountService {

	/**
	 * 如果没有员工月统计记录，则创建，返回记录id
	 * @Title: createEmployeeRecord 
	 * @author TobyHan
	 * Date : 2017年2月23日 上午10:56:45
	 */
	Integer createEmployeeRecord(Integer userId,Integer dateNo) throws ServiceException;
	
	/**
	 * 获取设计师排名列表
	 * @Title: getDesignEmployeeList 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午8:38:51
	 */
	List getDesignEmployeeList(Integer page,Integer rows,Integer startTime,Integer endTime) throws ServiceException;
	/**
	 * 根据type，获取所有，非删除员工列表,排序
	 * @Title: getAllEmployeeList 
	 * @author TobyHan
	 * Date : 2017年2月28日 下午3:25:02
	 */
	PageInfo getAllEmployeeList(Integer type,Integer page, Integer rows, Integer startTime, Integer endTime) throws ServiceException;
	
	Map getTotalResult(Integer page, Integer rows,String eids) throws ServiceException;
	
	/**
	 * 获取我的收益
	 * @Title: getStaffDetail 
	 * @author TobyHan
	 * Date : 2017年3月18日 下午5:02:20
	 */
	Map getStaffDetail(Integer employeeId,Integer startTime,Integer endTime);
	
}
