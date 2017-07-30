package com.light.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.mapper.LgCaseApplyMapper;
import com.light.mapper.LgCooperateCompanyMapper;
import com.light.mapper.LgMarketApplyMapper;
import com.light.mapper.LgNewsApplyMapper;
import com.light.mapper.LgStallApplyMapper;
import com.light.mapper.custom.LgApprovalCustomMapper;
import com.light.pojo.LgCaseApply;
import com.light.pojo.LgCaseApplyExample;
import com.light.pojo.LgCaseExample;
import com.light.pojo.LgCooperateCompany;
import com.light.pojo.LgMarketApply;
import com.light.pojo.LgMarketApplyExample;
import com.light.pojo.LgMarketExample;
import com.light.pojo.LgNewsApply;
import com.light.service.ApprovalService;

public class ApprovalServiceImpl implements ApprovalService {

	@Autowired LgCaseApplyMapper LgCaseApplyMapper;
	@Autowired LgMarketApplyMapper LgMarketApplyMapper;
	@Autowired LgStallApplyMapper LgStallApplyMapper;
	@Autowired LgNewsApplyMapper LgNewsApplyMapper;
	@Autowired LgCooperateCompanyMapper LgCooperateCompanyMapper;
	@Autowired LgApprovalCustomMapper lgApprovalCustomMapper;
	
	
	//获取案例申请列表
	@Override
	public PageInfo<?> getCaseApplyList(Integer page, Integer rows) {
		LgCaseApplyExample example = new LgCaseApplyExample();
		 example.createCriteria().andStateEqualTo(0);
		 
		 PageHelper.startPage(page, rows);
		 List<LgCaseApply> applies = LgCaseApplyMapper.selectByExample(example);
		 Map<String, Object> rsMap = null;
		 List finalList = new ArrayList<>();
		 for(LgCaseApply element : applies){
				 rsMap = new HashMap<>();
				 String caseName = lgApprovalCustomMapper.getCaseNameByCaseId(element.getCaseId());
				 rsMap.put("element", element);
				 rsMap.put("name",caseName);
			 finalList.add(rsMap);
		 }
		 PageInfo<?> pageInfo = new PageInfo<>(finalList);
		return pageInfo;
	}
	//获取市场申请列表
	@Override
	public PageInfo<?> getMarketApplyList(Integer page, Integer rows) {
		LgMarketApplyExample example =  new LgMarketApplyExample();
		 example.createCriteria().andStatusEqualTo(0);
		PageHelper.startPage(page, rows);
		List<LgMarketApply> applies = LgMarketApplyMapper.selectByExample(example);
			 Map<String, Object> rsMap = null;
			 List finalList = new ArrayList<>();
		for(LgMarketApply element : applies){
			rsMap = new HashMap<>();
			String marketName = lgApprovalCustomMapper.getMarketNameByMarketId(element.getMarketId());
			rsMap.put("element",element);
			rsMap.put("name", marketName);
			finalList.add(rsMap);
		}
		 PageInfo<?> pageInfo = new PageInfo<>(finalList);
		return pageInfo;
	}
	//获取摊位申请列表
	@Override
	public PageInfo<?> getStallApplyList(Integer page, Integer rows) {
		
		return null;
	}
	//获取新闻申请列表
	@Override
	public PageInfo<?> getNewsApplyList(Integer page, Integer rows) {
	
		return null;
	}
	//获取加盟申请列表
	@Override
	public PageInfo<?> getCooperateApplyList(Integer page, Integer rows) {

		return null;
	}
	//获取设备申请列表
	@Override
	public PageInfo<?> getGoodsApplyList(Integer page, Integer rows) {

		return null;
	}

}
