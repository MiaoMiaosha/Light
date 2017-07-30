package com.light.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ConstantVal;
import com.light.common.ReturnResult;
import com.light.pojo.LgBonus;
import com.light.pojo.LgCustomer;
import com.light.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired CustomerService CustomerService;
	
	/**
	 * 提交客户档案
	 * @Title: commitCustomer 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午6:00:11
	 */
	@RequestMapping("/admin/customer/commit")
	@ResponseBody
	Map commitCustomer(LgCustomer lgCustomer,String username,String password,
			HttpSession session,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);
		Map rsMap = null;
		try {
			Integer userId = (Integer) session.getAttribute("userId");
			rsMap = CustomerService.commitCustomer(lgCustomer, userId,username,password);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取客户档案列表
	 * @Title: getSalaryList 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午6:22:15
	 */
	@RequestMapping("/admin/customer/list")
	@ResponseBody
	Map getSalaryList(LgCustomer lgCustomer,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);

		Map rsMap = null;
		try {
			PageInfo<?> info = CustomerService.getCustomerList(lgCustomer, page, rows, startTime, endTime);
			rsMap = ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 修改客户档案
	 * @Title: editCustomer 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午6:23:45
	 */
	@RequestMapping("/admin/customer/edit")
	@ResponseBody
	Map editCustomer(LgCustomer lgCustomer,String username,String password,
			HttpSession session,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);
		Map rsMap = null;
		try {
			rsMap = CustomerService.modifyCustomeryId(lgCustomer,username,password);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除客户档案
	 * @Title: deleteCustomer 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午6:24:58
	 */
	@RequestMapping("/admin/customer/delete")
	@ResponseBody
	Map deleteCustomer(Integer cid,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);
		Map rsMap = null;
		try {
			rsMap = CustomerService.deleteCustomerById(cid);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
}
