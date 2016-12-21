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
public class TDetailDAO extends AbstractDao {

    public List<DataField> getList(String openid, String targetopenid, int currentpage, int pagesize) {//popular 人气
        String tempStr = ("".equals(openid) ? "" : (" and openid='" + openid + "' ")) + ("".equals(targetopenid) ? "" : (" and targetopenid='" + targetopenid + "' "));
        List list = (List) DaoFactory.getMysqlDao().getList("select id,openid,targetopenid,times,persize,remark from t_detail where 1=1 " + tempStr + " order by id desc limit " + (currentpage - 1) * pagesize + "," + pagesize, "id,openid,targetopenid,times,persize,remark");
        return list;
    }

    public int getTotalNum(String openid, String targetopenid) {
        int returnValue = 0;
        StringBuffer sql = new StringBuffer(512);
        String tempStr = ("".equals(openid) ? "" : (" and openid='" + openid + "' ")) + ("".equals(targetopenid) ? "" : (" and targetopenid='" + targetopenid + "' "));
        sql.append("select count(*) from t_detail where 1=1" + tempStr);
        returnValue = getDataCount(sql.toString());
        return returnValue;
    }

    public List<DataField> getList() {//persize 助养份数
        List list = (List) DaoFactory.getMysqlDao().getList("select id,openid,targetopenid,times,persize,remark from t_detail order by id desc", "id,openid,targetopenid,times,persize,remark");
        return list;
    }

    public List<DataField> getList(String remark) {//persize 助养份数
        List list = (List) DaoFactory.getMysqlDao().getList("select id,openid,targetopenid,times,persize,remark from t_detail where remark='" + remark + "' order by id desc", "id,openid,targetopenid,times,persize,remark");
        return list;
    }

    public List<DataField> getList(String openid, String targetopenid, String remark) {//persize 助养份数
        List list = (List) DaoFactory.getMysqlDao().getList("select id,openid,targetopenid,times,persize,remark from t_detail where openid='" + openid + "' and targetopenid='" + targetopenid + "' and remark='" + remark + "' order by id desc", "id,openid,targetopenid,times,persize,remark");
        return list;
    }

    public boolean add(String openid, String targetopenid, String times, int persize, String remark) {
        boolean flag = DaoFactory.getMysqlDao().add("insert into t_detail(openid,targetopenid,times,persize,remark) values ('" + openid + "','" + targetopenid + "','" + times + "'," + persize + ",'" + remark + "')");
        return flag;
    }
}
