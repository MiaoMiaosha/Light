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
import com.light.pojo.LgAccountingExtra;
import com.light.pojo.LgBonus;
import com.light.pojo.LgBonusExtra;
import com.light.pojo.LgBonusLevel;
import com.light.service.BonusService;

@Controller
public class BonusController {

	@Autowired BonusService BonusService;
	/**
	 * 提交分红
	 * @Title: commitSalary 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午7:43:58
	 */
	@RequestMapping("/admin/bonus/commit")
	@ResponseBody
	Map commitBonus(LgBonus lgBonus,
			@RequestParam(value="bonusUsers",required=true)String bonusUsers,
			HttpSession session,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);

		Map rsMap = null;
		try {
			Integer userId = (Integer) session.getAttribute("userId");
			rsMap = BonusService.commitBonus(lgBonus,userId,bonusUsers);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取分红列表
	 * @Title: getSalaryList 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午7:45:20
	 */
	@RequestMapping("/admin/bonus/list")
	@ResponseBody
	Map getSalaryList(LgBonus lgBonus,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime,Integer employeeId,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);

		Map rsMap = null;
		try {
			PageInfo<?> info = BonusService.getBonusList(lgBonus, page, rows, startTime, endTime,employeeId);
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
	 * Date : 2017年2月21日 上午11:56:51
	 */
	@RequestMapping("/admin/bonus/extralist")
	@ResponseBody
	Map getAccountingExtraList(LgBonusExtra extra,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);

		Map rsMap = null;
		try {
			List list = BonusService.getExtraInfoList(extra);
			rsMap = ReturnResult.ok(list, (long)list.size(), 1, list.size());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 确认分红状态
	 * @Title: confirmBonus 
	 * @author TobyHan
	 * Date : 2017年2月21日 下午3:07:07
	 */
	@RequestMapping("/admin/bonus/confirm")
	@ResponseBody
	Map confirmBonus(@RequestParam(value="id",required=true)Integer id,
			@RequestParam(value="status",required=true)Integer status ,HttpSession session,
			HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);

		Map rsMap = null;
		try {
			Integer userId = (Integer) session.getAttribute("userId");
			rsMap = BonusService.confirmBonus(id,userId,status);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 删除分红记录
	 * @Title: deleteBonus 
	 * @author TobyHan
	 * Date : 2017年2月21日 下午3:15:11
	 */
	@RequestMapping("/admin/bonus/delete")
	@ResponseBody
	Map deleteBonus(@RequestParam(value="id",required=true)Integer id,
			HttpServletResponse response){
		Map rsMap = null;
		try {
			rsMap = BonusService.deleteBonusById(id);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取分红级别
	 * @Title: bonusLevel 
	 * @author TobyHan
	 * Date : 2017年2月22日 上午9:32:56
	 */
	@RequestMapping("/admin/bonus/level")
	@ResponseBody
	Map bonusLevel(@RequestParam(value="bonusIds",required=true)String bonusIds,
			HttpServletResponse response){
		Map rsMap = null;
		try {
			rsMap = BonusService.getBonusLevel(bonusIds);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 编辑分红
	 * @Title: editLevel 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午3:35:34
	 */
	@RequestMapping("/admin/bonus/edit")
	@ResponseBody
	Map editLevel(LgBonus lgBonus,String bonusIds){
		Map rsMap = null;
		try {
			rsMap = BonusService.editBonus(lgBonus,bonusIds);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 编辑分红类型
	 * @Title: editBonusExtra 
	 * @author TobyHan
	 * Date : 2017年3月23日 下午4:40:49
	 */
	@RequestMapping("/admin/bonus/extraedit")
	@ResponseBody
	Map editBonusExtra(LgBonusExtra extra){
		Map rsMap = null;
		try {
			rsMap = BonusService.editBonusExtra(extra);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除分红类型
	 * @Title: deleteBonusExtra 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午10:27:10
	 */
	@RequestMapping("/admin/bonus/extradelete")
	@ResponseBody
	Map deleteBonusExtra(Integer id){
		Map rsMap = null;
		try {
			rsMap = BonusService.deleteBonusExtra(id);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 添加分红类型
	 * @Title: addBonusExtra 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午10:49:46
	 */
	@RequestMapping("/admin/bonus/extraadd")
	@ResponseBody
	Map addBonusExtra(LgBonusExtra extra){
		Map rsMap = null;
		try {
			rsMap = BonusService.addBonusExtra(extra);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取分红比例
	 * @Title: bonusLevelList 
	 * @author TobyHan
	 * Date : 2017年4月10日 下午1:11:16
	 */
	@RequestMapping("/admin/bonus/levellist")
	@ResponseBody
	Map bonusLevelList(LgBonusLevel level){
		Map rsMap = null;
		try {
			List list  = BonusService.getLevelList(level);
			rsMap = ReturnResult.ok(list);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 分红比例增加
	 * @Title: addbonusLevel 
	 * @author TobyHan
	 * Date : 2017年4月10日 下午1:23:40
	 */
	@RequestMapping("/admin/bonus/leveladd")
	@ResponseBody
	Map addbonusLevel(LgBonusLevel level){
		Map rsMap = null;
		try {
			 BonusService.addBonusLevel(level);
			rsMap = ReturnResult.ok();
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 分红比例修改
	 * @Title: editbonusLevel 
	 * @author TobyHan
	 * Date : 2017年4月10日 下午1:23:48
	 */
	@RequestMapping("/admin/bonus/leveledit")
	@ResponseBody
	Map editbonusLevel(LgBonusLevel level){
		Map rsMap = null;
		try {
			 BonusService.editBonusLevel(level);
			rsMap = ReturnResult.ok();
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除分红比例
	 * @Title: deleteBonusLevel 
	 * @author TobyHan
	 * Date : 2017年4月10日 下午1:24:46
	 */
	@RequestMapping("/admin/bonus/leveldelete")
	@ResponseBody
	Map deleteBonusLevel(@RequestParam(value="id",required=true)Integer id){
		Map rsMap = null;
		try {
			 BonusService.deleteBonusLevel(id);
			rsMap = ReturnResult.ok();
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
}
