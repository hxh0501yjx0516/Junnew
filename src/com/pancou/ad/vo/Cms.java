package com.pancou.ad.vo;

import java.io.Serializable;

/**
 * ����ʵ�� 
 * @author tmb
 *
 */
@SuppressWarnings("serial")
public class Cms implements Serializable{
	/**
	 * 
	 */
	private int cmsId;
	private int cmsClassId; //����ID
	private String cmsName; //����
	private String cmsContent; //����
	private String cmsImg; //ͼƬ
	private int cmsFlag; //��ʶ
	private int cmsStatus; //״̬
	private String addTime; //���ʱ��
	
	private String cmsClassName;
	public String getCmsClassName() {
		return cmsClassName;
	}
	public void setCmsClassName(String cmsClassName) {
		this.cmsClassName = cmsClassName;
	}
	public int getCmsId() {
		return cmsId;
	}
	public void setCmsId(int cmsId) {
		this.cmsId = cmsId;
	}
	public int getCmsClassId() {
		return cmsClassId;
	}
	public void setCmsClassId(int cmsClassId) {
		this.cmsClassId = cmsClassId;
	}
	public String getCmsName() {
		return cmsName;
	}
	public void setCmsName(String cmsName) {
		this.cmsName = cmsName;
	}
	public String getCmsContent() {
		return cmsContent;
	}
	public void setCmsContent(String cmsContent) {
		this.cmsContent = cmsContent;
	}
	public String getCmsImg() {
		return cmsImg;
	}
	public void setCmsImg(String cmsImg) {
		this.cmsImg = cmsImg;
	}
	public int getCmsFlag() {
		return cmsFlag;
	}
	public void setCmsFlag(int cmsFlag) {
		this.cmsFlag = cmsFlag;
	}
	public int getCmsStatus() {
		return cmsStatus;
	}
	public void setCmsStatus(int cmsStatus) {
		this.cmsStatus = cmsStatus;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}

