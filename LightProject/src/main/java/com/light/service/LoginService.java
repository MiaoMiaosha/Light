package com.light.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgLoginUser;

public interface LoginService {

	public Map validUserInfo(String username,String password,
			Integer roleId,String sessionId);
	String getLoginUserOpenId(String code,String state) throws ServiceException;
	/**
	 * 退出登录
	 * @Title: logOut 
	 * @author TobyHan
	 * Date : 2017年3月14日 下午4:17:22
	 */
	Map logOut(Integer type, String openId) throws ServiceException;
	/**
	 * 添加会计或管理员账号
	 * @param type
	 * @param lgLoginUser
	 * @return
	 */
	Map addAccount(LgLoginUser lgLoginUser) throws ServiceException;
	/**
	 * 编辑账号密码
	 * @param type
	 * @param lgLoginUser
	 * @return
	 * @throws ServiceException
	 */
	Map editAccount(LgLoginUser lgLoginUser) throws ServiceException;
	/**
	 * 获取账号列表
	 * @param lgLoginUser
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<?> getLoginUserList(LgLoginUser lgLoginUser,Integer page,Integer rows);
	/**
	 * 根据type和openId获取账户信息，type，1-员工；2-客户
	 * @param type
	 * @param openId
	 * @return
	 * @throws ServiceException
	 */
	Map getAccountDetailByOpenId(Integer type,String openId) throws ServiceException;
	
	/**
	 * 验证用户登录新街口
	 * @Title: validUserInfoNew 
	 * @author TobyHan
	 * Date : 2017年3月27日 上午10:12:01
	 */
	public Map validUserInfoNew(String username,String password) throws ServiceException;
	
	/**
	 * PC登录长轮询
	 * @Title: getPcLoginStatus 
	 * @author TobyHan
	 * Date : 2017年4月5日 下午2:30:57
	 */
	//Map getPcLoginStatus(String uid) throws ServiceException;
	
}
