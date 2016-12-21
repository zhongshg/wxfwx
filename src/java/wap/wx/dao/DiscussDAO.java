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
public class DiscussDAO {

    public List<Map<String, String>> getByTargetList(Map<String, String> target) {
        List<Map<String, String>> discussList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select d.id,d.openid,v.nickname vname,v.headimgurl vimg,d.content,d.vipid,d.times,d.isreply,d.praisecount from discuss d,subscriber v where d.openid=v.openid and d.vipid=? and d.isreply=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, target.get("id"));
            ptst.setInt(2, Integer.parseInt(target.get("isreply")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> discuss = new HashMap<String, String>();
                discuss.put("id", rs.getString("id"));
                discuss.put("openid", rs.getString("openid"));
                discuss.put("vname", rs.getString("vname"));
                discuss.put("vimg", rs.getString("vimg"));
                discuss.put("content", rs.getString("content"));
                discuss.put("vipid", rs.getString("vipid"));
                discuss.put("times", rs.getString("times"));
                discuss.put("isreply", rs.getString("isreply"));
                discuss.put("praisecount", rs.getString("praisecount"));
                discussList.add(discuss);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscussDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return discussList;
    }

    public List<Map<String, String>> getByTargetReplyList(Map<String, String> target) {
        List<Map<String, String>> discussList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,content,vipid,times,isreply,praisecount from discuss where vipid=? and isreply=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, target.get("id"));
            ptst.setInt(2, Integer.parseInt(target.get("isreply")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> discuss = new HashMap<String, String>();
                discuss.put("id", rs.getString("id"));
                discuss.put("openid", rs.getString("openid"));
                discuss.put("content", rs.getString("content"));
                discuss.put("vipid", rs.getString("vipid"));
                discuss.put("times", rs.getString("times"));
                discuss.put("isreply", rs.getString("isreply"));
                discuss.put("praisecount", rs.getString("praisecount"));
                discussList.add(discuss);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscussDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return discussList;
    }

    public List<Map<String, String>> getByGatItemsList(PageUtil pu, Map<String, String> gatItems) {
        List<Map<String, String>> discussList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,content,vipid,times,isreply,praisecount from discuss where vipid=? and isreply=? order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(gatItems.get("id")));
            ptst.setInt(2, Integer.parseInt(gatItems.get("isreply")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> discuss = new HashMap<String, String>();
                discuss.put("id", rs.getString("id"));
                discuss.put("openid", rs.getString("openid"));
                discuss.put("content", rs.getString("content"));
                discuss.put("vipid", rs.getString("vipid"));
                discuss.put("times", rs.getString("times"));
                discuss.put("isreply", rs.getString("isreply"));
                discuss.put("praisecount", rs.getString("praisecount"));
                discussList.add(discuss);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscussDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return discussList;
    }

    public int getConut(Map<String, String> gatItems) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from discuss where vipid=? and isreply=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(gatItems.get("id")));
            ptst.setInt(2, Integer.parseInt(gatItems.get("isreply")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscussDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public Map<String, String> getById(Map<String, String> discuss) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,content,vipid,times,praisecount from discuss where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(discuss.get("id")));
            rs = ptst.executeQuery();
            if (rs.next()) {
                discuss = new HashMap<String, String>();
                discuss.put("id", rs.getString("id"));
                discuss.put("openid", rs.getString("openid"));
                discuss.put("content", rs.getString("content"));
                discuss.put("vipid", rs.getString("vipid"));
                discuss.put("times", rs.getString("times"));
                discuss.put("praisecount", rs.getString("praisecount"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscussDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return discuss;
    }

    public void add(Map<String, String> discuss) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into discuss(openid,content,vipid,times,isreply,praisecount) values (?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, discuss.get("openid"));
            ptst.setString(2, discuss.get("content"));
            ptst.setInt(3, Integer.parseInt(discuss.get("vipid")));
            ptst.setString(4, discuss.get("times"));
            ptst.setInt(5, Integer.parseInt(discuss.get("isreply")));
            ptst.setInt(6, Integer.parseInt(discuss.get("praisecount")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DiscussDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void updatePraisecount(Map<String, String> discuss) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update discuss set praisecount=praisecount+1 where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(discuss.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DiscussDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void deleteByVip(Map<String, String> vip, Connection conn) {
        String sql = "delete from discuss where openid=?";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setString(1, vip.get("openid"));
            ptst.executeUpdate();
            ptst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DiscussDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteByTargetId(Map<String, String> discuss, Connection conn) {
        String sql = "delete from discuss where vipid=?";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setString(1, discuss.get("vipid"));
            ptst.executeUpdate();
            ptst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DiscussDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Map<String, String> discuss, Connection conn) {
        String sql = "delete from discuss where id=?";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setString(1, discuss.get("id"));
            ptst.executeUpdate();
            ptst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DiscussDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Map<String, String> discuss) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from discuss where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, discuss.get("id"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DiscussDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }
}
