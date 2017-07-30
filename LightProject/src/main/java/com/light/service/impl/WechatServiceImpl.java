package com.light.service.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.light.common.CheckNullUtil;
import com.light.common.ConstantVal;
import com.light.common.FormatUtil;
import com.light.common.HttpUtil;
import com.light.common.UidMake;
import com.light.common.WebAccessTokenReturn;
import com.light.dto.WXIdAndSecret;
import com.light.exception.ServiceException;
import com.light.mapper.LgUidMapper;
import com.light.mapper.LgUserMapper;
import com.light.mapper.LgWxUserMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgUid;
import com.light.pojo.LgUidExample;
import com.light.pojo.LgUser;
import com.light.pojo.LgUserExample;
import com.light.pojo.LgWxUser;
import com.light.pojo.LgWxUserExample;
import com.light.service.ConfigService;
import com.light.service.WechatService;

@Service
public class WechatServiceImpl implements WechatService {

	@Autowired LgUserMapper lgUserMapper;
	@Autowired LgPublicMapper lgPublicMapper;
	@Autowired LgWxUserMapper LgWxUserMapper;
	@Autowired LgUidMapper LgUidMapper;
	
	@Autowired ConfigService configService;
	//获取access_token json对象
	public WebAccessTokenReturn getAccessTokenByCode(String code) throws ServiceException {
		WebAccessTokenReturn tokenJson = null;
		WXIdAndSecret obj = configService.getIdAndSecret();
		
		String url = 
	"https://api.weixin.qq.com/sns/oauth2/access_token?"
	+ "appid="+obj.getAppid()+"&secret="+obj.getAppsecret()+"&code="+code+"&grant_type=authorization_code";
	
	    try {  
	        URL urlGet = new URL(url);  
	        HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();  
	        http.setRequestMethod("GET"); // 必须是get方式请求  
	        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
	        http.setDoOutput(true);  
	        http.setDoInput(true);  
	        http.connect();  
	        InputStream is = http.getInputStream();  
	        int size = is.available();  
	        byte[] jsonBytes = new byte[size];  
	        is.read(jsonBytes);  
	        String message = new String(jsonBytes, "UTF-8");  
	         tokenJson = JSONObject.parseObject(message,WebAccessTokenReturn.class);  
	         
	        is.close();  
	} catch (Exception e) {  
	        e.printStackTrace();  
	}
     return tokenJson;
  }

	//拉取用户信息并存储
	@Override
	public Integer getUserInfoAndSave(WebAccessTokenReturn json) throws ServiceException{
		
		if(json == null)
			throw new ServiceException("对象为空");
		int newUserId = 0;
		String accessToken = json.getAccess_token();
		String openId = json.getOpenid();
		String url = 
		"https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
		    try {  
		        URL urlGet = new URL(url);  
		        HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();  
		        http.setRequestMethod("GET"); // 必须是get方式请求  
		        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
		        http.setDoOutput(true);  
		        http.setDoInput(true);  
		        http.connect();  
		        InputStream is = http.getInputStream();  
		        int size = is.available();  
		        byte[] jsonBytes = new byte[size];  
		        is.read(jsonBytes);  
		        String message = new String(jsonBytes, "UTF-8");  
	            JSONObject returnJson = JSONObject.parseObject(message); 
	            is.close();  
	            if(returnJson.get("errcode") != null){
		        	//return ReturnResult.wrong(returnJson.getString("errmsg"));
					throw new ServiceException(returnJson.getString("errmsg"));
	            }
	            else{
	            	
	            String returnOpenId = returnJson.getString("openid");
	            String nickname = returnJson.getString("nickname");
	            String sex = returnJson.getString("sex");
	            String province = returnJson.getString("province");
	            String city = returnJson.getString("city");
	            String country = returnJson.getString("country");
	            String headimgurl = returnJson.getString("headimgurl");
	            //查询用户是否已经存在
	            LgUserExample userExample = new LgUserExample();
	            	userExample.createCriteria().andOpenidEqualTo(returnOpenId);
	            List list = lgUserMapper.selectByExample(userExample);
	            if(CheckNullUtil.listNotNull(list)){
	            	LgUser tempUser = (LgUser) list.get(0);
	            	//return ReturnResult.ok("用户已存在",tempUser.getUserId());
	            	Integer regTime = tempUser.getRegTime();
	            	Integer curTime = FormatUtil.timeStampInt();
	            	//三天更新一次用户最新数据
	            	if(regTime != null && regTime < curTime){
	            		regTime = curTime + 3600 * 24 * 3;
	            		LgUser record = new LgUser();
	            			record.setUserId(tempUser.getUserId());
	            			record.setRegTime(regTime);
	            			record.setUserName(nickname);
	            			record.setSex(Integer.valueOf(sex));
	            			record.setProvince(province);
	            			record.setCity(city);
	            			record.setCountry(country);
	            			record.setHeadimgurl(headimgurl);
	            		lgUserMapper.updateByPrimaryKeySelective(record);
	            	}
	            	return tempUser.getUserId();
	            }
	            

	            //String unionid = returnJson.getString("unionid");

	            LgUser lgUser = new LgUser();
	            lgUser.setUserName(nickname);
	            lgUser.setIsLocked(false);
	            lgUser.setSex(Integer.valueOf(sex));
	            lgUser.setRegTime(FormatUtil.timeStampInt());
	            lgUser.setHeadimgurl(headimgurl);
	            lgUser.setOpenid(returnOpenId);
	           // lgUser.setUnionid(unionid);
	            lgUser.setProvince(province);
	            lgUser.setCity(city);
	            lgUser.setCountry(country);
	            int insertSta = lgUserMapper.insertSelective(lgUser);
	            if(insertSta < 1)
	            	throw new ServiceException("新增用户失败");
		        	//return ReturnResult.wrong("新增用户失败");
	            }
	             newUserId = lgPublicMapper.selectLastInsertId();
		      
		} catch (Exception e) {  
		        //return ReturnResult.wrong(e.getMessage());
			  throw new ServiceException(e.getMessage());
		}
		
		return  newUserId;
	}
	//获取基本的access_token
	@Override
	public Map getBasicAccessToken(LgWxUser wxUser) throws ServiceException {
		
		Integer authorizerExpires = wxUser.getAuthorizerExpires();
		String  authorizerAccessToken = wxUser.getAuthorizerAccessToken();
		String appid = wxUser.getAppid();
		String appsecret = wxUser.getAppsecret();
		
        Map rsMap = new HashMap<>();
		if(authorizerAccessToken == null || "".equals(authorizerAccessToken)
			|| authorizerExpires == null || authorizerExpires == 0 ||
			authorizerExpires <= FormatUtil.timeStampInt() 
				){
			//token时间过期 或 为空,重新获取token
			String url = "https://api.weixin.qq.com/cgi-bin/token?"
					+ "grant_type=client_credential&appid="+appid
					+"&secret="+appsecret+"";
			String msg = HttpUtil.sendBasicParamUrlHttp(url, "GET");
		    JSONObject tempObj = JSONObject.parseObject(msg);
	        if(tempObj.getInteger("errcode") != null){
	        	//如果接口错误
	        	rsMap.put("code", 500);
	        	rsMap.put("errcode", tempObj.get("errcode"));
	        	rsMap.put("errmsg", tempObj.get("errmsg"));
	        }else{
	        	Integer expiresIn = FormatUtil.timeStampInt()+tempObj.getInteger("expires_in")-10;
	        	authorizerAccessToken = (String) tempObj.get("access_token");
	        	rsMap.put("code", 200);
	        	rsMap.put("access_token", authorizerAccessToken);
	        	rsMap.put("expires_in", expiresIn);

	        	//存到数据库中
	        	LgWxUser insertMsg = new LgWxUser();
	        		insertMsg.setId(wxUser.getId());
	        		insertMsg.setAuthorizerAccessToken(authorizerAccessToken);
	        		insertMsg.setAuthorizerExpires(expiresIn);
	        	
	        	if(LgWxUserMapper.updateByPrimaryKeySelective(insertMsg) < 1)
	        		throw new ServiceException("更新数据库失败");
	        	
	        } 
		}
		//否则直接返回token
		else{
			rsMap.put("code", 200);
        	rsMap.put("access_token",authorizerAccessToken);
        	rsMap.put("expires_in", wxUser.getAuthorizerExpires());
		}
		return rsMap;
	}
	//获取当前可用微信公众号
	@Override
	public LgWxUser getCurrentWxUser() throws ServiceException {
		/*LgWxUserExample example = new LgWxUserExample();
		example.createCriteria().andWxnameEqualTo(wxname);
		List<LgWxUser> list = LgWxUserMapper.selectByExample(example);
		if(list == null || list.size() < 1)
			throw new ServiceException("公众号不存在");
		LgWxUser getWxUser = list.get(0);*/
		
		String wxIdStr = configService.getConfigValueByName("wx_id");
		Integer wxId = Integer.valueOf(wxIdStr);
		LgWxUser getWxUser = LgWxUserMapper.selectByPrimaryKey(wxId);
		
		String appId = getWxUser.getAppid();
		String appSecret = getWxUser.getAppsecret();
		//WXIdAndSecret wxObj = configService.getIdAndSecret();
		//String appId = wxObj.getAppid();
		//String appSecret = wxObj.getAppsecret();
		if(CheckNullUtil.isEmpty(appId) || CheckNullUtil.isEmpty(appSecret))
			throw new ServiceException("请完善公众号配置");
		return getWxUser;
	}
	//给员工，发送模板消息
	@Override
	public Boolean sendCommentTemplateToUser(List<String> userList,String market,String title,String content,String url) {
		LgWxUser temp = getCurrentWxUser();
		Map rsMap = getBasicAccessToken(temp);
		Integer code = (Integer) rsMap.get("code");
		if(code != null){
			if(code == 200){
				String accessToken = (String) rsMap.get("access_token");
				String sendToUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken+"";
				for(String element : userList){//name 去掉
					if(element == null) continue;
					String sendStr =
							"{\n" +
									"    \"touser\": \""+element+"\",\n" +
									"    \"template_id\": \"xfpkxlbPCIX5ipN-IYaZXr53E600uk4JIq3AB3Ix6_k\",\n" +
									"    \"url\": \""+url+"\",\n" +
									"    \"topcolor\": \"#FF0000\",\n" +  
									"    \"data\":{\n" +
									"        \"market\":{\n" +
									"        	\"value\": \""+market+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"        },\n" +
									"        \"title\":{\n" +
									"    		 \"value\": \""+title+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"            \n" +
									"        },\n" +
									"        \"content\":{\n" +
									"        	\"value\": \""+content+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"        }\n" +
									"    }\n" +
									"}"
									;
					HttpUtil.sendJsonMsgWithJsonStr(sendToUrl,sendStr);
				}
			}
		}

		return null;
	}

	@Override
	public LgWxUser getCuWxUserById(Integer id) throws ServiceException {
		
		LgWxUser wxUser = LgWxUserMapper.selectByPrimaryKey(id);
		
		return wxUser;
	}
	//获得有效jsapi_ticket
	@Override
	public String getEffectiveJsapiTicket() throws ServiceException {
		String finalGetTicket = "";
		//获取当前公众号对象
		String getWxUserId = configService.getConfigValueByName("wx_id");
		Integer getWxUserIdInt = Integer.valueOf(getWxUserId);
		LgWxUser wxUser = getCuWxUserById(getWxUserIdInt);
		if(wxUser == null)
			throw new ServiceException("请设置公众号");
		//判断jsapi是否为空 ，是否过期
		String getJsapiTicket = wxUser.getJsapiTicket();
		Integer getExpires = wxUser.getJsapiTicketExpires();
		if(getJsapiTicket == null || getJsapiTicket.trim().equals("")
			|| getExpires == null || getExpires < FormatUtil.timeStampInt()){
			//重新获取
			//1.获取acesstoken
			LgWxUser temp = getCurrentWxUser();
			Map rsMap = getBasicAccessToken(temp);
			Integer code = (Integer) rsMap.get("code");
			if(code != null){
				if(code == 200){
					//2.根据accesstoken获取jsapi
					String accessToken = (String) rsMap.get("access_token");
					String jsUrl = 
					"https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
					String msg = HttpUtil.sendBasicParamUrlHttp(jsUrl, "GET");
					JSONObject jsObj = JSONObject.parseObject(msg);
					if(jsObj.getInteger("errcode") == 0){
						String correctTicket = jsObj.getString("ticket");
						Integer outTime = jsObj.getInteger("expires_in")+FormatUtil.timeStampInt()-20;
						LgWxUser record = new LgWxUser();
							record.setId(getWxUserIdInt);
							record.setJsapiTicket(correctTicket);
							record.setJsapiTicketExpires(outTime);
						LgWxUserMapper.updateByPrimaryKeySelective(record);
						finalGetTicket = correctTicket;
					}
				}
			}
			
			
		}
		else{
			finalGetTicket = getJsapiTicket;
		}
		return finalGetTicket;
	}
	//发送给员工，访客发布图面通知
	@Override
	public void customerPublishPost(List<String> userList, String market, String title, String content,
			String url, String username,String remark) {
		LgWxUser temp = getCurrentWxUser();
		Map rsMap = getBasicAccessToken(temp);
		Integer code = (Integer) rsMap.get("code");
		if(code != null){
			if(code == 200){
				String accessToken = (String) rsMap.get("access_token");
				String sendToUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken+"";
				for(String element : userList){//name 去掉
					if(element == null) continue;
					String sendStr =
							"{\n" +
									"    \"touser\": \""+element+"\",\n" +
									"    \"template_id\": \"-yKDOqL6BZUNAo3aftCG6q0ioFUZgaTAgWD0MjedW4o\",\n" +
									"    \"url\": \""+url+"\",\n" +
									"    \"topcolor\": \"#FF0000\",\n" +  
									"    \"data\":{\n" +
									"        \"first\":{\n" +
									"        	\"value\": \""+"您好!"+market+"有相关咨询"+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"        },\n" +
									"        \"user\":{\n" +
									"    		 \"value\": \""+username+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"            \n" +
									"        },\n" +
									"        \"ask\":{\n" +
									"        	\"value\": \""+title+":"+content+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"        },\n" +
									"        \"remark\":{\n" +
									"        	\"value\": \""+remark+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"        }\n" +
									"    }\n" +
									"}"
									;
					HttpUtil.sendJsonMsgWithJsonStr(sendToUrl,sendStr);
				}
			}
		}

		
	}
	//咨询回复提醒--员工或客户回复
	@Override
	public void msgForReply(List<String> userList, String url,String replyContent,String username, String replyTime, String askTime, String title,
			String content, String remark) {
		LgWxUser temp = getCurrentWxUser();
		Map rsMap = getBasicAccessToken(temp);
		Integer code = (Integer) rsMap.get("code");
		if(code != null){
			if(code == 200){
				String accessToken = (String) rsMap.get("access_token");
				String sendToUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken+"";
				for(String element : userList){//name 去掉
					if(element == null) continue;
					String sendStr =
							"{\n" +
									"    \"touser\": \""+element+"\",\n" +
									"    \"template_id\": \"nIG07_2eu6RZnbl1vO0USN0N-eAYSd5_2XuQmoPjTkI\",\n" +
									"    \"url\": \""+url+"\",\n" +
									"    \"topcolor\": \"#FF0000\",\n" +  
									"    \"data\":{\n" +
									"        \"first\":{\n" +
									"        	\"value\": \""+replyContent+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"        },\n" +
									"        \"keyword1\":{\n" +
									"    		 \"value\": \""+username+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"            \n" +
									"        },\n" +
									"        \"keyword2\":{\n" +
									"        	\"value\": \""+replyTime+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"        },\n" +
									"        \"keyword3\":{\n" +
									"        	\"value\": \""+askTime+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"        },\n" +
									"        \"keyword4\":{\n" +
									"        	\"value\": \""+title+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"        },\n" +
									"        \"keyword5\":{\n" +
									"        	\"value\": \""+content+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"        },\n" +
									"        \"remark\":{\n" +
									"        	\"value\": \""+remark+"\",\n" +
									"            \"color\": \"#173177\"\n" +
									"        }\n" +
									"    }\n" +
									"}"
									;
					HttpUtil.sendJsonMsgWithJsonStr(sendToUrl,sendStr);
				}
			}
		}
		
	}
	//更改uid状态
	@Override
	public void updateLoginUid(String uid,Integer userId) throws ServiceException {
		if(CheckNullUtil.isEmpty(uid))
			throw new ServiceException("uid为空");
		LgUidExample example = new LgUidExample();
			example.createCriteria().andUidEqualTo(uid);
		List<LgUid> list = LgUidMapper.selectByExample(example);
		if(list == null || list.size()<1)
			throw new ServiceException("uid不存在");
		LgUid getUid = list.get(0);
			getUid.setCreateTime(null);
			getUid.setStatus(1);
			getUid.setUid(null);
			getUid.setUserId(userId);
		if(LgUidMapper.updateByPrimaryKeySelective(getUid)<1)
			throw new ServiceException("更新uid失败");
	}

	@Override
	public String addUid() throws ServiceException {
		String makeUid = UidMake.getUid();
		LgUid newUid = new LgUid();
			newUid.setCreateTime(FormatUtil.timeStampInt());
			newUid.setStatus(0);
			newUid .setUid(makeUid);
		if(LgUidMapper.insertSelective(newUid)<1)
			throw new ServiceException("申请uid失败，请刷新");
		return makeUid;
	}
	//查询uid状态
	@Override
	public Map queryUidSta(String uid) throws ServiceException {
		Map<String, Object> dataMap = new HashMap<>();
		LgUidExample example = new LgUidExample();
			example.createCriteria().andUidEqualTo(uid);
		List<LgUid> list = LgUidMapper.selectByExample(example);
		if(list == null || list.size()<1)
			throw new ServiceException("uid不存在");
		LgUid getUid = list.get(0);
		int outTime = 2*24*3600;
		if(FormatUtil.timeStampInt() > getUid.getCreateTime()+outTime){
			getUid.setStatus(2);
		if(LgUidMapper.updateByPrimaryKeySelective(getUid)<1){
			throw new ServiceException("更新uid失败");}
			dataMap.put("status", 2);
    		dataMap.put("msg", "已过期");
			return dataMap;
		}
		else if (getUid.getStatus()==0) {
			dataMap.put("status", 0);
    		dataMap.put("msg", "未登录");
			return dataMap;
		}
		else if (getUid.getStatus()==1) {
			dataMap.put("status", 1);
    		dataMap.put("msg", "已登录");
    		dataMap.put("userId", getUid.getUserId());
			return dataMap;
		}
		dataMap.put("status", 2);
		dataMap.put("msg", "未知错误");
		return dataMap;
	}
}
