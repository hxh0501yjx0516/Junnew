package com.pancou.ad.util;

import java.io.Serializable;
/**
 * MemCached 中存放的ReadyBox对象
 * 
 */
public class MemSMsg implements Serializable {
	private static final long serialVersionUID = 1L;
	/**	 * 编号	 */
	private int ReadyBoxId=0;
	/**	 * 广告位id	 */
	private int AdBoxId=0;
	/**	 * 创意id	 */
	private int AdCreativeId=0;
	/**	 * 计划周期id	 */
	private int AdPlanCycleId=0;
	/**	 * 广告计划id	 */
	private int AdPlanId=0;
	/**	 * 域名id	 */
	private int UrlId=0;
	/**	 * 站长id	 */
	private int WebMasterId=0;
	/**	 * 付费模式	 */
	private int PayTypeId=0;
	/**	 * 站长单价	 */
	private Float WebMasterPrice=0F;
	/**	 * 扣量	 */
	private int Discount=0;	
	/**	 * 日流量控制	 */
	private String ShowIpString="";	
	/**	 * 目标url	 */
	private String AdCreativeUrl="";	
	/**	 * 当日流量控制	 */
	private int ShowIp=0;		
	/**	 * 当日是否到量,跟ReadyBoxStatus有区别的	 */
	private int IsValid=0;		
	/**	 * 创意轮播等级	 */
	private int AdCreativeLevel=0;	
	/**	 * 创意地区定向	 */
	private String AreaFix="";		
	/**	 * 投放是否有效	 */
	private int ReadyBoxStatus=0;	
	/**	 * 创意图片src	 */
	private String AdCreativeImg="";
	/**	 * 创意模板文件名称	 */
	private String ModelJS="";
	/**	 * 创意宽度	 */
	private int AdWidth=0;	
	/**	 * 创意高度	 */
	private int AdHeight=0;	
	/**	 * 是否域名绑定	 */
	private int isURLBand=0;	
	/**	 * 站长有效域名列表，用于域名绑定	 */
	private String URLList="";
	
	
	/**	 * @return the URLList	 */
	public String getURLList() {
		return URLList;
	}
	/**	 * @param URLList 	 */
	public void setURLList(String sURLList) {
		this.URLList = sURLList;
	}
	/**	 * @return the isURLBand	 */
	public int getisURLBand() {
		return isURLBand;
	}
	/**	 * @param isURLBand 	 */
	public void setisURLBand(int iisURLBand) {
		this.isURLBand = iisURLBand;
	}
	
	/**	 * @return the AdCreativeImg	 */
	public String getAdCreativeImg() {
		return AdCreativeImg;
	}
	/**	 * @param AdCreativeImg 	 */
	public void setAdCreativeImg(String sAdCreativeImg) {
		this.AdCreativeImg = sAdCreativeImg;
	}
	
	/**	 * @return the ModelJS	 */
	public String getModelJS() {
		return ModelJS;
	}
	/**	 * @param ModelJS 	 */
	public void setModelJS(String sModelJS) {
		this.ModelJS = sModelJS;
	}
	
	/**	 * @return the AdHeight	 */
	public int getAdHeight() {
		return AdHeight;
	}
	/**	 * @param AdHeight 	 */
	public void setAdHeight(int iAdHeight) {
		this.AdHeight = iAdHeight;
	}
	
	/**	 * @return the AdWidth	 */
	public int getAdWidth() {
		return AdWidth;
	}
	/**	 * @param AdWidth 	 */
	public void setAdWidth(int iAdWidth) {
		this.AdWidth = iAdWidth;
	}

	
	/**	 * @return the ReadyBoxStatus	 */
	public int getReadyBoxStatus() {
		return ReadyBoxStatus;
	}
	/**	 * @param ReadyBoxStatus 	 */
	public void setReadyBoxStatus(int iReadyBoxStatus) {
		this.ReadyBoxStatus = iReadyBoxStatus;
	}
	/**	 * @return the AreaFix	 */
	public String getAreaFix() {
		return AreaFix;
	}
	/**	 * @param AreaFix 	 */
	public void setAreaFix(String sAreaFix) {
		this.AreaFix = sAreaFix;
	}
	
	/**	 * @return the AdCreativeLevel	 */
	public int getAdCreativeLevel() {
		return AdCreativeLevel;
	}
	/**	 * @param AdCreativeLevel 	 */
	public void setAdCreativeLevel(int iAdCreativeLevel) {
		this.AdCreativeLevel = iAdCreativeLevel;
	}
	
	/**	 * @return the IsValid	 */
	public int getIsValid() {
		return IsValid;
	}
	/**	 * @param IsValid 	 */
	public void setIsValid(int iIsValid) {
		this.IsValid = iIsValid;
	}
	
	/**	 * @return the ShowIp	 */
	public int getShowIp() {
		return ShowIp;
	}
	/**	 * @param ShowIp 	 */
	public void setShowIp(int iShowIp) {
		this.ShowIp = iShowIp;
	}
	
	/**	 * @return the ShowIpString	 */
	public String getShowIpString() {
		return ShowIpString;
	}
	/**	 * @param ShowIpString 	 */
	public void setShowIpString(String sShowIpString) {
		this.ShowIpString = sShowIpString;
	}
	
	
	
	/**	 * @return the AdCreativeUrl	 */
	public String getAdCreativeUrl() {
		return AdCreativeUrl;
	}
	/**	 * @param AdCreativeUrl 	 */
	public void setAdCreativeUrl(String sAdCreativeUrl) {
		this.AdCreativeUrl = sAdCreativeUrl;
	}
	
	/**	 * @return the Discount	 */
	public int getDiscount() {
		return Discount;
	}
	/**	 * @param Discount 	 */
	public void setDiscount(int iDiscount) {
		this.Discount = iDiscount;
	}
	
	/**	 * @return the WebMasterPrice	 */
	public Float getWebMasterPrice() {
		return WebMasterPrice;
	}
	/**	 * @param WebMasterPrice 	 */
	public void setWebMasterPrice(Float fWebMasterPrice) {
		this.WebMasterPrice = fWebMasterPrice;
	}
	
	/**	 * @return the PayTypeId	 */
	public int getPayTypeId() {
		return PayTypeId;
	}
	/**	 * @param PayTypeId 	 */
	public void setPayTypeId(int iPayTypeId) {
		this.PayTypeId = iPayTypeId;
	}
	
	
	/**	 * @return the WebMasterId	 */
	public int getWebMasterId() {
		return WebMasterId;
	}
	/**	 * @param AdPlanId 	 */
	public void setWebMasterId(int iWebMasterId) {
		this.WebMasterId = iWebMasterId;
	}
	
	/**	 * @return the AdPlanId	 */
	public int getUrlId() {
		return UrlId;
	}
	/**	 * @param AdPlanId 	 */
	public void setUrlId(int iUrlId) {
		this.UrlId = iUrlId;
	}
	
	/**	 * @return the AdPlanId	 */
	public int getAdPlanId() {
		return AdPlanId;
	}
	/**	 * @param AdPlanId 	 */
	public void setAdPlanId(int iAdPlanId) {
		this.AdPlanId = iAdPlanId;
	}
	
	/**	 * @return the AdPlanCycleId	 */
	public int getAdPlanCycleId() {
		return AdPlanCycleId;
	}
	/**	 * @param ReadyBoxId 	 */
	public void setAdPlanCycleId(int iAdPlanCycleId) {
		this.AdPlanCycleId = iAdPlanCycleId;
	}
	
	/**	 * @return the ReadyBoxId	 */
	public int getAdCreativeId() {
		return AdCreativeId;
	}
	/**	 * @param ReadyBoxId 	 */
	public void setAdCreativeId(int iAdCreativeId) {
		this.AdCreativeId = iAdCreativeId;
	}
	
	/**	 * @return the ReadyBoxId	 */
	public int getReadyBoxId() {
		return ReadyBoxId;
	}
	/**	 * @param ReadyBoxId 	 */
	public void setReadyBoxId(int iReadyBoxId) {
		this.ReadyBoxId = iReadyBoxId;
	}
	
	/**	 * @return the ReadyBoxId	 */
	public int getAdBoxId() {
		return AdBoxId;
	}
	/**	 * @param AdBoxId 	 */
	public void setAdBoxId(int iAdBoxId) {
		this.AdBoxId = iAdBoxId;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(ReadyBoxId).append("#"); //可以考虑无需取
		sb.append(AdBoxId).append("#");		
		sb.append(AdCreativeId).append("#");		
		sb.append(AdPlanCycleId).append("#");		
		sb.append(AdPlanId).append("#");		
		sb.append(UrlId).append("#");		
		sb.append(WebMasterId).append("#");		
		sb.append(PayTypeId).append("#");		//可以考虑无需取
		sb.append(WebMasterPrice).append("#");	//可以考虑无需取
		sb.append(Discount).append("#");		
		sb.append(ShowIpString).append("#");	
		sb.append(AdCreativeUrl).append("#");		
		sb.append(ShowIp).append("#");		
		sb.append(IsValid).append("#");		
		sb.append(AdCreativeLevel).append("#");		
		sb.append(AreaFix).append("#");
		sb.append(ReadyBoxStatus).append("#");			
		sb.append(AdCreativeImg).append("#");	
		sb.append(AdWidth).append("#");	
		sb.append(AdHeight).append("#");	
		sb.append(ModelJS).append("#");	
		sb.append(isURLBand).append("#");
		sb.append(URLList).append("#");
		return sb.toString();
	}

	public synchronized static MemSMsg getMemSMsg(String body) throws Exception {
		MemSMsg memMsg = new MemSMsg();
		String[] arr = body.split("#");
		if (arr.length >= 10) {
			memMsg.setReadyBoxId(Integer.parseInt(arr[0]));
			memMsg.setAdBoxId(Integer.parseInt(arr[1]));
			memMsg.setAdCreativeId(Integer.parseInt(arr[2]));	
			memMsg.setAdPlanCycleId(Integer.parseInt(arr[3]));		
			memMsg.setAdPlanId(Integer.parseInt(arr[4]));		
			memMsg.setUrlId(Integer.parseInt(arr[5]));
			memMsg.setWebMasterId(Integer.parseInt(arr[6]));			
			memMsg.setPayTypeId(Integer.parseInt(arr[7]));
			memMsg.setWebMasterPrice(Float.parseFloat(arr[8]));
			memMsg.setDiscount(Integer.parseInt(arr[9]));			
			memMsg.setShowIpString(arr[10]);
			memMsg.setAdCreativeUrl(arr[11]);
			memMsg.setShowIp(Integer.parseInt(arr[12]));			
			memMsg.setIsValid(Integer.parseInt(arr[13]));
			memMsg.setAdCreativeLevel(Integer.parseInt(arr[14]));
			memMsg.setAreaFix(arr[15]);
			memMsg.setReadyBoxStatus(Integer.parseInt(arr[16]));
			memMsg.setAdCreativeImg(arr[17]);
			memMsg.setAdWidth(Integer.parseInt(arr[18]));
			memMsg.setAdHeight(Integer.parseInt(arr[19]));
			memMsg.setModelJS(arr[20]);
			memMsg.setisURLBand(Integer.parseInt(arr[21]));
			memMsg.setURLList(arr[22]);
			return memMsg;
		} else {
			throw new Exception("MemSMsg Format error:" + body);
		}
	}

	public static void main(String[] args) throws Exception {
		MemSMsg msg = new MemSMsg();

		msg.setAdBoxId(12);
		msg.setAdCreativeId(123);

		msg.setAdCreativeUrl("http://www.baidu.com");
		msg.setAreaFix("0");
	
		String str = msg.toString();
		System.out.println(str);
		msg = MemSMsg.getMemSMsg(str);
		str = msg.toString();
		System.out.println(str);

	}
}
