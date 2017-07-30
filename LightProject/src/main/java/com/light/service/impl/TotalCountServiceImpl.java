package com.light.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.FormatUtil;
import com.light.common.ReturnResult;
import com.light.dto.InputMybatis;
import com.light.dto.StaffSalaryCountBean;
import com.light.dto.TotalCountUserListBean;
import com.light.exception.ServiceException;
import com.light.mapper.LgBonusMapper;
import com.light.mapper.LgEmployeeMapper;
import com.light.mapper.LgTotalCountMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.mapper.custom.LgTotalCountCustomMapper;
import com.light.pojo.LgBonus;
import com.light.pojo.LgEmployee;
import com.light.pojo.LgEmployeeExample;
import com.light.pojo.LgSalary;
import com.light.pojo.LgTotalCount;
import com.light.pojo.LgTotalCountExample;
import com.light.service.TotalCountService;
@Service
public class TotalCountServiceImpl implements TotalCountService {

	@Autowired LgTotalCountMapper LgTotalCountMapper;
	@Autowired LgPublicMapper LgPublicMapper;
	@Autowired LgEmployeeMapper LgEmployeeMapper;
	@Autowired LgTotalCountCustomMapper LgTotalCountCustomMapper;
	
	//如果没有员工月统计记录，则创建，返回记录id
	@Override
	public Integer createEmployeeRecord(Integer userId, Integer dateNo) throws ServiceException {
		LgTotalCount lgTotalCount = new LgTotalCount(); 
			lgTotalCount.setUserId(userId);
			lgTotalCount.setDateNo(dateNo);
			lgTotalCount.setTotalSalary(0);
			lgTotalCount.setCommission(0);
			lgTotalCount.setReward(0);
			lgTotalCount.setTravelMoney(0);
			lgTotalCount.setBonus(0);
			lgTotalCount.setPersonSalary(0);
			lgTotalCount.setStatus(0);
		int insertSta = LgTotalCountMapper.insertSelective(lgTotalCount);
		if(insertSta < 1)
			throw new ServiceException("插入统计表失败");
		Integer insertId = LgPublicMapper.selectLastInsertId(); 
		return insertId;
	}
	//获取设计师排名列表
	@Override
	public List getDesignEmployeeList(Integer page,Integer rows,Integer startTime,Integer endTime) throws ServiceException {
		LgEmployeeExample employeeExample = new LgEmployeeExample();
		 PageHelper.startPage(page,rows);
		List<LgEmployee> employeeList = LgEmployeeMapper.selectByExample(employeeExample);
		//时间未设定，查询最近4个月的记录
		List<Integer> timeList = new ArrayList<>();
		for(int i=0; i<4; i++){
			timeList.add(FormatUtil.getPassOrFurtureDateNo(-i));
		}
		/*if(!FormatUtil.integerInterval(startTime, endTime)){
			startTime = FormatUtil.getPassOrFurtureDateNo(-3);
			endTime = FormatUtil.getPassOrFurtureDateNo(0);
		}*/
		
		
	   //对每个人查询近4个月的记录
		LgTotalCountExample totalCountExample = null;
		for(LgEmployee element : employeeList){
			totalCountExample = new LgTotalCountExample();
			totalCountExample.createCriteria().andUserIdEqualTo(element.getEid()).andDateNoBetween(startTime, endTime);
			LgTotalCountMapper.selectByExample(totalCountExample);
			//1.写时间的分页 2.提交员工id
			
		}
		//对每个月查询近4个人的记录
		return null;
	}
	
	@Override
	public PageInfo getAllEmployeeList(Integer type,Integer page, Integer rows, Integer startTime, Integer endTime)
			throws ServiceException {
		String keywordStr = "";
		if(CheckNullUtil.integerNull(type))
			throw new ServiceException("type为空");
		 switch (type) {
		case 1:
			keywordStr = "total_salary";			break;
		case 2:
			keywordStr = "commission";			break;
		case 3:
			keywordStr = "reward";			break;
		case 4:
			keywordStr = "travel_money";			break;		
		case 5:
			keywordStr = "bonus";			break;			
		case 6:
			keywordStr = "person_salary";			break;			
		case 7:
			keywordStr = "other_money";			break;			
		default:
			keywordStr = "other_money";
			break;
		}
	    InputMybatis inputParam = new InputMybatis(keywordStr);
		 PageHelper.startPage(page,rows);
        List<TotalCountUserListBean> list = LgTotalCountCustomMapper.getUserListDesc(inputParam);
         PageInfo<?> pageInfo =  new PageInfo<>(list);
		return pageInfo;
	}
	//获取最终结果
	@Override
	public Map getTotalResult(Integer page, Integer rows,String eids) throws ServiceException {
		 PageHelper.startPage(page,rows);
		List<Integer> timeList = LgTotalCountCustomMapper.getTimeInterval();
		
		String[] eidsArr = eids.split(":");
		//Map<Integer, Object> rsMap = new HashMap<>();
		List rsList = new ArrayList<>();
		Map userAndList = null;
		for(String eid : eidsArr){
			Integer eidInt = Integer.valueOf(eid);
			userAndList = new HashMap<>();
			List<Integer> employeDateNoList = new ArrayList<>();//个人一个时间段内，所有的数据
			for(Integer dateNo : timeList){
				InputMybatis obj = new InputMybatis("commission",eidInt,dateNo);
				List<Integer> resultList = LgTotalCountCustomMapper.specDataWithEidDateNo(obj);
				if(CheckNullUtil.listNotNull(resultList))employeDateNoList.add(resultList.get(0));
				else employeDateNoList.add(0);
			}
			//rsMap.put(eidInt, employeDateNoList);
			//rsList.add(employeDateNoList);
			userAndList.put("userId", eidInt);
			userAndList.put("dataList", employeDateNoList);
			rsList.add(userAndList);
		}
		Map rsMap = new HashMap<>();
		rsMap.put("timeList", timeList);
		rsMap.put("list", rsList);
		
        PageInfo<?> pageInfo =  new PageInfo<>(rsList);
		return ReturnResult.ok(rsMap, pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getPageNum());
	}
	
	@Override
	public Map getStaffDetail(Integer employeeId, Integer startTime, Integer endTime) {

		Map paramMap1 = new HashMap<>();
	//	Map paramMap2 = new HashMap<>();
		Map paramMap3 = new HashMap<>();
		
		if(CheckNullUtil.integerNotNull(employeeId)){
			paramMap1.put("employeeId", employeeId);
			//paramMap2.put("employeeId", employeeId);
			paramMap3.put("employeeId", employeeId);

		}
		if(CheckNullUtil.integerNotNull(startTime)){
			paramMap1.put("startTime", startTime);
			//paramMap2.put("startTime", startTime);
		}else{
			paramMap1.put("startTime", FormatUtil.getCurDateNo());
			//paramMap2.put("startTime", FormatUtil.getCurDateNo());

		}
		if(CheckNullUtil.integerNotNull(endTime)){
			paramMap1.put("endTime", endTime);
			//paramMap2.put("endTime", endTime);
		}else{
			paramMap1.put("endTime", FormatUtil.getCurMonthMaxTime());
		//	paramMap2.put("endTime", FormatUtil.getCurMonthMaxTime());

		}
		//paramMap1.put("status", 1);
		//paramMap2.put("status", 1);
		//paramMap3.put("status", 1);
		//paramMap1.put("startTime", FormatUtil.getCurDateNo());
		//paramMap1.put("endTime", FormatUtil.getCurMonthMaxTime());
		
		LgEmployee getEmployee = LgEmployeeMapper.selectByPrimaryKey(employeeId);
		if(getEmployee == null) throw new ServiceException("查找不到员工");
		Integer job = getEmployee.getJob();//职称，1-设计师，2-合同人，3-会计
		Map rsMap = new HashMap<>();
		//如果是设计师，查收入表
		if(job == 1){
			rsMap.put("job", 1);
			//月工资统计
			List<StaffSalaryCountBean> timeSalary = LgTotalCountCustomMapper.getSumSalary(paramMap1);
			//Integer sumReim = LgTotalCountCustomMapper.getSumReim(paramMap2);
			
			Map paramMap4 = new HashMap<>();
			paramMap4.put("employeeId", employeeId);
			paramMap4.put("startTime", FormatUtil.getCurDateNo());
			paramMap4.put("endTime", FormatUtil.getCurMonthMaxTime());
			paramMap4.put("status", 0);
			Integer monthsa = LgTotalCountCustomMapper.getSumNotAppSalary(paramMap4);
			StaffSalaryCountBean noappBeanSa = new StaffSalaryCountBean();
				noappBeanSa.setSumSalaryMoney(monthsa);
				noappBeanSa.setStatus(0);
			timeSalary.add(noappBeanSa);

			//总工资统计
			//只获取特定员工的，所有收入
			List<StaffSalaryCountBean> allTimeSalary = LgTotalCountCustomMapper.getSumSalary(paramMap3);
				rsMap.put("monthSalary", timeSalary);
				//rsMap.put("sumReim", sumReim);
				rsMap.put("totalSalary",allTimeSalary);
		}else if(job == 2){
			rsMap.put("job", 2);
			List<StaffSalaryCountBean> timeSalary = LgTotalCountCustomMapper.getSumBonus(paramMap1);
			//只获取特定员工的，所有分红，时间不限
			List<StaffSalaryCountBean> allTimeSalary = LgTotalCountCustomMapper.getSumBonus(paramMap3);
			
			Map paramMap4 = new HashMap<>();
			paramMap4.put("employeeId", employeeId);
			paramMap4.put("startTime", FormatUtil.getCurDateNo());
			paramMap4.put("endTime", FormatUtil.getCurMonthMaxTime());
			paramMap4.put("status", 0);
			Integer monthsa = LgTotalCountCustomMapper.getSumNotAppBonus(paramMap4);
			StaffSalaryCountBean noappBeanSa = new StaffSalaryCountBean();
				noappBeanSa.setBonusMoney(monthsa);
				noappBeanSa.setStatus(0);
			timeSalary.add(noappBeanSa);
			
			Map paramMap5 = new HashMap<>();
				paramMap5.put("employeeId", employeeId);
				paramMap5.put("status", 1);
			Integer totalPersonSalary = LgTotalCountCustomMapper.getPersonBusSalary(paramMap5);

			rsMap.put("totalPersonSalary", totalPersonSalary);
			rsMap.put("monthSalary", timeSalary);
			rsMap.put("totalSalary",allTimeSalary);
		}else {
			rsMap.put("job", 0);
		}
		//如果是合同人，查分红表
		return ReturnResult.ok(rsMap);
	}

}
