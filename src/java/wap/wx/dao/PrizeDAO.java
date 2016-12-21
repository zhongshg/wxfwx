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
public class PrizeDAO {

    public List<Map<String, String>> getByActivityList(Map<String, String> activity) {
        List<Map<String, String>> prizeList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,codeid,codename,name,num,discounts,aid,remark from prize where aid=? order by codeid asc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(activity.get("id")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> prize = new HashMap<String, String>();
                prize.put("id", rs.getString("id"));
                prize.put("codeid", rs.getString("codeid"));
                prize.put("codename", rs.getString("codename"));
                prize.put("name", rs.getString("name"));
                prize.put("num", rs.getString("num"));
                prize.put("discounts", rs.getString("discounts"));
                prize.put("aid", rs.getString("aid"));
                prize.put("remark", rs.getString("remark"));
                prizeList.add(prize);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return prizeList;
    }

    public List<Map<String, String>> getList(PageUtil pu) {
        List<Map<String, String>> prizeList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select p.id,p.codeid,p.codename,p.name,p.num,p.discounts,p.aid,a.name aname,p.remark from prize p left join activity a on p.aid=a.id order by aid,codeid asc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> prize = new HashMap<String, String>();
                prize.put("id", rs.getString("id"));
                prize.put("codeid", rs.getString("codeid"));
                prize.put("codename", rs.getString("codename"));
                prize.put("name", rs.getString("name"));
                prize.put("num", rs.getString("num"));
                prize.put("discounts", rs.getString("discounts"));
                prize.put("aid", rs.getString("aid"));
                prize.put("aname", rs.getString("aname"));
                prize.put("remark", rs.getString("remark"));
                prizeList.add(prize);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return prizeList;
    }

    public int getConut() {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from prize";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public Map<String, String> getById(Map<String, String> prize) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select p.id,p.codeid,p.codename,p.name,p.num,p.discounts,p.aid,a.name aname,p.remark from prize p left join activity a on p.aid=a.id where p.id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(prize.get("id")));
            rs = ptst.executeQuery();
            if (rs.next()) {
                prize = new HashMap<String, String>();
                prize.put("id", rs.getString("id"));
                prize.put("codeid", rs.getString("codeid"));
                prize.put("codename", rs.getString("codename"));
                prize.put("name", rs.getString("name"));
                prize.put("num", rs.getString("num"));
                prize.put("discounts", rs.getString("discounts"));
                prize.put("aid", rs.getString("aid"));
                prize.put("aname", rs.getString("aname"));
                prize.put("remark", rs.getString("remark"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return prize;
    }

    public void add(Map<String, String> prize) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into prize(codeid,codename,name,num,discounts,aid,remark) values (?,?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(prize.get("codeid")));
            ptst.setString(2, prize.get("codename"));
            ptst.setString(3, prize.get("name"));
            ptst.setInt(4, Integer.parseInt(prize.get("num")));
            ptst.setDouble(5, Double.parseDouble(prize.get("discounts")));
            ptst.setInt(6, Integer.parseInt(prize.get("aid")));
            ptst.setString(7, prize.get("remark"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void update(Map<String, String> prize) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update prize set codeid=?,codename=?,name=?,num=?,discounts=?,aid=?,remark=? where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(prize.get("codeid")));
            ptst.setString(2, prize.get("codename"));
            ptst.setString(3, prize.get("name"));
            ptst.setInt(4, Integer.parseInt(prize.get("num")));
            ptst.setDouble(5, Double.parseDouble(prize.get("discounts")));
            ptst.setInt(6, Integer.parseInt(prize.get("aid")));
            ptst.setString(7, prize.get("remark"));
            ptst.setInt(8, Integer.parseInt(prize.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void delete(Map<String, String> prize) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from prize where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(prize.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void deleteByActivity(Map<String, String> activity) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from prize where aid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(activity.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }
}
