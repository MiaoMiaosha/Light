package com.light.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.light.common.FormatUtil;

public class LoginInterceptor implements HandlerInterceptor{  
	  
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
        String url = request.getRequestURI();  
        if(url.indexOf("validlogin")>=0 || url.indexOf("redirect")>=0 
        		|| url.indexOf("logout")>=0 || url.indexOf("validcode")>=0
        		|| url.indexOf("info") >= 0){  
            return true;  
        }  
        //获取Session  
        HttpSession session = request.getSession();  
        Integer userId = (Integer)session.getAttribute("userId");  
          
        if(userId != null){  
            return true;  
        }  
        //不符合条件的，输出错误信息
        else{ 
        	//request.getRequestDispatcher("/validlogin").forward(request, response);  
    		PrintWriter writer = response.getWriter();
    		String output = 
    				"{\"status\":{\"timestamp\":"+FormatUtil.timeStampInt()+",\"code\":500,"
    				+ "\"msg\":\"login error\"},\"data\":[]}";
    	    writer.print(output);
    		writer.flush();
    		writer.close();
        }
          
        return false;  
    }  
  
}  