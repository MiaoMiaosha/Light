package com.light.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgCase;

/**
 * 案例相关服务service
 * @ClassName: CaseService 
 * @author TobyHan
 * @date 2017年2月14日 上午11:09:18 
 *
 */
public interface CaseService {

	/**
	 * 根据条件获取案例列表
	 * @Title: getCaseList 
	 * @author TobyHan
	 * Date : 2017年2月14日 上午11:11:11
	 */
	PageInfo getCaseList(LgCase lgCase,Integer page,Integer rows,
			Integer startTime,Integer endTime) throws ServiceException;
	/**
	 * 发布案例
	 * @Title: publishCase 
	 * @author TobyHan
	 * Date : 2017年2月14日 上午11:14:39
	 */
	Map addPublishCase(LgCase lgCase) throws ServiceException;
	/**
	 * 修改案例相关
	 * @Title: editCase 
	 * @author TobyHan
	 * Date : 2017年2月16日 上午10:54:24
	 */
	Map editCase(LgCase lgCase) throws ServiceException;
	/**
	 * 修改案例申请状态
	 * @Title: editCaseApply 
	 * @author TobyHan
	 * Date : 2017年2月16日 上午11:51:09
	 */
	Map editCaseApply(Integer applyId,Integer caseId, Integer status) throws ServiceException;
	/**
	 * 获取案例详情
	 * @Title: getCaseDetail 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午4:51:58
	 */
	LgCase getCaseDetail(Integer caseId) throws ServiceException;
}
