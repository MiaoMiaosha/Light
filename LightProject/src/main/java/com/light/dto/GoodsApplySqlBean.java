package com.light.dto;
/**
 * 设备申请返回
 * @ClassName: GoodsApplySqlBean 
 * @author TobyHan
 * @date 2017年3月15日 下午6:49:50 
 *
 */
public class GoodsApplySqlBean {

	private Integer gaid;
	private Integer goodsId;
	private Integer createTime;
	private Integer status;
	private String goodsName;
	private String img;
	public Integer getGaid() {
		return gaid;
	}
	public void setGaid(Integer gaid) {
		this.gaid = gaid;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
}
