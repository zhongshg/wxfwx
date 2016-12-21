/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import wap.wx.util.DbConn;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class SubscriberpromoteDAO {

    public List<Map<String, String>> getList(PageUtil pu, String parentopenid, String subscriberdate) {
        List<Map<String, String>> subscriberList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select scp.id,scp.openid,scp.nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times,parentopenid,salemoney,commission,isvip,vipid,qrcode,qrcodemediaid,qrcodemediaidtimes,issubscriber,sm.nickname smname from subscriberpromote scp left join salemember sm on scp.parentopenid=sm.openid where 1=1";
        if (!"".equals(parentopenid)) {
            sql += " and parentopenid='" + parentopenid + "'";
        }
        if (!"".equals(subscriberdate)) {
            sql += " and times like '" + subscriberdate + "%'";
        }
        sql += " order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> subscriber = new HashMap<String, String>();
                subscriber.put("id", rs.getString("id"));
                subscriber.put("openid", rs.getString("openid"));
                subscriber.put("nickname", rs.getString("nickname"));
                subscriber.put("sex", rs.getString("sex"));
                subscriber.put("language", rs.getString("language"));
                subscriber.put("city", rs.getString("city"));
                subscriber.put("province", rs.getString("province"));
                subscriber.put("country", rs.getString("country"));
                subscriber.put("headimgurl", rs.getString("headimgurl"));
                subscriber.put("unionid", rs.getString("unionid"));
                subscriber.put("percent", rs.getString("percent"));
                subscriber.put("remark", rs.getString("remark"));
                subscriber.put("times", rs.getString("times"));
                subscriber.put("parentopenid", rs.getString("parentopenid"));
                subscriber.put("salemoney", rs.getString("salemoney"));
                subscriber.put("commission", rs.getString("commission"));
                subscriber.put("isvip", rs.getString("isvip"));
                subscriber.put("vipid", rs.getString("vipid"));
                subscriber.put("qrcode", rs.getString("qrcode"));
                subscriber.put("qrcodemediaid", rs.getString("qrcodemediaid"));
                subscriber.put("qrcodemediaidtimes", rs.getString("qrcodemediaidtimes"));
                subscriber.put("issubscriber", rs.getString("issubscriber"));
                subscriber.put("smname", rs.getString("smname"));
                subscriberList.add(subscriber);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return subscriberList;
    }

    public int getCount(String parentopenid, String issubscriber, String subscriberdate) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from subscriberpromote where 1=1";
        if (!"".equals(parentopenid)) {
            sql += " and parentopenid='" + parentopenid + "'";
        }
        if (!"".equals(issubscriber)) {
            sql += " and issubscriber=" + issubscriber;
        }
        if (!"".equals(subscriberdate)) {
            sql += " and times like '" + subscriberdate + "%'";
        }
        sql += " order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public Map<String, String> getByOpenid(Map<String, String> subscriber) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times,issubscriber from subscriberpromote where openid=? and issubscriber=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, subscriber.get("openid"));
            ptst.setInt(2, Integer.parseInt(subscriber.get("issubscriber")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                subscriber = new HashMap<String, String>();
                subscriber.put("id", rs.getString("id"));
                subscriber.put("openid", rs.getString("openid"));
                subscriber.put("nickname", rs.getString("nickname"));
                subscriber.put("sex", rs.getString("sex"));
                subscriber.put("language", rs.getString("language"));
                subscriber.put("city", rs.getString("city"));
                subscriber.put("province", rs.getString("province"));
                subscriber.put("country", rs.getString("country"));
                subscriber.put("headimgurl", rs.getString("headimgurl"));
                subscriber.put("unionid", rs.getString("unionid"));
                subscriber.put("percent", rs.getString("percent"));
                subscriber.put("remark", rs.getString("remark"));
                subscriber.put("times", rs.getString("times"));
                subscriber.put("issubscriber", rs.getString("issubscriber"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return subscriber;
    }

    public void updateIssubscriberByOpenid(String openid, String yuanissubscriber, String issubscriber) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update subscriberpromote set issubscriber=? where openid=? and issubscriber=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, issubscriber);
            ptst.setString(2, openid);
            ptst.setString(3, yuanissubscriber);
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void add(Map<String, String> subscriber) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into subscriberpromote(openid,nickname,sex,language,city,province,country,headimgurl,unionid,percent,remark,times,wxsid,parentopenid,salemoney,commission,isvip,vipid,qrcode,qrcodemediaid,qrcodemediaidtimes,issubscriber) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, subscriber.get("openid"));
            ptst.setString(2, subscriber.get("nickname"));
            ptst.setInt(3, Integer.parseInt(subscriber.get("sex")));
            ptst.setString(4, subscriber.get("language"));
            ptst.setString(5, subscriber.get("city"));
            ptst.setString(6, subscriber.get("province"));
            ptst.setString(7, subscriber.get("country"));
            ptst.setString(8, subscriber.get("headimgurl"));
            ptst.setString(9, subscriber.get("unionid"));
            ptst.setInt(10, Integer.parseInt(subscriber.get("percent")));
            ptst.setString(11, subscriber.get("remark"));
            ptst.setString(12, subscriber.get("times"));
            ptst.setInt(13, 0);
            ptst.setString(14, subscriber.get("parentopenid"));
            ptst.setBigDecimal(15, new BigDecimal(subscriber.get("salemoney")));
            ptst.setBigDecimal(16, new BigDecimal(subscriber.get("commission")));
            ptst.setInt(17, Integer.parseInt(subscriber.get("isvip")));
            ptst.setString(18, subscriber.get("vipid"));
            ptst.setString(19, subscriber.get("qrcode"));
            ptst.setString(20, subscriber.get("qrcodemediaid"));
            ptst.setString(21, subscriber.get("qrcodemediaidtimes"));
            ptst.setInt(22, Integer.parseInt(subscriber.get("issubscriber")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }
}
