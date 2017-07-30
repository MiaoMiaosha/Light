package com.light.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.light.common.FormatUtil;
import com.light.service.WechatService;

public class PCLoginInterceptor implements HandlerInterceptor {
	
	@Autowired WechatService wechatService;

	  /** 
     * Handler执行完成之后调用这个方法 
     */  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception exc)  
            throws Exception {  
          
    }  
  
    /** 
     * Handler执行之后，ModelAndView返回之前调用这个方法 
     */  
    public void postHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler, ModelAndView modelAndView) throws Exception {  
    }  
  
    /** 
     * Handler执行之前调用这个方法 
     */  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler) throws Exception {  
        //获取请求的URL  
       // String url = request.getRequestURI();  
        //获取Session
    	String uid = request.getParameter("uid");
    	
    	if(uid == null || "".equals(uid)){
    		outputInfo(response, "uid为空");
    		return false;
    	}
    	Map rsMap = wechatService.queryUidSta(uid);
    	Integer status = (Integer) rsMap.get("status");
    	if(status == 1){
    		return true;
    	}
    	else if (status == 0) {
			outputInfo(response, "未登录");
		}
    	else if (status == 2) {
			outputInfo(response, "登录已过期");
		}
    	
        return false;  
    }
    
    public static void outputInfo(HttpServletResponse response,String msg) throws Exception{
    	PrintWriter writer = response.getWriter();
		String output = 
				"{\"status\":{\"timestamp\":"+FormatUtil.timeStampInt()+",\"code\":500,"
				+ "\"msg\":\""+msg+"\"},\"data\":[]}";
	    writer.print(output);
		writer.flush();
		writer.close();
    }
}
