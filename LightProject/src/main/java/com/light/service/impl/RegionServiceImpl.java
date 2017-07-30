package com.light.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.light.common.CheckNullUtil;
import com.light.common.ReturnResult;
import com.light.exception.ServiceException;
import com.light.mapper.LgRegionMapper;
import com.light.pojo.LgRegion;
import com.light.pojo.LgRegionExample;
import com.light.service.RegionService;
@Service
public class RegionServiceImpl implements RegionService {

	@Autowired LgRegionMapper LgRegionMapper;
	//根据父id获取下级列表
	@Override
	public List getRegionListByParentId(Integer parentId) throws ServiceException {
		if(CheckNullUtil.integerNull(parentId))
			throw new ServiceException("id为空");
		Map rsMap = null;
		LgRegionExample example = new LgRegionExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		List<LgRegion> list = LgRegionMapper.selectByExample(example);
		
		return list;
	}

}
