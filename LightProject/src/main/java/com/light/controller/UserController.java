package com.light.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.light.common.ReturnResult;
import com.light.pojo.LgUser;
import com.light.service.UserService;
/**
 * 获取微信个人用户信息
 * @ClassName: UserController 
 * @author TobyHan
 * @date 2017年2月17日 上午8:15:37 
 *
 */
@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	/**
	 * 根据用户id获取信息
	 * @Title: getUserInfoById 
	 * @author TobyHan
	 * Date : 2017年2月17日 上午8:28:43
	 */
	@RequestMapping("/wxuser/detail")
	@ResponseBody
	Map getUserInfoById(@RequestParam(value="userId",required=true)Integer userId){
		LgUser lgUser = userService.getUserInfoById(userId);
		if(lgUser != null)
			return ReturnResult.ok(lgUser);
		return ReturnResult.wrong("用户不存在");
	}
	
}
