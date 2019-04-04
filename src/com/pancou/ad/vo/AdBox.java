package com.pancou.ad.vo;

import java.io.Serializable;

/**
 * 广告位实体 
 * @author tmb
 *
 */
@SuppressWarnings("serial")
public class AdBox implements Serializable{
	/**
	 * 
	 */
	private int adBoxId;
	private String adBoxName; //广告位名称
	private int adBoxInfoId; //规格ID
	private int webMasterId; //站长ID
	private int urlId; //域名ID
	private int adPlanId; //计划ID
	private int adPlanCycleId; //计划周期ID
	private int adBoxStatus; //状态
	private int adBoxFlag; //标识：0-默认广告位，1-普通广告位
	private String addTime; //添加时间
	private int isURLBand;//
	
	
	private String adBoxInfoName;
	private String modelName;
	private String sizeName;
	private String webMasterName;
	private String urlName;
	
	public int getIsURLBand() {
		return isURLBand;
	}
	public void setIsURLBand(int isURLBand) {
		this.isURLBand = isURLBand;
	}
	public String getAdBoxInfoName() {
		return adBoxInfoName;
	}
	public void setAdBoxInfoName(String adBoxInfoName) {
		this.adBoxInfoName = adBoxInfoName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getWebMasterName() {
		return webMasterName;
	}
	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public int getAdBoxId() {
		return adBoxId;
	}
	public void setAdBoxId(int adBoxId) {
		this.adBoxId = adBoxId;
	}
	public String getAdBoxName() {
		return adBoxName;
	}
	public void setAdBoxName(String adBoxName) {
		this.adBoxName = adBoxName;
	}
	public int getAdBoxInfoId() {
		return adBoxInfoId;
	}
	public void setAdBoxInfoId(int adBoxInfoId) {
		this.adBoxInfoId = adBoxInfoId;
	}
	public int getWebMasterId() {
		return webMasterId;
	}
	public void setWebMasterId(int webMasterId) {
		this.webMasterId = webMasterId;
	}
	public int getUrlId() {
		return urlId;
	}
	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}
	public int getAdPlanId() {
		return adPlanId;
	}
	public void setAdPlanId(int adPlanId) {
		this.adPlanId = adPlanId;
	}
	public int getAdPlanCycleId() {
		return adPlanCycleId;
	}
	public void setAdPlanCycleId(int adPlanCycleId) {
		this.adPlanCycleId = adPlanCycleId;
	}
	public int getAdBoxStatus() {
		return adBoxStatus;
	}
	public void setAdBoxStatus(int adBoxStatus) {
		this.adBoxStatus = adBoxStatus;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public int getAdBoxFlag() {
		return adBoxFlag;
	}
	public void setAdBoxFlag(int adBoxFlag) {
		this.adBoxFlag = adBoxFlag;
	}

}

