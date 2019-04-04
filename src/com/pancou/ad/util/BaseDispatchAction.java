package com.pancou.ad.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.pancou.ad.dao.PlatDao;
import com.pancou.ad.util.json.JsonWriter;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.Customer;
import com.pancou.ad.vo.PayType;
import com.pancou.ad.vo.ReadyPlan;
import com.pancou.ad.vo.UrlAddress;
import com.pancou.ad.vo.Users;
import com.pancou.ad.vo.WebMaster;

public class BaseDispatchAction extends DispatchAction {

	private Map<String, Object> parms;
	protected static final int SUCCESS_STATUS = 200;
	protected static final int FAILURE_STATUS = 300;
	protected static final String CALLBACKTYPE_CLOSECURRENT = "closeCurrent";
	protected static final String CALLBACKTYPE_FORWARD = "forward";
	protected PlatDao dao = new PlatDao();
	private JsonWriter writer = new JsonWriter();
	private String json;
	private String forwardUrl;

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// System.out.println("BaseDispatchAction start!!!");
		// Users users = (Users)request.getSession().getAttribute("user");
		// if(users==null) {//�Ƿ���¼
		// request.setAttribute("mes", "�����µ�¼!");
		// return mapping.findForward("logout") ;
		// }
		// System.out.println("request.getRemoteAddr=="+request.getRemoteAddr());
		parms = WebUtils.getParametersStartingWith(request, "");
		return super.execute(mapping, form, request, response);
	}

	/**
	 * ������в���
	 * 
	 * @return
	 */
	protected String getParameter(String name) {
		if (parms.get(name) == null) {
			return "";
		}
		return parms.get(name).toString().trim();
	}

	/**
	 * �������KEYֵ
	 * 
	 * @param prefix
	 *            ��������ǰ׺
	 * @return ������ͬǰ׺�Ĳ�������
	 */
	protected List<String> getKeys(String prefix) {
		Set<String> keys = parms.keySet();
		List<String> keyList = new ArrayList<String>();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (null != prefix && !"".equals(prefix)) {
				if (key.indexOf(prefix) != -1) {
					keyList.add(key);
				}
			} else {
				keyList.add(key);
			}
		}
		return keyList;
	}

	/**
	 * �Ƴ�����
	 * 
	 * @return
	 */
	protected void removeParameter(String name) {
		parms.put(name, "");
	}

	/**
	 * �����Ч״̬�û��б�ҳ���ȡ�û�����Ϊuserslist
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void getServiceList(HttpServletRequest request) {
		Map<String, Object> parms = new HashMap<String, Object>();
		String serverAddress = Configure.get("img.url");
		List serviceList = dao.findAll(
				"from Service s where s.status = 0 order by s.id desc ", "",
				parms);
		request.setAttribute("serviceList", serviceList);
		request.setAttribute("serverAddress", serverAddress);
	}

	/**
	 * �����Ч״̬�û��б�ҳ���ȡ�û�����Ϊuserslist
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void getUsersList(HttpServletRequest request) {
		// String[] stateArr={"state"};
		// String[] valueArr={"0"};
		// List<Users> userslist = dao.findAll(Users.class,stateArr,valueArr);
		String condition = "where u.userId = ur.userId and ur.roleId =:roleId and u.state = :state";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", 11);
		params.put("state", 0);
		List<Users> userslist = dao.findAll(
				"select u from Users u,UserRole ur ", condition, params);
		request.setAttribute("userslist", userslist);
	}

	/**
	 * �����Ч״̬��������б�
	 * 
	 * @param request
	 * @param name
	 *            ����ֵ����
	 */
	@SuppressWarnings("unchecked")
	protected void getAdPlanCycleList(HttpServletRequest request, String name) {
		String sql = "select a.* from adPlanCycle a left join adPlan ap on a.adPlanId = ap.adPlanId "
				+ "where ap.customerId = :customerId ";
		Map<String, Object> parms = new HashMap<String, Object>();
		Customer users = (Customer) request.getSession().getAttribute(
				"customer");
		parms.put("customerId", users.getCustomerId());

		ArrayList<Object> cols = new ArrayList<Object>();
		cols.add(AdPlanCycle.class);

		List<AdPlanCycle> adPlanCycleList = dao.findBySql(sql, parms, cols);
		// ArrayList<AdPlanCycle> adPlanCycleList = new
		// ArrayList<AdPlanCycle>();
		// for(int i=0;i<list.size();i++){
		// AdPlanCycle adPlanCycle = (AdPlanCycle)list.get(i);
		// adPlanCycleList.add(adPlanCycle);
		// }
		request.setAttribute(name, adPlanCycleList);
	}

	/**
	 * �����Ч״̬���ƻ��б�
	 * 
	 * @param request
	 * @param name
	 *            ����ֵ����
	 */
	@SuppressWarnings("unchecked")
	protected void getAdPlanList(HttpServletRequest request, String name) {
		String[] stateArr = { "customerId" };
		Customer users = (Customer) request.getSession().getAttribute(
				"customer");
		System.out.println("�û�ID��" + users.getCustomerId());
		String[] valueArr = { users.getCustomerId() + "" };
		List<AdPlan> adPlanList = dao.findAll(AdPlan.class, stateArr, valueArr);
		request.setAttribute(name, adPlanList);
	}

	/**
	 * ���վ�����ͨ��������״̬�ļƻ�
	 * 
	 * @param request
	 * @param name
	 */
	@SuppressWarnings("unchecked")
	protected void getReadyPlanList(HttpServletRequest request, String name) {
		WebMaster webmaster = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		String[] stateArr = { "webMasterId" };
		String[] valueArr = { webmaster.getWebMasterId() + "" };
		List<ReadyPlan> ReadyPlanList = dao.findAll(ReadyPlan.class, stateArr,
				valueArr);
		request.setAttribute(name, ReadyPlanList);
	}

	/**
	 * ���վ�������б�
	 * 
	 * @param request
	 * @param name
	 */
	@SuppressWarnings("unchecked")
	protected void getUrlList(HttpServletRequest request, String name) {
		WebMaster webmaster = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		String[] stateArr = { "webMasterId" };
		String[] valueArr = { webmaster.getWebMasterId() + "" };
		List<UrlAddress> addressList = dao.findAll(UrlAddress.class, stateArr,
				valueArr);
		request.setAttribute(name, addressList);
	}

	/**
	 * ���֧��ģʽ��ҳ���ȡ����ΪpayTypeList
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void getPayTypeList(HttpServletRequest request) {
		String[] names = new String[0];
		String[] valueArrs = new String[0];
		List payTypeList = dao.findAll(PayType.class, names, valueArrs);
		request.setAttribute("payTypeList", payTypeList);
	}

	/**
	 * 
	 * @param request
	 * @param statusCode
	 * @param message
	 */
	protected void buildAjaxResult(HttpServletRequest request, int statusCode,
			String message) {
		request.setAttribute("statusCode", statusCode);
		request.setAttribute("message", message);
	}

	/**
	 * ���callbackTypeֵΪforward����Ҫ�����ú���ת·��forwardUrl
	 * 
	 * @param request
	 * @param statusCode
	 * @param message
	 * @param callbackType
	 */
	protected void buildAjaxResult(HttpServletRequest request, int statusCode,
			String message, String callbackType) {
		buildAjaxResult(request, statusCode, message);
		request.setAttribute("callbackType", callbackType);
		if (CALLBACKTYPE_FORWARD.equals(callbackType)) {
			request.setAttribute("forwardUrl", forwardUrl);
		}
	}

	/**
	 * 
	 * @param request
	 * @param statusCode
	 * @param message
	 * @param callbackType
	 * @param navTabId
	 */
	protected void buildAjaxResult(HttpServletRequest request, int statusCode,
			String message, String callbackType, String navTabId) {
		buildAjaxResult(request, statusCode, message, callbackType);
		request.setAttribute("navTabId", navTabId);
	}

	/**
	 * 
	 * @param request
	 * @param parms
	 *            ��ҳ����map����
	 */
	protected void buildPageResult(HttpServletRequest request,
			Map<String, Object> parms) {
		Set<String> keys = parms.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = parms.get(key);
			request.setAttribute(key, value);
		}

	}

	/**
	 * �������������תΪJSON������ֵ
	 */
	protected String createJsonResult(String result) {
		json = writer.write(result);
		return json;
	}
}
