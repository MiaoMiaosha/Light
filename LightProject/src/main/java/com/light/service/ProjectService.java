package com.light.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.exception.ServiceException;
import com.light.pojo.LgPost;
import com.light.pojo.LgProject;
import com.light.pojo.LgProjectProcess;

/**
 * 工程表
 * @ClassName: ProjectService 
 * @author TobyHan
 * @date 2017年2月15日 下午4:59:03 
 *
 */
public interface ProjectService {

	Map commitProject(LgProject lgProject,Integer userId,String contractIds);

	PageInfo<?> getProjectList(LgProject lgProject,Integer page, Integer rows,Integer startTime,
			Integer endTime,Integer employeeId);
	
	Map modifyProjectById(LgProject project, String contractIds);

	Map deleteProjectById(Integer sid);
	/**
	 * 验证工程表市场名是否唯一
	 * @Title: validProjectMarketName 
	 * @author TobyHan
	 * Date : 2017年3月1日 下午4:11:58
	 */
	public boolean validProjectMarketName(String marketName);
	/**
	 * 根据员工id，获取专属工程列表
	 * @Title: getProjectListByEmployeeId 
	 * @author TobyHan
	 * Date : 2017年3月1日 下午5:24:30
	 */
	PageInfo getProjectListByEmployeeId(Integer userId,LgProject lgProject,
			Integer page, Integer rows);
	/**
	 * 根据工程id获取帖子列表
	 * @Title: getPostListByProjectId 
	 * @author TobyHan
	 * Date : 2017年3月2日 下午8:36:16
	 */
	PageInfo getPostListByProjectId(LgPost lgPost, Integer projectId,Integer page,Integer rows) throws ServiceException;
	/**
	 * 根据projectId返回地址详细信息
	 * @author TobyHan
	 * Date : 2017年3月5日 下午2:05:37
	 * return Map
	 */
	Map getProjectMarketAddress(Integer projectId);
	/**
	 * 修改工程进度
	 * @author TobyHan
	 * Date : 2017年3月5日 下午3:50:28
	 * return Map
	 */
	Map updateProcessByProceeId(Integer type, Integer projectId,Integer processId);
	/**
	 * 获取工程进度列表
	 * @author TobyHan
	 * Date : 2017年3月5日 下午3:55:47
	 * return List
	 */
	List getProjectProcessList(LgProjectProcess process);
	/**
	 * 根据loginUserId获取工程详情
	 * @Title: getProjectListWithExtraWithLoginUserId 
	 * @author TobyHan
	 * Date : 2017年3月8日 上午10:11:48
	 */
	Map getProjectListWithExtraWithLoginUserId(Integer loginUserId);
	/**
	 * 获取具体工程进度详情
	 * @Title: getProjectProcessDetail 
	 * @author TobyHan
	 * Date : 2017年3月16日 上午11:49:05
	 */
	List getProjectProcessDetail(Integer projectId);
	
	List getCusProjectList(Integer customerId);
	/**
	 * 获取所有工程市场名称 和 projectId
	 * @Title: getProjectNameList 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午9:42:58
	 */
	List getProjectNameList(String marketName);
}
