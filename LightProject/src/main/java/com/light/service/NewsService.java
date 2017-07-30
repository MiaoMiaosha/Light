package com.light.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgNews;

public interface NewsService {

	/**
	 * 获取新闻列表
	 * @Title: getNewsList 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午8:39:28
	 */
	PageInfo<?> getNewsList(LgNews lgNews,Integer page,Integer rows,
			Integer startTime,Integer endTime) throws ServiceException;
	//----改版获取新闻列表接口//
	PageInfo<?> getNewsListNew(LgNews lgNews,Integer page, Integer rows,
			Integer startTime, Integer endTime) throws ServiceException;
	/**
	 * 获取新闻详情
	 * @Title: getNewsDetailById 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午9:15:27
	 */
	LgNews getNewsDetailById(Integer newsId) throws ServiceException;
	/**
	 * 发布新闻
	 * @Title: publishNews 
	 * @author TobyHan
	 * Date : 2017年2月17日 下午5:26:13
	 */
	Map publishNews(LgNews lgNews) throws ServiceException;
	/**
	 * 增加浏览记录
	 * @Title: addView 
	 * @author TobyHan
	 * Date : 2017年3月22日 下午4:50:31
	 */
	Map addView(Integer nid) throws ServiceException;
}
