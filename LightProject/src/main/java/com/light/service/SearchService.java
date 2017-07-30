package com.light.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;

public interface SearchService {
	/**
	 * 获得搜索结果
	 * @Title: getSearchResultList 
	 * @author TobyHan
	 * Date : 2017年3月23日 上午9:44:03
	 */
	Map getSearchResultList(String key,Integer page,Integer rows);
	/**
	 * 单独搜索
	 * @Title: getSearchOtherList 
	 * @author TobyHan
	 * Date : 2017年3月23日 上午10:38:15
	 */
	PageInfo<?> getSearchOtherList(Integer type,String key,Integer page,Integer rows);
	/**
	 * 获取热词
	 * @Title: getHotwordsList 
	 * @author TobyHan
	 * Date : 2017年3月23日 下午1:50:40
	 */
	PageInfo<?> getHotwordsList(Integer page, Integer rows);
}
