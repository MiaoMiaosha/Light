package com.light.service.impl;

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
import com.light.mapper.LgLikeMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgLike;
import com.light.pojo.LgLikeExample;
import com.light.pojo.LgLikeExample.Criteria;
import com.light.service.LikeService;
@Service
public class LikeServiceImpl implements LikeService {

	@Autowired LgLikeMapper LgLikeMapper;
	@Autowired LgPublicMapper LgPublicMapper;
	
	//收藏列表
	@Override
	public PageInfo<?> getLikeList(LgLike lgLike, Integer page, Integer rows) throws ServiceException {
		
		Integer id = lgLike.getId();
		Integer userId = lgLike.getUserId();
		Integer contentId = lgLike.getContentId();
		String content = lgLike.getContent();
		Integer type = lgLike.getType();
		
		if(CheckNullUtil.integerNull(userId))
			throw new ServiceException("用户id为空");
		LgLikeExample example = new LgLikeExample();
		Criteria criteria = example.createCriteria();
		if(CheckNullUtil.integerNotNull(type))
			criteria.andTypeEqualTo(type);
		if(CheckNullUtil.integerNotNull(userId))
			criteria.andUserIdEqualTo(userId);
		
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(page, rows);
		List list = LgLikeMapper.selectByExample(example);
		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	//添加收藏
	@Override
	public Map addToLike(LgLike lgLike) throws ServiceException {
		if(lgLike == null)
			throw new ServiceException("收藏内容为空");
		Integer userId = lgLike.getUserId();
		Integer contentId = lgLike.getContentId();
		String content = lgLike.getContent();
		Integer type = lgLike.getType();
		
		if(CheckNullUtil.integerNull(userId))
			throw new ServiceException("用户id为空");
		if(CheckNullUtil.integerNull(type))
			throw new ServiceException("类型不能为空");
		if(type != 5){//不为页面时，contentId不为空
			if(CheckNullUtil.integerNull(contentId))
				throw new ServiceException("contentId不为空");
		}
		LgLikeExample example = new LgLikeExample();
		 example.createCriteria().andUserIdEqualTo(userId).andContentIdEqualTo(contentId)
		 .andTypeEqualTo(type);
		 List<LgLike> list = LgLikeMapper.selectByExample(example);
		 if(list != null && list.size() > 0)
			 throw new ServiceException("已加过收藏");
		
		LgLike newLike = new LgLike();
		newLike.setUserId(userId);
		newLike.setContentId(contentId);
		newLike.setContent(content);
		newLike.setType(type);
		newLike.setCreateTime(FormatUtil.timeStampInt());
		int insertSta = LgLikeMapper.insertSelective(newLike);
		if(insertSta < 1)
			throw new ServiceException("插入失败");
		Integer insertId = LgPublicMapper.selectLastInsertId();
		
		return ReturnResult.ok(insertId);

	}
	//判断是否已收藏
	@Override
	public Map isLike(LgLike lgLike) throws ServiceException {
		Integer userId = lgLike.getUserId();
		Integer contentId = lgLike.getContentId();
		Integer type = lgLike.getType();
		//收藏的类型：1案例，2.招商，3设备 ,4 新闻，5页面
		if(CheckNullUtil.integerNull(userId) ||
		   CheckNullUtil.integerNull(contentId) ||
		   CheckNullUtil.integerNull(type)){
			throw new ServiceException("输入参数错误");
		}
		LgLikeExample example = new LgLikeExample();
		 example.createCriteria().andUserIdEqualTo(userId).andContentIdEqualTo(contentId)
		 .andTypeEqualTo(type);
		 List<LgLike> list = LgLikeMapper.selectByExample(example);
		 Map getMap = new HashMap<>();
		 if(CheckNullUtil.listNotNull(list)){
			 getMap.put("id", list.get(0).getId());
			 getMap.put("isLike", 1);
		 }else{
			 getMap.put("id", 0);
			 getMap.put("isLike", 0);
		 }
		
		return ReturnResult.ok(getMap);
	}
	//取消收藏
	@Override
	public Map deleteLike(Integer id) throws ServiceException {
		if(CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		if(LgLikeMapper.deleteByPrimaryKey(id) < 1)
			throw new ServiceException("取消收藏失败");
		
		return ReturnResult.ok();
	}
	

}
