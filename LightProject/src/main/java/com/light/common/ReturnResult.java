package com.light.common;

import java.util.HashMap;
import java.util.Map;


public class ReturnResult {

	public static final String StatusDefaultValue = "success";
	public static final int StatusDefaultCode = 200;
	public static final String WrongDefaultValue = "fail";
	public static final int WrongDefaultCode = 500;
	
	public static Map<String, Object> build(Integer statusCode,String msg,Object contentObj,Long total,Integer currentPage,Integer pageNum){
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> sontMap = new HashMap<>();
		sontMap.put("code",statusCode);
		sontMap.put("msg",msg);
		sontMap.put("timestamp",FormatUtil.timeStampInt());
		if(total != null && currentPage != null && pageNum != null){
			sontMap.put("curPage", currentPage);
			sontMap.put("total", total);
			sontMap.put("pageNum", pageNum);
		}

		resultMap.put("status", sontMap);
		if(contentObj != null)
		resultMap.put("data", contentObj);
		return resultMap;
	}
	
	public static Map<String, Object> ok(){
		return ReturnResult.build(StatusDefaultCode, StatusDefaultValue, null, null, null,null);
	}
	public static Map<String, Object> ok(Object data){
		return ReturnResult.build(StatusDefaultCode, StatusDefaultValue, data, null, null,null);
	}
	public static Map<String, Object> ok(String msg,Object data){
		return ReturnResult.build(StatusDefaultCode,msg,data,null,null,null);
	}
	public static Map<String, Object> ok(Integer code,String msg,Object data){
		return ReturnResult.build(code,msg,data,null,null,null);
	}
	public static Map<String, Object> ok(Object data,Long total,Integer curPage,Integer pageNum){
		return ReturnResult.build(StatusDefaultCode, StatusDefaultValue, data, total, curPage,pageNum);
	}
	public static Map<String, Object> wrong(){
		return ReturnResult.build(WrongDefaultCode,WrongDefaultValue,null,null,null,null);
	}
	public static Map<String, Object> wrong(String msg){
		return ReturnResult.build(WrongDefaultCode,msg,null,null,null,null);
	}
	public static Map<String, Object> wrong(Integer code,String msg){
		return ReturnResult.build(code,msg,null,null,null,null);
	}
	public static Map<String, Object> wrong(Integer code,String msg,Object data){
		return ReturnResult.build(code,msg,data,null,null,null);
	}
	
	
}
