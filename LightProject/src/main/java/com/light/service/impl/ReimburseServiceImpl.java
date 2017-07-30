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
import com.light.dto.ReimburseOutputBean;
import com.light.exception.ServiceException;
import com.light.mapper.LgReimburseExtraMapper;
import com.light.mapper.LgReimburseMapper;
import com.light.mapper.custom.LgCustomerCustomMapper;
import com.light.mapper.custom.LgEmployeeCustomMapper;
import com.light.mapper.custom.LgLoginUserCustomMapper;
import com.light.mapper.custom.LgReimburseCustomMapper;
import com.light.pojo.LgReimburse;
import com.light.pojo.LgReimburseExample;
import com.light.pojo.LgReimburseExample.Criteria;
import com.light.pojo.LgReimburseExtra;
import com.light.pojo.LgReimburseExtraExample;
import com.light.service.ReimburseService;
@Service
public class ReimburseServiceImpl implements ReimburseService {

	@Autowired LgReimburseMapper LgReimburseMapper;
	@Autowired LgReimburseExtraMapper LgReimburseExtraMapper;
	//@Autowired LgCustomerCustomMapper lgCustomerCustomMapper;
	@Autowired LgEmployeeCustomMapper lgEmployeeCustomMapper;
	
	@Autowired LgLoginUserCustomMapper lgLoginUserCustomMapper;
	@Autowired LgReimburseCustomMapper lgReimburseCustomMapper;
	//提交报销表
	@Override
	public Map commitReimbursement(LgReimburse lgReimburse,Integer userId) {
		
		if(lgReimburse == null)
			return ReturnResult.wrong();
		//lgReimburse.setCreateTime(FormatUtil.timeStampInt());
		lgReimburse.setLoginUserId(userId);
		
		//如果是会计，免审核
		String roleIds = lgLoginUserCustomMapper.getRoleIdByLoginId(userId);
		if(CheckNullUtil.isNotEmpty(roleIds) && roleIds.equals("2")){
			lgReimburse.setStatus(1);
			lgReimburse.setOperateNo(userId);
			lgReimburse.setUpdateTime(FormatUtil.timeStampInt());
		}
		else{
			lgReimburse.setStatus(0);
			if(CheckNullUtil.integerNotNull(lgReimburse.getUpdateTime()))
				throw new ServiceException("非会计，需要审批");
		}
		
		int insertSta = LgReimburseMapper.insertSelective(lgReimburse);
		if(insertSta < 1)
			return ReturnResult.wrong();
		
		return ReturnResult.ok();
	}
	//获取报销列表
	@Override
	public PageInfo<?> getReimbursementList(LgReimburse lgReimburse, Integer page, Integer rows, Integer startTime,
			Integer endTime) {
		LgReimburseExample example = new LgReimburseExample();
			Criteria criteria = example.createCriteria();
			Integer rid = lgReimburse.getRid();
			Integer userId = lgReimburse.getReimburseUserId();
			Integer inputStatus = lgReimburse.getStatus();
			
			String marketName= lgReimburse.getMarketName();
			if(CheckNullUtil.integerNotNull(rid))
				criteria.andRidEqualTo(rid);
			if(CheckNullUtil.integerNotNull(userId))
				criteria.andReimburseUserIdEqualTo(userId);
			if(CheckNullUtil.integerNotNull(inputStatus))
				criteria.andStatusEqualTo(inputStatus);
			if(CheckNullUtil.isNotEmpty(marketName))//市场名称
				criteria.andMarketNameLike("%"+marketName+"%");
			criteria.andStatusNotEqualTo(3);//3-标记删除
			
		Map<Integer,String> extraMap = new HashMap<>();
		LgReimburseExtraExample ExtraExample = new LgReimburseExtraExample();
		List<LgReimburseExtra> extraList = LgReimburseExtraMapper.selectByExample(ExtraExample);
        for(LgReimburseExtra element : extraList){
        	extraMap.put(element.getId(), element.getTypeName());
        }
        example.setOrderByClause("status asc");
		PageHelper.startPage(page, rows);
		List<LgReimburse> list = LgReimburseMapper.selectByExample(example);
		List<ReimburseOutputBean> outputList = new ArrayList<>();
		ReimburseOutputBean outputBean = null;
		for(LgReimburse element : list){
			//查询报销人的名称
			Integer type = element.getReimburseUserType();
			Integer userTypeId = element.getReimburseUserId();
			//报销人类型:0-员工档案；1-合作方档案-partner_id
			String getName = "";
			if(type == 0){
				getName = lgEmployeeCustomMapper.getEmployeeNameById(userTypeId);
			}
			else if(type == 1){
				//getName = lgCustomerCustomMapper.getCustomerNameByCid(userTypeId);
				getName = lgReimburseCustomMapper.getPartnerNameById(userTypeId);
			}
			
			outputBean = new ReimburseOutputBean(element,extraMap,getName);
			outputList.add(outputBean);
		}
		PageInfo<?> pageInfo =  new PageInfo<>(outputList);
		
		return pageInfo;
	}
	//修改报销
	@Override
	public Map modifyReimburseById(LgReimburse reimburse) {
		if(reimburse.getRid() == null)
			throw new ServiceException("rid为空");
		Integer status = reimburse.getStatus();
		if(status != null && status == 3)//标记删除时候
			throw new ServiceException("接口调用错误");
		if(status != null){
			reimburse.setUpdateTime(FormatUtil.timeStampInt());
		}
		if(LgReimburseMapper.updateByPrimaryKeySelective(reimburse) < 1)
			throw new ServiceException("报销表更新失败");

		return ReturnResult.ok();
	}
	//标记删除
	@Override
	public Map deleteReimburseById(Integer rid) {
		LgReimburse reimburse =  new LgReimburse();
		reimburse.setRid(rid);
		reimburse.setStatus(3);
		if(LgReimburseMapper.updateByPrimaryKeySelective(reimburse) < 1)
			throw new ServiceException("标记删除失败");
		return ReturnResult.ok();
	}
	@Override
	public List getExtraList(LgReimburseExtra extra) {
		LgReimburseExtraExample example = new LgReimburseExtraExample();
		if(extra != null){
			com.light.pojo.LgReimburseExtraExample.Criteria criteria = example.createCriteria();
			String typeName = extra.getTypeName();
			Integer id = extra.getId();
			if(CheckNullUtil.isNotEmpty(typeName))
				criteria.andTypeNameLike("%"+typeName+"%");
			if(CheckNullUtil.integerNotNull(id))
				criteria.andIdEqualTo(id);
		}
		List list = LgReimburseExtraMapper.selectByExample(example);
		return list;
	}
	//编辑类型
	@Override
	public Map editExtra(LgReimburseExtra extra) throws ServiceException {
		Integer id  = extra.getId();
		String typeName = extra.getTypeName();
		if(CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		if(CheckNullUtil.isEmpty(typeName))
			throw new ServiceException("类型名称为空");
		if(LgReimburseExtraMapper.updateByPrimaryKeySelective(extra)<1)
			throw new ServiceException("修改失败");
		return ReturnResult.ok();
	}
	//添加类型
	@Override
	public Map addExtra(LgReimburseExtra extra) throws ServiceException {
		String typeName = extra.getTypeName();
		if(CheckNullUtil.isEmpty(typeName))
			throw new ServiceException("类型名称为空");
		if(LgReimburseExtraMapper.insertSelective(extra)<1)
			throw new ServiceException("插入失败");
		return ReturnResult.ok();
	}
	//删除类型
	@Override
	public Map deleteExtra(Integer id) throws ServiceException {
		if(CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		//1.获取该类型下有无有效记录
		Integer count = lgReimburseCustomMapper.countNumByExtraId(id);
		if(count > 0) throw new ServiceException("该类型下有记录，不能删除");
		//2.删除记录
		if(LgReimburseExtraMapper.deleteByPrimaryKey(id)<1)
			throw new ServiceException("删除失败");
		return ReturnResult.ok();
	}

}
