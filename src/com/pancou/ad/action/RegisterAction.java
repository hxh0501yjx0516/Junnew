package com.pancou.ad.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.MD5;
import com.pancou.ad.vo.WebMaster;

public class RegisterAction extends BaseDispatchAction {
	@SuppressWarnings("unchecked")
	public ActionForward validate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String[] pname = new String[1];
		String[] pvalue = new String[1];
		pname[0] = "webMasterName";
		pvalue[0] = this.getParameter("username");
		String result = "";
		List userslist = dao.findAll(WebMaster.class, pname, pvalue);
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

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 验证码
		String code = this.getParameter("randCode");
		String rand = "";
		Object randT = request.getSession().getAttribute("rand");
		if (randT != null)
			rand = randT.toString();
		else
			return mapping.findForward("register");
		if (!code.equals(rand)) {
			// 判断验证码是否一致
			request.setAttribute("mes", "验证码输入错误！");
			return mapping.findForward("register");
		}
		WebMaster webMaster = new WebMaster();
		webMaster.setWebMasterName(this.getParameter("username"));
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
		webMaster.setWebMasterBankNumber(this.getParameter("banknumber"));
		webMaster.setWebMasterStatus(0);// 默认为开通状态
		webMaster.setUserId(0);
		webMaster.setAddTime(DatetimeHandle.formatCurrentDate());
		int count = dao.save(webMaster);
		if (count > 0) {
			request.getSession().setAttribute("webmaster", webMaster);
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/login.do?action=login&jumpcode=0&type=0&username="
				+ webMaster.getWebMasterName() + "&userpwd="
				+ this.getParameter("password"));
		forward.setRedirect(true);
		return forward;
	}

	public ActionForward forward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("register");
	}
}
