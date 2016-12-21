/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.service;

import com.alibaba.fastjson.JSONObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import job.tot.bean.DataField;
import job.tot.dao.DaoFactory;
import wap.wx.dao.SubscriberDAO;
import wap.wx.menu.WxMenuUtils;
import wap.wx.util.DbConn;

/**
 *
 * @author Administrator
 */
public class SubscriberService {

    public void delete(Map<String, String> subscriber, int wxsid, HttpServletRequest request) {
        Connection conn = DbConn.getConn();
        try {
            conn.setAutoCommit(false);
            if (!"".equals(subscriber.get("qrcode")) && null != subscriber.get("qrcode")) {
                java.io.File oldFile = new java.io.File(request.getServletContext()
                        .getRealPath(subscriber.get("qrcode")));
                oldFile.delete();
            }
            new SubscriberDAO().delete(subscriber, conn);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteqrcode(String wxsid, HttpServletRequest request) {
        Connection conn = DbConn.getConn();
        try {
            conn.setAutoCommit(false);
            SubscriberDAO subscriberDAO = new SubscriberDAO();
            List<Map<String, String>> subscriberList = subscriberDAO.getList();
            for (Map<String, String> subscriber : subscriberList) {
                if (!"".equals(subscriber.get("qrcode")) && null != subscriber.get("qrcode")) {
                    java.io.File oldFile = new java.io.File(request.getServletContext()
                            .getRealPath(subscriber.get("qrcode")));
                    oldFile.delete();
                }
            }
            new SubscriberDAO().deleteqrcode(wxsid, "", "", conn);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addSubscriber(Map<String, String> wx, String openid) {
        String parentopenid = "0";
        SubscriberDAO subscriberDAO = new SubscriberDAO();
        Map<String, String> subscriber = new HashMap<String, String>();
        subscriber.put("openid", openid);
        subscriber.put("percent", "0");
        subscriber.put("remark", "");
        subscriber.put("times", WxMenuUtils.format.format(new Date()));
        subscriber.put("wxsid", wx.get("id"));
        subscriber.put("parentopenid", parentopenid);

        //vipid 作为相对id
//        int tempcount = subscriberDAO.getCount(Integer.parseInt(wx.get("id")), parentopenid);
        JSONObject object = WxMenuUtils.getUserInfo(openid);
        object = null != object ? object : new JSONObject();
        System.out.println(object.toJSONString());
        subscriber.put("nickname", object.getString("nickname"));
        subscriber.put("sex", null != object.getString("sex") ? object.getString("sex") : "0");
        subscriber.put("language", object.getString("language"));
        subscriber.put("city", object.getString("city"));
        subscriber.put("province", object.getString("province"));
        subscriber.put("country", object.getString("country"));
        subscriber.put("headimgurl", object.getString("headimgurl"));
        subscriber.put("unionid", object.getString("unionid"));
        try {
            subscriberDAO.add(subscriber);
        } catch (Exception e) {
            System.out.println("用户关注nickname特殊字符,数据库存储error,存储空！");
            subscriber.put("nickname", "");
            subscriberDAO.add(subscriber);
        }
    }

    //活动专用
    public boolean addSubscriber(JSONObject object, String openid) {
        boolean flag = false;
        String parentopenid = "0";
        SubscriberDAO subscriberDAO = new SubscriberDAO();
        Map<String, String> subscriber = new HashMap<String, String>();
        subscriber.put("openid", openid);
        subscriber.put("percent", "0");
        subscriber.put("remark", "");
        subscriber.put("times", WxMenuUtils.format.format(new Date()));
        //parentopenid,salemoney,commission,isvip,vipid
        subscriber.put("parentopenid", parentopenid);
        subscriber.put("salemoney", "0");
        subscriber.put("commission", "0");
        subscriber.put("isvip", "0");

        //vipid 作为相对id
//        int tempcount = subscriberDAO.getCount(Integer.parseInt(wx.get("id")), parentopenid);
        int vipid = subscriberDAO.getMaxid();
        subscriber.put("vipid", String.valueOf(vipid + 1));
        subscriber.put("wxsid", "1");

        subscriber.put("qrcode", "");
        subscriber.put("qrcodemediaid", "");
        subscriber.put("qrcodemediaidtimes", String.valueOf(System.currentTimeMillis() / 1000 - 9 * 24 * 60 * 60));

        subscriber.put("nickname", object.getString("nickname"));
        subscriber.put("sex", null != object.getString("sex") ? object.getString("sex") : "0");
        subscriber.put("language", object.getString("language"));
        subscriber.put("city", object.getString("city"));
        subscriber.put("province", object.getString("province"));
        subscriber.put("country", object.getString("country"));
        subscriber.put("headimgurl", object.getString("headimgurl"));
        subscriber.put("unionid", object.getString("unionid"));
        try {
            subscriberDAO.add(subscriber);
            flag = true;
        } catch (Exception e) {
            System.out.println("用户关注nickname特殊字符,数据库存储error,存储空！");
            subscriber.put("nickname", "");
            subscriberDAO.add(subscriber);
            flag = true;
        }
        return flag;
    }

    public float totalcanmoney(Connection conn, SubscriberDAO subscriberDAO, Map<String, String> subscriber, Map<String, String> wx) {
        //计算可提现金额
        float totalorderalendYJ = 0;//已完成订单总佣金
        List<Map<String, String>> firstsubscriberList = subscriberDAO.getByParentopenidList(subscriber.get("id"), conn);
//        firstsubscribercount = firstsubscriberList.size();
        for (Map<String, String> firstsubscriber : firstsubscriberList) {
            //计算单数、金额
            List<DataField> firstorderList = (List<DataField>) DaoFactory.getOrderDAO().getList(wx.get("id"), firstsubscriber.get("openid"), -1, conn);
            for (DataField order : firstorderList) {
                switch (order.getInt("Sts")) {
//                    case 0:
//                        totalordernobuyYJ += order.getFloat("FirstYJ");
//                        totalordernobuymoney += order.getFloat("SF_Price");
//                        firstordernobuycount++;
//                        break;
//                    case 1:
//                        totalorderYJ += order.getFloat("FirstYJ");
//                        totalordermoney += order.getFloat("SF_Price");
//                        firstordercount++;
//                        break;
//                    case 2://已发货视为已付款
//                        totalorderYJ += order.getFloat("FirstYJ");
//                        totalordermoney += order.getFloat("SF_Price");
//                        firstordercount++;
//                        break;
//                    case 3:
//                        totalorderalthingYJ += order.getFloat("FirstYJ");
//                        totalordermoney += order.getFloat("SF_Price");
//                        firstordercount++;
//                        break;
                    case 5:
                        totalorderalendYJ += order.getFloat("FirstYJ");
                        break;
                }
            }

            List<Map<String, String>> secondsubscriberList = subscriberDAO.getByParentopenidList(firstsubscriber.get("id"), conn);
//            secondsubscribercount += secondsubscriberList.size();
            for (Map<String, String> secondsubscriber : secondsubscriberList) {

                //计算单数、金额
                List<DataField> secondorderList = (List<DataField>) DaoFactory.getOrderDAO().getList(wx.get("id"), secondsubscriber.get("openid"), -1, conn);
                for (DataField order : secondorderList) {
                    switch (order.getInt("Sts")) {
//                        case 0:
//                            totalordernobuyYJ += order.getFloat("SecondYJ");
//                            totalordernobuymoney += order.getFloat("SF_Price");
//                            secondordernobuycount++;
//                            break;
//                        case 1:
//                            totalorderYJ += order.getFloat("SecondYJ");
//                            totalordermoney += order.getFloat("SF_Price");
//                            secondordercount++;
//                            break;
//                        case 2://已发货视为已付款
//                            totalorderYJ += order.getFloat("SecondYJ");
//                            totalordermoney += order.getFloat("SF_Price");
//                            secondordercount++;
//                            break;
//                        case 3:
//                            totalorderalthingYJ += order.getFloat("SecondYJ");
//                            totalordermoney += order.getFloat("SF_Price");
//                            secondordercount++;
//                            break;
                        case 5:
                            totalorderalendYJ += order.getFloat("SecondYJ");
                            break;
                    }
                }

                List<Map<String, String>> thirdsubscriberList = subscriberDAO.getByParentopenidList(secondsubscriber.get("id"), conn);
//                thirdsubscribercount += thirdsubscriberList.size();

                for (Map<String, String> thirdsubscriber : thirdsubscriberList) {

                    //计算单数、金额
                    List<DataField> thirdorderList = (List<DataField>) DaoFactory.getOrderDAO().getList(wx.get("id"), thirdsubscriber.get("openid"), -1, conn);
                    for (DataField order : thirdorderList) {
                        switch (order.getInt("Sts")) {
//                            case 0:
//                                totalordernobuyYJ += order.getFloat("ThirdYJ");
//                                totalordernobuymoney += order.getFloat("SF_Price");
//                                thirdordernobuycount++;
//                                break;
//                            case 1:
//                                totalorderYJ += order.getFloat("ThirdYJ");
//                                totalordermoney += order.getFloat("SF_Price");
//                                thirdordercount++;
//                                break;
//                            case 2://已发货视为已付款
//                                totalorderYJ += order.getFloat("ThirdYJ");
//                                totalordermoney += order.getFloat("SF_Price");
//                                thirdordercount++;
//                                break;
//                            case 3:
//                                totalorderalthingYJ += order.getFloat("ThirdYJ");
//                                totalordermoney += order.getFloat("SF_Price");
//                                thirdordercount++;
//                                break;
                            case 5:
                                totalorderalendYJ += order.getFloat("ThirdYJ");
                                break;
                        }
                    }
                }
            }
        }
        //已提现财富(含申请)
        float totaltx = 0;
        List<DataField> txList = (List<DataField>) DaoFactory.getFundsDao().getList(0, 0, "", subscriber.get("openid"), "", -1, wx.get("id"), -1, -1);
        for (DataField tx : txList) {
            totaltx += tx.getFloat("F_Price");
        }
        return totalorderalendYJ - totaltx;
    }
}
