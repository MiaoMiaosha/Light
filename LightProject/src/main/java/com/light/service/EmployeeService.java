package com.light.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgEmployee;

/**
 * 员工档案相关
 * @ClassName: EmployeeService 
 * @author TobyHan
 * @date 2017年2月21日 上午6:56:58 
 *
 */
public interface EmployeeService {

	/**
	 * 获取员工列表
	 * @Title: getEmployeeList 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午6:58:39
	 */
	PageInfo getEmployeeList(LgEmployee employee,Integer page,Integer rows);
	/**
	 * 发布员工信息
	 * @Title: commitEmployee 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午8:51:36
	 */
	Map commitEmployee(LgEmployee employee, String username, String password);
	/**
	 * 编辑员工信息
	 * @Title: editEmployee 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午3:37:44
	 */
	Map editEmployee(LgEmployee employee,String username,String password) throws ServiceException;
	/**
	 * 标记删除员工
	 * @Title: deleteEmployee 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午3:37:55
	 */
	Map deleteEmployee(Integer eid);
	/**
	 * 获取所有除会计外的员工
	 * @Title: getEmployeeListExceptKuaiJi 
	 * @author TobyHan
	 * Date : 2017年3月9日 下午4:14:38
	 */
	PageInfo getEmployeeListExceptKuaiJi(LgEmployee employee,Integer page,Integer rows);

}
