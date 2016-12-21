/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.RedtypeDAO;
import wap.wx.dao.SendredpackDAO;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class RedtypeServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RedtypeDAO redtypeDAO = new RedtypeDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(redtypeDAO.getConut());
        pu.setList(redtypeDAO.getList(pu));
        pu.setServletName("RedtypeServlet");
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/sendredpack/redtype.jsp");
    }

    protected void initManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RedtypeDAO redtypeDAO = new RedtypeDAO();
        String id = request.getParameter("id");
        Map<String, String> activity = new HashMap<String, String>();
        activity.put("id", id);
        activity = redtypeDAO.getById(activity);
        request.setAttribute("activity", activity);
        List<Map<String, String>> sendredpackList = new SendredpackDAO().getList();
        request.setAttribute("sendredpackList", sendredpackList);
        Forward.forward(request, response, "/back/sendredpack/redtypemanage.jsp?page=" + request.getParameter("page"));
    }

    protected void manage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RedtypeDAO redtypeDAO = new RedtypeDAO();
        Map<String, String> activity = new HashMap<String, String>();
//        id,name,num,content,type,remark,mch_billno redtype
        activity.put("id", request.getParameter("id"));
        activity.put("name", request.getParameter("name"));
        activity.put("type", request.getParameter("type"));
        activity.put("num", request.getParameter("num"));
        activity.put("content", request.getParameter("content"));
        activity.put("remark", request.getParameter("remark"));
        activity.put("mch_billno", request.getParameter("mch_billno"));
        if ("0".equals(activity.get("id"))) {
            redtypeDAO.add(activity);
        } else {
            redtypeDAO.update(activity);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/RedtypeServlet?method=getList&page=" + request.getParameter("page"));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> activity = new HashMap<String, String>();
        activity.put("id", request.getParameter("id"));
        //删除图片
//        activity = new SendredpackDAO().getById(activity);
//        if (!"".equals(activity.get("img")) && null != activity.get("img")) {
//            java.io.File oldFile = new java.io.File(this.getServletContext()
//                    .getRealPath(activity.get("img")));
//            oldFile.delete();
//        }
        new RedtypeDAO().delete(activity);
        response.sendRedirect(request.getContextPath() + "/servlet/RedtypeServlet?method=getList");
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> activity = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            activity.put("id", !"".equals(id) ? id : "0");
//            activity = new SendredpackDAO().getById(activity);
//            if (!"".equals(activity.get("img")) && null != activity.get("img")) {
//                java.io.File oldFile = new java.io.File(this.getServletContext()
//                        .getRealPath(activity.get("img")));
//                oldFile.delete();
//            }
            new RedtypeDAO().delete(activity);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/RedtypeServlet?method=getList");
    }
}
