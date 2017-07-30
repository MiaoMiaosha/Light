package com.light.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgCustomer;

/**
 * 客户档案
 * @ClassName: CustomerService 
 * @author TobyHan
 * @date 2017年2月15日 下午5:02:02 
 *
 */
public interface CustomerService {
	/**
	 * 提交客户档案
	 * @Title: commitCustomer 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午5:46:19
	 */
	Map commitCustomer(LgCustomer lgCustomer,Integer userId,
			String username,String password) throws ServiceException;
	/**
	 * 获取客户档案列表
	 * @Title: getCustomerList 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午5:46:39
	 */
	PageInfo<?> getCustomerList(LgCustomer lgCustomer,Integer page, Integer rows,
			Integer startTime, Integer endTime) throws ServiceException;
	/**
	 * 修改客户档案
	 * @Title: modifyCustomeryId 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午5:46:52
	 */
	Map modifyCustomeryId(LgCustomer lgCustomer, String username, String password) throws ServiceException;

	/**
	 * 删除客户档案
	 * @Title: deleteCustomerById 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午5:47:00
	 */
	Map deleteCustomerById(Integer cid) throws ServiceException;
}
