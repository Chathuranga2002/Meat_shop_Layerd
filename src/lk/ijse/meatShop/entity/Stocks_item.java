package lk.ijse.meatShop.entity;

public class Stocks_item {
    private String stocks_code;
    private String item_code;
    private String date;
    private int qty;

    public Stocks_item(String stocks_code, String item_code, String date, int qty) {
        this.stocks_code = stocks_code;
        this.item_code = item_code;
        this.date = date;
        this.qty = qty;
    }

    public String getStocks_code() {
        return stocks_code;
    }

    public void setStocks_code(String stocks_code) {
        this.stocks_code = stocks_code;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Stocks_item{" +
                "stocks_code='" + stocks_code + '\'' +
                ", item_code='" + item_code + '\'' +
                ", date='" + date + '\'' +
                ", qty=" + qty +
                '}';
    }

    public Stocks_item() {
    }
}
