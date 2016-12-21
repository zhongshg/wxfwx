/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.EnrolDAO;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

public class EnrolServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = null != request.getParameter("sid") ? request.getParameter("sid") : request.getParameter("sign");
        EnrolDAO enrolDAO = new EnrolDAO();
        Map<String, String> enrol = new HashMap<String, String>();
        enrol.put("sid", sid);
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setSign(sid);
        pu.setMaxSize(enrolDAO.getCount(enrol));
        pu.setList(enrolDAO.getList(pu, enrol));
        pu.setServletName("EnrolServlet");
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/enrol/enrol.jsp");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> enrol = new HashMap<String, String>();
        enrol.put("id", request.getParameter("id"));
        new EnrolDAO().delete(enrol);
        response.sendRedirect(request.getContextPath() + "/servlet/EnrolServlet?method=getList&sid=" + request.getParameter("sid") + "&page=" + request.getParameter("page"));
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> enrol = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            enrol.put("id", !"".equals(id) ? id : "0");
            new EnrolDAO().delete(enrol);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/EnrolServlet?method=getList&sid=" + request.getParameter("sid") + "&page=" + request.getParameter("page"));
    }
}
