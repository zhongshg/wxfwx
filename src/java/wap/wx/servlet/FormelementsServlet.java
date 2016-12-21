/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.FormelementsDAO;
import wap.wx.util.Forward;

/**
 *
 * @author Administrator
 */
public class FormelementsServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<Object, Object>> list = new FormelementsDAO().getList();
        request.setAttribute("list", list);
        Forward.forward(request, response, "/back/forms/formelements.jsp");
    }

    protected void initManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormelementsDAO formelementsDAO = new FormelementsDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Map formelements = new HashMap<Object, Object>();
        formelements.put("id", id);
        formelements = formelementsDAO.getById(formelements);
        request.setAttribute("formelements", formelements);
        request.setAttribute("keyss", UUID.randomUUID());
        Forward.forward(request, response, "/back/forms/formelementsmanage.jsp?page=" + request.getParameter("page"));
    }

    protected void manage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormelementsDAO formelementsDAO = new FormelementsDAO();
        Map<Object, Object> formelements = new HashMap<Object, Object>();
        formelements.put("id", Integer.parseInt(request.getParameter("id")));
        formelements.put("name", request.getParameter("name"));
        formelements.put("type", request.getParameter("type"));
        formelements.put("keyss", request.getParameter("keyss"));
        formelements.put("valuess", request.getParameter("valuess"));
        formelements.put("evaluess", request.getParameter("evaluess"));
        formelements.put("eplaceholder", request.getParameter("eplaceholder"));
        formelements.put("isempty", Integer.parseInt(request.getParameter("isempty")));
        formelements.put("isregular", Integer.parseInt(request.getParameter("isregular")));
        formelements.put("regular", request.getParameter("regular"));
        formelements.put("regulartext", request.getParameter("regulartext"));
        if (0 == (Integer) formelements.get("id")) {
            formelementsDAO.add(formelements);
        } else {
            formelementsDAO.update(formelements);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/FormelementsServlet?method=getList&page=" + request.getParameter("page"));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<Object, Object> enrol = new HashMap<Object, Object>();
        enrol.put("id", Integer.parseInt(request.getParameter("id")));
        new FormelementsDAO().delete(enrol);
        response.sendRedirect(request.getContextPath() + "/servlet/FormelementsServlet?method=getList&sid=" + request.getParameter("sid") + "&page=" + request.getParameter("page"));
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<Object, Object> enrol = new HashMap<Object, Object>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            enrol.put("id", !"".equals(id) ? Integer.parseInt(id) : 0);
            new FormelementsDAO().delete(enrol);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/FormelementsServlet?method=getList&sid=" + request.getParameter("sid") + "&page=" + request.getParameter("page"));
    }
}
