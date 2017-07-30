package com.light.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 员工档案相关
 * @ClassName: EmployeeController 
 * @author TobyHan
 * @date 2017年2月21日 上午6:56:03 
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ConstantVal;
import com.light.common.ReturnResult;
import com.light.pojo.LgAccounting;
import com.light.pojo.LgEmployee;
import com.light.service.EmployeeService;
@Controller
public class EmployeeController {

	@Autowired EmployeeService EmployeeService;
	
	/**
	 * 注册员工
	 * @Title: commitEmployee 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午8:54:33
	 */
	@RequestMapping("/admin/employee/commit")
	@ResponseBody
	Map commitEmployee(LgEmployee employee,
			@RequestParam(value="username",required=true)String username, 
			@RequestParam(value="password",required=true)String password){
		Map rsMap = null;
		try {
			rsMap = EmployeeService.commitEmployee(employee,username,password);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 获取员工列表
	 * @Title: getEmployeeList 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午7:10:38
	 */
	@RequestMapping(ConstantVal.VALID_PRE+"/employee/list")
	@ResponseBody
	Map getEmployeeList(LgEmployee lgEmployee,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows){
		Map rsMap = null;
		try {
			PageInfo<?> info = EmployeeService.getEmployeeList(lgEmployee, page, rows);
			rsMap = ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 编辑员工信息
	 * @Title: editEmployee 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午4:01:24
	 */
	@RequestMapping("/admin/employee/edit")
	@ResponseBody
	Map editEmployee(LgEmployee employee,String username,String password){
		Map rsMap = null;
		try {
			rsMap = EmployeeService.editEmployee(employee,username,password);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 员工标记删除
	 * @Title: deleteEmployee 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午4:02:22
	 */
	@RequestMapping("/admin/employee/delete")
	@ResponseBody
	Map deleteEmployee(Integer eid){
		Map rsMap = null;
		try {
			rsMap = EmployeeService.deleteEmployee(eid);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取除会计外的所有员工列表
	 * @Title: getEmployeeWorkList 
	 * @author TobyHan
	 * Date : 2017年3月9日 下午4:16:52
	 */
	@RequestMapping(ConstantVal.VALID_PRE+"/employee/worklist")
	@ResponseBody
	Map getEmployeeWorkList(LgEmployee lgEmployee,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows){
		Map rsMap = null;
		try {
			PageInfo<?> info = EmployeeService.getEmployeeListExceptKuaiJi(lgEmployee, page, rows);
			rsMap = ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
}
