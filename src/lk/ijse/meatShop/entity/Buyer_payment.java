package lk.ijse.meatShop.entity;

public class Buyer_payment {
    private String buy_id ;
    private String date;
    private double price;
    private double payed;
    private double balance;

    public Buyer_payment() {
    }

    public Buyer_payment(String buy_id, String date, double price, double payed, double balance) {
        this.buy_id = buy_id;
        this.date = date;
        this.price = price;
        this.payed = payed;
        this.balance = balance;
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

    @Override
    public String toString() {
        return "Buyer_payment{" +
                "buy_id='" + buy_id + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", payed=" + payed +
                ", balance=" + balance +
                '}';
    }
}