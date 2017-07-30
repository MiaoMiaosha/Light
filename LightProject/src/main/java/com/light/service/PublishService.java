package com.light.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgCaseApply;
import com.light.pojo.LgCooperateCompanyApply;
import com.light.pojo.LgGoods;
import com.light.pojo.LgGoodsApply;
import com.light.pojo.LgMarketApply;
import com.light.pojo.LgNews;
import com.light.pojo.LgNewsApply;
import com.light.pojo.LgStallApply;

/**
 * 发布服务
 * @ClassName: PublishService 
 * @author TobyHan
 * @date 2017年2月18日 上午8:23:24 
 *
 */
public interface PublishService {

	/**
	 * 获取个人案例发布信息
	 * @Title: getCasePublish 
	 * @author TobyHan
	 * Date : 2017年2月18日 上午8:29:23
	 */
	PageInfo getCasePublish(LgCaseApply apply, Integer page, Integer rows,
			Integer startTime, Integer endTime, String order) throws ServiceException;
	/**
	 * 获取个人市场发布信息
	 * @Title: getMarketPublish 
	 * @author TobyHan
	 * Date : 2017年2月18日 上午8:29:55
	 */
	PageInfo getMarketPublish(LgMarketApply apply, Integer page, Integer rows,
			Integer startTime, Integer endTime, String order, String contactName,String contactMobile) throws ServiceException;
	/**
	 * 获取个人摊位发布信息
	 * @Title: getStallPublish 
	 * @author TobyHan
	 * Date : 2017年2月18日 上午8:30:06
	 */
	PageInfo getStallPublish(LgStallApply apply, Integer page, Integer rows,
			Integer startTime, Integer endTime, String order) throws ServiceException;
	/**
	 * 获取新闻发布信息
	 * @Title: getNewsPublish 
	 * @author TobyHan
	 * Date : 2017年2月18日 上午8:30:09
	 */
	PageInfo getNewsPublish(LgNewsApply apply, Integer page, Integer rows,
			Integer startTime, Integer endTime, String order, String newsTitle, Integer type) throws ServiceException;
	
	/**
	 * 获取加盟发布信息列表
	 * @Title: getCooperatePublish 
	 * @author TobyHan
	 * Date : 2017年3月7日 下午8:03:59
	 */
	PageInfo getCooperatePublish(LgCooperateCompanyApply apply, Integer page, Integer rows, String order) throws ServiceException;
	/**
	 * 获取设备发布信息列表
	 * @Title: getGoodsPublish 
	 * @author TobyHan
	 * Date : 2017年3月15日 下午6:41:04
	 */
	PageInfo getGoodsPublish(LgGoodsApply apply, Integer page, Integer rows, String order) throws ServiceException;

	
	/**
	 * 审核案例
	 * @Title: approveCase 
	 * @author TobyHan
	 * Date : 2017年3月7日 下午7:51:36
	 */
	Map approveCase(Integer caseApplyId,Integer status) throws ServiceException;
	/**
	 * 审核市场
	 * @Title: approveMarket 
	 * @author TobyHan
	 * Date : 2017年3月7日 下午7:52:22
	 */
	Map approveMarket(Integer marketApplyId, Integer status) throws ServiceException;
	/**
	 * 审核摊位
	 * @Title: approveStall 
	 * @author TobyHan
	 * Date : 2017年3月7日 下午7:53:19
	 */
	Map approveStall(Integer stallApplyId,Integer status) throws ServiceException;
	/**
	 * 审核新闻
	 * @Title: approveNews 
	 * @author TobyHan
	 * Date : 2017年3月7日 下午7:54:00
	 */
	Map approveNews(Integer newsApplyId, Integer status) throws ServiceException;
	/**
	 * 审核加盟
	 * @Title: approveCooperateCompany 
	 * @author TobyHan
	 * Date : 2017年3月7日 下午8:04:39
	 */
	Map approveCooperateCompany(Integer cooperateApplyId, Integer status) throws ServiceException;
	/**
	 * 审核设备
	 * @Title: approveGoods 
	 * @author TobyHan
	 * Date : 2017年3月15日 下午7:18:16
	 */
	Map approveGoods(Integer goodsApplyId, Integer status) throws ServiceException;
	/**
	 * 统一删除
	 * @Title: deleteAll 
	 * @author TobyHan
	 * Date : 2017年4月5日 上午11:23:22
	 */
	Map deleteAll(Integer type,Integer typeId) throws ServiceException;

}
