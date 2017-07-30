package com.light.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 工程相关
 * @ClassName: ProjectController 
 * @author TobyHan
 * @date 2017年2月21日 上午7:13:27 
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ConstantVal;
import com.light.common.ReturnResult;
import com.light.pojo.LgPost;
import com.light.pojo.LgProject;
import com.light.pojo.LgProjectProcess;
import com.light.service.ProjectService;
@Controller
public class ProjectController {

	@Autowired ProjectService ProjectService;
	/**
	 * 提交工程
	 * @Title: commitProject 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午8:09:12
	 */
	@RequestMapping("/admin/project/commit")
	@ResponseBody
	Map commitProject(LgProject lgProject,
			@RequestParam(value="contractIds",required=true)String contractIds,
			HttpSession session){
		Map rsMap = null;
		try {
			Integer userId = (Integer) session.getAttribute("userId");
			rsMap = ProjectService.commitProject(lgProject,userId,contractIds);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 获取工程列表
	 * @Title: getProjectList 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午7:23:05
	 */
	@RequestMapping(ConstantVal.VALID_PRE+"/project/list")
	@ResponseBody
	Map getProjectList(LgProject lgProject,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="4000")Integer rows,
			Integer startTime, Integer endTime,Integer employeeId){
		Map rsMap = null;
		try {
			PageInfo<?> info = ProjectService.getProjectList(lgProject, page, rows, startTime,endTime,employeeId);
			rsMap = ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 删除工程
	 * @Title: deleteProject 
	 * @author TobyHan
	 * Date : 2017年2月24日 下午2:58:23
	 */
	@RequestMapping("/admin/project/delete")
	@ResponseBody
	Map deleteProject(@RequestParam(value="pid",required=true)Integer pid){
		Map rsMap = null;
		try {
			rsMap = ProjectService.deleteProjectById(pid);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 编辑工程
	 * @Title: editProject 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午3:34:16
	 */
	@RequestMapping("/admin/project/edit")
	@ResponseBody
	Map editProject(LgProject lgProject,String contractIds){
	//Map editProject(HttpServletRequest request){
		Map rsMap = null;
		try {
			rsMap = ProjectService.modifyProjectById(lgProject,contractIds);
			//System.out.println(request.getRequestURI());
/*			Map map = new HashMap();
	        Enumeration paramNames = request.getParameterNames();
	        while (paramNames.hasMoreElements()) {
	            String paramName = (String) paramNames.nextElement();
	            String[] paramValues = request.getParameterValues(paramName);
	            if (paramValues.length == 1) {
	                String paramValue = paramValues[0];
	                if (paramValue.length() != 0) {
	                    map.put(paramName, paramValue);
	                }
	            }
	        }
	        Set<Map.Entry<String, String>> set = map.entrySet();
	        System.out.println("------------------------------");
	        for (Map.Entry entry : set) {
	            System.out.println(entry.getKey() + ":" + entry.getValue());
	        }
	        System.out.println("------------------------------");*/
			
			
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 验证市场名是否唯一
	 * @Title: validProjectMarketName 
	 * @author TobyHan
	 * Date : 2017年3月1日 下午4:14:21
	 */
	@RequestMapping("/admin/project/valid")
	@ResponseBody
	Map validProjectMarketName(@RequestParam(value="marketName",required=true)String marketName){
		Map rsMap = null;
		try {
			rsMap = ReturnResult.ok(ProjectService.validProjectMarketName(marketName));
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取员工相关工程列表
	 * @Title: getEProjectList 
	 * @author TobyHan
	 * Date : 2017年3月1日 下午6:49:16
	 */
	@RequestMapping(ConstantVal.VALID_PRE+"/project/eplist")
	@ResponseBody
	Map getEProjectList(LgProject lgProject,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows,
			@RequestParam(value="userId",required=true)Integer userId,
			Integer startTime, Integer endTime){
		Map rsMap = null;
		try {
			PageInfo<?> info = ProjectService.getProjectListByEmployeeId(userId, lgProject, page, rows);
			rsMap = ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 根据工程id，获取帖子列表
	 * @Title: getPostListByProjectId 
	 * @author TobyHan
	 * Date : 2017年3月2日 下午8:57:34
	 */
	@RequestMapping("/admin/project/postlist")
	@ResponseBody
	Map getPostListByProjectId(LgPost lgPost,
			@RequestParam(value="projectId",required=true)Integer projectId,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows){
		Map rsMap = null;
		try {
			PageInfo<?> info = ProjectService.getPostListByProjectId(lgPost, projectId, page, rows);
			rsMap = ReturnResult.ok(info.getList(), info.getTotal(), page, info.getPageNum());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取工程进度列表
	 * @Title: getPostListByProjectId 
	 * @author TobyHan
	 * Date : 2017年3月6日 上午10:41:55
	 */
	@RequestMapping("/admin/project/processlist")
	@ResponseBody
	Map getPostListByProjectId(LgProjectProcess process,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows){
		Map rsMap = null;
		try {
			List  list = ProjectService.getProjectProcessList(process);
			rsMap = ReturnResult.ok(list, (long) list.size(), page, list.size());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 根据loginUserId获取工程详情
	 * @Title: getProjectDetailByLoginUserId 
	 * @author TobyHan
	 * Date : 2017年3月8日 上午10:27:37
	 */
	@RequestMapping("/admin/project/cusdetail")
	@ResponseBody
	Map getProjectDetailByLoginUserId(HttpSession session){
		Map rsMap = null;
		try {
			Integer loginUserId = (Integer) session.getAttribute("userId");
			rsMap = ProjectService.getProjectListWithExtraWithLoginUserId(loginUserId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 更改工程进度
	 * @Title: editProcessDetail 
	 * @author TobyHan
	 * Date : 2017年3月16日 下午1:04:03
	 */
	@RequestMapping("/admin/project/editprodetail")
	@ResponseBody
	Map editProcessDetail(Integer type,Integer projectId,Integer processId){
		Map rsMap = null;
		try {
			rsMap = ProjectService.updateProcessByProceeId(type, projectId, processId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	/**
	 * 获取工程进度详情
	 * @Title: getProcessDetail 
	 * @author TobyHan
	 * Date : 2017年3月16日 下午12:38:19
	 */
	@RequestMapping("/admin/project/prodetail")
	@ResponseBody
	Map getProcessDetail(Integer projectId){
		Map rsMap = null;
		try {
			List list = ProjectService.getProjectProcessDetail(projectId);
			rsMap = ReturnResult.ok(list, (long) list.size(), 1, list.size());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 根据customerId获取工程列表
	 * @Title: getCusList 
	 * @author TobyHan
	 * Date : 2017年3月20日 上午11:11:02
	 */
	@RequestMapping("/admin/project/getcuslist")
	@ResponseBody
	Map getCusList(@RequestParam(value="customerId",required=true)Integer customerId){
		Map rsMap = null;
		try {
			List list  = ProjectService.getCusProjectList(customerId);
			rsMap = ReturnResult.ok(list, (long) list.size(), 1, list.size());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获得所有市场名称
	 * @Title: getAllMarketNameList 
	 * @author TobyHan
	 * Date : 2017年3月24日 上午9:51:31
	 */
	@RequestMapping("/admin/project/marketname")
	@ResponseBody
	Map getAllMarketNameList(@RequestParam(value="marketName",required=false)String marketName){
		Map rsMap = null;
		try {
			List list  = ProjectService.getProjectNameList(marketName);
			rsMap = ReturnResult.ok(list, (long) list.size(), 1, list.size());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	
}
