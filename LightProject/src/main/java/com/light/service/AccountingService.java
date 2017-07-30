package com.light.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.pojo.LgAccounting;
import com.light.pojo.LgAccountingExtra;

/**
 * 入账服务
 * @ClassName: AccountingService 
 * @author TobyHan
 * @date 2017年2月15日 下午5:09:34 
 *
 */
public interface AccountingService {

	Map commitAccounting(LgAccounting lgAccounting,Integer loginUserId);

	PageInfo<?> getAccountingList(LgAccounting lgAccounting,Integer page, Integer rows,
			Integer startTime, Integer endTime);
	/**
	 * 修改入账记录
	 * @Title: modifyAccounting 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午10:43:33
	 */
	Map modifyAccounting(LgAccounting lgAccounting,Integer loginUserId);

	Map deleteAccountingById(Integer sid);
	
	/**
	 * 获取额外信息
	 * @Title: getExtraInfoList 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午9:25:21
	 */
	List getExtraInfoList(LgAccountingExtra extra);
	
	/**
	 * 确定入账状态
	 * @Title: confirmAccout 
	 * @author TobyHan
	 * Date : 2017年2月21日 下午2:28:32
	 */
	Map confirmAccout(Integer accountId, Integer status,Integer loginUserId);
}
