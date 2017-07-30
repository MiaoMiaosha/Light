package com.light.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.pojo.LgCase;
import com.light.service.CaseService;

@Controller
public class CaseController {

	@Autowired
	CaseService caseService;
	
	/**
	 * 获取案例列表
	 * @Title: getCaselist 
	 * @author TobyHan
	 * Date : 2017年2月14日 下午2:28:44
	 */
	@RequestMapping("/case/list")
	@ResponseBody
	public Map getCaselist(LgCase lgCase,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime){
		PageInfo result = caseService.getCaseList(lgCase, page, rows, startTime, endTime);
		List list = result.getList();
			return ReturnResult.ok(list, result.getTotal(), result.getPages(),result.getPageNum());
		
	}
	/**
	 * 发布案例
	 * @Title: publishCase 
	 * @author TobyHan
	 * Date : 2017年2月16日 上午10:14:26
	 */
	@RequestMapping("/case/add")
	@ResponseBody
	public Map publishCase(LgCase lgCase){
		Map rsMap = null;
		try {
			 rsMap =  caseService.addPublishCase(lgCase);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 修改案例详细信息
	 * @Title: modifyCase 
	 * @author TobyHan
	 * Date : 2017年2月16日 上午10:48:13
	 */
	@RequestMapping("/admin/case/edit")
	@ResponseBody
	public Map modifyCase(LgCase lgCase){
		Map rsMap = null;
		try {
			 rsMap =  caseService.editCase(lgCase);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 修改案例申请状态
	 * @Title: modifyApply 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午4:59:09
	 */
	@RequestMapping("/admin/case/apply")
	@ResponseBody
	public Map modifyApply(Integer applyId, Integer caseId, Integer status){
		Map rsMap = null;
		try {
			 rsMap =  caseService.editCaseApply(applyId,caseId,status);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 获取案例详情
	 * @Title: getCaseDetail 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午4:49:54
	 */
	@RequestMapping("/case/detail")
	@ResponseBody
	public Map getCaseDetail(@RequestParam(value="caseId",required=true)Integer caseId){
		Map rsMap = null;
		try {
			 LgCase lgCase =  caseService.getCaseDetail(caseId);
			 if(lgCase != null)
				 rsMap = ReturnResult.ok(lgCase);
			 else
				 rsMap = ReturnResult.wrong("查不到案例信息");
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * pc端案例发布
	 * @Title pcPublishCase
	 * @author TobyHan
	 * Date : 2017年5月7日 下午3:43:42
	 * @param lgCase
	 * @return
	 */
	@RequestMapping("/pcpub/case/add")
	@ResponseBody
	public Map pcPublishCase(LgCase lgCase){
		Map rsMap = null;
		try {
			 rsMap =  caseService.addPublishCase(lgCase);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
}
