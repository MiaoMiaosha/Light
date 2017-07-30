package com.light.service.impl;

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
import com.light.exception.ServiceException;
import com.light.mapper.LgEmployeeMapper;
import com.light.mapper.LgLoginUserMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgCustomer;
import com.light.pojo.LgEmployee;
import com.light.pojo.LgEmployeeExample;
import com.light.pojo.LgLoginUser;
import com.light.pojo.LgLoginUserExample;
import com.light.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired LgEmployeeMapper LgEmployeeMapper; 
	@Autowired LgPublicMapper LgPublicMapper;
	@Autowired LgLoginUserMapper LgLoginUserMapper;
 	
	//注册员工
	@Override
	public Map commitEmployee(LgEmployee employee,String username, String password) {
		if(employee == null)
			return ReturnResult.wrong();
		if(CheckNullUtil.isEmpty(username))
			throw new ServiceException("员工登录账号为空");
		//验证账号是否已存在
		LgLoginUserExample validExa = new LgLoginUserExample();
			validExa.createCriteria().andUsernameEqualTo(username);
		List validList = LgLoginUserMapper.selectByExample(validExa);
		if(validList != null && validList.size() > 0)
			throw new ServiceException("账号已存在，请更换账号名");
		
		employee.setRegisterTime(FormatUtil.timeStampInt());
		int insertSta = LgEmployeeMapper.insertSelective(employee);
		if(insertSta < 1)
			return ReturnResult.wrong();
		int insertNewId = LgPublicMapper.selectLastInsertId();
		Integer job = employee.getJob();
		if(job == null)
			throw new ServiceException("job不能为空");
		String roleIds = "0";
		if(job == 1 || job == 2)
			roleIds ="3"; //员工
		else if(job == 3){
			roleIds = "2";//会计
		}
		//插入login_user表
		LgLoginUser newUser = new LgLoginUser();
		newUser.setUserTypeId(insertNewId);
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setRoleIds(roleIds);
		newUser.setIsLocked(0);
		newUser.setUserType(1);
		int inserSta2 = LgLoginUserMapper.insertSelective(newUser);
		if(inserSta2 < 1)
			return ReturnResult.wrong();
		int insertNewId2 = LgPublicMapper.selectLastInsertId();

		Map rsMap = new HashMap<>();
		rsMap.put("employeeId", insertNewId);
		rsMap.put("loginUserId", insertNewId2);
		rsMap.put("roleIds", roleIds);
		return ReturnResult.ok(rsMap);
	}
	//获取所有员工列表
	@Override
	public PageInfo getEmployeeList(LgEmployee employee, Integer page, Integer rows) {
		
		LgEmployeeExample example = new LgEmployeeExample();
		if(employee != null){
			Integer eid = employee.getEid();
			String name = employee.getName();
			Integer inputStatus = employee.getStatus();
			Integer job = employee.getJob();
			com.light.pojo.LgEmployeeExample.Criteria criteria = example.createCriteria();
			criteria.andStatusNotEqualTo(2);//2为标记删除状态
			if(CheckNullUtil.integerNotNull(eid))
				criteria.andEidEqualTo(eid);
			if(CheckNullUtil.isNotEmpty(name))
				criteria.andNameEqualTo(name);
			if(CheckNullUtil.integerNotNull(inputStatus))
				criteria.andStatusEqualTo(inputStatus);
			if(CheckNullUtil.integerNotNull(job))
				criteria.andJobEqualTo(job);
			
		}
		example.setOrderByClause("eid desc");
		PageHelper.startPage(page, rows);
		List<LgEmployee> list = LgEmployeeMapper.selectByExample(example);
		 if(CheckNullUtil.listNotNull(list)){
			 for(LgEmployee element : list){
				 Integer eid = element.getEid();
				 if(CheckNullUtil.integerNotNull(eid)){
					 LgLoginUserExample newExa = new LgLoginUserExample();
					 	newExa.createCriteria().andUserTypeIdEqualTo(eid).andRoleIdsEqualTo("3");
					 List<LgLoginUser> eList = LgLoginUserMapper.selectByExample(newExa);
					 if(eList != null){
						 LgLoginUser getUser = eList.get(0);
						 element.setUsername(getUser.getUsername());
						 element.setPassword(getUser.getPassword());
					 }
				 }
					 
			 }
		 }
		
		
		
		
		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	//编辑员工
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map editEmployee(LgEmployee employee,String username,String password) throws ServiceException {
		if(employee.getEid() == null)
			throw new ServiceException("eid为空");
		Integer status = employee.getStatus();
		if(status != null){
			if(status == 2)
				throw new ServiceException("接口调用错误");
			else if (status == 0) {
				LgLoginUserExample example = new LgLoginUserExample();
		    	example.createCriteria().andRoleIdsEqualTo("3").andUserTypeIdEqualTo(employee.getEid());
		    	LgLoginUser record = new LgLoginUser();
		    		record.setIsLocked(0);//解冻
		    	if(LgLoginUserMapper.updateByExampleSelective(record, example)<1)
		    		throw new ServiceException("修改状态失败");
			}else if(status == 1){
				LgLoginUserExample example = new LgLoginUserExample();
		    	example.createCriteria().andRoleIdsEqualTo("3").andUserTypeIdEqualTo(employee.getEid());
		    	LgLoginUser record = new LgLoginUser();
		    		record.setIsLocked(1);//冻结
		    	if(LgLoginUserMapper.updateByExampleSelective(record, example)<1)
		    		throw new ServiceException("修改状态失败");
			}
		}
		
	    if(LgEmployeeMapper.updateByPrimaryKeySelective(employee) < 1)
			throw new ServiceException("更新表失败");
	    if(CheckNullUtil.isNotEmpty(username) && 
	    		CheckNullUtil.isNotEmpty(password)){
	    		//修改账号密码
	    	LgLoginUserExample example = new LgLoginUserExample();
	    	example.createCriteria().andRoleIdsEqualTo("3").andUserTypeIdEqualTo(employee.getEid());
	    	LgLoginUser record = new LgLoginUser();
	    		record.setUsername(username);
	    		record.setPassword(password);
	    	if(LgLoginUserMapper.updateByExampleSelective(record, example)<1)
	    		throw new ServiceException("修改账号密码失败");
	    }

		return ReturnResult.ok();
	}
	//标记删除员工
	@Override
	public Map deleteEmployee(Integer eid) {
		LgEmployee employee = new LgEmployee();
		employee.setStatus(2);//标记删除
		employee.setEid(eid);
		if(LgEmployeeMapper.updateByPrimaryKeySelective(employee) < 1)
			throw new ServiceException("标记删除失败");
		return ReturnResult.ok();
	}
	@Override
	public PageInfo getEmployeeListExceptKuaiJi(LgEmployee employee, Integer page, Integer rows) {
		LgEmployeeExample example = new LgEmployeeExample();
			Integer eid = employee.getEid();
			String name = employee.getName();
			Integer inputStatus = employee.getStatus();
			Integer job = employee.getJob();
			
			com.light.pojo.LgEmployeeExample.Criteria criteria = example.createCriteria();
			criteria.andStatusNotEqualTo(2);//2为标记删除状态
			criteria.andJobNotEqualTo(3);//职称，1-设计师，2-合同人，3-会计
			if(CheckNullUtil.integerNotNull(eid))
				criteria.andEidEqualTo(eid);
			if(CheckNullUtil.isNotEmpty(name))
				criteria.andNameEqualTo(name);
			if(CheckNullUtil.integerNotNull(inputStatus))
				criteria.andStatusEqualTo(inputStatus);
			if(CheckNullUtil.integerNotNull(job))
				criteria.andJobEqualTo(job);
			
		PageHelper.startPage(page, rows);
		List list = LgEmployeeMapper.selectByExample(example);
		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;

	}


}
