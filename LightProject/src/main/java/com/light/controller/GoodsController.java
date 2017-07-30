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
import com.light.pojo.LgGoods;
import com.light.service.GoodsService;

@Controller
public class GoodsController {

	@Autowired
	GoodsService goodsService;
	/**
	 * 获取设备列表
	 * @Title: getGoodsList 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午3:09:57
	 */
	@RequestMapping("/goods/list")
	@ResponseBody
	public Map getGoodsList(LgGoods lgGoods,@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows){
		PageInfo<?> result = goodsService.getGoodsList(lgGoods, page, rows, null, null);
		List list = result.getList();
		return ReturnResult.ok(list, result.getTotal(), result.getPageNum(), result.getPageNum());
	}
	/**
	 * 获取设备详细信息
	 * @Title: getGoodsDetail 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午8:09:27
	 */
	@RequestMapping("/goods/detail")
	@ResponseBody
	public Map getGoodsDetail(@RequestParam(value="goodsId",required=true)Integer goodsId){
		LgGoods lgGoods = goodsService.getGoodsDetail(goodsId);
		if(lgGoods != null)
			return ReturnResult.ok(lgGoods);
		return ReturnResult.wrong("获取不到设备信息");
	}
	/**
	 * 发布设备
	 * @Title: addGoods 
	 * @author TobyHan
	 * Date : 2017年3月15日 上午9:15:36
	 */
	@RequestMapping("/goods/add")
	@ResponseBody
	public Map addGoods(LgGoods goods){
		Map rsMap = null;
		try {
			rsMap = goodsService.publishGoods(goods);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除设备
	 * @Title: deleteGoods 
	 * @author TobyHan
	 * Date : 2017年3月15日 上午9:16:16
	 */
	@RequestMapping("/goods/delete")
	@ResponseBody
	public Map deleteGoods(Integer goodsId){
		Map rsMap = null;
		try {
			rsMap = goodsService.deleteGoods(goodsId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * pc发布设备
	 * @Title pcAddGoods
	 * @author TobyHan
	 * Date : 2017年5月7日 下午3:50:03
	 * @param goods
	 * @return
	 */
	@RequestMapping("/pcpub/goods/add")
	@ResponseBody
	public Map pcAddGoods(LgGoods goods){
		Map rsMap = null;
		try {
			rsMap = goodsService.publishGoods(goods);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/*@RequestMapping("/goods/list")
	@ResponseBody
	public Map getGoodsOrderList(Lggoods lgGoods,@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows){
		PageInfo<?> result = goodsService.getGoodsList(lgGoods, page, rows, null, null);
		List list = result.getList();
		return ReturnResult.ok(list, result.getTotal(), result.getPageNum(), result.getPageNum());
	}*/
	
}
