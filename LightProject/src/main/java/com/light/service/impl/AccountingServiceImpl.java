package com.light.service.impl;

import java.util.HashMap;
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
import com.light.mapper.LgAccountingExtraMapper;
import com.light.mapper.LgAccountingMapper;
import com.light.mapper.custom.LgProjectCustomMapper;
import com.light.pojo.LgAccounting;
import com.light.pojo.LgAccountingExample;
import com.light.pojo.LgAccountingExample.Criteria;
import com.light.pojo.LgAccountingExtra;
import com.light.pojo.LgAccountingExtraExample;
import com.light.service.AccountingService;
@Service
public class AccountingServiceImpl implements AccountingService {

	@Autowired LgAccountingMapper LgAccountingMapper;
	@Autowired LgAccountingExtraMapper LgAccountingExtraMapper;
	@Autowired LgProjectCustomMapper lgProjectCustomMapper;
	//提交入账信息
	@Override
	public Map commitAccounting(LgAccounting lgAccounting,Integer loginUserId) {
		if(lgAccounting == null)
			return ReturnResult.wrong();
		
		//Must Change!!!
		lgAccounting.setStatus(1);//入账信息无需审核
		lgAccounting.setUpdateTime(FormatUtil.timeStampInt());
		lgAccounting.setOperateNo(loginUserId);//0为未操作
		lgAccounting.setLoginUserId(loginUserId);
		//
		int insertSta = LgAccountingMapper.insertSelective(lgAccounting);
		if(insertSta < 1)
			return ReturnResult.wrong();
		return ReturnResult.ok();
	}
	//获取入账信息列表
	@Override
	public PageInfo<?> getAccountingList(LgAccounting lgAccounting, Integer page, Integer rows, Integer startTime,
			Integer endTime) {
		LgAccountingExample example =  new LgAccountingExample();
		
			Criteria criteria = example.createCriteria();
			Integer aid = lgAccounting.getAid();
			Integer typeId = lgAccounting.getTypeId();
			Integer inputStatus = lgAccounting.getStatus();
			
			if(CheckNullUtil.integerNotNull(aid))
				criteria.andAidEqualTo(aid);
			if(CheckNullUtil.integerNotNull(typeId))
				criteria.andTypeIdEqualTo(typeId);
			if(CheckNullUtil.integerNotNull(inputStatus))
				criteria.andStatusEqualTo(inputStatus);
			example.setOrderByClause("receive_date desc");
			criteria.andStatusNotEqualTo(3);//3是标记删除
			LgAccountingExtra extra = new LgAccountingExtra();
				extra.setTypeContent(0);
			List<LgAccountingExtra> extraList = getExtraInfoList(extra);
			Map<Integer, String> extraMap = new HashMap<>();
			for(LgAccountingExtra element : extraList){
				extraMap.put(element.getId(), element.getTypeName());
			}
		
		PageHelper.startPage(page, rows);
		List<LgAccounting> list = LgAccountingMapper.selectByExample(example);
		for(LgAccounting element : list){
			//Integer getAid = element.getAid();
			Integer getTypeId = element.getTypeId();
			Integer getProjectId = element.getProjectId();
			element.setTypeName(extraMap.get(getTypeId));
			String projectMarketName = lgProjectCustomMapper.getProjectMarketName(getProjectId);
			element.setProjectMarketName(projectMarketName);
		}
		PageInfo pageInfo  = new PageInfo<>(list);
		return pageInfo;
	}
	//修改入账记录
	@Override
	public Map modifyAccounting(LgAccounting lgAccounting,Integer loginUserId) {
		if(lgAccounting == null)
			throw new ServiceException("输入参数为空");
		Integer aid = lgAccounting.getAid();
		if(aid == null || aid == 0)
			throw new ServiceException("aid为空");
		//Must Change!!!
		//lgAccounting.setStatus(lgAccounting.getStatus());
		lgAccounting.setUpdateTime(FormatUtil.timeStampInt());
		lgAccounting.setOperateNo(loginUserId);//0为未操作
		//
		int updateSta = LgAccountingMapper.updateByPrimaryKeySelective(lgAccounting);
		if(updateSta < 1)
			throw new ServiceException("更新失败");
		return ReturnResult.ok();
	}
	//删除入账记录
	@Override
	public Map deleteAccountingById(Integer sid) {

		int delSta = LgAccountingMapper.deleteByPrimaryKey(sid);
		if(delSta < 1)
			return ReturnResult.wrong();
		
		return ReturnResult.ok();
	}
	//获取额外信息
	@Override
	public List getExtraInfoList(LgAccountingExtra extra) {
		
	  LgAccountingExtraExample example = new LgAccountingExtraExample();
	  if(extra != null){
		  com.light.pojo.LgAccountingExtraExample.Criteria criteria = example.createCriteria();
		  Integer  id = extra.getId();
		  Integer typeContent = extra.getTypeContent();
		  String typeName = extra.getTypeName();
		  
		  if(CheckNullUtil.integerNotNull(id))
			  criteria.andIdEqualTo(id);
		  if(CheckNullUtil.integerNotNull(typeContent))
			  criteria.andTypeContentEqualTo(typeContent);
		  if(CheckNullUtil.isNotEmpty(typeName))
			  criteria.andTypeNameEqualTo(typeName);
	  }
	  List list = LgAccountingExtraMapper.selectByExample(example);
		return list;
	}
	//确定入账状态
	@Override
	public Map confirmAccout(Integer accountId, Integer status,Integer loginUserId) {
		if(accountId == null || accountId == 0)
			return ReturnResult.wrong();
		//Must Change!!!
		LgAccounting lgAccounting = new LgAccounting();
		lgAccounting.setStatus(status);
		lgAccounting.setUpdateTime(FormatUtil.timeStampInt());
		lgAccounting.setOperateNo(loginUserId);//设置审核管理员id
		//
		int updateSta = LgAccountingMapper.updateByPrimaryKeySelective(lgAccounting);
		if(updateSta < 1)
			return ReturnResult.wrong();
		return ReturnResult.ok();
	}

}
