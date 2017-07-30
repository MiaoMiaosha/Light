package com.light.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.light.common.CheckNullUtil;
import com.light.exception.ServiceException;
import com.light.mapper.LgFloorMapper;
import com.light.pojo.LgFloor;
import com.light.pojo.LgFloorExample;
import com.light.pojo.LgFloorExample.Criteria;
import com.light.service.FloorService;
@Service
public class FloorServiceImpl implements FloorService {

	@Autowired LgFloorMapper LgFloorMapper;
	//根据市场获取楼层列表
	@Override
	public List getFloorListById(Integer marketId) throws ServiceException {
		if(CheckNullUtil.integerNull(marketId))
			throw new ServiceException("市场id不能为空");
		LgFloorExample example = new LgFloorExample();
		Criteria criteria = example.createCriteria();
		criteria.andMarketIdEqualTo(marketId);
		example.setOrderByClause("fid desc");
		List<LgFloor> list = LgFloorMapper.selectByExample(example);
		return list;
	}

}
