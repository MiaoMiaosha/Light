package com.light.common;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

public class WXUtil {

	/**
	 * 判断用户是否关注了公众号
	 * @param token
	 * @param openid
	 * @return
	 */
	public static boolean judgeIsFollow(String token,String openid){
	    Integer subscribe = 0;
	        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+token+"&openid="+openid+"&lang=zh_CN";
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
	            JSONObject demoJson = JSONObject.parseObject(message);  
	            //System.out.println("JSON字符串："+demoJson);  
	            subscribe = demoJson.getInteger("subscribe");

	            is.close();  
	    } catch (Exception e) {  
	            e.printStackTrace();  
	    }
	    return 1==subscribe?true:false;
	}
}
