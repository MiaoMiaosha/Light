package com.light.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.pojo.LgBonus;
import com.light.pojo.LgPartner;
import com.light.service.PartnerService;

@Controller
public class PartnerController {

	@Autowired PartnerService partnerService;
	
	/**
	 * 提交合作方档案
	 * @Title: commitPartner 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午8:12:46
	 */
	@RequestMapping("/admin/partner/commit")
	@ResponseBody
	Map commitPartner(LgPartner lgPartner){
		Map rsMap = null;
		try {
			rsMap = partnerService.commitPartner(lgPartner);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取合作方列表
	 * @Title: getSalaryList 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午8:12:09
	 */
	@RequestMapping("/admin/partner/list")
	@ResponseBody
	Map getPartnerList(LgPartner lgPartner,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime){
		Map rsMap = null;
		try {
			PageInfo<?> info = partnerService.getPartnerList(lgPartner, page, rows, startTime, endTime);
			rsMap = ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除合作方档案
	 * @Title: deletePartner 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午7:07:07
	 */
	@RequestMapping("/admin/partner/delete")
	@ResponseBody
	Map deletePartner(@RequestParam(value="pid",required=true)Integer pid){
		Map rsMap = null;
		try {
			rsMap = partnerService.deletePartnerById(pid);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 修改合作方档案
	 * @Title: editPartner 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午7:07:42
	 */
	@RequestMapping("/admin/partner/edit")
	@ResponseBody
	Map editPartner(LgPartner lgPartner){
		Map rsMap = null;
		try {
			rsMap = partnerService.modifyPartnerById(lgPartner);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
}
