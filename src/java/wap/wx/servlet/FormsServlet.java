/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.FormelementsDAO;
import wap.wx.dao.FormsDAO;
import wap.wx.menu.WxMenuUtils;
import wap.wx.util.Forward;

/**
 *
 * @author Administrator
 */
public class FormsServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<Object, Object>> list = new FormsDAO().getList();
        request.setAttribute("list", list);
        Forward.forward(request, response, "/back/forms/forms.jsp");
    }

    protected void initManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormsDAO formsDAO = new FormsDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Map forms = new HashMap<Object, Object>();
        forms.put("id", id);
        forms = formsDAO.getById(forms);
        request.setAttribute("forms", forms);
        List<Map<Object, Object>> list = new FormelementsDAO().getList();
        request.setAttribute("list", list);
        Forward.forward(request, response, "/back/forms/formsmanage.jsp?page=" + request.getParameter("page"));
    }

    protected void manage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormsDAO formsDAO = new FormsDAO();
        Map<Object, Object> formelements = new HashMap<Object, Object>();
        formelements.put("id", Integer.parseInt(request.getParameter("id")));
        formelements.put("name", request.getParameter("name"));
        formelements.put("eid", request.getParameter("eid"));
        formelements.put("ip", request.getRemoteAddr());
        formelements.put("times", WxMenuUtils.format.format(new Date()));
        formelements.put("aid", Integer.parseInt(((Map<String, String>) request.getSession().getAttribute("users")).get("id")));
        if (0 == (Integer) formelements.get("id")) {
            formsDAO.add(formelements);
        } else {
            formsDAO.update(formelements);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/FormsServlet?method=getList&page=" + request.getParameter("page"));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<Object, Object> enrol = new HashMap<Object, Object>();
        enrol.put("id", Integer.parseInt(request.getParameter("id")));
        new FormsDAO().delete(enrol);
        response.sendRedirect(request.getContextPath() + "/servlet/FormsServlet?method=getList&sid=" + request.getParameter("sid") + "&page=" + request.getParameter("page"));
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<Object, Object> enrol = new HashMap<Object, Object>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            enrol.put("id", !"".equals(id) ? Integer.parseInt(id) : 0);
            new FormsDAO().delete(enrol);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/FormsServlet?method=getList&sid=" + request.getParameter("sid") + "&page=" + request.getParameter("page"));
    }
}
