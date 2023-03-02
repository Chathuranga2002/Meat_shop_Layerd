package lk.ijse.meatShop.dto;

public class Buy_detailDTO {
    private String buy_id;
    private String code;
    private int qty;
    private double unitPrice;


    public Buy_detailDTO() {
    }

    public Buy_detailDTO(String buy_id, String code, int qty, double unitPrice) {
        this.buy_id = buy_id;
        this.code = code;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Buy_detail{" +
                "buy_id='" + buy_id + '\'' +
                ", code='" + code + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public String getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(String buy_id) {
        this.buy_id = buy_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
