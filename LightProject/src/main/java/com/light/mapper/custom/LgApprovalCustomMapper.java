package com.light.mapper.custom;
/**
 * 审批相关
 * @ClassName: LgApprovalCustomMapper 
 * @author TobyHan
 * @date 2017年3月7日 下午3:56:10 
 *
 */
public interface LgApprovalCustomMapper {

	String getCaseNameByCaseId(Integer caseId);
	
	String getMarketNameByMarketId(Integer marketId);
	
	String getNewsTitleByNewsId(Integer newsId);
	
	String getCooperateCompanyById(Integer id);
	
	String getGoodsNameById(Integer goodId);
}
