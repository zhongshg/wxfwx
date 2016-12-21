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
public class RedtypeDAO {

    public List<Map<String, String>> getList(String mch_billno) {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select r.id,r.name,r.num,r.content,r.type,r.remark,r.mch_billno,s.name sname from redtype r,sendredpack s where r.mch_billno=s.mch_billno and r.mch_billno=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("name", rs.getString("name"));
                map.put("num", rs.getString("num"));
                map.put("content", rs.getString("content"));
                map.put("type", rs.getString("type"));
                map.put("remark", rs.getString("remark"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("sname", rs.getString("sname"));
                mapList.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RedtypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return mapList;
    }

    public List<Map<String, String>> getList(PageUtil pu) {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select r.id,r.name,r.num,r.content,r.type,r.remark,r.mch_billno,s.name sname from redtype r,sendredpack s where r.mch_billno=s.mch_billno order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("name", rs.getString("name"));
                map.put("num", rs.getString("num"));
                map.put("content", rs.getString("content"));
                map.put("type", rs.getString("type"));
                map.put("remark", rs.getString("remark"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("sname", rs.getString("sname"));
                mapList.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RedtypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return mapList;
    }

    public int getConut() {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from redtype";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RedtypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public Map<String, String> getById(Map<String, String> map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,num,content,type,remark,mch_billno from redtype where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(map.get("id")));
            rs = ptst.executeQuery();
            if (rs.next()) {
                map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("name", rs.getString("name"));
                map.put("num", rs.getString("num"));
                map.put("content", rs.getString("content"));
                map.put("type", rs.getString("type"));
                map.put("remark", rs.getString("remark"));
                map.put("mch_billno", rs.getString("mch_billno"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RedtypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return map;
    }

    public void add(Map<String, String> map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into redtype(name,num,content,type,remark,mch_billno) values (?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, map.get("name"));
            ptst.setInt(2, Integer.parseInt(map.get("num")));
            ptst.setString(3, map.get("content"));
            ptst.setInt(4, Integer.parseInt(map.get("type")));
            ptst.setString(5, map.get("remark"));
            ptst.setString(6, map.get("mch_billno"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RedtypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void update(Map<String, String> map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update redtype set name=?,num=?,content=?,type=?,remark=?,mch_billno=? where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, map.get("name"));
            ptst.setInt(2, Integer.parseInt(map.get("num")));
            ptst.setString(3, map.get("content"));
            ptst.setInt(4, Integer.parseInt(map.get("type")));
            ptst.setString(5, map.get("remark"));
            ptst.setString(6, map.get("mch_billno"));
            ptst.setInt(7, Integer.parseInt(map.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RedtypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void delete(Map<String, String> map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from redtype where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(map.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RedtypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }
}
