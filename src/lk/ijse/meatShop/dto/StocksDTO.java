package lk.ijse.meatShop.dto;

public class StocksDTO {
    private String code;
    private String category;
    private String description;
    private int qty_onHand;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty_onHand() {
        return qty_onHand;
    }

    public void setQty_onHand(int qty_onHand) {
        this.qty_onHand = qty_onHand;
    }

    @Override
    public String toString() {
        return "Stocks{" +
                "code='" + code + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", qty_onHand=" + qty_onHand +
                '}';
    }

    public StocksDTO() {
    }

    public StocksDTO(String code, String category, String description, int qty_onHand) {
        this.code = code;
        this.category = category;
        this.description = description;
        this.qty_onHand = qty_onHand;
    }
}
