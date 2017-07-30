package com.light.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 查询统计
 * @ClassName: TotalCountController 
 * @author TobyHan
 * @date 2017年2月27日 下午8:32:00 
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.service.TotalCountService;
@Controller
public class TotalCountController {

	@Autowired TotalCountService TotalCountService;
	/**
	 * 获取员工列表，根据type
	 * @Title: getUserLevelList 
	 * @author TobyHan
	 * Date : 2017年2月28日 下午3:47:25
	 */
	@RequestMapping("/total/userlist")
	@ResponseBody
	Map getUserLevelList(@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer type,
			String eids,
			Integer startTime, Integer endTime){
		//PageInfo result = TotalCountService.getAllEmployeeList(type,page, rows, startTime, endTime);
		Map result = TotalCountService.getTotalResult(page, rows,eids);

		//List list = result.getList();
		//	return ReturnResult.ok(list, result.getTotal(), result.getPages(),result.getPageNum());
		return result;
	}
	@RequestMapping("/admin/total/staffinfo")
	@ResponseBody
	Map getTotalByEmployeeId(Integer employeeId,Integer startTime,Integer endTime){
		Map rsMap = null;
		try {
			rsMap =TotalCountService.getStaffDetail(employeeId, startTime, endTime);

		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
}
