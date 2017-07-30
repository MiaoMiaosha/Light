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
import com.light.pojo.LgComment;
import com.light.pojo.LgCooperateCompany;
import com.light.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	CommentService CommentService;
	/**
	 * 获取评论列表
	 * @Title: getCooperatelist 
	 * @author TobyHan
	 * Date : 2017年3月10日 下午3:26:54
	 */
	@RequestMapping("/comment/list")
	@ResponseBody
	public Map getCooperatelist(LgComment lgComment,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows){
		PageInfo result = CommentService.getCommentList(lgComment, page, rows);
		List list = result.getList();
		return ReturnResult.ok(list, result.getTotal(), result.getPages(),result.getPageNum());
		
	}
	/**
	 * 发表评论
	 * @Title: publishCooperate 
	 * @author TobyHan
	 * Date : 2017年3月10日 下午3:28:47
	 */
	@RequestMapping("/comment/add")
	@ResponseBody
	Map addComment(LgComment lgComment){
		Map rsMap = null;
		try {
			rsMap = CommentService.addComment(lgComment);
		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除评论
	 * @Title: deleteComment 
	 * @author TobyHan
	 * Date : 2017年3月10日 下午3:29:49
	 */
	@RequestMapping("/comment/delete")
	@ResponseBody
	Map deleteComment(Integer id){
		Map rsMap = null;
		try {
			rsMap = CommentService.deleteComment(id);
		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
}
