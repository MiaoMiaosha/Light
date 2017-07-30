package com.light.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.xmlrpc.base.Data;

public class HttpUtil {

	/**
	 * 发送json消息
	 * @Title: sendJsonMsg 
	 * @author TobyHan
	 * Date : 2017年3月8日 下午5:05:24
	 */
	public static String sendJsonMsg(String add_url,JSONObject jsonObject){
        
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(add_url);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
             System.out.println(jsonObject.toString());

            out.writeBytes(jsonObject.toString());
            out.flush();
            out.close();
             
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
           
             while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    sbf.append(lines);
                }
               // System.out.println(sbf);
                reader.close();
                // 断开连接
                connection.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sbf.toString();
        
    }
/**
 * 发送json 文本消息
 * 获取响应json
 */
public static String sendJsonMsgWithJsonStr(String add_url,String jsonStr){
        
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(add_url);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(jsonStr.getBytes());
            out.flush();
            out.close();
             
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
           
             while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    sbf.append(lines);
                }
               // System.out.println(sbf);
                reader.close();
                // 断开连接
                connection.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sbf.toString();
        
    }
/**
 * 向url发送基本参数请求 #{url}?id=1&type=2
 * 获取返回的json文本
 */
public static String sendBasicParamUrlHttp(String url,String method){
	String message = "";
	try {
    URL urlGet = new URL(url);  
    HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();  
    http.setConnectTimeout(30000);  
    http.setReadTimeout(30000); 
    http.setRequestMethod(method); //GET OR POST
    http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
    http.setDoOutput(true);  
    http.setDoInput(true);  
    http.connect();  
    InputStream in = http.getInputStream();  
    long count = 0;    
    while (count == 0) {    
     count=http.getContentLength();
    }    
    byte[] b = new byte[(int) count];    
  
     int readCount = 0; // 已经成功读取的字节的个数    
     while (readCount < count) {    
      readCount += in.read(b, readCount, (int) (count - readCount)); 
     }
     in.close(); 
      message = new String(b, "UTF-8");
		} 
	catch (Exception e) {
  }
	
	return message;
}
	public static String getWhat(){
		return null;
	}
	
	public  static void main(String args[]){
	/*	String accessToken = "rucwzy2d3Hr_-7hCIAPm1g704HRgqJtanZc1GCiA33NXg7C3G0egszU3QJHWle3mJkVAtWo2_frXNYeQW6loZHicsvR7osWmOQ_OXifyBFFdnHH33VP8Ym2LyvT9uIEoKFHiAHADYA";
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken+"";
		JSONObject obj = new JSONObject();
		obj.put("touser", "ol6OBt-AaiMp7CrNHHfXgrypodbM");
		obj.put("template_id","y0KFD4AUVD_H5nZ__H9XUs13i09xqAvgkMX-KupPDzY");
		obj.put("url", "http://wytechhome.com/randomer-index/index.html");
		obj.put("topcolor", "#FF0000");
			JSONObject data = new JSONObject();
				JSONObject name = new JSONObject();
					name.put("value", "韩先生");
					name.put("color", "#173177");
				JSONObject market = new JSONObject();
					market.put("value", "光影农贸市场");
					market.put("color", "#173177");
				JSONObject title = new JSONObject();
					title.put("value", "室内装修咨询");
					title.put("color", "#173177");
				data.put("name", name);
				data.put("market", market);
				data.put("title", title);
		obj.put("data", data);

		//尊敬的{{name,DATA}}, 你的工程{{market,DATA}} 关于{{title,DATA}} 的咨询有了最新内容，请进行处理。
		String result = sendJsonMsg(url,obj);
		
		System.out.println(result);*/
		
/*		CommentTemplateMsg obj = new CommentTemplateMsg();
			obj.setTouser("ol6OBt-AaiMp7CrNHHfXgrypodbM");
			obj.setTemplate_id("y0KFD4AUVD_H5nZ__H9XUs13i09xqAvgkMX-KupPDzY");
			obj.setUrl("http://wytechhome.com/randomer-index/index.html");
			obj.setTopcolor("#FF0000");
			Map<String, SpecTemplateValue> data = new HashMap<>();
				SpecTemplateValue v1 = new SpecTemplateValue();
				SpecTemplateValue v2 = new SpecTemplateValue();
				SpecTemplateValue v3 = new SpecTemplateValue();
					v1.setValue("hello");
					v1.setColor("173177");
					v2.setValue("光影市场");
					v2.setColor("173177");
					v3.setValue("韩先生");
					v3.setColor("173177");
			data.put("title", v1);
			data.put("market", v2);
			data.put("name", v3);
			obj.setData(data);
		String s1 = JSONObject.toJSONString(obj,true);
		
		System.out.println(s1);
*/
		String accessToken = "V-iMdP7uInPjzCBDfXp9vPRUntmEPG9V_EC7BjjogZA0JfA_NCLVAoFG8WCWSosPmvjZCTdJ5rqICI1z1Ix3CBfUAflFhJfjrVFOMYHZoVAhfvf73OZqZzn8b2odF5JCEOTdACAOYD";
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken+"";
		String string = 
				"{\n" +
						"    \"touser\": \"ol6OBt-AaiMp7CrNHHfXgrypodbM\",\n" +
						"    \"template_id\": \"xfpkxlbPCIX5ipN-IYaZXr53E600uk4JIq3AB3Ix6_k\",\n" +
						"    \"url\": \"http://www.baidu.com\",\n" +
						"    \"topcolor\": \"#FF0000\",\n" +
						"    \"data\":{\n" +
						"        \"market\":{\n" +
						"        	\"value\": \"培龙市场\",\n" +
						"            \"color\": \"#173177\"\n" +
						"        },\n" +
						"        \"title\":{\n" +
						"    		 \"value\": \"这是一个标题\",\n" +
						"            \"color\": \"#173177\"\n" +
						"            \n" +
						"        },\n" +
						"        \"content\":{\n" +
						"        	\"value\": \"我需要你们的帮助啊啊啊啊\",\n" +
						"            \"color\": \"#173177\"\n" +
						"        }\n" +
						"    }\n" +
						"}"
				;
		System.out.println(sendJsonMsgWithJsonStr(url,string));
	}
}
