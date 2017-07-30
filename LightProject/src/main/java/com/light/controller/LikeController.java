package com.light.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.light.common.ReturnResult;
import com.light.pojo.LgLike;
import com.light.service.LikeService;
/**
 * 收藏
 * @ClassName: LikeController 
 * @author TobyHan
 * @date 2017年2月17日 下午4:27:07 
 *
 */
@Controller
public class LikeController {

	@Autowired LikeService likeService;
	
	/**
	 * 获取收藏列表
	 * @Title: getLikeList 
	 * @author TobyHan
	 * Date : 2017年2月17日 下午4:35:53
	 */
	@RequestMapping("/like/list")
	@ResponseBody
	Map getLikeList(LgLike lgLike,@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="30")Integer rows){
		PageInfo<?> pageInfo = null;
		try {
			 pageInfo = likeService.getLikeList(lgLike, page, rows);
		} catch (Exception e) {
			return ReturnResult.wrong(e.getMessage());
		}
		return ReturnResult.ok(pageInfo.getList(), pageInfo.getTotal(), page, pageInfo.getPageNum());
	}
	/**
	 * 加入收藏
	 * @Title: addToLike 
	 * @author TobyHan
	 * Date : 2017年2月17日 下午4:52:10
	 */
	@RequestMapping("/like/add")
	@ResponseBody
	Map addToLike(LgLike lgLike){
		Map rsMap = null;
		try {
			rsMap = likeService.addToLike(lgLike);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 判断是否已经收藏
	 * @Title: isLike 
	 * @author TobyHan
	 * Date : 2017年3月21日 上午2:12:46
	 */
	@RequestMapping("/like/islike")
	@ResponseBody
	Map isLike(LgLike lgLike){
		Map rsMap = null;
		try {
			rsMap = likeService.isLike(lgLike);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
	/**
	 * 删除收藏
	 * @Title: deleteLike 
	 * @author TobyHan
	 * Date : 2017年3月21日 上午2:13:35
	 */
	@RequestMapping("/like/delete")
	@ResponseBody
	Map deleteLike(@RequestParam(value="id",required=true)Integer id){
		Map rsMap = null;
		try {
			rsMap = likeService.deleteLike(id);
		} catch (Exception e) {
			rsMap = ReturnResult.wrong(e.getMessage());
		}
		return rsMap;
	}
}
