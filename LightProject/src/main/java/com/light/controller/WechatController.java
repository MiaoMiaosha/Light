package com.light.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.light.common.MatrixToImageWriter;
import com.light.common.ReturnResult;
import com.light.common.Sign;
import com.light.common.UidMake;
import com.light.common.WebAccessTokenReturn;
import com.light.mapper.custom.LgPublicMapper;
import com.light.service.ConfigService;
import com.light.service.WechatService;
/**
 * 微信
 * @ClassName: WechatController 
 * @author TobyHan
 * @date 2017年2月17日 上午6:27:21 
 *
 */
@Controller
public class WechatController {
	
	/**
	 * scope为snsapi_userinfo 
		https://open.weixin.qq.com/connect/oauth2/authorize?
		appid=wx6749be7c611a889c&redirect_uri=http%3a%2f%2fwytechhome.com%2fLightProject%2fresponse
		&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
	 */
     @Autowired
     WechatService WechatService;
     @Autowired
     ConfigService configService;
	/**
	 * redirect地址，接受code,存储用户信息
	 * 应自动跳转到首页
	 * @Title: responseForCode 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午6:29:51
	 */
	@RequestMapping("/response")
	String responseForCode(String code,String state){
		WebAccessTokenReturn returnObj = WechatService.getAccessTokenByCode(code);
		Integer userId = WechatService.getUserInfoAndSave(returnObj);
		String indexDir = configService.getIndexDir();
		String finalUrl = "";
		if(state != null && !state.equals("")){
			 if (state.equals("index")) {
				//前台首页
				if(indexDir == null || indexDir.length() < 1)
		    		finalUrl = "/404";
		    	else
		    		finalUrl = "http://"+indexDir+"/html/index.html?userId="+userId;
				return "redirect:"+ finalUrl;
			}
			else if (state.equals("amanage")) {
				//申请管理
				if(indexDir == null || indexDir.length() < 1)
		    		finalUrl = "/404";
		    	else
					finalUrl = "http://"+indexDir+"/html/managerparterner.html?userId="+userId;
				return "redirect:"+ finalUrl;
			}
			else if (state.equals("abus")) {
				//申请电商
				if(indexDir == null || indexDir.length() < 1)
		    		finalUrl = "/404";
		    	else
					finalUrl ="http://"+indexDir+"/html/findpaterner.html?userId="+userId;
				return "redirect:"+ finalUrl;
			}
			else{
				String uid = state.split("@")[1];
				//pc登录
				if(uid != null && !"".equals(uid)){
					if(indexDir == null || indexDir.length() < 1)
			    		finalUrl = "/404";
			    	else
						finalUrl = "http://"+indexDir+"/html/loginsuccess.html?errcode="+0;
					try {
						WechatService.updateLoginUid(uid, userId);
					} catch (Exception e) {
						//此处应该跳转到登录失败到页面
			    		finalUrl = "http://"+indexDir+"/html/loginfail.html?errcode="+1;
			    		//findUrl =  indexDir+"/html/loginfail.html?errmsg="+e.getMessage();
					}
				}
				
				return "redirect:"+finalUrl;
			}
			
		}
		
		
    	if(indexDir == null || indexDir.length() < 1)
    		finalUrl = "/404";
    	else
    		finalUrl = "http://"+indexDir+"/html/index.html?userId="+userId;
		//return "redirect:http://wytechhome.com/wechatTest/html/index.html?userId="+userId;
		return "redirect:"+ finalUrl;
	}
	/**
	 * 获取sign
	 * @Title: returnSignMsg 
	 * @author TobyHan
	 * Date : 2017年3月24日 下午6:13:35
	 */
	@RequestMapping("/sign")
	@ResponseBody
	Map returnSignMsg(String url){
		String jsapi_ticket = WechatService.getEffectiveJsapiTicket();
		String appid = configService.getAppid();
		Map map = Sign.sign(jsapi_ticket, url);
		map.put("appid", appid);
		return ReturnResult.ok(map);
	}
	
    /**
     * 获取登录二维码
     * @Title: ajaxQRCode 
     * @author TobyHan
     * Date : 2017年4月5日 下午2:12:34
     */
    @RequestMapping(value="/qrcode")  
    public void ajaxQRCode(String uid,HttpServletRequest request,HttpServletResponse  response) {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		try {
	    	String domainUrl = configService.getDomain();
			String content = "https://open.weixin.qq.com/connect/oauth2/authorize?"
					+ "appid=wx3d36f25cad23c1bf&redirect_uri=http%3a%2f%2f"+domainUrl+"%2fLightProject%2fresponse&response_type=code&"
					+ "scope=snsapi_userinfo&state=pcindex@"+uid+"#wechat_redirect "; 
	        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			 Map hints = new HashMap();  
		        //内容所使用编码  
		        hints.put(EncodeHintType.CHARACTER_SET, "gb2312");  
		        BitMatrix bitMatrix = multiFormatWriter.encode(content,BarcodeFormat.QR_CODE, 200, 200, hints);  
			MatrixToImageWriter.writeToStream(bitMatrix, "jpg", response.getOutputStream());
			//生成uid，开启定时请求任务
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * 判断登录状态
     * @Title pcLoginValid
     * @author TobyHan
     * Date : 2017年5月7日 下午2:48:34
     * @param session
     * @return
     */
    @RequestMapping(value="/pclogin") 
    @ResponseBody
    Map pcLoginValid(@RequestParam(value="uid",required=true)String uid) {
    	Map rsMap = null;
    	try {
    		rsMap = ReturnResult.ok(WechatService.queryUidSta(uid));
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
    	return rsMap;
    }
    /**
     * 获取pc的uid
     * @Title getNewUid
     * @author TobyHan
     * Date : 2017年5月7日 下午3:17:17
     * @return
     */
    @RequestMapping(value="/pcuid") 
    @ResponseBody
    Map getNewUid() {
    	Map rsMap = null;
    	try {
    		rsMap = ReturnResult.ok(WechatService.addUid());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
    	return rsMap;
    }


	
	@RequestMapping("/download11")
    public ModelAndView download(HttpServletRequest request,HttpServletResponse response){

        
        String fileName="MP_verify_4w5NtCIZPkijkpMO.txt";
        
        response.reset();// 不加这一句的话会出现下载错误 
        response.setHeader("Content-disposition", "attachment; filename="+fileName);// 设定输出文件头   
        response.setContentType("text/x-plain");// 定义输出类型 
        
        try {
            ServletOutputStream out = response.getOutputStream();
            
            //String path = System.getProperty("java.io.tmpdir") + "\\poem.txt";
            String path = this.getClass().getClassLoader().getResource("/").getPath()+
            		"MP_verify_4w5NtCIZPkijkpMO.txt";
            File file = new File(path);
            FileOutputStream fos = new FileOutputStream(file);   
            Writer writer = new OutputStreamWriter(fos, "utf-8");   
            
            String text="Hello！download!";
            writer.write(text);   
            writer.close();   
            fos.close();  
            
            FileInputStream fis = new java.io.FileInputStream(file);
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(4096);
            
            byte[] cache = new byte[4096];
            for (int offset = fis.read(cache); offset != -1; offset = fis.read(cache)) {
                    byteOutputStream.write(cache, 0, offset);
            }
            
            byte[] bt = null;
            bt = byteOutputStream.toByteArray();               
            
            out.write(bt);
            out.flush();
            out.close();
            fis.close();
            if(file.exists()){
                file.delete();
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
	@RequestMapping("MP_verify_4w5NtCIZPkijkpMO.txt")  
	public void download(HttpServletResponse res) throws IOException {  
	    OutputStream os = res.getOutputStream();  
	    try {  
	        res.reset();  
	        res.setHeader("Content-Disposition", "attachment; filename=MP_verify_4w5NtCIZPkijkpMO.txt");  
	        res.setContentType("application/octet-stream; charset=utf-8");  
	        
	        String s = "4w5NtCIZPkijkpMO";
            int len = s.length();
            for (int i = 0 ; i < len ; i++) {
                os.write((byte)s.charAt(i));
           }
	        os.flush();  
	    } finally {  
	        if (os != null) {  
	            os.close();  
	        }  
	    }  
	}
	
}
