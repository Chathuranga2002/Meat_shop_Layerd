package lk.ijse.meatShop.dto;

import lk.ijse.meatShop.entity.Buy_detail;
import lk.ijse.meatShop.entity.Stocks;

import java.util.ArrayList;

public class BuyDTO {
    private String buy_id;
    private String date;
    private String sup_id;
    private double tot;
    private double payed;
    private double balance;
    private ArrayList <Buy_detail>buyDeatils;

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }

    public double getPayed() {
        return payed;
    }

    public void setPayed(double payed) {
        this.payed = payed;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public BuyDTO(String buy_id, String date, String sup_id, double tot, double payed, double balance, ArrayList<Buy_detail> buyDeatils) {
        this.buy_id = buy_id;
        this.date = date;
        this.sup_id = sup_id;
        this.tot = tot;
        this.payed = payed;
        this.balance = balance;
        this.buyDeatils = buyDeatils;
    }

    public ArrayList<Buy_detail> getBuyDeatils() {
        return buyDeatils;
    }

    public void setBuyDeatils(ArrayList<Buy_detail> buyDeatils) {
        this.buyDeatils = buyDeatils;
    }

    public String getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(String buy_id) {
        this.buy_id = buy_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSup_id() {
        return sup_id;
    }

    public void setSup_id(String sup_id) {
        this.sup_id = sup_id;
    }

    public BuyDTO() {
    }

    public BuyDTO(String buy_id, String date, String sup_id) {
        this.buy_id = buy_id;
        this.date = date;
        this.sup_id = sup_id;
    }

    @Override
    public String toString() {
        return "Buy{" +
                "buy_id='" + buy_id + '\'' +
                ", date='" + date + '\'' +
                ", sup_id='" + sup_id + '\'' +
                '}';
    }
}
