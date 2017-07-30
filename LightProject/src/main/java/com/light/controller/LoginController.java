package com.light.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.light.common.ConstantVal;
import com.light.common.DigitRandom;
import com.light.common.FormatUtil;
import com.light.common.MatrixToImageWriter;
import com.light.common.RandomCodeServlet;
import com.light.common.ReturnResult;
import com.light.common.UidMake;
import com.light.common.ValidateCode;
import com.light.pojo.LgLoginUser;
import com.light.service.ConfigService;
import com.light.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	@Autowired ConfigService configService;
	
	/**
	 * 默认登录页面
	 * @Title: validLogin 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午5:05:13
	 */
	@RequestMapping(value="/validlogin")  
    public String validLogin() throws Exception{        
        return "validlogin";   
    }
	@RequestMapping(value="/404")  
    public String index() throws Exception{        
        return "404";   
    }
	
	/**
	 * 提交用户名密码接口
	 * @Title: redirect 
	 * @author TobyHan
	 * Date : 2017年2月21日 上午5:20:56
	 */
    @RequestMapping(value="/redirect")
    @ResponseBody
    public Map redirect(HttpSession session,@RequestParam(value="username",required=true)String username,
    		@RequestParam(value="password",required=true)String password,
    		@RequestParam(value="inpcode",required=true)String inpcode,
    		//@RequestParam(value="roleId",required=true)Integer roleId,
			HttpServletResponse response) throws Exception{
	    response.setHeader("Access-Control-Allow-Origin",ConstantVal.DOMAIN);
    	Object codeObj = session.getAttribute("randomCode");
    	if(codeObj == null)   return ReturnResult.wrong(500, "验证码错误");
    	if(!codeObj.equals(inpcode.toUpperCase())){
   	      return ReturnResult.wrong(500, "验证码错误");
   	    }
	    
    	String sessionId = session.getId();
    	//Map<String,Object> result = loginService.validUserInfo(username, password,roleId,sessionId);
    	Map<String,Object> result = loginService.validUserInfoNew(username, password);

    	Map statusMap = (Map) result.get("status");
    	Integer mapCode = (Integer) statusMap.get("code");
    	Map dataMap = (Map) result.get("data");
    	if (mapCode==200) {
        	Integer userId = (Integer) dataMap.get("userId");
        	String nickname = (String) dataMap.get("nickname");
    		//在Session里保存信息  
            session.setAttribute("nickname", nickname);
            session.setAttribute("userId", userId);
            session.setAttribute("randomCode","");

            session.setMaxInactiveInterval(24*60*60);//有效时间24小时
	            Cookie userCookie = new Cookie("userId", String.valueOf(userId)); 
	            userCookie.setPath("/");
            response.addCookie(userCookie);
            return ReturnResult.ok(200, "登录成功", result.get("data"));  
		}
    	else{
    	  session.setAttribute("errorMesg", "登录失败");
          return result;
    	}
    }
    
    /** 
     * 退出系统 
     * @param session 
     *          Session 
     * @return 
     * @throws Exception 
     */  
    @RequestMapping(value="/logout")
    @ResponseBody
    public Map logout(HttpSession session,
			HttpServletResponse response,
			@RequestParam(value="type",required=true)Integer type,
			String openId) {
    	Map rsMap = null;
    	try {
    		rsMap = loginService.logOut(type, openId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
        //清除Session  
        session.invalidate();  
          
        return ReturnResult.ok("退出成功",null);  
    }  
    /**
     * 获取验证码
     * @Title: ajaxValidateCode 
     * @author TobyHan
     * Date : 2017年2月21日 上午5:26:26
     */
    @RequestMapping(value="/validcode")  
    public void ajaxValidateCode(HttpServletRequest request,HttpServletResponse  response) {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		//ValidateCode randomValidateCode = new ValidateCode();
		DigitRandom obj = new DigitRandom();
		try {
			//ValidateCode.code = randomValidateCode.getRandcode(request,response);
			obj.getImg(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    //测试专用
    @RequestMapping(value="/info") 
    @ResponseBody
    public Map info(HttpSession session) throws Exception{  
          
        return ReturnResult.ok("用户名为："+session.getAttribute("username"));  
    }
    /**
     * 账号密码登录后，获取openId处理
     * 每次用户登录，都需要获取其最新的openId,
     * 查找数据库，如果没有其openId,1.对于员工则进行更新。2.对于用户则进行添加映射表
     * https://open.weixin.qq.com/connect/oauth2/authorize?
		appid=wx6749be7c611a889c&redirect_uri=http%3a%2f%2fwytechhome.com%2fLightProject%2fwxlogin
		&response_type=code&scope=snsapi_userinfo&state=loginUserId#wechat_redirect
     * @param code
     * @param state = loginUserId 登录用户的id
     * @return
     */
    @RequestMapping(value="/wxlogin") 
    String  getWxLoginInfo(String code,String state) {
    	String openId = "";
    	String err = "";
    	try {
             openId = loginService.getLoginUserOpenId(code, state);
		} catch (Exception e) {
			err = e.getMessage();
		}
    	String indexDir = configService.getIndexDir();
    	String finalUrl = "";
    	if(indexDir == null || indexDir.length() < 1)
    		finalUrl = "/404";
    	else
    		finalUrl = "http://"+indexDir+"/GYAdmin/html/perInfo/index.html?openId="+openId+"&err="+err;
		//return "redirect:http://wytechhome.com/wechatTest/GYAdmin/html/perInfo/index.html?openId="+openId+"&err="+err;
		return "redirect:"+finalUrl;

    }
    /**
     * 获取登录账号信息
     * @Title: getLoginUserList 
     * @author TobyHan
     * Date : 2017年3月16日 上午8:39:57
     */
    @RequestMapping(value="/admin/login/userlist") 
    @ResponseBody
    Map getLoginUserList(@RequestParam(value="page",defaultValue="1")Integer page,
    		@RequestParam(value="rows",defaultValue="30")Integer rows,
    		LgLoginUser lgLoginUser) {
    		PageInfo result = loginService.getLoginUserList(lgLoginUser, page, rows);
    		List list = result.getList();
    	return ReturnResult.ok(list, result.getTotal(), result.getPages(),result.getPageNum());
    }
    /**
     * 获取用户详细信息
     * @param type
     * @param openId
     * @return
     */
    @RequestMapping(value="/admin/login/userdetail") 
    @ResponseBody
    Map getLoginUserDetail(Integer type,String openId) {
    	Map rsMap = null;
    	try {
			rsMap = loginService.getAccountDetailByOpenId(type, openId);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
    	return rsMap;
    }
    
       
}
