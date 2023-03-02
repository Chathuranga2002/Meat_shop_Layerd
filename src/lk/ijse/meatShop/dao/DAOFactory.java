package lk.ijse.meatShop.dao;

import lk.ijse.meatShop.dao.custom.CustomerDAO;
import lk.ijse.meatShop.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,EMPLOYEE, BUY,ORDER_DETAILS,QUERY_DAO,FEEDBACK,ITEM,STOKS,SUPPLIYER,BUYPAYMENT,ORDER
        ,BUYDEATILS
    }

    public lk.ijse.meatShop.dao.SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CstomerDAOImpl() ;
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case BUY:
                return new BuyDAOImpl();
            case FEEDBACK:
                return new FeedbackDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case STOKS:
                return new StoksDAOImpl();
            case SUPPLIYER:
                return new SupplierDAOImpl();
            case BUYPAYMENT:
                return new PaymentDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDeatilsDAOImpl();
            case BUYDEATILS:
                return new BuyDeatilsDAOImpl();
            case QUERY_DAO:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }


}
