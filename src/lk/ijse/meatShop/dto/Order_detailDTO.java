package lk.ijse.meatShop.dto;

public class Order_detailDTO {
    private String ord_id;
    private String   item_code;
    private int qty;
    private double unitPrice;

    @Override
    public String toString() {
        return "Order_detail{" +
                "ord_id='" + ord_id + '\'' +
                ", item_code='" + item_code + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public Order_detailDTO() {
    }

    public Order_detailDTO(String ord_id, String item_code, int qty, double unitPrice) {
        this.ord_id = ord_id;
        this.item_code = item_code;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getOrd_id() {
        return ord_id;
    }

    public void setOrd_id(String ord_id) {
        this.ord_id = ord_id;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
