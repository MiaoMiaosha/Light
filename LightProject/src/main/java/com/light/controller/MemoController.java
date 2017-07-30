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
import com.light.pojo.LgMemo;
import com.light.service.MemoService;

@Controller
public class MemoController {

	@Autowired
	MemoService MemoService;
	/**
	 * 获取备忘录列表
	 * @Title: getCaselist 
	 * @author TobyHan
	 * Date : 2017年3月20日 下午11:37:21
	 */
	@RequestMapping("/admin/memo/list")
	@ResponseBody
	public Map getCaselist(LgMemo lgMemo,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			Integer startTime, Integer endTime){
		PageInfo result = MemoService.getMemoList(lgMemo, page, rows);
		List list = result.getList();
			return ReturnResult.ok(list, result.getTotal(), result.getPages(),result.getPageNum());
		
	}
	/**
	 * 新增备忘记录
	 * @Title: publishMemo 
	 * @author TobyHan
	 * Date : 2017年3月20日 下午11:38:16
	 */
	@RequestMapping("/admin/memo/add")
	@ResponseBody
	public Map publishMemo(LgMemo lgMemo){
		Map rsMap = null;
		try {
			 rsMap =  MemoService.addNewMemo(lgMemo);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 修改备忘
	 * @Title: editMemo 
	 * @author TobyHan
	 * Date : 2017年3月20日 下午11:39:14
	 */
	@RequestMapping("/admin/memo/edit")
	@ResponseBody
	public Map editMemo(LgMemo lgMemo){
		Map rsMap = null;
		try {
			 rsMap =  MemoService.editMemo(lgMemo);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除memo
	 * @Title: deleteMemo 
	 * @author TobyHan
	 * Date : 2017年3月20日 下午11:40:14
	 */
	@RequestMapping("/admin/memo/delete")
	@ResponseBody
	public Map deleteMemo(@RequestParam(value="id",required=true)Integer id){
		Map rsMap = null;
		try {
			 rsMap =  MemoService.deleteMemo(id);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
}
