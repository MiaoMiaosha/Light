package com.light.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.pojo.LgLike;
import com.light.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	SearchService SearchService;
	
	/**
	 * 搜索接口
	 * @Title: getSearchList 
	 * @author TobyHan
	 * Date : 2017年3月23日 上午10:31:15
	 */
	@RequestMapping("/search")
	@ResponseBody
	Map getSearchList(String key,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="3")Integer rows){
		Map rsMap = null;
		try {
			rsMap = SearchService.getSearchResultList(key, page, rows);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 其它搜索
	 * @Title: getOtherSearchList 
	 * @author TobyHan
	 * Date : 2017年3月23日 上午10:40:16
	 */
	@RequestMapping("/search/other")
	@ResponseBody
	Map getOtherSearchList(@RequestParam(value="type",required=true)Integer type,
			String key,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows){
		Map rsMap = null;
		try {
			PageInfo<?> pageInfo = SearchService.getSearchOtherList(type, key, page, rows);
			List list = pageInfo.getList();
			rsMap = ReturnResult.ok(list, pageInfo.getTotal(), page, pageInfo.getPageNum());
			
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取热词
	 * @Title: getHotwords 
	 * @author TobyHan
	 * Date : 2017年3月23日 下午1:53:51
	 */
	@RequestMapping("/hotwords")
	@ResponseBody
	Map getHotwords(
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="8")Integer rows){
		Map rsMap = null;
		try {
			PageInfo<?> pageInfo = SearchService.getHotwordsList(page, rows);
			rsMap = ReturnResult.ok(pageInfo.getList(), pageInfo.getTotal(), page, pageInfo.getPageNum());

		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
}
