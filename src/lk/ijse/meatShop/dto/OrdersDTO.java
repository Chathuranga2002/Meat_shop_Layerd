package lk.ijse.meatShop.dto;

import lk.ijse.meatShop.entity.Order_detail;

import java.util.ArrayList;

public class OrdersDTO {
    private String ord_id;
    private String date;
    private String cus_id;
    private String emp_id;
    private ArrayList<Order_detail> cartDeatils ;

    public OrdersDTO(String ord_id, String date, String cus_id, String emp_id, ArrayList<Order_detail> cartDeatils) {
        this.ord_id = ord_id;
        this.date = date;
        this.cus_id = cus_id;
        this.emp_id = emp_id;
        this.cartDeatils = cartDeatils;
    }

    public ArrayList<Order_detail> getCartDeatils() {
        return cartDeatils;
    }

    public void setCartDeatils(ArrayList<Order_detail> cartDeatils) {
        this.cartDeatils = cartDeatils;
    }

    public OrdersDTO(String ord_id, String date, String cus_id, String emp_id) {
        this.ord_id = ord_id;
        this.date = date;
        this.cus_id = cus_id;
        this.emp_id = emp_id;
    }

    public OrdersDTO() {
    }

    public String getOrd_id() {
        return ord_id;
    }

    public void setOrd_id(String ord_id) {
        this.ord_id = ord_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ord_id='" + ord_id + '\'' +
                ", date='" + date + '\'' +
                ", cus_id='" + cus_id + '\'' +
                ", emp_id='" + emp_id + '\'' +
                '}';
    }
}
