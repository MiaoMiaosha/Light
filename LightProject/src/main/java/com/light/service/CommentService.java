package com.light.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.pojo.LgComment;

public interface CommentService {

	/**
	 * 获取评论列表
	 * @Title: getCommentList 
	 * @author TobyHan
	 * Date : 2017年3月10日 下午2:30:27
	 */
	PageInfo<?> getCommentList(LgComment lgComment,Integer page,Integer rows);
	/**
	 * 发表评论
	 * @Title: addComment 
	 * @author TobyHan
	 * Date : 2017年3月10日 下午2:30:39
	 */
	Map addComment(LgComment lgComment);
	/**
	 * 物理删除评论
	 * @Title: deleteComment 
	 * @author TobyHan
	 * Date : 2017年3月10日 下午2:31:01
	 */
	Map deleteComment(Integer id);

}
