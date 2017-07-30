package com.light.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.FormatUtil;
import com.light.common.LengthUtil;
import com.light.common.ReturnResult;
import com.light.dto.FloorInfoCommitBean;
import com.light.dto.MarketOutputBean;
import com.light.exception.ServiceException;
import com.light.mapper.LgFloorMapper;
import com.light.mapper.LgMarketApplyMapper;
import com.light.mapper.LgMarketMapper;
import com.light.mapper.LgRegionMapper;
import com.light.mapper.LgStallMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgFloor;
import com.light.pojo.LgMarket;
import com.light.pojo.LgMarketApply;
import com.light.pojo.LgMarketExample;
import com.light.pojo.LgMarketExample.Criteria;
import com.light.pojo.LgStall;
import com.light.service.MarketService;

@Service
public class MarketServiceImpl implements MarketService {

	@Autowired LgMarketMapper LgMarketMapper;
	@Autowired LgRegionMapper LgRegionMapper;
	@Autowired LgPublicMapper LgPublicMapper;
	@Autowired LgMarketApplyMapper LgMarketApplyMapper;
	@Autowired LgFloorMapper LgFloorMapper;
	@Autowired LgStallMapper LgStallMapper;
	//获取市场列表
	@Override
	public PageInfo getMarketList(LgMarket lgMarket, Integer page, Integer rows) throws ServiceException {
		LgMarketExample example = new LgMarketExample();
		Criteria criteria = example.createCriteria();
		
		example.setOrderByClause("create_time desc");
		
		Integer userId = lgMarket.getUserId();
		String marketName = lgMarket.getMarketName();
		String contactName = lgMarket.getContactName();
		String mobile = lgMarket.getContactMobile();
		Integer status = lgMarket.getStatus();
		Integer mid = lgMarket.getMid();
		
		if(CheckNullUtil.integerNotNull(userId))
			criteria.andUserIdEqualTo(userId);
		if(CheckNullUtil.isNotEmpty(marketName))
			criteria.andMarketNameEqualTo(marketName);
		if(CheckNullUtil.isNotEmpty(contactName))
			criteria.andContactNameEqualTo(contactName);
		if(CheckNullUtil.isNotEmpty(mobile))
			criteria.andContactMobileEqualTo(mobile);
		if(CheckNullUtil.integerNotNull(status))
			criteria.andStatusEqualTo(status);
		if(CheckNullUtil.integerNotNull(mid))
			criteria.andMidEqualTo(mid);
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(page,rows);
		List<LgMarket> list = LgMarketMapper.selectByExample(example);
		PageInfo pageInfo = new PageInfo<>(list);
		if(list != null && list.size() > 0){
		
		List<MarketOutputBean> outputList = new ArrayList<>();
		MarketOutputBean outputBean = null;
		String provinceName ="";
		String cityName ="";
		String districtName ="";
		String townName ="";
		Integer province = null;
		Integer city = null;
		Integer district = null;
		Integer town = null;

		for(LgMarket element : list){
			province = element.getProvince();
			city = element.getCity();
			district = element.getDistrict();
			town = element.getTown();
			
			if(CheckNullUtil.integerNotNull(province))
				provinceName = LgRegionMapper.selectByPrimaryKey(province).getName();
			if(CheckNullUtil.integerNotNull(city))
				cityName = LgRegionMapper.selectByPrimaryKey(city).getName();
			if(CheckNullUtil.integerNotNull(district))
				districtName = LgRegionMapper.selectByPrimaryKey(district).getName();
			if(CheckNullUtil.integerNotNull(town))
				townName = LgRegionMapper.selectByPrimaryKey(town).getName();

			outputBean = new MarketOutputBean(element,provinceName,cityName,districtName,townName);
			outputList.add(outputBean);
		 }
		PageInfo pageInfo1 = new PageInfo<>(outputList);
		pageInfo1.setTotal(pageInfo.getTotal());
		pageInfo1.setPageNum(rows);
		pageInfo1.setPages(pageInfo.getPages());
		return pageInfo1;
		
		}
		
		return pageInfo;
	}
	//菜市场申请
	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public Map applyForMarket(LgMarket lgMarket, String floorInfo) throws ServiceException {
		 Map rsMap = new HashMap<>();
		Integer userId = lgMarket.getUserId();
		if(CheckNullUtil.integerNull(userId))
			throw new ServiceException("用户Id为空");
		String marketName = lgMarket.getMarketName();
		Integer province = lgMarket.getProvince();
		Integer city = lgMarket.getCity();
		Integer district = lgMarket.getDistrict();
		String address = lgMarket.getAddress();
		String area = lgMarket.getArea();
		String marketCompany = lgMarket.getMarketCompany();
		String marketIntro = lgMarket.getMarketIntro();
		String contactName = lgMarket.getContactName();
		String contactMobile = lgMarket.getContactMobile();
		String meetAddress = lgMarket.getMeetAddress();
		String imgUrl = lgMarket.getImgUrl();
		
		//判断市场名称长度
		LengthUtil.checkCNStrLength(marketName,25);
		
		/*
		LgMarketExample marketNameExa = new LgMarketExample();
		 marketNameExa.createCriteria().andMarketNameEqualTo(marketName);
		 List<LgMarket> list = LgMarketMapper.selectByExample(marketNameExa);
		 if(list == null && list.size() < 1)
			 throw new ServiceException("该市场名已被占用");
		 */
		validMarketName(marketName);
		
		LgMarket record = new LgMarket();
		 record.setMarketName(marketName);
		 record.setProvince(province);
		 record.setCity(city);
		 record.setDistrict(district);
		 record.setAddress(address);
		 record.setArea(area);
		 record.setCreateTime(FormatUtil.timeStampInt());
		 record.setMarketCompany(marketCompany);
		 record.setMarketIntro(marketIntro);
		 record.setContactName(contactName);
		 record.setContactMobile(contactMobile);
		 record.setMeetAddress(meetAddress);
		 record.setImgUrl(imgUrl);
		 record.setStatus(0);
		 
		 record.setUserId(userId);
		 
		 int newInsert = LgMarketMapper.insertSelective(record);
		 if(newInsert < 1)
			 throw new ServiceException("插入失败");
		 
		 Integer newMarketId = LgPublicMapper.selectLastInsertId();
		 LgMarketApply apply =  new LgMarketApply();
			 apply.setUserId(userId);
			 apply.setMarketId(newMarketId);
			 apply.setCreateTime(FormatUtil.timeStampInt());
			 apply.setStatus(0);
		 int applyStatus = LgMarketApplyMapper.insertSelective(apply);
		 if(applyStatus < 1)
			 throw new ServiceException("插入申请表失败");
		 Integer newApplyId = LgPublicMapper.selectLastInsertId();
		 
		 //添加楼层信息
		 if(CheckNullUtil.isNotEmpty(floorInfo)){
			List<FloorInfoCommitBean>  floorList = JSONObject.parseArray(floorInfo,FloorInfoCommitBean.class);
			 if(CheckNullUtil.listNotNull(floorList)){
				 for(FloorInfoCommitBean element : floorList){
					 LgFloor floor = new LgFloor();
					 floor.setFloorName(element.getFloor());
					 floor.setIsUsed(true);
					 floor.setMarketId(newMarketId);
					 int insertSta = LgFloorMapper.insertSelective(floor);
					 if(insertSta < 1){
						 throw new ServiceException("添加楼层失败");
					 }
					 Integer newFloorId = LgPublicMapper.selectLastInsertId();
					 String[] stallNames = element.getStall().split(",");
					 for(String sElemet : stallNames){
						 LgStall stall = new LgStall();
						 stall.setStallName(sElemet);
						 stall.setMarketId(newMarketId);
						 stall.setFloorId(newFloorId);
						 stall.setCreateTime(FormatUtil.timeStampInt());
						 stall.setStatus(0);//未使用
						 stall.setContactName("市场管理方");
						 stall.setContactMobile("请联系市场");
						 int insertStallSta = LgStallMapper.insertSelective(stall);
						 if(insertStallSta < 1)
							 throw new ServiceException("添加摊位失败");
					 }
				 }
				 
			 }else
				 throw new ServiceException("楼层/摊位输入信息错误");
			
			 rsMap.put("floorInfo", floorInfo);
		 }
		 
		 rsMap.put("marketId", newMarketId);
		 rsMap.put("applyId", newApplyId);
		
		return ReturnResult.ok("插入新市场成功", rsMap);
	}
	//验证市场名称唯一性
	@Override
	public boolean validMarketName(String name) throws ServiceException {
		//判断市场名称长度
		LengthUtil.checkCNStrLength(name,25);
		LgMarketExample marketNameExa = new LgMarketExample();
		 marketNameExa.createCriteria().andMarketNameEqualTo(name);
		 List<LgMarket> list = LgMarketMapper.selectByExample(marketNameExa);
		 if(list == null && list.size() < 1)
			 throw new ServiceException("该市场名已被占用");
		return true;
	}
	//修改市场信息
	@Override
	public Map editMarket(LgMarket lgMarket) throws ServiceException {
        int upSta = LgMarketMapper.updateByPrimaryKeySelective(lgMarket);
        if(upSta < 1)
        	return ReturnResult.wrong("更新表失败");
		
		return ReturnResult.ok();
	}

}
