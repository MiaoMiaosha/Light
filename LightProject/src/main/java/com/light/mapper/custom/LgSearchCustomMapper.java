package com.light.mapper.custom;

import java.util.List;

import com.light.dto.SearchOutoutBean;

public interface LgSearchCustomMapper {

	List<SearchOutoutBean> getCaseResultList(String key);
	List<SearchOutoutBean> getMarketResultList(String key);
	List<SearchOutoutBean> getGoodsResultList(String key);
	List<SearchOutoutBean> getNewsResultList(String key);
	List<SearchOutoutBean> getCooperateResultList(String key);
	
	Integer countHotWords();
	List<Integer> getCountSortList();

}
