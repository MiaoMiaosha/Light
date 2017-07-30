package com.light.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.common.CheckNullUtil;
import com.light.common.ReturnResult;
import com.light.service.FloorService;
/**
 * 摊位管理
 * @ClassName: FloorController 
 * @author TobyHan
 * @date 2017年2月16日 下午11:49:40 
 *
 */
@Controller
public class FloorController {

	@Autowired FloorService floorService;
	
	
	/**
	 * 根据市场获取楼层列表
	 * @Title: getFloorListByMarketId 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午11:22:48
	 */
	@RequestMapping("/floor/market")
	@ResponseBody
	Map getFloorListByMarketId(@RequestParam(value="marketId",required=true)Integer marketId){
		List list = floorService.getFloorListById(marketId);
		if(CheckNullUtil.listNotNull(list))
			return ReturnResult.ok(list, (long) list.size(), 1, list.size());
		return ReturnResult.wrong("楼层列表为空");
	}
}
