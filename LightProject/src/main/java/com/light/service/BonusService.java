package com.light.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.exception.ServiceException;
import com.light.pojo.LgBonus;
import com.light.pojo.LgBonusExtra;
import com.light.pojo.LgBonusLevel;

/**
 * 分红
 * @ClassName: BonusService 
 * @author TobyHan
 * @date 2017年2月15日 下午4:45:27 
 *
 */
public interface BonusService {

	/**
	 * 提交分红
	 * @Title: commitBonus 
	 * @author TobyHan
	 * Date : 2017年2月15日 下午4:46:13
	 */
	Map commitBonus(LgBonus lgBonus,Integer userId,String bonusUsers);
	/**
	 * 获取分红列表
	 * @Title: getBonusList 
	 * @author TobyHan
	 * Date : 2017年2月15日 下午4:47:33
	 */
	PageInfo<?> getBonusList(LgBonus lgBonus,Integer page, Integer rows, Integer startTime, Integer endTime
			,Integer employeeId);
	/**
	 * 根据id删除分红记录
	 * @Title: deleteBonusById 
	 * @author TobyHan
	 * Date : 2017年2月15日 下午4:51:07
	 */
	Map deleteBonusById(Integer id);
	
	/**
	 * 获取额外信息
	 * @Title: getExtraInfoList 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午11:48:50
	 */
	List getExtraInfoList(LgBonusExtra extra);
	/**
	 * 确认分红信息
	 * @Title: confirmBonus 
	 * @author TobyHan
	 * Date : 2017年2月21日 下午2:57:14
	 */
	Map confirmBonus(Integer id,Integer operateId,Integer status);
	
	/**
	 * 获取分红级别
	 * @Title: getBonusLevel 
	 * @author TobyHan
	 * Date : 2017年2月23日 下午4:45:53
	 */
	public Map getBonusLevel(String bonusIds);
	
	/**
	 * 修改分红详情，已审核的分红不能修改
	 * @Title: editBonus 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午3:11:10
	 */
	Map editBonus(LgBonus lgBonus,String bonusIds);
	/**
	 * 修改分红类型
	 * @Title: editBonusExtra 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午9:14:41
	 */
	Map editBonusExtra(LgBonusExtra extra) throws ServiceException;
	/**
	 * 删除分红类型
	 * @Title: deleteBonusExtra 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午10:21:40
	 */
	Map deleteBonusExtra(Integer id) throws ServiceException;
	/**
	 * 增加分红类型
	 * @Title: addBonusExtra 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午10:46:11
	 */
	Map addBonusExtra(LgBonusExtra lgBonusExtra) throws ServiceException;
	
	/**
	 * 获取分红比例
	 * @Title: getLevelList 
	 * @author TobyHan
	 * Date : 2017年4月10日 下午1:08:02
	 */
	List<LgBonusLevel> getLevelList(LgBonusLevel level);
	
	/**
	 * 添加分红比例
	 * @Title: addBonusLevel 
	 * @author TobyHan
	 * Date : 2017年4月10日 下午1:15:03
	 */
	void addBonusLevel(LgBonusLevel level);
	/**
	 * 编辑分红比例
	 * @Title: editBonusLevel 
	 * @author TobyHan
	 * Date : 2017年4月10日 下午1:17:17
	 */
	void editBonusLevel(LgBonusLevel level);
	/**
	 * 删除分红比例
	 * @Title: deleteBonusLevel 
	 * @author TobyHan
	 * Date : 2017年4月10日 下午1:17:25
	 */
	void deleteBonusLevel(Integer id);

	/**
	 * 获取当前时间内，已审核金额
	 * @Title: getCurTimeMoney 
	 * @author TobyHan
	 * Date : 2017年4月14日 上午9:50:54
	 */
	Integer getCurTimeMoney(Integer employeeId,Integer startTime,Integer endTime);
}
