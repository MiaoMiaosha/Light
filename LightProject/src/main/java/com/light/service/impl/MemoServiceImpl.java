package com.light.service.impl;

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
import com.light.mapper.LgMemoMapper;
import com.light.pojo.LgMemo;
import com.light.pojo.LgMemoExample;
import com.light.pojo.LgMemoExample.Criteria;
import com.light.service.MemoService;
@Service
public class MemoServiceImpl implements MemoService {

	@Autowired
	LgMemoMapper LgMemoMapper;
	@Override
	public Map addNewMemo(LgMemo lgMemo) throws ServiceException {
		Integer projectId = lgMemo.getProjectId();
		Integer processId = lgMemo.getProcessId();
		Integer loginUserId = lgMemo.getLoginUserId();
		if(CheckNullUtil.integerNull(projectId))
			throw new ServiceException("projectId为空");
		if(CheckNullUtil.integerNull(processId))
			throw new ServiceException("processId为空");
		if(CheckNullUtil.integerNull(loginUserId))
			throw new ServiceException("loginUserId为空");
		
		lgMemo.setCreateTime(FormatUtil.timeStampInt());
		
		if(LgMemoMapper.insertSelective(lgMemo) < 1)
			throw new ServiceException("插入失败");
		
		return ReturnResult.ok();
	}

	@Override
	public PageInfo<?> getMemoList(LgMemo lgMemo, Integer page, Integer rows) {
		Integer id = lgMemo.getId();
		Integer projectId = lgMemo.getProjectId();
		Integer processId = lgMemo.getProcessId();
		Integer loginUserId = lgMemo.getLoginUserId();
		
		LgMemoExample example = new LgMemoExample();
			Criteria criteria = example.createCriteria();
		example.setOrderByClause("create_time asc");
		
		if(CheckNullUtil.integerNotNull(id))
			criteria.andIdEqualTo(id);
		if(CheckNullUtil.integerNotNull(projectId))
			criteria.andProjectIdEqualTo(projectId);
		if(CheckNullUtil.integerNotNull(processId))
			criteria.andProcessIdEqualTo(processId);
		if(CheckNullUtil.integerNotNull(loginUserId))
			criteria.andLoginUserIdEqualTo(loginUserId);
		
		PageHelper.startPage(page, rows);
		List<LgMemo> list = LgMemoMapper.selectByExample(example);
		PageInfo<?> pageInfo = new PageInfo<>(list);
		
		return pageInfo;
	}

	@Override
	public Map editMemo(LgMemo lgMemo) throws ServiceException {
		Integer id = lgMemo.getId();
		String content = lgMemo.getContent();
		if(CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		LgMemo record = new LgMemo();
		record.setId(id);
		record.setContent(content);

		if(LgMemoMapper.updateByPrimaryKeySelective(record)<1)
			throw new ServiceException("插入失败");
		return ReturnResult.ok();
	}

	@Override
	public Map deleteMemo(Integer id) throws ServiceException {
		if(CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		if(LgMemoMapper.deleteByPrimaryKey(id) < 1)
			throw new ServiceException("删除失败");
		return ReturnResult.ok();
	}

}
