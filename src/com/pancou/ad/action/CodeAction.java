package com.pancou.ad.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.vo.AdCreative;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.ReadyPlan;
import com.pancou.ad.vo.UrlAddress;
import com.pancou.ad.vo.WebMaster;

public class CodeAction extends BaseDispatchAction {

	/**
	 * step1
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward step1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		WebMaster w = (WebMaster) request.getSession()
				.getAttribute("webmaster");

		// ready plan
		String hql = "from ReadyPlan r where r.readyPlanStatus = 0";
		String condition = " and r.webMasterId = :webmasterid";
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("webmasterid", w.getWebMasterId());
		List<ReadyPlan> readyPlanList = dao.findAll(hql, condition, parms);

		request.setAttribute("readyPlanList", readyPlanList);

		// url
		String hqlUrl = "from UrlAddress u where u.urlStatus = 1";
		String conditionUrl = " and u.webMasterId = :webmasterid";
		Map<String, Object> parmsUrl = new HashMap<String, Object>();
		parmsUrl.put("webmasterid", w.getWebMasterId());
		List<UrlAddress> urlList = dao.findAll(hqlUrl, conditionUrl, parmsUrl);

		request.setAttribute("urlList", urlList);
		return mapping.findForward("step1");
	}

	/**
	 * step2
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward step2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String plan = this.getParameter("plan");
		String url = this.getParameter("url");

		String sqlCycle = "select top 1 * from AdPlanCycle a where 1=1 and a.adPlanCycleStatus = 0 and a.adPlanId = :planid";
		String orderCycle = " order by a.adPlanCycleId desc";
		Map<String, Object> parmsCycle = new HashMap<String, Object>();
		parmsCycle.put("planid", Integer.parseInt(plan.split("-")[0]));
		List<Object> colsCycle = new ArrayList<Object>();
		colsCycle.add(AdPlanCycle.class);
		List<AdPlanCycle> listCycle = dao.findBySql(sqlCycle + orderCycle,
				parmsCycle, colsCycle);
		if (listCycle.size() <= 0) {
			// request.setAttribute("creativemsg", "暂无可用创意");
		} else {
			AdPlanCycle adPlanCycle = (AdPlanCycle) listCycle.get(0);
			String hql = "from AdCreative a where 1=1";
			String condition = " and a.adCreativeStatus = 0 and a.isDefault = 0 and a.adSizeId = 6 and a.adPlanCycleId = :cycleid";
			String order = " order by a.adCreativeId desc";
			Map<String, Object> parms = new HashMap<String, Object>();
			parms.put("cycleid", adPlanCycle.getAdPlanCycleId());
			List<AdCreative> list = dao.findAll(hql, condition + order, parms);
			request.setAttribute("creativeList", list);
		}

		request.setAttribute("plan", plan);
		request.setAttribute("url", url);
		return mapping.findForward("step2");
	}

}
