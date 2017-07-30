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
import com.light.pojo.LgCaseApply;
import com.light.pojo.LgCooperateCompany;
import com.light.pojo.LgCooperateCompanyApply;
import com.light.pojo.LgGoods;
import com.light.pojo.LgGoodsApply;
import com.light.pojo.LgMarketApply;
import com.light.pojo.LgNewsApply;
import com.light.pojo.LgStallApply;
import com.light.service.PublishService;

@Controller
public class PublishController {

	@Autowired PublishService publishService;
	
	/**
	 * 获取案例申请
	 * @Title: getCasePublishList 
	 * @author TobyHan
	 * Date : 2017年2月18日 上午11:46:16
	 */
	@RequestMapping("/apply/caselist")
	@ResponseBody
	Map getCasePublishList(LgCaseApply apply,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime,String order){
		PageInfo<?> info = publishService.getCasePublish(apply, page, rows, startTime, endTime,order);
		return ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
	}
	/**
	 * 获取市场发布申请
	 * @Title: getMarketPublishList 
	 * @author TobyHan
	 * Date : 2017年2月20日 下午1:46:22
	 */
	@RequestMapping("/apply/marketlist")
	@ResponseBody
	Map getMarketPublishList(LgMarketApply apply,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime,String order,String contactName, String contactMobile){
		PageInfo<?> info = publishService.getMarketPublish(apply, page, rows, startTime, endTime, order, contactName, contactMobile);
		return ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
	}
	/**
	 * 获取摊位发布申请
	 * @Title: getStallPublishList 
	 * @author TobyHan
	 * Date : 2017年2月20日 下午1:46:35
	 */
	@RequestMapping("/apply/stalllist")
	@ResponseBody
	Map getStallPublishList(LgStallApply apply,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime,String order){
		PageInfo<?> info = publishService.getStallPublish(apply, page, rows, startTime, endTime,order);
		return ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
	}
	/**
	 * 获取新闻发布申请
	 * @Title: getNewsPublishList 
	 * @author TobyHan
	 * Date : 2017年2月20日 下午1:46:48
	 */
	@RequestMapping("/apply/newslist")
	@ResponseBody
	Map getNewsPublishList(LgNewsApply apply,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime,String order,
			String newsTitle, Integer type){
		PageInfo<?> info = publishService.getNewsPublish(apply, page, rows, startTime, endTime,order,newsTitle,type);
		return ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
	}
	/**
	 * 获取加盟发布申请
	 * @Title: getCooperatePublishList 
	 * @author TobyHan
	 * Date : 2017年3月15日 上午11:52:48
	 */
	@RequestMapping("/apply/cooperatelist")
	@ResponseBody
	Map getCooperatePublishList(LgCooperateCompanyApply apply,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime,String order){
		PageInfo<?> info = publishService.getCooperatePublish(apply, page, rows, order);
		return ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
	}
	/**
	 * 获取设备发布列表
	 * @Title: getGoodsPublishList 
	 * @author TobyHan
	 * Date : 2017年3月15日 下午7:32:23
	 */
	@RequestMapping("/apply/goodslist")
	@ResponseBody
	Map getGoodsPublishList(LgGoodsApply apply,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			String order){
		PageInfo<?> info = publishService.getGoodsPublish(apply, page, rows, order);
		return ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
	}
	
	
	/**
	 * 审核案例
	 * @Title: approveCase 
	 * @author TobyHan
	 * Date : 2017年3月9日 下午7:40:22
	 */
	@RequestMapping("/admin/apply/appcase")
	@ResponseBody
	Map approveCase(Integer caseApplyId,Integer status){
		Map rsMap = null;
		try {
			rsMap = publishService.approveCase(caseApplyId, status);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
		
	}
	/**
	 * 审核市场
	 * @Title: approveMarket 
	 * @author TobyHan
	 * Date : 2017年3月9日 下午7:40:56
	 */
	@RequestMapping("/admin/apply/appmarket")
	@ResponseBody
	Map approveMarket(Integer marketApplyId,Integer status){
		Map rsMap = null;
		try {
			rsMap = publishService.approveMarket(marketApplyId, status);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
		
	}
	
	/**
	 * 审核摊位
	 * @Title: approveStall 
	 * @author TobyHan
	 * Date : 2017年3月9日 下午7:42:02
	 */
	@RequestMapping("/admin/apply/appstall")
	@ResponseBody
	Map approveStall(Integer stallApplyId,Integer status){
		Map rsMap = null;
		try {
			rsMap = publishService.approveStall(stallApplyId, status);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
		
	}
	/**
	 * 审核新闻
	 * @Title: approveNews 
	 * @author TobyHan
	 * Date : 2017年3月9日 下午7:42:47
	 */
	@RequestMapping("/admin/apply/appnews")
	@ResponseBody
	Map approveNews(Integer newsApplyId,Integer status){
		Map rsMap = null;
		try {
			rsMap = publishService.approveNews(newsApplyId, status);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
		
	}
	/**
	 * 审核加盟
	 * @Title: approveCooperateCompany 
	 * @author TobyHan
	 * Date : 2017年3月9日 下午7:43:25
	 */
	@RequestMapping("/admin/apply/appcooperate")
	@ResponseBody
	Map approveCooperateCompany(Integer cooperateApplyId,Integer status){
		Map rsMap = null;
		try {
			rsMap = publishService.approveCooperateCompany(cooperateApplyId, status);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
		
	}
	/**
	 * 审核设备
	 * @Title: approveGoods 
	 * @author TobyHan
	 * Date : 2017年3月15日 下午7:48:35
	 */
	@RequestMapping("/admin/apply/appgoods")
	@ResponseBody
	Map approveGoods(Integer goodsApplyId,Integer status){
		Map rsMap = null;
		try {
			rsMap = publishService.approveGoods(goodsApplyId, status);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
		
	}
	/**
	 * 统一删除接口
	 * @Title: deleteAll 
	 * @author TobyHan
	 * Date : 2017年4月5日 上午11:48:29
	 */
	@RequestMapping("/admin/apply/delete")
	@ResponseBody
	Map deleteAll(@RequestParam(value="type",required=true)Integer type,
			@RequestParam(value="typeId",required=true)Integer typeId){
		Map rsMap = null;
		try {
			rsMap = publishService.deleteAll(type, typeId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
}
