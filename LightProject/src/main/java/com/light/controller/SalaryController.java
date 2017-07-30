package com.light.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 收入相关
 * @ClassName: SalaryController 
 * @author TobyHan
 * @date 2017年2月21日 上午6:27:53 
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.ls.LSInput;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.pojo.LgSalary;
import com.light.pojo.LgSalaryExtra;
import com.light.service.SalaryService;
@Controller
public class SalaryController {

	@Autowired SalaryService SalaryService;
	
	/**
	 * 提交收入
	 * @Title: commitSalary 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午6:34:20
	 */
	@RequestMapping("/admin/salary/commit")
	@ResponseBody
	Map commitSalary(LgSalary salary, 
			HttpSession session){
		Map rsMap = null;
		try {
			Integer userId = (Integer) session.getAttribute("userId");
			rsMap = SalaryService.commitSalary(salary,userId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取收入类型 和 设计内容
	 * @Title: getSalaryListExtra 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午6:43:27
	 */
	@RequestMapping("/admin/salary/extra")
	@ResponseBody
	Map getSalaryListExtra(LgSalaryExtra lgSalaryExtra){
		Map rsMap = null;
		try {
			List list = SalaryService.getSalaryExtra(lgSalaryExtra);
			rsMap = ReturnResult.ok(list, (long)list.size(), 1, list.size());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 获取收入列表
	 * @Title: getSalaryList 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午6:50:08
	 */
	@RequestMapping("/admin/salary/list")
	@ResponseBody
	Map getSalaryList(LgSalary lgSalary,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime,String marketName){
		Map rsMap = null;
		try {
			PageInfo<?> info = SalaryService.getSalaryList(lgSalary, page, rows, startTime, endTime,marketName);
			rsMap = ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 修改工资详情
	 * @Title: editSalary 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午9:04:41
	 */
	@RequestMapping("/admin/salary/edit")
	@ResponseBody
	Map editSalary(LgSalary salary){
		Map rsMap = null;
		try {
			rsMap = SalaryService.modifySalaryById(salary);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 删除工资记录
	 * @Title: deleteSalary 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午6:54:25
	 */
	@RequestMapping("/admin/salary/delete")
	@ResponseBody
	Map deleteSalary(@RequestParam(value="sid")Integer sid){
		Map rsMap = null;
		try {
			rsMap = SalaryService.deleteSalaryById(sid);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 审核收入通过
	 * @Title: confirmSalary 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午9:05:26
	 */
	@RequestMapping("/admin/salary/confirm")
	@ResponseBody
	Map confirmSalary(@RequestParam(value="sid")Integer sid){
		Map rsMap = null;
		try {
			rsMap = SalaryService.setSuccessSalary(sid);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 编辑工资类型
	 * @Title: editSalaryListExtra 
	 * @author TobyHan
	 * Date : 2017年3月23日 下午4:11:56
	 */
	@RequestMapping("/admin/salary/extraedit")
	@ResponseBody
	Map editSalaryListExtra(LgSalaryExtra lgSalaryExtra){
		Map rsMap = null;
		try {
			rsMap = SalaryService.editSalaryExtra(lgSalaryExtra);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除工资类型
	 * @Title: deleteExtra 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午10:03:54
	 */
	@RequestMapping("/admin/salary/extradelete")
	@ResponseBody
	Map deleteExtra(@RequestParam(value="tid")Integer tid){
		Map rsMap = null;
		try {
			rsMap = SalaryService.deleteSalaryExtra(tid);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 增加工资类型
	 * @Title: addExtra 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午10:59:52
	 */
	@RequestMapping("/admin/salary/extraadd")
	@ResponseBody
	Map addExtra(LgSalaryExtra extra){
		Map rsMap = null;
		try {
			rsMap = SalaryService.addSalaryExtra(extra);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
}
