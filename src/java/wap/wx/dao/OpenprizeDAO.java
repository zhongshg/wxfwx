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
public class OpenprizeDAO {

    public List<Map<String, String>> getList(PageUtil pu, String sid) {
        List<Map<String, String>> openprizeList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select o.id,o.openid,o.aid,o.pid,o.sn,o.tel,o.opendate,o.state,o.name,o.sex,o.sid,o.isexp,v.name vname from openprize o left join vote v on o.aid=v.id where o.sid=? order by o.id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(sid));
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> openprize = new HashMap<String, String>();
                openprize.put("id", rs.getString("id"));
                openprize.put("openid", rs.getString("openid"));
                openprize.put("aid", rs.getString("aid"));
                openprize.put("pid", rs.getString("pid"));
                openprize.put("sn", rs.getString("sn"));
                openprize.put("tel", rs.getString("tel"));
                openprize.put("opendate", rs.getString("opendate"));
                openprize.put("state", rs.getString("state"));
                openprize.put("name", rs.getString("name"));
                openprize.put("sex", rs.getString("sex"));
                openprize.put("sid", rs.getString("sid"));
                openprize.put("isexp", rs.getString("isexp"));
                openprize.put("vname", rs.getString("vname"));
                openprizeList.add(openprize);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return openprizeList;
    }

    public List<Map<String, String>> getList(PageUtil pu, Map<String, String> activity) {
        List<Map<String, String>> openprizeList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select o.id,o.openid,o.aid,a.name aname,o.pid,p.name pname,o.sn,o.tel,o.opendate,o.state,o.name,o.sex,o.sid,o.isexp from openprize o left join prize p on o.pid=p.id inner join activity a on o.aid=a.id and o.aid=? order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(activity.get("id")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> openprize = new HashMap<String, String>();
                openprize.put("id", rs.getString("id"));
                openprize.put("openid", rs.getString("openid"));
                openprize.put("aid", rs.getString("aid"));
                openprize.put("aname", rs.getString("aname"));
                openprize.put("pid", rs.getString("pid"));
                openprize.put("pname", rs.getString("pname"));
                openprize.put("sn", rs.getString("sn"));
                openprize.put("tel", rs.getString("tel"));
                openprize.put("opendate", rs.getString("opendate"));
                openprize.put("state", rs.getString("state"));
                openprize.put("name", rs.getString("name"));
                openprize.put("sex", rs.getString("sex"));
                openprize.put("sid", rs.getString("sid"));
                openprize.put("isexp", rs.getString("isexp"));
                openprizeList.add(openprize);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return openprizeList;
    }

    public List<Map<String, String>> getByOpenprizeList(Map<String, String> activity, Map<String, String> openprize) {
        List<Map<String, String>> openprizeList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,openid,aid,pid,sn,tel,opendate,state,name,sex,sid,isexp from openprize where aid=? and openid=? and opendate between ? and ? and state=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(openprize.get("aid")));
            ptst.setString(2, openprize.get("openid"));
            ptst.setString(3, activity.get("starttime"));
            ptst.setString(4, activity.get("endtime"));
            ptst.setInt(5, Integer.parseInt(openprize.get("state")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                openprize = new HashMap<String, String>();
                openprize.put("id", rs.getString("id"));
                openprize.put("openid", rs.getString("openid"));
                openprize.put("aid", rs.getString("aid"));
                openprize.put("pid", rs.getString("pid"));
                openprize.put("sn", rs.getString("sn"));
                openprize.put("tel", rs.getString("tel"));
                openprize.put("opendate", rs.getString("opendate"));
                openprize.put("state", rs.getString("state"));
                openprize.put("name", rs.getString("name"));
                openprize.put("sex", rs.getString("sex"));
                openprize.put("sid", rs.getString("sid"));
                openprize.put("isexp", rs.getString("isexp"));
                openprizeList.add(openprize);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return openprizeList;
    }

    public int getCount(Map<String, String> activity) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from openprize where aid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(activity.get("id")));
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getCount(String sid) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from openprize where sid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(sid));
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public Map<String, String> getById(Map<String, String> openprize) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select o.id,o.openid,o.aid,a.name aname,o.pid,p.name pname,o.sn,o.tel,o.opendate,o.state,o.name,o.sex,o.sid,o.isexp from openprize o left join activity a on o.aid=a.id left join prize p on o.pid=p.id and o.aid=? order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(openprize.get("id")));
            rs = ptst.executeQuery();
            if (rs.next()) {
                openprize = new HashMap<String, String>();
                openprize.put("id", rs.getString("id"));
                openprize.put("openid", rs.getString("openid"));
                openprize.put("aid", rs.getString("aid"));
                openprize.put("aname", rs.getString("aname"));
                openprize.put("pid", rs.getString("pid"));
                openprize.put("pname", rs.getString("pname"));
                openprize.put("sn", rs.getString("sn"));
                openprize.put("tel", rs.getString("tel"));
                openprize.put("opendate", rs.getString("opendate"));
                openprize.put("state", rs.getString("state"));
                openprize.put("name", rs.getString("name"));
                openprize.put("sex", rs.getString("sex"));
                openprize.put("sid", rs.getString("sid"));
                openprize.put("isexp", rs.getString("isexp"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return openprize;
    }

    public void add(Map<String, String> openprize) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into openprize(openid,aid,pid,sn,tel,opendate,state,name,sex,sid,isexp) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, openprize.get("openid"));
            ptst.setInt(2, Integer.parseInt(openprize.get("aid")));
            ptst.setInt(3, Integer.parseInt(openprize.get("pid")));
            ptst.setString(4, openprize.get("sn"));
            ptst.setString(5, openprize.get("tel"));
            ptst.setString(6, openprize.get("opendate"));
            ptst.setInt(7, Integer.parseInt(openprize.get("state")));
            ptst.setString(8, openprize.get("name"));
            ptst.setInt(9, Integer.parseInt(openprize.get("sex")));
            ptst.setInt(10, Integer.parseInt(openprize.get("sid")));
            ptst.setInt(11, Integer.parseInt(openprize.get("isexp")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void updateData(Map<String, String> openprize) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update openprize set tel=?,name=?,sex=? where sn=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, openprize.get("tel"));
            ptst.setString(2, openprize.get("name"));
            ptst.setInt(3, Integer.parseInt(openprize.get("sex")));
            ptst.setString(4, openprize.get("sn"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void updateExp(Map<String, String> openprize) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update openprize set isexp=? where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(openprize.get("isexp")));
            ptst.setInt(2, Integer.parseInt(openprize.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void delete(Map<String, String> openprize) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "delete from openprize where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(openprize.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public int getTakeCount(String openid, String sid) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from openprize where openid=? and sid=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, openid);
            ptst.setInt(2, Integer.parseInt(sid));
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getTakeCount(Map<String, String> activity, Map<String, String> openprize) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from openprize where aid=? and openid=? and opendate between ? and ?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(activity.get("id")));
            ptst.setString(2, openprize.get("openid"));
            ptst.setString(3, activity.get("starttime"));
            ptst.setString(4, activity.get("endtime"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getPointCount(Map<String, String> activity, Map<String, String> openprize) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from openprize where aid=? and openid=? and state=? and opendate between ? and ?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(activity.get("id")));
            ptst.setString(2, openprize.get("openid"));
            ptst.setInt(3, Integer.parseInt(openprize.get("state")));
            ptst.setString(4, activity.get("starttime"));
            ptst.setString(5, activity.get("endtime"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getPrizePointCount(Map<String, String> activity, Map<String, String> openprize) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from openprize where aid=? and pid=? and state=? and opendate between ? and ?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(activity.get("id")));
            ptst.setInt(2, Integer.parseInt(openprize.get("pid")));
            ptst.setInt(3, Integer.parseInt(openprize.get("state")));
            ptst.setString(4, activity.get("starttime"));
            ptst.setString(5, activity.get("endtime"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public int getOpenidPointCountBySn(Map<String, String> openprize) {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from openprize where aid=? and pid=? and openid=? and sn=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(openprize.get("aid")));
            ptst.setInt(2, Integer.parseInt(openprize.get("pid")));
            ptst.setString(3, openprize.get("openid"));
            ptst.setString(4, openprize.get("sn"));
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenprizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }
}
