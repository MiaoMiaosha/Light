package com.light.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.light.common.CheckNullUtil;
import com.light.common.FormatUtil;
import com.light.common.ReturnResult;
import com.light.exception.ServiceException;
import com.light.mapper.LgCommentMapper;
import com.light.mapper.LgUserMapper;
import com.light.mapper.custom.LgLoginUserCustomMapper;
import com.light.mapper.custom.LgManagePublishMapper;
import com.light.mapper.custom.LgPublicMapper;
import com.light.pojo.LgComment;
import com.light.pojo.LgCommentExample;
import com.light.pojo.LgCommentExample.Criteria;
import com.light.service.CommentService;
/**
 * 评论相关服务
 * @ClassName: CommentServiceImpl 
 * @author TobyHan
 * @date 2017年3月10日 下午2:31:50 
 *
 */
@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	LgCommentMapper LgCommentMapper;
	@Autowired
	LgUserMapper LgUserMapper;
	@Autowired
	LgPublicMapper LgPublicMapper;

	@Override
	public PageInfo<?> getCommentList(LgComment lgComment, Integer page, Integer rows) {
		Integer type = lgComment.getType();
		Integer id = lgComment.getId();
		Integer typeId = lgComment.getTypeId();
		Integer userId = lgComment.getUserId();
		
		LgCommentExample example = new LgCommentExample();
			Criteria criteria =  example.createCriteria();
		if(CheckNullUtil.integerNotNull(type))
			criteria.andTypeEqualTo(type);
		if(CheckNullUtil.integerNotNull(id))
			criteria.andIdEqualTo(id);
		if(CheckNullUtil.integerNotNull(typeId))
			criteria.andTypeIdEqualTo(typeId);
		if(CheckNullUtil.integerNotNull(userId))
			criteria.andUserIdEqualTo(userId);
		example.setOrderByClause("create_time desc");
		
		PageHelper.startPage(page, rows);
		List<LgComment> list = LgCommentMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			for(LgComment element : list){
				String headImg = LgPublicMapper.getWXUserHeadImg(element.getUserId());
				element.setHeadImg(headImg);
			}
		}
		PageInfo<?> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public Map addComment(LgComment lgComment) {
		Integer type = lgComment.getType();
		Integer typeId = lgComment.getTypeId();
		Integer userId = lgComment.getUserId();
		String content = lgComment.getContent();
		if(type == null || typeId == null || userId == null
				|| content == null)
			throw new ServiceException("评论内容错误");
			lgComment.setCreateTime(FormatUtil.timeStampInt());
			String nickName = LgPublicMapper.getWXUserNameById(userId);
			lgComment.setNickName(nickName);
			
		if(LgCommentMapper.insertSelective(lgComment) < 1)
			throw new ServiceException("插入错误");
		return ReturnResult.ok();
	}

	@Override
	public Map deleteComment(Integer id) {
		if(CheckNullUtil.integerNull(id))
			throw new ServiceException("id为空");
		if(LgCommentMapper.deleteByPrimaryKey(id) < 1)
			throw new ServiceException("删除错误");
		return ReturnResult.ok();
	}
	

}
