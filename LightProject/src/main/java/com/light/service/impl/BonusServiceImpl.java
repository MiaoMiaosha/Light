package com.light.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.nntp.NewGroupsOrNewsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.FormatUtil;
import com.light.common.ReturnResult;
import com.light.dto.ContractIdsOutputBean;
import com.light.exception.ServiceException;
import com.light.mapper.LgBonusExtraMapper;
import com.light.mapper.LgBonusLevelMapper;
import com.light.mapper.LgBonusMapper;
import com.light.mapper.LgConfigMapper;
import com.light.mapper.LgEmployeeBonusMapper;
import com.light.mapper.LgEmployeeProjectMapper;
import com.light.mapper.LgLoginUserMapper;
import com.light.mapper.LgProjectMapper;
import com.light.mapper.LgTotalCountMapper;
import com.light.mapper.custom.LgBonusCustomMapper;
import com.light.mapper.custom.LgEmployeeCustomMapper;
import com.light.mapper.custom.LgLoginUserCustomMapper;
import com.light.mapper.custom.LgMarketCustomMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgBonus;
import com.light.pojo.LgBonusExample;
import com.light.pojo.LgBonusExample.Criteria;
import com.light.pojo.LgBonusExtra;
import com.light.pojo.LgBonusExtraExample;
import com.light.pojo.LgBonusLevel;
import com.light.pojo.LgBonusLevelExample;
import com.light.pojo.LgConfig;
import com.light.pojo.LgConfigExample;
import com.light.pojo.LgEmployeeBonus;
import com.light.pojo.LgEmployeeBonusExample;
import com.light.pojo.LgEmployeeProject;
import com.light.pojo.LgEmployeeProjectExample;
import com.light.pojo.LgProject;
import com.light.pojo.LgProjectExample;
import com.light.pojo.LgTotalCount;
import com.light.pojo.LgTotalCountExample;
import com.light.service.BonusService;
import com.light.service.TotalCountService;
@Service
public class BonusServiceImpl implements BonusService {

	@Autowired LgBonusMapper LgBonusMapper;
	@Autowired LgBonusExtraMapper LgBonusExtraMapper;
	@Autowired LgBonusCustomMapper LgBonusCustomMapper;
	@Autowired LgPublicMapper LgPublicMapper;
	@Autowired LgEmployeeBonusMapper LgEmployeeBonusMapper;
	@Autowired LgEmployeeCustomMapper LgEmployeeCustomMapper;
	@Autowired LgTotalCountMapper LgTotalCountMapper;
	@Autowired LgConfigMapper LgConfigMapper;
	@Autowired LgBonusLevelMapper LgBonusLevelMapper;
	@Autowired LgMarketCustomMapper LgMarketCustomMapper;
	@Autowired LgProjectMapper LgProjectMapper;
	@Autowired LgEmployeeProjectMapper LgEmployeeProjectMapper;
	@Autowired LgLoginUserCustomMapper LgLoginUserCustomMapper;
	
	@Autowired TotalCountService TotalCountService;
	//提交分红
	@Override
	public Map commitBonus(LgBonus lgBonus,Integer userId,String bonusUsers) {
		if(lgBonus == null)
			throw new ServiceException("信息为空");
		if(lgBonus.getProjectId() == null || lgBonus.getProjectId() == 0)
			throw new ServiceException("projectId为空");
		if(CheckNullUtil.isEmpty(bonusUsers))
			throw new ServiceException("分红人为空");

		//Must Chag！！
		lgBonus.setBonusTime(FormatUtil.timeStampInt());
		
		//如果是会计，免审核
		String roleIds = LgLoginUserCustomMapper.getRoleIdByLoginId(userId);
		if(CheckNullUtil.isNotEmpty(roleIds) && roleIds.equals("2")){
			lgBonus.setStatus(1);
			lgBonus.setOperateNo(userId);
			//lgBonus.setUpdateTime();--------------前台设置好参数吧
		}
		else{
			lgBonus.setStatus(0);
			if(CheckNullUtil.integerNotNull(lgBonus.getUpdateTime()))
				throw new ServiceException("非会计，需要审批");
		}
		lgBonus.setOperateNo(0);
		lgBonus.setLoginUserId(userId);
		//提交contractIds 合同ids，前台提交
		//
		//分红是多人记录，得添加到员工-分红关系表
		int inserSta = LgBonusMapper.insertSelective(lgBonus);
		if(inserSta < 1)
			return ReturnResult.wrong();
		int newBonusId = LgPublicMapper.selectLastInsertId();
		//添加到employee_bonus表中
		String[] users = bonusUsers.split(":");
		//String employeeName = "";
		for(String element : users){
			String  employeeName = LgEmployeeCustomMapper.getEmployeeNameById(Integer.valueOf(element));
			LgEmployeeBonus relation = new LgEmployeeBonus();
			relation.setEmployeeId(Integer.valueOf(element));
			relation.setBonusId(newBonusId);
			relation.setEmployeeName(employeeName);
			int insertRelationSta = LgEmployeeBonusMapper.insertSelective(relation);
			if(insertRelationSta < 1)
				throw new ServiceException("插入映射表失败");
		}
		
		return ReturnResult.ok();
	}

	//获取分红列表
	@Override
	public PageInfo<?> getBonusList(LgBonus lgBonus, Integer page, Integer rows, Integer startTime, Integer endTime,Integer employeeId) {
		LgBonusExample example = new LgBonusExample();
			Criteria criteria = example.createCriteria();
			example.setOrderByClause("status asc");
			Integer bid = lgBonus.getBid();
			String marketName = lgBonus.getMarketName();
			//Integer marketId = lgBonus.getMarketId();
			Integer inputStatus = lgBonus.getStatus();
			
			if(CheckNullUtil.integerNotNull(bid))
				criteria.andBidEqualTo(bid);
			//if(CheckNullUtil.integerNotNull(marketId))
			//	criteria.andMarketIdEqualTo(marketId);
			if(CheckNullUtil.integerNotNull(inputStatus))
				criteria.andStatusEqualTo(inputStatus);
			if(CheckNullUtil.isNotEmpty(marketName))//市场名称
				criteria.andMarketNameLike("%"+marketName+"%");
			if(CheckNullUtil.integerNotNull(employeeId)){
				LgEmployeeBonusExample relExa = new LgEmployeeBonusExample();
					relExa.createCriteria().andEmployeeIdEqualTo(employeeId);
				List<LgEmployeeBonus> ebList = LgEmployeeBonusMapper.selectByExample(relExa);
				if(CheckNullUtil.listNotNull(ebList)){
					List<Integer> iiiList = new ArrayList<>();
					for(LgEmployeeBonus element : ebList){
						iiiList.add(element.getBonusId());
					}
					criteria.andBidIn(iiiList);
				}else{
					criteria.andBidEqualTo(0);
				}
			}
			//审核通过时间--记入月份
			if(CheckNullUtil.integerNotNull(startTime))
				criteria.andUpdateTimeGreaterThanOrEqualTo(startTime);
			if(CheckNullUtil.integerNotNull(endTime))
				criteria.andUpdateTimeLessThanOrEqualTo(endTime);

			
			
		    criteria.andStatusNotEqualTo(3);//3是标记删除
		PageHelper.startPage(page,rows);
		List<LgBonus> list = LgBonusMapper.selectByExample(example);
		
		List<Map<String, Object>> mapList = new ArrayList<>();
		//查询详情的时候
		//if(CheckNullUtil.integerNotNull(bid)){
			if(CheckNullUtil.listNotNull(list)){
				Map rsMap = null;
//				List list1 = new ArrayList<>();
//				List list2 = new ArrayList<>();
				for(LgBonus element : list){
					rsMap = new HashMap<>();
				//获取分红人列表
					LgEmployeeBonusExample bonusExam = new LgEmployeeBonusExample();
					 bonusExam.createCriteria().andBonusIdEqualTo(element.getBid());
					List<LgEmployeeBonus> bonusIds = LgEmployeeBonusMapper.selectByExample(bonusExam);
				//获取合同人列表
					/*Integer projectMarketId = element.getMarketId();
					LgProjectExample projectExample = new LgProjectExample();
					  projectExample.createCriteria().andMarketIdEqualTo(projectMarketId);
					List<LgProject> projectList = LgProjectMapper.selectByExample(projectExample);
					  Integer getProjectId = projectList.get(0).getPid();
					LgEmployeeProjectExample employeeProjectExample = new LgEmployeeProjectExample();
					  employeeProjectExample.createCriteria().andProjectIdEqualTo(getProjectId);
					List<LgEmployeeProject> contractIds = LgEmployeeProjectMapper.selectByExample(employeeProjectExample);*/
					List<ContractIdsOutputBean> contractIds = new ArrayList<>();
					String contractIdsStr = element.getContractids();
					if(contractIdsStr != null && !"".equals(contractIdsStr)){
					String[] contractIdsArr = contractIdsStr.split(":");
					List<Integer> intList = new ArrayList<>();
					
					for(String strArr : contractIdsArr){
						intList.add(Integer.valueOf(strArr));
					}
					ContractIdsOutputBean outputBean = null;
					for(Integer intelement : intList){
						outputBean = new ContractIdsOutputBean();
						outputBean.setEmployeeId(intelement);
						outputBean.setEmployeeName(LgEmployeeCustomMapper.getEmployeeNameById(intelement));
						contractIds.add(outputBean);
					 }
					}
					rsMap.put("bonus", element);
					rsMap.put("bonusIds", bonusIds);
					rsMap.put("contractIds", contractIds);
					
					mapList.add(rsMap);
				}
			}
	//	}
		
		PageInfo<?> pageInfo = new PageInfo<>(mapList);
		return pageInfo;
	}

	//删除分红
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map deleteBonusById(Integer id) {
	/*	
		//确认一下该条分红当前的状态
		  //获取分红表中，每个人的分红金额、个人业绩金额，每个人都相同
		 Map<String, Integer> totalCountMap = LgBonusCustomMapper.getBonusMapByBid(id);
		  Integer bonusMoneyGet = totalCountMap.get("bonusMoney");
		  Integer personSalaryGet = totalCountMap.get("personSalary");
		  Integer updateTimeGet = totalCountMap.get("updateTime");//分红记录审核通过时间
		  Integer curStatus = totalCountMap.get("status");
		  if(CheckNullUtil.integerNull(updateTimeGet)) 
			  throw new ServiceException("该分红没有审核");
		  if(curStatus == 0 || curStatus == 2 || curStatus == 3 ){
			  //改分红属于可删除状态，直接删除
			  if(LgBonusMapper.deleteByPrimaryKey(id) < 1)
				  throw new ServiceException("删除失败");
			  return ReturnResult.ok();
		  }
		
		LgBonus bonus = new LgBonus();
		bonus.setBid(id);
		bonus.setStatus(3);
		//int delSta = LgBonusMapper.deleteByPrimaryKey(id);
		int updSta = LgBonusMapper.updateByPrimaryKeySelective(bonus);
		if(updSta < 1)
			//return ReturnResult.wrong();
			throw new ServiceException("更新分红表失败");
		//维护总计表
		//1条分红有多个人参与,获取分红相关人员列表
		//分红信息确认后
		//1.获取分红相关人员列表
		 List<Integer> employeeList = LgBonusCustomMapper.getBonusRelateEmployeeId(id);

		//2.确认后，获取分红的人totalCount中的信息
		if(employeeList != null && employeeList.size() > 0){
			for(Integer element : employeeList){
				LgTotalCountExample totalCountExample = new LgTotalCountExample();
				totalCountExample.createCriteria().andUserIdEqualTo(element).andDateNoEqualTo(FormatUtil.getDateNoByTimestamp(updateTimeGet));
				//查找每个人的总计表详情
				List<LgTotalCount> eInfoList = LgTotalCountMapper.selectByExample(totalCountExample);
				Integer needUpdateTotalCountId = null;
				Integer needUpdateBonus = 0;
				Integer needUpdatePersonSalary = 0;
				//查找不到总计信息，即时创建
				if(eInfoList == null || eInfoList.size() < 1){
					 throw new ServiceException("统计表中没有记录");
				}else{//查到信息，修改金额
					LgTotalCount totalCount = eInfoList.get(0);
					needUpdateTotalCountId = totalCount.getId();
					needUpdateBonus = totalCount.getBonus()-bonusMoneyGet;
					needUpdatePersonSalary = totalCount.getPersonSalary()-personSalaryGet;
				}
				
				LgTotalCount updateTotalCount = new LgTotalCount();
					updateTotalCount.setId(needUpdateTotalCountId);
					updateTotalCount.setBonus(needUpdateBonus); 
					updateTotalCount.setPersonSalary(needUpdatePersonSalary);
				int upTotalSta = LgTotalCountMapper.updateByPrimaryKeySelective(updateTotalCount);
				if(upTotalSta < 1)
					throw new ServiceException("更新总计表失败");
				
			}
		 }else
			throw new ServiceException("获取不到该记录下分红人员");
		*/
		if(CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		LgBonus record = new LgBonus();
			record.setBid(id);
			record.setStatus(3);
		if(LgBonusMapper.updateByPrimaryKeySelective(record)<1)
			throw new ServiceException("删除失败");
		//获取当前总计表用户数据
		return ReturnResult.ok();
	}
	//获取额外信息列表
	@Override
	public List getExtraInfoList(LgBonusExtra extra) {
		LgBonusExtraExample example = new LgBonusExtraExample();
		if(extra != null){
			Integer id = extra.getId();
			String bonusType = extra.getBonusType();
			com.light.pojo.LgBonusExtraExample.Criteria criteria = example.createCriteria();
			if(CheckNullUtil.integerNotNull(id))
				criteria.andIdEqualTo(id);
			if(CheckNullUtil.isNotEmpty(bonusType))
				criteria.andBonusTypeLike("%"+bonusType+"%");
		}
		List list = LgBonusExtraMapper.selectByExample(example);
		return list;
		
	}
	//确认分红信息---此接口只能设置通过，其他状态均报错。审核不通过--利用下面另一个接口
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map confirmBonus(Integer id, Integer operateId, Integer status) {
		LgBonus lgBonus = new LgBonus();
		lgBonus.setBid(id);
		lgBonus.setOperateNo(operateId);
		lgBonus.setStatus(status);
		if(status == 1)//如果为1，则设置审核通过时间
			lgBonus.setUpdateTime(FormatUtil.timeStampInt());//设置审核时间
		int updateSta = LgBonusMapper.updateByPrimaryKeySelective(lgBonus);
		if(updateSta < 1)
			throw new ServiceException("确认分红失败");
		/**
		 * 暂时注释
		 */
/*		if(status == 1){ // 如果设置为审核通过
		//分红信息确认后
		//1.获取分红相关人员列表
		 List<Integer> employeeList = LgBonusCustomMapper.getBonusRelateEmployeeId(id);
		  //获取分红表中，每个人的分红金额、个人业绩金额，每个人都相同
		 Map<String, Integer> totalCountMap = LgBonusCustomMapper.getBonusMapByBid(id);
		  Integer bonusMoneyGet = totalCountMap.get("bonusMoney");
		  Integer personSalaryGet = totalCountMap.get("personSalary");
		//2.确认后，获取分红的人totalCount中的信息
		if(employeeList != null && employeeList.size() > 0){
			for(Integer element : employeeList){
				LgTotalCountExample totalCountExample = new LgTotalCountExample();
				totalCountExample.createCriteria().andUserIdEqualTo(element).andDateNoEqualTo(FormatUtil.getCurDateNo());
				//查找每个人的总计表详情
				List<LgTotalCount> eInfoList = LgTotalCountMapper.selectByExample(totalCountExample);
				Integer needUpdateTotalCountId = null;
				Integer needUpdateBonus = 0;
				Integer needUpdatePersonSalary = 0;
				//查找不到总计信息，即时创建
				if(eInfoList == null || eInfoList.size() < 1){
					 needUpdateTotalCountId = TotalCountService.createEmployeeRecord(element, FormatUtil.getCurDateNo());
				}else{//查到信息，修改金额
					LgTotalCount totalCount = eInfoList.get(0);
					needUpdateTotalCountId = totalCount.getId();
					needUpdateBonus = totalCount.getBonus()+bonusMoneyGet;
					needUpdatePersonSalary = totalCount.getPersonSalary()+personSalaryGet;
				}
				
				LgTotalCount updateTotalCount = new LgTotalCount();
					updateTotalCount.setId(needUpdateTotalCountId);
					updateTotalCount.setBonus(needUpdateBonus); 
					updateTotalCount.setPersonSalary(needUpdatePersonSalary);
				int upTotalSta = LgTotalCountMapper.updateByPrimaryKeySelective(updateTotalCount);
				if(upTotalSta < 1)
					throw new ServiceException("更新总计表失败");
				
			}
		 }else
			throw new ServiceException("获取不到该记录下分红人员");
		 }//审核通过判断--end
		else{
			throw new ServiceException("接口调用错误");
		}
		*/
		//修改写入totalcount表
		return ReturnResult.ok();
	}

	//获取分红级别
	@Override
	public Map getBonusLevel(String bonusIds) {
		//获取规定时间段内，几个分红用户的个人业绩收入
		
		String[] ids = bonusIds.split(":");
		int idsLength = ids.length;
		if(idsLength == 0)
			throw new ServiceException("请提交正确的分红人id");
		
		//查找系统配置
		LgConfigExample configExample = new LgConfigExample();
		com.light.pojo.LgConfigExample.Criteria criteria1 = configExample.createCriteria();
		 criteria1.andNameEqualTo("bonus_start_time");
		com.light.pojo.LgConfigExample.Criteria criteria2 = configExample.createCriteria();
		 criteria2.andNameEqualTo("bonus_end_time");
		 configExample.or(criteria2);
		List<LgConfig> configList = LgConfigMapper.selectByExample(configExample);
		Integer startTime = FormatUtil.getCurDateNo();
		Integer endTime = FormatUtil.getCurMonthMaxTime();
		for(LgConfig element : configList){
			if(element.getName().equals("bonus_start_time"))
				startTime = Integer.valueOf(element.getValue());
			else if(element.getName().equals("bonus_end_time"))
				endTime = Integer.valueOf(element.getValue());
		}
		List<Integer> idValues = new ArrayList<Integer>();
		for(int i = 0;i < idsLength; i++){
			idValues.add(Integer.valueOf(ids[i]));
		}
		//获取每个人时间段内的总计对象
		LgTotalCountExample example =  new LgTotalCountExample();
		 example.createCriteria().andDateNoBetween(startTime, endTime).andUserIdIn(idValues);
		 //获取规定时间内 个人的 个人业绩收入总和
		 List<LgTotalCount> userTotalCount = LgTotalCountMapper.selectByExample(example);
		 
		 int personSalaryTotal = 0;
		 for(LgTotalCount element : userTotalCount){
			 personSalaryTotal += element.getPersonSalary();
		 }
		int averageTotal = personSalaryTotal/(idsLength);
		
		LgBonusLevelExample bLevelExample = new LgBonusLevelExample();
		 bLevelExample.createCriteria().andBonusLevelMoneyLessThanOrEqualTo(averageTotal);
		 bLevelExample.setOrderByClause("bonus_level_money desc");
		 List<LgBonusLevel> bLgBonusLevels = LgBonusLevelMapper.selectByExample(bLevelExample);
		 if(bLgBonusLevels == null || bLgBonusLevels.size() < 1)
			 throw new ServiceException("请设置正确的分红级别");
		 
		Map rsMap = new HashMap<>();
		rsMap.put("eids", bonusIds);
		rsMap.put("num", idsLength);
		rsMap.put("total", personSalaryTotal);
		rsMap.put("average", averageTotal);
		rsMap.put("levelName", bLgBonusLevels.get(0)==null?"未知":bLgBonusLevels.get(0).getBonusLevelName());
		
		return ReturnResult.ok(rsMap);
	}

	//编辑分红
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map editBonus(LgBonus lgBonus,String bonusIds) {
		Integer bid = lgBonus.getBid();
		if(bid == null)
			throw new ServiceException("没有设置主键");
		Integer status = lgBonus.getStatus();
		/*if(status != null && status != 2)
			throw new ServiceException("接口调用错误");*/
		if(status != null && status == 1){
			if(lgBonus.getUpdateTime() == null)
				throw new ServiceException("计入时间为空");
		}
			
		//如果市场id不为空
		Integer marketId = lgBonus.getMarketId();
		String insertMarketName = "未知市场";
		if(CheckNullUtil.integerNotNull(marketId)){
			insertMarketName = LgMarketCustomMapper.getMarketNameById(marketId);
			lgBonus.setMarketName(insertMarketName);
		}
		//如果分红人不为空
		if(CheckNullUtil.isNotEmpty(bonusIds)){
		String[] bonusArr = bonusIds.split(":");
		for(String element : bonusArr){
			Integer ebId = Integer.valueOf(element);
			LgEmployeeBonus employeeBonus = new LgEmployeeBonus();
			  employeeBonus.setEmployeeId(ebId);
			  employeeBonus.setBonusId(bid);
			  String employeeName = LgEmployeeCustomMapper.getEmployeeNameById(ebId);
			  employeeBonus.setEmployeeName(employeeName);
			LgEmployeeBonusExample employeeBonusExample = new LgEmployeeBonusExample();
			  employeeBonusExample.createCriteria().andEmployeeIdEqualTo(ebId).andBonusIdEqualTo(bid);
			 int upStatus = LgEmployeeBonusMapper.updateByExampleSelective(employeeBonus, employeeBonusExample);
			 if(upStatus < 1)
					throw new ServiceException("更新映射表失败");
		 }
		}
		
		int upSta = LgBonusMapper.updateByPrimaryKeySelective(lgBonus);
		if(upSta < 1)
			throw new ServiceException("编辑更新失败");
		return ReturnResult.ok();
	}
	//修改分红类型
	@Override
	public Map editBonusExtra(LgBonusExtra extra) throws ServiceException {
		Integer id = extra.getId();
		String bonusType = extra.getBonusType();
		
		if(CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		if(CheckNullUtil.isEmpty(bonusType))
			throw new ServiceException("分红类型为空");
		
		if(LgBonusExtraMapper.updateByPrimaryKeySelective(extra) <1)
			throw new ServiceException("更新失败");
		
		return ReturnResult.ok();
	}
	/**
	 * 删除分红类型
	 */
	@Override
	public Map deleteBonusExtra(Integer id) throws ServiceException {
		if(CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		Integer count = LgBonusCustomMapper.getCountByBonusTypeId(id);
		if(count != null && count > 0)
			throw new ServiceException("该类型下有分红记录，不能删除");
		
		if(LgBonusExtraMapper.deleteByPrimaryKey(id) < 1)
			throw new ServiceException("删除失败");
		return ReturnResult.ok();
	}
	//添加分红类型
	@Override
	public Map addBonusExtra(LgBonusExtra lgBonusExtra) throws ServiceException {
		String bonusType = lgBonusExtra.getBonusType();
		if(CheckNullUtil.isEmpty(bonusType))
			throw new ServiceException("分红类型不能为空");
		if(LgBonusExtraMapper.insertSelective(lgBonusExtra)<1)
			throw new ServiceException("插入失败");
		return ReturnResult.ok();
	}
	
	//获取分红比例
	@Override
	public List<LgBonusLevel> getLevelList(LgBonusLevel level) {
		Integer id = level.getId();
		LgBonusLevelExample example = new LgBonusLevelExample();
			com.light.pojo.LgBonusLevelExample.Criteria criteria = example.createCriteria();
		if (CheckNullUtil.integerNotNull(id))
				criteria.andIdEqualTo(id);
		List<LgBonusLevel> list = LgBonusLevelMapper.selectByExample(example);
		return list;
	}

	@Override
	public void addBonusLevel(LgBonusLevel level) {
		Integer bonusRate = level.getBonusRate();
		if (CheckNullUtil.integerNull(bonusRate))
			throw new ServiceException("分红比例为空");
		if(LgBonusLevelMapper.insertSelective(level)<1)
			throw new ServiceException("插入失败");
		
	}

	@Override
	public void editBonusLevel(LgBonusLevel level) {
		Integer id = level.getId();
		if (CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		if(LgBonusLevelMapper.updateByPrimaryKeySelective(level)<1)
			throw new ServiceException("修改失败");
		
	}

	@Override
	public void deleteBonusLevel(Integer id) {
		if (CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		if(LgBonusLevelMapper.deleteByPrimaryKey(id)<1)
			throw new ServiceException("删除失败");
		
	}
	//获取当前时间范围内，所有通过审核的分红
	@Override
	public Integer getCurTimeMoney(Integer employeeId, Integer startTime, Integer endTime) {
		
		return null;
	}

}
