package com.pancou.ad.vo;

import java.io.Serializable;

/**
 * �����ʵ�� 
 * @author tmb
 *
 */
public class Customer implements Serializable{
	/**
	 * 
	 */
	private int customerId;
	private String customerName; //�������
	private String customerPassword;
	private String customerType; //�ͻ���ҵ����
	private String customerContactName; //��ϵ��
	private String customerContactMobile; //��ϵ�˵绰
	private String customerAddress; //��ϵ��ַ
	private String customerPost; //�ʱ�
	private String customerUrl; //��ַ
	private int customerStatus ;  //״̬
	private String addTime; //���ʱ��
	private int userId;
	private String userName;
	private int planCount;
	private int planCycleCount;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPlanCount() {
		return planCount;
	}
	public void setPlanCount(int planCount) {
		this.planCount = planCount;
	}
	public int getPlanCycleCount() {
		return planCycleCount;
	}
	public void setPlanCycleCount(int planCycleCount) {
		this.planCycleCount = planCycleCount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerContactName() {
		return customerContactName;
	}
	public void setCustomerContactName(String customerContactName) {
		this.customerContactName = customerContactName;
	}
	public String getCustomerContactMobile() {
		return customerContactMobile;
	}
	public void setCustomerContactMobile(String customerContactMobile) {
		this.customerContactMobile = customerContactMobile;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPost() {
		return customerPost;
	}
	public void setCustomerPost(String customerPost) {
		this.customerPost = customerPost;
	}
	public String getCustomerUrl() {
		return customerUrl;
	}
	public void setCustomerUrl(String customerUrl) {
		this.customerUrl = customerUrl;
	}
	public int getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(int customerStatus) {
		this.customerStatus = customerStatus;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

}

