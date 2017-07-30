package com.light.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.pojo.LgPartner;
import com.light.pojo.LgSalary;

/**
 * 合作方档案
 * @ClassName: PartnerService 
 * @author TobyHan
 * @date 2017年2月15日 下午4:53:31 
 *
 */
public interface PartnerService {

	Map commitPartner(LgPartner lgPartner);

	PageInfo<?> getPartnerList(LgPartner lgPartner,Integer page, Integer rows,
			Integer startTime, Integer endTime);
	
	Map modifyPartnerById(LgPartner lgPartner);

	Map deletePartnerById(Integer id);
}
