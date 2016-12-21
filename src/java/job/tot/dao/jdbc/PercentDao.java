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
public class PercentDao extends AbstractDao {

    public List<DataField> getList(int currentpage, int pagesize) {//popular 人气
        List list = (List) DaoFactory.getMysqlDao().getList("select id,counts,type,limitwx,limittrade,limitmoney,isnotice from percent order by id desc limit " + (currentpage - 1) * pagesize + "," + pagesize, "id,counts,type,limitwx,limittrade,limitmoney,isnotice");
        return list;
    }

    public int getTotalNum() {
        int returnValue = 0;
        StringBuffer sql = new StringBuffer(512);
        sql.append("select count(*) from percent");
        returnValue = getDataCount(sql.toString());
        return returnValue;
    }

    public List<DataField> getList() {//popular 人气
        List list = (List) DaoFactory.getMysqlDao().getList("select id,counts,type,limitwx,limittrade,limitmoney,isnotice from percent order by counts desc limit 0,9", "id,counts,type,limitwx,limittrade,limitmoney,isnotice");
        return list;
    }

    public DataField get(int id) {//popular 人气
        DataField df = DaoFactory.getMysqlDao().getFirstData("select id,counts,type,limitwx,limittrade,limitmoney,isnotice from percent where id=" + id, "id,counts,type,limitwx,limittrade,limitmoney,isnotice");
        return df;
    }

    public boolean add(int counts, int type, int limitwx, int limittrade, float limitmoney, int isnotice) {
        boolean flag = DaoFactory.getMysqlDao().add("insert into percent(counts,limitwx,type,limittrade,limitmoney,isnotice) values (" + counts + "," + type + "," + limitwx + "," + limittrade + "," + limitmoney + "," + isnotice + ")");
        return flag;
    }

    public boolean mod(int id, int counts, int type, int limitwx, int limittrade, float limitmoney, int isnotice) {
        boolean flag = DaoFactory.getMysqlDao().mod("update percent set counts=" + counts + ",type=" + type + ",limitwx=" + limitwx + ",limittrade=" + limittrade + ",limitmoney=" + limitmoney + ",isnotice= " + isnotice + " where id=" + id);
        return flag;
    }

    public void batDel(String[] s) {
        this.bat("delete from percent where id=?", s);
    }
}
