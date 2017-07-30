package com.light.service;

import java.util.List;
import java.util.Map;

import com.light.common.WebAccessTokenReturn;
import com.light.exception.ServiceException;
import com.light.pojo.LgWxUser;

/**
 * 微信服务
 * @ClassName: WechatService 
 * @author TobyHan
 * @date 2017年2月17日 上午6:51:35 
 *
 */
public interface WechatService {

	/**
	 * 获取网页授权access_token json对象
	 * 只能用一次
	 * @Title: getAccessTokenByCode 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午7:07:27
	 */
	WebAccessTokenReturn getAccessTokenByCode(String code) throws ServiceException;
	/**
	 * 拉取用户信息并储存
	 * @Title: getUserInfoAndSave 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午7:09:09
	 */
	Integer getUserInfoAndSave(WebAccessTokenReturn json) throws ServiceException;
	/**
	 * 获取基本access_token
	 * @Title: getBasicAccessToken 
	 * @author TobyHan
	 * Date : 2017年3月8日 下午7:39:32
	 */
	Map getBasicAccessToken(LgWxUser wxUser) throws ServiceException;
	/**
	 * 获取当前可用微信公众号
	 * @Title: getCurrentWxUser 
	 * @author TobyHan
	 * Date : 2017年3月8日 下午7:58:29
	 */
	LgWxUser getCurrentWxUser() throws ServiceException;
	/**
	 * 发送评论提醒模板消息给员工
	 * @Title: sendCommentTemplateToUser 
	 * @author TobyHan
	 * Date : 2017年3月8日 下午9:16:26
	 */
	Boolean sendCommentTemplateToUser(List<String> userList,String market,String title,String content,String url);
	/**
	 * 根据id获取公众号对象
	 * @Title: getCuWxUserById 
	 * @author TobyHan
	 * Date : 2017年3月24日 下午3:07:33
	 */
	LgWxUser getCuWxUserById(Integer id) throws ServiceException;
	/**
	 * 获得有效jsapi_ticket
	 * @Title: getEffectiveJsapiTicket 
	 * @author TobyHan
	 * Date : 2017年3月24日 下午5:11:35
	 */
	String getEffectiveJsapiTicket() throws ServiceException;
	
	/**
	 * 访客来咨询通知--客户发布图面
	 * @Title: sendNormalCommentTemplateToUser 
	 * @author TobyHan
	 * Date : 2017年4月5日 下午7:16:32
	 */
	void customerPublishPost(List<String> userList,String market,String title,String content,String url,String username,String remark);
	/**
	 * 咨询回复提醒--员工或客户回复
	 * @Title: msgForReply 
	 * @author TobyHan
	 * Date : 2017年4月5日 下午7:32:24
	 */
	void msgForReply(List<String> userList, String url,String replyContent,String username,String replyTime,String askTime,String title,String content,String remark);
	/**
	 * 申请uid
	 * @Title addUid
	 * @author TobyHan
	 * Date : 2017年5月7日 下午2:23:28
	 * @return
	 * @throws ServiceException
	 */
	String addUid() throws ServiceException;
	/**
	 * 判断uid状态
	 * @Title queryUidSta
	 * @author TobyHan
	 * Date : 2017年5月7日 下午2:55:20
	 * @param uid
	 * @return
	 * @throws ServiceException
	 */
	Map queryUidSta(String uid) throws ServiceException;
	/**
	 * 更改uid登录状态
	 * @Title updateLoginUid
	 * @author TobyHan
	 * Date : 2017年5月7日 下午1:49:09
	 * @param uid
	 * @throws ServiceException
	 */
	void updateLoginUid(String uid,Integer userId) throws ServiceException;
	
}
