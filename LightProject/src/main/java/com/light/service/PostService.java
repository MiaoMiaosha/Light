package com.light.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgPost;
import com.light.pojo.LgPostComment;

public interface PostService {

	/**
	 * 获取帖子列表
	 * @Title: getPostList 
	 * @author TobyHan
	 * Date : 2017年3月4日 上午10:21:00
	 */
	PageInfo<?> getPostList(LgPost lgPost,Integer page,Integer rows) throws ServiceException;
	/**
	 * 发布图面
	 * @author TobyHan
	 * Date : 2017年3月5日 下午2:03:10
	 * return Map
	 */
	Map addPost(LgPost lgPost,String employeeIds) throws ServiceException;
	
	
	
	
	/**
	 * 获取帖子评论列表
	 * @Title: getPostCommentList 
	 * @author TobyHan
	 * Date : 2017年3月2日 下午9:10:42
	 */
	PageInfo<?> getPostCommentList(LgPostComment lgPostComment, Integer page, Integer rows)
	  throws ServiceException;
	/**
	 * 回复帖子
	 * @Title: replyPost 
	 * @author TobyHan
	 * Date : 2017年3月2日 下午9:22:20
	 */
	boolean replyPost(LgPostComment lgPostComment,Integer type,String employeeIds) throws ServiceException;
	/**
	 * 删除帖子
	 * @Title: deletePost 
	 * @author TobyHan
	 * Date : 2017年3月2日 下午9:22:57
	 */
	 Map deletePostComment(Integer pcid) throws ServiceException;
	 /**
	  * 获取我的解答列表
	  * @Title: getMyPost 
	  * @author TobyHan
	  * Date : 2017年3月6日 上午11:54:42
	  */
	 PageInfo<?> getMyPost(Integer page,Integer rows, Integer loginId,Integer type) throws ServiceException;
	 /**
	  * 给所有员工发送评论模板消息提醒
	  * @Title: sendCommentMentionToAllEmployee 
	  * @author TobyHan
	  * Date : 2017年3月9日 上午8:36:36
	  */
	 void sendCommentMentionToAllEmployee(Integer postId, String content,Integer type,String employeeIds);
	 /**
	  * 访客来咨询通知--客户发布图面
	  * @Title: getOpenIdListStr 
	  * @author TobyHan
	  * Date : 2017年4月5日 下午8:30:38
	  */
	 public void customerPublishTemplate(Integer postId, String content) throws ServiceException;

	 
}
