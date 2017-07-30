package com.light.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgMemo;

public interface MemoService {

	/**
	 * 添加新备忘记录
	 * @Title: addNewMemo 
	 * @author TobyHan
	 * Date : 2017年3月20日 下午11:05:27
	 */
	Map addNewMemo(LgMemo lgMemo) throws ServiceException;
	/**
	 * 获取备忘录列表
	 * @Title: getMemoList 
	 * @author TobyHan
	 * Date : 2017年3月20日 下午11:05:42
	 */
	PageInfo<?> getMemoList(LgMemo lgMemo,Integer page,Integer rows);
	/**
	 * 编辑备忘录
	 * @Title: editMemo 
	 * @author TobyHan
	 * Date : 2017年3月20日 下午11:06:17
	 */
	Map editMemo(LgMemo lgMemo) throws ServiceException;
	/**
	 * 删除记录
	 * @Title: deleteMemo 
	 * @author TobyHan
	 * Date : 2017年3月20日 下午11:06:50
	 */
	Map deleteMemo(Integer id) throws ServiceException;
	
}
