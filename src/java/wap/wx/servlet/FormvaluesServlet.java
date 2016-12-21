/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wap.wx.dao.FormelementsDAO;
import wap.wx.dao.FormsDAO;
import wap.wx.dao.FormvaluesDAO;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class FormvaluesServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = null != request.getParameter("sid") ? request.getParameter("sid") : request.getParameter("sign");
        PageUtil<Map<Object, Object>> pu = new PageUtil<Map<Object, Object>>();
        List<Map<Object, Object>> formslist = new FormsDAO().getList();
        int totalcount = 0;
        for (Map form : formslist) {
            int subtotalcount = 0;
            List<Map> elementslist = new LinkedList<Map>();
            String[] es = ((String) form.get("eid")).split(" ");
            for (String eid : es) {
                Map element = new HashMap();
                if ("".equals(eid)) {
                    continue;
                }
                element.put("id", Integer.parseInt(eid.split(",")[0].trim()));
                element = new FormelementsDAO().getById(element);
                Map formvalues = new HashMap<Object, Object>();
                formvalues.put("fid", (Integer) form.get("id"));
                formvalues.put("eid", Integer.parseInt(eid.split(",")[0].trim()));
                formvalues.put("sid", Integer.parseInt(sid));
                List<Map<Object, Object>> formvalueslist = new FormvaluesDAO().getListValues(formvalues);
                element.put("list", formvalueslist);
                elementslist.add(element);
                subtotalcount = formvalueslist.size();
            }
            totalcount += subtotalcount;
            form.put("list", elementslist);
        }

//        for (Map form : formslist) {
//            for (Map element : (List<Map>) form.get("list")) {
//                System.out.println(element.get("name") + " ");
//                for (Map formvalue : (List<Map>) element.get("list")) {
//                    System.out.println(formvalue.get("valuess") + " ");
//                }
//            }
//        }
//        System.out.println("totalcount  " + totalcount);
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setSign(sid);
        pu.setMaxSize(totalcount);
        pu.setList(formslist);
        pu.setServletName("FormvaluesServlet");
        request.setAttribute("pu", pu);
        Forward.forward(request, response, "/back/subscriber/formslist.jsp");
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map formvalue = new HashMap();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String formfv : array) {
            formfv = !"".equals(formfv) ? formfv : "0--- ";
            int fid = Integer.parseInt(formfv.split("---")[0]);
            String fvkeyss = "";
            try {
                fvkeyss = formfv.split("---")[1];
            } catch (Exception e) {
                System.out.println("无相关openid(fvkeyss)!");
            }
            int sid = Integer.parseInt(request.getParameter("sign"));
            formvalue.put("fid", fid);
            formvalue.put("keyss", fvkeyss);
            formvalue.put("sid", sid);
            new FormvaluesDAO().delete(formvalue);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/FormvaluesServlet?method=getList&sid=" + request.getParameter("sign"));
    }
}
