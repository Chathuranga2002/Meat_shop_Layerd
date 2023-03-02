package lk.ijse.meatShop.entity;

public class Feedback {
    private String cus_id;
    private String comment;
    private int rete;

    public Feedback() {
    }

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
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

    public Feedback(String cus_id, String comment, int rete) {
        this.cus_id = cus_id;
        this.comment = comment;
        this.rete = rete;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "cus_id='" + cus_id + '\'' +
                ", comment='" + comment + '\'' +
                ", rete=" + rete +
                '}';
    }
}
