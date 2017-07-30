package com.light.service;

import java.util.List;
import java.util.Map;

import com.light.dto.QNInfo;
import com.light.dto.WXIdAndSecret;
import com.light.exception.ServiceException;
import com.light.pojo.LgIntroduce;

public interface ConfigService {

	/**
	 * 获取首页相关配置
	 * @Title: getIndexSetting 
	 * @author TobyHan
	 * Date : 2017年3月8日 下午1:57:52
	 */
	Map getIndexSetting();
	/**
	 * 获取招租图片
	 * @Title: getBusPic 
	 * @author TobyHan
	 * Date : 2017年3月8日 下午2:30:25
	 */
	Map getBusPic();
	/**
	 * 设置首页banner1 banner2
	 * type: 1-banner1 2-banner2 3-招租图片
	 * @Title: setIndexSetting 
	 * @author TobyHan
	 * Date : 2017年3月9日 下午5:13:37
	 */
	Map setIndexSetting(Integer type,String urlKey,String adLink) throws ServiceException;
	/**
	 * 设置首页小图
	 * @Title: setSmallPic 
	 * @author TobyHan
	 * Date : 2017年3月9日 下午5:30:06
	 */
	Map setSmallPic(Integer num,String urlKey) throws ServiceException;
	/**
	 * 设置招租图片
	 * @Title: setZhaoZuPics 
	 * @author TobyHan
	 * Date : 2017年3月14日 下午6:47:51
	 */
	Map setZhaoZuPics(Integer num,String urlKey) throws ServiceException;
	/**
	 * 设置置顶
	 * @Title: setTop 
	 * @author TobyHan
	 * Date : 2017年3月15日 下午8:25:17
	 */
	Map setTop(Integer type, Integer id) throws ServiceException;
	/**
	 * 设置公司介绍
	 * @Title: setIntro 
	 * @author TobyHan
	 * Date : 2017年3月30日 下午5:40:15
	 */
	Map setIntro(LgIntroduce lgIntroduce) throws ServiceException;
	/**
	 * 获取公司介绍
	 * @Title: getIntro 
	 * @author TobyHan
	 * Date : 2017年3月30日 下午5:45:49
	 */
	List getIntro(Integer id) throws ServiceException;
	/**
	 * 通过名字获取配置值
	 * @Title: getConfigValueByName 
	 * @author TobyHan
	 * Date : 2017年3月24日 下午2:25:02
	 */
	String getConfigValueByName(String name) throws ServiceException;
	/**
	 * 设置config值
	 * @Title: setConfigValueByName 
	 * @author TobyHan
	 * Date : 2017年4月7日 上午10:03:40
	 */
	void setConfigValueByName(String name,String value) throws ServiceException;
	
	//获取域名
	String getDomain() throws ServiceException;
	//获取前台配置目录
	String getIndexDir() throws ServiceException;
	//获取微信配置
	String getAppid() throws ServiceException;
	String getAppsecret() throws ServiceException;
	WXIdAndSecret getIdAndSecret() throws ServiceException;
	//获取七牛配置
	QNInfo getQninfo() throws ServiceException; 
	
}
