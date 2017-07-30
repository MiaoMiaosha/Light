package com.light.common;

import java.util.Map;

public class CommentTemplateMsg {

	private String touser;
	private String template_id;
	private String url;
	private String topcolor;
	private Map<String,SpecTemplateValue> data;
	String getTouser() {
		return touser;
	}
	void setTouser(String touser) {
		this.touser = touser;
	}
	String getTemplate_id() {
		return template_id;
	}
	void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	String getUrl() {
		return url;
	}
	void setUrl(String url) {
		this.url = url;
	}
	String getTopcolor() {
		return topcolor;
	}
	void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	Map<String, SpecTemplateValue> getData() {
		return data;
	}
	void setData(Map<String, SpecTemplateValue> data) {
		this.data = data;
	}
	
	
}
