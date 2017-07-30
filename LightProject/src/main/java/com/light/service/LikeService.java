package com.light.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgLike;

public interface LikeService {

	/**
	 * 获取收藏列表
	 * @Title: getLikeList 
	 * @author TobyHan
	 * Date : 2017年2月17日 下午4:15:45
	 */
	PageInfo<?> getLikeList(LgLike lgLike, Integer page, Integer rows) throws ServiceException;
	/**
	 * 添加收藏
	 * @Title: addToLike 
	 * @author TobyHan
	 * Date : 2017年2月17日 下午4:44:03
	 */
	Map addToLike(LgLike lgLike) throws ServiceException;
	/**
	 * 判断是否已收藏
	 * @Title: isLike 
	 * @author TobyHan
	 * Date : 2017年3月21日 上午1:56:11
	 */
	 Map isLike(LgLike lgLike) throws ServiceException;
	/**
	 * 取消收藏
	 * @Title: deleteLike 
	 * @author TobyHan
	 * Date : 2017年3月21日 上午2:10:03
	 */
	 Map deleteLike(Integer id) throws ServiceException;
}
