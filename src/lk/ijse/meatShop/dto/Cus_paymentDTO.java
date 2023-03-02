package lk.ijse.meatShop.dto;

public class Cus_paymentDTO {
    private String ord_id;
    private String date;
    private double price;
    private double payed;
    private  double balance;


    public Cus_paymentDTO(String ord_id, String date, double price, double payed, double balance) {
        this.ord_id = ord_id;
        this.date = date;
        this.price = price;
        this.payed = payed;
        this.balance = balance;
    }

    public Cus_paymentDTO() {
    }

    @Override
    public String toString() {
        return "Cus_payment{" +
                "ord_id='" + ord_id + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", payed=" + payed +
                ", balance=" + balance +
                '}';
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
