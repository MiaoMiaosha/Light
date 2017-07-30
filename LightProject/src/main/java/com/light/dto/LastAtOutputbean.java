package com.light.dto;

import com.light.pojo.LgPost;

/**
 * 
 * @ClassName: LastAtOutputbean 
 * @author TobyHan
 * @date 2017年3月6日 下午4:28:45 
 *
 */
public class LastAtOutputbean {
	
	
	 	private Integer pid;

	    private Integer loginUserId;

	    private String nickName;

	    private String postTitle;

	    private Integer type;

	    private String content;

	    private String uploadImg;

	    private String uploadDoc;

	    private Integer createTime;

	    private Integer projectId;

	    private Integer status;
	    
	    private String processName;
	    
	    private String marketName;

	private Integer commentCount;
	public LastAtOutputbean(){}
	public LastAtOutputbean(LastAtOutputbean outputbean,String processName,String marketName){
		
			pid = outputbean.getPid();
	        loginUserId = outputbean.getLoginUserId();
	       nickName = outputbean.getNickName();
	       postTitle = outputbean.getPostTitle();
	        type = outputbean.getType();
	       content = outputbean.getContent();
	       uploadImg = outputbean.getUploadImg();
	       uploadDoc = outputbean.getUploadDoc();
	        createTime = outputbean.getCreateTime();
	        projectId = outputbean.getProjectId();
	        status = outputbean.getStatus();
	        commentCount = outputbean.getCommentCount();
		    this.processName = processName;
		    this.marketName = marketName;
	}
	public LastAtOutputbean(LgPost lgPost,Integer commentCount,
			String processName,String marketName){
		
		pid = lgPost.getPid();
        loginUserId = lgPost.getLoginUserId();
       nickName = lgPost.getNickName();
       postTitle = lgPost.getPostTitle();
        type = lgPost.getType();
       content = lgPost.getContent();
       uploadImg = lgPost.getUploadImg();
       uploadDoc = lgPost.getUploadDoc();
        createTime = lgPost.getCreateTime();
        projectId = lgPost.getProjectId();
        status = lgPost.getStatus();
	    this.processName = processName;
	    this.marketName = marketName;
	    this.commentCount = commentCount;
}
	
	
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(Integer loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUploadImg() {
		return uploadImg;
	}
	public void setUploadImg(String uploadImg) {
		this.uploadImg = uploadImg;
	}
	public String getUploadDoc() {
		return uploadDoc;
	}
	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}
	public Integer getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	
	
}
