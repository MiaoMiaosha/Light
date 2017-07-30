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
import com.light.mapper.LgGoodsApplyMapper;
import com.light.mapper.LgGoodsMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgGoods;
import com.light.pojo.LgGoodsApply;
import com.light.pojo.LgGoodsExample;
import com.light.pojo.LgGoodsExample.Criteria;
import com.light.service.GoodsService;
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	LgGoodsMapper LgGoodsMapper;
	@Autowired
	LgPublicMapper lgPublicMapper;
	@Autowired
	LgGoodsApplyMapper LgGoodsApplyMapper;
	//获取设备列表
	@Override
	public PageInfo getGoodsList(LgGoods lgGoods, Integer page, Integer rows, Integer startTime, Integer endTime)
			throws ServiceException {
		LgGoodsExample example = new LgGoodsExample();
		Criteria criteria = example.createCriteria();
		Integer goodsId = lgGoods.getGoodsId();
		String goodsName = lgGoods.getGoodsName();
		//Integer goodsPrice = lgGoods.getGoodsPrice();
		String brand = lgGoods.getBrand();
		String mobile = lgGoods.getMobile();
		//String address = lgGoods.getAddress();
		Integer status = lgGoods.getStatus();
		Integer userId = lgGoods.getUserId();
		
		Integer type = lgGoods.getType();
		
		if(CheckNullUtil.isNotEmpty(goodsName))
			criteria.andGoodsNameLike("%"+goodsName+"%");
		if(CheckNullUtil.isNotEmpty(brand))
			criteria.andBrandEqualTo(brand);
		if(CheckNullUtil.isNotEmpty(mobile))
			criteria.andMobileEqualTo(mobile);
		if(CheckNullUtil.integerNotNull(status))
			criteria.andStatusEqualTo(status);
		if(CheckNullUtil.integerNotNull(userId))
			criteria.andUserIdEqualTo(userId);
		if(CheckNullUtil.integerNotNull(goodsId))
			criteria.andGoodsIdEqualTo(goodsId);
		
		if(CheckNullUtil.integerNotNull(type))
			criteria.andTypeEqualTo(type);
		
		example.setOrderByClause("last_update_time desc");
		PageHelper.startPage(page,rows);
		List<LgGoods> list = LgGoodsMapper.selectByExample(example);
		PageInfo pageInfo = new PageInfo<>(list);
		return pageInfo;
		
	}
	//获取设备详细信息
	@Override
	public LgGoods getGoodsDetail(Integer goodsId) {
		LgGoods lgGoods = LgGoodsMapper.selectByPrimaryKey(goodsId);
		return lgGoods;
	}
	//发布设备
	@Override
	public Map publishGoods(LgGoods goods) throws ServiceException {
		if(CheckNullUtil.isEmpty(goods.getGoodsName()))
			throw new ServiceException("设备名为空");
		if(CheckNullUtil.integerNull(goods.getGoodsPrice()))
			throw new ServiceException("价格为空");
		if(CheckNullUtil.isEmpty(goods.getDescription()))
			throw new ServiceException("描述为空");
		if(goods.getType() == null)
			throw new ServiceException("设备发布类型为空");

		goods.setStatus(0);
		if(LgGoodsMapper.insertSelective(goods) < 1)
			throw new ServiceException("插入为空");
		Integer lastGoodsId = lgPublicMapper.selectLastInsertId();

		LgGoodsApply apply = new LgGoodsApply();
		 apply.setUserId(goods.getUserId());
		 apply.setGoodsId(lastGoodsId);
		 apply.setCreateTime(FormatUtil.timeStampInt());
		 apply.setStatus(0);
		if(LgGoodsApplyMapper.insertSelective(apply)<1)
			throw new ServiceException("插入失败");
		
		return ReturnResult.ok();
	}
	//删除设备
	@Override
	public Map deleteGoods(Integer goodsId) throws ServiceException {
		if(goodsId == null || goodsId == 0)
			throw new ServiceException("goodsId为空");
		if(LgGoodsMapper.deleteByPrimaryKey(goodsId) < 1)
			throw new ServiceException("删除失败");
		return ReturnResult.ok();
	}

}
