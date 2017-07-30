package com.light.controller;

import java.util.List;
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
import com.light.pojo.LgReimburse;
import com.light.pojo.LgReimburseExtra;
import com.light.service.ReimburseService;

@Controller
public class ReimburseController {

	@Autowired ReimburseService ReimburseService;
	/**
	 * 提交报销
	 * @Title: commitReimburse 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午7:55:47
	 */
 	@RequestMapping("/admin/reimburse/commit")
	@ResponseBody
	Map commitReimburse(LgReimburse lgReimburse,HttpSession session,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);

		Map rsMap = null;
		try {
			Integer userId = (Integer) session.getAttribute("userId");
			rsMap = ReimburseService.commitReimbursement(lgReimburse,userId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取报销列表
	 * @Title: getSalaryList 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午7:45:20
	 */
	@RequestMapping("/admin/reimburse/list")
	@ResponseBody
	Map getReimburseList(LgReimburse lgReimburse,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);

		Map rsMap = null;
		try {
			PageInfo<?> info = ReimburseService.getReimbursementList(lgReimburse, page, rows, startTime, endTime);
			rsMap = ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 获取报销额外信息
	 * @Title: getReimburseExtraList 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午4:11:18
	 */
	@RequestMapping("/admin/reimburse/extralist")
	@ResponseBody
	Map getReimburseExtraList(LgReimburseExtra extra,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);

		Map rsMap = null;
		try {
			List list = ReimburseService.getExtraList(extra);
			rsMap = ReturnResult.ok(list, (long)list.size(), 1, list.size());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 修改报销
	 * @Title: editReimburse 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午4:10:52
	 */
	@RequestMapping("/admin/reimburse/edit")
	@ResponseBody
	Map editReimburse(LgReimburse reimburse,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);

		Map rsMap = null;
		try {
			rsMap = ReimburseService.modifyReimburseById(reimburse);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 标记删除
	 * @Title: deleteReimburse 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午4:16:27
	 */
	@RequestMapping("/admin/reimburse/delete")
	@ResponseBody
	Map deleteReimburse(Integer rid,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);

		Map rsMap = null;
		try {
			rsMap = ReimburseService.deleteReimburseById(rid);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 编辑类型
	 * @Title: editReimburseExtra 
	 * @author TobyHan
	 * Date : 2017年3月28日 下午5:08:36
	 */
	@RequestMapping("/admin/reimburse/extraedit")
	@ResponseBody
	Map editReimburseExtra(LgReimburseExtra extra){

		Map rsMap = null;
		try {
			rsMap = ReimburseService.editExtra(extra);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 添加类型
	 * @Title: addReimburseExtra 
	 * @author TobyHan
	 * Date : 2017年3月28日 下午5:09:02
	 */
	@RequestMapping("/admin/reimburse/extraadd")
	@ResponseBody
	Map addReimburseExtra(LgReimburseExtra extra){
		Map rsMap = null;
		try {
			rsMap = ReimburseService.addExtra(extra);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除类型
	 * @Title: deleteReimburseExtra 
	 * @author TobyHan
	 * Date : 2017年3月28日 下午5:10:00
	 */
	@RequestMapping("/admin/reimburse/extradelete")
	@ResponseBody
	Map deleteReimburseExtra(@RequestParam(value="id",required=true)Integer id){
		Map rsMap = null;
		try {
			rsMap = ReimburseService.deleteExtra(id);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
}
