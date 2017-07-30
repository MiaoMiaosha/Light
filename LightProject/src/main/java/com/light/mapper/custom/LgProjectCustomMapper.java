package com.light.mapper.custom;

import java.util.List;
import java.util.Map;

import com.light.dto.ProjectMarketnameBean;
import com.light.pojo.LgProject;

/**
 * 工程自定义mapper
 * @ClassName: LgProjectCustomMapper 
 * @author TobyHan
 * @date 2017年2月27日 上午10:25:23 
 *
 */
public interface LgProjectCustomMapper {

	/**
	 * 根据工程id，获取工程名
	 * @Title: getProjectMarketName 
	 * @author TobyHan
	 * Date : 2017年2月27日 上午10:26:13
	 */
	String getProjectMarketName(Integer pid);
	/**
	 * 查找当前存在工程市场名称个数
	 * @Title: getMarketNameByName 
	 * @author TobyHan
	 * Date : 2017年3月1日 下午3:52:31
	 */
	Integer getMarketNameByName(String name);
	/**
	 * 获取员工下属工程
	 * @author TobyHan
	 * Date : 2017年3月5日 下午4:00:21
	 * return List<LgProject>
	 */
	List<LgProject> getProjectListByEmployeeId(Map<String,Integer> paramMap);
	/**
	 * 根据工程id获取工程进度
	 * @author TobyHan
	 * Date : 2017年3月5日 下午4:01:16
	 * return Integer
	 */
	Integer getProjectProcessByProjectId(Integer projectId);
	/**
	 * 根据阶段id，获取阶段名
	 * @Title: getProcessNameById 
	 * @author TobyHan
	 * Date : 2017年3月6日 下午4:52:26
	 */
	String getProcessNameById(Integer id);
	/**
	 * 获取工程与员工所有的映射数
	 * @Title: getCountFromEPByPorjectId 
	 * @author TobyHan
	 * Date : 2017年3月15日 下午4:30:01
	 */
	Integer getCountFromEPByPorjectId(Integer projectId);
	
	/**
	 * 根据市场名，模糊搜索 projectId
	 * @Title: getProjectIdByMarketName 
	 * @author TobyHan
	 * Date : 2017年3月20日 下午8:33:28
	 */
	Integer getProjectIdByMarketName(String marketName);
	/**
	 * 获得所有的工程市场名称list
	 * @Title: getAllProjectMarketName 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午9:47:28
	 */
	List<ProjectMarketnameBean> getAllProjectMarketName(Map paramMap);
}
