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
import com.light.mapper.LgCaseApplyMapper;
import com.light.mapper.LgCaseMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgCase;
import com.light.pojo.LgCaseApply;
import com.light.pojo.LgCaseExample;
import com.light.service.CaseService;

@Service
@Transactional
public class CaseServiceImpl implements CaseService {

	@Autowired
	LgCaseMapper LgCaseMapper;
	@Autowired
	LgCaseApplyMapper LgCaseApplyMapper;
	@Autowired
	LgPublicMapper LgPublicMapper;
	//获取案例列表
	@Override
	public PageInfo getCaseList(LgCase lgCase, Integer page, Integer rows,
			Integer startTime,Integer endTime) throws ServiceException{
		
			LgCaseExample example = new LgCaseExample();
			com.light.pojo.LgCaseExample.Criteria criteria = example.createCriteria();
			Integer cid = lgCase.getCid();
			Integer userId = lgCase.getUserId();
			String caseName = lgCase.getCaseName();
			Integer level = lgCase.getLevel();
			
			Integer state = lgCase.getState();
			
			if(CheckNullUtil.integerNotNull(state))
				criteria.andStateEqualTo(state);
			if(CheckNullUtil.integerNotNull(cid))
				criteria.andCidEqualTo(cid);
			if(CheckNullUtil.integerNotNull(userId))
				criteria.andUserIdEqualTo(userId);
			if(CheckNullUtil.isNotEmpty(caseName))
				criteria.andCaseNameLike("%"+caseName+"%");
			if(CheckNullUtil.integerNotNull(level))
				criteria.andLevelEqualTo(level);
			if(FormatUtil.integerInterval(startTime, endTime))
				criteria.andCreateTimeBetween(startTime, endTime);
			else{
			if(CheckNullUtil.integerNotNull(startTime))
				criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
			else if(CheckNullUtil.integerNotNull(endTime))
				criteria.andCreateTimeLessThan(endTime);
			}
			//增加排序
			example.setOrderByClause("create_time desc");
			
			PageHelper.startPage(page,rows);
			List<LgCase> list = LgCaseMapper.selectByExample(example);
			PageInfo pageInfo = new PageInfo<>(list);
				return pageInfo;
		
	}
	//发布一个新案例
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map addPublishCase(LgCase lgCase) throws ServiceException{
		Integer userId = lgCase.getUserId();
		String imgUrl = lgCase.getImgUrl();
		String caseName = lgCase.getCaseName();
		//Integer level = lgCase.getLevel();
		String description = lgCase.getDescription();
		String area = lgCase.getArea();
		
		//1.插入一条新案例记录
		if(CheckNullUtil.integerNull(userId))
			throw new ServiceException("用户id为空");
		LgCase newCase = new LgCase();
			newCase.setUserId(userId);
			newCase.setImgUrl(imgUrl);
			newCase.setCaseName(caseName);
			newCase.setLevel(0);
			newCase.setDescription(description);
			newCase.setCreateTime(FormatUtil.timeStampInt());
			newCase.setArea(area);
			newCase.setState(0);
			
		int insertSta = LgCaseMapper.insertSelective(newCase);
		if(insertSta < 1)
			//return ReturnResult.wrong("插入案例失败");
			throw new ServiceException("插入案例失败");
		//2.新增一条案例申请
		Integer lastCaseId = LgPublicMapper.selectLastInsertId();
		LgCaseApply apply = new LgCaseApply();
		apply.setUserId(userId);
		apply.setCaseId(lastCaseId);
		apply.setCreateTime(FormatUtil.timeStampInt());
		apply.setState(0);
		int applyInsert = LgCaseApplyMapper.insertSelective(apply);
		if(applyInsert < 1)
			throw new ServiceException("插入申请表失败");
		Integer lastApplyId = LgPublicMapper.selectLastInsertId();

		Map rsMap = new HashMap<>();
		rsMap.put("caseId", lastCaseId);
		rsMap.put("applyId", lastApplyId);
		return ReturnResult.ok("插入成功", rsMap);
	}
	//修改案例详细信息
	@Override
	public Map editCase(LgCase lgCase) throws ServiceException {
		if(lgCase ==null)
	    	throw new ServiceException("更新信息为空");

			Integer caseId = lgCase.getCid();
			String caseName = lgCase.getCaseName();
			Integer level = lgCase.getLevel();
			String description = lgCase.getDescription();
			String area = lgCase.getArea();
			Integer state = lgCase.getState();
			
			LgCase record = new LgCase();
			
			if(CheckNullUtil.integerNull(caseId))
				throw new ServiceException("caseId为空");
			record.setCid(caseId);//设置主键
			if(CheckNullUtil.isNotEmpty(caseName))
				record.setCaseName(caseName);
			if(CheckNullUtil.integerNotNull(level))
				record.setLevel(level);
			if(CheckNullUtil.isNotEmpty(description))
				record.setDescription(description);
			if(CheckNullUtil.isNotEmpty(area))
				record.setArea(area);
			if(CheckNullUtil.integerNotNull(state))
				record.setState(state);
		    int updateStatus = LgCaseMapper.updateByPrimaryKeySelective(record);
		    if(updateStatus < 1)
		    	throw new ServiceException("更新失败");
		
		return ReturnResult.ok("更新信息成功", null);
	}
	//修改案例申请状态
	@Override
	public Map editCaseApply(Integer applyId, Integer caseId, Integer status) throws ServiceException {
		if(CheckNullUtil.integerNull(caseId) || 
		   CheckNullUtil.integerNull(status) ||
		   CheckNullUtil.integerNull(applyId))
			throw new ServiceException("参数为空");
		LgCaseApply apply = new LgCaseApply();
			apply.setCaid(applyId);
			apply.setCaseId(caseId);
			apply.setState(status);
		int updateStatus = LgCaseApplyMapper.updateByPrimaryKeySelective(apply);
	    if(updateStatus < 1)
	    	throw new ServiceException("案例申请更新失败");
	    LgCase lgCase = new LgCase();
	    	lgCase.setCid(caseId);
	    	lgCase.setState(1);//1-使用中
	    int caseUpdate = LgCaseMapper.updateByPrimaryKeySelective(lgCase);
	    if(caseUpdate < 1)
	    	throw new ServiceException("案例状态更新失败");
		return ReturnResult.ok("更新信息成功", null);
	}
	//获取案例详情
	@Override
	public LgCase getCaseDetail(Integer caseId) throws ServiceException {
		if(caseId == null)
			throw new ServiceException("caseId为空");
		LgCase lgCase = LgCaseMapper.selectByPrimaryKey(caseId);
		return lgCase;
	}

}
