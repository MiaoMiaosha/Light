package com.light.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.ReturnResult;
import com.light.dto.ProjectMarketnameBean;
import com.light.exception.ServiceException;
import com.light.mapper.LgEmployeeProjectMapper;
import com.light.mapper.LgLoginCuslistMapper;
import com.light.mapper.LgLoginUserMapper;
import com.light.mapper.LgMarketMapper;
import com.light.mapper.LgPostMapper;
import com.light.mapper.LgProjectMapper;
import com.light.mapper.LgProjectProcessMapper;
import com.light.mapper.LgRegionMapper;
import com.light.mapper.custom.LgEmployeeCustomMapper;
import com.light.mapper.custom.LgLoginUserCustomMapper;
import com.light.mapper.custom.LgMarketCustomMapper;
import com.light.mapper.custom.LgPostCustomMapper;
import com.light.mapper.custom.LgProjectCustomMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgEmployeeProject;
import com.light.pojo.LgEmployeeProjectExample;
import com.light.pojo.LgLoginCuslist;
import com.light.pojo.LgLoginCuslistExample;
import com.light.pojo.LgLoginUser;
import com.light.pojo.LgMarket;
import com.light.pojo.LgPost;
import com.light.pojo.LgPostExample;
import com.light.pojo.LgProject;
import com.light.pojo.LgProjectExample;
import com.light.pojo.LgRegion;
import com.light.pojo.LgProjectExample.Criteria;
import com.light.pojo.LgProjectProcess;
import com.light.pojo.LgProjectProcessExample;
import com.light.service.ProjectService;
import com.light.service.WechatService;
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired LgProjectMapper LgProjectMapper; 
	@Autowired LgMarketMapper LgMarketMapper;
	@Autowired LgEmployeeProjectMapper LgEmployeeProjectMapper;
	@Autowired LgPublicMapper LgPublicMapper;
	@Autowired LgMarketCustomMapper LgMarketCustomMapper; 
	@Autowired LgEmployeeCustomMapper LgEmployeeCustomMapper;
	@Autowired com.light.mapper.custom.LgCustomerCustomMapper LgCustomerCustomMapper;
	@Autowired LgProjectCustomMapper lgProjectCustomMapper;
	@Autowired LgPostMapper LgPostMapper;
	@Autowired LgPostCustomMapper LgPostCustomMapper;
	@Autowired LgRegionMapper LgRegionMapper;
	@Autowired LgProjectProcessMapper LgProjectProcessMapper;
	@Autowired LgLoginUserCustomMapper lgLoginUserCustomMapper;
	@Autowired WechatService wechatService;
	@Autowired LgLoginUserMapper LgLoginUserMapper;
	@Autowired LgLoginCuslistMapper LgLoginCuslistMapper;
	//提交工程
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map commitProject(LgProject lgProject,Integer userId,String contractIds) {
		if(lgProject == null)
			return ReturnResult.wrong();
		
		/*Integer marketId = lgProject.getMarketId();
		if(CheckNullUtil.integerNull(marketId))
			throw new ServiceException("市场为空");
			*/
		String marketName = lgProject.getMarketName();
		if(CheckNullUtil.isEmpty(marketName))
			throw new ServiceException("市场名不能为空");
		if(CheckNullUtil.integerNull(lgProject.getCustomerId()))
			throw new ServiceException("客户档案id不能为空");
		Integer marketNum = lgProjectCustomMapper.getMarketNameByName(marketName);
		if(marketNum > 0) 
			throw new ServiceException("市场名重复");
		//lgProject.setMarketName(LgMarketCustomMapper.getMarketNameById(lgProject.getMarketId()));
		lgProject.setLoginUserId(userId);
		lgProject.setStatus(1);//无需审核
		lgProject.setCustomerName(LgCustomerCustomMapper.getCustomerNameByCid(lgProject.getCustomerId()));
		
		int insertSta = LgProjectMapper.insertSelective(lgProject);
		if(insertSta < 1)
			throw new ServiceException("提交工程失败");
		Integer insertLastProjectId = LgPublicMapper.selectLastInsertId();
		
/*		LgMarket lgMarket =  new LgMarket();
		//lgMarket.setMid(marketId);
		lgMarket.setContractUrl(contractUrls);
		int updateSta = LgMarketMapper.updateByPrimaryKeySelective(lgMarket);
		if(updateSta < 1)
			throw new ServiceException("更新市场合同失败");*/
		
		//添加到employee_project映射
		String[] contractIdsArr = contractIds.split(":");
		for(String element : contractIdsArr){
			Integer tempId = Integer.valueOf(element);
			String tempName = LgEmployeeCustomMapper.getEmployeeNameById(tempId);
			LgEmployeeProject epRelation = new LgEmployeeProject();
			 epRelation.setEmployeeId(tempId);
			 epRelation.setEmployeeName(tempName);
			 epRelation.setProjectId(insertLastProjectId);
			int insSta = LgEmployeeProjectMapper.insertSelective(epRelation);
			if(insSta < 1)
				throw new ServiceException("插入员工-工程映射表失败");
		}
		return ReturnResult.ok();
	}

	//获取工程列表
	@Override
	public PageInfo<?> getProjectList(LgProject lgProject, Integer page, Integer rows, 
			Integer startTime,Integer endTime,Integer employeeId) {
		LgProjectExample example = new LgProjectExample();
			Criteria criteria = example.createCriteria();
			Integer pid = lgProject.getPid();
			String marketName = lgProject.getMarketName();
			Integer inputStatus = lgProject.getStatus();
			Integer customerId =lgProject.getCustomerId();
			
			if(CheckNullUtil.integerNotNull(pid))
				criteria.andPidEqualTo(pid);
			if(CheckNullUtil.isNotEmpty(marketName))
				criteria.andMarketNameLike("%"+marketName+"%");
			if(CheckNullUtil.integerNotNull(inputStatus))
				criteria.andStatusEqualTo(inputStatus);
			if(CheckNullUtil.integerNotNull(customerId))
				criteria.andCustomerIdEqualTo(customerId);
			criteria.andStatusNotEqualTo(5);//5-标记删除
			
			//add on 0412 1742
			if(CheckNullUtil.integerNotNull(employeeId)){
				LgEmployeeProjectExample emExa = new LgEmployeeProjectExample();
					emExa.createCriteria().andEmployeeIdEqualTo(employeeId);
				List<LgEmployeeProject> eProjectsList = LgEmployeeProjectMapper.selectByExample(emExa);
				if(CheckNullUtil.listNotNull(eProjectsList)){
					List<Integer> intList = new ArrayList<Integer>();
					for(LgEmployeeProject element : eProjectsList){
						intList.add(element.getProjectId());
					}
					criteria.andPidIn(intList);
				}
				
			}
			
		example.setOrderByClause("pid desc");	
		PageHelper.startPage(page, rows);
		List<LgProject> list = LgProjectMapper.selectByExample(example);
			PageInfo<?> tempPage = new PageInfo<>(list);
		
		List<Map<String, Object>> mapList = new ArrayList<>();
		Map<String,Object> beanMap = null;
		for(LgProject element : list){
			beanMap =  new HashMap<>();
			 LgEmployeeProjectExample epRelationExample = new LgEmployeeProjectExample();
			 	epRelationExample.createCriteria().andProjectIdEqualTo(element.getPid());
			 List<LgEmployeeProject> eList = LgEmployeeProjectMapper.selectByExample(epRelationExample); 
				 beanMap.put("project", element);
				 beanMap.put("list", eList);
			 mapList.add(beanMap);
		}
		PageInfo<?> pageInfo = new PageInfo<>(mapList);
			pageInfo.setTotal(tempPage.getTotal());
			pageInfo.setPageNum(tempPage.getPageNum());

		return pageInfo;
	}

	//更改工程表信息
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map modifyProjectById(LgProject project,String contractIds) {
		//如果更改工程进度的话，报错
		if(CheckNullUtil.integerNotNull(project.getProcessId()))
			throw new ServiceException("此接口不能修改工程进度");
		if(project.getStatus() != null && project.getStatus() == 5)
			throw new ServiceException("接口操作错误");
		Integer projectId = project.getPid();
		if(projectId == null)
			throw new ServiceException("pid为空");
		//如果更改合同人的话，修改工程-员工映射关系
		if(CheckNullUtil.isNotEmpty(contractIds)){
			//查看该表是否有映射
			if(lgProjectCustomMapper.getCountFromEPByPorjectId(projectId) > 0){
				//删除所有employee_project映射
				LgEmployeeProjectExample reExample = new LgEmployeeProjectExample();
					reExample.createCriteria().andProjectIdEqualTo(projectId);
				if(LgEmployeeProjectMapper.deleteByExample(reExample) < 1)
					throw new ServiceException("删除映射错误");
			}
			
			String[] contractArr = contractIds.split(":");
			for(String element : contractArr){
				Integer id = Integer.valueOf(element);
				LgEmployeeProject relation = new LgEmployeeProject();
				 relation.setEmployeeId(id);
				 relation.setProjectId(projectId);
				 //获取员工姓名
				 String searchEmployeeName = LgEmployeeCustomMapper.getEmployeeNameById(id);
				 relation.setEmployeeName(searchEmployeeName);
				//LgEmployeeProjectExample epExample =  new LgEmployeeProjectExample();
				 //epExample.createCriteria().andEmployeeIdEqualTo(id).andProjectIdEqualTo(projectId); 
				int inseSta = LgEmployeeProjectMapper.insertSelective(relation);
				if(inseSta < 1)
					throw new ServiceException("插入映射表失败");
			}
		}
		//如果更改客户档案的话，获取最新用户customerName
		Integer customerId = project.getCustomerId();
		String insertCustomerName = "未知用户";
		if(CheckNullUtil.integerNotNull(customerId)){
			insertCustomerName = LgCustomerCustomMapper.getCustomerNameByCid(customerId);
			project.setCustomerName(insertCustomerName);
		}

		int upSta2 = LgProjectMapper.updateByPrimaryKeySelective(project);
		if(upSta2 < 1)
			throw new ServiceException("更新表失败");
		
		return ReturnResult.ok();
		
	}
	//删除工程
	@Override
	public Map deleteProjectById(Integer sid) {
		LgProject project =  new LgProject();
		project.setPid(sid);
		project.setStatus(5);//标记删除
		int delSta = LgProjectMapper.updateByPrimaryKeySelective(project);
		if(delSta < 1){
			throw new ServiceException("删除失败");
		}
		
		//还要删除员工-工程映射
		LgEmployeeProjectExample example = new LgEmployeeProjectExample();
			example.createCriteria().andProjectIdEqualTo(sid);
		if(LgEmployeeProjectMapper.deleteByExample(example)<1)
			throw new ServiceException("删除失败");
		return ReturnResult.ok();
	}
	//获取市场名是否唯一
	public boolean validProjectMarketName(String marketName){
		Integer marketNum = lgProjectCustomMapper.getMarketNameByName(marketName);
	    if(marketNum < 1) return false;
		return true;
	}
	//根据员工id，获取专属工程列表
	@Override
	public PageInfo getProjectListByEmployeeId(Integer userId, LgProject lgProject,
			Integer page, Integer rows) {
		Integer employeeId = lgLoginUserCustomMapper.getEmployeeIdByLoginId(userId);
		if(CheckNullUtil.integerNull(employeeId))
			throw new ServiceException("员工id为空");
		Map<String,Integer> paramMap =  new HashMap<String, Integer>(); 
		Integer status = lgProject.getStatus();
		Integer processId = lgProject.getProcessId();
		
		paramMap.put("employeeId", employeeId);

		if(CheckNullUtil.integerNotNull(status))
			paramMap.put("status", status);
		if(CheckNullUtil.integerNotNull(processId))
			paramMap.put("processId", processId);
		PageHelper.startPage(page, rows);
		List<LgProject> list = lgProjectCustomMapper.getProjectListByEmployeeId(paramMap);
		PageInfo<?> pageInfo = new PageInfo<>(list);
		
		return pageInfo;
	}
	//根据工程id获取帖子列表
	@Override
	public PageInfo getPostListByProjectId(LgPost lgPost, Integer projectId,Integer page,Integer rows) throws ServiceException {
		LgPostExample example = new LgPostExample();
		com.light.pojo.LgPostExample.Criteria criteria  = example.createCriteria();
		 criteria.andProjectIdEqualTo(projectId);
		 
		 Integer type = lgPost.getType();//当前阶段
		 Integer pid = lgPost.getPid();
		 if(CheckNullUtil.integerNotNull(type))
			 criteria.andTypeEqualTo(type);
		 if(CheckNullUtil.integerNotNull(pid))
			 criteria.andPidEqualTo(pid);
			 
		 PageHelper.startPage(page,rows);
		List<LgPost> postList = LgPostMapper.selectByExample(example);
		List resultList = new ArrayList<>();
		Map<String, Object> rsMap = null;
		for(LgPost element : postList){
			rsMap = new HashMap<>();
			//获取帖子评论数
			Integer commentCount = LgPostCustomMapper.getCountCommemtWithPostId(element.getPid());
			//String headImg = lgLoginUserCustomMapper.getHeadUrlByLoginId(element.getLoginUserId());
			String headImg = "";
			LgLoginUser lgLoginUser = LgLoginUserMapper.selectByPrimaryKey(element.getLoginUserId());
			if(lgLoginUser != null){
				String roleIds = lgLoginUser.getRoleIds();
				if(roleIds.equals("3")){
					//员工
					headImg = lgLoginUser.getHeadImg();
				}
				else if(roleIds.equals("4")){
					//客户
					String openId = element.getOpenId();
					LgLoginCuslistExample cuslistExample = new LgLoginCuslistExample();
						cuslistExample.createCriteria().andOpenIdEqualTo(openId);
					List<LgLoginCuslist> getCList = LgLoginCuslistMapper.selectByExample(cuslistExample);
					if(CheckNullUtil.listNotNull(getCList))
						headImg = getCList.get(0).getHeadimgurl();
				}
			}
			rsMap.put("userImg", headImg);
			rsMap.put("element", element);
			rsMap.put("commentCount", commentCount);
			resultList.add(rsMap);
		}
		PageInfo<?> pageInfo =  new PageInfo<>(resultList);
		return pageInfo;
	}
	//获取工程地址信息
	@Override
	public Map getProjectMarketAddress(Integer projectId) {
		if(CheckNullUtil.integerNull(projectId))
				throw new ServiceException("id为空");
		LgProject getProject = LgProjectMapper.selectByPrimaryKey(projectId);
		if(getProject == null)
			throw new ServiceException("工程对象为空");
		Integer province = getProject.getProvince();
		Integer city = getProject.getCity();
		Integer district = getProject.getDistrict();
		
		if(province == null || city == null || district == null)
			throw new ServiceException("地址信息不完善");
		LgRegion pRegion = LgRegionMapper.selectByPrimaryKey(province);	
		LgRegion cRegion = LgRegionMapper.selectByPrimaryKey(city);	
		LgRegion dRegion = LgRegionMapper.selectByPrimaryKey(district);	

		Map rsMap = new HashMap<>();
			rsMap.put("province", pRegion);
			rsMap.put("city", cRegion);
			rsMap.put("district", cRegion);
			rsMap.put("address", getProject.getMarketAddress());
		
		return ReturnResult.ok(rsMap);
	}
	//更改当前工程进度
	@Override
	public Map updateProcessByProceeId(Integer type, Integer projectId,Integer processId) {
		LgProject getProject = LgProjectMapper.selectByPrimaryKey(projectId);
		if(getProject == null)
			throw new ServiceException("没有相关工程");
		Integer status = getProject.getStatus();
		if(status != 1)
			throw new ServiceException("当前非可修改状态");
		if(type == null)
			throw new ServiceException("type为空");
		//获取进度列表
		LgProjectProcessExample pExam = new LgProjectProcessExample();
		List<LgProjectProcess> pList = LgProjectProcessMapper.selectByExample(pExam);
		if(pList == null || pList.size() < 1){
			throw new ServiceException("工程进度列表为空");
		}
	//	List<Map<String, Object>> level1List = new ArrayList<>();
	//	Map<Integer, Object> tempMap = new HashMap<>();
		//Map<Integer,Object> level1Map = new HashMap<>();
		List<Integer> level1List = new ArrayList<>();
		for(LgProjectProcess element : pList){
			if(element.getLevel() == 1){
				//level1Map.put(element.getId(), element.getId());
				//level1Map.put("name", element.getName());
				level1List.add(element.getId());
			}
		}
		Collections.sort(level1List);

		if(type == 1){
			//1-更改第一级
			boolean haveId = level1List.contains(processId);
			Integer getProcessId = getProject.getProcessId();
			
			if(getProcessId != null){
				if(!haveId || processId < getProject.getProcessId())
					throw new ServiceException("更改进度错误");
			}else{
				processId = 1;
			}
			int needUpdateIndex = level1List.indexOf(processId)+1;
			Integer needUpdateNum = 0;
			if(needUpdateIndex > level1List.size()-1)
				needUpdateNum = 20;
			else
				needUpdateNum = level1List.get(needUpdateIndex);
			LgProject record = new LgProject();
				record.setPid(projectId);
				record.setProcessId(needUpdateNum);//此处已修改，未测试
				if(processId == 19)
					record.setStatus(4);//工程状态：0-未开始；1-进行中；2-审核失败；3-审核成功；4-成功结束；5-标记删除
			if(LgProjectMapper.updateByPrimaryKeySelective(record) < 1)
				throw new ServiceException("更新失败");
		}
		else if(type == 2){
			//2-更改第二级
			String insertStr = "";
			String processStr = getProject.getProcessStr();
			if(processStr == null || processStr.equals("")){
				//如果为空，添加一条就行
				insertStr = processId + "";
			}
			String[] proArr = processStr.split(":");
			List<Integer> intList = new ArrayList<>();
			for(String element : proArr){
				Integer intVal = Integer.valueOf(element);
				if(intVal == processId) throw new ServiceException("该阶段已完成，不能再次修改");
				intList.add(intVal);
			}
			intList.add(processId);
			Collections.sort(intList);
			StringBuffer sBuffer = new StringBuffer();
			int size = intList.size();
			for(int i=0; i<size; i++){
				if(i == size-1){
					sBuffer.append(intList.get(i));
					break;
				}
				sBuffer.append(intList.get(i)+":");
			}
			LgProject record = new LgProject();
			record.setPid(projectId);
			record.setProcessStr(sBuffer.toString());
			if(LgProjectMapper.updateByPrimaryKeySelective(record) < 1)
				throw new ServiceException("更新失败");
			//推送客户回访单
			sendTableToCustomer(getProject.getCustomerId(),getProject.getMarketName(),"请对本阶段服务作出评价","感谢您对本公司对支持，请点击进入表单评价");
		}
		return ReturnResult.ok();
	}
	 public void sendTableToCustomer(Integer customerId,String market,String title,String content){
		//获取工程相关的客户id
			//Integer customerId = sqlBean.getCustomerId();
		List<String> cusList = LgPostCustomMapper.getCusListByCustomerId(customerId);
			String url = "http://wytechhome.com/wechatTest/GYAdmin/html/perInfo/backInterView.html";
		wechatService.sendCommentTemplateToUser(cusList, market, title, content, url);
	 }
	//获取工程进度list
	@Override
	public List getProjectProcessList(LgProjectProcess process) {
		LgProjectProcessExample example =  new LgProjectProcessExample();
		 com.light.pojo.LgProjectProcessExample.Criteria criteria = example.createCriteria();
         Integer id = process.getId();
         String name = process.getName();
         Integer level = process.getLevel();
         Integer parentId = process.getParentId();
         if(CheckNullUtil.integerNotNull(id))
        	 criteria.andIdEqualTo(id);
         if(CheckNullUtil.integerNotNull(level))
        	 criteria.andLevelEqualTo(level);
         if(CheckNullUtil.integerNotNull(parentId))
        	 criteria.andParentIdEqualTo(parentId);
         if(CheckNullUtil.isNotEmpty(name))
        	 criteria.andNameEqualTo(name);
         List<LgProjectProcess> list = LgProjectProcessMapper.selectByExample(example);
         
		return list;
	}

	//根据loginUserId获取工程详情
	//此接口有比较大大bug，应该是直接获取projectId,然后根据这个去获取
	@Override
	public Map getProjectListWithExtraWithLoginUserId(Integer loginUserId) {
		if(CheckNullUtil.integerNull(loginUserId))
			throw new ServiceException("loginUserId为空");
		//如果是员工，应该lgloginUser->loginUserId--userTypeId=?employeeId,再去...employee_project里面找
		//如果是客户，则lgloginUser->loginuserId---userTypeId=?customerId,project表，customerId
		LgProjectExample example =  new LgProjectExample();
		Criteria criteria = example.createCriteria();
		criteria.andLoginUserIdEqualTo(loginUserId);
		List<LgProject> list = LgProjectMapper.selectByExample(example);
		if(!CheckNullUtil.listNotNull(list))
			throw new ServiceException("无相关工程");
		LgProject getProject = list.get(0);
		if(getProject == null)
			throw new ServiceException("无相关工程");
		Map rsMap  = new HashMap<>();
		
		Integer provinceId = getProject.getProvince();
		Integer cityId = getProject.getCity();
		Integer districtId = getProject.getDistrict();
		LgRegion province = null,city = null,district=null;
		if(provinceId != null)
			province = LgRegionMapper.selectByPrimaryKey(provinceId);
		if(cityId != null)
			city = LgRegionMapper.selectByPrimaryKey(cityId);
		if(districtId != null)
		  district = LgRegionMapper.selectByPrimaryKey(districtId);
		rsMap.put("province", province);
		rsMap.put("city", city);
		rsMap.put("district", district);
		rsMap.put("element", getProject);
		
		return rsMap;
	}
	//获取工程具体进度详情
	@Override
	public List getProjectProcessDetail(Integer projectId) {
		LgProject getProject = LgProjectMapper.selectByPrimaryKey(projectId);
		if(getProject == null)
			throw new ServiceException("没有相关工程");
		Integer getProcessId = getProject.getProcessId();
		String getProcessStr = getProject.getProcessStr();
		List<Integer> intList = new ArrayList<>();
		
		if(getProcessStr != null){
			String[]  getArr = getProcessStr.split(":");
			for(String element : getArr){
				intList.add(Integer.valueOf(element));
			}
		}
		
		LgProjectProcessExample pExam = new LgProjectProcessExample();
		List<LgProjectProcess> pList = LgProjectProcessMapper.selectByExample(pExam);
		if(pList == null || pList.size() < 1)
			throw new ServiceException("工程进度列表为空");
		List list1 = new ArrayList<>();List list2 = new ArrayList<>();
		List list3 = new ArrayList<>();List list4 = new ArrayList<>();
		List list5= new ArrayList<>();List list6 = new ArrayList<>();
		
		for(LgProjectProcess element : pList){
			Integer parentId = element.getParentId();
			Integer curId = element.getId();
			Map sonMap = new HashMap<>();
			if(element.getLevel() == 2){
				switch (parentId) {
				case 1:
					sonMap.put("id", element.getId());
					sonMap.put("name", element.getName());
					if(intList.contains(curId))
						sonMap.put("status", 1);
					else 
						sonMap.put("status", 0);
					list1.add(sonMap);
					break;
				case 3:
					sonMap.put("id", element.getId());
					sonMap.put("name", element.getName());
					if(intList.contains(curId))
						sonMap.put("status", 1);
					else 
						sonMap.put("status", 0);
					list2.add(sonMap);

					break;
				case 7:
					sonMap.put("id", element.getId());
					sonMap.put("name", element.getName());
					if(intList.contains(curId))
						sonMap.put("status", 1);
					else 
						sonMap.put("status", 0);
					list3.add(sonMap);

					break;
				case 11:
					sonMap.put("id", element.getId());
					sonMap.put("name", element.getName());
					if(intList.contains(curId))
						sonMap.put("status", 1);
					else 
						sonMap.put("status", 0);
					list4.add(sonMap);

					break;
				case 15:
					sonMap.put("id", element.getId());
					sonMap.put("name", element.getName());
					if(intList.contains(curId))
						sonMap.put("status", 1);
					else 
						sonMap.put("status", 0);
					list5.add(sonMap);

					break;
				case 19:
					sonMap.put("id", element.getId());
					sonMap.put("name", element.getName());
					if(intList.contains(curId))
						sonMap.put("status", 1);
					else 
						sonMap.put("status", 0);
					list6.add(sonMap);

					break;

				default:
					break;
				}
			}
		}
		
		
		List finalList = new ArrayList<>();
		Map level1Map = null;
		for(LgProjectProcess element : pList){
			Integer curId = element.getId();
			level1Map = new HashMap<>();
			if(element.getLevel() == 1){
				if(curId < getProcessId)
					level1Map.put("status", 1);
				else
					level1Map.put("status", 0);
				level1Map.put("id", curId);
				level1Map.put("name", element.getName());
				
				switch (curId) {
				case 1:
					level1Map.put("list", list1);
					break;
				case 3:
					level1Map.put("list", list2);
					break;
				case 7:
					level1Map.put("list", list3);
					break;
				case 11:
					level1Map.put("list", list4);
					break;
				case 15:
					level1Map.put("list", list5);
					break;
				case 19:
					level1Map.put("list", list6);
					break;

				default:
					break;
				}
				finalList.add(level1Map);

			}
		}
		return finalList;
	}

	@Override
	public List getCusProjectList(Integer customerId) {
		if(CheckNullUtil.integerNull(customerId))
			throw new ServiceException("customerId为空");
		LgProjectExample example =  new LgProjectExample();
		Criteria criteria = example.createCriteria();
			criteria.andCustomerIdEqualTo(customerId);
			example.setOrderByClause("status asc");
		List<LgProject> list = LgProjectMapper.selectByExample(example);
		if(!CheckNullUtil.listNotNull(list))
			throw new ServiceException("无相关工程");
		List<Map> mapList = new ArrayList<>();
		for(LgProject getProject : list){
		//LgProject getProject = list.get(0);
		Map rsMap  = new HashMap<>();
		Integer provinceId = getProject.getProvince();
		Integer cityId = getProject.getCity();
		Integer districtId = getProject.getDistrict();
		LgRegion province = null,city = null,district=null;
		if(provinceId != null)
			province = LgRegionMapper.selectByPrimaryKey(provinceId);
		if(cityId != null)
			city = LgRegionMapper.selectByPrimaryKey(cityId);
		if(districtId != null)
		  district = LgRegionMapper.selectByPrimaryKey(districtId);
		rsMap.put("province", province);
		rsMap.put("city", city);
		rsMap.put("district", district);
		rsMap.put("element", getProject);
		
		mapList.add(rsMap);
		}
		
		return mapList;
		
	}
	//获得所有市场名称
	@Override
	public List getProjectNameList(String marketName) {
		Map paramMap = new HashMap<>();
			if(CheckNullUtil.isNotEmpty(marketName))
				paramMap.put("marketName", "%"+marketName+"%");
		List<ProjectMarketnameBean> list = lgProjectCustomMapper.getAllProjectMarketName(paramMap);
		return list;
	}

}
	
