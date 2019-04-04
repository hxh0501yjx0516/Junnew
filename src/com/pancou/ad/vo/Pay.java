package com.pancou.ad.vo;


/**
 * Pay entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Pay implements java.io.Serializable {

	// Fields

	private Integer payId;
	private Integer adPlanId;
	private String adPlanName;
	private Integer adPlanCycleId;
	private String adPlanCycleName;
	private Integer webMasterId;
	private String webMasterName;
	private Integer userId;
	private String userName;
	private Integer count;
	private float money;
	private Integer realCount;
	private float realMoney;
	private float trueMoney;
	private String remarks;
	private String payBeginTime;
	private String payEndTime;
	private String payAddTime;

	// Constructors

	/** default constructor */
	public Pay() {
	}


	// Property accessors

	public Integer getPayId() {
		return this.payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public Integer getAdPlanId() {
		return this.adPlanId;
	}

	public void setAdPlanId(Integer adPlanId) {
		this.adPlanId = adPlanId;
	}

	public String getAdPlanName() {
		return this.adPlanName;
	}

	public void setAdPlanName(String adPlanName) {
		this.adPlanName = adPlanName;
	}

	public Integer getAdPlanCycleId() {
		return this.adPlanCycleId;
	}

	public void setAdPlanCycleId(Integer adPlanCycleId) {
		this.adPlanCycleId = adPlanCycleId;
	}

	public String getAdPlanCycleName() {
		return this.adPlanCycleName;
	}

	public void setAdPlanCycleName(String adPlanCycleName) {
		this.adPlanCycleName = adPlanCycleName;
	}

	public Integer getWebMasterId() {
		return this.webMasterId;
	}

	public void setWebMasterId(Integer webMasterId) {
		this.webMasterId = webMasterId;
	}

	public String getWebMasterName() {
		return this.webMasterName;
	}

	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	public Integer getRealCount() {
		return this.realCount;
	}

	public void setRealCount(Integer realCount) {
		this.realCount = realCount;
	}


	public float getMoney() {
		return money;
	}


	public void setMoney(float money) {
		this.money = money;
	}


	public float getRealMoney() {
		return realMoney;
	}


	public void setRealMoney(float realMoney) {
		this.realMoney = realMoney;
	}


	public float getTrueMoney() {
		return trueMoney;
	}


	public void setTrueMoney(float trueMoney) {
		this.trueMoney = trueMoney;
	}


	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getPayBeginTime() {
		return payBeginTime;
	}


	public void setPayBeginTime(String payBeginTime) {
		this.payBeginTime = payBeginTime;
	}


	public String getPayEndTime() {
		return payEndTime;
	}


	public void setPayEndTime(String payEndTime) {
		this.payEndTime = payEndTime;
	}


	public String getPayAddTime() {
		return payAddTime;
	}


	public void setPayAddTime(String payAddTime) {
		this.payAddTime = payAddTime;
	}


}