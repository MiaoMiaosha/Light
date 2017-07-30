package com.light.service.impl;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.light.common.CheckNullUtil;
import com.light.common.FormatUtil;
import com.light.common.ReturnResult;
import com.light.exception.ServiceException;
import com.light.mapper.LgFloorMapper;
import com.light.mapper.LgMarketMapper;
import com.light.mapper.LgStallApplyMapper;
import com.light.mapper.LgStallMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgStall;
import com.light.pojo.LgStallApply;
import com.light.pojo.LgStallApplyExample;
import com.light.pojo.LgStallExample;
import com.light.service.StallService;
@Service
public class StallServiceImpl implements StallService {

	@Autowired LgStallMapper LgStallMapper;
	@Autowired LgPublicMapper LgPublicMapper;
	@Autowired LgStallApplyMapper LgStallApplyMapper;
	@Autowired LgMarketMapper LgMarketMapper;
	@Autowired LgFloorMapper LgFloorMapper;
	//获取市场楼层下所有摊位
	@Override
	public List getStallListByFloorId(Integer floorId) throws ServiceException {
		if(CheckNullUtil.integerNull(floorId))
			throw new ServiceException("id为空");
		LgStallExample example = new LgStallExample();
		example.createCriteria().andFloorIdEqualTo(floorId);
		example.setOrderByClause("create_time desc");
		List<LgStall> list = LgStallMapper.selectByExample(example);
		return list;
	}
	//市场增加摊位
	@Override
	public Map addNewStall(LgStall lgStall) throws ServiceException{
		if(lgStall == null)
			throw new ServiceException("摊位信息为空");
		LgStall newStall = new LgStall();
		 newStall.setMarketId(lgStall.getMarketId());
		 newStall.setFloorId(lgStall.getFloorId());
		 newStall.setStallName(lgStall.getStallName());
		// newStall.setContactMobile(lgStall.getContactMobile());
		// newStall.setContactName(lgStall.getStallName());
		 newStall.setCreateTime(FormatUtil.timeStampInt());
		 newStall.setStatus(0);//增加默认未使用
		int insertSta = LgStallMapper.insertSelective(newStall);
		if(insertSta < 1)
			throw new ServiceException("插入失败");
		int lastInsertId = LgPublicMapper.selectLastInsertId();
		Map rsMap = new HashMap<>();
		 rsMap.put("stallId", lastInsertId);
		 rsMap.put("stallName", lgStall.getStallName());
		return ReturnResult.ok(rsMap);
	}
	//用户申请摊位
	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public Map applyForStall(LgStallApply lgApply,Integer userId) throws ServiceException {
		if(lgApply == null)
			throw new ServiceException("摊位信息为空");
		if(userId == null)
			throw new ServiceException("用户id为空");
		Integer stallId = lgApply.getStallId();
		String contactName = lgApply.getContactName();
		String contactMobile =lgApply.getContactMobile();
		String stallName = lgApply.getStallName();
		Integer marketId = lgApply.getMarketId();
		Integer floorId = lgApply.getFloorId();
		
		
		if(stallId == null)
			throw new ServiceException("摊位id为空");
	    if(CheckNullUtil.isEmpty(contactMobile))
			throw new ServiceException("联系人电话为空");
	    if(CheckNullUtil.isEmpty(contactName))
			throw new ServiceException("联系人姓名为空");
	    if(CheckNullUtil.isEmpty(stallName))
			throw new ServiceException("摊位名为空");	    
	    if(CheckNullUtil.integerNull(marketId))
			throw new ServiceException("市场id为空");
	    if(CheckNullUtil.integerNull(floorId))
			throw new ServiceException("楼层id为空");
	    
		LgStall stallInfo = LgStallMapper.selectByPrimaryKey(stallId);
		if(stallInfo == null)
			throw new ServiceException("摊位不存在");
        if(stallInfo.getStatus() != 0)
			throw new ServiceException("摊位为非申请状态");
	    
	    
		//1.新增一条申请记录
		LgStallApply apply = new LgStallApply();
		apply.setUserId(userId);
		apply.setStallId(stallId);
		apply.setCreateTime(FormatUtil.timeStampInt());
		apply.setStatus(0);//0-申请中，1-申请成功，2-申请失败
		apply.setContactMobile(contactMobile);
		apply.setContactName(contactName);
		apply.setStallId(stallId);
		apply.setMarketId(marketId);
		apply.setFloorId(floorId);
		String searchStallName = LgStallMapper.selectByPrimaryKey(stallId).getStallName();
		String searchMarketName = LgMarketMapper.selectByPrimaryKey(marketId).getMarketName();
		String searchFloorName = LgFloorMapper.selectByPrimaryKey(floorId).getFloorName();
		apply.setMarketName(searchMarketName);
		apply.setStallName(searchStallName);
		apply.setFloorName(searchFloorName);
		
		int insertApply = LgStallApplyMapper.insertSelective(apply);
		if(insertApply < 1)
			throw new ServiceException("申请插入失败");
		Integer newApplyId = LgPublicMapper.selectLastInsertId();
		/*不需要锁定摊位
		//2.更改stall信息
		//2.1查询目前stall状态
		LgStall stallInfo = LgStallMapper.selectByPrimaryKey(stallId);
		if(stallInfo == null)
			throw new ServiceException("摊位不存在");
        if(stallInfo.getStatus() != 0)
			throw new ServiceException("摊位为非申请状态");
        //2.2 可申请，更改状态
		LgStall newStall = new LgStall();
		 newStall.setSid(stallId);
		 //管理员审核后才修改下面字段
		// newStall.setContactMobile(lgStall.getContactMobile());
		// newStall.setContactName(lgStall.getContactName());
		 newStall.setStatus(1);//状态：0-未使用；1-申请中；2-使用中；3-已关闭
		 int updateStall = LgStallMapper.updateByPrimaryKeySelective(newStall);
		 if(insertApply < 1)
			throw new ServiceException("更新摊位信息失败");
			*/
		return ReturnResult.ok(newApplyId);
	}
	/**
	 * 删除摊位
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map deleteStall(Integer stallId) throws ServiceException {
		String success = "删除成功";
		if(CheckNullUtil.integerNull(stallId))
			throw new ServiceException("stallId为空");
		if(LgStallMapper.deleteByPrimaryKey(stallId) < 1)
			throw new ServiceException("删除摊位失败");
		//将用户申请，设置为已关闭
		LgStallApplyExample example = new LgStallApplyExample();
		 example.createCriteria().andStallIdEqualTo(stallId);
		 LgStallApply record = new LgStallApply();
		 	record.setStatus(2);//关闭申请
		 if(LgStallApplyMapper.updateByExampleSelective(record, example) < 1)
			 success = "无相关申请";
		
		return ReturnResult.ok(success);
	}

}
