package com.light.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.FormatUtil;
import com.light.common.ReturnResult;
import com.light.dto.CommentSqlBean;
import com.light.dto.LastAtOutputbean;
import com.light.exception.ServiceException;
import com.light.mapper.LgLoginCuslistMapper;
import com.light.mapper.LgLoginUserMapper;
import com.light.mapper.LgPostCommentMapper;
import com.light.mapper.LgPostEmployeeMapper;
import com.light.mapper.LgPostMapper;
import com.light.mapper.custom.LgLoginUserCustomMapper;
import com.light.mapper.custom.LgPostCustomMapper;
import com.light.mapper.custom.LgProjectCustomMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgLoginCuslist;
import com.light.pojo.LgLoginCuslistExample;
import com.light.pojo.LgLoginUser;
import com.light.pojo.LgLoginUserExample;
import com.light.pojo.LgPost;
import com.light.pojo.LgPostComment;
import com.light.pojo.LgPostCommentExample;
import com.light.pojo.LgPostEmployee;
import com.light.pojo.LgPostExample;
import com.light.pojo.LgPostExample.Criteria;
import com.light.service.ConfigService;
import com.light.service.PostService;
import com.light.service.WechatService;
/**
 * 帖子  以及 评论相关
 * @ClassName: PostServiceImpl 
 * @author TobyHan
 * @date 2017年3月4日 上午10:19:09 
 *
 */
@Service
public class PostServiceImpl implements PostService {
	
	@Autowired LgPostMapper LgPostMapper;
	@Autowired LgPostCustomMapper LgPostCustomMapper;
	@Autowired LgPostCommentMapper LgPostCommentMapper;
	@Autowired LgLoginUserCustomMapper LgLoginUserCustomMapper;
	@Autowired LgProjectCustomMapper LgProjectCustomMapper;
	@Autowired LgPublicMapper LgPublicMapper;
	@Autowired LgPostEmployeeMapper LgPostEmployeeMapper;
	@Autowired LgLoginUserMapper LgLoginUserMapper;
	@Autowired WechatService WechatService;
	@Autowired LgLoginCuslistMapper LgLoginCuslistMapper;
	
	
	@Autowired ConfigService configService;
	//获取帖子列表
	@Override
	public PageInfo<?> getPostList(LgPost lgPost, Integer page, Integer rows) throws ServiceException {
		Integer pid = lgPost.getPid();
		String nickName = lgPost.getNickName();
		//获取工程相关帖子，必输字段
		Integer projectId = lgPost.getProjectId();
		
		LgPostExample example  = new LgPostExample();
		Criteria criteria = example.createCriteria();
		if(CheckNullUtil.integerNotNull(projectId))
			criteria.andProjectIdEqualTo(projectId);
		if(CheckNullUtil.integerNotNull(pid))
			criteria.andPidEqualTo(pid);
		if(CheckNullUtil.isNotEmpty(nickName))
			criteria.andNickNameLike("%"+nickName+"%");
		criteria.andStatusNotEqualTo(1);//1为标记删除
		example.setOrderByClause("create_time desc");//根据最新发布排序
		PageHelper.startPage(page, rows);
		List<LgPost> postList = LgPostMapper.selectByExample(example);
		List<Map> mapList = new ArrayList<>();
		if(CheckNullUtil.listNotNull(postList)){
			for(LgPost element : postList){
				Map elementMap = new HashMap<>();
				Integer count = LgPostCustomMapper.getCountCommemtWithPostId(element.getPid());
				//String headImg = LgLoginUserCustomMapper.getHeadUrlByLoginId(element.getLoginUserId());
				String headImg = "";
				//String headImg = LgLoginUserCustomMapper.getHeadUrlByLoginId(element.getLoginUserId());
				LgLoginUser lgLoginUser = LgLoginUserMapper.selectByPrimaryKey(element.getLoginUserId());
				if(lgLoginUser != null){
					String roleIds = lgLoginUser.getRoleIds();
					if(roleIds.equals("3")){
						//员工
						headImg = lgLoginUser.getHeadImg();
					}
					else if(roleIds.equals("4")){
						//客户
						String openId = element.getOpenId();
						LgLoginCuslistExample cuslistExample = new LgLoginCuslistExample();
							cuslistExample.createCriteria().andOpenIdEqualTo(openId);
						List<LgLoginCuslist> getCList = LgLoginCuslistMapper.selectByExample(cuslistExample);
						if(CheckNullUtil.listNotNull(getCList))
							headImg = getCList.get(0).getHeadimgurl();
					}
				}
				
				elementMap.put("element", element);
				elementMap.put("commentCount", count);
				elementMap.put("userImg", headImg);
				mapList.add(elementMap);
			}
		}
		
		PageInfo<?> pageInfo =  new PageInfo<>(mapList);
		return pageInfo;
	}
	//发布图面
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map addPost(LgPost lgPost,String employeeIds) throws ServiceException {
		Integer loginId = lgPost.getLoginUserId();
		String openId = lgPost.getOpenId();
		if(CheckNullUtil.integerNull(loginId))
			throw new ServiceException("登录用户为空");
		if(CheckNullUtil.isEmpty(openId))
			throw new ServiceException("openId为空");
		
		String nickName = "未知";
		LgLoginUser lgLoginUser = LgLoginUserMapper.selectByPrimaryKey(loginId);
		if(lgLoginUser != null){
			String roleIds = lgLoginUser.getRoleIds();
			if(roleIds.equals("3")){
				//员工
				nickName = lgLoginUser.getNickName();
			}
			else if(roleIds.equals("4")){
				//客户
				LgLoginCuslistExample cuslistExample = new LgLoginCuslistExample();
					cuslistExample.createCriteria().andOpenIdEqualTo(openId);
				List<LgLoginCuslist> getCList = LgLoginCuslistMapper.selectByExample(cuslistExample);
				if(CheckNullUtil.listNotNull(getCList))
					nickName = getCList.get(0).getNickname();
			}
		}
		
		lgPost.setCreateTime(FormatUtil.timeStampInt());
		lgPost.setStatus(0);//0-正常，1-删除
		lgPost.setNickName(nickName);
		
		int inseSta = LgPostMapper.insertSelective(lgPost);
		if(inseSta < 1)
			throw new ServiceException("发布图面失败");
		Integer newPostId = LgPublicMapper.selectLastInsertId();
		if(CheckNullUtil.isNotEmpty(employeeIds)){
			String[] eArr = employeeIds.split(":");
			if(eArr != null && eArr.length > 0){
				//加入post_employee映射
				for(String element : eArr){
					LgPostEmployee record = new LgPostEmployee();
						record.setPostId(newPostId);
						record.setEmployeeId(Integer.valueOf(element));
					if(LgPostEmployeeMapper.insertSelective(record) < 1)
						throw new ServiceException("插入映射表失败");
				}
			}
		}
		//sendCommentMentionToAllEmployee(newPostId,lgPost.getPostTitle(),1,"");
		customerPublishTemplate(newPostId,lgPost.getContent());
		
		return ReturnResult.ok();
	}
	
	//获取帖子评论列表,
	@Override
	public PageInfo<?> getPostCommentList(LgPostComment lgPostComment, Integer page, Integer rows)
			throws ServiceException {
		Integer postId =  lgPostComment.getPostId();
		String loginUserName = lgPostComment.getLoginUserName();
		
		LgPostCommentExample example =  new LgPostCommentExample();
		com.light.pojo.LgPostCommentExample.Criteria criteria =  example.createCriteria();
		example.setOrderByClause("create_time desc");//根据最新发布排序
		
		if(CheckNullUtil.integerNull(postId))
			throw new ServiceException("postId不能为空");
			criteria.andPostIdEqualTo(postId);
		if(CheckNullUtil.isNotEmpty(loginUserName))
			criteria.andLoginUserNameLike("%"+loginUserName+"%");
		PageHelper.startPage(page, rows);
		List<LgPostComment> commentList = LgPostCommentMapper.selectByExample(example);
		List<Map> mapList = new ArrayList<>();

		if(CheckNullUtil.listNotNull(commentList)){
			for(LgPostComment element : commentList){
				Map elementMap = new HashMap<>();
				String headImg = "";
				//String headImg = LgLoginUserCustomMapper.getHeadUrlByLoginId(element.getLoginUserId());
				LgLoginUser lgLoginUser = LgLoginUserMapper.selectByPrimaryKey(element.getLoginUserId());
				if(lgLoginUser != null){
					String roleIds = lgLoginUser.getRoleIds();
					if(roleIds.equals("3")){
						//员工
						headImg = lgLoginUser.getHeadImg();
					}
					else if(roleIds.equals("4")){
						//客户
						String openId = element.getOpenId();
						LgLoginCuslistExample cuslistExample = new LgLoginCuslistExample();
							cuslistExample.createCriteria().andOpenIdEqualTo(openId);
						List<LgLoginCuslist> getCList = LgLoginCuslistMapper.selectByExample(cuslistExample);
						if(CheckNullUtil.listNotNull(getCList))
							headImg = getCList.get(0).getHeadimgurl();
					}
				}
				
				elementMap.put("element", element);
				elementMap.put("userImg", headImg);
				mapList.add(elementMap);
			}
		}
		
		PageInfo<?> pageInfo =  new PageInfo<>(mapList);
		return pageInfo;
	}
	//回复帖子
	@Override
	public boolean replyPost(LgPostComment lgPostComment,Integer type,String employeeIds) throws ServiceException {
		Integer parentId = lgPostComment.getParentId();
		Integer loginUserId = lgPostComment.getLoginUserId();
		Integer postId = lgPostComment.getPostId();
		String content = lgPostComment.getContent();
		String openId = lgPostComment.getOpenId();
		
		if(CheckNullUtil.integerNull(loginUserId))
			throw new ServiceException("没有登录人id");
		if(CheckNullUtil.integerNull(postId))
			throw new ServiceException("没有帖子id");
		if(CheckNullUtil.isEmpty(content))
			throw new ServiceException("评论内容为空");	
		if(CheckNullUtil.integerNotNull(parentId)){
			//如果有parentId，则意思为回复某个人的评论
			//获取那个评论的login_user_name
			String parentUserName = LgPostCustomMapper.getPostLoginUserNameByPcid(parentId);
			lgPostComment.setParentUserName(parentUserName);
		}else{
			lgPostComment.setParentUserName("未知用户");
		}
		LgLoginUser getUser = LgLoginUserMapper.selectByPrimaryKey(loginUserId);	
		if(getUser == null) throw new ServiceException("无相关回复人");
		String nickName = "未知";
		String roleIds = getUser.getRoleIds();
		if(roleIds.equals("4")){
			//如果是客户
			if(CheckNullUtil.isNotEmpty(openId)){
				LgLoginCuslistExample eCuslistExample = new LgLoginCuslistExample();
					eCuslistExample.createCriteria().andOpenIdEqualTo(openId);
				List<LgLoginCuslist> eList = LgLoginCuslistMapper.selectByExample(eCuslistExample);
				if(eList != null && eList.size() > 0){
					nickName = eList.get(0).getNickname();
					//setOpenId
				}
			}
		}else if (roleIds.equals("3")) {
			//如果是员工
			nickName = getUser.getNickName();
		}
	
		//登录人昵称获取-->login_user表nickname
		//String nickName = LgLoginUserCustomMapper.getUserNickNameByLoginId(loginUserId);
		lgPostComment.setLoginUserName(nickName);
			Integer replyTime = FormatUtil.timeStampInt();
		lgPostComment.setCreateTime(replyTime);
		int insertSta = LgPostCommentMapper.insertSelective(lgPostComment);
		if(insertSta < 1)
			throw new ServiceException("插入失败");
		
		LgPost getPost = LgPostMapper.selectByPrimaryKey(postId);
	/*	content nickName  replyTime 
		replyContent username replyTime askTime title content remark*/
		Integer askTime = getPost.getCreateTime();
		replyCommentTemplate(type,postId,employeeIds,content,nickName,FormatUtil. timeStamp2Date(replyTime,null),FormatUtil.timeStamp2Date(askTime,null),
				getPost.getPostTitle(),getPost.getContent());
		
		return true;
	}

	//删除评论，以及所有子评论
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map deletePostComment(Integer pcid) throws ServiceException {
		if(CheckNullUtil.integerNull(pcid))
			throw new ServiceException("id为空");
		List<Integer> sonIdList = LgPostCustomMapper.getSonCommentByPcid(pcid);
		if(CheckNullUtil.listNotNull(sonIdList)){
			for(Integer element : sonIdList){
				//删除的时候，还要对接七牛删除资源的接口
				if(LgPostCommentMapper.deleteByPrimaryKey(element) < 1)
					throw new ServiceException("删除子评论失败");
			}
		}
		if(LgPostCommentMapper.deleteByPrimaryKey(pcid) < 1)
			throw new ServiceException("删除评论失败");

		return ReturnResult.ok();
	}
	//获取我的解答列表
	@Override
	public PageInfo<?> getMyPost(Integer page, Integer rows, Integer loginId,Integer type) throws ServiceException {
		if(CheckNullUtil.integerNull(type))
			throw new ServiceException("请输入查询类型");
		if(CheckNullUtil.integerNull(loginId))
			throw new ServiceException("请输入用户id");
		
		 List<LastAtOutputbean> outputList = new ArrayList<>();
		if(type == 0){
			 //	查找用户相关的最新"评论"帖子列表
			PageHelper.startPage(page, rows);
			 List<LastAtOutputbean> lastList = LgPostCustomMapper.getLastCommentPostList(loginId);
			 LastAtOutputbean outputbean = null;
			 for(LastAtOutputbean element : lastList){
				 
				String processName =  LgProjectCustomMapper.getProcessNameById(element.getType());
				 
				String marketName = LgProjectCustomMapper.getProjectMarketName(element.getProjectId());
				 outputbean = new LastAtOutputbean(element,processName,marketName);
				 outputList.add(outputbean);
			 }
		}else if(type == 1){
			 //  查找用户相关的最新"@"帖子列表
			PageHelper.startPage(page, rows);
			 List<LgPost> newCList = LgPostCustomMapper.getLastATPostList(loginId);
			 LastAtOutputbean outputbean = null;
			 for(LgPost element : newCList){
				 String processName = LgProjectCustomMapper.getProcessNameById(element.getType());
				 String marketName = LgProjectCustomMapper.getProjectMarketName(element.getProjectId());
				 Integer commentCount = LgPostCustomMapper.getCountCommemtWithPostId(element.getPid());
				 outputbean = new LastAtOutputbean(element,commentCount,processName,marketName);
				 outputList.add(outputbean);
			 }
		}else if(type == 2){
			 //  查找用户发布的最新"发布"帖子列表
			PageHelper.startPage(page, rows);
			 List<LgPost> newCList = LgPostCustomMapper.getLastPublicPostList(loginId);
			 LastAtOutputbean outputbean = null;
			 for(LgPost element : newCList){
				 String processName = LgProjectCustomMapper.getProcessNameById(element.getType());
				 String marketName = LgProjectCustomMapper.getProjectMarketName(element.getProjectId());
				 Integer commentCount = LgPostCustomMapper.getCountCommemtWithPostId(element.getPid());
				 outputbean = new LastAtOutputbean(element,commentCount,processName,marketName);
				 outputList.add(outputbean);
			 }
		}
		PageInfo<?> pageInfo = new PageInfo<>(outputList);
		return pageInfo;
	}
	
	/**
	 * 20170405--此方法已弃用，改用新方法
	 */
	//给所有员工 和 客户  发送评论模板消息提醒
	@Override
	public void sendCommentMentionToAllEmployee(Integer postId, String content,Integer type,String employeeIds) {
		LgLoginUserExample example = new LgLoginUserExample();
		//com.light.pojo.LgLoginUserExample.Criteria criteria1 = 
		//type只会传1和2进来，如果是0的话，就不会调用此方法
		if(type == 2){
			//2-指定员工+所有客户
			String[] emArr = employeeIds.split(":");
			if(emArr != null && emArr.length > 0){
				List<Integer> emIntegers = new ArrayList<>();
				for(String element : emArr){
					emIntegers.add(Integer.valueOf(element));
				}
				example.createCriteria().andRoleIdsEqualTo("3").andIsLockedEqualTo(0).andUserTypeIdIn(emIntegers);
			}
		}else{
			//1-所有员工和客户
			//所有员工
			example.createCriteria().andRoleIdsEqualTo("3").andIsLockedEqualTo(0);
		}
		
/*		com.light.pojo.LgLoginUserExample.Criteria criteria2 =
				example.createCriteria().andRoleIdsEqualTo("3");
		example.or(criteria2);*/
		List<LgLoginUser> list = LgLoginUserMapper.selectByExample(example);
		if(list == null || list.size() < 1)
			throw new ServiceException("无员工");
		List<String> openIdList = new ArrayList<>();
		for(LgLoginUser element : list){
			if(element.getOpenId() != null)
				openIdList.add(element.getOpenId());
		}
		//project -customer_id -> login_user -user_type_id ->lg_login_cuslist 
		CommentSqlBean sqlBean = LgPostCustomMapper.getCommentBean(postId);
		if(sqlBean == null)
			throw new ServiceException("找不到相关数据");
			String market = sqlBean.getMarket();
			String title = sqlBean.getTitle();
			//获取工程相关的客户id
			Integer customerId = sqlBean.getCustomerId();
		List<String> cusList = LgPostCustomMapper.getCusListByCustomerId(customerId);
		openIdList.addAll(cusList);
		String indexDir = "http://"+configService.getIndexDir();
			String url = indexDir+"/GYAdmin/html/perInfo/index.html#bbsinfo?pid="+postId;
		WechatService.sendCommentTemplateToUser(openIdList, market, title, content, url);
	}
	/**
	 * 发布图面，推送的模板消息
	 */
	public void customerPublishTemplate(Integer postId, String content){
		LgLoginUserExample example = new LgLoginUserExample();
			//1-所有员工和客户---发布图面，默认全部推送
			example.createCriteria().andRoleIdsEqualTo("3").andIsLockedEqualTo(0);

		
		List<LgLoginUser> list = LgLoginUserMapper.selectByExample(example);
		if(list == null || list.size() < 1)
			throw new ServiceException("无员工");
		List<String> openIdList = new ArrayList<>();
		for(LgLoginUser element : list){
			if(element.getOpenId() != null)
				openIdList.add(element.getOpenId());
		}
		
		//project -customer_id -> login_user -user_type_id ->lg_login_cuslist 
		CommentSqlBean sqlBean = LgPostCustomMapper.getCommentBean(postId);
		if(sqlBean == null)
			throw new ServiceException("找不到相关数据");
			String market = sqlBean.getMarket();
			String title = sqlBean.getTitle();
			String username = sqlBean.getNickName();
			//获取工程相关的客户id
			Integer customerId = sqlBean.getCustomerId();
		List<String> cusList = LgPostCustomMapper.getCusListByCustomerId(customerId);
		openIdList.addAll(cusList);
		String indexDir = configService.getIndexDir();
		String url = "http://"+indexDir+"/GYAdmin/html/perInfo/index.html#bbsinfo?pid="+postId;
		WechatService.customerPublishPost(openIdList, market, title, content, url, username, "回复访客请点击此处");
		
	}
	/**
	 * 回复评论
	 * @Title: replyCommentTemplate 
	 * @author TobyHan
	 * Date : 2017年4月5日 下午8:51:37
	 */
	public void replyCommentTemplate(Integer type,Integer postId,String employeeIds, String replyContent,String nickName,String replyTime,String askTime,
			String title,String content){
		LgLoginUserExample example = new LgLoginUserExample();
			//1-所有员工和客户---发布图面，默认全部推送
			if(type == 1)
				example.createCriteria().andRoleIdsEqualTo("3").andIsLockedEqualTo(0);
			else if (type == 2) {
				//2-指定员工+所有客户
				String[] emArr = employeeIds.split(":");
				if(emArr != null && emArr.length > 0){
					List<Integer> emIntegers = new ArrayList<>();
					for(String element : emArr){
						emIntegers.add(Integer.valueOf(element));
					}
					example.createCriteria().andRoleIdsEqualTo("3").andIsLockedEqualTo(0).andUserTypeIdIn(emIntegers);
				}
			}else {
				return;
			}
		
		List<LgLoginUser> list = LgLoginUserMapper.selectByExample(example);
		if(list == null || list.size() < 1)
			throw new ServiceException("无员工");
		List<String> openIdList = new ArrayList<>();
		for(LgLoginUser element : list){
			if(element.getOpenId() != null)
				openIdList.add(element.getOpenId());
		}
		
		//project -customer_id -> login_user -user_type_id ->lg_login_cuslist 
		CommentSqlBean sqlBean = LgPostCustomMapper.getCommentBean(postId);
		if(sqlBean == null)
			throw new ServiceException("找不到相关数据");
			//String market = sqlBean.getMarket();
			//String title = sqlBean.getTitle();
			// username = sqlBean.getNickName();
			//获取工程相关的客户id
			Integer customerId = sqlBean.getCustomerId();
		List<String> cusList = LgPostCustomMapper.getCusListByCustomerId(customerId);
		openIdList.addAll(cusList);
		String indexDir = "http://"+configService.getIndexDir();
		String url = indexDir+"/GYAdmin/html/perInfo/index.html#bbsinfo?pid="+postId;
		WechatService.msgForReply(openIdList, url, replyContent, nickName, replyTime, askTime, title, content, "请点击此处查看详情");
		
	}


}
