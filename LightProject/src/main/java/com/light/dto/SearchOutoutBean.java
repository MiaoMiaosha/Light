package com.light.dto;
/**
 *  搜索返回
 * @ClassName: SearchOutoutBean 
 * @author TobyHan
 * @date 2017年3月23日 上午10:04:55 
 *
 */
public class SearchOutoutBean {

	private Integer contentId;
	private String content;
	private Integer type;
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
