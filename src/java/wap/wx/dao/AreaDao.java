package wap.wx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import job.tot.bean.DataField;
import job.tot.dao.AbstractDao;
import job.tot.exception.DatabaseException;
import job.tot.exception.ObjectNotFoundException;
import wap.wx.util.DbConn;

/**
 *
 * @author Administrator
 */
public class AreaDao extends AbstractDao {

	private static Log log = LogFactory.getLog(AreaDao.class);

	public List<Map<String,String>> getList(int pid) {
		String fieldArr = "id,name,pid";
		String sqlStr = "select " + fieldArr + " from k_area where pid=? ";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String,String>> returnList = new ArrayList<Map<String,String>>();
		try {
			conn = DbConn.getConn();
			pst = conn.prepareStatement(sqlStr);
			pst.setInt(1, pid);
			rs = pst.executeQuery();
			Map<String,String> df = null;
			String[] splitStr = null;
			splitStr = fieldArr.split(",");
			while (rs.next()) {
				df = new HashMap<String,String>();
				for (int i = 0; i < splitStr.length; i++) {
					df.put(splitStr[i], rs.getString(i + 1));
				}
				returnList.add(df);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Sql Exception Error:", e);
		} finally {
			DbConn.getAllClose(conn, pst, rs);
		}
		return returnList;
	}

	public boolean add(String name, int pid, int sortid, String lat, String lng) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = true;
		String sql = "insert into k_area(name,pid) values(?,?)";
		try {
			conn = DbConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, pid);
			if (ps.executeUpdate() != 1) {
				returnValue = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("add Role error", e);
		} finally {
			DbConn.getAllClose(conn, ps, null);
		}
		return returnValue;
	}

	public boolean mod(int id, String name, int pid) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = true;
		String sql = "update k_area set name=?,pid=? where id=?";
		try {
			conn = DbConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, pid);
			ps.setInt(3, id);
			if (ps.executeUpdate() != 1) {
				returnValue = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("mod role error", e);
		} finally {
			DbConn.getAllClose(conn, ps, null);
		}
		return returnValue;
	}

	public boolean del(int id) throws ObjectNotFoundException, DatabaseException {
		return exe("delete from k_area where id=" + id);
	}

	public Collection get_Limit(int pid, int currentpage, int pagesize) {
		String str = "id,name,pid";
		StringBuilder sql = new StringBuilder("select id,name,pid from k_area where pid=" + pid);
		return getDataList_mysqlLimit(sql.toString(), str, pagesize, (currentpage - 1) * pagesize);
	}

	public DataField get(String id) {
		return getFirstData("select id,name,pid from k_area where id=" + id, "id,name,pid");
	}

	public int getTotalCount(int pid) {
		String sqlStr = "select count(*) from k_area where pid=?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int returnInt = 0;
		try {
			conn = DbConn.getConn();
			pst = conn.prepareStatement(sqlStr);
			pst.setInt(1, pid);
			rs = pst.executeQuery();
			if (rs.next()) {
				returnInt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Sql Exception Error:", e);
		} finally {
			DbConn.getAllClose(conn, pst, rs);
		}
		return returnInt;
	}

	public int getMaxId() {
		return getDataCount("select max(id) from k_area");
	}
}
