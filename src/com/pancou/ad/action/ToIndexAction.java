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
import com.pancou.ad.vo.Cms;

public class ToIndexAction extends BaseDispatchAction {
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ##########################骏易动态、结算公告、成功案例、广告计划###########################
		Map<String, Object> parms = new HashMap<String, Object>();
		String condition = "";
		List<Cms> cmsList = dao
				.findAll(
						"from Cms c where c.cmsStatus = 0 order by c.addTime desc,c.cmsFlag desc",
						condition, parms);
		List<Cms> trendList = new ArrayList<Cms>();
		List<Cms> noticeList = new ArrayList<Cms>();
		List<Cms> caseList = new ArrayList<Cms>();
		List<Cms> planList = new ArrayList<Cms>();
		for (Cms cms : cmsList) {
			int classId = cms.getCmsClassId();
			switch (classId) {
			case 3:
				// 骏易动态
				trendList.add(cms);
				break;
			case 2:
				// 结算公告
				noticeList.add(cms);
				break;
			case 4:
				// 经典案例
				caseList.add(cms);
				break;
			case 7:
				// 广告计划
				planList.add(cms);
				break;
			}
		}
		request.setAttribute("trendList", trendList);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("caseList", caseList);
		request.setAttribute("adPlanList", planList);
		// ##########################广告活动###########################
		// List<AdPlan> adPlanList =
		// dao.findAll("from AdPlan a where a.adPlanFlag = 0 order by a.adPlanId desc",condition,parms);
		// request.setAttribute("adPlanList", adPlanList);
		// ##########################在线客服###########################
		this.getServiceList(request);
		return mapping.findForward("list");
	}
}
