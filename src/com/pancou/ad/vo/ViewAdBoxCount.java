package com.pancou.ad.vo;

import java.util.Date;

/**
 * ViewAdBoxCountId entity. @author MyEclipse Persistence Tools
 */

public class ViewAdBoxCount implements java.io.Serializable {

	// Fields

	private int adBoxCountId;
	private int readyBoxId;
	private int browseC;
	private int browseTrue;
	private int pv;
	private int uv;
	private int ip;
	private String adBoxCountTime;
	private int adPlanId;
	private String adPlanName;
	private int adPlanCycleId;
	private String adPlanCycleName;
	private int urlId;
	private String url;
	private int userId;
	private String userName;
	private int webMasterId;
	private String webMasterName;
	private int adCreativeId;
	private String adCreativeName;
	private String adCreativeImg;
	private int isDefault;
	private int discount;
	private int showType;
	
	private int viewCols;



	public int getViewCols() {
		return viewCols;
	}

	public void setViewCols(int viewCols) {
		this.viewCols = viewCols;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public int getAdBoxCountId() {
		return this.adBoxCountId;
	}

	public void setAdBoxCountId(int adBoxCountId) {
		this.adBoxCountId = adBoxCountId;
	}

	public int getReadyBoxId() {
		return this.readyBoxId;
	}

	public void setReadyBoxId(int readyBoxId) {
		this.readyBoxId = readyBoxId;
	}

	public int getBrowseC() {
		return this.browseC;
	}

	public void setBrowseC(int browseC) {
		this.browseC = browseC;
	}

	public int getBrowseTrue() {
		return this.browseTrue;
	}

	public void setBrowseTrue(int browseTrue) {
		this.browseTrue = browseTrue;
	}

	public int getPv() {
		return this.pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getUv() {
		return this.uv;
	}

	public void setUv(int uv) {
		this.uv = uv;
	}

	public int getIp() {
		return this.ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

	public String getAdBoxCountTime() {
		return this.adBoxCountTime;
	}

	public void setAdBoxCountTime(String adBoxCountTime) {
		this.adBoxCountTime = adBoxCountTime;
	}

	public int getAdPlanId() {
		return this.adPlanId;
	}

	public void setAdPlanId(int adPlanId) {
		this.adPlanId = adPlanId;
	}

	public String getAdPlanName() {
		return this.adPlanName;
	}

	public void setAdPlanName(String adPlanName) {
		this.adPlanName = adPlanName;
	}

	public int getAdPlanCycleId() {
		return this.adPlanCycleId;
	}

	public void setAdPlanCycleId(int adPlanCycleId) {
		this.adPlanCycleId = adPlanCycleId;
	}

	public String getAdPlanCycleName() {
		return this.adPlanCycleName;
	}

	public void setAdPlanCycleName(String adPlanCycleName) {
		this.adPlanCycleName = adPlanCycleName;
	}

	public int getUrlId() {
		return this.urlId;
	}

	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getWebMasterId() {
		return this.webMasterId;
	}

	public void setWebMasterId(int webMasterId) {
		this.webMasterId = webMasterId;
	}

	public String getWebMasterName() {
		return this.webMasterName;
	}

	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}
	
	public int getAdCreativeId() {
		return this.adCreativeId;
	}

	public void setAdCreativeId(int adCreativeId) {
		this.adCreativeId = adCreativeId;
	}
	
	public String getAdCreativeName() {
		return this.adCreativeName;
	}

	public void setAdCreativeName(String adCreativeName) {
		this.adCreativeName = adCreativeName;
	}
	
	public String getAdCreativeImg() {
		return this.adCreativeImg;
	}

	public void setAdCreativeImg(String adCreativeImg) {
		this.adCreativeImg = adCreativeImg;
	}
	
	public void setDiscount(int discount){
		this.discount = discount;
	}
	
	public int getDiscount(){
		return discount;
	}
	
	public void setShowType(int showType){
		this.showType = showType;
	}
	
	public int getShowType(){
		return showType;
	}
}