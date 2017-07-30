package com.light.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.common.ReturnResult;
import com.light.pojo.LgStall;
import com.light.pojo.LgStallApply;
import com.light.service.StallService;

@Controller
public class StallController {

	@Autowired
	StallService stallService;
	/**
	 * 获取市场楼层摊位列表
	 * @Title: getListByFloorId 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午1:07:46
	 */
	@RequestMapping("/stall/list")
	@ResponseBody
	Map getListByFloorId(@RequestParam(value="floorId",required=true)Integer floorId){
		
		return ReturnResult.ok(stallService.getStallListByFloorId(floorId));
	}
	/**
	 * 增加新摊位
	 * @Title: addNewStall 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午1:09:32
	 */
	@RequestMapping("/stall/add")
	@ResponseBody
	Map addNewStall(LgStall lgStall){
		return stallService.addNewStall(lgStall);
	}
	/**
	 * 用户申请摊位
	 * @Title: applyStall 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午4:35:18
	 */
	@RequestMapping("/stall/apply")
	@ResponseBody
	Map applyStall(LgStallApply apply,@RequestParam(value="userId",required=true)Integer userId){
		Map rsMap = null;
		try {
			rsMap = stallService.applyForStall(apply,userId);
		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除摊位
	 * @Title: deleteStall 
	 * @author TobyHan
	 * Date : 2017年3月21日 上午10:39:30
	 */
	@RequestMapping("/stall/delete")
	@ResponseBody
	Map deleteStall(@RequestParam(value="stallId",required=true)Integer stallId){
		Map rsMap = null;
		try {
			rsMap = stallService.deleteStall(stallId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * pc申请摊位
	 * @Title pcApplyStall
	 * @author TobyHan
	 * Date : 2017年5月7日 下午3:51:07
	 * @param apply
	 * @param userId
	 * @return
	 */
	@RequestMapping("/pcpub/stall/apply")
	@ResponseBody
	Map pcApplyStall(LgStallApply apply,@RequestParam(value="userId",required=true)Integer userId){
		Map rsMap = null;
		try {
			rsMap = stallService.applyForStall(apply,userId);
		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
}
