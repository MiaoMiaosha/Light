package com.light.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgCooperateCompany;

public interface CooperateService {

	/**
	 * 招商加盟申请
	 * @Title: publishCooperate 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午9:23:24
	 */
	Map publishCooperate(LgCooperateCompany company,Integer userId) throws ServiceException;
	/**
	 * 获取加盟商家列表
	 * @Title: getCooperateList 
	 * @author TobyHan
	 * Date : 2017年3月7日 上午10:11:35
	 */
	PageInfo<?> getCooperateList(LgCooperateCompany company,Integer page, Integer rows);
	/**
	 * 获取主营业务列表
	 * @Title: getMainBusinessList 
	 * @author TobyHan
	 * Date : 2017年3月10日 下午2:20:20
	 */
	List getMainBusinessList(Integer id);
	/**
	 * 删除加盟公司，标记删除
	 * @Title: deleteCooperateCompany 
	 * @author TobyHan
	 * Date : 2017年3月10日 下午4:32:55
	 */
	Map deleteCooperateCompany(Integer cid);
}
