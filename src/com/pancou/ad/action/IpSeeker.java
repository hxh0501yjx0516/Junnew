package com.pancou.ad.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pancou.ad.util.NetWorkUtil;
import com.pancou.ad.util.ipseeker.IPSeeker;

@SuppressWarnings("serial")
public class IpSeeker extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Ip interface</title></head><body>");

		String clientIp = "";
		if (!"".equals(request.getParameter("ip"))
				&& request.getParameter("ip") != null) {
			clientIp = request.getParameter("ip");
		} else {
			clientIp = NetWorkUtil.getRemoteIP(request);
		}

		String datName = "ip.dat";
		String datPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "dat";

		IPSeeker ips = new IPSeeker(datName, datPath);
		String country = ips.getCountry(clientIp);
		String area = ips.getArea(clientIp);

		if (!"".equals(request.getParameter("ip"))) {
			out.println("查询：" + clientIp + "</br>");
			out.println("属地：" + country + "，" + area + "</br>");
		} else {
			out.println("当前：" + clientIp + "</br>");
			out.println("属地：" + country + "，" + area + "</br>");
		}

		out.println("</body></html>");
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
