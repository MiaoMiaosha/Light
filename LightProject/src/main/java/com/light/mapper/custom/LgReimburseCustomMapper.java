package com.light.mapper.custom;

public interface LgReimburseCustomMapper {
	/**
	 * 获取类型下的所有记录数
	 * @Title: countNumByExtraId 
	 * @author TobyHan
	 * Date : 2017年3月28日 下午4:56:21
	 */
	Integer countNumByExtraId(Integer id);
	String getPartnerNameById(Integer id);
}
