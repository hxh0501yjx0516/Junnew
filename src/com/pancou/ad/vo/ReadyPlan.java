package com.pancou.ad.vo;

import java.io.Serializable;

/**
 * ���ƻ���ý���Ӧʵ�� 
 * @author tmb
 *
 */
@SuppressWarnings("serial")
public class ReadyPlan implements Serializable{
	private int readyPlanId;
	private int adPlanId; //���ƻ�ID
	private String adPlanName; //�ƻ�����
	private int webMasterId; //վ��ID
	private String webMasterName; //վ������
	private int readyPlanStatus;//״̬
	private String addTime; //���ʱ��
	public int getReadyPlanId() {
		return readyPlanId;
	}
	public void setReadyPlanId(int readyPlanId) {
		this.readyPlanId = readyPlanId;
	}
	public int getAdPlanId() {
		return adPlanId;
	}
	public void setAdPlanId(int adPlanId) {
		this.adPlanId = adPlanId;
	}
	public String getAdPlanName() {
		return adPlanName;
	}
	public void setAdPlanName(String adPlanName) {
		this.adPlanName = adPlanName;
	}
	public int getWebMasterId() {
		return webMasterId;
	}
	public void setWebMasterId(int webMasterId) {
		this.webMasterId = webMasterId;
	}
	public String getWebMasterName() {
		return webMasterName;
	}
	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}
	public int getReadyPlanStatus() {
		return readyPlanStatus;
	}
	public void setReadyPlanStatus(int readyPlanStatus) {
		this.readyPlanStatus = readyPlanStatus;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}

