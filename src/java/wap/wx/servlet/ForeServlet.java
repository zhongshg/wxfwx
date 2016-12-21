/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import job.tot.bean.DataField;
import job.tot.dao.DaoFactory;
import job.tot.exception.DatabaseException;
import job.tot.exception.ObjectNotFoundException;
import job.tot.global.Sysconfig;
import job.tot.util.RequestUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import wap.wx.dao.ActivityDAO;
import wap.wx.dao.DiscussDAO;
import wap.wx.dao.EnrolDAO;
import wap.wx.dao.FormelementsDAO;
import wap.wx.dao.FormsDAO;
import wap.wx.dao.FormvaluesDAO;
import wap.wx.dao.ForumDAO;
import wap.wx.dao.GatItemsDAO;
import wap.wx.dao.NewsDAO;
import wap.wx.dao.NewstypesDAO;
import wap.wx.dao.OpenprizeDAO;
import wap.wx.dao.PrizeDAO;
import wap.wx.dao.RedtypeDAO;
import wap.wx.dao.SendredpackDAO;
import wap.wx.dao.SendredpacklogsDAO;
import wap.wx.dao.SubscriberDAO;
import wap.wx.dao.WxsDAO;
import wap.wx.menu.HttpClientConnectionManager;
import wap.wx.menu.WxJsApiUtils;
import wap.wx.menu.WxMenuUtils;
import wap.wx.menu.WxPayUtils;
import wap.wx.service.SubscriberService;
import wap.wx.util.Forward;
import wap.wx.util.PageUtil;
import wap.wx.util.ValidCrossDomain;
import wap.wx.util.WxReader;

/**
 *
 * @author Administrator
 */
public class ForeServlet extends BaseServlet {

    protected void newstypesliebiao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String parentid = request.getParameter("id");
        parentid = null != parentid ? parentid : "0";
        NewstypesDAO newstypesDAO = new NewstypesDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        List<Map<String, String>> list = newstypesDAO.getList(pu, sid, parentid);
        if (0 == list.size()) {//没有分类，进入新闻列表
            Forward.forward(request, response, "/ForeServlet?method=liebiao&sid=" + sid);
        } else {
            //判断下级有无分类列表
            for (Map<String, String> map : list) {
                int count = newstypesDAO.getConut(sid, map.get("id"));
                if (0 == count) {//无下级分类，页面进入新闻列表
                    map.put("sign", "0");
                } else {//继续下级分类
                    map.put("sign", "1");
                }
            }
            pu.setMaxSize(newstypesDAO.getConut(sid, parentid));
            pu.setList(list);
            pu.setServletName("ForeServlet?method=newstypesliebiao");
            request.setAttribute("pu", pu);
            request.setAttribute("sid", sid);
            request.setAttribute("parentid", parentid);
            Forward.forward(request, response, "/web/newstypesliebiao.jsp");
        }
    }

    protected void liebiao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String newstypesid = request.getParameter("id");
        newstypesid = null != newstypesid ? newstypesid : "0";
        NewsDAO newsDAO = new NewsDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        List<Map<String, String>> list = newsDAO.getList(pu, sid, newstypesid);
        if (0 == list.size()) {//无新闻列表 默认
            request.setAttribute("list", list);
            Forward.forward(request, response, "/web/liebiao.jsp");
        } else if (list.size() <= 1 && 1 == pu.getPage()) {//一条直接进详情
            Forward.forward(request, response, "/ForeServlet?method=xiangqing&id=" + (0 != list.size() ? list.get(0).get("id") : "0"));
        } else {
            pu.setMaxSize(newsDAO.getConut(sid, newstypesid));
            pu.setList(list);
            pu.setServletName("ForeServlet?method=liebiao");
            request.setAttribute("pu", pu);
            request.setAttribute("sid", sid);
            request.setAttribute("newstypesid", newstypesid);
            Forward.forward(request, response, "/web/liebiao.jsp");
        }
    }

    protected void newstypesboxliebiao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String parentid = request.getParameter("id");
        parentid = null != parentid ? parentid : "0";
        NewstypesDAO newstypesDAO = new NewstypesDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        List<Map<String, String>> list = newstypesDAO.getList(pu, sid, parentid);
        if (0 == list.size()) {//没有分类，进入新闻列表
            Forward.forward(request, response, "/ForeServlet?method=boxliebiao&sid=" + sid);
        } else {
            //判断下级有无分类列表
            for (Map<String, String> map : list) {
                int count = newstypesDAO.getConut(sid, map.get("id"));
                if (0 == count) {//无下级分类，页面进入新闻列表
                    map.put("sign", "0");
                } else {//继续下级分类
                    map.put("sign", "1");
                }
            }
            pu.setMaxSize(newstypesDAO.getConut(sid, parentid));
            pu.setList(list);
            pu.setServletName("ForeServlet?method=newstypesboxliebiao");
            request.setAttribute("pu", pu);
            request.setAttribute("sid", sid);
            request.setAttribute("parentid", parentid);
            Forward.forward(request, response, "/web/newstypesboxliebiao.jsp");
        }
    }

    protected void boxliebiao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String newstypesid = request.getParameter("id");
        newstypesid = null != newstypesid ? newstypesid : "0";
        NewsDAO newsDAO = new NewsDAO();
        PageUtil<Map<String, String>> pu = new PageUtil<Map<String, String>>();
        pu.setPageSize(9);
        pu.setPage(null != request.getParameter("page") && !"null".equals(request.getParameter("page")) ? Integer.parseInt(request.getParameter("page")) : 1);
        List<Map<String, String>> list = newsDAO.getList(pu, sid, newstypesid);
        if (0 == list.size()) {//无新闻列表 默认
            request.setAttribute("list", list);
            Forward.forward(request, response, "/web/boxliebiao.jsp");
        } else if (list.size() <= 1 && 1 == pu.getPage()) {//一条直接进详情
            Forward.forward(request, response, "/ForeServlet?method=xiangqing&id=" + (0 != list.size() ? list.get(0).get("id") : "0"));
        } else {
            pu.setMaxSize(newsDAO.getConut(sid, newstypesid));
            pu.setList(list);
            pu.setServletName("ForeServlet?method=boxliebiao");
            request.setAttribute("pu", pu);
            request.setAttribute("sid", sid);
            request.setAttribute("newstypesid", newstypesid);
            Forward.forward(request, response, "/web/boxliebiao.jsp");
        }
    }

    protected void xiangqing(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NewsDAO newsDAO = new NewsDAO();
        String id = request.getParameter("id");
        Map<String, String> news = new HashMap<String, String>();
        news.put("id", id);
        //阅读量
        newsDAO.updateViewcounts(id);
        news = newsDAO.getById(news);
        request.setAttribute("map", news);
        Forward.forward(request, response, "/web/xiangqing.jsp");
    }

    //网页获取openid
    protected void enrol(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectUri = "http://wanxiangfangwx.cityapp360.com/ForeServlet?method=enrol_redirectUri&sid=" + request.getParameter("sid");
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxReader.getWxInfo().get("AppId") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    //回调url
    protected void enrol_redirectUri(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxReader.getWxInfo().get("AppId") + "&secret=" + WxReader.getWxInfo().get("AppSecret") + "&code=" + code + "&grant_type=authorization_code");
        HttpResponse responses = WxMenuUtils.httpclient.execute(get);
        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
        JSONObject object = JSON.parseObject(jsonStr);

        String openid = object.getString("openid");
        String sid = request.getParameter("sid");
        //id,name,sex,ages,phone,tel,email,education,specialty,comname,remark,times,openid,sid
        Map<String, String> testenrol = new EnrolDAO().getByOpenId(openid);

        request.setAttribute("sid", sid);
        request.setAttribute("openid", openid);
        request.setAttribute("testenrol", testenrol);
        request.setAttribute("now", WxMenuUtils.format.format(new Date()));
        Forward.forward(request, response, "/vote/enrol/index.jsp");
    }

    protected void enrol_do(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String openid = request.getParameter("openid");

        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String specialty = request.getParameter("specialty");
        String comname = request.getParameter("comname");
        String times = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //id,name,sex,ages,phone,tel,email,education,specialty,comname,remark,times,openid,sid
        Map<String, String> enrol = new HashMap<String, String>();
        enrol.put("name", name);
        enrol.put("sex", "0");// request.getParameter("sex"));
        enrol.put("ages", "0");//request.getParameter("ages"));
        enrol.put("phone", request.getParameter("phone"));
        enrol.put("tel", tel);
        enrol.put("email", request.getParameter("email"));
        enrol.put("education", request.getParameter("education"));
        enrol.put("specialty", specialty);
        enrol.put("comname", comname);
        enrol.put("remark", request.getParameter("remark"));
        enrol.put("times", times);
        enrol.put("openid", openid);
        enrol.put("sid", sid);
        PrintWriter out = response.getWriter();
        EnrolDAO enrolDAO = new EnrolDAO();
        Map<String, String> testenrol = enrolDAO.getByOpenId(openid);
        if (null == testenrol.get("id")) {
            try {
                new EnrolDAO().add(enrol);
                out.print("0");

                //附加 发送客服通知
                StringBuilder content = new StringBuilder("《我爱我家之社区厨房争霸赛》");
                content.append("\n您已通过微信成功报名，稍后客服人员将与您联系，感谢您的支持！");
                WxMenuUtils.sendCustomService(openid, content.toString());

                content = new StringBuilder("《我爱我家之社区厨房争霸赛》");
                content.append("\n参赛选手:" + name);
                content.append("\n手机号码:" + tel);
                content.append("\n社区名称:" + comname);
                content.append("\n参赛菜名:" + specialty);
                content.append("\n报名时间:" + times);
                content.append("\n该选手已通过微信成功报名，请客服人员与其联系，谢谢！");

                String wangle = "oSoAcuLZp1o4L7hCCXcRvtIBL1UA";
                WxMenuUtils.sendCustomService(wangle, content.toString());
            } catch (Exception e) {
                out.print("2");
            }
        } else {
            out.print("1");
        }
        out.close();
    }

    //网页获取openid
    protected void enrol2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectUri = "http://wanxiangfangwx.cityapp360.com/ForeServlet?method=enrol2_redirectUri&sid=" + request.getParameter("sid");
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxReader.getWxInfo().get("AppId") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    //回调url
    protected void enrol2_redirectUri(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxReader.getWxInfo().get("AppId") + "&secret=" + WxReader.getWxInfo().get("AppSecret") + "&code=" + code + "&grant_type=authorization_code");
        HttpResponse responses = WxMenuUtils.httpclient.execute(get);
        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
        JSONObject object = JSON.parseObject(jsonStr);

        String openid = object.getString("openid");
        String sid = request.getParameter("sid");
        //id,name,sex,ages,phone,tel,email,education,specialty,comname,remark,times,openid,sid
        Map<String, String> testenrol = new EnrolDAO().getByOpenId(openid);

        request.setAttribute("sid", sid);
        request.setAttribute("openid", openid);
        request.setAttribute("testenrol", testenrol);
        request.setAttribute("now", WxMenuUtils.format.format(new Date()));
        Forward.forward(request, response, "/vote2/enrol/index.jsp");
    }

    protected void enrol2_do(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String openid = request.getParameter("openid");

        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String education = request.getParameter("education");
        String specialty = request.getParameter("specialty");
        String sex = request.getParameter("sex");
        String times = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //id,name,sex,ages,phone,tel,email,education,specialty,comname,remark,times,openid,sid
        Map<String, String> enrol = new HashMap<String, String>();
        enrol.put("name", name);
        enrol.put("sex", sex);// request.getParameter("sex"));
        enrol.put("ages", "0");//request.getParameter("ages"));
        enrol.put("phone", request.getParameter("phone"));
        enrol.put("tel", tel);
        enrol.put("email", request.getParameter("email"));
        enrol.put("education", education);
        enrol.put("specialty", specialty);
        enrol.put("comname", "");
        enrol.put("remark", request.getParameter("remark"));
        enrol.put("times", times);
        enrol.put("openid", openid);
        enrol.put("sid", sid);
        PrintWriter out = response.getWriter();
        EnrolDAO enrolDAO = new EnrolDAO();
        Map<String, String> testenrol = enrolDAO.getByOpenId(openid);
        if (null == testenrol.get("id")) {
            try {
                new EnrolDAO().add(enrol);
                out.print("0");

                //附加 发送客服通知万巷坊携手红黄蓝亲子园---最炫“萌宝”评比大赛
                StringBuilder content = new StringBuilder("万巷坊携手红黄蓝亲子园\n---最炫“萌宝”亲子运动会");
                content.append("\n您已通过微信成功报名，稍后客服人员将与您联系，感谢您的支持！");
                WxMenuUtils.sendCustomService(openid, content.toString());

                content = new StringBuilder("万巷坊携手红黄蓝亲子园\n---最炫“萌宝”亲子运动会");
                content.append("\n姓名:" + name);
                content.append("\n性别:" + ("1".equals(sex) ? "女" : "男"));
                content.append("\n出生年月:" + education + "年" + specialty + "月");
                content.append("\n电话:" + tel);
                content.append("\n报名时间:" + times);
                content.append("\n该选手已通过微信成功报名，请客服人员与其联系，谢谢！");
                String wangle = "oSoAcuLZp1o4L7hCCXcRvtIBL1UA";
                WxMenuUtils.sendCustomService(wangle, content.toString());
            } catch (Exception e) {
                out.print("2");
            }
        } else {
            out.print("1");
        }
        out.close();
    }

    protected void forms(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String fid = request.getParameter("fid");
        String keyss = request.getParameter("openid");//注：该keyss为用户唯一标识即openid，formelements为元素id与name
        //id,fid,eid,keyss,valuess,sid formvalues
        Map forms = new HashMap<Object, Object>();
        forms.put("id", Integer.parseInt(fid));
        forms = new FormsDAO().getById(forms);
        String[] es = ((String) forms.get("eid")).split(" ");
        List<Map> list = new LinkedList<Map>();
        for (String eid : es) {
            Map element = new HashMap();
            if ("".equals(eid)) {
                continue;
            }
            element.put("id", Integer.parseInt(eid.split(",")[0].trim()));
            element = new FormelementsDAO().getById(element);
            Map formvalues = new HashMap<Object, Object>();
            formvalues.put("fid", Integer.parseInt(fid));
            formvalues.put("eid", Integer.parseInt(eid.split(",")[0].trim()));
            formvalues.put("keyss", keyss);
            formvalues.put("sid", Integer.parseInt(sid));
            formvalues = new FormvaluesDAO().getValues(formvalues);
            element.put("formvalues", null != formvalues ? formvalues.get("valuess") : "");
            list.add(element);
        }
//        System.out.println(list.size());
//        for (Map ele : list) {
//            System.out.println(ele.get("type") + "  " + ele.get("formvalues"));
//        }
        forms.put("list", list);
        request.setAttribute("sid", sid);
        request.setAttribute("forms", forms);
        request.setAttribute("keyss", keyss);
        request.setAttribute("now", WxMenuUtils.format.format(new Date()));
        Forward.forward(request, response, "/fore/forms/forms.jsp");
    }

    protected void forms_do(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String fid = request.getParameter("fid");
        String keyss = request.getParameter("keyss");
        //id,fid,eid,keyss,valuess,sid formvalues
        Map forms = new HashMap<Object, Object>();
        forms.put("id", Integer.parseInt(fid));
        forms = new FormsDAO().getById(forms);
        String[] es = ((String) forms.get("eid")).split(" ");
        List<Map> list = new LinkedList<Map>();
        for (String eid : es) {
            Map element = new HashMap();
            if ("".equals(eid)) {
                continue;
            }
            element.put("id", Integer.parseInt(eid.split(",")[0].trim()));
            element = new FormelementsDAO().getById(element);
            String valuess = "";
            if ("checkbox".equals(element.get("type"))) {
                String[] strs = request.getParameterValues((String) element.get("keyss"));
                for (String str : strs) {
                    valuess += (str + ",");
                }
            } else {
                valuess = request.getParameter((String) element.get("keyss"));
            }

            Map formvalues = new HashMap<Object, Object>();
            formvalues.put("fid", Integer.parseInt(fid));
            formvalues.put("eid", element.get("id"));
            formvalues.put("keyss", keyss);
            formvalues.put("valuess", valuess);
            formvalues.put("sid", Integer.parseInt(sid));
            FormvaluesDAO formvaluesDAO = new FormvaluesDAO();
            Map testformvalues = formvaluesDAO.getValues(formvalues);
            if (!"".equals(keyss) && null != testformvalues.get("id")) {
                formvaluesDAO.updateValues(formvalues);
            } else {
                formvaluesDAO.add(formvalues);
            }
        }
        response.sendRedirect(request.getContextPath() + "/ForeServlet?method=forms&sid=" + sid + "&fid=" + fid + "&openid=" + keyss);
    }

    /*
     * 微论坛专区
     */
    //微论坛列表
    protected void weiforumList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubscriberDAO subscriberDAO = new SubscriberDAO();
        String sid = request.getParameter("sid");

        //评论会员
        String openid = request.getParameter("openid");
        if (null == openid) {
            response.sendRedirect("http://m.tl-kj.com/tljs.asp");
        }
        Map<String, String> subscriber = new HashMap<String, String>();
        subscriber.put("openid", openid);
        subscriber = subscriberDAO.getByOpenid(subscriber);

        //注册验证
        if (null == subscriber.get("id") || null == subscriber.get("nickname") || "".equals(subscriber.get("nickname")) || null == subscriber.get("headimgurl") || "".equals(subscriber.get("headimgurl"))) {
            //获取用户信息
            //id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times
            subscriber.put("openid", openid);
            subscriber.put("percent", "0");
            subscriber.put("remark", "");
            subscriber.put("times", WxMenuUtils.format.format(new Date()));
            JSONObject object = WxMenuUtils.getUserInfo(openid);
            object = null != object ? object : new JSONObject();
            subscriber.put("nickname", object.getString("nickname"));
            subscriber.put("sex", null != object.getString("sex") ? object.getString("sex") : "0");
            subscriber.put("language", object.getString("language"));
            subscriber.put("city", object.getString("city"));
            subscriber.put("province", object.getString("province"));
            subscriber.put("country", object.getString("country"));
            subscriber.put("headimgurl", object.getString("headimgurl"));
            subscriber.put("unionid", object.getString("unionid"));
            try {
                if (null == subscriber.get("id")) {
                    subscriberDAO.add(subscriber);
                } else {
                    subscriberDAO.update(subscriber);
                }

            } catch (Exception e) {
                System.out.println("万巷坊用户关注nickname特殊字符,数据库存储error,存储空！");
                subscriber.put("nickname", "");
                if (null == subscriber.get("id")) {
                    subscriberDAO.add(subscriber);
                } else {
                    subscriberDAO.update(subscriber);
                }
            }
//            request.setAttribute("url", request.getRequestURL() + "?" + request.getQueryString());
//            Forward.forward(request, response, "/ForeServlet?method=register&openid=" + openid);
//            return;
        }

        //赞排名
        List praiseVipList = subscriberDAO.getVipGatItemsPraisecountList("0,4");

        //被评论话题及发布人列表（target）
        Map target = new HashMap();
        target.put("sid", sid);
        List targetList = new GatItemsDAO().getList(target);//话题列表
        Iterator targetIt = targetList.iterator();
        while (targetIt.hasNext()) {
            target = (Map) targetIt.next();
            target.put("isreply", "0");//评论/非回复
            target.put("discusscount", String.valueOf(new DiscussDAO().getConut(target)));//被评论总数
            Map vip = new HashMap();
            vip.put("openid", target.get("author"));
            vip = subscriberDAO.getByOpenid(vip);//发布人信息
            target.put("vipname", null != vip.get("nickname") ? (String) vip.get("nickname") : "万巷坊");
            target.put("vipimg", null != vip.get("headimgurl") ? (String) vip.get("headimgurl") : request.getContextPath() + "/web/weiforum/logo.jpg");
            target.put("vipcity", null != vip.get("city") ? (String) vip.get("city") : "济南");
            target.put("isv", null != vip.get("isv") ? (String) vip.get("isv") : "会员");

            //已评论会员列表 后加
            DiscussDAO discussDAO = new DiscussDAO();
            target.put("isreply", "0");
            List discussList = discussDAO.getByTargetList(target);
            Iterator discussIt = discussList.iterator();
            while (discussIt.hasNext()) {
                Map discuss = (Map) discussIt.next();

                Map avip = new HashMap();
                avip.put("openid", discuss.get("openid"));
                avip = subscriberDAO.getByOpenid(avip);//评论人信息

                discuss.put("vipname", null != avip.get("nickname") ? (String) avip.get("nickname") : "万巷坊");
                discuss.put("vipimg", null != avip.get("headimgurl") ? (String) avip.get("headimgurl") : request.getContextPath() + "/web/weiforum/logo.jpg");
                discuss.put("vipcity", null != avip.get("name") ? (String) avip.get("city") : "济南");
            }
            target.put("discussList", discussList);

            //判断是否点赞
            Map forum = new HashMap();
            forum.put("openid", openid);
            forum.put("target", target.get("id"));
            forum.put("sid", "1");//j20论坛与微论坛点赞为1，评论点赞为2
            forum = new ForumDAO().getByOT(forum);
            if (null == forum.get("id")) {//未点过赞
                target.put("isforum", "0");
            } else {
                target.put("isforum", "1");
            }
        }

        //话题总量
        Map obj = new HashMap();
        obj.put("sid", sid);
        obj.put("gatitemscounts", String.valueOf(new GatItemsDAO().getConut(obj)));

        //可以参加互动的人数
        int count = subscriberDAO.getCount();

        request.setAttribute("openid", openid);
        request.setAttribute("sid", sid);
        request.setAttribute("praiseVipList", praiseVipList);
        request.setAttribute("count", Integer.valueOf(count));
        request.setAttribute("obj", obj);
        request.setAttribute("targetList", targetList);
        Forward.forward(request, response, "/web/weiforum/index.jsp");
    }

    //微论坛
    protected void weiforum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubscriberDAO subscriberDAO = new SubscriberDAO();
        //被评论话题及发布人
        Map target = new HashMap();
        target.put("id", request.getParameter("id"));
        target = new GatItemsDAO().getById(target);
        target.put("isreply", "0");
        target.put("discusscount", String.valueOf(new DiscussDAO().getConut(target)));//被评论总数
        Map targetvip = new HashMap();
        targetvip.put("openid", target.get("author"));
        targetvip = subscriberDAO.getByOpenid(targetvip);//发布人信息
        //屏蔽平台话题
        if (null == targetvip.get("id")) {
            targetvip.put("name", null);
            targetvip.put("img", null);
            targetvip.put("city", null);
        }
        //注册验证
        if ("".equals(targetvip.get("nickname")) || "".equals(targetvip.get("headimgurl"))) {
            request.setAttribute("url", request.getRequestURL() + "?" + request.getQueryString());
            Forward.forward(request, response, "/ForeServlet?method=register&openid=" + target.get("author"));
            return;
        }

        targetvip.put("name", null != targetvip.get("nickname") ? (String) targetvip.get("nickname") : "万巷坊");
        targetvip.put("img", null != targetvip.get("headimgurl") ? (String) targetvip.get("headimgurl") : request.getContextPath() + "/web/weiforum/logo.jpg");
        targetvip.put("city", null != targetvip.get("name") ? (String) targetvip.get("city") : "济南");

        //评论会员
        String openid = request.getParameter("openid");
        Map vip = new HashMap();
        vip.put("openid", openid);
        vip = subscriberDAO.getByOpenid(vip);

        //判断是否点赞(话题)
        Map forum = new HashMap();
        forum.put("openid", openid);
        forum.put("target", target.get("id"));
        forum.put("sid", "1");//j20论坛与微论坛点赞为1，评论点赞为2
        forum = new ForumDAO().getByOT(forum);
        if (null == forum.get("id")) {//未点过赞
            target.put("isforum", "0");
        } else {
            target.put("isforum", "1");
        }

        //已评论会员列表及回复
        DiscussDAO discussDAO = new DiscussDAO();
        Map discussMap = new LinkedHashMap();
        target.put("isreply", "0");
        List discussList = discussDAO.getByTargetList(target);
        Iterator discussIt = discussList.iterator();
        while (discussIt.hasNext()) {
            Map discuss = (Map) discussIt.next();

            Map avip = new HashMap();
            avip.put("openid", discuss.get("openid"));
            avip = subscriberDAO.getByOpenid(avip);//评论人信息

            discuss.put("vipname", null != avip.get("nickname") ? (String) avip.get("nickname") : "万巷坊");
            discuss.put("vipimg", null != avip.get("headimgurl") ? (String) avip.get("headimgurl") : request.getContextPath() + "/web/weiforum/logo.jpg");
            discuss.put("vipcity", null != avip.get("city") ? (String) avip.get("city") : "济南");

            //判断是否点赞（评论）
            Map discussforum = new HashMap();
            discussforum.put("openid", openid);
            discussforum.put("target", discuss.get("id"));
            discussforum.put("sid", "2");//j20论坛与微论坛点赞为1，评论点赞为2
            discussforum = new ForumDAO().getByOT(discussforum);
            if (null == discussforum.get("id")) {//未点过赞
                discuss.put("isforum", "0");
            } else {
                discuss.put("isforum", "1");
            }

            discuss.put("isreply", "1");
            List discussReplyList = discussDAO.getByTargetReplyList(discuss);
            Iterator discussReplyIt = discussReplyList.iterator();
            while (discussReplyIt.hasNext()) {
                Map discussReply = (Map) discussReplyIt.next();

                Map areply = new HashMap();
                areply.put("openid", discussReply.get("openid"));
                areply = subscriberDAO.getByOpenid(areply);//回复人信息

                discussReply.put("vipname", null != areply.get("nickname") ? (String) areply.get("nickname") : "万巷坊");
                discussReply.put("vipimg", null != areply.get("headimgurl") ? (String) areply.get("headimgurl") : request.getContextPath() + "/web/weiforum/logo.jpg");
                discussReply.put("vipcity", null != areply.get("city") ? (String) areply.get("city") : "济南");
            }
            discussMap.put(discuss, discussReplyList);
        }

        request.setAttribute("openid", openid);//评论者openid
        request.setAttribute("target", target);
        request.setAttribute("targetvip", targetvip);
        request.setAttribute("vip", vip);
        request.setAttribute("discussMap", discussMap);
        Forward.forward(request, response, "/web/weiforum/detail.jsp");
    }

    protected void paihang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openid = request.getParameter("openid");
        String sid = request.getParameter("sid");

        //话题评论回复排行(人气)
        List vipList = new SubscriberDAO().getVipDiscussConutList(" 0,9 ");
        request.setAttribute("vipList", vipList);

        request.setAttribute("openid", request.getParameter("openid"));
        request.setAttribute("sid", request.getParameter("sid"));
        request.setAttribute("gatitemscounts", request.getParameter("gatitemscounts"));
        request.setAttribute("count", request.getParameter("count"));
        Forward.forward(request, response, "/web/weiforum/paihang.jsp");
    }

    //微话题发表初始化
    protected void weiforuminitadd_do(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String openid = request.getParameter("openid");
        String sid = request.getParameter("sid");
        Map vip = new HashMap();
        vip.put("openid", openid);
        vip = new SubscriberDAO().getByOpenid(vip);
        vip.put("sid", sid);
        request.setAttribute("vip", vip);
        Forward.forward(request, response, "/web/weiforum/add.jsp");
    }

    //微评论初始化
    protected void discussinitadd_do(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String openid = request.getParameter("openid");
        String sid = request.getParameter("sid");
        Map vip = new HashMap();
        vip.put("openid", openid);
        vip = new SubscriberDAO().getByOpenid(vip);
        vip.put("target", id);
        vip.put("sid", sid);
        request.setAttribute("vip", vip);
        Forward.forward(request, response, "/web/weiforum/discussadd.jsp");
    }

    //微话题发表
    protected void weiforumadd_do(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        GatItemsDAO gatItemsDAO = new GatItemsDAO();
        Map gatItems = new HashMap();
        gatItems.put("name", request.getParameter("name"));
        gatItems.put("brief", request.getParameter("brief"));
        gatItems.put("img", request.getParameter("img"));
        gatItems.put("describer", request.getParameter("describer"));
        gatItems.put("sid", request.getParameter("sid"));
        gatItems.put("orders", String.valueOf(gatItemsDAO.getMaxId()));
        gatItems.put("praisecount", "0");
        gatItems.put("author", request.getParameter("openid"));
        gatItems.put("times", WxMenuUtils.format.format(new Date()));
        gatItems.put("state", "0");
        gatItemsDAO.add(gatItems);
        out.print("0");
        out.close();
    }

    //点赞
    protected void pointcount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ForumDAO forumDAO = new ForumDAO();
        String target = request.getParameter("target");
        String openid = request.getParameter("openid");
        //id,openid,target,ip,times forum 赞表

        Map forum = new HashMap();
        forum.put("openid", openid);
        forum.put("target", target);
        forum.put("sid", request.getParameter("sid"));
        forum = new ForumDAO().getByOT(forum);
        if (null == forum.get("id")) {
            forum.put("ip", request.getRemoteAddr());
            forum.put("times", WxMenuUtils.format.format(new Date()));
            forumDAO.add(forum);

            Map targetVip = new HashMap();
            targetVip.put("id", target);
//            new VipDAO().updateCount(targetVip);//针对vip点赞（已作废）
            new GatItemsDAO().updatePraisecount(targetVip);
            out.print("0");
        } else {
            out.print("1");
        }
        out.close();
    }

    //评论点赞
    protected void discusspointcount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ForumDAO forumDAO = new ForumDAO();
        String target = request.getParameter("target");
        String openid = request.getParameter("openid");

        //id,openid,target,ip,times forum 赞表
        Map forum = new HashMap();
        forum.put("openid", openid);
        forum.put("target", target);
        forum.put("sid", request.getParameter("sid"));
        forum = new ForumDAO().getByOT(forum);
        if (null == forum.get("id")) {
            forum.put("ip", request.getRemoteAddr());
            forum.put("times", WxMenuUtils.format.format(new Date()));
            forumDAO.add(forum);

            Map targetVip = new HashMap();
            targetVip.put("id", target);
//            new VipDAO().updateCount(targetVip);//针对vip点赞（已作废）
            new DiscussDAO().updatePraisecount(targetVip);
            out.print("0");
        } else {
            out.print("1");
        }
        out.close();
    }

    //评论
    protected void discussadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        DiscussDAO discussDAO = new DiscussDAO();
        String target = request.getParameter("target");
        String openid = request.getParameter("openid");
        String discontent = request.getParameter("discontent");

        //id,openid,content,vipid,times discuss 评论表
        Map discuss = new HashMap();
        discuss.put("openid", openid);
        discuss.put("vipid", target);
        discuss.put("content", discontent);

        //        forum = new ForumDAO().getByOT(forum);//限制发表？
//        if (null == forum.get("id")) {
        String times = WxMenuUtils.format.format(new Date());
        discuss.put("times", times);
        discuss.put("isreply", request.getParameter("isreply"));
        discuss.put("praisecount", "0");
        discussDAO.add(discuss);

//        Map vip = new HashMap();
//        vip.put("openid", openid);
//        vip = new VipDAO().getByOpenid(vip);
//        }
        out.print("0");
        out.close();
    }

    /*
     * 微论坛结束
     */
    protected void roulette(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
//        if (ua.indexOf("micromessenger") > 0) {
        String openid = request.getParameter("openid");
        String id = request.getParameter("id");
        Map<String, String> activity = new HashMap<String, String>();
        activity.put("id", id);
        activity = new ActivityDAO().getById(activity);
        List<Map<String, String>> prizeList = new PrizeDAO().getByActivityList(activity);

        //临时 
        DataField vote = DaoFactory.getMysqlDao().getFirstData("select id,name,img,classsid,content,remark,starttime,endtime,sid from vote where id=" + id, "id,name,img,classsid,content,remark,starttime,endtime,sid");
        activity.put("name", vote.getFieldValue("name"));
        activity.put("starttime", "2015-10-15 00:00:00");
        activity.put("endtime", "2015-12-15 00:00:00");
        activity.put("state", "0");

        request.setAttribute("openid", openid);
        request.setAttribute("activity", activity);
        request.setAttribute("prizeList", prizeList);
        request.setAttribute("now", WxMenuUtils.format.format(new Date()));
        Forward.forward(request, response, "/fore/roulette/roulette.jsp");
//        } else {
//            response.sendRedirect("http://m.tl-kj.com/tljs.asp");
//        }
    }

    protected void roulette_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String openid = request.getParameter("openid");
        String aid = request.getParameter("aid");
        Map<String, String> activity = new HashMap<String, String>();
        activity.put("id", aid);
        activity = new ActivityDAO().getById(activity);
        List<Map<String, String>> prizeList = new PrizeDAO().getByActivityList(activity);
        int[] pa = new int[Integer.parseInt(activity.get("counts"))];
        int i = 0;
        for (Map<String, String> prize : prizeList) {
            for (int j = 0; j < Math.rint(Integer.parseInt(activity.get("counts")) * Double.parseDouble(prize.get("discounts"))); i++, j++) {
                pa[i] = Integer.parseInt(prize.get("id"));
            }
        }

//        for (i = 0; i < pa.length; i++) {
//            System.out.println(i + ":" + pa[i]);
//        }
        Random random = new Random();
        int rn = random.nextInt(pa.length);
        int pz = pa[rn];
        long sn = System.currentTimeMillis();
        String dates = WxMenuUtils.format.format(new Date());
        int expz = 0;
        OpenprizeDAO openprizeDAO = new OpenprizeDAO();
        Map<String, String> openprize = new HashMap<String, String>();
        openprize.put("openid", openid);
        openprize.put("state", "1");
        int takeNum = openprizeDAO.getTakeCount(activity, openprize);//抽奖次数
        int pointNum = openprizeDAO.getPointCount(activity, openprize);//中奖次数
        if (Integer.parseInt(activity.get("taketimes")) <= takeNum) {
            out.print("{\"error\":\"invalid\",\"success\":\"false\",\"prizetype\":0,\"sn\":0}");
        } else if (Integer.parseInt(activity.get("pointtimes")) <= pointNum) {
            out.print("{\"error\":\"getsn\",\"success\":\"false\",\"prizetype\":0,\"sn\":0}");
        } else {
            openprize.put("aid", aid);
            openprize.put("sn", String.valueOf(sn));
            openprize.put("tel", "");
            openprize.put("opendate", dates);
            openprize.put("name", "");
            openprize.put("sex", "0");
            openprize.put("sid", activity.get("sid"));
            openprize.put("isexp", "0");
            Map<String, String> prize = new HashMap<String, String>();
            prize.put("id", String.valueOf(pz));
            prize = new PrizeDAO().getById(prize);
            openprize.put("pid", prize.get("id"));
            int prizePointCount = openprizeDAO.getPrizePointCount(activity, openprize);//奖品中出数量
            if (null != prize.get("codeid") && prizePointCount < Integer.parseInt(prize.get("num"))) {
//                System.out.println(prize.get("codename"));
                openprize.put("pid", prize.get("id"));
                openprize.put("state", "1");
                openprizeDAO.add(openprize);
                out.print("{\"error\":\"point\",\"success\":\"true\",\"prizetype\":\"" + prize.get("codeid") + "\",\"sn\":\"" + sn + "\",\"codename\":\"" + prize.get("codename") + "\",\"pname\":\"" + prize.get("name") + "\"}");
            } else {
//                System.out.println(0);
                openprize.put("pid", "0");
                openprize.put("state", "0");
                openprizeDAO.add(openprize);
                out.print("{\"error\":\"nopoint\",\"success\":\"false\",\"prizetype\":\"\",\"sn\":\"0\",\"pname\":\"\"}");
            }
        }
    }

    protected void roulette_ok(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String sn = request.getParameter("code");
        String tel = request.getParameter("tel");
        Map<String, String> openprize = new HashMap<String, String>();
        openprize.put("sn", sn);
        openprize.put("tel", tel);
        openprize.put("name", "");
        openprize.put("sex", "0");
        new OpenprizeDAO().updateData(openprize);
        out.print("{\"success\":\"true\"}");
    }
    private final int takeCountLimit = 1;
    private final int pointCountLimit = 1;

    //网页获取openid
    protected void sendredpackcoupon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectUri = "http://wanxiangfangwx.cityapp360.com/ForeServlet?method=sendredpackcoupon_redirectUri&id=" + request.getParameter("id");
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxReader.getWxInfo().get("AppId") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    //回调url
    protected void sendredpackcoupon_redirectUri(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxReader.getWxInfo().get("AppId") + "&secret=" + WxReader.getWxInfo().get("AppSecret") + "&code=" + code + "&grant_type=authorization_code");
        HttpResponse responses = WxMenuUtils.httpclient.execute(get);
        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
        JSONObject object = JSON.parseObject(jsonStr);

        String openid = object.getString("openid");
//        String accessToken2 = object.getString("access_token");
//        get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken2 + "&openid=" + openid + "&lang=zh_CN");
//        responses = httpclient.execute(get);
//        jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
//        System.out.println("jsonStr  " + jsonStr);
//        object = JSON.parseObject(jsonStr);
//        System.out.println(object.getString("nickname"));
//    }
//
//    protected void sendredpackcoupon(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
        SendredpackDAO sendredpackDAO = new SendredpackDAO();
//        String openid = request.getParameter("openid");//用户openid
        String id = request.getParameter("id");//活动id
        String ip = request.getRemoteAddr();//ip
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map = sendredpackDAO.getById(map);
        map.put("ip", ip);
        SendredpacklogsDAO sendredpacklogsDAO = new SendredpacklogsDAO();

        int takeNum = sendredpacklogsDAO.getConut(map.get("mch_billno"), openid, map.get("starttime"), map.get("endtime"));
        int pointNum = sendredpacklogsDAO.getConut(map.get("mch_billno"), openid, 1, map.get("starttime"), map.get("endtime"));
        if (takeCountLimit <= takeNum) {
            request.setAttribute("message", "invalid");
        } else if (pointCountLimit <= pointNum) {
            request.setAttribute("message", "alpoint");
        } else {
            request.setAttribute("message", "success");
        }
        request.setAttribute("map", map);
        request.setAttribute("openid", openid);
        request.setAttribute("now", WxMenuUtils.format.format(new Date()));
        Forward.forward(request, response, "/fore/redpak/index.jsp");
    }

    protected void sendredpackcoupon_do(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        SendredpackDAO sendredpackDAO = new SendredpackDAO();
        String openid = request.getParameter("openid");//用户openid
        String id = request.getParameter("id");//红包批次id
        String ip = request.getRemoteAddr();//ip
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map = sendredpackDAO.getById(map);
        map.put("ip", ip);
        //判断个人次数
        SendredpacklogsDAO sendredpacklogsDAO = new SendredpacklogsDAO();
        int takeNum = sendredpacklogsDAO.getConut(map.get("mch_billno"), openid, map.get("starttime"), map.get("endtime"));
        int pointNum = sendredpacklogsDAO.getConut(map.get("mch_billno"), openid, 1, map.get("starttime"), map.get("endtime"));
        if (takeCountLimit <= takeNum || pointCountLimit <= pointNum) {
            out.print("{'message':'failopen','msg':'次数已用完，下次再来吧!'}");
            return;
        }

        //总中奖数量
        int prizePointTotalCount = sendredpacklogsDAO.getConut(map.get("mch_billno"), 1, map.get("starttime"), map.get("endtime"));

        int redtypemoney = 0;//最终金额
        String coupon = "";
        String remark = "";
        int state = 0;
        int prizeid = 0;

        //按类型划分
        if ("0".equals(map.get("type"))) {
            if (Integer.parseInt(map.get("num")) <= prizePointTotalCount) {
                out.print("{'message':'failopen','msg':'红包已抢完，下次再来吧!'}");
                out.close();
                return;
            }
            int money = Integer.parseInt(map.get("money")) / Integer.parseInt(map.get("num"));
            redtypemoney = money;
            state = 1;
        } else {

            //取出所有奖品
            List<Map<String, String>> redprizeList = new RedtypeDAO().getList(map.get("mch_billno"));
            int totalRedPrize = 0;//红包总数量
            //设置空奖专用
            totalRedPrize = Integer.parseInt(map.get("num"));
//            for (Map<String, String> redprize : redprizeList) {
//                totalRedPrize += Integer.parseInt(redprize.get("num"));
//            }

            int[] pa = new int[totalRedPrize];
            int i = 0;
            for (Map<String, String> redprize : redprizeList) {
                pa[i] = 0;
                for (int j = 0; j < Integer.parseInt(redprize.get("num")); i++, j++) {
                    pa[i] = Integer.parseInt(redprize.get("id"));
                }
            }

//            for (i = 0; i < pa.length; i++) {
//                System.out.println(i + ":" + pa[i]);
//            }

            if (pa.length <= prizePointTotalCount) {
                out.print("{'message':'failopen','msg':'红包已抢完，下次再来吧!'}");
                out.close();
                return;
            }

            Random random = new Random();
            int rn = random.nextInt(pa.length);
            int pz = pa[rn];
            long sn = System.currentTimeMillis();
            String dates = WxMenuUtils.format.format(new Date());
            int expz = 0;

            Map<String, String> redtypecoupon = new HashMap<String, String>();
            redtypecoupon.put("id", String.valueOf(pz));
            redtypecoupon = new RedtypeDAO().getById(redtypecoupon);
            int prizePointCount = sendredpacklogsDAO.getPrizePointConut(map.get("mch_billno"), redtypecoupon.get("id"), map.get("starttime"), map.get("endtime"));//奖品中出数量
            System.out.println("redtypecoupon.get(\"id\") " + redtypecoupon.get("id"));
            if (null != redtypecoupon.get("id") && 0 != Integer.parseInt(redtypecoupon.get("id")) && prizePointCount < Integer.parseInt(redtypecoupon.get("num"))) {
                System.out.println(redtypecoupon.get("id") + " " + redtypecoupon.get("content"));
                if ("1".equals(redtypecoupon.get("type"))) {
                    redtypemoney = Integer.parseInt(redtypecoupon.get("content"));
                } else if ("2".equals(redtypecoupon.get("type"))) {
                    coupon = redtypecoupon.get("content");
                } else {
                    remark = redtypecoupon.get("content");;
                }
                state = 1;
                prizeid = Integer.parseInt(redtypecoupon.get("id"));
            } else {
//                while (prizePointTotalCount < totalRedPrize) {
//                    System.out.println("循环 prizePointCount " + prizePointCount + " " + totalRedPrize);
//                    sendredpackcoupon_do(request, response);
//                }
                redtypemoney = 0;
                state = 0;
            }

        }

        //组织记录参数
        //            id,openid,mch_billno,nick_name,send_name,total_amount,wishing,client_ip,act_name,remark,send_listid,send_time sendredpacklogs
        Map<String, String> sendredpacklogs = new HashMap<String, String>();
        sendredpacklogs.put("openid", openid);
        sendredpacklogs.put("mch_billno", map.get("mch_billno"));
        sendredpacklogs.put("nick_name", map.get("nick_name"));
        sendredpacklogs.put("send_name", map.get("send_name"));
        sendredpacklogs.put("wishing", map.get("wishing"));
        sendredpacklogs.put("client_ip", request.getRemoteAddr());
        sendredpacklogs.put("act_name", map.get("name"));
        sendredpacklogs.put("remark", remark);
        sendredpacklogs.put("send_listid", "0");
        sendredpacklogs.put("send_time", "0");

        sendredpacklogs.put("total_amount", String.valueOf(redtypemoney));
        sendredpacklogs.put("state", String.valueOf(state));
        sendredpacklogs.put("prizeid", String.valueOf(prizeid));
        sendredpacklogs.put("logo_imgurl", map.get("img"));
        sendredpacklogs.put("times", WxMenuUtils.format.format(new Date()));

        sendredpacklogs.put("mark", map.get("send_name"));

        boolean flag = false;
        try {
            if (0 < redtypemoney) {
//            发送
                flag = new WxPayUtils().sendredpack(sendredpacklogs, openid);
            } else if (!"".equals(coupon)) {
//            发送
                String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
                //商户单据号
                String partner_trade_no = WxReader.getWxInfo().get("mch_id") + WxMenuUtils.formatd.format(new Date()) + String.valueOf(new Random().nextInt(10000)) + String.valueOf(new Random().nextInt(10000));//异步传输，异常查询使用

                //组织参数
                Map<String, String> send_coupon = new HashMap<String, String>();
                send_coupon.put("coupon_stock_id", coupon);
                send_coupon.put("openid_count", "1");//openid记录数，1
                send_coupon.put("partner_trade_no", partner_trade_no);
                send_coupon.put("openid", openid);
                send_coupon.put("appid", WxReader.getWxInfo().get("AppId"));//公众账号id
                send_coupon.put("mch_id", WxReader.getWxInfo().get("mch_id"));//商户号
                send_coupon.put("nonce_str", timeStamp);//随机字符串

//        map.put("op_user_id", WxReader.getWxInfo().get("mch_id"));//操作员 否
//        map.put("device_info", "WEB");//设备号 否
//        map.put("version", "1.0");//协议版本 否
//        map.put("type", "XML");//协议类型 否
                send_coupon = new WxPayUtils().send_coupon(send_coupon, WxReader.getWxInfo().get("key"));
                if ("SUCCESS".equals(map.get("result_code"))) {
                    sendredpacklogs.put("remark", coupon);
                    sendredpacklogs.put("isexp", "1");
                    sendredpacklogs.put("exptimes", WxMenuUtils.format.format(new Date()));
                    flag = sendredpacklogsDAO.add(sendredpacklogs);
                    System.out.println("代金券发放成功：" + " " + map.get("coupon_stock_id") + " " + map.get("coupon_id"));
                } else {
                    System.out.println("代金券发放失败：" + " " + map.get("coupon_stock_id") + " " + map.get("coupon_id"));
                }
            } else {
                sendredpacklogs.put("isexp", "0");
                sendredpacklogs.put("exptimes", WxMenuUtils.format.format(new Date()));
                flag = sendredpacklogsDAO.add(sendredpacklogs);
            }
            if (flag) {
                if (1 == state) {
                    if (0 < redtypemoney) {
                        out.print("{'message':'successopen','msg':'您手气真棒，获得" + ((float) Math.round(redtypemoney)) / 100 + "元红包！'}");
                    } else if (!"".equals(coupon)) {
                        out.print("{'message':'successopen','msg':'您手气真棒，获得一张代金券！'}");
                    } else {
                        if (!"".equals(remark)) {
                            out.print("{'message':'successopen','msg':'您手气真棒，恭喜您获得万巷坊提供的" + remark + "!'}");
                        } else {
                            out.print("{'message':'failopen','msg':'红包已抢完，下次再来吧!'}");
                        }
                    }
                } else {
                    out.print("{'message':'failopen','msg':'恭喜您抢到万巷坊提供的红包X5！'}");
                }
            } else {
                out.print("{'message':'failopen','msg':'系统繁忙，请稍后再试！'}");
            }
        } catch (Exception e) {
            out.print("{'message':'failopen','msg':'系统繁忙，请稍后再试！'}");
        }
        out.close();
    }

    protected void myredpack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openid = request.getParameter("openid");
        String mch_billno = request.getParameter("mch_billno");
        String sign = request.getParameter("sign");
        if ("1".equals(sign)) {
            List<Map<String, String>> sendredpacklogsList = new SendredpacklogsDAO().getList(mch_billno, openid);
            request.setAttribute("sendredpacklogsList", sendredpacklogsList);
            //二维码路径
            String path = "";
            //判断有无实物奖品
            boolean flag = false;
            for (Map<String, String> sendredpacklogs : sendredpacklogsList) {
                if ("0".equals(sendredpacklogs.get("isexp"))) {
                    Map<String, String> redpack = new HashMap<String, String>();
                    redpack.put("id", sendredpacklogs.get("prizeid"));
                    redpack = new RedtypeDAO().getById(redpack);
                    if ("0".equals(redpack.get("type"))) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) {
                path = WxMenuUtils.getQRCode(request, "http://wanxiangfangwx.cityapp360.com/ForeServlet?method=myredpack_do&mch_billno=" + mch_billno + "&openid=" + openid);
            }
            request.setAttribute("path", path);
        } else {
            Map<String, String> map = new SendredpackDAO().getByMch_billno(mch_billno);
            request.setAttribute("map", map);
        }
        request.setAttribute("sign", sign);
        Forward.forward(request, response, "/fore/redpak/jsp.jsp");
    }

    protected void myredpack_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mch_billno = request.getParameter("mch_billno");
        String openid = request.getParameter("openid");
        List<Map<String, String>> sendredpacklogsList = new SendredpacklogsDAO().getList(mch_billno, openid, 1);
        Map<String, String> subscriber = new HashMap<String, String>();
        subscriber.put("openid", openid);
        subscriber = new SubscriberDAO().getByOpenid(subscriber);
        Map<String, String> sendredpack = new SendredpackDAO().getByMch_billno(mch_billno);
        request.setAttribute("sendredpacklogsList", sendredpacklogsList);
        request.setAttribute("subscriber", subscriber);
        request.setAttribute("sendredpack", sendredpack);
        Forward.forward(request, response, "/fore/redpak/jsp_back_do.jsp");
    }

    protected void myredpack_do_exp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mch_billno = request.getParameter("mch_billno");
        String openid = request.getParameter("openid");
        new SendredpacklogsDAO().update(openid, mch_billno, 1, WxMenuUtils.format.format(new Date()));
    }

    //网页获取openid
    protected void shop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String uid = request.getParameter("wx");
//        wx = new HashMap<String, String>();
//        wx.put("id", uid);
        wx = new WxsDAO().getById(wx);
        String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/ForeServlet?method=shop_do&wxsid=" + wx.get("id");
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wx.get("appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    //回调url
    protected void shop_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wxsid = request.getParameter("wxsid");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        wx.put("id", wxsid);
        wx = new WxsDAO().getById(wx);
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wx.get("appid") + "&secret=" + wx.get("appsecret") + "&code=" + code + "&grant_type=authorization_code");
        CloseableHttpResponse responses = WxMenuUtils.httpclient.execute(get);
        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
        JSONObject object = JSON.parseObject(jsonStr);
        response.sendRedirect("shop2/shop.jsp?openid=" + object.getString("openid") + "&wx=" + wx.get("id"));
//        Forward.forward(request, response, "shop/index.jsp?openid=" + object.getString("openid") + "&wx=" + wx.get("id"));
//        String openid = object.getString("openid");
//        String accessToken2 = object.getString("access_token");
//        get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken2 + "&openid=" + openid + "&lang=zh_CN");
//        responses = WxMenuUtils.httpclient.execute(get);
//        jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
//        System.out.println("jsonStr  " + jsonStr);
//        object = JSON.parseObject(jsonStr);
//        System.out.println(object.getString("nickname"));
    }

    //网页获取openid
    protected void vip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String uid = request.getParameter("wx");
//        wx = new HashMap<String, String>();
//        wx.put("id", uid);
        wx = new WxsDAO().getById(wx);
        String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/ForeServlet?method=vip_do&wxsid=" + wx.get("id");
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wx.get("appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    //回调url
    protected void vip_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wxsid = request.getParameter("wxsid");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        wx.put("id", wxsid);
        wx = new WxsDAO().getById(wx);
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wx.get("appid") + "&secret=" + wx.get("appsecret") + "&code=" + code + "&grant_type=authorization_code");
        CloseableHttpResponse responses = WxMenuUtils.httpclient.execute(get);
        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
        JSONObject object = JSON.parseObject(jsonStr);
        response.sendRedirect("shop2/vip.jsp?openid=" + object.getString("openid") + "&wx=" + wx.get("id"));
//        Forward.forward(request, response, "shop/index.jsp?openid=" + object.getString("openid") + "&wx=" + wx.get("id"));
//        String openid = object.getString("openid");
//        String accessToken2 = object.getString("access_token");
//        get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken2 + "&openid=" + openid + "&lang=zh_CN");
//        responses = WxMenuUtils.httpclient.execute(get);
//        jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
//        System.out.println("jsonStr  " + jsonStr);
//        object = JSON.parseObject(jsonStr);
//        System.out.println(object.getString("nickname"));
    }

    /**
     * 微信支付块
     */
    /**
     * 外来参数，不包含微信
     *
     * @param request 订单号(商品描述，总订单号，总金额)， wxsid，openid
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wxsid = request.getParameter("wxsid");
        //注：如果感觉不安全，建议重新获取一下openid，这里忽略
        String openid = request.getParameter("openid");
        String F_No = request.getParameter("F_No");
        System.out.println("监视付款操作：" + F_No);
        //微信信息
        wx = new HashMap<String, String>();
        wx.put("id", wxsid);
        wx = new WxsDAO().getById(wx);
        //订单信息
        String remark = "";
        DataField order = DaoFactory.getOrderDAO().get(F_No);
        List<DataField> prolist = (List<DataField>) DaoFactory.getBasketDAO().getListNo(F_No);
        for (DataField pro : prolist) {
            remark += pro.getFieldValue("Pname") + "，" + pro.getFieldValue("Pnum") + "件，" + pro.getFieldValue("Tot_Price") + "元\n";
        }
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String out_trade_no = order.getFieldValue("out_trade_no");
        if (null == out_trade_no || "".equals(out_trade_no)) {
            out_trade_no = wx.get("mch_id") + WxMenuUtils.format2.format(new Date()) + String.valueOf(new Random().nextInt(10000));//异步传输，异常查询使用;
        }
        System.out.println("out_trade_no " + out_trade_no);
        //这里判断一下订单状态 支付/未支付
        if ("1".equals(order.getFieldValue("IsPay"))) {

            try {
                //        out_trade_no = F_No;//重新定义
                DaoFactory.getOrderDAO().modout_trade_no(F_No, out_trade_no);
            } catch (ObjectNotFoundException ex) {
                Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DatabaseException ex) {
                Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String total_fee = String.valueOf((int) (order.getFloat("SF_Price") * 100));//分
            //统一下单参数
            WxPayUtils wxPayUtils = new WxPayUtils();
            Map map = new HashMap();
            map.put("appid", wx.get("appid"));//公众账号id
            map.put("mch_id", wx.get("mch_id"));//商户号
//        map.put("device_info", "WEB");//设备号 否
            map.put("nonce_str", String.valueOf(System.currentTimeMillis() / 1000));//随机字符串
            map.put("body", remark);//商品描述
//        map.put("detail", "商品详情");//商品详情 否
        map.put("attach", "订单自定义数据");//附加数据 否
            map.put("out_trade_no", out_trade_no);//商户订单号
//        map.put("fee_type", "CNY");//货币类型 否
            map.put("total_fee", total_fee);//总金额 分
            map.put("spbill_create_ip", request.getRemoteAddr());//终端ip
//        map.put("time_start", WxMenuUtils.format.format(new Date()));//交易起始时间 否
//        map.put("time_expire", WxMenuUtils.format.format(new Date()));//交易结束时间 否
//        map.put("goods_tag", "商品标记");//商品标记 否
            map.put("notify_url", request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/Wxpaynotify_urlServlet");//通知地址
            map.put("trade_type", "JSAPI");//交易类型
//        map.put("product_id", "");//商品id 否 native必传
            map.put("openid", openid);//用户标识 否 jsapi必传
            //下单
            map = wxPayUtils.payUnifiedorder(map, wx.get("wxpaykey"));
            String prepay_id = (String) map.get("prepay_id");
            System.out.println("prepay_id   " + prepay_id);

            if (null != prepay_id) {
                Map jsapimap = new HashMap<String, String>();
                jsapimap.put("appId", map.get("appid"));
                jsapimap.put("timeStamp", timeStamp);
                jsapimap.put("nonceStr", map.get("nonce_str"));
                jsapimap.put("package", "prepay_id=" + prepay_id);
                jsapimap.put("signType", "MD5");
                String paySign = wxPayUtils.getSignature(jsapimap, wx.get("wxpaykey"));
                jsapimap.put("packages", "prepay_id=" + prepay_id);//package疑似关键字，用packages代替传输
                jsapimap.put("paySign", paySign);
                System.out.println(jsapimap.get("appId") + " " + jsapimap.get("timeStamp") + " " + jsapimap.get("nonceStr") + " " + prepay_id + " " + "MD5" + " " + paySign);
                //附加参数
                jsapimap.put("F_No", F_No);
                jsapimap.put("out_trade_no", out_trade_no);
                jsapimap.put("openid", openid);

                request.setAttribute("jsapimap", jsapimap);
                request.setAttribute("wx", wx);
                Forward.forward(request, response, "/fore/jsapi/jsapi.jsp");
            } else {
                //错误处理
                System.out.println("统一下单失败！");
                Forward.forward(request, response, "/shop/index.jsp?act=order&wx=" + wxsid + "&openid=" + openid);
            }
        } else {
            //已处理
            System.out.println("订单支付失败，该订单已支付！" + out_trade_no);
            Forward.forward(request, response, "/shop/index.jsp?act=order&wx=" + wxsid + "&openid=" + openid);
        }
    }

//    protected void wxpaynotify_url(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        
//    }
    //主动查询订单
    protected void orderquery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", "");
        map.put("mch_id", "");
        map.put("transaction_id", "");
        System.out.println("out_trade_no   " + request.getParameter("out_trade_no"));
        map.put("out_trade_no", request.getParameter("out_trade_no"));
        map.put("nonce_str", String.valueOf(UUID.randomUUID()));
//        map = new WxPayUtils().payorderquery(map, wx);
        //订单查询处理
    }

    //主动关闭订单
    protected void closeorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", "");
        map.put("mch_id", "");
        System.out.println("out_trade_no   " + request.getParameter("out_trade_no"));
        map.put("out_trade_no", request.getParameter("out_trade_no"));
        map.put("nonce_str", String.valueOf(UUID.randomUUID()));
        map = new WxPayUtils().paycloseorder(map, wx.get("wxpaykey"));
        //订单关闭处理
    }

    //退款
    protected void refund(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wxsid = request.getParameter("wxsid");
        String openid = request.getParameter("openid");
//        String transaction_id=request.getParameter("transaction_id");
        String F_No = request.getParameter("F_No");
        System.out.println("监视退货操作：" + F_No);
        System.out.println("wxsid " + wxsid + " openid " + openid + " F_No " + F_No);
        wx = new HashMap<String, String>();
        wx.put("id", wxsid);
        wx = new WxsDAO().getById(wx);
        DataField order = DaoFactory.getOrderDAO().get(F_No);

        if (null != order && ("1".equals(order.getFieldValue("Sts")) || "3".equals(order.getFieldValue("Sts")))) {
            System.out.println("退货out_trade_no " + order.getFieldValue("out_trade_no"));
            //更改订单状态
            try {
                DaoFactory.getOrderDAO().modStsByout_trade_no(order.getFieldValue("out_trade_no"), 6);
            } catch (ObjectNotFoundException ex) {
                Logger.getLogger(WxPayUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DatabaseException ex) {
                Logger.getLogger(WxPayUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("该状态不允许退款out_trade_no " + order.getFieldValue("out_trade_no") + " Sts" + order.getFieldValue("Sts"));
        }

//        Map<String, String> map = new HashMap<String, String>();
//        map.put("appid", wx.get("appid"));
//        map.put("mch_id", wx.get("mch_id"));
////        map.put("device_info", "");//否
//        map.put("nonce_str", String.valueOf(System.currentTimeMillis() / 1000));
////        map.put("transaction_id", transaction_id);//微信订单号
//        map.put("out_trade_no", order.getFieldValue("out_trade_no"));//商户订单号
//        map.put("out_refund_no", "-" + order.getFieldValue("out_trade_no"));//商户退款单号
//        map.put("total_fee", String.valueOf((int) (order.getFloat("SF_Price") * 100)));//总金额
//        map.put("refund_fee", String.valueOf((int) (order.getFloat("SF_Price") * 100)));//退款金额
////        map.put("refund_fee_type", "CNY");//货币种类 否
//        map.put("op_user_id", wx.get("mch_id"));//操作员
//        map = new WxPayUtils().payrefund(map, wx);
        Forward.forward(request, response, "/shop2/vip.jsp?act=order&wx=" + wxsid + "&openid=" + openid);
    }

    //查询退款
    protected void refundquery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", "");
        map.put("mch_id", "");
        map.put("device_info", "");//否
        map.put("nonce_str", String.valueOf(UUID.randomUUID()));
        map.put("transaction_id", "");//微信订单号
        map.put("out_trade_no", "");//商户订单号
        map.put("out_refund_no", "");//商户退款单号
        map.put("refund_id", "");//微信退款单号
        map = new WxPayUtils().refundquery(map, wx.get("wxpaykey"));
    }

    //对账单
    protected void downloadbill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", "");
        map.put("mch_id", "");
        map.put("device_info", "");//否
        map.put("nonce_str", String.valueOf(UUID.randomUUID()));
        map.put("bill_date", "");//下载对账单的日期
        map.put("bill_type", "ALL");//订单类型
//        ALL，返回当日所有订单信息，默认值
//SUCCESS，返回当日成功支付的订单
//REFUND，返回当日退款订单
//REVOKED，已撤销的订单
        map = new WxPayUtils().downloadbill(map, wx.get("wxpaykey"), this.getServletContext());
        System.out.println("path   " + map.get("path"));
    }

    /**
     * 微信支付块结束
     */
    //网页获取openid
    protected void shop2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectUri = Sysconfig.getLoginActionUrl() + "ForeServlet?method=shop2_do";
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxReader.getWxInfo().get("AppId") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    //回调url
    protected void shop2_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        System.out.println(code);
        String state = request.getParameter("state");
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxReader.getWxInfo().get("AppId") + "&secret=" + WxReader.getWxInfo().get("AppSecret") + "&code=" + code + "&grant_type=authorization_code");
        CloseableHttpResponse responses = WxMenuUtils.httpclient.execute(get);
        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
        JSONObject object = JSON.parseObject(jsonStr);
        System.out.println("openid  " + object.getString("openid"));
        response.sendRedirect("shop3/index_do.jsp?openid=" + object.getString("openid"));
    }

    protected void shop2_pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //注：如果感觉不安全，建议重新获取一下openid，这里忽略
        String openid = request.getParameter("openid");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        int pnum = Integer.parseInt(request.getParameter("pnum"));
        Float sfprice = (float) 60 * pnum;
        int aid = Integer.parseInt(request.getParameter("aid"));
        String address = request.getParameter("address");
//        String postcode = request.getParameter("postcode");
        String remark = request.getParameter("remark");
        String fno = String.valueOf(System.currentTimeMillis());
        boolean flag = DaoFactory.getOrder2DaoImplJDBC().add(fno, WxMenuUtils.format.format(new Date()), sfprice, 1, sfprice, openid, request.getRemoteAddr(), 0, 1, 1, name, address, tel, tel, remark, "", "", "", 0, 1, aid, 60, pnum);
        if (flag) {
            String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
            String out_trade_no = WxReader.getWxInfo().get("mch_id") + WxMenuUtils.formatd.format(new Date()) + String.valueOf(new Random().nextInt(10000)) + String.valueOf(new Random().nextInt(10000));//异步传输，异常查询使用
            try {
                DaoFactory.getOrder2DaoImplJDBC().modout_trade_no(fno, out_trade_no);
            } catch (ObjectNotFoundException ex) {
                Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DatabaseException ex) {
                Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String total_fee = String.valueOf((int) (sfprice * 100));//分
            System.out.println("out_trade_no " + out_trade_no + " total_fee " + total_fee + " remark " + remark);
            //统一下单参数
            WxPayUtils wxPayUtils = new WxPayUtils();
            Map map = new HashMap();
            map.put("appid", WxReader.getWxInfo().get("AppId"));//公众账号id
            map.put("mch_id", WxReader.getWxInfo().get("mch_id"));//商户号
//        map.put("device_info", "WEB");//设备号 否
            map.put("nonce_str", String.valueOf(System.currentTimeMillis() / 1000));//随机字符串
            map.put("body", "老徐巨峰葡萄 " + pnum + "箱 " + sfprice + "元");//商品描述
//        map.put("detail", "商品详情");//商品详情 否
//        map.put("attach", "订单自定义数据");//附加数据 否
            map.put("out_trade_no", out_trade_no);//商户订单号
//        map.put("fee_type", "CNY");//货币类型 否
            map.put("total_fee", total_fee);//总金额 分
            map.put("spbill_create_ip", request.getRemoteAddr());//终端ip
//        map.put("time_start", WxMenuUtils.formatdt.format(new Date()));//交易起始时间 否
//        map.put("time_expire", Long.parseLong(WxMenuUtils.formatdt.format(new Date())) + 5 * 60);//交易结束时间 否
//        map.put("goods_tag", "商品标记");//商品标记 否
            map.put("notify_url", request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/Wxpaynotify_urlServlet2");//通知地址
            map.put("trade_type", "JSAPI");//交易类型
//        map.put("product_id", "");//商品id 否 native必传
            map.put("openid", openid);//用户标识 否 jsapi必传
            //下单
            map = wxPayUtils.payUnifiedorder(map, WxReader.getWxInfo().get("key"));
            String prepay_id = (String) map.get("prepay_id");
            System.out.println("prepay_id   " + prepay_id);
            System.out.println("code_url   " + map.get("code_url"));

            if (null != prepay_id) {
                Map jsapimap = new HashMap<String, String>();
                jsapimap.put("appId", map.get("appid"));
                jsapimap.put("timeStamp", timeStamp);
                jsapimap.put("nonceStr", map.get("nonce_str"));
                jsapimap.put("package", "prepay_id=" + prepay_id);
                jsapimap.put("signType", "MD5");
                String paySign = wxPayUtils.getSignature(jsapimap, WxReader.getWxInfo().get("key"));
                jsapimap.put("packages", "prepay_id=" + prepay_id);//package疑似关键字，用packages代替传输
                jsapimap.put("paySign", paySign);
                System.out.println(jsapimap.get("appId") + " " + jsapimap.get("timeStamp") + " " + jsapimap.get("nonceStr") + " " + prepay_id + " " + "MD5" + " " + paySign);
                //附加参数
                jsapimap.put("out_trade_no", out_trade_no);
                jsapimap.put("openid", openid);

                request.setAttribute("jsapimap", jsapimap);
                Forward.forward(request, response, "/fore/jsapi/jsapi2.jsp");
            } else {
                //错误处理
                System.out.println("统一下单失败！");
                Forward.forward(request, response, "/shop3/index.jsp");
            }
        } else {
            PrintWriter out = response.getWriter();
            out.print("系统错误，请重试！");
            out.close();
        }
    }
//    /**
//     * 代金券块
//     */
//    protected void send_coupon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        WxPayUtils wxPayUtils = new WxPayUtils();
//        //注：如果感觉不安全，建议重新获取一下openid，这里忽略
//        String openid = request.getParameter("openid");
//        System.out.println(" openid " + openid);
//        String coupon_stock_id = request.getParameter("id");//代金券批次id
//
//        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
//        //商户单据号
//        String partner_trade_no = WxReader.getWxInfo().get("mch_id") + WxMenuUtils.formatd.format(new Date()) + String.valueOf(new Random().nextInt(10000)) + String.valueOf(new Random().nextInt(10000));//异步传输，异常查询使用
//
//        //组织参数
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("coupon_stock_id", coupon_stock_id);
//        map.put("openid_count", "1");//openid记录数，1
//        map.put("partner_trade_no", partner_trade_no);
//        map.put("openid", openid);
//        map.put("appid", WxReader.getWxInfo().get("AppId"));//公众账号id
//        map.put("mch_id", WxReader.getWxInfo().get("mch_id"));//商户号
//        map.put("nonce_str", timeStamp);//随机字符串
//
////        map.put("op_user_id", WxReader.getWxInfo().get("mch_id"));//操作员 否
////        map.put("device_info", "WEB");//设备号 否
////        map.put("version", "1.0");//协议版本 否
////        map.put("type", "XML");//协议类型 否
//
//        map = wxPayUtils.send_coupon(map, WxReader.getWxInfo().get("key"));
//        System.out.println("map " + map);
//        if ("SUCCESS".equals(map.get("result_code"))) {
//            System.out.println("代金券发放成功：" + " " + map.get("coupon_stock_id") + " " + map.get("coupon_id"));
//        } else {
//            System.out.println("代金券发放失败：" + " " + map.get("coupon_stock_id") + " " + map.get("coupon_id"));
//        }
//    }
//    /**
//     * 代金券结束
//     */

//    protected void vote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String sid = request.getParameter("sid");
////        String redirectUri = "http://wanxiangfangwx.cityapp360.com/vote/index.jsp?sid=" + sid;
//        String id = request.getParameter("id");
//        String redirectUri = "http://wanxiangfangwx.cityapp360.com/vote/vote.jsp?sid=" + sid + "&id=" + id;
//        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxReader.getWxInfo().get("AppId") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
//    }
//    http://wanxiangfangwx.cityapp360.com/vote/vote/index.jsp?sid=1#mp.weixin.qq.com
    protected void vote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String id = request.getParameter("id");
        String votebodyid = request.getParameter("votebodyid");
        String redirectUri = "http://wanxiangfangwx.cityapp360.com/vote/vote/detail.jsp?sid=" + sid + "&id=" + id + "&votebodyid=" + votebodyid;
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxReader.getWxInfo().get("AppId") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    protected void vote_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String id = request.getParameter("id");
        String votebodyid = request.getParameter("votebodyid");
        String openid = request.getParameter("openid");
        PrintWriter out = response.getWriter();
        if (ValidCrossDomain.validateRequest(request)) {
            //验证有无关注
            Map<String, String> subscriber = new SubscriberDAO().getByOpenid(openid);
            if (null != subscriber.get("id")) {
                //验证一下今天有无投票
                DataField votedetailsign = DaoFactory.getMysqlDao().getFirstData("select id from votedetail where openid='" + openid + "' and voteid=" + id + " and votebodyid=" + votebodyid + " and votetimes like '" + WxMenuUtils.formatdate.format(new Date()) + "%'", "id");
                if (null == votedetailsign) {
                    //添加投票记录
                    boolean flagvotedetail = DaoFactory.getMysqlDao().add("insert into votedetail(openid,voteid,votebodyid,votetimes,ip,sid) values ('" + openid + "'," + id + "," + votebodyid + ",'" + WxMenuUtils.format.format(new Date()) + "','" + request.getRemoteAddr() + "'," + sid + ")");
                    //增加票数
                    if (flagvotedetail) {
                        flagvotedetail = DaoFactory.getMysqlDao().mod("update votebody set votecount=votecount+1 where id=" + votebodyid + " and sid=" + sid);
                    }
                    if (flagvotedetail) {
                        out.print("{\"success\":\"0\",\"message\":\"投票成功，送您一次转盘机会！\"}");
                    } else {
                        out.print("{\"success\":\"4\",\"message\":\"投票异常，请在微信手机客户端进行投票！\"}");
                    }
                } else {
                    out.print("{\"success\":\"1\",\"message\":\"您今天已经投过票了，明天再来吧！\"}");
                }
            } else {
                out.print("{\"success\":\"2\",\"message\":\"您尚未关注本公众号，请关注后再进行投票！\"}");
            }
        } else {
            System.out.println("厨王争霸投票异常 " + ValidCrossDomain.validateRequest(request));
            out.print("{\"success\":\"3\",\"message\":\"请在微信手机客户端进行投票！\"}");
        }
        out.close();
    }

//    http://wanxiangfangwx.cityapp360.com/vote2/vote/vote.jsp?sid=2&id=7
    protected void vote2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String id = request.getParameter("id");
        String votebodyid = request.getParameter("votebodyid");
        String redirectUri = "http://wanxiangfangwx.cityapp360.com/vote2/vote/detail.jsp?sid=" + sid + "&id=" + id + "&votebodyid=" + votebodyid;
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxReader.getWxInfo().get("AppId") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    protected void vote2_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String id = request.getParameter("id");
        String votebodyid = request.getParameter("votebodyid");
        String openid = request.getParameter("openid");
        PrintWriter out = response.getWriter();
        if (ValidCrossDomain.validateRequest(request)) {
            //验证有无关注
            Map<String, String> subscriber = new SubscriberDAO().getByOpenid(openid);
            if (null != subscriber.get("id")) {
                //验证一下今天有无投票
                DataField votedetailsign = DaoFactory.getMysqlDao().getFirstData("select id from votedetail where openid='" + openid + "' and voteid=" + id + " and votebodyid=" + votebodyid + " and votetimes like '" + WxMenuUtils.formatdate.format(new Date()) + "%'", "id");
                if (null == votedetailsign) {
                    //添加投票记录
                    boolean flagvotedetail = DaoFactory.getMysqlDao().add("insert into votedetail(openid,voteid,votebodyid,votetimes,ip,sid) values ('" + openid + "'," + id + "," + votebodyid + ",'" + WxMenuUtils.format.format(new Date()) + "','" + request.getRemoteAddr() + "'," + sid + ")");
                    //增加票数
                    if (flagvotedetail) {
                        flagvotedetail = DaoFactory.getMysqlDao().mod("update votebody set votecount=votecount+1 where id=" + votebodyid + " and sid=" + sid);
                    }
                    if (flagvotedetail) {
                        out.print("{\"success\":\"0\",\"message\":\"投票成功，送您一次转盘机会！\"}");
                    } else {
                        out.print("{\"success\":\"4\",\"message\":\"投票异常，请在微信手机客户端进行投票！\"}");
                    }
                } else {
                    out.print("{\"success\":\"1\",\"message\":\"您今天已经投过票了，明天再来吧！\"}");
                }
            } else {
                out.print("{\"success\":\"2\",\"message\":\"您尚未关注本公众号，请关注后再进行投票！\"}");
            }
        } else {
            System.out.println("萌宝投票异常 " + ValidCrossDomain.validateRequest(request));
            out.print("{\"success\":\"3\",\"message\":\"请在微信手机客户端进行投票！\"}");
        }
        out.close();
    }

//    http://wanxiangfangwx.cityapp360.com/vote3/vote/vote.jsp?sid=3&id=8
    protected void vote3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String id = request.getParameter("id");
        String votebodyid = request.getParameter("votebodyid");
        String redirectUri = "http://wanxiangfangwx.cityapp360.com/vote3/vote/detail.jsp?sid=" + sid + "&id=" + id + "&votebodyid=" + votebodyid;
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxReader.getWxInfo().get("AppId") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    protected void vote3_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String id = request.getParameter("id");
        String votebodyid = request.getParameter("votebodyid");
        String openid = request.getParameter("openid");
        PrintWriter out = response.getWriter();
        if (ValidCrossDomain.validateRequest(request)) {
            //验证有无关注
            Map<String, String> subscriber = new SubscriberDAO().getByOpenid(openid);
            if (null != subscriber.get("id")) {
                //验证一下今天有无投票
                DataField votedetailsign = DaoFactory.getMysqlDao().getFirstData("select id from votedetail where openid='" + openid + "' and voteid=" + id + " and votebodyid=" + votebodyid + " and votetimes like '" + WxMenuUtils.formatdate.format(new Date()) + "%'", "id");
                if (null == votedetailsign) {
                    //添加投票记录
                    boolean flagvotedetail = DaoFactory.getMysqlDao().add("insert into votedetail(openid,voteid,votebodyid,votetimes,ip,sid) values ('" + openid + "'," + id + "," + votebodyid + ",'" + WxMenuUtils.format.format(new Date()) + "','" + request.getRemoteAddr() + "'," + sid + ")");
                    //增加票数
                    if (flagvotedetail) {
                        flagvotedetail = DaoFactory.getMysqlDao().mod("update votebody set votecount=votecount+1 where id=" + votebodyid + " and sid=" + sid);
                    }
                    if (flagvotedetail) {
                        out.print("{\"success\":\"0\",\"message\":\"投票成功，送您一次转盘机会！\"}");
                    } else {
                        out.print("{\"success\":\"4\",\"message\":\"投票异常，请在微信手机客户端进行投票！\"}");
                    }
                } else {
                    out.print("{\"success\":\"1\",\"message\":\"您今天已经投过票了，明天再来吧！\"}");
                }
            } else {
                out.print("{\"success\":\"2\",\"message\":\"您尚未关注本公众号，请关注后再进行投票！\"}");
            }
        } else {
            System.out.println("山地车投票异常 " + ValidCrossDomain.validateRequest(request));
            out.print("{\"success\":\"3\",\"message\":\"请在微信手机客户端进行投票！\"}");
        }
        out.close();
    }

    protected void weizhibo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        List<Map<String, String>> newsList = new NewsDAO().getList(sid);

        int subscribercount = new SubscriberDAO().getCount();

        request.setAttribute("sid", sid);
        request.setAttribute("newsList", newsList);
        request.setAttribute("newscount", newsList.size());
        request.setAttribute("subscribercount", subscribercount);

        String url = request.getRequestURL() + "?" + request.getQueryString();
        Map<String, String> jsapi = new WxJsApiUtils().jsapi(url);
        String jsApiList = "'previewImage'";
        jsapi.put("jsApiList", jsApiList);
        request.setAttribute("jsapi", jsapi);
        Forward.forward(request, response, "/fore/newszhibo/index.jsp");
    }

    protected void weizhibo_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String newscount = request.getParameter("newscount");
        PrintWriter out = response.getWriter();
        List<Map<String, String>> newsList = new NewsDAO().getList(sid);
        if (newsList.size() > Integer.parseInt(newscount)) {
            out.print("{\"success\":\"1\",\"addcount\":\"" + (newsList.size() - Integer.parseInt(newscount)) + "\"}");
        } else {
            out.print("{\"success\":\"0\"}");
        }
        out.close();
    }

    protected void roulette_do2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openid = request.getParameter("openid");
        String aid = request.getParameter("aid");//投票社区id视为活动id
        String sid = "2";
        OpenprizeDAO openprizeDAO = new OpenprizeDAO();
        PrintWriter out = response.getWriter();
        int takeNum = openprizeDAO.getTakeCount(openid, sid);

        Map<String, String> openprize = new HashMap<String, String>();
        openprize.put("openid", openid);
        openprize.put("aid", aid);
        openprize.put("sn", String.valueOf(System.currentTimeMillis()));
        openprize.put("tel", "");
        openprize.put("opendate", WxMenuUtils.format.format(new Date()));
        openprize.put("name", "");
        openprize.put("sex", "0");
        openprize.put("sid", sid);
        if (0 == takeNum) {
            openprize.put("pid", "1");
            openprize.put("state", "1");
            openprize.put("isexp", "1");
            openprizeDAO.add(openprize);
            out.print("1");
        } else {
            openprize.put("pid", "0");
            openprize.put("state", "0");
            openprize.put("isexp", "0");
            openprizeDAO.add(openprize);
            out.print("0");
        }
        out.close();
    }

    protected void saleqrcode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openid = request.getParameter("openid");
        System.out.println("openid " + openid);
        String qrcode = WxMenuUtils.saleqrcode(request, openid);
        request.setAttribute("qrcode", qrcode);
        Forward.forward(request, response, "/fore/saleMember/index.jsp");
    }

    protected void votebutton_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String votebodyid = request.getParameter("votebodyid");
        PrintWriter out = response.getWriter();
        int random = new Random().nextInt(10) % (10 - 5 + 1) + 5;
        int randompopular = new Random().nextInt(200) % (200 - 100 + 1) + 100;
        System.out.println(random + " " + randompopular);
        //增加票数
        DaoFactory.getMysqlDao().mod("update votebody set votecount=votecount+" + random + " where id=" + votebodyid);
        //增加人气
        DaoFactory.getMysqlDao().mod("update votebody set popular=popular+" + randompopular + " where id=" + votebodyid);
        out.print("{\"success\":\"0\",\"message\":\"投票成功," + WxMenuUtils.format.format(new Date()) + "！\"}");
        out.close();
    }

    /*
     * vote4开始
     */
    protected void vote4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/vote4/index.jsp";
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxMenuUtils.APPID + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
    }
    /*
     * vote4结束
     */

    /**
     * 狗粮活动开始
     */
    protected void dog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/dog/index.jsp";
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxReader.getWxInfo().get("AppId") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    protected void lingyang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = RequestUtil.getString(request, "act");
        String openid = request.getParameter("openid");
        String targetopenid = openid;
        int pnum = 0;//支付返回用
        //领养
        if ("0".equals(act)) {//首次领取
            //判断有无领养
            DataField target = DaoFactory.getTargetDAO().getByOpenid(openid);
            if (null == target) {//未领养，已领养忽略
                boolean flag = false;
                do {
                    JSONObject object = WxMenuUtils.getUserInfo(openid);
                    object = null != object ? object : new JSONObject();
                    String nickname = object.getString("nickname");
                    String headimgurl = object.getString("headimgurl");
                    //记录领取记录
                    flag = DaoFactory.gettDetailDAO().add(openid, openid, WxMenuUtils.format.format(new Date()), 10, "领养");
                    if (flag) {
                        flag = DaoFactory.getTargetDAO().add(openid, nickname, "tel", headimgurl, 10, WxMenuUtils.format.format(new Date()), 0);
                    }
                    System.out.println("领养 " + flag);
                } while (!flag);
            }
        } else if ("1".equals(act) && null != openid) {//分享成功
            //判断有无分享
            List<DataField> tdetailList = DaoFactory.gettDetailDAO().getList(openid, openid, "分享");
            if (0 == tdetailList.size()) {
                boolean flag = false;
                do {
                    //记录分享记录
                    flag = DaoFactory.gettDetailDAO().add(openid, openid, WxMenuUtils.format.format(new Date()), 5, "分享");
                    if (flag) {
                        flag = DaoFactory.getTargetDAO().updateCounts(openid, 5);
                    }
                } while (!flag);
            }
        } else if ("2".equals(act)) {//支付助养
//            targetopenid = request.getParameter("targetopenid");
//            System.out.println("targetopenid " + targetopenid);
//            pnum = RequestUtil.getInt(request, "pnum");
//            boolean flag = false;
//            do {
//                //记录助养记录
//                flag = DaoFactory.gettDetailDAO().add(openid, targetopenid, WxMenuUtils.format.format(new Date()), 1, "购买");
//                if (flag) {
//                    flag = DaoFactory.getTargetDAO().updateCounts(targetopenid, 1);
//                }
//            } while (!flag);
        }

        //获取分享注入
        String url = request.getRequestURL() + "?" + request.getQueryString();
        Map<String, String> jsapi = new WxJsApiUtils().jsapi(url);
//        System.out.println(jsapi.get("noncestr") + "   " + jsapi.get("jsapi_ticket") + "   " + jsapi.get("timestamp") + "   " + jsapi.get("url") + "  " + jsapi.get("signature") + "   " + jsapi.get("appId"));
        String jsApiList = "'checkJsApi','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo'";
        jsapi.put("jsApiList", jsApiList);
        request.setAttribute("jsapi", jsapi);
        if (openid.equals(targetopenid)) {//自己操作
            Forward.forward(request, response, "/dog/lingyang.jsp?openid=" + openid + "&act=2&pnum=" + pnum);
        } else {//他人操作
            Forward.forward(request, response, "/ForeServlet?method=ciye2&targetopenid=" + targetopenid);
        }
    }

    protected void ciye2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetopenid = request.getParameter("targetopenid");
        //此处除需获得助养目标身份外，还需获取助养者身份
        String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/dog/ciye2.jsp?targetopenid=" + targetopenid;
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxReader.getWxInfo().get("AppId") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    protected void zhuyang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openid = request.getParameter("openid");
        String targetopenid = request.getParameter("targetopenid");
        System.out.println("openid " + openid + " targetopenid " + targetopenid);
        PrintWriter out = response.getWriter();
        //判断有无助养过
        List<DataField> list = DaoFactory.gettDetailDAO().getList(openid, targetopenid, "助养");
        if (1 <= list.size()) {//助养次数超过
            out.print("{\"message\":\"false\",\"sign\":\"1\"}");
        } else {
            boolean flag = DaoFactory.gettDetailDAO().add(openid, targetopenid, WxMenuUtils.format.format(new Date()), 5, "助养");
            if (flag) {
                flag = DaoFactory.getTargetDAO().updateCounts(targetopenid, 5);
            }
            if (flag) {
                out.print("{\"message\":\"success\",\"sign\":\"0\"}");
            } else {//数据库异常
                out.print("{\"message\":\"false\",\"sign\":\"2\"}");
            }
        }
        out.close();
    }

    //网页获取openid
    protected void shop3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetopenid = request.getParameter("targetopenid");
        String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/dog/shop_do.jsp?targetopenid=" + targetopenid;
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxReader.getWxInfo().get("AppId") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    //回调url
//    protected void shop3_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String targetopenid=request.getParameter("targetopenid");
//        String code = request.getParameter("code");
//        System.out.println("targetopenid "+targetopenid);
//        String state = request.getParameter("state");
//        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxReader.getWxInfo().get("AppId") + "&secret=" + WxReader.getWxInfo().get("AppSecret") + "&code=" + code + "&grant_type=authorization_code");
//        CloseableHttpResponse responses = WxMenuUtils.httpclient.execute(get);
//        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
//        JSONObject object = JSON.parseObject(jsonStr);
//        System.out.println("openid  " + object.getString("openid"));
//        response.sendRedirect("shop_do.jsp?openid=" + object.getString("openid")+"&targetopenid=");
//    }
    protected void shop3_pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //注：如果感觉不安全，建议重新获取一下openid，这里忽略
        String openid = request.getParameter("openid");
        String targetopenid = request.getParameter("targetopenid");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        int pnum = Integer.parseInt(request.getParameter("pnum"));
        Float sfprice = (float) 0.01 * pnum;
        int aid = Integer.parseInt(request.getParameter("aid"));
        String address = request.getParameter("address");
//        String postcode = request.getParameter("postcode");
        String remark = request.getParameter("remark");
        String fno = String.valueOf(System.currentTimeMillis());
        boolean flag = DaoFactory.getOrder2DaoImplJDBC().add(fno, WxMenuUtils.format.format(new Date()), sfprice, 1, sfprice, openid, request.getRemoteAddr(), 0, 1, 1, name, address, tel, tel, remark, "", "", "", 0, 1, aid, 60, pnum);
        if (flag) {
            String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
            String out_trade_no = WxReader.getWxInfo().get("mch_id") + WxMenuUtils.formatd.format(new Date()) + String.valueOf(new Random().nextInt(10000)) + String.valueOf(new Random().nextInt(10000));//异步传输，异常查询使用
            try {
                DaoFactory.getOrder2DaoImplJDBC().modout_trade_no(fno, out_trade_no);
            } catch (ObjectNotFoundException ex) {
                Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DatabaseException ex) {
                Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String total_fee = String.valueOf((int) (sfprice * 100));//分
            System.out.println("out_trade_no " + out_trade_no + " total_fee " + total_fee + " remark " + remark);
            //统一下单参数
            WxPayUtils wxPayUtils = new WxPayUtils();
            Map map = new HashMap();
            map.put("appid", WxReader.getWxInfo().get("AppId"));//公众账号id
            map.put("mch_id", WxReader.getWxInfo().get("mch_id"));//商户号
//        map.put("device_info", "WEB");//设备号 否
            map.put("nonce_str", String.valueOf(System.currentTimeMillis() / 1000));//随机字符串
            map.put("body", "老徐巨峰葡萄 " + pnum + "箱 " + sfprice + "元");//商品描述
//        map.put("detail", "商品详情");//商品详情 否
//        map.put("attach", "订单自定义数据");//附加数据 否
            map.put("out_trade_no", out_trade_no);//商户订单号
//        map.put("fee_type", "CNY");//货币类型 否
            map.put("total_fee", total_fee);//总金额 分
            map.put("spbill_create_ip", request.getRemoteAddr());//终端ip
//        map.put("time_start", WxMenuUtils.formatdt.format(new Date()));//交易起始时间 否
//        map.put("time_expire", Long.parseLong(WxMenuUtils.formatdt.format(new Date())) + 5 * 60);//交易结束时间 否
//        map.put("goods_tag", "商品标记");//商品标记 否
            map.put("notify_url", request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/Wxpaynotify_urlServlet3");//通知地址
            map.put("trade_type", "JSAPI");//交易类型
//        map.put("product_id", "");//商品id 否 native必传
            map.put("openid", openid);//用户标识 否 jsapi必传
            //下单
            map = wxPayUtils.payUnifiedorder(map, WxReader.getWxInfo().get("key"));
            String prepay_id = (String) map.get("prepay_id");
            System.out.println("prepay_id   " + prepay_id);
            System.out.println("code_url   " + map.get("code_url"));

            if (null != prepay_id) {
                Map jsapimap = new HashMap<String, String>();
                jsapimap.put("appId", map.get("appid"));
                jsapimap.put("timeStamp", timeStamp);
                jsapimap.put("nonceStr", map.get("nonce_str"));
                jsapimap.put("package", "prepay_id=" + prepay_id);
                jsapimap.put("signType", "MD5");
                String paySign = wxPayUtils.getSignature(jsapimap, WxReader.getWxInfo().get("key"));
                jsapimap.put("packages", "prepay_id=" + prepay_id);//package疑似关键字，用packages代替传输
                jsapimap.put("paySign", paySign);
                System.out.println(jsapimap.get("appId") + " " + jsapimap.get("timeStamp") + " " + jsapimap.get("nonceStr") + " " + prepay_id + " " + "MD5" + " " + paySign);
                //附加参数
                jsapimap.put("out_trade_no", out_trade_no);
                jsapimap.put("openid", openid);
                jsapimap.put("targetopenid", targetopenid);

                request.setAttribute("jsapimap", jsapimap);
                Forward.forward(request, response, "/fore/jsapi/jsapi3.jsp?pnum=" + pnum);
            } else {
                //错误处理
                System.out.println("统一下单失败！");
                Forward.forward(request, response, "/shop.jsp");
            }
        } else {
            PrintWriter out = response.getWriter();
            out.print("系统错误，请重试！");
            out.close();
        }
    }

    protected void issubscriber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openid = request.getParameter("openid");
        PrintWriter out = response.getWriter();
        Map<String, String> subscriber = new SubscriberDAO().getByOpenid(openid);
        if (null != subscriber.get("openid")) {
            out.print("1");
        } else {
            //获取用户信息
            JSONObject object = WxMenuUtils.getUserInfo(openid);
            object = null != object ? object : new JSONObject();
            if (null != object.getString("nickname")) {
                //添加关注记录
                boolean flag = new SubscriberService().addSubscriber(object, openid);
                if (flag) {
                    out.print("1");
                } else {
                    out.print("0");
                }
            } else {
                out.print("0");
            }
        }
        out.close();
    }

    protected void isshare(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openid = request.getParameter("openid");
        PrintWriter out = response.getWriter();
        List<DataField> tdetailList = DaoFactory.gettDetailDAO().getList(openid, openid, "分享");
        if (0 == tdetailList.size()) {
            out.print("0");
        } else {
            out.print("1");
        }
        out.close();
    }

    //网页获取openid
    protected void shop4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetopenid = RequestUtil.getString(request, "targetopenid");
//        String uid = request.getParameter("wx");
//        wx = new HashMap<String, String>();
//        wx.put("id", uid);
        wx = new WxsDAO().getById(wx);
        String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/ForeServlet?method=shop4_do&wxsid=" + wx.get("id") + "&targetopenid=" + targetopenid;
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wx.get("appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    //回调url
    protected void shop4_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wxsid = request.getParameter("wxsid");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        wx.put("id", "1");
        wx = new WxsDAO().getById(wx);
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wx.get("appid") + "&secret=" + wx.get("appsecret") + "&code=" + code + "&grant_type=authorization_code");
        CloseableHttpResponse responses = WxMenuUtils.httpclient.execute(get);
        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
        JSONObject object = JSON.parseObject(jsonStr);
        String openid = object.getString("openid");
        String targetopenid = request.getParameter("targetopenid");
        //狗粮活动特色
        System.out.println("shop4 openid " + openid + " targetopenid " + targetopenid);
        //修改目标为自己
        request.getServletContext().setAttribute(openid + "targetopenid", openid);
        System.out.println("点击首页修改目标为自己" + request.getServletContext().getAttribute(openid + "targetopenid"));
//        if (null != targetopenid && !"null".equals(targetopenid) && !"".equals(targetopenid)) {
//            request.getServletContext().setAttribute(openid + "targetopenid", targetopenid);
//            System.out.println("修改目标 " + request.getServletContext().getAttribute(openid + "targetopenid"));
//        } else {
//            System.out.println("未修改目标 " + request.getServletContext().getAttribute(openid + "targetopenid"));
//        }
        response.sendRedirect("shop4/index.jsp?openid=" + openid + "&wx=" + wx.get("id"));
//        Forward.forward(request, response, "shop/index.jsp?openid=" + object.getString("openid") + "&wx=" + wx.get("id"));
//        String openid = object.getString("openid");
//        String accessToken2 = object.getString("access_token");
//        get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken2 + "&openid=" + openid + "&lang=zh_CN");
//        responses = WxMenuUtils.httpclient.execute(get);
//        jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
//        System.out.println("jsonStr  " + jsonStr);
//        object = JSON.parseObject(jsonStr);
//        System.out.println(object.getString("nickname"));
    }

    //网页获取openid
    protected void vip4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String uid = request.getParameter("wx");
//        wx = new HashMap<String, String>();
//        wx.put("id", uid);
        wx = new WxsDAO().getById(wx);
        String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/ForeServlet?method=vip_do&wxsid=" + wx.get("id");
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wx.get("appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    //回调url
    protected void vip4_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wxsid = request.getParameter("wxsid");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        wx.put("id", wxsid);
        wx = new WxsDAO().getById(wx);
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wx.get("appid") + "&secret=" + wx.get("appsecret") + "&code=" + code + "&grant_type=authorization_code");
        CloseableHttpResponse responses = WxMenuUtils.httpclient.execute(get);
        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
        JSONObject object = JSON.parseObject(jsonStr);
        response.sendRedirect("shop5/vip.jsp?openid=" + object.getString("openid") + "&wx=" + wx.get("id"));
//        Forward.forward(request, response, "shop/index.jsp?openid=" + object.getString("openid") + "&wx=" + wx.get("id"));
//        String openid = object.getString("openid");
//        String accessToken2 = object.getString("access_token");
//        get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken2 + "&openid=" + openid + "&lang=zh_CN");
//        responses = WxMenuUtils.httpclient.execute(get);
//        jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
//        System.out.println("jsonStr  " + jsonStr);
//        object = JSON.parseObject(jsonStr);
//        System.out.println(object.getString("nickname"));
    }

    protected void pay4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wxsid = request.getParameter("wxsid");
        //注：如果感觉不安全，建议重新获取一下openid，这里忽略
        String openid = request.getParameter("openid");
        String F_No = request.getParameter("F_No");
        System.out.println("pay4 F_No " + F_No);
        System.out.println("监视付款操作：" + F_No);
        //微信信息
        wx = new HashMap<String, String>();
        wx.put("id", wxsid);
        wx = new WxsDAO().getById(wx);
        //订单信息
        String remark = "";
        DataField order = DaoFactory.getOrderDAO().get(F_No);
        List<DataField> prolist = (List<DataField>) DaoFactory.getBasketDAO().getListNo(F_No);
        for (DataField pro : prolist) {
            remark += pro.getFieldValue("Pname") + "，" + pro.getFieldValue("Pnum") + "件，" + (pro.getFloat("Tot_Price") + pro.getFloat("TSF_Price")) + "元\n";
        }
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);

        //这里判断一下订单状态 支付/未支付
        System.out.println("out_trade_no付款 " + openid + " " + order.getFieldValue("IsPay"));
        if ("1".equals(order.getFieldValue("IsPay"))) {
            String out_trade_no = order.getFieldValue("out_trade_no");
//        if (null == out_trade_no || "".equals(out_trade_no)) {
            out_trade_no = wx.get("mch_id") + WxMenuUtils.format2.format(new Date()) + String.valueOf(new Random().nextInt(10000));//异步传输，异常查询使用;
//        }
            System.out.println("out_trade_no付款 " + openid + " " + out_trade_no);
            try {
                //        out_trade_no = F_No;//重新定义
                DaoFactory.getOrderDAO().modout_trade_no(F_No, out_trade_no);
            } catch (ObjectNotFoundException ex) {
                Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DatabaseException ex) {
                Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            int total_fee = (int) (order.getFloat("SF_Price") * 100 + order.getFloat("TSF_Price") * 100);//分

            //团购
            int tuangouid = RequestUtil.getInt(request, "tuangou");
            System.out.println("tuangouid " + tuangouid);
            DataField tuangou = DaoFactory.getShopactivityDAO().get(tuangouid);
            if (null != tuangou) {
                DataField basket = null;
                for (DataField pro : prolist) {
                    if (pro.getInt("Pid") == tuangou.getInt("limitproduct")) {
                        basket = pro;
                        break;
                    }
                }
                total_fee -= tuangou.getFloat("maaddtype") * basket.getInt("Pnum") * 100;
            }
            System.out.println("total_fee " + total_fee);

            //满减送 现金直接抵现
            int manjiansongid = RequestUtil.getInt(request, "manjiansong");
            System.out.println("manjiansongid " + manjiansongid);
            DataField manjiansong = DaoFactory.getShopactivityDAO().get(manjiansongid);
            if (null != manjiansong) {
                if (1 == manjiansong.getInt("matype")) {
                    total_fee -= manjiansong.getFloat("maaddtype") * 100;
                } else if (2 == manjiansong.getInt("matype")) {
                    total_fee -= order.getFloat("TSF_Price") * 100;
                }
            }
            System.out.println("total_fee " + total_fee);

            //订单返现 直接抵现
            int dingdanfanxianid = RequestUtil.getInt(request, "dingdanfanxian");
            System.out.println("dingdanfanxianid " + dingdanfanxianid);
            DataField dingdanfanxian = DaoFactory.getShopactivityDAO().get(dingdanfanxianid);
            if (null != dingdanfanxian) {
                total_fee -= dingdanfanxian.getFloat("minmoney") / 100 * order.getFloat("SF_Price") * 100;
            }
            System.out.println("total_fee " + total_fee);

            //优惠券 优惠码优惠 优惠码字符串
            int shopactivityid = 0;
            String shopactivityidStr = RequestUtil.getString(request, "shopactivityid");
            System.out.println("shopactivityidStr " + shopactivityidStr);
            String[] macodes = shopactivityidStr.split(",");
            System.out.println("macodes " + macodes);
            if ("-1".equals(macodes[0])) {
                System.out.println("这是优惠码未领取");
                boolean flag = true;
                DataField youhuima = DaoFactory.getShopactivityDAO().getByMaaddtype(macodes[1]);//通用码取法
                if (null == youhuima || (null != youhuima && 0 != youhuima.getInt("matype"))) {
                    DataField shopactivitydetail = DaoFactory.getShopactivitydetailDao().get(macodes[1], 2);//一卡一码取法
                    if (null == shopactivitydetail) {
                        flag = false;
                    } else if (0 != shopactivitydetail.getInt("giftid")) {
                        flag = false;
                    } else {
                        youhuima = DaoFactory.getShopactivityDAO().get(shopactivitydetail.getInt("shopactivityid"));
                    }
                }
                if (null != youhuima) {
                    try {
                        //判断优惠码是否过期
                        Date starttime = WxMenuUtils.format.parse(youhuima.getFieldValue("starttime"));
                        Date endtime = WxMenuUtils.format.parse(youhuima.getFieldValue("endtime"));
                        if (starttime.before(new java.util.Date()) && new java.util.Date().before(endtime)) {//未过期
                            if (0 == youhuima.getFloat("limitorder") || order.getFloat("TF_Price") >= youhuima.getFloat("limitorder")) {
                                //商品限制有两种情况下可用，故作总结果
                                boolean flag1 = false;
                                if (!"0".equals(youhuima.getFieldValue("limitproduct"))) {
                                    //循环该订单产品有无符合优惠券指定产品的
                                    for (DataField pro : prolist) {
                                        if (-1 != youhuima.getFieldValue("limitproduct").indexOf(("," + pro.getFieldValue("Pid") + ","))) {
                                            flag1 = true;
                                            break;
                                        }
                                    }
                                }
                                if (!flag1) {
                                    flag = false;
                                }
                                //判断用户领取次数限制
                                if (flag) {
                                    int giftcount = DaoFactory.getGiftsDAO().getByOpenidGiftidList(openid, youhuima.getInt("id")).size();
                                    if (giftcount >= youhuima.getInt("limitpercounts")) {
                                        flag = false;
                                    }
                                }
                            } else {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }

                    } catch (ParseException ex) {
                        Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    flag = false;
                }

                if (flag) {
                    System.out.println("shopactivityv支付前优惠 " + youhuima.getFloat("moneys"));
                    total_fee -= (int) (youhuima.getFloat("moneys") * 100);
                }
            } else if (!"0".equals(shopactivityidStr)) {//0未使用优惠
                System.out.println("这是优惠已领取");
                shopactivityid = Integer.parseInt(shopactivityidStr);
                System.out.println("shopactivityid " + shopactivityid);
                DataField shopactivity = DaoFactory.getShopactivityDAO().get(shopactivityid);
                //校验一下是否已领取
                System.out.println(DaoFactory.getGiftsDAO().getByOpenidGiftidList(openid, shopactivityid));
                int giftCount = DaoFactory.getGiftsDAO().getByOpenidGiftidList(openid, shopactivityid).size();
                if (0 < giftCount) {
                    System.out.println("shopactivityv支付前优惠 " + shopactivity.getFloat("moneys"));
                    total_fee -= (int) (shopactivity.getFloat("moneys") * 100);
                } else {
                    System.out.println("shopactivityv不是自己的优惠 " + shopactivityid);
                }
            }
            System.out.println("total_fee " + total_fee);

            //赠品
            int zengpinid = RequestUtil.getInt(request, "zengpin");


            //统一下单参数
            WxPayUtils wxPayUtils = new WxPayUtils();
            Map<String, String> map = new HashMap<String, String>();
            map.put("appid", wx.get("appid"));//公众账号id
            map.put("mch_id", wx.get("mch_id"));//商户号
//        map.put("device_info", "WEB");//设备号 否
            map.put("nonce_str", String.valueOf(System.currentTimeMillis() / 1000));//随机字符串
            map.put("body", remark);//商品描述
//        map.put("detail", "商品详情");//商品详情 否
            System.out.println("fukuancanshu " + F_No + "," + tuangouid + "," + manjiansongid + "," + dingdanfanxianid + "," + shopactivityidStr);
            map.put("attach", F_No + "," + tuangouid + "," + manjiansongid + "," + dingdanfanxianid + "," + zengpinid + "," + shopactivityidStr);//附加数据 否 优惠标记,用于核销优惠
            map.put("out_trade_no", out_trade_no);//商户订单号
//        map.put("fee_type", "CNY");//货币类型 否
            map.put("total_fee", String.valueOf(total_fee));//总金额 分
            map.put("spbill_create_ip", request.getRemoteAddr());//终端ip
//        map.put("time_start", WxMenuUtils.format.format(new Date()));//交易起始时间 否
//        map.put("time_expire", WxMenuUtils.format.format(new Date()));//交易结束时间 否
//        map.put("goods_tag", "商品标记");//商品标记 否
            map.put("notify_url", request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/Wxpaynotify_urlServlet4");//通知地址
            map.put("trade_type", "JSAPI");//交易类型
//        map.put("product_id", "");//商品id 否 native必传
            map.put("openid", openid);//用户标识 否 jsapi必传
            //下单
            map = wxPayUtils.payUnifiedorder(map, wx.get("wxpaykey"));
            String prepay_id = (String) map.get("prepay_id");
            System.out.println("prepay_id   " + prepay_id);

            if (null != prepay_id) {
                Map jsapimap = new HashMap<String, String>();
                jsapimap.put("appId", map.get("appid"));
                jsapimap.put("timeStamp", timeStamp);
                jsapimap.put("nonceStr", map.get("nonce_str"));
                jsapimap.put("package", "prepay_id=" + prepay_id);
                jsapimap.put("signType", "MD5");
                String paySign = wxPayUtils.getSignature(jsapimap, wx.get("wxpaykey"));
                jsapimap.put("packages", "prepay_id=" + prepay_id);//package疑似关键字，用packages代替传输
                jsapimap.put("paySign", paySign);
                System.out.println(jsapimap.get("appId") + " " + jsapimap.get("timeStamp") + " " + jsapimap.get("nonceStr") + " " + prepay_id + " " + "MD5" + " " + paySign);
                //附加参数
                jsapimap.put("F_No", F_No);
                jsapimap.put("out_trade_no", out_trade_no);
                jsapimap.put("openid", openid);

                request.setAttribute("jsapimap", jsapimap);
                request.setAttribute("wx", wx);

                //取出订单明细
                int pnum = 0;
                List<DataField> basketList = (List<DataField>) DaoFactory.getBasketDAO().getListNo(order.getFieldValue("F_No"));
                for (DataField basket : basketList) {
                    System.out.println("basket.getInt(\"Pid\") " + basket.getInt("Pid"));
                    if (1 == basket.getInt("Pid")) {
                        pnum += basket.getInt("Pnum");
                    }
                }
                request.setAttribute("pnum", pnum * 5);
                Forward.forward(request, response, "/fore/jsapi/jsapi4.jsp");
            } else {
                //错误处理
                System.out.println("统一下单失败！");
                Forward.forward(request, response, "/shop5/vip.jsp?act=order&wx=" + wxsid + "&openid=" + openid);
            }
        } else {
            //已处理
            System.out.println("订单支付失败，该订单已支付！" + F_No);
            Forward.forward(request, response, "/shop5/vip.jsp?act=order&wx=" + wxsid + "&openid=" + openid);
        }
    }

    //退款
    protected void refund4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wxsid = request.getParameter("wxsid");
        String openid = request.getParameter("openid");
//        String transaction_id=request.getParameter("transaction_id");
        String F_No = request.getParameter("F_No");
        System.out.println("监视退货操作：" + F_No);
        System.out.println("wxsid " + wxsid + " openid " + openid + " F_No " + F_No);
        wx = new HashMap<String, String>();
        wx.put("id", wxsid);
        wx = new WxsDAO().getById(wx);
        DataField order = DaoFactory.getOrderDAO().get(F_No);

        if (null != order && ("1".equals(order.getFieldValue("Sts")) || "3".equals(order.getFieldValue("Sts")))) {
            System.out.println("退货out_trade_no " + order.getFieldValue("out_trade_no"));
            //更改订单状态
            try {
                DaoFactory.getOrderDAO().modStsByout_trade_no(order.getFieldValue("out_trade_no"), 6);
            } catch (ObjectNotFoundException ex) {
                Logger.getLogger(WxPayUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DatabaseException ex) {
                Logger.getLogger(WxPayUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("该状态不允许退款out_trade_no " + order.getFieldValue("out_trade_no") + " Sts" + order.getFieldValue("Sts"));
        }

//        Map<String, String> map = new HashMap<String, String>();
//        map.put("appid", wx.get("appid"));
//        map.put("mch_id", wx.get("mch_id"));
////        map.put("device_info", "");//否
//        map.put("nonce_str", String.valueOf(System.currentTimeMillis() / 1000));
////        map.put("transaction_id", transaction_id);//微信订单号
//        map.put("out_trade_no", order.getFieldValue("out_trade_no"));//商户订单号
//        map.put("out_refund_no", "-" + order.getFieldValue("out_trade_no"));//商户退款单号
//        map.put("total_fee", String.valueOf((int) (order.getFloat("SF_Price") * 100)));//总金额
//        map.put("refund_fee", String.valueOf((int) (order.getFloat("SF_Price") * 100)));//退款金额
////        map.put("refund_fee_type", "CNY");//货币种类 否
//        map.put("op_user_id", wx.get("mch_id"));//操作员
//        map = new WxPayUtils().payrefund(map, wx);
        Forward.forward(request, response, "/shop5/vip.jsp?act=order&wx=" + wxsid + "&openid=" + openid);
    }

    protected void productdetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = RequestUtil.getString(request, "id");
        String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/ForeServlet?method=productdetail_do&id=" + id;
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wx.get("appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    //回调url
    protected void productdetail_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = RequestUtil.getString(request, "id");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxReader.getWxInfo().get("AppId") + "&secret=" + WxReader.getWxInfo().get("AppSecret") + "&code=" + code + "&grant_type=authorization_code");
        CloseableHttpResponse responses = WxMenuUtils.httpclient.execute(get);
        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
        JSONObject object = JSON.parseObject(jsonStr);
        response.sendRedirect("/shop5/shop.jsp?act=detail&id=" + id + "&wx=1&openid=" + object.getString("openid"));
    }

    protected void modIsPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Fnos = RequestUtil.getString(request, "Fnos");
        int Sts = RequestUtil.getInt(request, "Sts");
        PrintWriter out = response.getWriter();
        //判断状态
        DataField order = DaoFactory.getOrderDAO().get(Fnos);
        if (0 == order.getInt("Sts")) {
            boolean flag = DaoFactory.getOrderDAO().modIsPay(Fnos, Sts);
            if (flag) {
                out.print("0");
            } else {
                out.print("2");
            }
        } else {
            out.print("1");
        }
        out.close();
    }

    /**
     * 狗粮活动结束
     */
    protected void youhuimalingqu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maaddtype = RequestUtil.getString(request, "maaddtype");
        String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
        if (ua.indexOf("micromessenger") > 0) {
            wx = new WxsDAO().getById(wx);
            String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/shop4/shopactivity/youhuima.jsp?maaddtype=" + maaddtype;
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wx.get("appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
        } else {
            response.sendRedirect(request.getContextPath() + "/shop4/shopactivity/youhuima.jsp?maaddtype=" + maaddtype);
        }
    }

    protected void weilingquyouhuimatest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String openid = RequestUtil.getString(request, "openid");
        String Fnos = RequestUtil.getString(request, "Fnos");
        String weilingquyouhuima = RequestUtil.getString(request, "weilingquyouhuima");
        PrintWriter out = response.getWriter();
        DataField youhuima = DaoFactory.getShopactivityDAO().getByMaaddtype(weilingquyouhuima);//通用码取法
        if (null == youhuima || (null != youhuima && 0 != youhuima.getInt("matype"))) {
            DataField shopactivitydetail = DaoFactory.getShopactivitydetailDao().get(weilingquyouhuima, 2);//一卡一码取法
            if (null == shopactivitydetail) {
                out.print("1");//无效
                out.close();
                return;
            } else if (0 != shopactivitydetail.getInt("giftid")) {
                out.print("6");//已被领取
                out.close();
                return;
            } else {
                youhuima = DaoFactory.getShopactivityDAO().get(shopactivitydetail.getInt("shopactivityid"));
            }
        }
        if (null != youhuima) {
            try {
                //判断优惠码是否过期
                Date starttime = WxMenuUtils.format.parse(youhuima.getFieldValue("starttime"));
                Date endtime = WxMenuUtils.format.parse(youhuima.getFieldValue("endtime"));
                if (starttime.before(new java.util.Date()) && new java.util.Date().before(endtime)) {//未过期
                    DataField order = DaoFactory.getOrderDAO().get(Fnos);
                    if (0 == youhuima.getFloat("limitorder") || order.getFloat("TF_Price") >= youhuima.getFloat("limitorder")) {
                        //商品限制有两种情况下可用，故作总结果
                        boolean flag = false;
                        if ("0".equals(youhuima.getFieldValue("limitproduct"))) {
                            flag = true;
                        } else {
                            //循环该订单产品有无符合优惠券指定产品的
                            List<DataField> prolist = (List<DataField>) DaoFactory.getBasketDAO().getListNo(order.getFieldValue("F_No"));
                            for (DataField pro : prolist) {
                                if (-1 != youhuima.getFieldValue("limitproduct").indexOf(("," + pro.getFieldValue("Pid") + ","))) {
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag) {
                                out.print("4");//未满足指定产品
                                out.close();
                                return;
                            }
                        }
                        //判断用户领取次数限制
                        if (flag) {
                            int giftcount = DaoFactory.getGiftsDAO().getByOpenidGiftidList(openid, youhuima.getInt("id")).size();
                            if (giftcount >= youhuima.getInt("limitpercounts")) {
                                out.print("5");//领取次数已到
                                out.close();
                                return;
                            } else {
                                out.print("minus," + youhuima.getFieldValue("moneys"));//所有条件满足可以使用
                            }
                        }
                    } else {
                        out.print("3");//未满足指定金额
                        out.close();
                        return;
                    }
                } else {
                    out.print("2");//已过期
                    out.close();
                    return;
                }
                //判断已领取次数加上这次是否超额
            } catch (ParseException ex) {
                Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        out.close();
    }

    protected void tuangou(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shopactivityid = request.getParameter("shopactivityid");
        String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
        if (ua.indexOf("micromessenger") > 0) {
            wx = new WxsDAO().getById(wx);
            String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/shop4/shopactivity/tuangou.jsp?shopactivityid=" + shopactivityid;
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wx.get("appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
        } else {
            response.sendRedirect(request.getContextPath() + "/shop4/shopactivity/tuangou.jsp?shopactivityid=" + shopactivityid);
        }
    }

    protected void paimai(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paimaiid = request.getParameter("paimaiid");
        String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
        if (ua.indexOf("micromessenger") > 0) {
            wx = new WxsDAO().getById(wx);
            String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/shop4/shopactivity/paimai.jsp?paimaiid=" + paimaiid;
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wx.get("appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
        } else {
            response.sendRedirect(request.getContextPath() + "/shop4/shopactivity/paimai.jsp?paimaiid=" + paimaiid);
        }
    }

    protected void paimai_fore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = RequestUtil.getInt(request, "id");
        String openid = RequestUtil.getString(request, "openid");
        DataField shopactivity = DaoFactory.getShopactivityDAO().get(id);
        //判断活动有无截止
//        判断商品是否售完

        PrintWriter out = response.getWriter();
        //核算价格
        float paistartprice = shopactivity.getFloat("paistartprice");
        float paiminprice = shopactivity.getFloat("paiminprice");
        float currprice = paistartprice;
        Date starttime = new Date();
        Date endtime = new Date();
        try {
            starttime = WxMenuUtils.format.parse(shopactivity.getFieldValue("starttime"));
            endtime = WxMenuUtils.format.parse(shopactivity.getFieldValue("endtime"));
        } catch (ParseException ex) {
            Logger.getLogger(ForeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        int alminus = (int) (endtime.getTime() - starttime.getTime()) / 1000 / 60;
        System.out.println("alminus " + alminus);
        currprice = paiminprice < (paistartprice - alminus * shopactivity.getFloat("paiperminusprice")) ? (paistartprice - alminus * shopactivity.getFloat("paiperminusprice")) : paiminprice;

        if (starttime.before(new Date()) && new Date().before(endtime)) {
            List<DataField> giftList = DaoFactory.getGiftsDAO().getList(id, 7);
            if (giftList.size() < shopactivity.getInt("paicounts")) {
                DaoFactory.getGiftsDAO().add(openid, currprice, id, "", 0, WxMenuUtils.format.format(new Date()), "拍卖点击", 7);
                out.print("0," + currprice);
            } else {
                out.print("2,");
            }
        } else {
            out.print("1,");
        }
    }

    protected void wx2web(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String parentid = request.getParameter("id");
        parentid = null == parentid ? "0" : parentid;
        NewstypesDAO newstypesDAO = new NewstypesDAO();
        List<Map<String, String>> mapsList = newstypesDAO.getList(sid, parentid);
        request.setAttribute("sid", sid);
        request.setAttribute("mapsList", mapsList);

        //logo
        String logo = "";
        List<Map<String, String>> logoList = newstypesDAO.getList("14", "0");
        if (0 < logoList.size()) {
            logo = logoList.get(0).get("img");
        }
        request.setAttribute("logo", logo);
        Forward.forward(request, response, "/fore/web/index.jsp");
    }

    protected void wx2webnews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String newstypeid = request.getParameter("id");
        List<Map<String, String>> mapsList = new NewsDAO().getList(sid, newstypeid);
        request.setAttribute("sid", sid);
        request.setAttribute("mapsList", mapsList);
        Forward.forward(request, response, "/fore/web/newslist.jsp");
    }

    protected void news(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map = new NewsDAO().getById(map);
        request.setAttribute("map", map);
        Forward.forward(request, response, "/fore/web/news.jsp");
    }

    protected void wx2forum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取出所有话题
        String openid = request.getParameter("openid");
        String sid = request.getParameter("sid");
        NewstypesDAO newstypesDAO = new NewstypesDAO();
        Map<Map<String, String>, List<Map<String, String>>> map = new LinkedHashMap<Map<String, String>, List<Map<String, String>>>();
        List<Map<String, String>> mapList = newstypesDAO.getList(sid, "0");
        //附加回复
        for (Map<String, String> map1 : mapList) {
            List<Map<String, String>> list = newstypesDAO.getList(sid, map1.get("id"));
            map.put(map1, list);
        }
        request.setAttribute("map", map);
        request.setAttribute("sid", sid);
        request.setAttribute("openid", openid);
        Forward.forward(request, response, "/fore/forum/index.jsp");
    }

    protected void wx2forum_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nc = request.getParameter("nc");
        String msg = request.getParameter("msg");
        String pid = request.getParameter("pid");
        String sid = request.getParameter("sid");
        String openid = request.getParameter("openid");
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", nc);
        map.put("img", "");
        map.put("parentid", pid);
        map.put("parentname", "");
        map.put("remark", msg);
        map.put("times", WxMenuUtils.format.format(new Date()));
        map.put("sid", sid);
        map.put("uid", "0");//int
        new NewstypesDAO().add(map);
        PrintWriter out = response.getWriter();
        out.print("ok");
        out.close();
    }

    protected void shopw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wxsid = request.getParameter("wx");
        wx = new HashMap<String, String>();
        wx.put("id", wxsid);
        wx = new WxsDAO().getById(wx);
        String redirectUri = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/ForeServlet?method=shopw_do&wxsid=" + wx.get("id");
        response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wx.get("appid") + "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
    }

    //回调url
    protected void shopw_do(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wxsid = request.getParameter("wxsid");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        wx.put("id", wxsid);
        wx = new WxsDAO().getById(wx);
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wx.get("appid") + "&secret=" + wx.get("appsecret") + "&code=" + code + "&grant_type=authorization_code");
        CloseableHttpResponse responses = WxMenuUtils.httpclient.execute(get);
        String jsonStr = EntityUtils.toString(responses.getEntity(), "utf-8");
        JSONObject object = JSON.parseObject(jsonStr);
        response.sendRedirect("shop/index.jsp?openid=" + object.getString("openid") + "&wx=" + wx.get("id"));
    }
}
