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
public class ActivityDAO {

    public List<Map<String, String>> getList() {
        List<Map<String, String>> activityList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,activitykey,name,img,counts,taketimes,pointtimes,starttime,endtime,state,remark,successpoint,nopoint,overpoint,endpoint,sid,changetimes,changepercent from activity order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> activity = new HashMap<String, String>();
                activity.put("id", rs.getString("id"));
                activity.put("activitykey", rs.getString("activitykey"));
                activity.put("name", rs.getString("name"));
                activity.put("img", rs.getString("img"));
                activity.put("counts", rs.getString("counts"));
                activity.put("taketimes", rs.getString("taketimes"));
                activity.put("pointtimes", rs.getString("pointtimes"));
                activity.put("starttime", rs.getString("starttime"));
                activity.put("endtime", rs.getString("endtime"));
                activity.put("state", rs.getString("state"));
                activity.put("remark", rs.getString("remark"));
                activity.put("successpoint", rs.getString("successpoint"));
                activity.put("nopoint", rs.getString("nopoint"));
                activity.put("overpoint", rs.getString("overpoint"));
                activity.put("endpoint", rs.getString("endpoint"));
                activity.put("sid", rs.getString("sid"));
                activity.put("changetimes", rs.getString("changetimes"));
                activity.put("changepercent", rs.getString("changepercent"));
                activityList.add(activity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return activityList;
    }

    public List<Map<String, String>> getList(PageUtil pu) {
        List<Map<String, String>> activityList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,activitykey,name,img,counts,taketimes,pointtimes,starttime,endtime,state,remark,successpoint,nopoint,overpoint,endpoint,sid,changetimes,changepercent from activity order by id desc limit " + pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                Map<String, String> activity = new HashMap<String, String>();
                activity.put("id", rs.getString("id"));
                activity.put("activitykey", rs.getString("activitykey"));
                activity.put("name", rs.getString("name"));
                activity.put("img", rs.getString("img"));
                activity.put("counts", rs.getString("counts"));
                activity.put("taketimes", rs.getString("taketimes"));
                activity.put("pointtimes", rs.getString("pointtimes"));
                activity.put("starttime", rs.getString("starttime"));
                activity.put("endtime", rs.getString("endtime"));
                activity.put("state", rs.getString("state"));
                activity.put("remark", rs.getString("remark"));
                activity.put("successpoint", rs.getString("successpoint"));
                activity.put("nopoint", rs.getString("nopoint"));
                activity.put("overpoint", rs.getString("overpoint"));
                activity.put("endpoint", rs.getString("endpoint"));
                activity.put("sid", rs.getString("sid"));
                activity.put("changetimes", rs.getString("changetimes"));
                activity.put("changepercent", rs.getString("changepercent"));
                activityList.add(activity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return activityList;
    }

    public int getConut() {
        int count = 0;
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select count(id) count from activity";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return count;
    }

    public List<Map<String, String>> getBySidList(Map<String, String> activity) {
        List<Map<String, String>> activityList = new ArrayList<Map<String, String>>();
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,activitykey,name,img,counts,taketimes,pointtimes,starttime,endtime,state,remark,successpoint,nopoint,overpoint,endpoint,sid,changetimes,changepercent from activity where sid=" + activity.get("sid") + " order by id desc";
        try {
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            while (rs.next()) {
                activity = new HashMap<String, String>();
                activity.put("id", rs.getString("id"));
                activity.put("activitykey", rs.getString("activitykey"));
                activity.put("name", rs.getString("name"));
                activity.put("img", rs.getString("img"));
                activity.put("counts", rs.getString("counts"));
                activity.put("taketimes", rs.getString("taketimes"));
                activity.put("pointtimes", rs.getString("pointtimes"));
                activity.put("starttime", rs.getString("starttime"));
                activity.put("endtime", rs.getString("endtime"));
                activity.put("state", rs.getString("state"));
                activity.put("remark", rs.getString("remark"));
                activity.put("successpoint", rs.getString("successpoint"));
                activity.put("nopoint", rs.getString("nopoint"));
                activity.put("overpoint", rs.getString("overpoint"));
                activity.put("endpoint", rs.getString("endpoint"));
                activity.put("sid", rs.getString("sid"));
                activity.put("changetimes", rs.getString("changetimes"));
                activity.put("changepercent", rs.getString("changepercent"));
                activityList.add(activity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return activityList;
    }

    public Map<String, String> getById(Map<String, String> activity) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,activitykey,name,img,counts,taketimes,pointtimes,starttime,endtime,state,remark,successpoint,nopoint,overpoint,endpoint,sid,changetimes,changepercent from activity where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(activity.get("id")));
            rs = ptst.executeQuery();
            if (rs.next()) {
                activity = new HashMap<String, String>();
                activity.put("id", rs.getString("id"));
                activity.put("activitykey", rs.getString("activitykey"));
                activity.put("name", rs.getString("name"));
                activity.put("img", rs.getString("img"));
                activity.put("counts", rs.getString("counts"));
                activity.put("taketimes", rs.getString("taketimes"));
                activity.put("pointtimes", rs.getString("pointtimes"));
                activity.put("starttime", rs.getString("starttime"));
                activity.put("endtime", rs.getString("endtime"));
                activity.put("state", rs.getString("state"));
                activity.put("remark", rs.getString("remark"));
                activity.put("successpoint", rs.getString("successpoint"));
                activity.put("nopoint", rs.getString("nopoint"));
                activity.put("overpoint", rs.getString("overpoint"));
                activity.put("endpoint", rs.getString("endpoint"));
                activity.put("sid", rs.getString("sid"));
                activity.put("changetimes", rs.getString("changetimes"));
                activity.put("changepercent", rs.getString("changepercent"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return activity;
    }

    public Map<String, String> getByActivitykey(Map<String, String> activity) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "select id,activitykey,name,img,counts,taketimes,pointtimes,starttime,endtime,state,remark,successpoint,nopoint,overpoint,endpoint,sid,changetimes,changepercent from activity where activitykey=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, activity.get("activitykey"));
            rs = ptst.executeQuery();
            activity = new HashMap<String, String>();
            if (rs.next()) {
                activity.put("id", rs.getString("id"));
                activity.put("activitykey", rs.getString("activitykey"));
                activity.put("name", rs.getString("name"));
                activity.put("img", rs.getString("img"));
                activity.put("counts", rs.getString("counts"));
                activity.put("taketimes", rs.getString("taketimes"));
                activity.put("pointtimes", rs.getString("pointtimes"));
                activity.put("starttime", rs.getString("starttime"));
                activity.put("endtime", rs.getString("endtime"));
                activity.put("state", rs.getString("state"));
                activity.put("remark", rs.getString("remark"));
                activity.put("successpoint", rs.getString("successpoint"));
                activity.put("nopoint", rs.getString("nopoint"));
                activity.put("overpoint", rs.getString("overpoint"));
                activity.put("endpoint", rs.getString("endpoint"));
                activity.put("sid", rs.getString("sid"));
                activity.put("changetimes", rs.getString("changetimes"));
                activity.put("changepercent", rs.getString("changepercent"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
        return activity;
    }

    public void add(Map<String, String> activity) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "insert into activity(activitykey,name,img,counts,taketimes,pointtimes,starttime,endtime,state,remark,successpoint,nopoint,overpoint,endpoint,sid,changetimes,changepercent) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, activity.get("activitykey"));
            ptst.setString(2, activity.get("name"));
            ptst.setString(3, activity.get("img"));
            ptst.setInt(4, Integer.parseInt(activity.get("counts")));
            ptst.setInt(5, Integer.parseInt(activity.get("taketimes")));
            ptst.setInt(6, Integer.parseInt(activity.get("pointtimes")));
            ptst.setString(7, activity.get("starttime"));
            ptst.setString(8, activity.get("endtime"));
            ptst.setString(9, activity.get("state"));
            ptst.setString(10, activity.get("remark"));
            ptst.setString(11, activity.get("successpoint"));
            ptst.setString(12, activity.get("nopoint"));
            ptst.setString(13, activity.get("overpoint"));
            ptst.setString(14, activity.get("endpoint"));
            ptst.setInt(15, Integer.parseInt(activity.get("sid")));
            ptst.setInt(16, Integer.parseInt(activity.get("changetimes")));
            ptst.setString(17, activity.get("changepercent"));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void update(Map<String, String> activity) {
        Connection conn = DbConn.getConn();
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String sql = "update activity set activitykey=?,name=?,img=?,counts=?,taketimes=?,pointtimes=?,starttime=?,endtime=?,state=?,remark=?,successpoint=?,nopoint=?,overpoint=?,endpoint=?,sid=?,changetimes=?,changepercent=? where id=?";
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, activity.get("activitykey"));
            ptst.setString(2, activity.get("name"));
            ptst.setString(3, activity.get("img"));
            ptst.setInt(4, Integer.parseInt(activity.get("counts")));
            ptst.setInt(5, Integer.parseInt(activity.get("taketimes")));
            ptst.setInt(6, Integer.parseInt(activity.get("pointtimes")));
            ptst.setString(7, activity.get("starttime"));
            ptst.setString(8, activity.get("endtime"));
            ptst.setString(9, activity.get("state"));
            ptst.setString(10, activity.get("remark"));
            ptst.setString(11, activity.get("successpoint"));
            ptst.setString(12, activity.get("nopoint"));
            ptst.setString(13, activity.get("overpoint"));
            ptst.setString(14, activity.get("endpoint"));
            ptst.setInt(15, Integer.parseInt(activity.get("sid")));
            ptst.setInt(16, Integer.parseInt(activity.get("changetimes")));
            ptst.setString(17, activity.get("changepercent"));
            ptst.setInt(18, Integer.parseInt(activity.get("id")));
            ptst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbConn.getAllClose(conn, ptst, rs);
        }
    }

    public void delete(Map<String, String> activity, Connection conn) {
        String sql = "delete from activity where id=?";
        try {
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setInt(1, Integer.parseInt(activity.get("id")));
            ptst.executeUpdate();
            ptst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
