package lk.ijse.meatShop.entity;

public class CustomEntity {
    //suplier
    private String sup_id;

    //employee
    private String emp_id;
    private String user_name;
    private String password;
    private String rool;
    //customer
    private String cus_id;

    //sup+emp+cus
    private String tel_no;
    private String name;
    private String address;
    private String nic;
    //item +stoks
    private String item_code;
    private String category;
    private String description;
    private double unit_Price;
    private int qty_onHand;
    private String code;



    //buy
    private String buy_id;
    private String ord_id;
   //buy+order
    private String date;
    private double price;
    private double payed;
    private  double balance;
   //buy deatils
    private int qty;
    private double unitPrice;

    private String comment;
    private int rete;

    @Override
    public String toString() {
        return "CustomEntity{" +
                "sup_id='" + sup_id + '\'' +
                ", emp_id='" + emp_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", rool='" + rool + '\'' +
                ", cus_id='" + cus_id + '\'' +
                ", tel_no='" + tel_no + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", item_code='" + item_code + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", unit_Price=" + unit_Price +
                ", qty_onHand=" + qty_onHand +
                ", code='" + code + '\'' +
                ", buy_id='" + buy_id + '\'' +
                ", ord_id='" + ord_id + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", payed=" + payed +
                ", balance=" + balance +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", comment='" + comment + '\'' +
                ", rete=" + rete +
                '}';
    }

    public CustomEntity() {
    }

    public String getSup_id() {
        return sup_id;
    }

    public void setSup_id(String sup_id) {
        this.sup_id = sup_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRool() {
        return rool;
    }

    public void setRool(String rool) {
        this.rool = rool;
    }

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    public String getTel_no() {
        return tel_no;
    }

    public void setTel_no(String tel_no) {
        this.tel_no = tel_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(String buy_id) {
        this.buy_id = buy_id;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRete() {
        return rete;
    }

    public void setRete(int rete) {
        this.rete = rete;
    }
}
