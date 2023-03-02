package lk.ijse.meatShop.dto;

public class EmployeeDTO {
    private String emp_id;
    private String user_name;
    private String password;
    private String nic;
    private String name;
    private String address;
    private String rool;
    private String tel_no;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String emp_id, String user_name, String nic, String name, String address, String rool,String tel_no) {
        this.emp_id = emp_id;
        this.user_name = user_name;
        this.tel_no = tel_no;
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.rool = rool;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_id='" + emp_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rool='" + rool + '\'' +
                ", tel_no='" + tel_no + '\'' +
                '}';
    }

    public EmployeeDTO(String emp_id, String user_name, String password, String nic, String name, String address, String rool, String tel_no) {
        this.emp_id = emp_id;
        this.user_name = user_name;
        this.password = password;
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.rool = rool;
        this.tel_no = tel_no;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public String getRool() {
        return rool;
    }

    public void setRool(String rool) {
        this.rool = rool;
    }

    public String getTel_no() {
        return tel_no;
    }

    public void setTel_no(String tel_no) {
        this.tel_no = tel_no;
    }
}
