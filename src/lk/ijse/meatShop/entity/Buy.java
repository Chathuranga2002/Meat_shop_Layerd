package lk.ijse.meatShop.entity;

public class Buy {
    private String buy_id;
    private String date;
    private String sup_id;

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

    public Buy() {
    }

    public Buy(String buy_id, String date, String sup_id) {
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
