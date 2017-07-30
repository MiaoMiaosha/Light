package com.light.service;

import java.util.List;
import java.util.Map;

import com.light.exception.ServiceException;
import com.light.pojo.LgStall;
import com.light.pojo.LgStallApply;

/**
 * 摊位管理
 * @ClassName: StallService 
 * @author TobyHan
 * @date 2017年2月16日 下午11:52:09 
 *
 */
public interface StallService {
	/**
	 * 获取市场楼层下所有摊位
	 * @Title: getStallListByFloorId 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午11:53:38
	 */
	List getStallListByFloorId(Integer floorId) throws ServiceException;
	/**
	 * 市场增加摊位
	 * @Title: addNewStall 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午1:00:21
	 */
	Map addNewStall(LgStall lgStall) throws ServiceException;
	/**
	 * 用户申请摊位
	 * @Title: applyForStall 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午4:16:47
	 */
	Map applyForStall(LgStallApply lgApply,Integer userId) throws ServiceException;
	/**
	 * 删除摊位
	 * @Title: deleteStall 
	 * @author TobyHan
	 * Date : 2017年3月21日 上午10:08:15
	 */
	Map deleteStall(Integer stallId) throws ServiceException;
}
