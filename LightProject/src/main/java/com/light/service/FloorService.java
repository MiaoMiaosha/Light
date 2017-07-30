package com.light.service;

import java.util.List;

import com.light.exception.ServiceException;

public interface FloorService {

	/**
	 * 根据市场获取楼层列表
	 * @Title: getFloorListById 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午10:35:03
	 */
	List getFloorListById(Integer marketId) throws ServiceException;
}
