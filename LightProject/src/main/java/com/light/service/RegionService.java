package com.light.service;

import java.util.List;
import java.util.Map;

import com.light.exception.ServiceException;

/**
 * 地区相关服务
 * @ClassName: RegionService 
 * @author TobyHan
 * @date 2017年2月16日 下午10:17:39 
 *
 */
public interface RegionService {

	/**
	 * 根据上级id，获取下级所有列表
	 * @Title: getRegionListByParentId 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午10:19:09
	 */
	List getRegionListByParentId(Integer parentId) throws ServiceException;
}
