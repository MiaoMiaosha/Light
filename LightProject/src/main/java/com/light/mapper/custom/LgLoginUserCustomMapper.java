package com.light.mapper.custom;

public interface LgLoginUserCustomMapper {

	/**
	 * 根据登录人id获取昵称
	 * @Title: getUserNickNameByLoginId 
	 * @author TobyHan
	 * Date : 2017年3月4日 上午10:40:58
	 */
	String getUserNickNameByLoginId(Integer loginId);
	/**
	 * 根据userId获取loginId
	 * @Title: getEmployeeIdByLoginId 
	 * @author TobyHan
	 * Date : 2017年3月6日 下午3:16:39
	 */
	Integer getEmployeeIdByLoginId(Integer userId);
	/**
	 * 根据登录id获取用户头像
	 * @Title: getHeadUrlByLoginId 
	 * @author TobyHan
	 * Date : 2017年3月6日 下午3:51:36
	 */
	String getHeadUrlByLoginId(Integer loginId);
	/**
	 * 根据loginId获取role
	 * @Title: getRoleIdByLoginId 
	 * @author TobyHan
	 * Date : 2017年3月27日 下午5:57:20
	 */
	String getRoleIdByLoginId(Integer loginId);
}
