package com.light.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgMarket;

public interface MarketService {

	/**
	 * 获取市场列表
	 * @Title: getMarketList 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午3:14:59
	 */
	PageInfo getMarketList(LgMarket lgMarket,Integer page, Integer rows) throws ServiceException;
	/**
	 * 菜市场申请
	 * @Title: applyForMarket 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午5:10:29
	 */
	Map applyForMarket(LgMarket lgMarket, String floorInfo) throws ServiceException;
	/**
	 * 验证市场名称唯一
	 * @Title: validMarketName 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午10:02:34
	 */
	boolean validMarketName(String name) throws ServiceException;
	
	/**
	 * 修改市场信息
	 * @Title: editMarket 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午3:46:27
	 */
	Map editMarket(LgMarket lgMarket) throws ServiceException;
}
