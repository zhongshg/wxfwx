/*
 * AbstractDao.java
 *
 * Created on 2006��7��25��, ����4:32
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package job.tot.dao;

import java.sql.*;
import java.util.*;

import job.tot.bean.DataField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import job.tot.db.DBUtils;
import job.tot.exception.DatabaseException;
import job.tot.exception.ObjectNotFoundException;
import wap.wx.util.DbConn;

/**
 *
 * @author ������
 */
public class AbstractDao {

    private static Log log = LogFactory.getLog(AbstractDao.class);

    /**
     * Creates a new instance of AbstractDao
     */
    public AbstractDao() {
    }

    public Collection getData(String sqlStr, String fieldArr, Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        Collection returnList = new ArrayList();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlStr);
            DataField df;
            String[] splitStr = null;
            splitStr = fieldArr.split(",");
            while (rs.next()) {
                df = new DataField();
                for (int i = 0; i < splitStr.length; i++) {
                    df.setField(splitStr[i], rs.getString(i + 1), 0);
                }
                returnList.add(df);
            }
        } catch (SQLException e) {
            log.error("Sql Exception Error:", e);
        } finally {
            DBUtils.closeResultSet(rs);
            //DBUtils.resetStatement(stmt);
            DBUtils.closeStatement(stmt);
        }
        return returnList;
    }

    public Collection getData(String sqlStr, String fieldArr) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Collection returnList = new ArrayList();
        try {
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlStr);
            DataField df;
            String[] splitStr = null;
            splitStr = fieldArr.split(",");
            while (rs.next()) {
                df = new DataField();
                for (int i = 0; i < splitStr.length; i++) {
                    df.setField(splitStr[i], rs.getString(i + 1), 0);
                }
                returnList.add(df);
            }
        } catch (SQLException e) {
            log.error("Sql Exception Error:", e);
        } finally {
            DBUtils.closeResultSet(rs);
            //DBUtils.resetStatement(stmt);
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(conn);
        }
        return returnList;
    }

    public Collection getDataList_mysqlLimit(String sqlStr, String fieldArr, int rowsNum, int offset) {
        StringBuffer sb = new StringBuffer(512);
        sb.append(sqlStr);
        sb.append(" limit " + offset + "," + rowsNum);
        return this.getData(sb.toString(), fieldArr);
    }

    public Collection getDataList_Limit_Normal(String sqlStr, String fieldArr, int rowsNum, int offset) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Collection returnList = new ArrayList();
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sqlStr, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setMaxRows(offset + rowsNum);
            try {
                ps.setFetchSize(rowsNum);
            } catch (SQLException sqle) {
                //do nothing, postgreSQL doesnt support this method
            }
            rs = ps.executeQuery();
            boolean loop = rs.absolute(offset + 1);
            DataField df;
            String[] splitStr = null;
            splitStr = fieldArr.split(",");
            while (loop) {
                df = new DataField();
                for (int i = 0; i < splitStr.length; i++) {
                    df.setField(splitStr[i], rs.getString(i + 1), 0);
                }
                returnList.add(df);
                if (returnList.size() >= rowsNum) {
                    break;// Fix the Sybase bug
                }
                loop = rs.next();
            }
        } catch (SQLException e) {
            log.error("Sql Exception Error:", e);
        } finally {
            DBUtils.closeResultSet(rs);
            DBUtils.resetStatement(ps);
            DBUtils.closeStatement(ps);
            DBUtils.closeConnection(conn);
        }
        return returnList;
    }

    public DataField getFirstData(String sqlStr, String fieldArr) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        DataField df = null;
        try {
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlStr);
            String[] splitStr = null;
            splitStr = fieldArr.split(",");
            if (rs.next()) {
                df = new DataField();
                for (int i = 0; i < splitStr.length; i++) {
                    df.setField(splitStr[i], rs.getString(i + 1), 0);
                }
            }
        } catch (SQLException e) {
            log.error("Sql Exception Error:", e);
        } finally {
            DBUtils.closeResultSet(rs);
            //DBUtils.resetStatement(stmt);
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(conn);
        }
        return df;
    }

    public int getDataCount(String sqlStr) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int returnInt = 0;
        try {
            conn = DBUtils.getConnection();
            ResultSet resultSet = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlStr);
            if (rs.next()) {
                returnInt = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error("Sql Exception Error:", e);
        } finally {
            DBUtils.closeResultSet(rs);
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(conn);
        }
        return returnInt;
    }

    public boolean exe(String sqlStr) throws ObjectNotFoundException, DatabaseException {
        Connection conn = null;
        Statement stmt = null;
        boolean returnValue = true;
        try {
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            if (stmt.executeUpdate(sqlStr) != 1) {
                returnValue = false;
            } else {
                returnValue = true;
            }
        } catch (SQLException e) {
            log.error("Sql Exception Error:", e);
            throw new DatabaseException("Got Exception on Call Medthod exe in tot.dao.AbstractDao");
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(conn);
        }
        return returnValue;
    }

    public void bat(String sqlStr, String[] fieldvalue) {
        Connection conn = null;
        PreparedStatement ps = null;
        int returnInt = 0;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sqlStr);
            for (int i = 0; i < fieldvalue.length; i++) {
                int f = Integer.parseInt(fieldvalue[i]);
                ps.setInt(1, f);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Sql Exception Error:", e);
        } finally {
            DBUtils.closePrepareStatement(ps);
            DBUtils.closeConnection(conn);
        }
    }

    public void bat_String(String sqlStr, String[] fieldvalue) {
        Connection conn = null;
        PreparedStatement ps = null;
        int returnInt = 0;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sqlStr);
            for (int i = 0; i < fieldvalue.length; i++) {
                ps.setString(1, fieldvalue[i]);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Sql Exception Error:", e);
        } finally {
            DBUtils.closePrepareStatement(ps);
            DBUtils.closeConnection(conn);
        }
    }
}
