package com.light.mapper.custom;

import java.util.List;
import java.util.Map;

import com.light.dto.CommentSqlBean;
import com.light.dto.LastAtOutputbean;
import com.light.pojo.LgPost;
import com.light.pojo.LgPostComment;

/**
 * 帖子自定义查询
 * @ClassName: LgPostCustomMapper 
 * @author TobyHan
 * @date 2017年3月2日 下午8:45:31 
 *
 */
public interface LgPostCustomMapper {

	/**
	 * 根据帖子id获取评论数
	 * @Title: getCountCommemtWithPostId 
	 * @author TobyHan
	 * Date : 2017年3月2日 下午8:46:28
	 */
	Integer getCountCommemtWithPostId(Integer postId);
	/**
	 * 根据项目id获取帖子列表
	 * @Title: getPostListByProjectId 
	 * @author TobyHan
	 * Date : 2017年3月4日 上午9:38:10
	 */
	List<LgPostComment> getPostListByProjectId(Integer projectId);
	
	/**
	 * 根据帖子评论id获取登录用户名
	 * @Title: getPostLoginUserNameByPcid 
	 * @author TobyHan
	 * Date : 2017年3月4日 上午11:06:18
	 */
	String getPostLoginUserNameByPcid(Integer pcid);
	
	/**
	 * 获取某一评论下所有子评论
	 * @Title: getSonCommentByPcid 
	 * @author TobyHan
	 * Date : 2017年3月4日 上午11:12:01
	 */
	List<Integer> getSonCommentByPcid(Integer pcid);
	/**
	 * 查找用户相关的最新"@"帖子列表
	 * @Title: getLastATPostList 
	 * @author TobyHan
	 * Date : 2017年3月6日 上午11:17:01
	 */
	List<LgPost> getLastATPostList(Integer loginUserId); 
	/**
	 * 查找用户相关的最新"评论"帖子列表
	 * @Title: getLastCommentPostList 
	 * @author TobyHan
	 * Date : 2017年3月6日 上午11:12:11
	 */
	List<LastAtOutputbean> getLastCommentPostList(Integer loginUserId); 

	/**
	 * 获取评论返回数据
	 * @Title: getCommentBean 
	 * @author TobyHan
	 * Date : 2017年3月9日 上午10:07:56
	 */
	CommentSqlBean getCommentBean(Integer postId);
	/**
	 * 根据customerId获取用户openId列表
	 * @Title: getCusListByCustomerId 
	 * @author TobyHan
	 * Date : 2017年3月13日 下午2:05:20
	 */
	List<String> getCusListByCustomerId(Integer customerId);
	
	/**
	 * 获取客户 最近发布的帖子
	 * @Title: getLastPublicPostList 
	 * @author TobyHan
	 * Date : 2017年3月20日 上午10:36:56
	 */
	List<LgPost> getLastPublicPostList(Integer loginUserId);
}
