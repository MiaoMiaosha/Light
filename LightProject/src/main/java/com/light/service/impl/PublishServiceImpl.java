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
import com.light.mapper.LgCooperateCompanyApplyMapper;
import com.light.mapper.LgCooperateCompanyMapper;
import com.light.mapper.LgGoodsApplyMapper;
import com.light.mapper.LgGoodsMapper;
import com.light.mapper.LgMarketApplyMapper;
import com.light.mapper.LgMarketMapper;
import com.light.mapper.LgNewsApplyMapper;
import com.light.mapper.LgNewsMapper;
import com.light.mapper.LgStallApplyMapper;
import com.light.mapper.LgStallMapper;
import com.light.mapper.custom.LgManagePublishMapper;
import com.light.pojo.LgCase;
import com.light.pojo.LgCaseApply;
import com.light.pojo.LgCaseApplyExample;
import com.light.pojo.LgCaseApplyExample.Criteria;
import com.light.pojo.LgCooperateCompany;
import com.light.pojo.LgCooperateCompanyApply;
import com.light.pojo.LgCooperateCompanyApplyExample;
import com.light.pojo.LgGoods;
import com.light.pojo.LgGoodsApply;
import com.light.pojo.LgGoodsApplyExample;
import com.light.pojo.LgMarket;
import com.light.pojo.LgMarketApply;
import com.light.pojo.LgMarketApplyExample;
import com.light.pojo.LgNews;
import com.light.pojo.LgNewsApply;
import com.light.pojo.LgNewsApplyExample;
import com.light.pojo.LgStall;
import com.light.pojo.LgStallApply;
import com.light.pojo.LgStallApplyExample;
import com.light.service.PublishService;
@Service
public class PublishServiceImpl implements PublishService {
	
	@Autowired LgManagePublishMapper LgManagePublishMapper;
	@Autowired LgStallApplyMapper LgStallApplyMapper;
	@Autowired LgCaseApplyMapper LgCaseApplyMapper;
	@Autowired LgMarketApplyMapper LgMarketApplyMapper;
	@Autowired LgNewsApplyMapper LgNewsApplyMapper;
	@Autowired LgCooperateCompanyApplyMapper LgCooperateCompanyApplyMapper; 
	@Autowired LgGoodsApplyMapper LgGoodsApplyMapper;
	
	@Autowired LgCaseMapper LgCaseMapper;
	@Autowired LgMarketMapper LgMarketMapper;
	@Autowired LgNewsMapper LgNewsMapper;
	@Autowired LgCooperateCompanyMapper LgCooperateCompanyMapper;
	@Autowired LgStallMapper LgStallMapper;
	@Autowired LgGoodsMapper LgGoodsMapper;
	
	//获取个人案例发布信息
	@Override
	public PageInfo getCasePublish(LgCaseApply apply, Integer page, Integer rows,
			Integer startTime, Integer endTime,String order) throws ServiceException {
		Integer caidId = apply.getCaid();
		Integer userId = apply.getUserId();
		Integer caseId = apply.getCaseId();
		Integer state = apply.getState();
		
		Map paramMap = new HashMap<>();
		if(CheckNullUtil.integerNotNull(caidId))
			paramMap.put("caidId", caidId);
		if(CheckNullUtil.integerNotNull(userId))
			paramMap.put("userId", userId);
		if(CheckNullUtil.integerNotNull(caseId))
			paramMap.put("caseId", caseId);
		if(CheckNullUtil.integerNotNull(state))			
			paramMap.put("state", state);
		//时间间隔
		if(FormatUtil.integerInterval(startTime, endTime)){
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
		}
		else{
		if(CheckNullUtil.integerNotNull(startTime))
			paramMap.put("startTime", startTime);
		else if(CheckNullUtil.integerNotNull(endTime))
			paramMap.put("endTime", endTime);
		}
		
		if(CheckNullUtil.isNotEmpty(order))
			paramMap.put("order", order);
		
		PageHelper.startPage(page, rows);
		//List list = LgManagePublishMapper.getCasePubilishApplyList(example);
		List list = LgManagePublishMapper.getCasePubilishApplyList(paramMap);

		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
    //获取个人市场发布信息
	@Override
	public PageInfo getMarketPublish(LgMarketApply apply, Integer page, Integer rows,
			Integer startTime, Integer endTime, String order,String contactName,String contactMobile) throws ServiceException {
		
		Integer maid = apply.getMaid();
		Integer userId = apply.getUserId();
		Integer marketId = apply.getMarketId();
		Integer status = apply.getStatus();
		
		Map paramMap = new HashMap<>();
		if(CheckNullUtil.integerNotNull(maid))
			paramMap.put("maid", maid);
		if(CheckNullUtil.integerNotNull(userId))
			paramMap.put("userId", userId);
		if(CheckNullUtil.integerNotNull(marketId))
			paramMap.put("marketId", marketId);
		if(CheckNullUtil.integerNotNull(status))			
			paramMap.put("status", status);
		
		if(CheckNullUtil.isNotEmpty(contactName))			
			paramMap.put("contactName", contactName);
		if(CheckNullUtil.isNotEmpty(contactMobile))			
			paramMap.put("contactMobile", contactMobile);
		//时间间隔
		if(FormatUtil.integerInterval(startTime, endTime)){
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
		}
		else{
		if(CheckNullUtil.integerNotNull(startTime))
			paramMap.put("startTime", startTime);
		else if(CheckNullUtil.integerNotNull(endTime))
			paramMap.put("endTime", endTime);
		}		

		if(CheckNullUtil.isNotEmpty(order))
			paramMap.put("order", order);
		
		PageHelper.startPage(page, rows);
		//List list = LgManagePublishMapper.getCasePubilishApplyList(example);
		List list = LgManagePublishMapper.getMarketApplyList(paramMap);

		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
		
		
	}
	//获取个人摊位发布信息
	@Override
	public PageInfo getStallPublish(LgStallApply apply, Integer page, Integer rows,
			Integer startTime, Integer endTime, String order) throws ServiceException {
		Integer said = apply.getSaid();
		Integer userId = apply.getUserId();
		Integer stallId = apply.getStallId();
		Integer status = apply.getStatus();
		String marketName = apply.getMarketName();
		String stallName = apply.getStallName();
		Integer marketId = apply.getMarketId();
		
		LgStallApplyExample example = new LgStallApplyExample();
		com.light.pojo.LgStallApplyExample.Criteria criteria = example.createCriteria();
		
		if(CheckNullUtil.integerNotNull(said))
			criteria.andSaidEqualTo(said);
		if(CheckNullUtil.integerNotNull(userId))
			criteria.andUserIdEqualTo(userId);
		if(CheckNullUtil.integerNotNull(stallId))
		    criteria.andStallIdEqualTo(stallId);
		if(CheckNullUtil.integerNotNull(status))
		    criteria.andStatusEqualTo(status);
		if(CheckNullUtil.isNotEmpty(marketName))
		    criteria.andMarketNameEqualTo(marketName);
		if(CheckNullUtil.isNotEmpty(stallName))
		    criteria.andStallNameEqualTo(stallName);
		if(CheckNullUtil.integerNotNull(marketId))
			criteria.andMarketIdEqualTo(marketId);
		//时间间隔
		if(FormatUtil.integerInterval(startTime, endTime)){
			criteria.andCreateTimeBetween(startTime, endTime);
		}
		else{
		if(CheckNullUtil.integerNotNull(startTime))
			criteria.andCreateTimeGreaterThan(startTime);
		else if(CheckNullUtil.integerNotNull(endTime))
			criteria.andCreateTimeLessThanOrEqualTo(endTime);
		}
		if(CheckNullUtil.isNotEmpty(order))
			example.setOrderByClause(order);
		
		PageHelper.startPage(page,rows);
		List list = LgStallApplyMapper.selectByExample(example);
		
		PageInfo pageInfo = new PageInfo<>(list);
		
		return pageInfo;
	}
	//获取新闻发布信息
	@Override
	public PageInfo getNewsPublish(LgNewsApply apply, Integer page, Integer rows,
			Integer startTime, Integer endTime, String order, String newsTitle, Integer type) throws ServiceException {
		Integer naid = apply.getNaid();
		Integer userId = apply.getUserId();
		Integer newsId = apply.getNewsId();
		Integer status = apply.getStatus();
		
		
		Map paramMap = new HashMap<>();
		if(CheckNullUtil.integerNotNull(naid))
			paramMap.put("naid", naid);
		if(CheckNullUtil.integerNotNull(userId))
			paramMap.put("userId", userId);
		if(CheckNullUtil.integerNotNull(newsId))
			paramMap.put("newsId", newsId);
		if(CheckNullUtil.integerNotNull(status))			
			paramMap.put("status", status);
		
		if(CheckNullUtil.isNotEmpty(newsTitle))
			paramMap.put("newsTitle", "%"+newsTitle+"%");
		if(CheckNullUtil.integerNotNull(type))			
			paramMap.put("type", type);		
		//时间间隔
		if(FormatUtil.integerInterval(startTime, endTime)){
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
		}
		else{
		if(CheckNullUtil.integerNotNull(startTime))
			paramMap.put("startTime", startTime);
		else if(CheckNullUtil.integerNotNull(endTime))
			paramMap.put("endTime", endTime);
		}
		
		if(CheckNullUtil.isNotEmpty(order))
			paramMap.put("order", order);
		
		PageHelper.startPage(page, rows);
		//List list = LgManagePublishMapper.getCasePubilishApplyList(example);
		List list = LgManagePublishMapper.getNewsApplyList(paramMap);

		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
		
	}
	//获取发布加盟列表
	@Override
	public PageInfo getCooperatePublish(LgCooperateCompanyApply apply, Integer page, Integer rows, String order) throws ServiceException {
		Integer cid = apply.getCid();
		Integer state = apply.getState();
		Integer userId = apply.getUserId();
		Map paramMap = new HashMap<>();
		if(CheckNullUtil.integerNotNull(cid))
			paramMap.put("cid", cid);
		if(CheckNullUtil.isNotEmpty(order))
			paramMap.put("order", order);
		if(CheckNullUtil.integerNotNull(state))
			paramMap.put("state", state);
		if(CheckNullUtil.integerNotNull(userId))
			paramMap.put("userId", userId);
		
		
		PageHelper.startPage(page, rows);
		List list = LgManagePublishMapper.getCooperateApplyList(paramMap);
		
		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	//获取设备发布信息列表
	@Override
	public PageInfo getGoodsPublish(LgGoodsApply apply, Integer page, Integer rows, String order)
			throws ServiceException {
		Integer gaid = apply.getGaid();
		Integer status = apply.getStatus();
		Integer userId= apply.getUserId();
		Map paramMap = new HashMap<>();
		if(CheckNullUtil.integerNotNull(gaid))
			paramMap.put("gaid", gaid);
		if(CheckNullUtil.integerNotNull(status))
			paramMap.put("status", status);
		if(CheckNullUtil.isNotEmpty(order))
			paramMap.put("order", order);
		if(CheckNullUtil.integerNotNull(userId))
			paramMap.put("userId", userId);
		PageHelper.startPage(page, rows);
		List list = LgManagePublishMapper.getGoodsApplyList(paramMap);
		
		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	//审核案例
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map approveCase(Integer caseApplyId, Integer status) throws ServiceException {
		LgCaseApply getApply = LgCaseApplyMapper.selectByPrimaryKey(caseApplyId);
		if(getApply == null)
			throw new ServiceException("无此申请");
		if(getApply.getState() != 0)
			throw new ServiceException("非可审核状态");
		if(status == null)
			throw new ServiceException("status不能为空");
		if(status != 1 && status != 2)
			throw new ServiceException("状态代码错误");
		
		//1.更新apply表为state = status
		LgCaseApply record1 = new LgCaseApply();
			record1.setCaid(caseApplyId);
			record1.setState(status);
		if(LgCaseApplyMapper.updateByPrimaryKeySelective(record1) < 1)
			throw new ServiceException("更新apply失败");
		//2.更新case表 为 state  = status
		LgCase record2 = new LgCase();
			record2.setCid(getApply.getCaseId());
			record2.setState(status);
			//add on 0324 
			record2.setCreateTime(FormatUtil.timeStampInt());
		if(LgCaseMapper.updateByPrimaryKeySelective(record2) < 1)
			throw new ServiceException("更新case失败");

		return ReturnResult.ok();
	}
	//审核市场
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map approveMarket(Integer marketApplyId, Integer status) throws ServiceException {
		LgMarketApply getApply = LgMarketApplyMapper.selectByPrimaryKey(marketApplyId);
		if(getApply == null)
			throw new ServiceException("无此申请");
		if(getApply.getStatus() != 0)
			throw new ServiceException("非可审核状态");
		if(status == null)
			throw new ServiceException("status不能为空");
		if(status != 1 && status != 2)
			throw new ServiceException("状态代码错误");
		
		LgMarketApply record1 = new LgMarketApply();
			record1.setMaid(marketApplyId);
			record1.setStatus(status);
		if(LgMarketApplyMapper.updateByPrimaryKeySelective(record1) < 1)
			throw new ServiceException("更新apply失败");
		
		LgMarket record2 = new LgMarket();
			record2.setMid(getApply.getMarketId());
			record2.setStatus(status);
			//add on 0324 
			record2.setCreateTime(FormatUtil.timeStampInt());

		if(LgMarketMapper.updateByPrimaryKeySelective(record2) < 1)
			throw new ServiceException("更新market失败");

		return ReturnResult.ok();
	}
	//审核摊位
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map approveStall(Integer stallApplyId, Integer status) throws ServiceException {
		LgStallApply getApply = LgStallApplyMapper.selectByPrimaryKey(stallApplyId);
		if(getApply == null)
			throw new ServiceException("无此申请");
/*		if(getApply.getStatus() != 0)
			throw new ServiceException("非可审核状态");*/
		if(status == null)
			throw new ServiceException("status不能为空");
		if(status != 1 && status != 2)
			throw new ServiceException("状态代码错误");
		Integer stallId = getApply.getStallId();
		LgStall getStall = LgStallMapper.selectByPrimaryKey(stallId);
		if(getStall == null)
			throw new ServiceException("无此摊位");
		if(getStall.getStatus() != 0){//摊位非申请状态
			//将当前申请关闭
			LgStallApply record = new LgStallApply();
				record.setSaid(stallApplyId);
				record.setStatus(2);
			LgStallApplyMapper.updateByPrimaryKeySelective(record);
			return ReturnResult.wrong("摊位已出租");
		}
		//摊位为可申请状态
		if(status == 1){//审核成功
		LgStallApply record1 = new LgStallApply();
			record1.setSaid(stallApplyId);
			record1.setStatus(1);
		if(LgStallApplyMapper.updateByPrimaryKeySelective(record1) < 1)
			throw new ServiceException("更新apply失败");
		LgStall record2 =new LgStall();
			record2.setSid(getApply.getStallId());
			record2.setContactMobile(getApply.getContactMobile());
			record2.setContactName(getApply.getContactName());
			record2.setStallName(getApply.getStallName());
			record2.setStatus(1);
			//add on 0324 
			record2.setCreateTime(FormatUtil.timeStampInt());

		if(LgStallMapper.updateByPrimaryKeySelective(record2) < 1)
			throw new ServiceException("更新stall失败");
		}else{//审核失败
			LgStallApply record3 = new LgStallApply();
			record3.setSaid(stallApplyId);
			record3.setStatus(2);
		if(LgStallApplyMapper.updateByPrimaryKeySelective(record3) < 1)
			throw new ServiceException("更新apply失败");
		}
			
		return ReturnResult.ok();
	}
	//审核新闻
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map approveNews(Integer newsApplyId, Integer status) throws ServiceException {
		LgNewsApply getApply = LgNewsApplyMapper.selectByPrimaryKey(newsApplyId);
		if(getApply == null)
			throw new ServiceException("无此申请");
		if(getApply.getStatus() != 0)
			throw new ServiceException("非可审核状态");
		if(status == null)
			throw new ServiceException("status不能为空");
		if(status != 1 && status != 2)
			throw new ServiceException("状态代码错误");
		
		LgNewsApply record1 =  new LgNewsApply();
			record1.setNaid(newsApplyId);
			record1.setStatus(status);
		if(LgNewsApplyMapper.updateByPrimaryKeySelective(record1) < 1)
			throw new ServiceException("更新apply失败");
		LgNews record2 = new LgNews();
		 	record2.setNid(getApply.getNewsId());
		 	record2.setStatus(status);
			//add on 0324 
			record2.setCreateTime(FormatUtil.timeStampInt());

		 if(LgNewsMapper.updateByPrimaryKeySelective(record2) < 1)
			throw new ServiceException("更新news失败");

		return ReturnResult.ok();
	}
	//审核加盟
	@Override
	public Map approveCooperateCompany(Integer cooperateApplyId, Integer status) throws ServiceException {
		LgCooperateCompanyApply getApply = LgCooperateCompanyApplyMapper.selectByPrimaryKey(cooperateApplyId);
		if(getApply == null)
			throw new ServiceException("无此申请");
		if(getApply.getState() != 0)
			throw new ServiceException("非可审核状态");
		if(status == null)
			throw new ServiceException("status不能为空");
		if(status != 1 && status != 2)
			throw new ServiceException("状态代码错误");
		LgCooperateCompanyApply record1 = new LgCooperateCompanyApply();
			record1.setCid(cooperateApplyId);
			record1.setState(status);
		if(LgCooperateCompanyApplyMapper.updateByPrimaryKeySelective(record1) < 1)
			throw new ServiceException("更新apply失败");
		LgCooperateCompany record2 = new LgCooperateCompany();
			record2.setCid(getApply.getCooperateCompanyId());
			record2.setStatus(status);
			//add on 0324 
			record2.setCreateTime(FormatUtil.timeStampInt());

		if(LgCooperateCompanyMapper.updateByPrimaryKeySelective(record2) < 1)
			throw new ServiceException("更新表失败");
		return ReturnResult.ok();
	}
	
	//审核设备
	@Override
	public Map approveGoods(Integer goodsApplyId, Integer status) throws ServiceException {
		LgGoodsApply getApply = LgGoodsApplyMapper.selectByPrimaryKey(goodsApplyId);
		if(getApply == null)
			throw new ServiceException("无此申请");
		if(getApply.getStatus() != 0)
			throw new ServiceException("非可审核状态");
		if(status == null)
			throw new ServiceException("status不能为空");
		if(status != 1 && status != 2)
			throw new ServiceException("状态代码错误");
		LgGoodsApply record1 = new LgGoodsApply();
			record1.setGaid(goodsApplyId);
			record1.setStatus(status);
		if(LgGoodsApplyMapper.updateByPrimaryKeySelective(record1) < 1)
			throw new ServiceException("更新apply失败");
		LgGoods record2 = new LgGoods();
			record2.setGoodsId(getApply.getGoodsId());
			record2.setStatus(status);
			//add on 0324 
			record2.setLastUpdateTime(FormatUtil.timeStampInt());

		if(LgGoodsMapper.updateByPrimaryKeySelective(record2) < 1)
			throw new ServiceException("更新表失败");
		return ReturnResult.ok();
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map deleteAll(Integer type, Integer typeId) throws ServiceException {
		if(CheckNullUtil.integerNull(type))
			throw new ServiceException("type不能为空");
		if(CheckNullUtil.integerNull(typeId))
			throw new ServiceException("typeId不能为空");
		//类型，1-案例，2-市场，3-新闻，4-加盟，5-设备
		switch (type) {
		case 1:
			LgCase record = new LgCase();
				record.setCid(typeId);
				record.setState(2);
			if(LgCaseMapper.updateByPrimaryKeySelective(record)<1)
				throw new ServiceException("删除失败");
			//修改申请表
			LgCaseApply applyRecord = new LgCaseApply();
				applyRecord.setState(2);
			LgCaseApplyExample example = new LgCaseApplyExample();
				example.createCriteria().andCaseIdEqualTo(typeId);
			if(LgCaseApplyMapper.updateByExampleSelective(applyRecord, example)<1)
				throw new ServiceException("删除失败");
			break;
		case 2:
			LgMarket market = new LgMarket();
				market.setMid(typeId);
				market.setStatus(2);
			if(LgMarketMapper.updateByPrimaryKeySelective(market)<1)
				throw new ServiceException("删除失败");
			
			LgMarketApply apply = new LgMarketApply();
				apply.setStatus(2);
			LgMarketApplyExample example2 = new LgMarketApplyExample();
				example2.createCriteria().andMarketIdEqualTo(typeId);
			if(LgMarketApplyMapper.updateByExampleSelective(apply, example2)<1)
				throw new ServiceException("删除失败");

			break;
		case 3:
			LgNews news = new LgNews();
				news.setNid(typeId);
				news.setStatus(2);
			if(LgNewsMapper.updateByPrimaryKeySelective(news)<1)
				throw new ServiceException("删除失败");
			LgNewsApply newsApply = new LgNewsApply();
				newsApply.setStatus(2);
			LgNewsApplyExample example3 = new LgNewsApplyExample();
				example3.createCriteria().andNewsIdEqualTo(typeId);
			if(LgNewsApplyMapper.updateByExampleSelective(newsApply, example3)<1)
				throw new ServiceException("删除失败");

			break;
		case 4:
			LgCooperateCompany company = new LgCooperateCompany();
				company.setCid(typeId);
				company.setStatus(2);
			if(LgCooperateCompanyMapper.updateByPrimaryKeySelective(company)<1)
				throw new ServiceException("删除失败");
			
			LgCooperateCompanyApply companyApply = new LgCooperateCompanyApply();
				companyApply.setState(2);
			LgCooperateCompanyApplyExample example4 = new LgCooperateCompanyApplyExample();
				example4.createCriteria().andCooperateCompanyIdEqualTo(typeId);
			if(LgCooperateCompanyApplyMapper.updateByExampleSelective(companyApply, example4)<1)
				throw new ServiceException("删除失败");

			break;
		case 5:
			LgGoods goods = new LgGoods();
				goods.setGoodsId(typeId);
				goods.setStatus(2);
			if(LgGoodsMapper.updateByPrimaryKeySelective(goods)<1)
				throw new ServiceException("删除失败");
			LgGoodsApply goodsApply = new LgGoodsApply();
				goodsApply.setStatus(2);
			LgGoodsApplyExample example5 = new LgGoodsApplyExample();
				example5.createCriteria().andGoodsIdEqualTo(typeId);
			if(LgGoodsApplyMapper.updateByExampleSelective(goodsApply, example5)<1)
				throw new ServiceException("删除失败");

			break;

		default:
			throw new ServiceException("参数错误");
		}
		
		return ReturnResult.ok();
	}


}
