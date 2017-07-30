package com.light.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.FormatUtil;
import com.light.common.ReturnResult;
import com.light.dto.SalaryOutputBean;
import com.light.exception.ServiceException;
import com.light.mapper.LgProjectMapper;
import com.light.mapper.LgSalaryExtraMapper;
import com.light.mapper.LgSalaryMapper;
import com.light.mapper.LgTotalCountMapper;
import com.light.mapper.custom.LgEmployeeCustomMapper;
import com.light.mapper.custom.LgLoginUserCustomMapper;
import com.light.mapper.custom.LgProjectCustomMapper;
import com.light.pojo.LgSalary;
import com.light.pojo.LgSalaryExample;
import com.light.pojo.LgSalaryExtra;
import com.light.pojo.LgSalaryExample.Criteria;
import com.light.pojo.LgSalaryExtraExample;
import com.light.pojo.LgTotalCount;
import com.light.pojo.LgTotalCountExample;
import com.light.service.SalaryService;
@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired LgSalaryMapper LgSalaryMapper;
	@Autowired LgSalaryExtraMapper LgSalaryExtraMapper;
	@Autowired LgTotalCountMapper LgTotalCountMapper;
	@Autowired LgEmployeeCustomMapper lgEmployeeCustomMapper;
	@Autowired LgProjectCustomMapper lgProjectCustomMapper;
	
	@Autowired LgLoginUserCustomMapper lgLoginUserCustomMapper;
	//提交收入
	@Override
	public Map commitSalary(LgSalary salary, Integer userId) {
		if(salary == null)
			throw new ServiceException("提交空对象");
		if(salary.getPersonCommitTime() == null)
			throw new ServiceException("提交日期必须输入");
		salary.setCreateTime(FormatUtil.timeStampInt());
		salary.setLoginUserId(userId);
		
		//如果是会计，免审核
		String roleIds = lgLoginUserCustomMapper.getRoleIdByLoginId(userId);
		if(CheckNullUtil.isNotEmpty(roleIds) && roleIds.equals("2")){
			salary.setStatus(1);
			salary.setOperateNo(userId);
			//lgBonus.setUpdateTime();--------------前台设置好参数吧
		}
		else{
			salary.setStatus(0);
			if(CheckNullUtil.integerNotNull(salary.getFinishTime()))
				throw new ServiceException("非会计，需要审批");
		}
		int inserSta = LgSalaryMapper.insertSelective(salary);
		if(inserSta < 1)
			throw new ServiceException("插入失败");
		return ReturnResult.ok();

	}
	//获取收入类型说明
	@Override
	public List getSalaryExtra(LgSalaryExtra lgSalaryExtra) {
		
		LgSalaryExtraExample example = new LgSalaryExtraExample();
		
			com.light.pojo.LgSalaryExtraExample.Criteria criteria = example.createCriteria();
			Integer tid = lgSalaryExtra.getTid();
			String typeName = lgSalaryExtra.getTypeName();
			Integer type = lgSalaryExtra.getType();
			
			if(CheckNullUtil.integerNotNull(tid))
				criteria.andTidEqualTo(tid);
			if(CheckNullUtil.integerNotNull(type))
				criteria.andTypeEqualTo(type);
			if(CheckNullUtil.isNotEmpty(typeName))
				criteria.andTypeNameLike("%"+typeName+"%");

		List list = LgSalaryExtraMapper.selectByExample(example);
		return list;
	}
	
	//获取收入列表
	@Override
	public PageInfo<?> getSalaryList(LgSalary salary, Integer page, Integer rows, Integer startTime, Integer endTime,String marketName) {
		
		LgSalaryExample example = new LgSalaryExample();
		  	Integer sid = salary.getSid();
			Integer employeeId = salary.getEmployeeId();
			Integer projectId = salary.getProjectId();
			Integer inputStatus = salary.getStatus();
			
			Criteria criteria = example.createCriteria();
			criteria.andStatusNotEqualTo(3);//3-标记删除
			
			if(CheckNullUtil.integerNotNull(sid))
				criteria.andSidEqualTo(sid);
			if(CheckNullUtil.integerNotNull(employeeId))
				criteria.andEmployeeIdEqualTo(employeeId);
			if(CheckNullUtil.integerNotNull(projectId))
				criteria.andProjectIdEqualTo(projectId);
			
			if(CheckNullUtil.integerNotNull(inputStatus))
				criteria.andStatusEqualTo(inputStatus);
			if(CheckNullUtil.isNotEmpty(marketName)){
				//通过marketName获取projectId
				Integer pid = lgProjectCustomMapper.getProjectIdByMarketName("%"+marketName+"%");
				if(CheckNullUtil.integerNotNull(pid))
					criteria.andProjectIdEqualTo(pid);
			}
			//筛选审核通过时间
			if (CheckNullUtil.integerNotNull(startTime))
				criteria.andFinishTimeGreaterThanOrEqualTo(startTime);
			if(CheckNullUtil.integerNotNull(endTime))
				criteria.andFinishTimeLessThanOrEqualTo(endTime);
			
		example.setOrderByClause("status asc");
		PageHelper.startPage(page, rows);
		List<LgSalary> list = LgSalaryMapper.selectByExample(example);
		List<SalaryOutputBean> outputBeansList = new ArrayList<>();
		if(list != null && list.size() > 0){
			SalaryOutputBean outputBean =  null;
			Integer eEmployeeId = 0;
			Integer eProjectId = 0;
		//	Integer eSalaryType = 0;
		//	Integer eDesignContentType = 0;
			String employeeName = "unknown";
			String projectMarketName = "unknown";
			//查询收入类型名称 和 设计类型名称
			LgSalaryExtraExample extraExample = new LgSalaryExtraExample();
			List<LgSalaryExtra> extrasList = LgSalaryExtraMapper.selectByExample(extraExample);
			Map<Integer,String> salaryMap = new HashMap<>(); 
			Map<Integer, String> designContentMap = new HashMap<>();
			for(LgSalaryExtra extraElement : extrasList){
				if(extraElement.getType() == 0)
					salaryMap.put(extraElement.getTid(), extraElement.getTypeName());
				else
					designContentMap.put(extraElement.getTid(), extraElement.getTypeName());
			}
			
		for(LgSalary element : list){
			//查询员工姓名
			eEmployeeId = element.getEmployeeId();
			employeeName = lgEmployeeCustomMapper.getEmployeeNameById(eEmployeeId);
			//查询工程市场名称
			eProjectId = element.getProjectId();
			projectMarketName = lgProjectCustomMapper.getProjectMarketName(eProjectId);
			outputBean = new SalaryOutputBean(element,employeeName,projectMarketName,salaryMap,designContentMap);
			outputBeansList.add(outputBean);
		 }
		}
		PageInfo<?> pageInfo = new PageInfo<>(outputBeansList);
		return pageInfo;
	}
	//修改工资记录
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map modifySalaryById(LgSalary salary) {
		Integer sid = salary.getSid();
		if(CheckNullUtil.integerNull(sid))
			throw new ServiceException("sid为空");
		//状态：0-未审核；1-审核成功；2-已关闭；3-标记删除
		Integer status = salary.getStatus();
		/*if(CheckNullUtil.integerNotNull(status)){
			if(status == 0 || status == 1 ||status == 3) // 只能为2
				throw new ServiceException("接口调用错误");
		}*/
		if(status != null && status == 1){
			if(salary.getFinishTime() == null)
				throw new ServiceException("计入时间为空");
		}
		int updaSta = LgSalaryMapper.updateByPrimaryKeySelective(salary);
		if(updaSta < 1)
			throw new ServiceException("修改失败");
		return ReturnResult.ok();
	}
	//删除记录
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map deleteSalaryById(Integer sid) {
		if(CheckNullUtil.integerNull(sid))
			throw new ServiceException("sid为空");
		LgSalary salary = new LgSalary();
		salary.setSid(sid);
		salary.setStatus(3);
		int delSta = LgSalaryMapper.updateByPrimaryKeySelective(salary);
		if(delSta < 1)
			throw new ServiceException("删除失败");
		return ReturnResult.ok();
	}
	//审核收入通过
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map setSuccessSalary(Integer sid) throws ServiceException {
		
		LgSalary lgSalary = LgSalaryMapper.selectByPrimaryKey(sid);
		if(lgSalary == null)
			throw new ServiceException("获取不到记录");
		if(lgSalary.getStatus() != 0)
			throw new ServiceException("该收入非审核状态");
		//可审核，才开始审核
		LgSalary salary = new LgSalary();
		salary.setSid(sid);
		salary.setStatus(1);
		salary.setFinishTime(FormatUtil.timeStampInt());
		int susSta = LgSalaryMapper.updateByPrimaryKeySelective(salary);
		if(susSta < 1)
			throw new ServiceException("审核通过失败");
		//审核通过之后，修改total_count表

		/*Integer salaryTypeId = lgSalary.getSalaryType();
		int insTotalSalary = 0;
		int insCommission = 0;
		int insReward = 0;
		int insTravelMoney = 0;
		int insOtherMoney = 0;

		if(salaryTypeId == 1){
			//提成
			insCommission = lgSalary.getSalaryMoney();
		}
		else if(salaryTypeId == 2 || salaryTypeId == 4){
			//阶段奖金 或 业绩奖金
			insReward = lgSalary.getSalaryMoney();
		}
		else if(salaryTypeId == 3){
			//出差
			insTravelMoney = lgSalary.getSalaryMoney();
		}
		else if(salaryTypeId == 5){
			//底薪
			insTotalSalary = lgSalary.getSalaryMoney();
		}
		else{
			//其他
			insOtherMoney = lgSalary.getSalaryMoney();
		}
		LgTotalCountExample totalCountExample = new LgTotalCountExample();
		 com.light.pojo.LgTotalCountExample.Criteria criteria = totalCountExample.createCriteria();
		 //获取员工当月的总计表记录
		 if(CheckNullUtil.integerNull(lgSalary.getEmployeeId())) 
			 throw new ServiceException("员工id为空");
		 criteria.andUserIdEqualTo(lgSalary.getEmployeeId()).andDateNoEqualTo(FormatUtil.getCurDateNo());
		List<LgTotalCount> list = LgTotalCountMapper.selectByExample(totalCountExample);
		 if(list == null || list.size() < 1){
			 //没有员工当月总计记录
		    LgTotalCount newTotalCount = new LgTotalCount();
			 newTotalCount.setUserId(lgSalary.getEmployeeId());
			 newTotalCount.setDateNo(FormatUtil.getCurDateNo());
			 newTotalCount.setTotalSalary(insTotalSalary);
			 newTotalCount.setCommission(insCommission);
			 newTotalCount.setReward(insReward);
			 newTotalCount.setTravelMoney(insTravelMoney);
			 newTotalCount.setBonus(0);
			 newTotalCount.setPersonSalary(0);
			 newTotalCount.setStatus(0);
			 newTotalCount.setOtherMoney(insOtherMoney);
			if(LgTotalCountMapper.insertSelective(newTotalCount) < 1)
				throw new ServiceException("插入总计表失败");
			
		 }else{
			 //有员工当月记录
			 LgTotalCount OriginRecord = list.get(0);
			 
			 LgTotalCount newTotalCount = new LgTotalCount();
			 newTotalCount.setId(OriginRecord.getId());
			 newTotalCount.setTotalSalary(OriginRecord.getTotalSalary()+insTotalSalary);
			 newTotalCount.setCommission(OriginRecord.getCommission()+insCommission);
			 newTotalCount.setReward(OriginRecord.getReward()+insReward);
			 newTotalCount.setTravelMoney(OriginRecord.getTravelMoney()+insTravelMoney);
			 newTotalCount.setOtherMoney(OriginRecord.getOtherMoney()+insOtherMoney);
			 if(LgTotalCountMapper.updateByPrimaryKeySelective(newTotalCount) < 1)
					throw new ServiceException("更新员工总计表失败");
		 }
		 */
		return ReturnResult.ok();
	}
	//编辑工资类型
	@Override
	public Map editSalaryExtra(LgSalaryExtra extra) throws ServiceException {
		Integer tid = extra.getTid();
		String typeName = extra.getTypeName();
		Integer typeMoney = extra.getTypeMoney();
		if(CheckNullUtil.integerNull(tid))
			throw new ServiceException("tid为空");
		if(tid == 12 || tid == 24 || tid ==25)
			throw new ServiceException("该类型不允许更改");
		
		/*LgSalaryExtra record = new LgSalaryExtra();
			record.setTid(tid);
			if(CheckNullUtil.isNotEmpty(typeName))
				record.setTypeName(typeName);
			if(CheckNullUtil.integerNotNull(typeMoney))
				record.setTypeMoney(typeMoney);*/
		
		if(LgSalaryExtraMapper.updateByPrimaryKeySelective(extra)<1)
			throw new ServiceException("编辑失败");
		
		return ReturnResult.ok();
	}
	//删除工资类型
	@Override
	public Map deleteSalaryExtra(Integer tid) throws ServiceException {
		if(CheckNullUtil.integerNull(tid))
			throw new ServiceException("id为空");
		if(tid == 12 || tid == 24 || tid ==25)
			throw new ServiceException("该类型不允许删除");

		if(LgSalaryExtraMapper.deleteByPrimaryKey(tid) < 1)
			throw new ServiceException("删除失败");
		return ReturnResult.ok();
	}
	//增加工资类型
	@Override
	public Map addSalaryExtra(LgSalaryExtra extra) throws ServiceException {
		Integer type = extra.getType();
		String typeName = extra.getTypeName();
		Integer typeMoney = extra.getTypeMoney();
		
		if(CheckNullUtil.integerNull(type))
			throw new ServiceException("type为空");
		if(CheckNullUtil.isEmpty(typeName))
			throw new ServiceException("typeName为空");
		if(type == 1 && CheckNullUtil.integerNull(typeMoney))
			throw new ServiceException("金额为空");
		
		if(LgSalaryExtraMapper.insertSelective(extra) < 1)
			throw new ServiceException("插入失败");
		
		return ReturnResult.ok();
	}



}
