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
import com.light.mapper.LgNewsApplyMapper;
import com.light.mapper.LgNewsMapper;
import com.light.mapper.LgUserMapper;
import com.light.mapper.custom.LgCommentCustomMapper;
import com.light.mapper.custom.LgNewsCustomMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgNews;
import com.light.pojo.LgNewsApply;
import com.light.pojo.LgNewsExample;
import com.light.pojo.LgNewsExample.Criteria;
import com.light.pojo.LgUser;
import com.light.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired LgNewsMapper LgNewsMapper;
	@Autowired LgUserMapper LgUserMapper;
	@Autowired LgPublicMapper LgPublicMapper;
	@Autowired LgNewsApplyMapper LgNewsApplyMapper;
	@Autowired LgCommentCustomMapper lgCommentCustomMapper;
	@Autowired LgNewsCustomMapper lgNewsCustomMapper;
	//获取新闻列表---原接口
	@Override
	public PageInfo<?> getNewsList(LgNews lgNews, Integer page,
			Integer rows, Integer startTime, Integer endTime) throws ServiceException {
		Integer userId = lgNews.getUserId();
		String userName = lgNews.getUserName();
		Integer status = lgNews.getStatus();
	    Integer type = lgNews.getType();
	    
	    LgNewsExample example = new LgNewsExample();
	    Criteria criteria = example.createCriteria();
	    if(CheckNullUtil.integerNotNull(userId))
	    	criteria.andUserIdEqualTo(userId);
	    if(CheckNullUtil.isNotEmpty(userName))
	    	criteria.andUserNameEqualTo(userName);
	    if(CheckNullUtil.integerNotNull(status))
	    	criteria.andStatusEqualTo(status);
	    if(CheckNullUtil.integerNotNull(type))
	    	criteria.andTypeEqualTo(type);
	    if(FormatUtil.integerInterval(startTime, endTime)){
	    	criteria.andCreateTimeBetween(startTime, endTime);
	    }else{
	    	if(CheckNullUtil.integerNotNull(startTime))
	    		criteria.andCreateTimeGreaterThan(startTime);
	    	else if(CheckNullUtil.integerNotNull(endTime))
	    		criteria.andCreateTimeLessThan(endTime);
	    }
	    PageHelper.startPage(page,rows);
	    List<LgNews> list = LgNewsMapper.selectByExample(example);
	    if(CheckNullUtil.listNotNull(list)){
	    	for(LgNews element : list){
	    		Integer getNid = element.getNid();
	    		Integer countNum = lgCommentCustomMapper.getCommentCountByNid(getNid);
	    		element.setCommentCount(countNum==null?0:countNum);
	    	}
	    }
	    PageInfo<?> pageInfo = new PageInfo<>(list);
	    return pageInfo;
	    
	}
	//获取新闻详情
	@Override
	public LgNews getNewsDetailById(Integer newsId) throws ServiceException {
		if(CheckNullUtil.integerNull(newsId))
			throw new ServiceException("id为空");
		LgNews lgNews = LgNewsMapper.selectByPrimaryKey(newsId);
	    if(lgNews != null){
	    		Integer getNid = lgNews.getNid();
	    		Integer countNum = lgCommentCustomMapper.getCommentCountByNid(getNid);
	    		lgNews.setCommentCount(countNum==null?0:countNum);
	    }
		return lgNews;
	}
	//发布新闻
	@Override
	public Map publishNews(LgNews lgNews) throws ServiceException {
		if(lgNews == null)
			throw new ServiceException("发布内容为空");
		Integer userId = lgNews.getUserId();
		if(CheckNullUtil.integerNull(userId))
			throw new ServiceException("userId为空");

		//userName查询获得
		String content = lgNews.getContent();
		String newsTitle = lgNews.getNewsTitle();
		
		if(CheckNullUtil.isEmpty(newsTitle))
			throw new ServiceException("newsTitle为空");
		Integer type = lgNews.getType();//1-公司新闻 2-行业资讯
		String imgUrl = lgNews.getImgUrl();
		
		Integer contentType = lgNews.getContentType();
		if(contentType == null){
			throw new ServiceException("发布类型为空");
		}

		
		LgNews newNews = new LgNews();
		newNews.setUserId(userId);
		LgUser lgUser = LgUserMapper.selectByPrimaryKey(userId);
		if(lgUser != null)
			newNews.setUserName(lgUser.getUserName());
		else 
			newNews.setUserName("UnKnow");
		if(contentType == 1){
			newNews.setPcContent(lgNews.getPcContent());
		}else{
		    newNews.setContent(content);
		}
		newNews.setStatus(0);//状态：0-未发布；1-发布中；2-发布关闭
		newNews.setNewsTitle(newsTitle);
		newNews.setType(type);
		newNews.setCreateTime(FormatUtil.timeStampInt());
		newNews.setImgUrl(imgUrl);
		newNews.setContentType(contentType);
		int insertNews = LgNewsMapper.insertSelective(newNews);
		if(insertNews < 1)
			throw new ServiceException("插入新闻表失败");
		Integer insertNewsId = LgPublicMapper.selectLastInsertId();
		
		LgNewsApply apply = new LgNewsApply();
		apply.setUserId(userId);
		apply.setNewsId(insertNewsId);
		apply.setCreateTime(FormatUtil.timeStampInt());
		apply.setStatus(0);
		int insertApplySta = LgNewsApplyMapper.insertSelective(apply);
		if(insertApplySta < 1)
			throw new ServiceException("插入新闻申请失败");
        Map rsMap = new HashMap<>();
        rsMap.put("newsId", insertNewsId);

		return ReturnResult.ok(rsMap);
	}
	//增加浏览记录
	@Override
	public Map addView(Integer nid) throws ServiceException {
		if(CheckNullUtil.integerNull(nid))
			throw new ServiceException("nid为空");
		LgNews lgNews = LgNewsMapper.selectByPrimaryKey(nid);
		if(lgNews == null) throw new ServiceException("无此新闻");
		Integer count = lgNews.getViewCount();
			int saveCount = 0;
		if(count == null) saveCount = 1;
		else {
			saveCount = ++count;
		}
		LgNews record = new LgNews();
			record.setNid(nid);
			record.setViewCount(saveCount);
		if(LgNewsMapper.updateByPrimaryKeySelective(record) <1)
			throw new ServiceException("更新失败");
		return ReturnResult.ok();
	}
	//获取新闻列表--新接口
	@Override
	public PageInfo<?> getNewsListNew(LgNews lgNews, Integer page, Integer rows, Integer startTime, Integer endTime)
			throws ServiceException {
		
		Integer userId = lgNews.getUserId();
		String userName = lgNews.getUserName();
		Integer status = lgNews.getStatus();
	    Integer type = lgNews.getType();
	    Integer contentType = lgNews.getContentType();
	    
	    Map paramMap = new HashMap<>();
	    if(CheckNullUtil.integerNotNull(userId))
	    	paramMap.put("userId", userId);
	    if(CheckNullUtil.isNotEmpty(userName))
	    	paramMap.put("userName", userName);
	    if(CheckNullUtil.integerNotNull(status))
	    	paramMap.put("status", status);
	    if(CheckNullUtil.integerNotNull(type))
	    	paramMap.put("type", type);
	    if(CheckNullUtil.integerNotNull(contentType))
	    	paramMap.put("contentType", contentType);
	    if(FormatUtil.integerInterval(startTime, endTime)){
	    	paramMap.put("startTime", startTime);
	    	paramMap.put("endTime", endTime);
	    }else{
	    	if(CheckNullUtil.integerNotNull(startTime))
		    	paramMap.put("startTime", startTime);
	    	else if(CheckNullUtil.integerNotNull(endTime))
		    	paramMap.put("endTime", endTime);
	    }
    	paramMap.put("order", "create_time");

		PageHelper.startPage(page, rows);
		List<LgNews> list = lgNewsCustomMapper.getListWithoutContent(paramMap);
	    if(CheckNullUtil.listNotNull(list)){
	    	for(LgNews element : list){
	    		Integer getNid = element.getNid();
	    		Integer countNum = lgCommentCustomMapper.getCommentCountByNid(getNid);
	    		element.setCommentCount(countNum==null?0:countNum);
	    	}
	    }
		
		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}
