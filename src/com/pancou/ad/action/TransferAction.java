package com.pancou.ad.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;

public class TransferAction extends BaseDispatchAction {

	public ActionForward ggz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.getServiceList(request);
		return mapping.findForward("ggz");
	}

	public ActionForward wzz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.getServiceList(request);
		return mapping.findForward("wzz");
	}

	public ActionForward bzzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.getServiceList(request);
		return mapping.findForward("bzzx");
	}

	public ActionForward cus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.getServiceList(request);
		return mapping.findForward("cus");
	}

	public ActionForward ggms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// this.getServiceList(request);
		return mapping.findForward("ggms");
	}

}
