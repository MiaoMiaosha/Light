package com.light.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.FormatUtil;
import com.light.common.ReturnResult;
import com.light.dto.SearchOutoutBean;
import com.light.exception.ServiceException;
import com.light.mapper.LgHotwordsMapper;
import com.light.mapper.custom.LgSearchCustomMapper;
import com.light.pojo.LgHotwords;
import com.light.pojo.LgHotwordsExample;
import com.light.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired LgSearchCustomMapper LgSearchCustomMapper;
	@Autowired LgHotwordsMapper LgHotwordsMapper;
	//获得搜索列表
	@Override
	public Map getSearchResultList(String key, Integer page, Integer rows) {
		
		if(CheckNullUtil.isEmpty(key))
			throw new ServiceException("请输入关键词");
		PageHelper.startPage(page, rows);
		List<SearchOutoutBean> caseList = LgSearchCustomMapper.getCaseResultList("%"+key+"%");
		 setList(caseList,1);
		
		PageHelper.startPage(page, rows);
		List<SearchOutoutBean> marketList = LgSearchCustomMapper.getMarketResultList("%"+key+"%");
		 setList(marketList,2);
		
		PageHelper.startPage(page, rows);
		List<SearchOutoutBean> goodsList  = LgSearchCustomMapper.getGoodsResultList("%"+key+"%");
		 setList(goodsList,3);

		PageHelper.startPage(page, rows);
		List<SearchOutoutBean> newsList = LgSearchCustomMapper.getNewsResultList("%"+key+"%");
		 setList(newsList,4);

		PageHelper.startPage(page, rows);
		List<SearchOutoutBean> cooperateList = LgSearchCustomMapper.getCooperateResultList("%"+key+"%");
		 setList(cooperateList,5);

		Map rsMap = new HashMap<>();
		rsMap.put("caseList", caseList);
		rsMap.put("marketList",marketList);
		rsMap.put("goodsList",goodsList);
		rsMap.put("newsList",newsList );
		rsMap.put("cooperateList",cooperateList);
		
		addHotwords(key, 0);

		return ReturnResult.ok(rsMap);
	}
	//其它搜索
	@Override
	public PageInfo<?> getSearchOtherList(Integer type, String key, Integer page, Integer rows) {
		if(CheckNullUtil.integerNull(type))
			throw new ServiceException("type为null");
		if(CheckNullUtil.isEmpty(key))
			throw new ServiceException("请输入关键词");
		List<SearchOutoutBean> list = null;
		switch (type) {
		case 1:
			PageHelper.startPage(page, rows);
			 list = LgSearchCustomMapper.getCaseResultList("%"+key+"%");
			 setList(list,1);
			break;
		case 2:
			PageHelper.startPage(page, rows);
			list = LgSearchCustomMapper.getMarketResultList("%"+key+"%");
			 setList(list,2);
			break;
		case 3:
			PageHelper.startPage(page, rows);
			list  = LgSearchCustomMapper.getGoodsResultList("%"+key+"%");
			 setList(list,3);

			break;
		case 4:
			PageHelper.startPage(page, rows);
			list = LgSearchCustomMapper.getNewsResultList("%"+key+"%");
			 setList(list,4);

			break;
		case 5:
			PageHelper.startPage(page, rows);
			list = LgSearchCustomMapper.getCooperateResultList("%"+key+"%");
			 setList(list,5);
			break;
		default:
			list = new ArrayList<>();;
		}
		PageInfo<?> pageInfo = new PageInfo<>(list);
		
		addHotwords(key,type);
		
		return pageInfo;
	}
	List<SearchOutoutBean> setList(List<SearchOutoutBean> list,Integer type){
		for(SearchOutoutBean element : list){
			element.setType(type);
		} 
		return list;
	}
	void addHotwords(String content,Integer type){
		if(content == null || content.trim().equals(""))
			return;
		LgHotwordsExample example = new LgHotwordsExample();
		example.createCriteria().andContentEqualTo(content).andTypeEqualTo(type);
		List<LgHotwords> list = LgHotwordsMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			LgHotwords getWord = list.get(0);
			LgHotwords record = new LgHotwords();
				record.setId(getWord.getId());
				record.setHeat(getWord.getHeat()+1);
			LgHotwordsMapper.updateByPrimaryKeySelective(record);
		}else{
			LgHotwords record = new LgHotwords();
			record.setContent(content);
			record.setType(type);
			record.setHeat(0);
			LgHotwordsMapper.insertSelective(record);
		}

	}
	//获取热词
	@Override
	public PageInfo<?> getHotwordsList(Integer page, Integer rows) {
		LgHotwordsExample example = new LgHotwordsExample();
			example.setOrderByClause("heat desc");
		PageHelper.startPage(page,rows);
		List<LgHotwords> list = LgHotwordsMapper.selectByExample(example);
		PageInfo<?> pageInfo =  new PageInfo<>(list);
		return pageInfo;
	}
	/**
	 * 删除热词
	 * @Title: deleteHotWords 
	 * @author TobyHan
	 * Date : 2017年3月23日 下午1:57:35
	 */
	@Scheduled(cron = "0 0 2 * * ?")
	void deleteHotWords(){
		Integer count = LgSearchCustomMapper.countHotWords();
		if(count != null && count > 32){
			List<Integer> orderList = LgSearchCustomMapper.getCountSortList();
			int size = orderList.size();
			for(int i=32;i<size;i++){
				LgHotwordsMapper.deleteByPrimaryKey(orderList.get(i));
			}
		}
		
		//System.out.println(FormatUtil.timeStamp2Date(FormatUtil.timeStampInt(), null)+"执行了一次");
		//System.out.println("count为："+count);
	}
	

}
