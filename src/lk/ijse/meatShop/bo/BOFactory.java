package lk.ijse.meatShop.bo;

import lk.ijse.meatShop.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ADDEMPLOYEE,ADDFEEDBACK,ADDITEM,ADDTOSHOWCASE,ADDSUPPLIER,VIEWFEEDBACK,PAYMENT,VIEWEMP,UPDATEEMP,VIEWSUP
        ,VVIEWORDER,BUYITEM,PLACEORDER
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new AddCustomerBOImpl();
            case ADDEMPLOYEE:
                return new AddEmployeeBOImpl();
            case ADDFEEDBACK:
                return new AddFeedbackBOImpl();
            case ADDITEM:
                return new AddItemBOImpl();
            case ADDTOSHOWCASE:
                return new AddShowcaseBOImpl();
            case ADDSUPPLIER:
                return new AddSupplierBOImpl();
            case VIEWFEEDBACK:
                return new FeedbackBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case VIEWEMP:
                return new ViewEmployeeBOImpl();
            case UPDATEEMP:
                return new UpdateEmployeeBOImpl();
            case VIEWSUP:
                return new ViewSupplierBOImpl();
            case VVIEWORDER:
                return new ViewOrdersBOImpl();
            case BUYITEM:
                return new BuyItemBOImpl();
            case PLACEORDER:
                return new ChashierDashBordBOImpl();
            default:
                return null;
        }
    }

}
