package com.pancou.ad.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.dao.PlatDao;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.MD5;
import com.pancou.ad.vo.Customer;
import com.pancou.ad.vo.WebMaster;

public class LoginAction extends BaseDispatchAction {
	PlatDao dao = new PlatDao();

	@SuppressWarnings("unchecked")
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebMaster wm = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		Customer ct = (Customer) request.getSession().getAttribute("customer");
		// 登陆类型
		String loginType = this.getParameter("type");
		String username = this.getParameter("username");
		String userpwd = this.getParameter("userpwd");

		loginType = "".equals(loginType) ? wm == null ? "1" : "2" : loginType;
		username = "".equals(username) ? wm == null ? ct == null ? "" : ct
				.getCustomerName() : wm.getWebMasterName() : username;
		userpwd = "".equals(userpwd) ? wm == null ? ct == null ? "" : ct
				.getCustomerPassword() : wm.getWebMasterPassWord() : MD5
				.MD5Encode(userpwd);

		request.setAttribute("loginType", loginType);
		// 验证码
		// if("".equals(this.getParameter("jumpcode"))){
		if (null == wm && null == ct) {
			String code = this.getParameter("randCode");
			String rand = "";
			Object randT = request.getSession().getAttribute("rand");
			if (randT != null)
				rand = randT.toString();
			else
				return mapping.findForward("logout");
			if (!code.equals(rand)) {
				// 判断验证码是否一致
				request.setAttribute("username", username);
				request.setAttribute("userpwd", this.getParameter("userpwd"));
				request.setAttribute("mes", "验证码输入错误！");
				return mapping.findForward("logout");
			}
		}
		// }
		String pwd = userpwd;
		if (loginType.equals("1")) {
			// 广告主登陆
			String[] puser = new String[2];
			puser[0] = "customerName";
			puser[1] = "customerStatus";
			String[] puv = new String[2];
			puv[0] = username;
			puv[1] = "0";
			// 根据用户名查userid
			List<Customer> userlist = dao.findAll(Customer.class, puser, puv);
			if (userlist.size() > 0) {
				if (!pwd.equals(userlist.get(0).getCustomerPassword())) {
					// 判断用户名和密码是否一致
					request.setAttribute("mes", "密码错误！");
					request.setAttribute("username", username);
					return mapping.findForward("logout");
				}
			} else {
				// 该用户名不存在
				request.setAttribute("mes", "该用户名不存在!");
				request.setAttribute("username", username);
				return mapping.findForward("logout");
			}
			request.getSession().setAttribute("customer", userlist.get(0));
			return mapping.findForward("customer");
		} else {
			// 网站主登陆
			String[] puser = new String[2];
			puser[0] = "webMasterName";
			puser[1] = "webMasterStatus";
			String[] puv = new String[2];
			puv[0] = username;
			puv[1] = "0";
			// 根据用户名查userid
			List<WebMaster> userlist = dao.findAll(WebMaster.class, puser, puv);
			if (userlist.size() > 0) {
				if (!pwd.equals(userlist.get(0).getWebMasterPassWord())) {
					// 判断用户名和密码是否一致
					request.setAttribute("mes", "密码错误！");
					request.setAttribute("username", username);
					return mapping.findForward("logout");
				}
			} else {
				// 该用户名不存在
				request.setAttribute("mes", "该用户名不存在!");
				request.setAttribute("username", username);
				return mapping.findForward("logout");
			}
			request.getSession().setAttribute("webmaster", userlist.get(0));
			return mapping.findForward("webmaster");
		}

	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		@SuppressWarnings("unused")
		Customer customer = (Customer) request.getSession().getAttribute(
				"customer");
		@SuppressWarnings("unused")
		WebMaster webmaster = (WebMaster) request.getSession().getAttribute(
				"webmaster");
		// if(customer==null && webmaster == null) {//非法登录
		// request.setAttribute("mes", "请重新登录!");
		// return mapping.findForward("logout") ;
		// }
		return super.execute(mapping, form, request, response);
	}
}
