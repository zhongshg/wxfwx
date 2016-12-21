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

import job.tot.db.DBUtils;
import wap.wx.util.DbConn;
import wap.wx.util.PageUtil;

/**
 *
 * @author Administrator
 */
public class BannerDao {

	public List<Map<String, String>> getList(int sid) {
		List<Map<String, String>> bannerList = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,code,name,img,ts,dr";
		String sql = "select " + field + " from k_banner where sid=? order by dr desc ";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, sid);
			rs = ptst.executeQuery();
			Map<String, String> banner = null;
			while (rs.next()) {
				banner = new HashMap<String, String>();
				banner.put("id", rs.getString("id"));
				banner.put("code", rs.getString("code"));
				banner.put("name", rs.getString("name"));
				banner.put("img", rs.getString("img"));
				banner.put("ts", rs.getString("ts"));
				banner.put("dr", rs.getString("dr"));
				bannerList.add(banner);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(BannerDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return bannerList;
	}

	public List<Map<String, String>> getList(PageUtil pu, String sid, String tid, String zcode, double lat, double lon,
			String orders) {
		List<Map<String, String>> bannerList = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,code,name,img,ts,dr";
		String sql = "select " + field + " from k_banner where sid=? ";
		sql += pu.getPageSize() * (0 < pu.getPage() ? pu.getPage() - 1 : 0) + "," + pu.getPageSize();
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(sid));

			rs = ptst.executeQuery();
			Map<String, String> banner = null;
			while (rs.next()) {
				banner = new HashMap<String, String>();
				banner.put("id", rs.getString("id"));
				banner.put("code", rs.getString("code"));
				banner.put("name", rs.getString("name"));
				banner.put("img", rs.getString("img"));
				banner.put("ts", rs.getString("ts"));
				banner.put("dr", rs.getString("dr"));
				bannerList.add(banner);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(BannerDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return bannerList;
	}

	public int getConut(Map<String, String> banner) {
		int count = 0;
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String sql = "select count(id) count from k_banner where sid=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(banner.get("sid")));
			rs = ptst.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(BannerDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return count;
	}

	public int getConut(String sid, String tid, String acode) {
		int count = 0;
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String sql = "select count(id) count from k_banner wheresid=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(sid));
			rs = ptst.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(BannerDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return count;
	}

	public Map<String, String> getById(Map<String, String> banner) {
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String field = "id,code,name,img,ts,dr,sid";
		String sql = "select " + field + " from k_banner where id=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(banner.get("id")));
			rs = ptst.executeQuery();
			if (rs.next()) {
				banner = new HashMap<String, String>();
				banner = new HashMap<String, String>();
				banner.put("id", rs.getString("id"));
				banner.put("code", rs.getString("code"));
				banner.put("name", rs.getString("name"));
				banner.put("img", rs.getString("img"));
				banner.put("sid", rs.getString("sid"));
				banner.put("ts", rs.getString("ts"));
				banner.put("dr", rs.getString("dr"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(BannerDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
		return banner;
	}

	public void add(Map<String, String> banner) {
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String sql = "insert into k_banner(code,name,img,dr,sid) values (?,?,?,?,?)";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setString(1, banner.get("code"));
			ptst.setString(2, banner.get("name"));
			ptst.setString(3, banner.get("img"));
			ptst.setString(4, banner.get("dr"));
			ptst.setString(5, banner.get("sid"));
			ptst.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(BannerDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
	}

	public void update(Map<String, String> banner) {
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		String sql = "update k_banner set code=?,name=?,img=?,dr=? where id=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setString(1, banner.get("code"));
			ptst.setString(2, banner.get("name"));
			ptst.setString(3, banner.get("img"));
			ptst.setString(4, banner.get("dr"));
			ptst.setInt(5, Integer.parseInt(banner.get("id")));
			ptst.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(BannerDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, rs);
		}
	}

	public void delete(Map<String, String> banner, Connection conn) {
		String sql = "delete from k_banner where id=?";
		try {
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(banner.get("id")));
			ptst.executeUpdate();
			ptst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(BannerDao.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void delete(Map<String, String> banner) {
		Connection conn = null;
		PreparedStatement ptst = null;
		String sql = "delete from k_banner where id=?";
		try {
			conn = DbConn.getConn();
			ptst = conn.prepareStatement(sql);
			ptst.setInt(1, Integer.parseInt(banner.get("id")));
			ptst.executeUpdate();
			ptst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(BannerDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			DbConn.getAllClose(conn, ptst, null);
		}
	}

}
