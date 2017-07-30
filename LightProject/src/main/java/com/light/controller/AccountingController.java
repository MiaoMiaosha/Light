package com.light.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.github.pagehelper.PageInfo;
import com.light.common.ConstantVal;
import com.light.common.DomainUtil;
import com.light.common.ReturnResult;
import com.light.pojo.LgAccounting;
import com.light.pojo.LgAccountingExtra;
import com.light.pojo.LgBonus;
import com.light.service.AccountingService;
import com.light.service.BonusService;

@Controller
public class AccountingController {

	@Autowired AccountingService AccountingService;
	
	/**
	 * 提交入账信息
	 * @Title: commitAccounting 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午8:27:38
	 */
	@RequestMapping("/admin/account/commit")
	@ResponseBody
	Map commitAccounting(LgAccounting lgAccounting, HttpSession session,
			HttpServletResponse response){
		DomainUtil.setDomain(response);
		Map rsMap = null;
		try {
			Integer userId = (Integer) session.getAttribute("userId");
			rsMap = AccountingService.commitAccounting(lgAccounting,userId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取入账列表
	 * @Title: getAccountingList 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午8:27:50
	 */
	@RequestMapping("/admin/account/list")
	@ResponseBody
	Map getAccountingList(LgAccounting lgAccounting,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime,
			HttpServletResponse response){
		DomainUtil.setDomain(response);
		Map rsMap = null;
		try {
			PageInfo<?> info = AccountingService.getAccountingList(lgAccounting, page, rows, startTime, endTime);
			rsMap = ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 获取额外信息
	 * @Title: getAccountingExtraList 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午9:39:51
	 */
	@RequestMapping("/admin/account/extralist")
	@ResponseBody
	Map getAccountingExtraList(LgAccountingExtra extra,
			HttpServletResponse response){
		DomainUtil.setDomain(response);

		Map rsMap = null;
		try {
			List list = AccountingService.getExtraInfoList(extra);
			rsMap = ReturnResult.ok(list, (long)list.size(), 1, list.size());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 修改入账记录
	 * @Title: editAccounting 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午10:41:29
	 */
	@RequestMapping("/admin/account/edit")
	@ResponseBody
	Map editAccounting(LgAccounting lgAccounting, HttpSession session,
			HttpServletResponse response){
		DomainUtil.setDomain(response);

		Map rsMap = null;
		try {
			Integer userId = (Integer) session.getAttribute("userId");
			rsMap = AccountingService.modifyAccounting(lgAccounting,userId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 删除记录
	 * @Title: deleteAccounting 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午11:01:32
	 */
	@RequestMapping("/admin/account/delete")
	@ResponseBody
	Map deleteAccounting(@RequestParam(value="id",required=true)Integer id,
			HttpServletResponse response){
		DomainUtil.setDomain(response);

		Map rsMap = null;
		try {
			rsMap = AccountingService.deleteAccountingById(id);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 确定入账状态
	 * @Title: confirmAccount 
	 * @author TobyHan
	 * Date : 2017年2月21日 下午2:36:09
	 */
	@RequestMapping("/admin/account/confirm")
	@ResponseBody
	Map confirmAccount(@RequestParam(value="id",required=true)Integer id,
			@RequestParam(value="status",required=true)Integer status,
			HttpSession session,
			HttpServletResponse response){
		DomainUtil.setDomain(response);

		Map rsMap = null;
		try {
			Integer userId = (Integer) session.getAttribute("userId");
			//rsMap = AccountingService.deleteAccountingById(id);
			rsMap = AccountingService.confirmAccout(id, status, userId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
}
