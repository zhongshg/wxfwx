/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wap.wx.dao;

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
public class SaleMemberDAO {

    public List<Map<String, String>> getList(PageUtil pu, String openid, String issm) {
        List<Map<String, String>> subscriberList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,nickname,issm,saleqrcode from salemember where 1=1";
        if (!"".equals(openid)) {
            sql += " and openid='" + openid + "'";
        }
        if (!"".equals(issm)) {
            sql += " and issm=" + issm;
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
                subscriber.put("issm", rs.getString("issm"));
                subscriber.put("saleqrcode", rs.getString("saleqrcode"));
                subscriberList.add(subscriber);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return subscriberList;
    }

    public int getCount(String openid, String issm) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from salemember where 1=1";
        if (!"".equals(openid)) {
            sql += " and openid='" + openid + "'";
        }
        if (!"".equals(issm)) {
            sql += " and issm=" + issm;
        }
        sql += " order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public Map<String, String> getByOpenid(Map<String, String> subscriber) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,nickname,issm,saleqrcode from salemember where openid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, subscriber.get("openid"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                subscriber = new HashMap<String, String>();
                subscriber.put("id", rs.getString("id"));
                subscriber.put("openid", rs.getString("openid"));
                subscriber.put("nickname", rs.getString("nickname"));
                subscriber.put("issm", rs.getString("issm"));
                subscriber.put("saleqrcode", rs.getString("saleqrcode"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubscriberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return subscriber;
    }

    public void add(Map<String, String> subscriber) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into salemember(openid,nickname,issm,saleqrcode) values (?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, subscriber.get("openid"));
            ptst.setString(2, subscriber.get("nickname"));
            ptst.setInt(3, Integer.parseInt(subscriber.get("issm")));
            ptst.setString(4, subscriber.get("saleqrcode"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SaleMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void updateIssm(String openid, String issm) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update salemember set issm=? where openid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(issm));
            ptst.setString(2, openid);
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SaleMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void updateqrcode(String openid, String saleqrcode) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update salemember set saleqrcode=? where openid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, saleqrcode);
            ptst.setString(2, openid);
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SaleMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }
}
