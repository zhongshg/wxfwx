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
public class PowersDAO {

    public List<Map<String, String>> getList() {
        List<Map<String, String>> powersList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select p.id,p.name,p.parentid,pa.name parentname,p.url,p.remark from powers p left join powers pa on p.parentid=pa.id order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> powers = new HashMap<String, String>();
                powers.put("id", rs.getString("id"));
                powers.put("name", rs.getString("name"));
                powers.put("parentid", rs.getString("parentid"));
                powers.put("parentname", rs.getString("parentname"));
                powers.put("url", rs.getString("url"));
                powers.put("remark", rs.getString("remark"));
                powersList.add(powers);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return powersList;
    }

    public List<Map<String, String>> getList(PageUtil pu) {
        List<Map<String, String>> powersList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select p.id,p.name,p.parentid,pa.name parentname,p.url,p.remark from powers p left join powers pa on p.parentid=pa.id order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> powers = new HashMap<String, String>();
                powers.put("id", rs.getString("id"));
                powers.put("name", rs.getString("name"));
                powers.put("parentid", rs.getString("parentid"));
                powers.put("parentname", rs.getString("parentname"));
                powers.put("url", rs.getString("url"));
                powers.put("remark", rs.getString("remark"));
                powersList.add(powers);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return powersList;
    }

    public int getConut() {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from powers";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public List<Map<String, String>> getListByRP(Map<String, String> roles, Map<String, String> powers) {
        List<Map<String, String>> powersList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,parentid,url,remark from powers where id in (select pid from rolespowers where rid=?) and parentid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, roles.get("id"));
            ptst.setString(2, powers.get("id"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                powers = new HashMap<String, String>();
                powers.put("id", rs.getString("id"));
                powers.put("name", rs.getString("name"));
                powers.put("parentid", rs.getString("parentid"));
                powers.put("url", rs.getString("url"));
                powers.put("remark", rs.getString("remark"));
                powersList.add(powers);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return powersList;
    }

    public Map<String, String> getById(Map<String, String> powers) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select p.id,p.name,p.parentid,pa.name parentname,p.url,p.remark from powers p left join powers pa on p.parentid=pa.id where p.id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(powers.get("id")));
            rs = ptst.executeQuery();
            if (rs.next()) {
                powers = new HashMap<String, String>();
                powers.put("id", rs.getString("id"));
                powers.put("name", rs.getString("name"));
                powers.put("parentid", rs.getString("parentid"));
                powers.put("parentname", rs.getString("parentname"));
                powers.put("url", rs.getString("url"));
                powers.put("remark", rs.getString("remark"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return powers;
    }

    public void add(Map<String, String> powers) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into powers(name,parentid,url,remark) values (?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, powers.get("name"));
            ptst.setInt(2, Integer.parseInt(powers.get("parentid")));
            ptst.setString(3, powers.get("url"));
            ptst.setString(4, powers.get("remark"));;
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void update(Map<String, String> powers) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update powers set name=?,parentid=?,url=?,remark=? where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, powers.get("name"));
            ptst.setInt(2, Integer.parseInt(powers.get("parentid")));
            ptst.setString(3, powers.get("url"));
            ptst.setString(4, powers.get("remark"));
            ptst.setString(5, powers.get("id"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void delete(Map<String, String> powers) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from powers where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, powers.get("id"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }
}
