package com.light.service;

import com.github.pagehelper.PageInfo;

/**
 * 前台栏目审批
 * @ClassName: ApprovalService 
 * @author TobyHan
 * @date 2017年3月7日 上午11:02:39 
 *
 */
public interface ApprovalService {

	/**
	 * 获取案例申请列表
	 * @Title: getCaseApplyList 
	 * @author TobyHan
	 * Date : 2017年3月7日 上午11:05:48
	 */
	PageInfo<?> getCaseApplyList(Integer page,Integer rows);
	/**
	 * 获取市场申请列表
	 * @Title: getMarketApplyList 
	 * @author TobyHan
	 * Date : 2017年3月7日 上午11:03:49
	 */
	PageInfo<?> getMarketApplyList(Integer page,Integer rows);
	/**
	 * 获取摊位申请列表
	 * @Title: getStallApplyList 
	 * @author TobyHan
	 * Date : 2017年3月7日 上午11:05:01
	 */
	PageInfo<?> getStallApplyList(Integer page,Integer rows);

	/**
	 * 获取新闻申请列表
	 * @Title: getNewsApplyList 
	 * @author TobyHan
	 * Date : 2017年3月7日 上午11:07:00
	 */
	PageInfo<?> getNewsApplyList(Integer page,Integer rows);
	/**
	 * 获取加盟申请列表
	 * @Title: getCooperateApplyList 
	 * @author TobyHan
	 * Date : 2017年3月7日 上午11:07:45
	 */
	PageInfo<?> getCooperateApplyList(Integer page,Integer rows);
	/**
	 * 获取设备申请列表
	 * @Title: getGoodsApplyList 
	 * @author TobyHan
	 * Date : 2017年3月7日 上午11:08:43
	 */
	PageInfo<?> getGoodsApplyList(Integer page, Integer rows);
}
