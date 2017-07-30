package com.light.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.mapper.LgNewsMapper;
import com.light.pojo.LgNews;
import com.light.service.NewsService;

@Controller
public class NewsController {

	@Autowired
	NewsService newsService;
	/**
	 * 获取新闻列表
	 * @Title: getNewsList 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午8:53:59
	 */
	@RequestMapping("/news/list")
	@ResponseBody
	Map getNewsList(LgNews lgNews, @RequestParam(value="page",defaultValue="1")Integer page, 
			@RequestParam(value="rows",defaultValue="30")Integer rows, 
			Integer startTime, Integer endTime){
		//PageInfo result = newsService.getNewsList(lgNews, page, rows, startTime, endTime);
		PageInfo result = newsService.getNewsListNew(lgNews, page, rows, startTime, endTime);
		return ReturnResult.ok(result.getList(), result.getTotal(), result.getPages(), result.getPageNum());
	}
	/**
	 * 获取新闻详情
	 * @Title: getNewsById 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午9:02:23
	 */
	@RequestMapping("/news/detail")
	@ResponseBody
	Map getNewsById(@RequestParam(value="newsId",required=true)Integer newsId){
		LgNews lgNews = newsService.getNewsDetailById(newsId);
		if(lgNews != null)
			return ReturnResult.ok(lgNews);
		return ReturnResult.wrong("获取不到新闻信息");
	}
	/**
	 * 发布新闻
	 * @Title: publishNews 
	 * @author TobyHan
	 * Date : 2017年2月17日 下午5:52:27
	 */
	@RequestMapping("/news/publish")
	@ResponseBody
	Map publishNews(LgNews lgNews){
		Map rsMap = null;
		try {
			rsMap = newsService.publishNews(lgNews);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 增加浏览量
	 * @Title: addView 
	 * @author TobyHan
	 * Date : 2017年3月22日 下午4:56:26
	 */
	@RequestMapping("/news/viewadd")
	@ResponseBody
	Map addView(@RequestParam(value="nid",required=true)Integer nid){
		Map rsMap = null;
		try {
			rsMap = newsService.addView(nid);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * pc端发布新闻
	 * @Title pcPublishNews
	 * @author TobyHan
	 * Date : 2017年5月7日 下午3:45:35
	 * @param lgNews
	 * @return
	 */
	@RequestMapping("/pcpub/news/publish")
	@ResponseBody
	Map pcPublishNews(LgNews lgNews){
		Map rsMap = null;
		try {
			rsMap = newsService.publishNews(lgNews);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
}
