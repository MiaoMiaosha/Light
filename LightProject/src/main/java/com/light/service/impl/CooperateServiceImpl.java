package com.light.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.light.mapper.LgCooperateCompanyApplyMapper;
import com.light.mapper.LgCooperateCompanyMapper;
import com.light.mapper.LgMainBusinessMapper;
import com.light.mapper.LgRegionMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgCooperateCompany;
import com.light.pojo.LgCooperateCompanyApply;
import com.light.pojo.LgCooperateCompanyExample;
import com.light.pojo.LgCooperateCompanyExample.Criteria;
import com.light.pojo.LgMainBusiness;
import com.light.pojo.LgMainBusinessExample;
import com.light.pojo.LgRegion;
import com.light.service.CooperateService;
@Service
public class CooperateServiceImpl implements CooperateService {

	@Autowired LgCooperateCompanyMapper companyMapper;
	@Autowired LgPublicMapper LgPublicMapper;
	@Autowired LgCooperateCompanyApplyMapper applyMapper;
	@Autowired LgMainBusinessMapper LgMainBusinessMapper;
	@Autowired LgRegionMapper LgRegionMapper;
	//申请加盟
	@Override
	public Map publishCooperate(LgCooperateCompany company,Integer userId) throws ServiceException {
		String fullName = company.getFullName();
		Integer province = company.getProvince();
		Integer city = company.getCity();
		Integer district = company.getDistrict();
		String mainBusiness = company.getMainBusiness();
		String companyIntro = company.getCompanyIntro();
		if(CheckNullUtil.isEmpty(fullName))
			throw new ServiceException("公司名不能为空");
		if(CheckNullUtil.integerNull(userId))
			throw new ServiceException("用户id不能为空");
		//1.插入加盟表中
		LgCooperateCompany newCom = new LgCooperateCompany();
		newCom.setUserId(userId);
		newCom.setFullName(fullName);
		newCom.setCountry(0);
		newCom.setProvince(province);
		newCom.setCity(city);
		newCom.setDistrict(district);
		newCom.setMainBusiness(mainBusiness);
		newCom.setCompanyIntro(companyIntro);
		newCom.setCreateTime(FormatUtil.timeStampInt());
		newCom.setStatus(0);
		int insertSta = companyMapper.insertSelective(newCom);
		if(insertSta < 1)
			throw new ServiceException("加盟表插入失败");
		int cooperateInsertId = LgPublicMapper.selectLastInsertId();
		//2.加入加盟申请中
		LgCooperateCompanyApply apply = new LgCooperateCompanyApply();
		apply.setUserId(userId);
		apply.setCooperateCompanyId(cooperateInsertId);
		apply.setCreateTime(FormatUtil.timeStampInt());
		apply.setState(0);
		int insertSta2 = applyMapper.insertSelective(apply);
		if(insertSta < 1)
			throw new ServiceException("申请表插入失败");
		int applyInsertId = LgPublicMapper.selectLastInsertId();
		
		Map rsMap = new HashMap<>();
		rsMap.put("cid", cooperateInsertId);
		rsMap.put("applyId", applyInsertId);
		return ReturnResult.ok(rsMap);
	}
	//获取加盟商家列表
	@Override
	public PageInfo<?> getCooperateList(LgCooperateCompany company, Integer page, Integer rows) {
		Integer cid =  company.getCid();
		Integer userId = company.getUserId();
		String fullName = company.getFullName();
		Integer status = company.getStatus();
		
		LgCooperateCompanyExample example = new LgCooperateCompanyExample();
		 Criteria criteria = example.createCriteria();
		if(CheckNullUtil.integerNotNull(cid))
			criteria.andCidEqualTo(cid);
		if(CheckNullUtil.integerNotNull(userId))
			criteria.andUserIdEqualTo(userId);
		if(CheckNullUtil.integerNotNull(status))
			criteria.andStatusEqualTo(status);
		if(CheckNullUtil.isNotEmpty(fullName))
			criteria.andFullNameLike("%"+fullName+"%");
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(page, rows);
		List<LgCooperateCompany> list = companyMapper.selectByExample(example);
		
			LgMainBusinessExample lgMainBusinessExample = new LgMainBusinessExample();
			List<LgMainBusiness> mainList = LgMainBusinessMapper.selectByExample(lgMainBusinessExample);
			Map<Integer, String> mainMap =  new HashMap<>();
			for(LgMainBusiness element : mainList){
				mainMap.put(element.getMbid(), element.getMainBusinessName());
			}
		Map rsMap = null;
		List mapList = new ArrayList<>();

		for(LgCooperateCompany element : list){
			rsMap = new HashMap<>();
			Integer provinceId = element.getProvince();
			Integer cityId = element.getCity();
			Integer districtId = element.getDistrict();
			LgRegion province = null,city = null,district=null;
			if(provinceId != null)
				province = LgRegionMapper.selectByPrimaryKey(provinceId);
			if(cityId != null)
				city = LgRegionMapper.selectByPrimaryKey(cityId);
			if(districtId != null)
			  district = LgRegionMapper.selectByPrimaryKey(districtId);
			rsMap.put("province", province);
			rsMap.put("city", city);
			rsMap.put("district", district);

			
			String mainBus = element.getMainBusiness();
			List<String> strList = new ArrayList<>();
			if(mainBus != null && !mainBus.equals("")){
				String[] mainArr = mainBus.split(":");
				for(String ele2 : mainArr){
					strList.add(mainMap.get(Integer.valueOf(ele2)));
				}
			}
			rsMap.put("element", element);
			rsMap.put("list", strList);
			mapList.add(rsMap);
		}
		
		PageInfo<?> pageInfo = new PageInfo(mapList);

		return pageInfo;
	}
	//获取主营业务
	@Override
	public List getMainBusinessList(Integer id) {
		LgMainBusinessExample example =  new LgMainBusinessExample();
		com.light.pojo.LgMainBusinessExample.Criteria criteria = example.createCriteria();
		if(CheckNullUtil.integerNotNull(id))
			criteria.andMbidEqualTo(id);
		List list = LgMainBusinessMapper.selectByExample(example);
		return list;
	}
	//删除加盟公司
	@Override
	public Map deleteCooperateCompany(Integer cid) {
		if(cid == null || cid == 0)
			throw new ServiceException("cid为空");
		LgCooperateCompany company = new LgCooperateCompany();
		  company.setCid(cid);
		  company.setStatus(2);
		  if(companyMapper.updateByPrimaryKeySelective(company) < 1)
			  throw new ServiceException("删除失败");
		return ReturnResult.ok();
	}

}
