package com.light.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.pojo.LgMarket;
import com.light.service.MarketService;

/**
 * 市场案例
 * @ClassName: MarketController 
 * @author TobyHan
 * @date 2017年2月16日 下午3:13:55 
 *
 */
@Controller
public class MarketController {

	@Autowired
	MarketService MarketService;
	
	/**
	 * 获取市场列表
	 * @Title: getGoodsList 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午3:23:48
	 */
	@RequestMapping("/market/list")
	@ResponseBody
	public Map getGoodsList(LgMarket lgMarket,@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			HttpServletResponse response){
		PageInfo<?> result = null;
		try {
			 result = MarketService.getMarketList(lgMarket, page, rows);
		     response.setHeader("Access-Control-Allow-Origin", "*");

		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		List list = result.getList();
		return ReturnResult.ok(list, result.getTotal(), page, result.getPageNum());
	}
	/**
	 * 发布市场
	 * @Title: publishMarket 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午10:15:18
	 */
	@RequestMapping(value="/market/add",method=RequestMethod.POST)
	@ResponseBody
	public Map publishMarket(LgMarket lgMarket,String floorInfo){
		Map rsMap = null;
		try {
			rsMap = MarketService.applyForMarket(lgMarket,floorInfo);
		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 修改市场接口
	 * @Title: publishMarket 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午3:45:43
	 */
	@RequestMapping(value="/admin/market/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map editMarket(LgMarket lgMarket){
		Map rsMap = null;
		try {
			rsMap = MarketService.editMarket(lgMarket);
		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * pc发布市场
	 * @Title pcPublishMarket
	 * @author TobyHan
	 * Date : 2017年5月7日 下午3:47:51
	 * @param lgMarket
	 * @param floorInfo
	 * @return
	 */
	@RequestMapping(value="/pcpub/market/add",method=RequestMethod.POST)
	@ResponseBody
	public Map pcPublishMarket(LgMarket lgMarket,String floorInfo){
		Map rsMap = null;
		try {
			rsMap = MarketService.applyForMarket(lgMarket,floorInfo);
		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
}
