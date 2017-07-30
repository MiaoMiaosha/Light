package com.light.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.exception.ServiceException;
import com.light.pojo.LgReimburse;
import com.light.pojo.LgReimburseExtra;

/**
 * 报销
 * @ClassName: ReimburseService 
 * @author TobyHan
 * @date 2017年2月15日 下午4:52:11 
 *
 */
public interface ReimburseService {

	Map commitReimbursement(LgReimburse lgReimburse,Integer userId);

	PageInfo<?> getReimbursementList(LgReimburse lgReimburse,Integer page, Integer rows,
			Integer startTime, Integer endTime);
	
	Map modifyReimburseById(LgReimburse reimburse);

	Map deleteReimburseById(Integer rid);
	
	/**
	 * 获取额外信息
	 * @Title: getExtraList 
	 * @author TobyHan
	 * Date : 2017年2月22日 上午11:58:02
	 */
	List getExtraList(LgReimburseExtra extra);
	/**
	 * 修改额外信息
	 * @Title: editExtra 
	 * @author TobyHan
	 * Date : 2017年3月28日 下午4:48:23
	 */
	Map editExtra(LgReimburseExtra extra) throws ServiceException;
	/**
	 * 增加额外信息
	 * @Title: addExtra 
	 * @author TobyHan
	 * Date : 2017年3月28日 下午4:48:44
	 */
	Map addExtra(LgReimburseExtra extra) throws ServiceException;
	/**
	 * 删除类型
	 * @Title: deleteExtra 
	 * @author TobyHan
	 * Date : 2017年3月28日 下午4:49:08
	 */
	Map deleteExtra(Integer id) throws ServiceException;

}
