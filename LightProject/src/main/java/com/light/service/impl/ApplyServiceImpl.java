package com.light.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.ReturnResult;
import com.light.exception.ServiceException;
import com.light.mapper.LgOtherApplyMapper;
import com.light.pojo.LgOtherApply;
import com.light.pojo.LgOtherApplyExample;
import com.light.pojo.LgOtherApplyExample.Criteria;
import com.light.service.ApplyService;

@Service
public class ApplyServiceImpl implements ApplyService {

	@Autowired 
	LgOtherApplyMapper lgOtherApplyMapper;
	
	@Override
	public Map addApply(LgOtherApply apply) throws ServiceException {
		Integer userId = apply.getUserId();
		String name = apply.getName();
		String mobile = apply.getMobile();
		String typeName = apply.getTypeName();
		Integer type = apply.getType();
		
		if(CheckNullUtil.integerNull(userId))
			throw new ServiceException("userId为空");
		if(CheckNullUtil.isEmpty(name))
			throw new ServiceException("name为空");
		if(CheckNullUtil.isEmpty(mobile))
			throw new ServiceException("mobile为空");
		if(CheckNullUtil.isEmpty(typeName))
			throw new ServiceException("typeName为空");
		if(CheckNullUtil.integerNull(type))
			throw new ServiceException("type为空");
		
		apply.setApplyStatus(0);
		if(lgOtherApplyMapper.insertSelective(apply)<1)
			throw new ServiceException("插入异常");
		
		return ReturnResult.ok();
	}

	@Override
	public Map updateApply(LgOtherApply apply) throws ServiceException {
		if(apply.getId() == null)
			throw new ServiceException("id为空");
		if(lgOtherApplyMapper.updateByPrimaryKeySelective(apply)<1)
			throw new ServiceException("更新失败");
		return ReturnResult.ok();
	}

	@Override
	public PageInfo<LgOtherApply> selectApply(LgOtherApply apply, Integer page, Integer rows) throws ServiceException {
		Integer userId = apply.getUserId();
		String name = apply.getName();
		Integer type = apply.getType();
		Integer applyStatus = apply.getApplyStatus();
		
		LgOtherApplyExample example = new LgOtherApplyExample();
		Criteria criteria = example.createCriteria();
		
		if(CheckNullUtil.integerNotNull(userId))
			criteria.andUserIdEqualTo(userId);
		if(CheckNullUtil.isNotEmpty(name))
			criteria.andNameLike("%"+name+"%");
		if(CheckNullUtil.integerNotNull(type))
			criteria.andTypeEqualTo(type);
		if(CheckNullUtil.integerNotNull(applyStatus))
			criteria.andApplyStatusEqualTo(applyStatus);
		
		PageHelper.startPage(page,rows);
		List<LgOtherApply> list = lgOtherApplyMapper.selectByExample(example);
		PageInfo<LgOtherApply> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public Map deleteApply(Integer id) throws ServiceException {
		if(CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		if(lgOtherApplyMapper.deleteByPrimaryKey(id)<1)
			throw new ServiceException("删除失败");
		
		return ReturnResult.ok();
	}

}
