package com.light.mapper.custom;

import java.util.List;
import java.util.Map;

import com.light.dto.CaseApplySqlBean;
import com.light.dto.CooperateApplySqlBean;
import com.light.dto.GoodsApplySqlBean;
import com.light.dto.MarketApplySqlBean;
import com.light.dto.NewsApplySqlBean;
import com.light.dto.StallApplySqlBean;

/**
 * 发布管理，自定义查询
 * @ClassName: LgManagePublishMapper 
 * @author TobyHan
 * @date 2017年2月18日 上午10:08:57 
 *
 */
public interface LgManagePublishMapper {

	/**
	 * 获取案例发布状态列表
	 * @Title: getCasePubilishApplyList 
	 * @author TobyHan
	 * Date : 2017年2月18日 上午10:32:21
	 */
    List<CaseApplySqlBean> getCasePubilishApplyList(Map paraMap);
    /**
     * 获取市场发布状态列表
     * @Title: getMarketApplyList 
     * @author TobyHan
     * Date : 2017年2月20日 下午12:00:33
     */
    List<MarketApplySqlBean> getMarketApplyList(Map paraMap);
    
    
    /** 
     * 获取摊位列表
     * @Title: getStallApplyList 
     * @author TobyHan
     * Date : 2017年2月20日 下午12:01:26
     */
    //暂时不需要此接口
    List<StallApplySqlBean> getStallApplyList(Map paraMap);

    /**
     * 获取新闻列表
     * @Title: getNewsApplyList 
     * @author TobyHan
     * Date : 2017年2月20日 下午12:02:10
     */
    List<NewsApplySqlBean> getNewsApplyList(Map paraMap);
    /**
     * 获取加盟列表
     * @Title: getCooperateApplyList 
     * @author TobyHan
     * Date : 2017年3月7日 下午9:09:48
     */
    List<CooperateApplySqlBean> getCooperateApplyList(Map paraMap);
    /**
     * 获取设备发布列表
     * @Title: getGoodsApplyList 
     * @author TobyHan
     * Date : 2017年3月15日 下午6:45:29
     */
    List<GoodsApplySqlBean> getGoodsApplyList(Map paraMap);

}
