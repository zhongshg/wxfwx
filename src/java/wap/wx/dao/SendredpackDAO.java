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
public class SendredpackDAO {

    public List<Map<String, String>> getList() {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,mch_billno,nick_name,send_name,money,num,wishing,remark,img,type,starttime,endtime from sendredpack order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("name", rs.getString("name"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("money", rs.getString("money"));
                map.put("num", rs.getString("num"));
                map.put("wishing", rs.getString("wishing"));
                map.put("remark", rs.getString("remark"));
                map.put("img", rs.getString("img"));
                map.put("type", rs.getString("type"));
                map.put("starttime", rs.getString("starttime"));
                map.put("endtime", rs.getString("endtime"));
                mapList.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpackDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "select id,name,mch_billno,nick_name,send_name,money,num,wishing,remark,img,type,starttime,endtime from sendredpack order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("name", rs.getString("name"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("money", rs.getString("money"));
                map.put("num", rs.getString("num"));
                map.put("wishing", rs.getString("wishing"));
                map.put("remark", rs.getString("remark"));
                map.put("img", rs.getString("img"));
                map.put("type", rs.getString("type"));
                map.put("starttime", rs.getString("starttime"));
                map.put("endtime", rs.getString("endtime"));
                mapList.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpackDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "select count(id) count from sendredpack";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public Map<String, String> getById(Map<String, String> map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,mch_billno,nick_name,send_name,money,num,wishing,remark,img,type,starttime,endtime from sendredpack where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(map.get("id")));
            rs = ptst.executeQuery();
            if (rs.next()) {
                map = new HashMap<String, String>();
                map.put("id", rs.getString("id"));
                map.put("name", rs.getString("name"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("money", rs.getString("money"));
                map.put("num", rs.getString("num"));
                map.put("wishing", rs.getString("wishing"));
                map.put("remark", rs.getString("remark"));
                map.put("img", rs.getString("img"));
                map.put("type", rs.getString("type"));
                map.put("starttime", rs.getString("starttime"));
                map.put("endtime", rs.getString("endtime"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return map;
    }

    public Map<String, String> getByMch_billno(String mch_billno) {
        Map<String, String> map = new HashMap<String, String>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,name,mch_billno,nick_name,send_name,money,num,wishing,remark,img,type,starttime,endtime from sendredpack where mch_billno=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mch_billno);
            rs = ptst.executeQuery();
            if (rs.next()) {
                map.put("id", rs.getString("id"));
                map.put("name", rs.getString("name"));
                map.put("mch_billno", rs.getString("mch_billno"));
                map.put("nick_name", rs.getString("nick_name"));
                map.put("send_name", rs.getString("send_name"));
                map.put("money", rs.getString("money"));
                map.put("num", rs.getString("num"));
                map.put("wishing", rs.getString("wishing"));
                map.put("remark", rs.getString("remark"));
                map.put("img", rs.getString("img"));
                map.put("type", rs.getString("type"));
                map.put("starttime", rs.getString("starttime"));
                map.put("endtime", rs.getString("endtime"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendredpackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return map;
    }

    public void add(Map<String, String> map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into sendredpack(name,mch_billno,nick_name,send_name,money,num,wishing,remark,img,type,starttime,endtime) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, map.get("name"));
            ptst.setString(2, map.get("mch_billno"));
            ptst.setString(3, map.get("nick_name"));
            ptst.setString(4, map.get("send_name"));
            ptst.setInt(5, Integer.parseInt(map.get("money")));
            ptst.setInt(6, Integer.parseInt(map.get("num")));
            ptst.setString(7, map.get("wishing"));
            ptst.setString(8, map.get("remark"));
            ptst.setString(9, map.get("img"));
            ptst.setInt(10, Integer.parseInt(map.get("type")));
            ptst.setString(11, map.get("starttime"));
            ptst.setString(12, map.get("endtime"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SendredpackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void update(Map<String, String> map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update sendredpack set name=?,nick_name=?,send_name=?,money=?,num=?,wishing=?,remark=?,img=?,type=?,starttime=?,endtime=? where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, map.get("name"));
            ptst.setString(2, map.get("nick_name"));
            ptst.setString(3, map.get("send_name"));
            ptst.setInt(4, Integer.parseInt(map.get("money")));
            ptst.setInt(5, Integer.parseInt(map.get("num")));
            ptst.setString(6, map.get("wishing"));
            ptst.setString(7, map.get("remark"));
            ptst.setString(8, map.get("img"));
            ptst.setInt(9, Integer.parseInt(map.get("type")));
            ptst.setString(10, map.get("starttime"));
            ptst.setString(11, map.get("endtime"));
            ptst.setInt(12, Integer.parseInt(map.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SendredpackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void delete(Map<String, String> map) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from sendredpack where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(map.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SendredpackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }
}
