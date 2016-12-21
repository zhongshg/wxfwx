/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.SaleMemberDAO;
import wap.wx.dao.SubscriberpromoteDAO;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class SaleMemberServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String openid = request.getParameter("openid");
        String issm = request.getParameter("issm");
        String subscriberdate = request.getParameter("subscriberdate");
        openid = null != openid ? openid : "";
        issm = null != issm ? issm : "";
        subscriberdate = null != subscriberdate ? subscriberdate : "";

        request.setAttribute("openid", openid);
        request.setAttribute("issm", issm);
        request.setAttribute("subscriberdate", subscriberdate);

        SaleMemberDAO saleMemberDAO = new SaleMemberDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(saleMemberDAO.getCount(openid, issm));
        List<Map<String, String>> saleMemberList = saleMemberDAO.getList(pu, openid, issm);
        for (Map<String, String> saleMember : saleMemberList) {
            //推广关注人数
            int subscriberpromote = new SubscriberpromoteDAO().getCount(saleMember.get("openid"), "0", subscriberdate) + new SubscriberpromoteDAO().getCount(saleMember.get("openid"), "1", subscriberdate);
            //推广取消关注人员
            int nosubscriberpromote = new SubscriberpromoteDAO().getCount(saleMember.get("openid"), "1", subscriberdate);
            saleMember.put("subscriberpromote", String.valueOf(subscriberpromote));
            saleMember.put("nosubscriberpromote", String.valueOf(nosubscriberpromote));
        }

        pu.setList(saleMemberList);
        pu.setServletName("SaleMemberServlet");
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/saleMember/index.jsp");
    }

    protected void issm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openid = request.getParameter("openid");
        String issm = request.getParameter("issm");
        new SaleMemberDAO().updateIssm(openid, issm);
        response.sendRedirect(request.getContextPath() + "/servlet/SaleMemberServlet?method=getList&page=" + request.getParameter("page"));
    }

    protected void subscriberdetaillist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openid = request.getParameter("openid");
        String subscriberdate = request.getParameter("subscriberdate");
        request.setAttribute("openid", openid);
        request.setAttribute("subscriberdate", subscriberdate);
        SubscriberpromoteDAO subscriberpromoteDAO = new SubscriberpromoteDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(subscriberpromoteDAO.getCount(openid, "", subscriberdate));
        pu.setList(subscriberpromoteDAO.getList(pu, openid, subscriberdate));
        pu.setServletName("SaleMemberServlet?method=subscriberdetaillist");
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/saleMember/detail.jsp");
    }
}
