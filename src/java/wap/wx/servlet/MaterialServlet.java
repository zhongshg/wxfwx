/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import wap.wx.dao.ItemsmDAO;
import wap.wx.dao.MaterialDAO;
import wap.wx.dao.NewsmDAO;
import wap.wx.menu.WxMenuUtils;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class MaterialServlet extends BaseServlet {

    protected void getList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MaterialDAO materialDAO = new MaterialDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        pu.setMaxSize(materialDAO.getCount());
        pu.setList(materialDAO.getList(pu));
        pu.setServletName("MaterialServlet");
        request.setAttribute("pu", pu);
        request.setAttribute("now", WxMenuUtils.format.format(new Date()));
        Forward.forward(request, response, "/back/message/material.jsp");
    }

    protected void initManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //上传素材管理不能修改
        MaterialDAO materialDAO = new MaterialDAO();
        String id = request.getParameter("id");
        Map<String, String> material = new HashMap<String, String>();
        material.put("id", id);
        material = materialDAO.getById(material);
        request.setAttribute("material", material);
        Forward.forward(request, response, "/back/message/materialmanage.jsp?page=" + request.getParameter("page"));
    }

    protected void initMsgManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MaterialDAO materialDAO = new MaterialDAO();
        String id = request.getParameter("id");
        Map<String, String> material = new HashMap<String, String>();
        material.put("id", id);
        material = materialDAO.getById(material);
        request.setAttribute("material", material);
        List<Map<String, String>> materialList = materialDAO.getList();
        request.setAttribute("materialList", materialList);
        Forward.forward(request, response, "/back/message/materialmsgmanage.jsp?page=" + request.getParameter("page"));
    }

    protected void initMassMsgManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MaterialDAO materialDAO = new MaterialDAO();
        String id = request.getParameter("id");
        Map<String, String> material = new HashMap<String, String>();
        material.put("id", id);
        material = materialDAO.getById(material);
        request.setAttribute("material", material);
        Forward.forward(request, response, "/back/message/materialmassmsgmanage.jsp?page=" + request.getParameter("page"));
    }

    //id,name,type,mediaid,times,ip,userid material
    protected void manage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MaterialDAO materialDAO = new MaterialDAO();
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", request.getParameter("id"));
        map.put("name", request.getParameter("name"));
        map.put("type", request.getParameter("type"));
        if ("music".equals(map.get("type"))) {
            map.put("mediaid", request.getParameter("mediaidmusic").split(",")[0]);
            map.put("times", request.getParameter("mediaidmusic").split(",")[1]);
        } else {
            map.put("mediaid", request.getParameter("mediaid").split(",")[0]);
            map.put("times", request.getParameter("mediaid").split(",")[1]);
        }
        map.put("title", request.getParameter("title"));
        map.put("description", request.getParameter("description"));
        map.put("musicurl", request.getParameter("musicurl"));
        map.put("hqmusicurl", request.getParameter("hqmusicurl"));
        map.put("ip", request.getRemoteAddr());
        map.put("userid", ((Map<String, String>) request.getSession().getAttribute("users")).get("id"));
        if ("0".equals(map.get("id"))) {
            materialDAO.add(map);
        } else {
            materialDAO.update(map);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/MaterialServlet?method=getList&page=" + request.getParameter("page"));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> enrol = new HashMap<String, String>();
        enrol.put("id", request.getParameter("id"));
        new MaterialDAO().delete(enrol);
        response.sendRedirect(request.getContextPath() + "/servlet/MaterialServlet?method=getList&page=" + request.getParameter("page"));
    }

    protected void delAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MaterialDAO materialDAO = new MaterialDAO();
        Map<String, String> enrol = new HashMap<String, String>();
        String ids = request.getParameter("ids");
        String array[] = ids.split(",");
        for (String id : array) {
            enrol.put("id", !"".equals(id) ? id : "0");
            materialDAO.delete(enrol);
        }
        response.sendRedirect(request.getContextPath() + "/servlet/MaterialServlet?method=getList&page=" + request.getParameter("page"));
    }

    protected void uploadmassmaterialinit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, String>> newsmList = new NewsmDAO().getList();
        request.setAttribute("newsmList", newsmList);
        Forward.forward(request, response, "/back/message/uploadmassmaterial.jsp");
    }

    protected void uploadmassmaterial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String newsmid = request.getParameter("newsm");
        Map<String, String> newsm = new HashMap<String, String>();
        newsm.put("id", newsmid);
        ItemsmDAO itemsmDAO = new ItemsmDAO();
        List<Map<String, String>> itemsmList = itemsmDAO.getByNewsmList(newsm);

        //上传多媒体文件（判断media是否有效）
        String mediaid = "";
        //上传图文消息
        String mediastr = "{"
                + "   \"articles\": [";
        int i = 0;
        for (Map<String, String> itemsm : itemsmList) {
            i++;
            String mediatimes = itemsm.get("mediatimes");
            long mediatimeslong = 0;
            try {
                if (null == mediatimes) {
                    mediatimes = "2015-01-01 00:00:00";
                }
                mediatimeslong = WxMenuUtils.format.parse(mediatimes).getTime();
            } catch (ParseException ex) {
                Logger.getLogger(MaterialServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //判断有无失效mediaid
            if (258600 * 1000 < System.currentTimeMillis() - mediatimeslong) {//mediaid超时，重新上传多媒体
                out.print("{\"msg\":\"invalid\"}");
                out.close();
                return;
            } else {//mediaid可用
                mediaid = itemsm.get("mediaid");
            }

            if (1 != i) {
                mediastr += ",";
            }
            mediastr += "{"
                    + "                        \"thumb_media_id\":\"" + mediaid + "\","
                    + "                        \"author\":\"\","
                    + "			 \"title\":\"" + itemsm.get("name") + "\","
                    + "			 \"content_source_url\":\"" + itemsm.get("url") + "\","
                    + "			 \"content\":\"" + itemsm.get("describes") + "\","
                    + "			 \"digest\":\"" + itemsm.get("describes") + "\","
                    + "                        \"show_cover_pic\":\"1\""
                    + "		 }";
        }
        mediastr += " ]"
                + "}";
        System.out.println("mediastr  " + mediastr);
        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=" + WxMenuUtils.getAccessToken());
        httpPost.setEntity(new StringEntity(mediastr, "UTF-8"));
        HttpResponse responses = WxMenuUtils.httpclient.execute(httpPost);
        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
//                System.out.println(jsonStr);
        String media_id = "";
        try {
            JSONObject object = JSON.parseObject(jsonStr);
            media_id = object.getString("media_id");
            System.out.println("图文id  " + media_id);
            long created_at = object.getLong("created_at") * 1000;
            System.out.println("图文created_at  " + created_at);

            out.print("{\"msg\":\"success\",\"type\":\"" + object.getString("type") + "\",\"mediaid\":\"" + media_id + "\",\"times\":\"" + WxMenuUtils.format.format(new Date(created_at)) + "\"}");
            out.close();
        } catch (Exception e) {
            out.print("{\"msg\":\"upfalse\"}");
            out.close();
        }
    }
}
