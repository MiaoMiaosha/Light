package com.light.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 系统相关设置
 * @ClassName: ConfigController 
 * @author TobyHan
 * @date 2017年3月8日 下午1:38:59 
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.common.ReturnResult;
import com.light.pojo.LgIntroduce;
import com.light.pojo.LgLoginUser;
import com.light.service.ConfigService;
import com.light.service.LoginService;
@Controller
public class ConfigController {
	@Autowired
	ConfigService ConfigService;
	@Autowired
	LoginService LoginService;
	
	/**
	 * 获取首页相关配置
	 * @Title: getIndexConfig 
	 * @author TobyHan
	 * Date : 2017年3月8日 下午2:16:55
	 */
	@RequestMapping("/config/index")
	@ResponseBody
	public Map getIndexConfig(){
		Map rsMap = null;
		try {
			rsMap = ConfigService.getIndexSetting();
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取招租图片
	 * @Title: getZhaozuConfig 
	 * @author TobyHan
	 * Date : 2017年3月8日 下午2:39:05
	 */
	@RequestMapping("/config/bus")
	@ResponseBody
	public Map getZhaozuConfig(){
		Map rsMap = null;
		try {
			rsMap = ConfigService.getBusPic();
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 设置首页相关配置
	 * @Title: setIndex 
	 * @author TobyHan
	 * Date : 2017年3月9日 下午5:51:27
	 */
	@RequestMapping("/admin/config/setindex")
	@ResponseBody
	public Map setIndex(@RequestParam(value="type",required =true)Integer type,
			@RequestParam(value="urlKey",required=true)String urlKey,String adLink){
		Map rsMap = null;
		try {
			rsMap = ConfigService.setIndexSetting(type, urlKey,adLink);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 设置首页小图
	 * @Title: setSmallPic 
	 * @author TobyHan
	 * Date : 2017年3月9日 下午5:53:17
	 */
	@RequestMapping("/admin/config/setsmallpic")
	@ResponseBody
	public Map setSmallPic(@RequestParam(value="num",required =true)Integer num,
			@RequestParam(value="urlKey",required=true)String urlKey){
		Map rsMap = null;
		try {
			rsMap = ConfigService.setSmallPic(num, urlKey);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 设置招租图片
	 * @Title: setBus 
	 * @author TobyHan
	 * Date : 2017年3月14日 下午6:50:05
	 */
	@RequestMapping("/admin/config/setbus")
	@ResponseBody
	public Map setBus(@RequestParam(value="num",required =true)Integer num,
			@RequestParam(value="urlKey",required=true)String urlKey){
		Map rsMap = null;
		try {
			rsMap = ConfigService.setZhaoZuPics(num, urlKey);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 设置置顶
	 * @Title: setTop 
	 * @author TobyHan
	 * Date : 2017年3月15日 下午8:23:11
	 */
	@RequestMapping("/admin/config/settop")
	@ResponseBody
	public Map setTop(@RequestParam(value="type",required =true)Integer type,
			@RequestParam(value="id",required=true)Integer id){
		Map rsMap = null;
		try {
			rsMap = ConfigService.setTop(type, id);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 设置公司简介
	 * @Title: setIntro 
	 * @author TobyHan
	 * Date : 2017年4月5日 上午10:55:41
	 */
	@RequestMapping("/admin/config/setintro")
	@ResponseBody
	public Map setIntro(LgIntroduce lgIntroduce){
		Map rsMap = null;
		try {
			rsMap = ConfigService.setIntro(lgIntroduce);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取简介列表
	 * @Title: getIntro 
	 * @author TobyHan
	 * Date : 2017年4月5日 上午10:55:52
	 */
	@RequestMapping("/config/introlist")
	@ResponseBody
	public Map getIntro(Integer id){
		Map rsMap = null;
		try {
			List list = ConfigService.getIntro(id);
			rsMap = ReturnResult.ok(list, (long) list.size(), 1, list.size());
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	
	
	
	/**
	 * 添加会计或管理员账号
	 * @return
	 */
	@RequestMapping("/admin/config/addacc")
	@ResponseBody
	public Map addAccount(LgLoginUser lgLoginUser){
		Map rsMap = null;
		try {
			rsMap = LoginService.addAccount(lgLoginUser);
			//rsMap = ConfigService.setTop(type, id);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 修改账号密码
	 * @return
	 */
	@RequestMapping("/admin/config/editacc")
	@ResponseBody
	public Map editAccount(LgLoginUser lgLoginUser){
		Map rsMap = null;
		try {
			rsMap = LoginService.editAccount(lgLoginUser);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 获取水印
	 * @Title: waterMarkt 
	 * @author TobyHan
	 * Date : 2017年4月7日 上午9:57:59
	 */
	@RequestMapping("/watermark")
	@ResponseBody
	public Map waterMarkt(){
		Map rsMap = null;
		try {
			rsMap = ReturnResult.ok(ConfigService.getConfigValueByName("watermark"));
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 设置水印
	 * @Title: waterMarktEdit 
	 * @author TobyHan
	 * Date : 2017年4月7日 上午10:37:35
	 */
	@RequestMapping("/watermark/edit")
	@ResponseBody
	public Map waterMarktEdit(@RequestParam(value="value",required=true)
	String value){
		Map rsMap = null;
		try {
			ConfigService.setConfigValueByName("watermark",value);
			rsMap = ReturnResult.ok();
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
}
