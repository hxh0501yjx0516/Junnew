package com.pancou.ad.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.Configure;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.ReadyBox;
import com.pancou.ad.vo.ReadyPlan;
import com.pancou.ad.vo.WebMaster;

public class AdPlanAction extends BaseDispatchAction {

	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int pageNum = 0;// 页次
		int numPerPage = 8;// 每页显示条数
		if (!"".equals(this.getParameter("pageNum"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
		}
		String sql = "select a.*,c.customerName customerName from adPlan a left join customer c on a.customerId = c.customerId where 1=1 ";
		Map<String, Object> parms = new HashMap<String, Object>();
		String condition = " and a.adPlanStatus = :adPlanStatus and a.adPlanFlag = :adPlanFlag";
		parms.put("adPlanStatus", 0);
		parms.put("adPlanFlag", 0);

		List<Object> cols = new ArrayList<Object>();
		cols.add(AdPlan.class);
		cols.add("customerName");

		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage, dao.getCount(AdPlan.class, condition, parms));
		List<Object[]> list = dao.findBySql(pageMap.get("first"),
				pageMap.get("size"), sql + condition
						+ " order by a.addTime desc", parms, cols);
		String path = Configure.get("img.url");
		List<AdPlan> adPlanList = new ArrayList<AdPlan>();
		for (int i = 0; i < list.size(); i++) {
			Object[] object = list.get(i);
			AdPlan adPlan = (AdPlan) object[0];
			adPlan.setCustomerName(object[1] + "");
			if (adPlan.getAdPlanImg() == null
					|| "".equals(adPlan.getAdPlanImg())) {
				adPlan.setAdPlanImg(Configure.get("img.url.default"));
			} else {
				adPlan.setAdPlanImg(path + adPlan.getAdPlanImg());
			}
			adPlanList.add(adPlan);
		}
		this.buildPageResult(request,
				PagingHandle.getResultMap(pageMap, "adPlanList", adPlanList));
		return mapping.findForward("list");
	}

	@SuppressWarnings("unchecked")
	public ActionForward myadplan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebMaster users = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		int pageNum = 0;// 页次
		int numPerPage = 15;// 每页显示条数
		if (!"".equals(this.getParameter("pageNum"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
		}

		String condition = " and r.webMasterId = :webMasterId ";
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("webMasterId", users.getWebMasterId());
		if (!"".equals(this.getParameter("state"))) {
			condition += " and r.readyPlanStatus= :readyPlanStatus ";
			parms.put("readyPlanStatus",
					Integer.parseInt(this.getParameter("state")));
		}
		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage, dao.getCount(ReadyPlan.class, condition, parms));
		List<ReadyPlan> list = dao.findAll(pageMap.get("first"),
				pageMap.get("size"), "from ReadyPlan r where 1=1 ", condition,
				parms);
		if (list != null) {

			this.buildPageResult(request,
					PagingHandle.getResultMap(pageMap, "readyPlanList", list));
		}
		return mapping.findForward("readyplan");
	}

	@SuppressWarnings("unchecked")
	public ActionForward readybox(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebMaster users = (WebMaster) request.getSession().getAttribute(
				"webmaster");

		String sqlCount = "select count(*) "
				+ "from ReadyBox r "
				+ "left join AdPlan ap on r.adPlanId = ap.adPlanId "
				+ "left join url u on r.urlId = u.urlId "
				+ "left join AdCreative ac on r.adCreativeId = ac.adCreativeId "
				+ "left join AdBox ab on r.adBoxId = ab.adBoxId "
				+ "left join PayType pt on r.payTypeId = pt.payTypeId where ac.isDefault = 0 ";

		String sql = "select r.*,ap.adPlanName adPlanName,ac.adCreativeName adCreativeName,ab.adBoxName adBoxName,pt.payTypeName payTypeName,ac.isDefault isDefault,u.url url "
				+ "from ReadyBox r "
				+ "left join AdPlan ap on r.adPlanId = ap.adPlanId "
				+ "left join url u on r.urlId = u.urlId "
				+ "left join AdCreative ac on r.adCreativeId = ac.adCreativeId "
				+ "left join AdBox ab on r.adBoxId = ab.adBoxId "
				+ "left join PayType pt on r.payTypeId = pt.payTypeId where ac.isDefault = 0 ";
		int pageNum = 0;// 页次
		int numPerPage = 15;// 每页显示条数
		if (!"".equals(this.getParameter("pageNum"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
		}
		String condition = " and r.webMasterId = :webMasterId ";
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("webMasterId", users.getWebMasterId());
		List<Object> cols = new ArrayList<Object>();
		cols.add(ReadyBox.class);
		cols.add("adPlanName");
		cols.add("adCreativeName");
		cols.add("adBoxName");
		cols.add("payTypeName");
		cols.add("isDefault");
		cols.add("url");
		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage, dao.getCount(sqlCount + condition, parms));
		List<Object[]> list = dao.findBySql(pageMap.get("first"),
				pageMap.get("size"), sql + condition
						+ " order by r.readyBoxStatus asc", parms, cols);
		List<ReadyBox> readyBoxList = new ArrayList<ReadyBox>();
		String[] DayIpString = {};
		for (Object[] object : list) {
			ReadyBox readyBox = (ReadyBox) object[0];
			// 获得投放周期
			if (readyBox.getShowIpString() != null
					&& !"".equals(readyBox.getShowIpString())) {
				DayIpString = readyBox.getShowIpString().split("|");
				List<String> days = new ArrayList<String>();
				for (int i = 0; i < DayIpString.length; i++) {
					days.add(DayIpString[i].split(",")[1]);
				}
				Collections.sort(days);
				readyBox.setAdPlanCycleName(days.get(0) + "~"
						+ days.get(days.size() - 1));
			} else {
				readyBox.setAdPlanCycleName("无限制");
			}
			readyBox.setAdPlanName(object[1] + "");
			readyBox.setAdCreativeName(object[2] + "");
			readyBox.setAdBoxName(object[3] + "");
			readyBox.setPayTypeName(object[4] + "");
			readyBox.setIsDefault(Integer.parseInt(object[5] + ""));
			readyBox.setUrlName(object[6] + "");
			readyBoxList.add(readyBox);
		}
		if (readyBoxList != null) {

			this.buildPageResult(request, PagingHandle.getResultMap(pageMap,
					"readyBoxList", readyBoxList));
		}
		return mapping.findForward("readybox");
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebMaster users = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		if (users == null) {// 非法登录
			request.setAttribute("mes", "请重新登录!");
			return mapping.findForward("relogin");
		}
		return super.execute(mapping, form, request, response);
	}
}
