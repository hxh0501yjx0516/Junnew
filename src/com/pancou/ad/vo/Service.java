package com.pancou.ad.vo;

import java.io.Serializable;

/**
 * �ͷ�ʵ�� 
 * @author tmb
 *
 */
@SuppressWarnings("serial")
public class Service implements Serializable{
	/**
	 * 
	 */
	private int id;
	private String name; //�ǳ�
	private int qq; //qq
	private int typeId; //����0-ý�飬1-���ۣ�2-Ǣ̸��3-�ͷ���4-����
	private int status; //״̬0-��ʾ��1-����
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQq() {
		return qq;
	}
	public void setQq(int qq) {
		this.qq = qq;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}

