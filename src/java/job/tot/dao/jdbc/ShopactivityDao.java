/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package job.tot.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import job.tot.bean.DataField;
import job.tot.dao.AbstractDao;
import job.tot.db.DBUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ASUS
 */
public class ShopactivityDao extends AbstractDao {

    private static Log log = LogFactory.getLog(ShopactivityDao.class);

    /**
     * Creates a new instance of MessageDaoImplJDBC
     */
    public ShopactivityDao() {
    }

    public DataField get(int id) {
        String fieldArr = "id,name,counts,moneys,limitorder,limitproduct,limitsub,limitpercounts,starttime,endtime,warntime,remark,allingsub,allingtimes,matype,maaddtype,minmoney,maxmoney,tuanprice,paistartprice,paiminprice,paiperminusprice,paicounts,sid,url,qrcode";
        return getFirstData("select " + fieldArr + " from shopactivity where id=" + id, fieldArr);
    }

    public DataField getByMaaddtype(String maaddtype) {
        String fieldArr = "id,name,counts,moneys,limitorder,limitproduct,limitsub,limitpercounts,starttime,endtime,warntime,remark,allingsub,allingtimes,matype,maaddtype,minmoney,maxmoney,tuanprice,paistartprice,paiminprice,paiperminusprice,paicounts,sid,url,qrcode";
        return getFirstData("select " + fieldArr + " from shopactivity where maaddtype='" + maaddtype + "'", fieldArr);
    }

    public Collection getList(int sid, int CurrentPage, int PageSize) {
        StringBuffer sql = new StringBuffer(512);
        String fieldArr = "id,name,counts,moneys,limitorder,limitproduct,limitsub,limitpercounts,starttime,endtime,warntime,remark,allingsub,allingtimes,matype,maaddtype,minmoney,maxmoney,tuanprice,paistartprice,paiminprice,paiperminusprice,paicounts,sid,url,qrcode";
        sql.append("select ");
        sql.append(fieldArr);
        sql.append(" from shopactivity where sid=" + sid);
        if (CurrentPage >= 0 && PageSize > 0) {
            sql.append(" limit ");
            sql.append((CurrentPage - 1) * PageSize);
            sql.append(",");
            sql.append(PageSize);
        }
        return this.getData(sql.toString(), fieldArr);
    }

    public int getTotalCount(int sid) {
        StringBuffer sql = new StringBuffer(512);
        sql.append("select count(*) from shopactivity where sid=" + sid);
        return (this.getDataCount(sql.toString()));
    }

    public Collection getList(int sid) {
        StringBuffer sql = new StringBuffer(512);
        String fieldArr = "id,name,counts,moneys,limitorder,limitproduct,limitsub,limitpercounts,starttime,endtime,warntime,remark,allingsub,allingtimes,matype,maaddtype,minmoney,maxmoney,tuanprice,paistartprice,paiminprice,paiperminusprice,paicounts,sid,url,qrcode";
        sql.append("select ");
        sql.append(fieldArr);
        sql.append(" from shopactivity where sid=" + sid);
        return this.getData(sql.toString(), fieldArr);
    }

    public Collection getLimitTimeList(int sid) {
        StringBuffer sql = new StringBuffer(512);
        String fieldArr = "id,name,counts,moneys,limitorder,limitproduct,limitsub,limitpercounts,starttime,endtime,warntime,remark,allingsub,allingtimes,matype,maaddtype,minmoney,maxmoney,tuanprice,paistartprice,paiminprice,paiperminusprice,paicounts,sid,url,qrcode";
        sql.append("select ");
        sql.append(fieldArr);
        sql.append(" from shopactivity where sid=" + sid);
        sql.append(" and starttime<now() and endtime>now()");
        sql.append("order by id desc");
        return this.getData(sql.toString(), fieldArr);
    }

    public Collection getList(int sid, String vipgrade) {
        StringBuffer sql = new StringBuffer(512);
        String fieldArr = "id,name,counts,moneys,limitorder,limitproduct,limitsub,limitpercounts,starttime,endtime,warntime,remark,allingsub,allingtimes,matype,maaddtype,minmoney,maxmoney,tuanprice,paistartprice,paiminprice,paiperminusprice,paicounts,sid,url,qrcode";
        sql.append("select ");
        sql.append(fieldArr);
        sql.append(" from shopactivity where sid=" + sid);
        sql.append(" and limitsub=" + vipgrade);
        sql.append(" and starttime<now() and endtime>now()");
        return this.getData(sql.toString(), fieldArr);
    }

    public Collection getByproductList(int sid, String limitproduct) {
        StringBuffer sql = new StringBuffer(512);
        String fieldArr = "id,name,counts,moneys,limitorder,limitproduct,limitsub,limitpercounts,starttime,endtime,warntime,remark,allingsub,allingtimes,matype,maaddtype,minmoney,maxmoney,tuanprice,paistartprice,paiminprice,paiperminusprice,paicounts,sid,url,qrcode";
        sql.append("select ");
        sql.append(fieldArr);
        sql.append(" from shopactivity where sid=" + sid);
        sql.append(" and limitproduct='" + limitproduct + "'");
        sql.append(" and starttime<now() and endtime>now()");
        sql.append("order by counts desc");
        return this.getData(sql.toString(), fieldArr);
    }

    public Collection getBylimitproductList(int sid, String limitproduct) {
        StringBuffer sql = new StringBuffer(512);
        String fieldArr = "id,name,counts,moneys,limitorder,limitproduct,limitsub,limitpercounts,starttime,endtime,warntime,remark,allingsub,allingtimes,matype,maaddtype,minmoney,maxmoney,tuanprice,paistartprice,paiminprice,paiperminusprice,paicounts,sid,url,qrcode";
        sql.append("select ");
        sql.append(fieldArr);
        sql.append(" from shopactivity where sid=" + sid);
        sql.append(" and limitproduct like '%," + limitproduct + ",%'");
        sql.append(" and starttime<now() and endtime>now()");
        sql.append("order by counts desc");
        return this.getData(sql.toString(), fieldArr);
    }

    public Collection getBylimitproductUnionList(int sid, String limitproduct) {
        StringBuffer sql = new StringBuffer(512);
        String fieldArr = "id,name,counts,moneys,limitorder,limitproduct,limitsub,limitpercounts,starttime,endtime,warntime,remark,allingsub,allingtimes,matype,maaddtype,minmoney,maxmoney,tuanprice,paistartprice,paiminprice,paiperminusprice,paicounts,sid,url,qrcode";
        sql.append("select ");
        sql.append(fieldArr);
        sql.append(" from shopactivity where sid=" + sid);
        sql.append(" and limitproduct='0'");
        sql.append(" and starttime<now() and endtime>now()");
        sql.append(" union");
        sql.append(" select ");
        sql.append(fieldArr);
        sql.append(" from shopactivity where sid=" + sid);
        sql.append(" and limitproduct like '%," + limitproduct + ",%'");
        sql.append(" and starttime<now() and endtime>now()");
        sql.append("order by counts desc");
        return this.getData(sql.toString(), fieldArr);
    }

    //获取最后的ID 
    public int getLastId() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int returnValue = 0;
        String sql = null;
        if (DBUtils.getDatabaseType() == DBUtils.DATABASE_SQLSERVER) {
            sql = "select top 1 id from shopactivity order by id desc";
        } else if (DBUtils.getDatabaseType() == DBUtils.DATABASE_MYSQL) {
            sql = "select id from shopactivity order by id desc limit 0,1";
        }
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                returnValue = rs.getInt(1) + 1;
            } else {
                returnValue = 1;
            }
        } catch (SQLException e) {
            log.error("get last id", e);
        } finally {
            DBUtils.closeResultSet(rs);
            DBUtils.closePrepareStatement(ps);
            DBUtils.closeConnection(conn);
        }
        return returnValue;
    }

    public boolean add(String name, int counts, float moneys, String limitorder, String limitproduct, String limitsub, String limitpercounts, String starttime, String endtime, int warntime, String remark, int allingsub, int allingtimes, int matype, String maaddtype, int minmoney, int maxmoney, float tuanprice, float paistartprice, float paiminprice, float paiperminusprice, int paicounts, int sid, String url, String qrcode) {
        String fieldArr = "";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = true;
        String sql = "insert into shopactivity(name,counts,moneys,limitorder,limitproduct,limitsub,limitpercounts,starttime,endtime,warntime,remark,allingsub,allingtimes,matype,maaddtype,minmoney,maxmoney,tuanprice,paistartprice,paiminprice,paiperminusprice,paicounts,sid,url,qrcode) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, counts);
            ps.setFloat(3, moneys);
            ps.setString(4, limitorder);
            ps.setString(5, limitproduct);
            ps.setString(6, limitsub);
            ps.setString(7, limitpercounts);
            ps.setString(8, starttime);
            ps.setString(9, endtime);
            ps.setInt(10, warntime);
            ps.setString(11, remark);
            ps.setInt(12, allingsub);
            ps.setInt(13, allingtimes);
            ps.setInt(14, matype);
            ps.setString(15, maaddtype);
            ps.setInt(16, minmoney);
            ps.setInt(17, maxmoney);
            ps.setFloat(18, tuanprice);
            ps.setFloat(19, paistartprice);
            ps.setFloat(20, paiminprice);
            ps.setFloat(21, paiperminusprice);
            ps.setInt(22, paicounts);
            ps.setInt(23, sid);
            ps.setString(24, url);
            ps.setString(25, qrcode);
            if (ps.executeUpdate() != 1) {
                returnValue = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtils.closePrepareStatement(ps);
            DBUtils.closeConnection(conn);
        }
        return returnValue;
    }

    public boolean mod(int id, String name, int counts, float moneys, String limitorder, String limitproduct, String limitsub, String limitpercounts, String starttime, String endtime, int warntime, String remark, int allingsub, int allingtimes, int matype, String maaddtype, int minmoney, int maxmoney, float tuanprice, float paistartprice, float paiminprice, float paiperminusprice, int paicounts, int sid) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = true;
        String sql = "update shopactivity set name=?,counts=?,moneys=?,limitorder=?,limitproduct=?,limitsub=?,limitpercounts=?,starttime=?,endtime=?,warntime=?,remark=?,allingsub=?,allingtimes=?,matype=?,maaddtype=?,minmoney=?,maxmoney=?,tuanprice=?,paistartprice=?,paiminprice=?,paiperminusprice=?,paicounts=?,sid=? where id=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, counts);
            ps.setFloat(3, moneys);
            ps.setString(4, limitorder);
            ps.setString(5, limitproduct);
            ps.setString(6, limitsub);
            ps.setString(7, limitpercounts);
            ps.setString(8, starttime);
            ps.setString(9, endtime);
            ps.setInt(10, warntime);
            ps.setString(11, remark);
            ps.setInt(12, allingsub);
            ps.setInt(13, allingtimes);
            ps.setInt(14, matype);
            ps.setString(15, maaddtype);
            ps.setInt(16, minmoney);
            ps.setInt(17, maxmoney);
            ps.setFloat(18, tuanprice);
            ps.setFloat(19, paistartprice);
            ps.setFloat(20, paiminprice);
            ps.setFloat(21, paiperminusprice);
            ps.setInt(22, paicounts);
            ps.setInt(23, sid);
            ps.setInt(24, id);
            if (ps.executeUpdate() != 1) {
                returnValue = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtils.closePrepareStatement(ps);
            DBUtils.closeConnection(conn);
        }
        return returnValue;
    }

    public boolean mod(int id, String url, String qrcode) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = true;
        String sql = "update shopactivity set url=?,qrcode=? where id=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, url);
            ps.setString(2, qrcode);
            ps.setInt(3, id);
            if (ps.executeUpdate() != 1) {
                returnValue = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtils.closePrepareStatement(ps);
            DBUtils.closeConnection(conn);
        }
        return returnValue;
    }

    public boolean modallingsub(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = true;
        String sql = "update shopactivity set allingsub=allingsub+1 where id=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() != 1) {
                returnValue = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtils.closePrepareStatement(ps);
            DBUtils.closeConnection(conn);
        }
        return returnValue;
    }

    public boolean modallingtimes(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = true;
        String sql = "update shopactivity set allingtimes=allingtimes+1 where id=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() != 1) {
                returnValue = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtils.closePrepareStatement(ps);
            DBUtils.closeConnection(conn);
        }
        return returnValue;
    }

    public void batDel(String[] s) {
        this.bat("delete from shopactivity where id=?", s);
    }
}
