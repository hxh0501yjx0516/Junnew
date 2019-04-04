package com.pancou.ad.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;

public class LogoutAction extends BaseDispatchAction{
	public ActionForward webmaster(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			request.getSession().setAttribute("webmaster", null);
			request.getSession().removeAttribute("webmaster");
	 		return mapping.findForward("out");
		}
	public ActionForward customer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			request.getSession().setAttribute("customer", null);
			request.getSession().removeAttribute("customer");
	 		return mapping.findForward("out");
		}
}
