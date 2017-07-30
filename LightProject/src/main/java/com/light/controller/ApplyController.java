package com.light.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 其他申请
 * @author tobyhan
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.pojo.LgOtherApply;
import com.light.service.ApplyService;
@Controller
public class ApplyController {

	@Autowired
	ApplyService applyService;
	
	/**
	 * 添加申请
	 * @Title addApply
	 * @author TobyHan
	 * Date : 2017年6月1日 下午11:17:17
	 * @param apply
	 * @return
	 */
	@RequestMapping("/apply/add")
	@ResponseBody
	public Map addApply(LgOtherApply apply){
		Map rsMap = null;
		try {
			rsMap = applyService.addApply(apply);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong();
		}
		return rsMap;
	}
	/**
	 * 编辑申请
	 * @Title editApply
	 * @author TobyHan
	 * Date : 2017年6月1日 下午11:18:14
	 * @param apply
	 * @return
	 */
	@RequestMapping("/apply/edit")
	@ResponseBody
	public Map editApply(LgOtherApply apply){
		Map rsMap = null;
		try {
			rsMap = applyService.updateApply(apply);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong();
		}
		return rsMap;
	}
	/**
	 * 分页查询
	 * @Title getApplies
	 * @author TobyHan
	 * Date : 2017年6月1日 下午11:22:35
	 * @param apply
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/apply/list")
	@ResponseBody
	public Map getApplies(LgOtherApply apply,
			@RequestParam(value="page",required=true,defaultValue="1")Integer page, 
			@RequestParam(value="rows",required=true,defaultValue="30")Integer rows){
		Map rsMap = null;
		try {
			PageInfo<LgOtherApply> pageInfo = applyService.selectApply(apply, page, rows);
			rsMap = ReturnResult.ok(pageInfo.getList(), pageInfo.getTotal(), page, pageInfo.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong();
		}
		return rsMap;
	}
	/**
	 * 删除申请
	 * @Title deleteApply
	 * @author TobyHan
	 * Date : 2017年6月1日 下午11:23:21
	 * @param id
	 * @return
	 */
	@RequestMapping("/apply/delete")
	@ResponseBody
	public Map deleteApply(Integer id){
		Map rsMap = null;
		try {
			rsMap = applyService.deleteApply(id);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong();
		}
		return rsMap;
	}
	
	
	
	
}
