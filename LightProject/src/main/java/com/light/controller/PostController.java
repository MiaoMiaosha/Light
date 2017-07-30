package com.light.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.pojo.LgCase;
import com.light.pojo.LgPost;
import com.light.pojo.LgPostComment;
import com.light.service.PostService;
/**
 * 帖子解答
 * @ClassName: PostController 
 * @author TobyHan
 * @date 2017年3月2日 下午9:08:37 
 *
 */
@Controller
public class PostController {
	
	@Autowired PostService PostService;

	/**
	 * 获取帖子列表
	 * @author TobyHan
	 * Date : 2017年3月5日 下午2:37:04
	 * return Map
	 */
	@RequestMapping("/admin/post/list")
	@ResponseBody
	public Map getPostlist(LgPost lgPost,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows){
		PageInfo result = PostService.getPostList(lgPost, page, rows);
		List list = result.getList();
			return ReturnResult.ok(list, result.getTotal(), result.getPages(),result.getPageNum());
	}
	/**
	 * 发布图面
	 * @author TobyHan
	 * Date : 2017年3月5日 下午2:38:10
	 * return Map
	 */
	@RequestMapping("/admin/post/commit")
	@ResponseBody
	public Map publishPost(LgPost lgPost,
			@RequestParam(value="employeeIds",required=true) String employeeIds,
			HttpSession session){
		Map rsMap = null;
		try {
			Integer userId = (Integer) session.getAttribute("userId");
			 rsMap =  PostService.addPost(lgPost,employeeIds);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取帖子评论列表
	 * @author TobyHan
	 * Date : 2017年3月5日 下午2:39:28
	 * return Map
	 */
	@RequestMapping("/admin/pcomment/list")
	@ResponseBody
	public Map getPostCommentlist(LgPostComment lgPostComment,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows){
		PageInfo result = PostService.getPostCommentList(lgPostComment, page, rows);
		List list = result.getList();
			return ReturnResult.ok(list, result.getTotal(), result.getPages(),result.getPageNum());
		
	}
	/**
	 * 回复帖子
	 * @author TobyHan
	 * Date : 2017年3月5日 下午2:50:37
	 * return Map
	 */
	@RequestMapping("/admin/pcomment/reply")
	@ResponseBody
	public Map replyPost(LgPostComment lgPostComment,
			@RequestParam(value="type",defaultValue="0")Integer type,
			String employeeIds,String openId){
		Map rsMap = null;
		try {
			  /*if(PostService.replyPost(lgPostComment)){
				  //回复成功
				  rsMap = ReturnResult.ok();
				  Integer  postId = lgPostComment.getPostId();
				  String content = lgPostComment.getContent();
				  if(type == 1 || type == 2)
				  PostService.sendCommentMentionToAllEmployee(postId, content,type,employeeIds);
			  }*/
			PostService.replyPost(lgPostComment, type, employeeIds);
			rsMap = ReturnResult.ok();
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除帖子评论
	 * @author TobyHan
	 * Date : 2017年3月5日 下午2:51:30
	 * return Map
	 */
	@RequestMapping("/admin/pcomment/delete")
	@ResponseBody
	public Map deletePostComment(Integer pcid){
		Map rsMap = null;
		try {
			 rsMap =  PostService.deletePostComment(pcid);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取我的解答列表
	 * @Title: getMyPostList 
	 * @author TobyHan
	 * Date : 2017年3月6日 下午2:07:06
	 */
	@RequestMapping("/admin/post/mylist")
	@ResponseBody
	public Map getMyPostList(@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			@RequestParam(value="type",required=true)Integer type,
			HttpSession session){
		Map rsMap = null;
		try {
			Integer loginId = (Integer) session.getAttribute("userId");
			 PageInfo<?> result = PostService.getMyPost(page, rows, loginId, type);
			 List list = result.getList();
			return ReturnResult.ok(list, result.getTotal(), result.getPages(),result.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
}
