package com.pancou.ad.action;

import java.io.OutputStream;
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
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.Pay;
import com.pancou.ad.vo.ReportCount;
import com.pancou.ad.vo.ViewAdBoxCount;
import com.pancou.ad.vo.WebMaster;

public class ReportAction extends BaseDispatchAction {
	/**
	 * 今日数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward daily(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			int pageNum = 0;// 页次
			int numPerPage = 15;// 每页显示条数
			if (!"".equals(this.getParameter("pageNum"))) {
				// 分页
				pageNum = Integer.parseInt(this.getParameter("pageNum"));
			}
			WebMaster webMaster = (WebMaster) request.getSession()
					.getAttribute("webmaster");
			int webMasterId = webMaster.getWebMasterId();

			String condition = " and v.webMasterId =:webMasterId and v.isDefault = 0";
			Map<String, Object> parms = new HashMap<String, Object>();
			parms.put("webMasterId", webMasterId);

			Map<String, Integer> pageMap = PagingHandle
					.getPagingParams(
							pageNum,
							numPerPage,
							dao.getCount(
									"select count(*) from ViewAdBoxCount v where DateDiff(day,getdate(),v.adBoxCountTime) = 0 "
											+ condition, parms));

			String sqlSum = "select v.*	from ViewAdBoxCount v "
					+ " Where DateDiff(day,getdate(),v.adBoxCountTime) = 0 and v.isDefault = 0";
			List<Object> cols = new ArrayList<Object>();
			cols.add(ViewAdBoxCount.class);
			List<ViewAdBoxCount> list = dao.findBySql(pageMap.get("first"),
					pageMap.get("size"), sqlSum + condition, parms, cols);
			List<ViewAdBoxCount> vList = new ArrayList<ViewAdBoxCount>();
			for (ViewAdBoxCount count : list) {
				switch (count.getShowType()) {
				case 0:
					// pv
					count.setViewCols(count.getPv()
							* (100 - count.getDiscount()) / 100);
					break;
				case 1:
					// uv
					count.setViewCols(count.getUv()
							* (100 - count.getDiscount()) / 100);
					break;
				case 2:
					// ip
					count.setViewCols(count.getIp()
							* (100 - count.getDiscount()) / 100);
					break;
				}
				vList.add(count);
			}
			if (list != null) {
				this.buildPageResult(request, PagingHandle.getResultMap(
						pageMap, "adBoxCountList", vList));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("daily");
	}

	/**
	 * 历史数据(报表导出方法：reportExcel)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward history(ActionMapping mapping, ActionForm form,
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
			String beginTime = this.getParameter("beginTime");
			String endTime = this.getParameter("endTime");
			WebMaster webMaster = (WebMaster) request.getSession()
					.getAttribute("webmaster");
			int webMasterId = webMaster.getWebMasterId();

			String condition = " and v.webMasterId =:webMasterId and v.isDefault = 0 ";
			Map<String, Object> parms = new HashMap<String, Object>();
			parms.put("webMasterId", webMasterId);
			if (!"".equals(this.getParameter("adPlanId"))) {
				condition += " and v.adPlanId =:adPlanId";
				parms.put("adPlanId",
						Integer.parseInt(this.getParameter("adPlanId")));
			}
			if ("".equals(this.getParameter("beginTime"))
					&& "".equals(this.getParameter("endTime"))) {
				condition += " and datediff(day,v.adBoxCountTime,getdate()) = 0";
				beginTime = DatetimeHandle.formatDate(new Date(),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				endTime = DatetimeHandle.formatDate(new Date(),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
			} else {
				if (!"".equals(this.getParameter("beginTime"))) {
					condition += " and v.adBoxCountTime >=:bt ";
					parms.put("bt", this.getParameter("beginTime"));
				}
				if (!"".equals(this.getParameter("endTime"))) {
					condition += " and v.adBoxCountTime <= :et";
					parms.put("et", this.getParameter("endTime") + " 23:59:59");
				}
			}
			Map<String, Integer> pageMap = PagingHandle.getPagingParams(
					pageNum, numPerPage,
					dao.getCount(ViewAdBoxCount.class, condition, parms));

			String sqlSum = "select v.*	from ViewAdBoxCount v  where v.isDefault = 0";
			List<Object> cols = new ArrayList<Object>();
			cols.add(ViewAdBoxCount.class);
			List<ViewAdBoxCount> list = dao.findBySql(pageMap.get("first"),
					pageMap.get("size"), sqlSum + condition, parms, cols);
			List<ViewAdBoxCount> vList = new ArrayList<ViewAdBoxCount>();
			for (ViewAdBoxCount count : list) {
				switch (count.getShowType()) {
				case 0:
					// pv
					count.setViewCols(count.getPv()
							* (100 - count.getDiscount()) / 100);
					break;
				case 1:
					// uv
					count.setViewCols(count.getUv()
							* (100 - count.getDiscount()) / 100);
					break;
				case 2:
					// ip
					count.setViewCols(count.getIp()
							* (100 - count.getDiscount()) / 100);
					break;
				}
				vList.add(count);
			}
			if (list != null) {
				this.buildPageResult(request, PagingHandle.getResultMap(
						pageMap, "adBoxCountList", vList));
			}
			this.getReadyPlanList(request, "readyPlanList");
			request.setAttribute("adPlanId", this.getParameter("adPlanId"));
			request.setAttribute("beginTime", beginTime);
			request.setAttribute("endTime", endTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("history");
	}

	/**
	 * 待返金额
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward unbalance(ActionMapping mapping, ActionForm form,
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
			String beginTime = this.getParameter("beginTime");
			String endTime = this.getParameter("endTime");
			WebMaster webMaster = (WebMaster) request.getSession()
					.getAttribute("webmaster");
			int webMasterId = webMaster.getWebMasterId();

			String condition = " and r.webMasterId =:webMasterId and r.reportStatus = 1 ";
			Map<String, Object> parms = new HashMap<String, Object>();
			parms.put("webMasterId", webMasterId);
			if (!"".equals(this.getParameter("adPlanId"))) {
				condition += " and r.adplanId =:adPlanId";
				parms.put("adPlanId",
						Integer.parseInt(this.getParameter("adPlanId")));
			}
			if (!"".equals(this.getParameter("urlId"))) {
				condition += " and r.urlId =:urlId";
				parms.put("urlId", Integer.parseInt(this.getParameter("urlId")));
			}
			if ("".equals(this.getParameter("beginTime"))
					&& "".equals(this.getParameter("endTime"))) {
				condition += " and datediff(day,r.reportTime,getdate()) = 0";
				beginTime = DatetimeHandle.formatDate(new Date(),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				endTime = DatetimeHandle.formatDate(new Date(),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
			} else {
				if (!"".equals(this.getParameter("beginTime"))) {
					condition += " and r.reportTime >=:bt ";
					parms.put("bt", this.getParameter("beginTime"));
				}
				if (!"".equals(this.getParameter("endTime"))) {
					condition += " and r.reportTime <= :et";
					parms.put("et", this.getParameter("endTime") + " 23:59:59");
				}
			}
			Map<String, Integer> pageMap = PagingHandle.getPagingParams(
					pageNum, numPerPage,
					dao.getCount(ReportCount.class, condition, parms));

			String sqlSum = "select {r.*},u.url url from ReportCount r left join url u on r.urlId = u.urlId  where 1 = 1 ";
			List<Object> cols = new ArrayList<Object>();
			cols.add(ReportCount.class);
			cols.add("url");
			List<Object[]> list = dao.findBySql(pageMap.get("first"),
					pageMap.get("size"), sqlSum + condition
							+ " order by r.reportTime desc ", parms, cols);
			List<ReportCount> rList = new ArrayList<ReportCount>();
			for (Object[] object : list) {
				ReportCount reportCount = (ReportCount) object[0];
				reportCount.setUrlName(object[1] + "");
				rList.add(reportCount);
			}
			if (list != null) {
				this.buildPageResult(request, PagingHandle.getResultMap(
						pageMap, "reportCountList", rList));
			}
			int sumCount = 0;
			float sumMoney = 0;
			List<Object[]> sumList = dao
					.findBySql(
							"select sum(r.realCount) realcount,sum(r.realMoney) realMoney from ReportCount r where 1 = 1 "
									+ condition, parms);
			for (Object[] object : sumList) {
				if (null != object[0] && !"".equals(object[0])) {
					sumCount = Integer.parseInt(object[0] + "");
				}
				if (null != object[1] && !"".equals(object[1])) {
					sumMoney = Float.parseFloat(object[1] + "");
				}
			}
			request.setAttribute("sumCount", sumCount);
			request.setAttribute("sumMoney", sumMoney);

			this.getReadyPlanList(request, "readyPlanList");
			this.getUrlList(request, "urlList");
			request.setAttribute("adPlanId", this.getParameter("adPlanId"));
			request.setAttribute("urlId", this.getParameter("urlId"));
			request.setAttribute("beginTime", beginTime);
			request.setAttribute("endTime", endTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("unbalance");
	}

	/**
	 * 历史数据导出
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
			String fileName = "历史数据";
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			// URLDecoder.decode(fileName ,"utf-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel;charset=UTF-8");// 定义输出类型
			List<String> headerList = new ArrayList<String>();
			headerList.add("编号");
			headerList.add("创意");
			headerList.add("所属计划");
			headerList.add("域名");
			headerList.add("点击数");
			headerList.add("日期");

			int pageNum = 0;// 页次
			int numPerPage = 15;// 每页显示条数
			if (!"".equals(this.getParameter("pageNum"))) {
				// 分页
				pageNum = Integer.parseInt(this.getParameter("pageNum"));
				// numPerPage =
				// Integer.parseInt(this.getParameter("numPerPage"));
			}
			WebMaster webMaster = (WebMaster) request.getSession()
					.getAttribute("webmaster");
			int webMasterId = webMaster.getWebMasterId();

			String condition = " and v.webMasterId =:webMasterId  and v.isDefault = 0  ";
			Map<String, Object> parms = new HashMap<String, Object>();
			parms.put("webMasterId", webMasterId);
			if (!"".equals(this.getParameter("adPlanId"))) {
				condition += " and v.adPlanId =:adPlanId ";
				parms.put("adPlanId",
						Integer.parseInt(this.getParameter("adPlanId")));
			}
			if ("".equals(this.getParameter("beginTime"))
					&& "".equals(this.getParameter("endTime"))) {
				condition += " and datediff(day,v.adBoxCountTime,getdate()) = 0";
			} else {
				if (!"".equals(this.getParameter("beginTime"))) {
					condition += " and v.adBoxCountTime >=:bt ";
					parms.put("bt", this.getParameter("beginTime"));
				}
				if (!"".equals(this.getParameter("endTime"))) {
					condition += " and v.adBoxCountTime <= :et";
					parms.put("et", this.getParameter("endTime") + " 23:59:59");
				}
			}
//			Map<String, Integer> pageMap = PagingHandle.getPagingParams(
//					pageNum, numPerPage,
//					dao.getCount(ViewAdBoxCount.class, condition, parms));

			String sqlSum = "select v.*	from ViewAdBoxCount v  where 1=1 ";
			List<Object> cols = new ArrayList<Object>();
			cols.add(ViewAdBoxCount.class);
//			List<ViewAdBoxCount> list = dao.findBySql(pageMap.get("first"),
//					pageMap.get("size"), sqlSum + condition, parms, cols);
//			
			List<ViewAdBoxCount> list = dao.findBySql(sqlSum + condition, parms, cols);
			List<ViewAdBoxCount> vList = new ArrayList<ViewAdBoxCount>();
			for (ViewAdBoxCount count : list) {
				switch (count.getShowType()) {
				case 0:
					// pv
					count.setViewCols(count.getPv()
							* (100 - count.getDiscount()) / 100);
					break;
				case 1:
					// uv
					count.setViewCols(count.getUv()
							* (100 - count.getDiscount()) / 100);
					break;
				case 2:
					// ip
					count.setViewCols(count.getIp()
							* (100 - count.getDiscount()) / 100);
					break;
				}
				vList.add(count);
			}
			List<Object[]> datas = new ArrayList<Object[]>();
			DecimalFormat df = new DecimalFormat("##,###.###");
			int k = 1;
			for (ViewAdBoxCount adboxCount : vList) {
				Object[] object = new Object[6];
				object[0] = k;
				object[1] = adboxCount.getAdCreativeName();
				object[2] = adboxCount.getAdPlanName();
				object[3] = adboxCount.getUrl();
				object[4] = df.format(adboxCount.getViewCols());
				object[5] = DatetimeHandle.formatDate(DatetimeHandle
						.parseDate(adboxCount.getAdBoxCountTime()),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				datas.add(object);
				k++;
			}
			ExcelUtil.export(datas, headerList, fileName, os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebMaster webmaster = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		if (webmaster == null) {// 非法登录
			request.setAttribute("mes", "请重新登录!");
			return mapping.findForward("logout");
		}
		return super.execute(mapping, form, request, response);
	}
}
