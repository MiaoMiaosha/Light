package com.light.service.impl;

import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.FormatUtil;
import com.light.common.ReturnResult;
import com.light.exception.ServiceException;
import com.light.mapper.LgPartnerMapper;
import com.light.pojo.LgPartner;
import com.light.pojo.LgPartnerExample;
import com.light.pojo.LgPartnerExample.Criteria;
import com.light.service.PartnerService;
@Service
public class PartnerServiceImpl implements PartnerService {

	@Autowired LgPartnerMapper LgPartnerMapper;
	//提交合作方档案
	@Override
	public Map commitPartner(LgPartner lgPartner) {
		if(lgPartner == null)
			return ReturnResult.wrong();
		
		//!!MUST CHANGE!!
		lgPartner.setCreateTime(FormatUtil.timeStampInt());
		lgPartner.setStatus(1);
		//
		
		int insertSta = LgPartnerMapper.insertSelective(lgPartner);
		if(insertSta < 1)
			return ReturnResult.wrong();
		return ReturnResult.ok();
	}

	//获取合作方列表
	@Override
	public PageInfo<?> getPartnerList(LgPartner lgPartner, Integer page, Integer rows, Integer startTime,
			Integer endTime) {
		LgPartnerExample example = new LgPartnerExample();
			Criteria criteria = example.createCriteria();
			Integer pid = lgPartner.getPid();
			String chiefPerson = lgPartner.getChiefPerson();
			Integer inputStatus = lgPartner.getStatus();
			
			criteria.andStatusNotEqualTo(3);//3-标记删除
			if(CheckNullUtil.integerNotNull(pid))
				criteria.andPidEqualTo(pid);
			if(CheckNullUtil.isNotEmpty(chiefPerson))
				criteria.andChiefPersonLike("%"+chiefPerson+"%");
			if(CheckNullUtil.integerNotNull(inputStatus))
				criteria.andStatusEqualTo(inputStatus);
			
			if(FormatUtil.integerInterval(startTime, endTime))
				criteria.andCreateTimeBetween(startTime, endTime);
			else{
			if(CheckNullUtil.integerNotNull(startTime))
				criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
			else if(CheckNullUtil.integerNotNull(endTime))
				criteria.andCreateTimeLessThan(endTime);
			}
		example.setOrderByClause("create_time");	
			
		PageHelper.startPage(page,rows);	
		List list = LgPartnerMapper.selectByExample(example);
		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
    //修改合作方档案
	@Override
	public Map modifyPartnerById(LgPartner lgPartner) {
		Integer status = lgPartner.getStatus();
		if(lgPartner.getPid() == null)
			throw new ServiceException("没有pid");
		if(status != null && status == 3)
			throw new ServiceException("接口调用错误");
		int updateSta = LgPartnerMapper.updateByPrimaryKeySelective(lgPartner);
		if(updateSta < 1)
			throw new ServiceException("修改表错误");
		return ReturnResult.ok();
	}
   //删除合作方档案
	@Override
	public Map deletePartnerById(Integer id) {
		LgPartner partner = new LgPartner();
		 partner.setPid(id);
		 partner.setStatus(3);
		int updateSta = LgPartnerMapper.updateByPrimaryKeySelective(partner);
		if(updateSta < 1)
			throw new ServiceException("删除失败");
		return ReturnResult.ok();
	}

}
