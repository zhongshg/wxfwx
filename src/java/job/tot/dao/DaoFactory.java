/*
 * DaoFactory.java
 *
 * Created on 2006��7��27��, ����11:59
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package job.tot.dao;

import job.tot.dao.jdbc.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import job.tot.dao.jdbc.TDetailDAO;
import job.tot.dao.jdbc.TTargetDAO;

/**
 *
 * @author Administrator
 */
public class DaoFactory {

    private static Log log = LogFactory.getLog(DaoFactory.class);
    private static CategoryDaoImplJDBC CategoryDao = new CategoryDaoImplJDBC();
    private static ProductDaoImplJDBC ProductDao = new ProductDaoImplJDBC();
    private static OrderDaoImplJDBC OrderDao = new OrderDaoImplJDBC();
    private static BasketDaoImplJDBC BasketDao = new BasketDaoImplJDBC();
    private static SlideDaoImplJDBC SlideDao = new SlideDaoImplJDBC();
    private static AddressDaoImplJDBC AddressDao = new AddressDaoImplJDBC();
    private static MysqlDaoImplJDBC MysqlDao = new MysqlDaoImplJDBC();
    private static FundsDaoImplJDBC Funds = new FundsDaoImplJDBC();
    private static ExportlogDaoImplJDBC exportlogDaoImplJDBC = new ExportlogDaoImplJDBC();
    private static PropertysDaoImplJDBC propertysDaoImplJDBC = new PropertysDaoImplJDBC();
    private static AreaDaoImplJDBC areaDaoImplJDBC = new AreaDaoImplJDBC();
    private static CourierDaoImplJDBC courierDaoImplJDBC = new CourierDaoImplJDBC();
    private static Order2DaoImplJDBC order2DaoImplJDBC = new Order2DaoImplJDBC();
    private static TTargetDAO targetDAO = new TTargetDAO();
    private static TDetailDAO tDetailDAO = new TDetailDAO();
    private static GiftsDAO giftsDAO = new GiftsDAO();
    private static ShopactivityDao shopactivityDAO = new ShopactivityDao();
    private static VipDao vipDao = new VipDao();
    private static PercentDao percentDao = new PercentDao();
    private static ShopactivitydetailDao shopactivitydetailDao = new ShopactivitydetailDao();

    public static FundsDaoImplJDBC getFundsDao() {
        return Funds;
    }

    public static MysqlDaoImplJDBC getMysqlDao() {

        return MysqlDao;

    }

    public static CategoryDaoImplJDBC getCategoryDAO() {
        return CategoryDao;
    }

    public static ProductDaoImplJDBC getProductDAO() {
        return ProductDao;
    }

    public static OrderDaoImplJDBC getOrderDAO() {
        return OrderDao;
    }

    public static BasketDaoImplJDBC getBasketDAO() {
        return BasketDao;
    }

    public static SlideDaoImplJDBC getSlideDAO() {
        return SlideDao;
    }

    public static AddressDaoImplJDBC getAddressDAO() {
        return AddressDao;
    }

    public static ExportlogDaoImplJDBC getExportlogDaoImplJDBC() {
        return exportlogDaoImplJDBC;
    }

    public static PropertysDaoImplJDBC getPropertysDaoImplJDBC() {
        return propertysDaoImplJDBC;
    }

    public static AreaDaoImplJDBC getAreaDaoImplJDBC() {
        return areaDaoImplJDBC;
    }

    public static CourierDaoImplJDBC getCourierDaoImplJDBC() {
        return courierDaoImplJDBC;
    }

    public static Order2DaoImplJDBC getOrder2DaoImplJDBC() {
        return order2DaoImplJDBC;
    }

    public static TTargetDAO getTargetDAO() {
        return targetDAO;
    }

    public static TDetailDAO gettDetailDAO() {
        return tDetailDAO;
    }

    public static GiftsDAO getGiftsDAO() {
        return giftsDAO;
    }

    public static ShopactivityDao getShopactivityDAO() {
        return shopactivityDAO;
    }

    public static VipDao getVipDao() {
        return vipDao;
    }

    public static PercentDao getPercentDao() {
        return percentDao;
    }

    public static ShopactivitydetailDao getShopactivitydetailDao() {
        return shopactivitydetailDao;
    }

    public DaoFactory() {
    }
}
