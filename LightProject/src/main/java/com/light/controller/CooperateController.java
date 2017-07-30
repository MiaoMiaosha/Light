package com.light.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.pojo.LgCase;
import com.light.pojo.LgCooperateCompany;
import com.light.service.CooperateService;

@Controller
public class CooperateController {

	@Autowired
	CooperateService cooperateService;
	
	/**
	 * 加盟申请
	 * @Title: publishCooperate 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午10:13:42
	 */
	@RequestMapping("/cooperate/publish")
	@ResponseBody
	Map publishCooperate(LgCooperateCompany company, Integer userId){
		Map rsMap = null;
		try {
			rsMap = cooperateService.publishCooperate(company, userId);
		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取加盟列表
	 * @Title: getCooperatelist 
	 * @author TobyHan
	 * Date : 2017年3月7日 上午9:59:02
	 */
	@RequestMapping("/cooperate/list")
	@ResponseBody
	public Map getCooperatelist(LgCooperateCompany lgCooperateCompany,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime){
		PageInfo result = cooperateService.getCooperateList(lgCooperateCompany, page, rows);
		List list = result.getList();
		return ReturnResult.ok(list, result.getTotal(), result.getPages(),result.getPageNum());
		
	}
	/**
	 * 获取主营业务
	 * @Title: getMainBusinesslist 
	 * @author TobyHan
	 * Date : 2017年3月10日 下午2:25:09
	 */
	@RequestMapping("/cooperate/buslist")
	@ResponseBody
	public Map getMainBusinesslist(Integer id){
		List list  = cooperateService.getMainBusinessList(id);
		return ReturnResult.ok(list, (long) list.size(), 1,list.size());
		
	}
	/**
	 * 删除加盟公司
	 * @Title: deleteCooperate 
	 * @author TobyHan
	 * Date : 2017年3月10日 下午4:37:29
	 */
	@RequestMapping("/cooperate/delete")
	@ResponseBody
	public Map deleteCooperate(@RequestParam(value="cid",required=true)Integer cid){
		Map rsMap = null;
		try {
			rsMap  = cooperateService.deleteCooperateCompany(cid);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
		
	}
	
	
}
