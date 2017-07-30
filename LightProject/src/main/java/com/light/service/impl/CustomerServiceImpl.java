package com.light.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.FormatUtil;
import com.light.common.ReturnResult;
import com.light.exception.ServiceException;
import com.light.mapper.LgCustomerMapper;
import com.light.mapper.LgLoginUserMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgCustomer;
import com.light.pojo.LgCustomerExample;
import com.light.pojo.LgLoginUser;
import com.light.pojo.LgLoginUserExample;
import com.light.pojo.LgCustomerExample.Criteria;
import com.light.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired LgCustomerMapper LgCustomerMapper;
	@Autowired LgLoginUserMapper lgLoginUserMapper;
	@Autowired LgPublicMapper LgPublicMapper;
	
	//提交客户档案
	@Override
	public Map commitCustomer(LgCustomer lgCustomer,Integer userId,
			String username,String password) throws ServiceException {
		if(CheckNullUtil.isEmpty(username) || CheckNullUtil.isEmpty(password))
			throw new ServiceException("请输入正确的账号信息");
		//验证账号是否已存在
		LgLoginUserExample validExa = new LgLoginUserExample();
			validExa.createCriteria().andUsernameEqualTo(username);
		List validList = lgLoginUserMapper.selectByExample(validExa);
		if(validList != null && validList.size() > 0)
			throw new ServiceException("账号已存在，请更换账号名");
		
		lgCustomer.setStatus(0);
		lgCustomer.setCreateTime(FormatUtil.timeStampInt());
		lgCustomer.setLoginUserId(userId);
		
		int insertSta = LgCustomerMapper.insertSelective(lgCustomer);
		if(insertSta < 1)
			throw new ServiceException("插入表失败");
		int newCustomerId = LgPublicMapper.selectLastInsertId();

		//插入login_user表w
				LgLoginUser newUser = new LgLoginUser();
				newUser.setUserTypeId(newCustomerId);
				newUser.setUsername(username);
				newUser.setPassword(password);
				newUser.setRoleIds("4");
				newUser.setIsLocked(0);
				int inserSta2 = lgLoginUserMapper.insertSelective(newUser);
				if(inserSta2 < 1)
					throw new ServiceException("插入表失败");
		
		
		return ReturnResult.ok();
	}
   //获取客户档案
	@Override
	public PageInfo<?> getCustomerList(LgCustomer lgCustomer, Integer page, Integer rows, Integer startTime,
			Integer endTime) throws ServiceException{
		Integer cid = lgCustomer.getCid();
		String companyName = lgCustomer.getCompanyName();
		Integer inputStatus = lgCustomer.getStatus();
		
		LgCustomerExample example = new LgCustomerExample();
		 Criteria criteria = example.createCriteria();
		 criteria.andStatusNotEqualTo(3);//设置不搜索删除的记录
		 if(CheckNullUtil.integerNotNull(cid))
			 criteria.andCidEqualTo(cid);
			
			if(CheckNullUtil.integerNotNull(inputStatus))
				criteria.andStatusEqualTo(inputStatus);
		 
		 if(CheckNullUtil.isNotEmpty(companyName)){
			 criteria.andCompanyNameLike("%"+companyName+"%");
		 }
			if(FormatUtil.integerInterval(startTime, endTime))
				criteria.andCreateTimeBetween(startTime, endTime);
			else{
			if(CheckNullUtil.integerNotNull(startTime))
				criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
			else if(CheckNullUtil.integerNotNull(endTime))
				criteria.andCreateTimeLessThan(endTime);
			}
		example.setOrderByClause("create_time desc");
		 PageHelper.startPage(page, rows);
		 List<LgCustomer> list = LgCustomerMapper.selectByExample(example);
		 if(CheckNullUtil.listNotNull(list)){
			 for(LgCustomer element : list){
				 Integer customerId = element.getCid();
				 if(CheckNullUtil.integerNotNull(customerId)){
					 LgLoginUserExample newExa = new LgLoginUserExample();
					 	newExa.createCriteria().andUserTypeIdEqualTo(customerId).andRoleIdsEqualTo("4");
					 List<LgLoginUser> cusList = lgLoginUserMapper.selectByExample(newExa);
					 if(cusList != null){
						 LgLoginUser getUser = cusList.get(0);
						 element.setUsername(getUser.getUsername());
						 element.setPassword(getUser.getPassword());
					 }
				 }
					 
			 }
		 }
		 
		 
		 PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	//修改客户档案
	@Override
	public Map modifyCustomeryId(LgCustomer lgCustomer,String username,String password)  throws ServiceException{
		Integer status = lgCustomer.getStatus();
		Integer cid = lgCustomer.getCid();
		if(CheckNullUtil.integerNull(cid))
			throw new ServiceException("cid为空");
		if(CheckNullUtil.integerNotNull(status)){
			if(status == 3)
				throw new ServiceException("接口调用错误");
		}
		int updateSta = LgCustomerMapper.updateByPrimaryKeySelective(lgCustomer);
		if(updateSta < 1)
			throw new ServiceException("修改客户档案失败");
		if(CheckNullUtil.isNotEmpty(username) && CheckNullUtil.isNotEmpty(password)){
			LgLoginUserExample example =  new LgLoginUserExample();
				example.createCriteria().andRoleIdsEqualTo("4").andUserTypeIdEqualTo(cid);
			LgLoginUser record = new LgLoginUser();
				record.setUsername(username);
				record.setPassword(password);
			if(lgLoginUserMapper.updateByExampleSelective(record, example) < 1)
				throw new ServiceException("更新客户账号密码失败");
		}
		
		return ReturnResult.ok();
	}
	//删除客户档案
	@Override
	public Map deleteCustomerById(Integer cid) throws ServiceException{
		LgCustomer customer = new LgCustomer();
		customer.setCid(cid);
		customer.setStatus(3);//3-标记删除
		int updateSta = LgCustomerMapper.updateByPrimaryKeySelective(customer);
		if(updateSta < 1)
			throw new ServiceException("删除客户档案失败");
		return ReturnResult.ok();
	}

}
