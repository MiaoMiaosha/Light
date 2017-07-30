package com.light.mapper.custom;

import com.light.dto.WXIdAndSecret;

public interface LgPublicMapper {

	/**
	 * 查找最后插入的id
	 * @Title: selectLastInsertId 
	 * @author TobyHan
	 * Date : 2017年3月7日 下午6:26:03
	 */
	Integer selectLastInsertId();
	
	String getWXUserNameById(Integer userId);
	
	String getWXUserHeadImg(Integer userId);
	
	/**
	 * 根据配置名，获取相关值
	 * @Title: getValueByConfigName 
	 * @author TobyHan
	 * Date : 2017年3月24日 下午2:28:33
	 */
	String getValueByConfigName(String name);
	//获取微信相关配置
	String getAppid(Integer id);
	String getAppSecret(Integer id);
	WXIdAndSecret getAppIdAndAppSecret(Integer id);
}
