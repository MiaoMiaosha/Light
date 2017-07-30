package com.light.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.light.common.CheckNullUtil;
import com.light.common.ReturnResult;
import com.light.dto.QNInfo;
import com.light.dto.WXIdAndSecret;
import com.light.exception.ServiceException;
import com.light.mapper.LgCaseMapper;
import com.light.mapper.LgConfigMapper;
import com.light.mapper.LgGoodsMapper;
import com.light.mapper.LgIntroduceMapper;
import com.light.mapper.LgMarketMapper;
import com.light.mapper.LgNewsMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgCase;
import com.light.pojo.LgCaseExample;
import com.light.pojo.LgConfig;
import com.light.pojo.LgConfigExample;
import com.light.pojo.LgGoods;
import com.light.pojo.LgGoodsExample;
import com.light.pojo.LgIntroduce;
import com.light.pojo.LgIntroduceExample;
import com.light.pojo.LgMarket;
import com.light.pojo.LgMarketExample;
import com.light.pojo.LgNews;
import com.light.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService{

	@Autowired LgConfigMapper LgConfigMapper;
	@Autowired LgCaseMapper LgCaseMapper; 
	@Autowired LgMarketMapper LgMarketMapper;
	@Autowired LgGoodsMapper LgGoodsMapper;
	@Autowired LgNewsMapper LgNewsMapper;
	@Autowired LgPublicMapper lgPublicMapper;
	@Autowired LgIntroduceMapper LgIntroduceMapper;
	//获取首页配置图片
	@Override
	public Map getIndexSetting() {
		LgConfigExample example = new LgConfigExample();
		 example.createCriteria().andIncTypeEqualTo("index");
		List<LgConfig> listIndex = LgConfigMapper.selectByExample(example);
		if(!CheckNullUtil.listNotNull(listIndex))
			throw new ServiceException("无相关配置");
		Map<String,Object> rsMap = new HashMap<>();
		for(LgConfig element : listIndex){
			rsMap.put(element.getName(), element.getValue());
		}
		LgConfigExample example2 = new LgConfigExample();
		 example2.createCriteria().andIncTypeEqualTo("index-small");
		List<LgConfig> listIndexSmall = LgConfigMapper.selectByExample(example2);
		if(!CheckNullUtil.listNotNull(listIndexSmall))
			throw new ServiceException("无相关配置");
		rsMap.put("picList", listIndexSmall);
		
/*		LgCaseExample caseExam = new LgCaseExample();
		 caseExam.createCriteria().andStateEqualTo(1);
		 caseExam.setOrderByClause("create_time desc");
		 PageHelper.startPage(1, 3);
		List<LgCase> caseList = LgCaseMapper.selectByExample(caseExam);
		List<Map<String,Object>> mapList = new ArrayList<>();
		if(CheckNullUtil.listNotNull(caseList)){
			for(LgCase element : caseList){
				Map<String,Object> elementMap = new HashMap<>();
				elementMap.put("cid",element.getCid());
					String getImgUrl = element.getImgUrl();
					String tempUrl = "";
				if(CheckNullUtil.isNotEmpty(getImgUrl))
					tempUrl = getImgUrl.split("#")[0];
				elementMap.put("imgUrl", tempUrl);
				mapList.add(elementMap);
			}
		}
		rsMap.put("caseList", mapList);*/
		LgConfigExample topExample = new LgConfigExample();
		topExample.createCriteria().andIncTypeEqualTo("index-top");
		List<LgConfig> topList= LgConfigMapper.selectByExample(topExample);
		List<Map<String, Object>> maps = null;
		for(LgConfig eConfig : topList){
			String getName = eConfig.getName();
			String getVal = eConfig.getValue();
			switch (getName) {
			case "case_list":
				maps = getCaseListByString(getVal);
				rsMap.put("caseList", maps);
				break;
			case "market_list":
				maps = getMarketListByString(getVal);
				rsMap.put("marketList", maps);
				break;
			case "goods_list":
				maps = getGoodsListByString(getVal);
				rsMap.put("goodsList", maps);
				break;
			case "news_list":
				maps = getNewsListByString(getVal);
				rsMap.put("newsLit", maps);
				break;
			default:
				break;
			}
		}
		
		
		LgConfigExample example3 = new LgConfigExample();
		 example3.createCriteria().andIncTypeEqualTo("index-setting");
		List<LgConfig> settingList= LgConfigMapper.selectByExample(example3);
		Map sonMap = new HashMap<>();
		if(settingList != null && settingList.size() >0){
			for(LgConfig element : settingList){
				sonMap.put(element.getName(), element.getValue());
			}
		}
		rsMap.put("settingMap", sonMap);
		PageHelper.startPage(1,3);
		LgCaseExample lasetCaseExa = new LgCaseExample();
			lasetCaseExa.createCriteria().andStateEqualTo(1);//审核通过才显示
			lasetCaseExa.setOrderByClause("create_time desc");
		List<LgCase> lastCaseList = LgCaseMapper.selectByExample(lasetCaseExa);
		rsMap.put("lastCase", lastCaseList);
		
		return ReturnResult.ok(rsMap);
	}
	//获取招租图片
	@Override
	public Map getBusPic() {
		LgConfigExample example = new LgConfigExample();
		 example.createCriteria().andIncTypeEqualTo("bus-pic");
		 List<LgConfig> lgConfigs = LgConfigMapper.selectByExample(example);
		 if(!CheckNullUtil.listNotNull(lgConfigs))
			 throw new ServiceException("无相关图片");
		return ReturnResult.ok(lgConfigs);
	}
	//设置首页相关配置
	@Override
	public Map setIndexSetting(Integer type, String urlKey,String adLink) throws ServiceException {
		if(type == null || type == 0)
			throw new ServiceException("请输入正确的type");
		String name = "";
		if(type == 1)
			name = "index_ad1";
		else if(type == 2){
			name = "index_ad2";
			
			LgConfigExample example = new LgConfigExample();
			 example.createCriteria().andNameEqualTo("index_ad2_url");
			LgConfig config =  new LgConfig();
			config.setValue(adLink);
			if(LgConfigMapper.updateByExampleSelective(config, example) < 1)
				throw new ServiceException("更新失败");
			
		}
		else if(type == 3)
			name = "mobile_number";
		else if(type == 4)
			name = "ICP";
		else if(type == 5)
			name = "copyright";
		else if(type == 6)
			name = "index_ad2_url";
		else {
			throw new ServiceException("请输入正确的type");
		}
		LgConfigExample example = new LgConfigExample();
		 example.createCriteria().andNameEqualTo(name);
		LgConfig config =  new LgConfig();
		config.setValue(urlKey);
		if(LgConfigMapper.updateByExampleSelective(config, example) < 1)
			throw new ServiceException("更新失败");
		
		return ReturnResult.ok();
	}
	//设置首页小图
	@Override
	public Map setSmallPic(Integer num, String urlKey) throws ServiceException {
		String name = "index_small_pic_"+num;
		LgConfigExample example = new LgConfigExample();
		 example.createCriteria().andNameEqualTo(name);
		LgConfig config =  new LgConfig();
		config.setValue(urlKey);
		if(LgConfigMapper.updateByExampleSelective(config, example) < 1)
			throw new ServiceException("更新失败");
		return ReturnResult.ok();
	}
	//设置招租图片
	@Override
	public Map setZhaoZuPics(Integer num, String urlKey) throws ServiceException {
		String name = "bus_pic_"+num;
		LgConfigExample example = new LgConfigExample();
		 example.createCriteria().andNameEqualTo(name);
		LgConfig config =  new LgConfig();
		config.setValue(urlKey);
		if(LgConfigMapper.updateByExampleSelective(config, example) < 1)
			throw new ServiceException("更新失败");
		return ReturnResult.ok();
	}
	//根据 1:2:3获取案例
	public List<Map<String, Object>> getCaseListByString(String sort){
		String[] sortArr = sort.split(":");
		if(sortArr == null || sortArr.length <1)
			throw new ServiceException("案例列表为空");
		List<Integer> values = new ArrayList<>();
		for(String element : sortArr){
			values.add(Integer.valueOf(element));
		}
		List<LgCase> caseList = new ArrayList<>();
		LgCase lgCase = null;
		for(Integer element : values){
			lgCase = LgCaseMapper.selectByPrimaryKey(element);
			if(lgCase != null)
			caseList.add(lgCase);
		}
		
/*		LgCaseExample caseExam = new LgCaseExample();
		 caseExam.createCriteria().andCidIn(values);
		List<LgCase> caseList = LgCaseMapper.selectByExample(caseExam);*/
		List<Map<String,Object>> mapList = new ArrayList<>();
		if(CheckNullUtil.listNotNull(caseList)){
			for(LgCase element : caseList){
				Map<String,Object> elementMap = new HashMap<>();
				elementMap.put("cid",element.getCid());
					String getImgUrl = element.getImgUrl();
					String tempUrl = "";
				if(CheckNullUtil.isNotEmpty(getImgUrl))
					tempUrl = getImgUrl.split("#")[0];
				elementMap.put("imgUrl", tempUrl);
				elementMap.put("caseName", element.getCaseName());
				elementMap.put("area", element.getArea());
				Integer level = element.getLevel();
				String levelName = "";
				switch (level) {
				case 1:
					levelName="装修中";
					break;
				case 2:
					levelName="农贸市场建筑";
					break;
				case 3:
					levelName="三星级";
					break;
				case 4:
					levelName="四星级";
					break;
				case 5:
					levelName="五星级";
					break;
				default:
					levelName="未评级";
					break;
				}
				elementMap.put("level",levelName);

				
				mapList.add(elementMap);
			}
		}
		return mapList;
		
	}
	//根据str获取市场
	public List<Map<String, Object>> getMarketListByString(String sort){
		String[] sortArr = sort.split(":");
		if(sortArr == null || sortArr.length <1)
			throw new ServiceException("市场列表为空");
		List<Integer> values = new ArrayList<>();
		for(String element : sortArr){
			values.add(Integer.valueOf(element));
		}
		List<LgMarket> markets = new ArrayList<>();
		LgMarket lgMarket = null;
		for(Integer element : values){
			lgMarket = LgMarketMapper.selectByPrimaryKey(element);
			if(lgMarket != null)
				markets.add(lgMarket);
		}
		
/*		LgMarketExample marketExample = new LgMarketExample();
		marketExample.createCriteria().andMidIn(values);
		List<LgMarket> markets = LgMarketMapper.selectByExample(marketExample);*/
		List<Map<String,Object>> mapList = new ArrayList<>();
		if(CheckNullUtil.listNotNull(markets)){
			for(LgMarket element : markets){
				Map<String,Object> elementMap = new HashMap<>();
				elementMap.put("mid",element.getMid());
					String getImgUrl = element.getImgUrl();
					String tempUrl = "";
				if(CheckNullUtil.isNotEmpty(getImgUrl))
					tempUrl = getImgUrl.split("#")[0];
				elementMap.put("imgUrl", tempUrl);
				elementMap.put("marketName", element.getMarketName());

				mapList.add(elementMap);
			}
		}
		return mapList;
	}
	//根据str获取设备列表
	public List<Map<String, Object>> getGoodsListByString(String sort){
		String[] sortArr = sort.split(":");
		if(sortArr == null || sortArr.length <1)
			throw new ServiceException("设备列表为空");
		List<Integer> values = new ArrayList<>();
		for(String element : sortArr){
			values.add(Integer.valueOf(element));
		}
		List<LgGoods> goods = new ArrayList<>();
		LgGoods good = null;
		for(Integer element : values){
			 good = LgGoodsMapper.selectByPrimaryKey(element);
			 if(good != null)
			 goods.add(good);
		}
		/*LgGoodsExample goodsExample = new LgGoodsExample();
		goodsExample.createCriteria().andGoodsIdIn(values);
		List<LgGoods> goods = LgGoodsMapper.selectByExample(goodsExample);*/
		List<Map<String,Object>> mapList = new ArrayList<>();
		if(CheckNullUtil.listNotNull(goods)){
			for(LgGoods element : goods){
				Map<String,Object> elementMap = new HashMap<>();
				elementMap.put("goodsId",element.getGoodsId());
					String getImgUrl = element.getImg();
					String tempUrl = "";
				if(CheckNullUtil.isNotEmpty(getImgUrl))
					tempUrl = getImgUrl.split("#")[0];
				elementMap.put("imgUrl", tempUrl);
				elementMap.put("goodsName", element.getGoodsName());
				elementMap.put("price", element.getGoodsPrice());
				mapList.add(elementMap);
			}
		}
		return mapList;
	}
	//根据str获取新闻
	public List<Map<String, Object>> getNewsListByString(String sort){
		String[] sortArr = sort.split(":");
		if(sortArr == null || sortArr.length <1)
			throw new ServiceException("新闻列表为空");
		List<Integer> values = new ArrayList<>();
		for(String element : sortArr){
			values.add(Integer.valueOf(element));
		}
		List<LgNews> newses = new ArrayList<>();
		LgNews news = null;
		for(Integer element : values){
			news = LgNewsMapper.selectByPrimaryKey(element);
			 if(news != null)
				 newses.add(news);
		}
		List<Map<String,Object>> mapList = new ArrayList<>();
		if(CheckNullUtil.listNotNull(newses)){
			for(LgNews element : newses){
				Map<String,Object> elementMap = new HashMap<>();
				elementMap.put("nid",element.getNid());
					String getImgUrl = element.getImgUrl();
					String tempUrl = "";
				if(CheckNullUtil.isNotEmpty(getImgUrl))
					tempUrl = getImgUrl.split("#")[0];
				elementMap.put("imgUrl", tempUrl);
				elementMap.put("newsTitle", element.getNewsTitle());
				elementMap.put("type", element.getType());
					Integer type = element.getType();
					String typeName = "未分类";
				if(type != null){
					if(type == 1){
						typeName = "公司新闻";
					}else if (type == 2) {
						typeName = "行业资讯";
					}
				}
				elementMap.put("typeName", typeName);
				
				mapList.add(elementMap);
			}
		}
		return mapList;
		
		
		
	}
	//设置置顶
	@Override
	public Map setTop(Integer type, Integer id) throws ServiceException {
		if(id == null || id == 0)
			throw new ServiceException("id为空");
		if(type == null || type == 0)
			throw new ServiceException("type为空");
		switch (type) {
		case 1:
			//案例
			setTopByNameWithId("case_list",id);
			break;
		case 2:
			//招商
			setTopByNameWithId("market_list",id);

			break;
		case 3:
			//设备
			setTopByNameWithId("goods_list",id);
			break;
		case 4:
			//新闻
			setTopByNameWithId("news_list",id);
		default:
			break;
		}
		
		return ReturnResult.ok();
	}
	 void setTopByNameWithId(String name,Integer id) throws ServiceException{
			LgConfigExample example = new LgConfigExample();
			 example.createCriteria().andNameEqualTo(name);
			List<LgConfig> list = LgConfigMapper.selectByExample(example);
			if(CheckNullUtil.listNotNull(list)){
				String configVal = list.get(0).getValue();
				String[] configValArr = configVal.split(":");
				 int len = configValArr.length;
				if(configValArr != null && len > 0){
					//1.如果置顶第一个
					if(id == Integer.valueOf(configValArr[0]))
						throw new ServiceException("当前已置顶");
					//2.如果置顶第二个
					else if(id == Integer.valueOf(configValArr[1])){
						String tmp = configValArr[0];
						configValArr[0] = String.valueOf(id);
						configValArr[1] = tmp;
					}else{
					for(int i=len-1;i>0;i--){
						configValArr[i] = configValArr[i-1];
					}
					configValArr[0] = String.valueOf(id);
				}
					LgConfig upObj = new LgConfig();
					StringBuffer sBuffer = new StringBuffer();
					for(int j=0;j<len;j++){
						if(j==len-1){
							sBuffer.append(configValArr[j]);
							break;
						}
						sBuffer.append(configValArr[j]+":");
					}
					upObj.setValue(sBuffer.toString());
					if(LgConfigMapper.updateByExampleSelective(upObj, example) < 1)
						throw new ServiceException("更新置顶失败");
				}
			}
	 }
	 //根据名字获取值
	@Override
	public String getConfigValueByName(String name) throws ServiceException {
		String gettingName = lgPublicMapper.getValueByConfigName(name);
		return gettingName;
	}
	@Override
	public String getDomain() throws ServiceException {
		return getConfigValueByName("domain");
	}
	@Override
	public String getIndexDir() throws ServiceException {
		return getConfigValueByName("html_index");
	}
	//获取微信信息
	@Override
	public String getAppid() throws ServiceException {
		String getWxUserId = getConfigValueByName("wx_id");
		return lgPublicMapper.getAppid(Integer.valueOf(getWxUserId));
	}
	@Override
	public String getAppsecret() throws ServiceException {
		String getWxUserId = getConfigValueByName("wx_id");
		return lgPublicMapper.getAppSecret(Integer.valueOf(getWxUserId));
	}
	@Override
	public WXIdAndSecret getIdAndSecret() throws ServiceException {
		String getWxUserId = getConfigValueByName("wx_id");
		return lgPublicMapper.getAppIdAndAppSecret(Integer.valueOf(getWxUserId));
	}
	@Override
	public QNInfo getQninfo() throws ServiceException {
		String ak = getConfigValueByName("QN_ACCESS_KEY");
		String sk = getConfigValueByName("QN_SECRET_KEY");
		String bucket = getConfigValueByName("QN_BUCKET_NAME");
		QNInfo qnInfo = new QNInfo();
			qnInfo.setAccessKey(ak);
			qnInfo.setSecretKey(sk);
			qnInfo.setBucketName(bucket);
		return qnInfo;
	}
	@Override
	public Map setIntro(LgIntroduce lgIntroduce) throws ServiceException {
		if(LgIntroduceMapper.updateByPrimaryKeySelective(lgIntroduce)<1)
			throw new ServiceException("更新失败");
		return ReturnResult.ok();
	}
	@Override
	public List getIntro(Integer id) throws ServiceException {
		LgIntroduceExample example =  new LgIntroduceExample();
		if(CheckNullUtil.integerNotNull(id)){
			example.createCriteria().andIdEqualTo(id);
		}
		List<LgIntroduce> list = LgIntroduceMapper.selectByExample(example);
		
		return list;
	}
	//设置config值
	@Override
	public void setConfigValueByName(String name, String value) throws ServiceException {
		LgConfigExample example = new LgConfigExample();
			example.createCriteria().andNameEqualTo(name);
		LgConfig record = new LgConfig();
			record.setValue(value);
		if(LgConfigMapper.updateByExampleSelective(record, example)<1)
			throw new ServiceException("设置出错");
	}
	

}
