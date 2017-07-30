package com.light.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgGoods;

/**
 * 设备
 * @ClassName: GoodsService 
 * @author TobyHan
 * @date 2017年2月16日 下午2:29:35 
 *
 */
public interface GoodsService {

	/**
	 * 获取设备列表
	 * @Title: getGoodsList 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午2:36:14
	 */
	PageInfo getGoodsList(LgGoods lgGoods,Integer page, Integer rows, 
			Integer startTime, Integer endTime) throws ServiceException;
	/**
	 * 获取设备详细信息
	 * @Title: getGoodsDetail 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午8:07:27
	 */
	LgGoods getGoodsDetail(Integer goodsId);
	/**
	 * 发布设备
	 * @Title: publishGoods 
	 * @author TobyHan
	 * Date : 2017年3月15日 上午8:32:03
	 */
	Map publishGoods(LgGoods goods) throws ServiceException;
	/**
	 * 发布设备
	 * @Title: deleteGoods 
	 * @author TobyHan
	 * Date : 2017年3月15日 上午8:32:26
	 */
	Map deleteGoods(Integer goodsId) throws ServiceException;

	
}
