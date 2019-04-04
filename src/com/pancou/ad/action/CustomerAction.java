package com.pancou.ad.action;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.ExcelUtil;
import com.pancou.ad.util.MD5;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.Customer;

public class CustomerAction extends BaseDispatchAction {
	/**
	 * 广告主首页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward myhome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			System.out
					.println("customerAction中的myhome方法##################################");
			Map<String, Object> parms = new HashMap<String, Object>();
			Customer customer = (Customer) request.getSession().getAttribute(
					"customer");
			parms.put("customerId", customer.getCustomerId());
			// parms.put("readyBoxStatus",Integer.parseInt("0"));
			// parms.put("adPlanStatus",Integer.parseInt("0"));
			// parms.put("adPlanCycleStatus",Integer.parseInt("0"));
			List<Object> cols = new ArrayList<Object>();
			cols.add(AdPlanCycle.class);
			cols.add("adPlanName");
			List<Object[]> adPlanlist = dao.findBySql(
					"select ac.*,a.adPlanName adPlanName from adPlanCycle ac "
							+ "left join adPlan a on ac.adPlanId = a.adPlanId "
							+ "where a.customerId=:customerId and "
							+ "a.adPlanStatus = 0 and "
							+ "ac.adPlanCycleStatus = 0 ", parms, cols);
			// 查询计划周期当日流量
			int disCount = 0;
			Map<String, Object> parms1 = new HashMap<String, Object>();
			String condition = " rb.adPlanCycleId =:adPlanCycleId and abc.adBoxCountTime >= convert(varchar(100),getDate(),102) and abc.adBoxCountTime < convert(varchar(100),getDate()+1,102)";
			// 获得正在投放的计划周期
			List<AdPlanCycle> adPlanCycleList = new ArrayList<AdPlanCycle>();
			for (int i = 0; i < adPlanlist.size(); i++) {
				Object[] object = adPlanlist.get(i);
				AdPlanCycle adPlanCycle = (AdPlanCycle) object[0];
				adPlanCycle.setAdPlanName(object[1] + "");
				disCount = adPlanCycle.getDisCount() / 100;
				parms1.put("adPlanCycleId", adPlanCycle.getAdPlanCycleId());
				// 获取实际数量
				List<Object[]> realList = dao
						.findBySql(
								"select sum(pv) PV,sum(uv) UV,sum(ip) IP from AdBoxCount abc right join ReadyBox rb on abc.readyBoxId = rb.readyBoxId where "
										+ condition, parms1);
				if (realList != null && realList.size() > 0) {
					switch (adPlanCycle.getShowType()) {
					case 1:
						// UV
						if (null != realList.get(0)[1]) {
							adPlanCycle
									.setRealFlowList(Integer.parseInt(realList
											.get(0)[1] + "")
											- (Integer.parseInt(realList.get(0)[1]
													+ "") * disCount));
						}
						break;
					case 2:
						// PV
						if (null != realList.get(0)[0]) {
							adPlanCycle
									.setRealFlowList(Integer.parseInt(realList
											.get(0)[0] + "")
											- (Integer.parseInt(realList.get(0)[0]
													+ "") * disCount));
						}
						break;
					case 3:
						// IP
						if (null != realList.get(0)[2]) {
							adPlanCycle
									.setRealFlowList(Integer.parseInt(realList
											.get(0)[2] + "")
											- (Integer.parseInt(realList.get(0)[2]
													+ "") * disCount));
						}
						break;
					}
				} else {
					adPlanCycle.setRealFlowList(0);

				}
				adPlanCycle.setCurrentTime(DatetimeHandle.formatCurrentDate());
				// 周期数据总和列表集合
				if (adPlanCycle.getRealFlowList() == 0) {
					continue;
				}
				adPlanCycleList.add(adPlanCycle);
			}
			int adPlanSum = dao.getCount(AdPlan.class,
					"and a.customerId=:customerId ", parms);
			int adPlanRun = dao.getCount(AdPlan.class,
					" and a.customerId=:customerId and a.adPlanStatus = 0",
					parms);
			int adPlanOver = dao.getCount(AdPlan.class,
					" and a.customerId=:customerId and a.adPlanStatus = 1",
					parms);
			int cycleSum = dao
					.getCount(
							"select count(ac.adPlanCycleId) from adPlan a right join adPlanCycle ac on a.adPlanId = ac.adPlanId where a.customerId=:customerId",
							parms);
			int cycleRun = dao
					.getCount(
							"select count(ac.adPlanCycleId) from adPlan a right join adPlanCycle ac on a.adPlanId = ac.adPlanId where  ac.adPlanCycleStatus = 0 and  a.customerId=:customerId",
							parms);
			int cycleOver = dao
					.getCount(
							"select count(ac.adPlanCycleId) from adPlan a right join adPlanCycle ac on a.adPlanId = ac.adPlanId where  ac.adPlanCycleStatus = 1 and a.customerId=:customerId",
							parms);
			request.setAttribute("adPlanSum", adPlanSum);
			request.setAttribute("adPlanRun", adPlanRun);
			request.setAttribute("adPlanOver", adPlanOver);
			request.setAttribute("cycleSum", cycleSum);
			request.setAttribute("cycleRun", cycleRun);
			request.setAttribute("cycleOver", cycleOver);
			request.setAttribute("adPlanCycleList", adPlanCycleList);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return mapping.findForward("myhome");
	}

	/**
	 * 广告计划页面
	 * 
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward adplan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int pageNum = 0;// 页次
		int numPerPage = 15;// 每页显示条数
		if (!"".equals(this.getParameter("pageNum"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			// numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");
		Map<String, Object> parms = new HashMap<String, Object>();
		String condition = " and  a.customerId = :customerId ";
		parms.put("customerId", customer.getCustomerId());
		// parms.put("adPlanStatus",0);
		if (!"".equals(this.getParameter("state"))) {
			condition += " and a.adPlanStatus = :state";
			parms.put("state", Integer.parseInt(this.getParameter("state")));
		}
		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage, dao.getCount(AdPlan.class, condition, parms));
		List<AdPlan> adPlanList = dao.findAll(pageMap.get("first"),
				pageMap.get("size"), "from AdPlan a where 1=1 ", condition
						+ " order by a.adPlanId desc ", parms);
		// List qryList = dao.findBySql(pageMap.get("first"),
		// pageMap.get("size"),sql+condition+" order by a.adPlanId desc",parms,cols);
		if (adPlanList != null) {
			this.buildPageResult(request, PagingHandle.getResultMap(pageMap,
					"adPlanList", adPlanList));
		}
		request.setAttribute("state", this.getParameter("state"));
		return mapping.findForward("adplan");
	}

	/**
	 * 计划周期页面
	 * 
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward adplancycle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int pageNum = 0;// 页次
		int numPerPage = 15;// 每页显示条数
		if (!"".equals(this.getParameter("pageNum"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			// numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");
		String sqlCount = "";
		String sql = "";
		String condition = "";
		Map<String, Object> parms = new HashMap<String, Object>();
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		sqlCount = "select count(a.adPlanCycleId) from adPlanCycle a left join adPlan ap on a.adPlanId = ap.adPlanId where ";
		sql = "select a.*,ap.adPlanName adPlanName from adPlanCycle a left join adPlan ap on a.adPlanId = ap.adPlanId where ";
		condition = " ap.customerId = :customerId";
		parms.put("customerId", customer.getCustomerId());
		if (!"".equals(this.getParameter("adPlanId"))) {
			condition += " and a.adPlanId = :adPlanId ";
			parms.put("adPlanId",
					Integer.parseInt(this.getParameter("adPlanId")));
		}
		if (!"".equals(this.getParameter("state"))) {
			condition += " and a.adPlanCycleStatus = :state";
			parms.put("state", Integer.parseInt(this.getParameter("state")));
		}
		pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,
				dao.getCount(sqlCount + condition, parms));
		List<Object> cols = new ArrayList<Object>();
		cols.add(AdPlanCycle.class);
		cols.add("adPlanName");
		List qryList = dao
				.findBySql(pageMap.get("first"), pageMap.get("size"), sql
						+ condition + " order by a.adPlanCycleId desc", parms,
						cols);
		List<AdPlanCycle> list = new ArrayList<AdPlanCycle>();
		for (int i = 0; i < qryList.size(); i++) {
			Object[] object = (Object[]) qryList.get(i);
			AdPlanCycle adPlanCycle = (AdPlanCycle) object[0];
			adPlanCycle.setAdPlanName(object[1] + "");
			list.add(adPlanCycle);
		}
		if (list != null) {
			this.buildPageResult(request,
					PagingHandle.getResultMap(pageMap, "adPlanCycleList", list));
		}
		request.setAttribute("adPlanId", this.getParameter("adPlanId"));
		request.setAttribute("state", this.getParameter("state"));
		return mapping.findForward("adplancycle");
	}

	/**
	 * 广告主数据报表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward report(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			int pageNum = 0;// 页次
			int numPerPage = 15;// 每页显示条数
			if (!"".equals(this.getParameter("pageNum"))) {
				// 分页
				pageNum = Integer.parseInt(this.getParameter("pageNum"));
				// numPerPage =
				// Integer.parseInt(this.getParameter("numPerPage"));
			}
			Customer customer = (Customer) request.getSession().getAttribute(
					"customer");
			request.setAttribute("customer", customer);
			// 获得客户广告计划
			this.getAdPlanList(request, "adPlanList");
			// 获得客户广告计划周期
			this.getAdPlanCycleList(request, "cycleList");

			String beginTime = this.getParameter("beginTime");
			String endTime = this.getParameter("endTime");
			if (!"".equals(this.getParameter("flag"))) {
				beginTime = DatetimeHandle.formatDate(new Date(),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				endTime = DatetimeHandle.formatDate(new Date(),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
			}
			Map<String, Object> parms = new HashMap<String, Object>();
			String condition = " ";
			parms.put("customerId", customer.getCustomerId());

			if (!"".equals(this.getParameter("adPlanId"))) {
				condition += " and ap.adPlanId = :adPlanId ";
				parms.put("adPlanId",
						Integer.parseInt(this.getParameter("adPlanId")));
			}
			if (!"".equals(this.getParameter("adPlanCycleId"))) {
				condition += " and apc.adPlanCycleId = :adPlanCycleId ";
				parms.put("adPlanCycleId",
						Integer.parseInt(this.getParameter("adPlanCycleId")));
			}
			if (!"".equals(beginTime)) {
				condition += " and adp.adBoxCountTime >= :beginTime ";
				parms.put("beginTime", beginTime);
			}
			if (!"".equals(endTime)) {
				condition += "and adp.adBoxCountTime <= :endTime";
				parms.put("endTime", endTime + " 23:59:59");
			}
			List<Object> cols = new ArrayList<Object>();
			cols.add("showType");
			cols.add("adPlanCycleName");
			cols.add("uv");
			cols.add("pv");
			cols.add("ip");
			cols.add("adBoxCountTime");
			cols.add("adPlanName");
			String sql = "select apc.showType showType,apc.adPlanCycleName adPlanCycleName,adp.pv pv,adp.uv uv,adp.ip ip,adp.adBoxCountTime adBoxCountTime,ap.adplanName adPlanName from ("
					+ "select sum(pv) PV,sum(uv) UV,sum(ip) IP,abc.adBoxCountTime adBoxCountTime,rb.adPlanCycleId cycleId from AdBoxCount abc "
					+ "left join ReadyBox rb on abc.readyBoxId = rb.readyBoxId "
					+ "where exists("
					+ "select ac.adplancycleid from adplancycle ac "
					+ "left join adplan a on ac.adplanid = a.adplanid "
					+ "where a.customerid = :customerId and rb.adPlanCycleId = ac.adplancycleid"
					+ ") "
					+ "group by abc.adBoxCountTime,rb.adPlanCycleId"
					+ ") adp "
					+ "left join adplancycle apc on apc.adplancycleid = adp.cycleid left join adplan ap on apc.adplanId = ap.adplanId where 1=1 ";
			String sqlCount = "select count(s.pv) from (" + sql + condition
					+ ") s";
			Map<String, Integer> pageMap = new HashMap<String, Integer>();
			pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,
					dao.getCount(sqlCount, parms));
			List<Object[]> adPlanlist = dao.findBySql(pageMap.get("first"),
					pageMap.get("size"), sql + condition, parms, cols);
			// 查询计划周期当日流量
			int disCount = 0;
			// 获得正在投放的计划周期
			List<AdPlanCycle> adPlanCycleList = new ArrayList<AdPlanCycle>();
			for (int i = 0; i < adPlanlist.size(); i++) {
				Object[] object = adPlanlist.get(i);
				AdPlanCycle adPlanCycle = new AdPlanCycle();
				adPlanCycle.setAdPlanCycleId(i + 1);
				adPlanCycle.setAdPlanCycleName(object[1] + "");
				disCount = adPlanCycle.getDisCount() / 100;
				switch (Integer.parseInt(object[0] + "")) {
				case 1:
					// UV
					if (null != object[2]) {
						adPlanCycle
								.setRealFlowList(Integer.parseInt(object[2]
										+ "")
										- (Integer.parseInt(object[2] + "") * disCount));
					}
					break;
				case 2:
					// PV
					if (null != object[3]) {
						adPlanCycle
								.setRealFlowList(Integer.parseInt(object[3]
										+ "")
										- (Integer.parseInt(object[3] + "") * disCount));
					}
					break;
				case 3:
					// IP
					if (null != object[4]) {
						adPlanCycle
								.setRealFlowList(Integer.parseInt(object[4]
										+ "")
										- (Integer.parseInt(object[4] + "") * disCount));
					}
					break;
				}
				adPlanCycle.setCurrentTime(object[5] + "");
				adPlanCycle.setAdPlanName(object[6] + "");
				// 周期数据总和列表集合
				adPlanCycleList.add(adPlanCycle);
			}
			if (adPlanCycleList != null) {
				this.buildPageResult(request, PagingHandle.getResultMap(
						pageMap, "adPlanCycleList", adPlanCycleList));
			}
			request.setAttribute("adPlanId", this.getParameter("adPlanId"));
			request.setAttribute("adPlanCycleId",
					this.getParameter("adPlanCycleId"));
			request.setAttribute("beginTime", beginTime);
			request.setAttribute("endTime", endTime);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return mapping.findForward("report");
	}

	/*
	 * @SuppressWarnings("unchecked") public ActionForward report(ActionMapping
	 * mapping, ActionForm form, HttpServletRequest request, HttpServletResponse
	 * response) throws Exception { try { Customer customer =
	 * (Customer)request.getSession().getAttribute("customer"); //获得客户广告计划
	 * this.getAdPlanList(request,"adPlanList"); //获得客户广告计划周期
	 * this.getAdPlanCycleList(request,"adPlanCycleList");
	 * 
	 * Map<String,Object> parms = new HashMap<String, Object>(); String
	 * condition = " ap.customerId=:customerId ";
	 * parms.put("customerId",customer.getCustomerId());
	 * 
	 * if(!"".equals(this.getParameter("adPlanId"))){ condition
	 * +=" and a.adPlanId = :adPlanId "; parms.put("adPlanId",
	 * Integer.parseInt(this.getParameter("adPlanId"))); }
	 * if(!"".equals(this.getParameter("adPlanCycleId"))){ condition
	 * +=" and a.adPlanCycleId = :adPlanCycleId "; parms.put("adPlanCycleId",
	 * Integer.parseInt(this.getParameter("adPlanCycleId"))); } List<Object>
	 * cols = new ArrayList<Object>(); cols.add(AdPlanCycle.class);
	 * cols.add("adPlanName");
	 * 
	 * List<Object[]> adPlanlist =
	 * dao.findBySql("select a.*,ap.adPlanName adPlanName from adPlanCycle a " +
	 * "left join adPlan ap on a.adPlanId = ap.adPlanId " + "where "+condition,
	 * parms,cols);
	 * 
	 * //查询计划周期当日流量 int disCount = 0; Map<String,Object> parms1 = new
	 * HashMap<String, Object>(); String condition1 =
	 * " rb.adPlanCycleId =:adPlanCycleId ";
	 * if(!"".equals(this.getParameter("beginTime"))){ condition1
	 * +=" and abc.adBoxCountTime >= :beginTime "; parms1.put("beginTime",
	 * this.getParameter("beginTime")); }
	 * if(!"".equals(this.getParameter("endTime"))){ condition1 +=
	 * "and abc.adBoxCountTime <= :endTime" ; parms1.put("endTime",
	 * this.getParameter("endTime")+" 23:59:59"); } condition1 +=
	 * " group by abc.adBoxCountTime"; String sql1=
	 * "select sum(pv) PV,sum(uv) UV,sum(ip) IP,abc.adBoxCountTime adBoxCountTime from AdBoxCount abc "
	 * + "left join ReadyBox rb on abc.readyBoxId = rb.readyBoxId where ";
	 * //获得正在投放的计划周期 List<AdPlanCycle> adPlanCycleList = new
	 * ArrayList<AdPlanCycle>(); for(int i=0;i<adPlanlist.size();i++){ Object[]
	 * object = adPlanlist.get(i); AdPlanCycle adPlanCycle =
	 * (AdPlanCycle)object[0]; adPlanCycle.setAdPlanName(object[1]+""); disCount
	 * = adPlanCycle.getDisCount()/100; parms1.put("adPlanCycleId",
	 * adPlanCycle.getAdPlanCycleId()); //获取实际数量 List<Object[]> realList =
	 * dao.findBySql(sql1+condition1, parms1); List<RealDayFlow> realDayFlowList
	 * = new ArrayList<RealDayFlow>(); if(realList !=null && realList.size()>0){
	 * for(int j=0;j<realList.size();j++){ RealDayFlow dayFlow = new
	 * RealDayFlow(); switch(adPlanCycle.getShowType()){ case 1: //UV
	 * dayFlow.setRealFlow(Integer.parseInt(realList.get(j)[1]+""));
	 * dayFlow.setDateTime(realList.get(j)[3]+""); realDayFlowList.add(dayFlow);
	 * break; case 2: //PV
	 * dayFlow.setRealFlow(Integer.parseInt(realList.get(j)[0]+""));
	 * dayFlow.setDateTime(realList.get(j)[3]+""); realDayFlowList.add(dayFlow);
	 * break; case 3: //IP
	 * dayFlow.setRealFlow(Integer.parseInt(realList.get(j)[2]+""));
	 * dayFlow.setDateTime(realList.get(j)[3]+""); realDayFlowList.add(dayFlow);
	 * break; } } adPlanCycle.setDayFlow(realDayFlowList); }else{
	 * adPlanCycle.setDayFlow(realDayFlowList); } //周期数据总和列表集合
	 * adPlanCycleList.add(adPlanCycle); } request.setAttribute("adPlanId",
	 * this.getParameter("adPlanId"));
	 * request.setAttribute("adPlanCycleId",this.getParameter("adPlanCycleId"));
	 * request.setAttribute("beginTime", this.getParameter("beginTime"));
	 * request.setAttribute("endTime", this.getParameter("endTime"));
	 * request.setAttribute("adPlanCycleList", adPlanCycleList); } catch
	 * (NumberFormatException e) { e.printStackTrace(); } return
	 * mapping.findForward("report"); }
	 */
	/**
	 * 广告主数据报表导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward reportExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String fileName = "数据报表";
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			// URLDecoder.decode(fileName ,"utf-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel;charset=UTF-8");// 定义输出类型
			List<String> headerList = new ArrayList<String>();
			headerList.add("编号");
			headerList.add("广告计划");
			headerList.add("广告排期");
			headerList.add("点击数");
			headerList.add("日期");
			Customer customer = (Customer) request.getSession().getAttribute(
					"customer");
			// 获得客户广告计划
			this.getAdPlanList(request, "adPlanList");
			// 获得客户广告计划周期
			this.getAdPlanCycleList(request, "adPlanCycleList");

			Map<String, Object> parms = new HashMap<String, Object>();
			String condition = " ";
			parms.put("customerId", customer.getCustomerId());

			if (!"".equals(this.getParameter("adPlanId"))) {
				condition += " and ap.adPlanId = :adPlanId ";
				parms.put("adPlanId",
						Integer.parseInt(this.getParameter("adPlanId")));
			}
			if (!"".equals(this.getParameter("adPlanCycleId"))) {
				condition += " and apc.adPlanCycleId = :adPlanCycleId ";
				parms.put("adPlanCycleId",
						Integer.parseInt(this.getParameter("adPlanCycleId")));
			}
			if (!"".equals(this.getParameter("beginTime"))) {
				condition += " and adp.adBoxCountTime >= :beginTime ";
				parms.put("beginTime", this.getParameter("beginTime"));
			}
			if (!"".equals(this.getParameter("endTime"))) {
				condition += "and adp.adBoxCountTime <= :endTime";
				parms.put("endTime", this.getParameter("endTime") + " 23:59:59");
			}
			List<Object> cols = new ArrayList<Object>();
			cols.add("showType");
			cols.add("adPlanCycleName");
			cols.add("uv");
			cols.add("pv");
			cols.add("ip");
			cols.add("adBoxCountTime");
			cols.add("adPlanName");
			String sql = "select apc.showType showType,apc.adPlanCycleName adPlanCycleName,adp.pv pv,adp.uv uv,adp.ip ip,adp.adBoxCountTime adBoxCountTime,ap.adplanName adPlanName from ("
					+ "select sum(pv) PV,sum(uv) UV,sum(ip) IP,abc.adBoxCountTime adBoxCountTime,rb.adPlanCycleId cycleId from AdBoxCount abc "
					+ "left join ReadyBox rb on abc.readyBoxId = rb.readyBoxId "
					+ "where exists("
					+ "select ac.adplancycleid from adplancycle ac "
					+ "left join adplan a on ac.adplanid = a.adplanid "
					+ "where a.customerid = :customerId and rb.adPlanCycleId = ac.adplancycleid"
					+ ") "
					+ "group by abc.adBoxCountTime,rb.adPlanCycleId"
					+ ") adp "
					+ "left join adplancycle apc on apc.adplancycleid = adp.cycleid left join adplan ap on apc.adplanId = ap.adplanId where 1=1 ";
			List<Object[]> adPlanlist = dao.findBySql(sql + condition, parms,
					cols);
			// 查询计划周期当日流量
			int disCount = 0;
			// 获得正在投放的计划周期
			List<AdPlanCycle> adPlanCycleList = new ArrayList<AdPlanCycle>();
			for (int i = 0; i < adPlanlist.size(); i++) {
				Object[] object = adPlanlist.get(i);
				AdPlanCycle adPlanCycle = new AdPlanCycle();
				adPlanCycle.setAdPlanCycleId(i + 1);
				adPlanCycle.setAdPlanCycleName(object[1] + "");
				disCount = adPlanCycle.getDisCount() / 100;
				switch (Integer.parseInt(object[0] + "")) {
				case 1:
					// UV
					if (null != object[2]) {
						adPlanCycle
								.setRealFlowList(Integer.parseInt(object[2]
										+ "")
										- (Integer.parseInt(object[2] + "") * disCount));
					}
					break;
				case 2:
					// PV
					if (null != object[3]) {
						adPlanCycle
								.setRealFlowList(Integer.parseInt(object[3]
										+ "")
										- (Integer.parseInt(object[3] + "") * disCount));
					}
					break;
				case 3:
					// IP
					if (null != object[4]) {
						adPlanCycle
								.setRealFlowList(Integer.parseInt(object[4]
										+ "")
										- (Integer.parseInt(object[4] + "") * disCount));
					}
					break;
				}
				adPlanCycle.setCurrentTime(object[5] + "");
				adPlanCycle.setAdPlanName(object[6] + "");
				// 周期数据总和列表集合
				adPlanCycleList.add(adPlanCycle);
			}
			List<Object[]> datas = new ArrayList<Object[]>();
			DecimalFormat df = new DecimalFormat("##,###.###");
			int k = 1;
			for (AdPlanCycle cycle : adPlanCycleList) {
				Object[] object = new Object[5];
				object[0] = k;
				object[1] = cycle.getAdPlanName();
				object[2] = cycle.getAdPlanCycleName();
				object[3] = df.format(cycle.getRealFlowList());
				object[4] = DatetimeHandle.formatDate(
						DatetimeHandle.parseDate(cycle.getCurrentTime()),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				datas.add(object);
				k++;
			}
			ExcelUtil.export(datas, headerList, fileName, os);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 修改客户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mypage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!"".equals(this.getParameter("flag"))
				&& "edit".equals(this.getParameter("flag"))) {
			try {
				Customer customer = (Customer) dao.findById(Customer.class,
						Integer.parseInt(this.getParameter("customerId")));
				// customer.setCustomerName(this.getParameter("username"));
				customer.setCustomerPassword(MD5.MD5Encode(this
						.getParameter("password")));
				customer.setCustomerContactName(this.getParameter("contact"));
				customer.setCustomerContactMobile(this
						.getParameter("telephone"));
				customer.setCustomerAddress(this.getParameter("address"));
				customer.setCustomerPost(this.getParameter("post"));
				customer.setCustomerUrl(this.getParameter("url"));
				dao.update(customer);
				request.setAttribute("msg", "修改成功");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "修改失败");
			}
		}
		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");
		request.setAttribute("customer", customer);
		return mapping.findForward("mypage");
	}

	/**
	 * 
	 * 查询客户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward validate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String[] pname = new String[1];
		String[] pvalue = new String[1];
		pname[0] = "customerName";
		pvalue[0] = this.getParameter("username");
		String result = "";
		List userslist = dao.findAll(Customer.class, pname, pvalue);
		Customer users = (Customer) request.getSession().getAttribute(
				"customer");
		if (userslist != null && userslist.size() > 0) {
			// 该用户名已存在
			if (users.getCustomerId() != ((Customer) userslist.get(0))
					.getCustomerId()) {
				result = this.createJsonResult("faild");
			} else {
				result = this.createJsonResult("success");
			}
		} else {
			result = this.createJsonResult("success");
		}
		PrintWriter pw = response.getWriter();
		pw.write(result);
		pw.flush();
		return null;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Customer users = (Customer) request.getSession().getAttribute(
				"customer");
		if (users == null) {// 非法登录
			request.setAttribute("mes", "请重新登录!");
			return mapping.findForward("relogin");
		}
		return super.execute(mapping, form, request, response);
	}

}
