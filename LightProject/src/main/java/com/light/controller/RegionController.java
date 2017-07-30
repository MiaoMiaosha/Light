package com.light.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 地区
 * @ClassName: RegionController 
 * @author TobyHan
 * @date 2017年2月16日 下午10:16:51 
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.common.CheckNullUtil;
import com.light.common.ReturnResult;
import com.light.service.RegionService;
@Controller
public class RegionController {

	@Autowired
	RegionService regionService;
	
	/**
	 * 根据parentId获取地区列表
	 * @Title: getListByParentId 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午10:34:00
	 */
	@RequestMapping("/region/parent")
	@ResponseBody
	 Map getListByParentId(@RequestParam(value="parentId",required=true)Integer parentId){
		 Map rsMap = null;
		 try {
			List list = regionService.getRegionListByParentId(parentId);
			if(CheckNullUtil.listNotNull(list)){
				rsMap = ReturnResult.ok(list);
			}else
				rsMap = ReturnResult.wrong("列表为空");
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		 return rsMap;
	 }
}
