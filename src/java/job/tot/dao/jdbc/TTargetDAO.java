/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package job.tot.dao.jdbc;

import java.util.List;
import job.tot.bean.DataField;
import job.tot.dao.AbstractDao;
import job.tot.dao.DaoFactory;

/**
 *
 * @author Administrator
 */
public class TTargetDAO extends AbstractDao {

    public List<DataField> getList(int currentpage, int pagesize) {//popular 人气
        List list = (List) DaoFactory.getMysqlDao().getList("select id,openid,name,tel,img,counts,times,popular from t_target order by counts desc limit " + (currentpage - 1) * pagesize + "," + pagesize, "id,openid,name,tel,img,counts,times,popular");
        return list;
    }

    public int getTotalNum() {
        int returnValue = 0;
        StringBuffer sql = new StringBuffer(512);
        sql.append("select count(*) from t_target");
        returnValue = getDataCount(sql.toString());
        return returnValue;
    }

    public List<DataField> getList() {//popular 人气
        List list = (List) DaoFactory.getMysqlDao().getList("select id,openid,name,tel,img,counts,times,popular from t_target order by counts desc,id", "id,openid,name,tel,img,counts,times,popular");
        return list;
    }

    public DataField getPaiming(String openid) {
        DataField df = DaoFactory.getMysqlDao().getFirstData("select count(id) from t_target where counts>(select counts from t_target where openid='" + openid + "')", "counts");
        return df;
    }

    public DataField getByOpenid(String openid) {
        DataField df = DaoFactory.getMysqlDao().getFirstData("select id,openid,name,tel,img,counts,times,popular from t_target where openid='" + openid + "'", "id,openid,name,tel,img,counts,times,popular");
        return df;
    }

    public boolean add(String openid, String name, String tel, String img, int counts, String times, int popular) {
        boolean flag = DaoFactory.getMysqlDao().add("insert into t_target(openid,name,tel,img,counts,times,popular) values ('" + openid + "','" + name + "','" + tel + "','" + img + "'," + counts + ",'" + times + "'," + popular + ")");
        return flag;
    }

    public boolean updateCounts(int counts) {
        boolean flag = DaoFactory.getMysqlDao().mod("update t_target set counts=counts+" + counts);
        return flag;
    }

    public boolean updateCounts(String openid, int counts) {
        boolean flag = DaoFactory.getMysqlDao().mod("update t_target set counts=counts+" + counts + " where openid='" + openid + "'");
        return flag;
    }

    public boolean updatePopular(String openid, String popular) {
        boolean flag = DaoFactory.getMysqlDao().mod("update t_target set popular=popular+" + popular + " where openid='" + openid + "'");
        return flag;
    }
}
