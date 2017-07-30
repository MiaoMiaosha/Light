package com.light.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.ConstantVal;
import com.light.common.HttpUtil;
import com.light.common.ReturnResult;
import com.light.dto.StaffAndCusInfoBean;
import com.light.dto.WXIdAndSecret;
import com.light.exception.ServiceException;
import com.light.mapper.LgEmployeeMapper;
import com.light.mapper.LgLoginCuslistMapper;
import com.light.mapper.LgLoginUserMapper;
import com.light.mapper.LgUidMapper;
import com.light.pojo.LgEmployee;
import com.light.pojo.LgLoginCuslist;
import com.light.pojo.LgLoginCuslistExample;
import com.light.pojo.LgLoginUser;
import com.light.pojo.LgLoginUserExample;
import com.light.pojo.LgUid;
import com.light.pojo.LgUidExample;
import com.light.pojo.LgLoginUserExample.Criteria;
import com.light.service.ConfigService;
import com.light.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired LgLoginUserMapper LgLoginUserMapper;
	@Autowired LgLoginCuslistMapper LgLoginCuslistMapper;
	@Autowired LgEmployeeMapper LgEmployeeMapper;
	@Autowired ConfigService configService;
	@Autowired LgUidMapper LgUidMapper;
 	
	
	@Override
	public Map validUserInfo(String username, String password,
			Integer roleId,String sessionId) {
		//String finalPaw = com.light.common.EncryptUtil.toMD5(password, 32);
		LgLoginUserExample example = new LgLoginUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		List<LgLoginUser> list = LgLoginUserMapper.selectByExample(example);
		if(list != null && list.size() == 1){
			LgLoginUser user = list.get(0);
			Integer userId = user.getId();
			String nickname = user.getNickName();
			Integer isLocked = user.getIsLocked();
			if(isLocked != 0)
				return ReturnResult.wrong("用户已冻结");
			
			String getRoleIds = user.getRoleIds();
			String[] roleArr = getRoleIds.split(":");
			if(roleArr == null){
				return ReturnResult.wrong("用户角色不存在");
			}
			
			Map rsMap = new HashMap<>();
			boolean flag =false;
			for(String element : roleArr){
				if(roleId == Integer.valueOf(element)){
					 rsMap.put("roleId", roleId);
					 flag =true;
				}
			}
			if(!flag)
				return ReturnResult.wrong("用户权限错误");
			if(roleId == 2 || roleId == 3){
				Integer employeeId = user.getUserTypeId();
				rsMap.put("employeeId", employeeId);
				
				LgEmployee lgEmployee = LgEmployeeMapper.selectByPrimaryKey(employeeId);
				if(lgEmployee != null){
					rsMap.put("job", lgEmployee.getJob());//职称，1-设计师，2-合同人，3-会计
				}

				
			}
			if(roleId == 4){
				Integer customerId = user.getUserTypeId();
				rsMap.put("customerId", customerId);
			}
			
			
			rsMap.put("userId", userId);
			rsMap.put("nickname", nickname);
			//rsMap.put("JSESSION", sessionId);
			return ReturnResult.ok(rsMap);
		}
		
		return ReturnResult.wrong("找不到用户");
	}
	//获取用户openId
	@Override
	public String getLoginUserOpenId(String code, String state) throws ServiceException {
		//根据code获取网页授权access_token，同时获得open ID
		WXIdAndSecret wxObj = configService.getIdAndSecret();
		String url = 
	"https://api.weixin.qq.com/sns/oauth2/access_token?"
	+ "appid="+wxObj.getAppid()+"&secret="+wxObj.getAppsecret()+"&code="+code+"&grant_type=authorization_code";
		String msg = HttpUtil.sendBasicParamUrlHttp(url,"GET");
		JSONObject jsonObject = JSONObject.parseObject(msg);
		if(jsonObject.getInteger("errcode") != null){
			//返回接口错误信息
			throw new ServiceException(jsonObject.getString("errmsg"));
		}else{
			//获取accessToken接口，只有 access_token,expires_in,refresh_token,openid,scope
			String openId = jsonObject.getString("openid");
			String accessToken = jsonObject.getString("access_token");
			
			//通过accessToken拉取用户信息
			String getUserInfoUrl = 
					"https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";	
			String userMsg = HttpUtil.sendBasicParamUrlHttp(getUserInfoUrl, "GET");
				JSONObject userJsonObject = JSONObject.parseObject(userMsg);

			if(userJsonObject.getInteger("errcode") != null){
				throw new ServiceException(userJsonObject.getString("errmsg"));
			}
			else{
				String nickname = userJsonObject.getString("nickname");
				String headimgurl = userJsonObject.getString("headimgurl");
				
				Integer loginUserId = Integer.valueOf(state);
				LgLoginUser loginUser = LgLoginUserMapper.selectByPrimaryKey(loginUserId);
				String markisnull = "loginUserIsNull";
				if(loginUser != null){
				//1.如果是员工
					markisnull = "loginUserNotNull";
					if("3".equals(loginUser.getRoleIds())){
						markisnull = "roleIdIs3";
						String gettingOpenId = loginUser.getOpenId();
						if(gettingOpenId == null || gettingOpenId.equals("") || !openId.equals(gettingOpenId)){
							//openId不一样则更新
							LgLoginUser upUser = new LgLoginUser();
								upUser.setId(loginUserId);
								upUser.setOpenId(openId);
								upUser.setNickName(nickname);
								upUser.setHeadImg(headimgurl);
							if(LgLoginUserMapper.updateByPrimaryKeySelective(upUser) < 1)
								throw new ServiceException("更新失败");
						}
					}

				//2.如果是客户
					if("4".equals(loginUser.getRoleIds())){
						LgLoginCuslistExample example = new LgLoginCuslistExample();
							example.createCriteria().andOpenIdEqualTo(openId).andLoginUserIdEqualTo(loginUserId);
						List list = LgLoginCuslistMapper.selectByExample(example);
						if(list == null || list.size() < 1){
							LgLoginCuslist insertCus = new LgLoginCuslist();
								insertCus.setLoginUserId(loginUserId);
								insertCus.setOpenId(openId);
								insertCus.setHeadimgurl(headimgurl);
								insertCus.setNickname(nickname);
	//TODO							//这里应该再设置一下用户名，和头像
								
							if(LgLoginCuslistMapper.insertSelective(insertCus)<1)
								throw new ServiceException("插入新客户映射失败");
						}
					}

				}
				
				
			}//拉取用户信息成功
			
			
			return openId;
		}
		//根据State 即用户id，判定用户身份
	}
	//退出登录
	@Override
	public Map logOut(Integer type, String openId) throws ServiceException {
		if(type == null || type > 2)
			throw new ServiceException("注销类型不正确");
		if(type == 1){
			//员工注销
			LgLoginUserExample example = new LgLoginUserExample();
			 example.createCriteria().andOpenIdEqualTo(openId);
			 LgLoginUser user = new LgLoginUser();
			 user.setOpenId("");
			if(LgLoginUserMapper.updateByExampleSelective(user, example) < 1)
				throw new ServiceException("该员工未登录");
		}
		else if (type == 2) {
			//客户注销
			LgLoginCuslistExample example = new LgLoginCuslistExample();
				example.createCriteria().andOpenIdEqualTo(openId);
			if(LgLoginCuslistMapper.deleteByExample(example) < 1)
				throw new ServiceException("该客户未登录");
		}
			
		return ReturnResult.ok();
	}
	//添加会计或管理员账号
	@Override
	public Map addAccount(LgLoginUser lgLoginUser) {
		String roleIds = lgLoginUser.getRoleIds();
		if(lgLoginUser.getUsername() == null || lgLoginUser.getPassword() == null ||
				roleIds == null)
			throw new ServiceException("请传入正确的账号信息");
		//验证账号是否已存在
		LgLoginUserExample validExa = new LgLoginUserExample();
			validExa.createCriteria().andUsernameEqualTo(lgLoginUser.getUsername());
		List validList = LgLoginUserMapper.selectByExample(validExa);
		if(validList != null && validList.size() > 0)
			throw new ServiceException("账号已存在，请更换账号名");
		
		if (roleIds.equals("1")||roleIds.equals("2")) {
			/**
			 * 插入时需要字段
			 * （必填）username password roleIds 
			 * （可选）nickName userTypeId=0
			 */
			lgLoginUser.setIsLocked(0);
			lgLoginUser.setUserType(1);
			if(lgLoginUser.getNickName()==null)
				lgLoginUser.setNickName("未命名");
			
			if(LgLoginUserMapper.insertSelective(lgLoginUser) < 1)
				throw new ServiceException("插入失败");
		}
			
		return ReturnResult.ok();
	}
	//编辑账号
	@Override
	public Map editAccount(LgLoginUser lgLoginUser) throws ServiceException {
		Integer id = lgLoginUser.getId();
		if(CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		/*if(CheckNullUtil.isNotEmpty(lgLoginUser.getRoleIds()))
			throw new ServiceException("用户角色不允许更改");*/
		if(LgLoginUserMapper.updateByPrimaryKeySelective(lgLoginUser) < 1)
			throw new ServiceException("更新失败");
		return ReturnResult.ok();
	}
	//获取账号列表
	@Override
	public PageInfo<?> getLoginUserList(LgLoginUser lgLoginUser, Integer page, Integer rows) {
		Integer id = lgLoginUser.getId();
		String username = lgLoginUser.getUsername();
		String roleIds = lgLoginUser.getRoleIds();
		Integer isLocked = lgLoginUser.getIsLocked();
		String nickName = lgLoginUser.getNickName();
		
		LgLoginUserExample example = new LgLoginUserExample();
			Criteria criteria = example.createCriteria(); 
		if(CheckNullUtil.integerNotNull(id))
			criteria.andIdEqualTo(id);
		if(CheckNullUtil.isNotEmpty(username))
			criteria.andUsernameLike("%"+username+"%");
		if(CheckNullUtil.isNotEmpty(roleIds))
			criteria.andRoleIdsEqualTo(roleIds);
		if(CheckNullUtil.integerNotNull(isLocked))
			criteria.andIsLockedEqualTo(isLocked);
		if(CheckNullUtil.isNotEmpty(nickName))
			criteria.andNickNameLike("%"+nickName+"%");
		example.setOrderByClause("id desc");
		PageHelper.startPage(page, rows);
		List<LgLoginUser> list = LgLoginUserMapper.selectByExample(example);
		/*for(LgLoginUser element : list){
			element.setPassword("");
		}*/
		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	//根据type和openId获取用户信息
	@Override
	public Map getAccountDetailByOpenId(Integer type, String openId) throws ServiceException {
		/*if(CheckNullUtil.integerNull(type) || CheckNullUtil.isEmpty(openId))
			throw new ServiceException("参数错误");*/
		if(CheckNullUtil.integerNull(type))
			throw new ServiceException("登录类型为空");
		if(CheckNullUtil.isEmpty(openId))
			throw new ServiceException("获取微信信息出错");
		
		StaffAndCusInfoBean out = null;
		if(type == 1){
			//员工
			LgLoginUserExample example =  new LgLoginUserExample();
			example.createCriteria().andRoleIdsEqualTo("3").andOpenIdEqualTo(openId);
			List<LgLoginUser> list = LgLoginUserMapper.selectByExample(example);
			if(CheckNullUtil.listNotNull(list)){
				LgLoginUser user = list.get(0);
				 out = new StaffAndCusInfoBean(user);
			}
		}
		else if(type ==2){
			//客户
			LgLoginCuslistExample example = new LgLoginCuslistExample();
			example.createCriteria().andOpenIdEqualTo(openId);
			List<LgLoginCuslist> list = LgLoginCuslistMapper.selectByExample(example);
			if(CheckNullUtil.listNotNull(list)){
				LgLoginCuslist user = list.get(0);
				 out = new StaffAndCusInfoBean(user);
			}
		}else {
			throw new ServiceException("登录类型错误");
		}
		return out==null?ReturnResult.wrong("无相关对象"):ReturnResult.ok(out);
	}
	@Override
	public Map validUserInfoNew(String username, String password) throws ServiceException {
		//String finalPaw = com.light.common.EncryptUtil.toMD5(password, 32);
				LgLoginUserExample example = new LgLoginUserExample();
				Criteria criteria = example.createCriteria();
				criteria.andUsernameEqualTo(username);
				criteria.andPasswordEqualTo(password);
				List<LgLoginUser> list = LgLoginUserMapper.selectByExample(example);
				if(list != null && list.size() == 1){
					LgLoginUser user = list.get(0);
					Integer userId = user.getId();
					String nickname = user.getNickName();
					Integer isLocked = user.getIsLocked();
					if(isLocked != 0)
						return ReturnResult.wrong("用户已冻结");
					
					String getRoleIds = user.getRoleIds();
					String[] roleArr = getRoleIds.split(":");
					if(roleArr == null){
						return ReturnResult.wrong("用户角色不存在");
					}
					
					Map rsMap = new HashMap<>();
					/*boolean flag =false;
					for(String element : roleArr){
						if(roleId == Integer.valueOf(element)){
							 rsMap.put("roleId", roleId);
							 flag =true;
						}
					}
					if(!flag)
						return ReturnResult.wrong("用户权限错误");*/
					Integer roleId = Integer.valueOf(roleArr[0]);
					 rsMap.put("roleId", roleId);

					if(roleId == 2 || roleId == 3){
						Integer employeeId = user.getUserTypeId();
						rsMap.put("employeeId", employeeId);
						
						LgEmployee lgEmployee = LgEmployeeMapper.selectByPrimaryKey(employeeId);
						if(lgEmployee != null){
							rsMap.put("job", lgEmployee.getJob());//职称，1-设计师，2-合同人，3-会计
						}

						
					}
					if(roleId == 4){
						Integer customerId = user.getUserTypeId();
						rsMap.put("customerId", customerId);
					}
					
					
					rsMap.put("userId", userId);
					rsMap.put("nickname", nickname);
					//rsMap.put("JSESSION", sessionId);
					return ReturnResult.ok(rsMap);
				}
				
				return ReturnResult.wrong("用户账号密码错误");
		
		
	}
/*	//pc登录长轮询
	@Override
	public Map getPcLoginStatus(String uid) throws ServiceException {

		if(CheckNullUtil.isEmpty(uid))
			throw new ServiceException("uid为空");
		LgUidExample lgUidExample = new LgUidExample();
			lgUidExample.createCriteria().andUidEqualTo(uid);
		List<LgUid> list = LgUidMapper.selectByExample(lgUidExample);
		if(!CheckNullUtil.listNotNull(list))
			throw new ServiceException("list无记录");
		LgUid getLgUid = list.get(0);
		Integer getStatus = getLgUid.getStatus();
		if(getStatus != null){
			if(getStatus == 1)
			return ReturnResult.ok(getLgUid.getUserId());
		}
		else{
			return null;
		}
	}*/

}
