package com.light.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.common.ReturnResult;
import com.light.service.UploadPicService;
/**
 * 七牛图片上传相关
 * @ClassName: UploadPicController 
 * @author TobyHan
 * @date 2017年2月16日 下午8:33:46 
 *
 */
@Controller
public class UploadPicController {

	@Autowired UploadPicService uploadPicService;
	
	/**
	 * 从数据库获取token
	 * @Title: getUploadToken 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午8:54:14
	 */
	@RequestMapping("/upload/token")
	@ResponseBody
	Map getUploadToken(HttpServletResponse response){
		Map rsMap = null;
		try {
			rsMap = uploadPicService.getUploadToken();
			response.setHeader("Access-Control-Allow-Origin", "*");
		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		return rsMap;

	}
	/**
	 * 获取新的token
	 * @Title: getNewUploadToken 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午8:55:07
	 */
	@RequestMapping("/upload/newtoken")
	@ResponseBody
	Map getNewUploadToken(){
		Map rsMap = null;
		try {
			rsMap = uploadPicService.getNewUploadToken();
		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除空间中的文件
	 * @Title: deleteKey 
	 * @author TobyHan
	 * Date : 2017年3月18日 上午11:00:51
	 */
	@RequestMapping("/upload/delete")
	@ResponseBody
	Map deleteKey(@RequestParam(value="key",required=true)String key){
		//Map rsMap = null;
			return uploadPicService.deleteFileByKey(key);
		
	}
}
