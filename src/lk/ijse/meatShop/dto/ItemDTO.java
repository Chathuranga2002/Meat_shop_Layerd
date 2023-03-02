package lk.ijse.meatShop.dto;

public class ItemDTO {
    private String item_code;
    private String category;
    private String description;
    private double unit_Price;
    private int qty_onHand;

    public ItemDTO() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_code='" + item_code + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", unit_Price=" + unit_Price +
                ", qty_onHand=" + qty_onHand +
                '}';
    }

    public ItemDTO(String item_code, String category, String description, double unit_Price, int qty_onHand) {
        this.item_code = item_code;
        this.category = category;
        this.description = description;
        this.unit_Price = unit_Price;
        this.qty_onHand = qty_onHand;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
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

    public double getUnit_Price() {
        return unit_Price;
    }

    public void setUnit_Price(double unit_Price) {
        this.unit_Price = unit_Price;
    }

    public int getQty_onHand() {
        return qty_onHand;
    }

    public void setQty_onHand(int qty_onHand) {
        this.qty_onHand = qty_onHand;
    }
}
