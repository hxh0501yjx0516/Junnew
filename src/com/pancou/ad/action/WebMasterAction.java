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

import com.pancou.ad.util.AlexaSeeker;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.ExcelUtil;
import com.pancou.ad.util.MD5;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.Pay;
import com.pancou.ad.vo.ReadyPlan;
import com.pancou.ad.vo.ReportCount;
import com.pancou.ad.vo.UrlAddress;
import com.pancou.ad.vo.Users;
import com.pancou.ad.vo.WebMaster;

public class WebMasterAction extends BaseDispatchAction {
	/**
	 *我的首页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myhome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebMaster webMaster = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		//查询站长媒介
		Users users = (Users) dao.findById(Users.class, webMaster.getUserId());
		request.setAttribute("medium", users);
		request.setAttribute("medium", users);
		//查询站长域名 0-待审核；1-审核通过；2-锁定
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("webMasterId", webMaster.getWebMasterId());
		int urlSum = dao.getCount(UrlAddress.class,
				"and u.webMasterId = :webMasterId ", parms);
		int urlOver = dao.getCount(UrlAddress.class,
				" and u.webMasterId = :webMasterId and u.urlStatus = 0", parms);
		int urlRun = dao.getCount(UrlAddress.class,
				" and u.webMasterId=:webMasterId and u.urlStatus = 1", parms);
		int urlLock = dao.getCount(UrlAddress.class,
				" and u.webMasterId=:webMasterId and u.urlStatus = 2", parms);
		//查询站长广告计划 0-审核通过；1-锁定
		int planSum = dao.getCount(ReadyPlan.class,
				"and r.webMasterId = :webMasterId ", parms);
		int planRun = dao.getCount(ReadyPlan.class,
				" and r.webMasterId = :webMasterId and r.readyPlanStatus = 0",
				parms);
		int planOver = dao.getCount(ReadyPlan.class,
				" and r.webMasterId=:webMasterId and r.readyPlanStatus = 1",
				parms);

		request.setAttribute("urlSum", urlSum);
		request.setAttribute("urlOver", urlOver);
		request.setAttribute("urlRun", urlRun);
		request.setAttribute("urlLock", urlLock);

		request.setAttribute("planSum", planSum);
		request.setAttribute("planRun", planRun);
		request.setAttribute("planOver", planOver);
		return mapping.findForward("myhome");
	}

	/**
	 * 修改个人资料
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
		try {
			WebMaster webMaster = (WebMaster) request.getSession()
					.getAttribute("webmaster");
			if ("edit".equals(this.getParameter("flag"))) {
				// �޸�
				webMaster.setWebMasterPassWord(MD5.MD5Encode(this
						.getParameter("password")));
				webMaster.setWebMasterContactName(this.getParameter("contact"));
				webMaster.setWebMasterCard(this.getParameter("card"));
				webMaster.setWebMasterAddress(this.getParameter("address"));
				webMaster.setWebMasterPost(this.getParameter("post"));
				webMaster.setWebMasterMobile(this.getParameter("telephone"));
				webMaster.setWebMasterQQ(this.getParameter("qq"));
				webMaster.setWebMasterBank(this.getParameter("bank"));
				webMaster.setWebMasterBankName(this.getParameter("bankname"));
				webMaster.setWebMasterBankNumber(this
						.getParameter("banknumber"));
				boolean bool = dao.update(webMaster);
				if (bool) {
					request.setAttribute("msg", "修改成功！");
				} else {
					request.setAttribute("msg", "修改失败！");
				}
			}
			request.setAttribute("webMaster", webMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("mypage");
	}

	/**
	 *域名列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward urladdress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebMaster users = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		int pageNum = 0;// 页次
		int numPerPage = 15;//每页显示条数
		if (!"".equals(this.getParameter("pageNum"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
		}
		String condition = " and u.webMasterId = :webMasterId ";
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("webMasterId", users.getWebMasterId());
		if (!"".equals(this.getParameter("state"))) {
			condition += " and u.urlStatus = :urlStatus ";
			parms.put("urlStatus", Integer.parseInt(this.getParameter("state")));
		}
		List<Object> cols = new ArrayList<Object>();
		cols.add(UrlAddress.class);
		cols.add("adboxCount");
		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage, dao.getCount(UrlAddress.class, condition, parms));
		List<Object[]> urlList = dao
				.findBySql(
						pageMap.get("first"),
						pageMap.get("size"),
						"select u.*,adboxCount=(select count(adBoxId) from adBox where urlId = u.urlId) from url u where 1=1 "
								+ condition, parms, cols);
		List<UrlAddress> list = new ArrayList<UrlAddress>();
		for (Object[] object : urlList) {
			UrlAddress urlAddress = (UrlAddress) object[0];
			urlAddress.setAdBoxCount(Integer.parseInt(object[1] + ""));
			list.add(urlAddress);
		}
		this.buildPageResult(request,
				PagingHandle.getResultMap(pageMap, "urlList", list));
		return mapping.findForward("url");
	}

	/**
	 *收入查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward balance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebMaster users = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		int pageNum = 0;// 页次
		int numPerPage = 15;//每页显示条数
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		if (!"".equals(this.getParameter("pageNum"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
		}
		String beginTime = this.getParameter("beginTime");
		String endTime = this.getParameter("endTime");
		String sql = "select * from Pay p where 1=1 ";
		Map<String, Object> parms = new HashMap<String, Object>();
		String condition = " and p.webMasterId = :webMasterId ";
		parms.put("webMasterId", users.getWebMasterId());
		if (!"".equals(this.getParameter("adPlanId"))) {
			condition += " and p.adPlanId = :adPlanId ";
			parms.put("adPlanId",
					Integer.parseInt(this.getParameter("adPlanId")));
		}
		if ("".equals(this.getParameter("beginTime"))
				&& "".equals(this.getParameter("endTime"))) {
			condition += " and datediff(day,p.payAddTime,getdate()) = 0";
			beginTime = DatetimeHandle.formatDate(new Date(),
					DatetimeHandle.SHORT_TIME_FORMAT_STRING);
			endTime = DatetimeHandle.formatDate(new Date(),
					DatetimeHandle.SHORT_TIME_FORMAT_STRING);
		} else {
			if (!"".equals(this.getParameter("beginTime"))) {
				condition += " and p.payAddTime >= :beginTime ";
				parms.put("beginTime", this.getParameter("beginTime"));
			}
			if (!"".equals(this.getParameter("endTime"))) {
				condition += " and p.payAddTime <= :endTime";
				parms.put("endTime", this.getParameter("endTime") + " 23:59:59");
			}
		}
		List<Object> cols = new ArrayList<Object>();
		cols.add(Pay.class);
		pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,
				dao.getCount(Pay.class, condition, parms));
		List<Pay> payList = dao.findBySql(pageMap.get("first"),
				pageMap.get("size"),
				sql + condition + " order by p.payId desc", parms, cols);
		if (payList != null) {
			this.buildPageResult(request,
					PagingHandle.getResultMap(pageMap, "payList", payList));
		}
		this.getReadyPlanList(request, "readyPlanList");
		request.setAttribute("adPlanId", this.getParameter("adPlanId"));
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		return mapping.findForward("pay");
	}

	/**
	 *广告主数据报表导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward excel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			WebMaster users = (WebMaster) request.getSession().getAttribute(
					"webmaster");
			String fileName = "收入数据报表";
			OutputStream os = response.getOutputStream();//取得输出流
			response.reset();//清空输出流
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");//设定输出文件头
			response.setContentType("application/msexcel;charset=UTF-8");//定义输出类型
			List<String> headerList = new ArrayList<String>();
			headerList.add("编号");
			headerList.add("广告计划");
			headerList.add("结算数");
			headerList.add("结算佣金");
			headerList.add("结算周期");
			headerList.add("结算时间");
			String sql = "select * from Pay p where 1=1 ";
			Map<String, Object> parms = new HashMap<String, Object>();
			String condition = " and p.webMasterId = :webMasterId ";
			parms.put("webMasterId", users.getWebMasterId());
			if (!"".equals(this.getParameter("adPlanId"))) {
				condition += " and p.adPlanId = :adPlanId ";
				parms.put("adPlanId", this.getParameter("adPlanId"));
			}
			if ("".equals(this.getParameter("beginTime"))
					&& "".equals(this.getParameter("endTime"))) {
				condition += " and datediff(day,p.payAddTime,getdate()) = 0";
			} else {
				if (!"".equals(this.getParameter("beginTime"))) {
					condition += " and p.payAddTime >= :beginTime ";
					parms.put("beginTime", this.getParameter("beginTime"));
				}
				if (!"".equals(this.getParameter("endTime"))) {
					condition += " and p.payAddTime <= :endTime";
					parms.put("endTime", this.getParameter("endTime")
							+ " 23:59:59");
				}
			}
			List<Object> cols = new ArrayList<Object>();
			cols.add(Pay.class);
			List<Pay> payList = dao.findBySql(sql + condition
					+ " order by p.payId desc", parms, cols);
			List<Object[]> datas = new ArrayList<Object[]>();
			DecimalFormat df = new DecimalFormat("##,###.###");
			int k = 1;
			for (Pay pay : payList) {
				Object[] object = new Object[6];
				object[0] = k;
				object[1] = pay.getAdPlanName();
				object[2] = df.format(pay.getRealCount());
				object[3] = "￥" + df.format(pay.getTrueMoney());
				object[4] = DatetimeHandle.formatDate(
						DatetimeHandle.parseDate(pay.getPayBeginTime()),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING)
						+ "~"
						+ DatetimeHandle.formatDate(
								DatetimeHandle.parseDate(pay.getPayEndTime()),
								DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				object[5] = DatetimeHandle.formatDate(
						DatetimeHandle.parseDate(pay.getPayAddTime()),
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

	@SuppressWarnings("unchecked")
	public ActionForward balanceDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int pageNum = 0;// 页数
		int numPerPage = 10;// 每页显示条数
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		if (!"".equals(this.getParameter("pageNum"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
		}
		String sql = "from ReportCount r where 1=1 ";
		Map<String, Object> parms = new HashMap<String, Object>();
		String condition = " and r.payId=:payId ";
		parms.put("payId", Integer.parseInt(this.getParameter("payId")));
		pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,
				dao.getCount(ReportCount.class, condition, parms));
		List<ReportCount> reportList = dao.findAll(pageMap.get("first"),
				pageMap.get("size"), sql, condition, parms);
		if (reportList != null) {
			this.buildPageResult(request, PagingHandle.getResultMap(pageMap,
					"reportList", reportList));
		}
		request.setAttribute("payId", this.getParameter("payId"));
		return mapping.findForward("details");
	}

	public ActionForward addUrladdress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebMaster users = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		if ("save".equals(this.getParameter("flag"))) {
			String webadd = this.getParameter("url");
			if (!"".equals(webadd)) {
				if (webadd.startsWith("www.")) {
					webadd = webadd.replaceFirst("www.", "");
				} else if (webadd.startsWith("http://www.")) {
					webadd = webadd.replaceFirst("http://www.", "");
				} else if (webadd.startsWith("http://")) {
					webadd = webadd.replaceFirst("http://", "");
				}
			}
			UrlAddress urlAddress = new UrlAddress();
			urlAddress.setWebMasterId(users.getWebMasterId());
			urlAddress.setWebMasterName(users.getWebMasterName());
			urlAddress.setUrlName(this.getParameter("urlName"));
			urlAddress.setUrl(webadd);
			urlAddress.setUrlICP(this.getParameter("urlIcp"));
			urlAddress.setUrlDayIp(this.getParameter("urlDayIp"));

			AlexaSeeker alexaSeeker = new AlexaSeeker();
			String alexa = alexaSeeker.getValues("POPULARITY", "TEXT",
					this.getParameter("url"));
			if (alexa != null && !"".equals(alexa)) {
				urlAddress.setUrlAlexa(Integer.parseInt(alexa));
			} else {
				urlAddress.setUrlAlexa(0);
			}
			urlAddress.setUrlArea(this.getParameter("urlArea"));
			urlAddress.setUrlTypeId(Integer.parseInt(this
					.getParameter("urlTypeId")));
			urlAddress.setUrlTypeName(this.getParameter("urlTypeName"));
			urlAddress.setUrlStatus(0);
			urlAddress.setAddTime(DatetimeHandle.formatCurrentDate());
			if (dao.save(urlAddress) > 0) {
				ActionForward forward = new ActionForward();
				forward.setPath("/webmaster.do?action=urladdress");
				forward.setRedirect(true);
				return forward;
			} else {
				request.setAttribute("msg", "添加域名失败");
				return mapping.findForward("addurl");
			}
		}
		return mapping.findForward("addurl");
	}

	public ActionForward validate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String webadd = this.getParameter("url");
		if (!"".equals(webadd)) {
			if (webadd.startsWith("www.")) {
				webadd = webadd.replaceFirst("www.", "");
			} else if (webadd.startsWith("http://www.")) {
				webadd = webadd.replaceFirst("http://www.", "");
			} else if (webadd.startsWith("http://")) {
				webadd = webadd.replaceFirst("http://", "");
			}
		}
		String[] pname = new String[1];
		String[] pvalue = new String[1];
		pname[0] = "url";
		pvalue[0] = webadd;
		String result = "";
		List userslist = dao.findAll(UrlAddress.class, pname, pvalue);
		if (userslist != null && userslist.size() > 0) {
			// 该用户名已存在
			result = this.createJsonResult("faild");
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
		WebMaster users = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		if (users == null) {// 非法登陆
			request.setAttribute("mes", "请重新登录!");
			return mapping.findForward("relogin");
		}
		return super.execute(mapping, form, request, response);
	}
}
