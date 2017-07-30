package com.light.mapper.custom;
/**
 * 客户档案，自定义查询
 * @ClassName: LgCustomerCustomMapper 
 * @author TobyHan
 * @date 2017年2月27日 下午7:34:31 
 *
 */
public interface LgCustomerCustomMapper {

	/**
	 * 根据cid查客户名称
	 * @Title: getCustomerNameByCid 
	 * @author TobyHan
	 * Date : 2017年2月27日 下午7:35:02
	 */
	String getCustomerNameByCid(Integer cid);
}
