package com.knights.hotelmanagementsystem;

public class delivery_model {
    private String name;
    private String email;
    private String phone;
    private String fooditm;
    private String quantity;
    private String address;
    private String city;
    private int totalprice;

    public delivery_model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFooditm() {
        return fooditm;
    }

    public void setFooditm(String fooditm) {
        this.fooditm = fooditm;
    }

    public String getQuantity() { return quantity; }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTotalprice() { return totalprice; }

    public void setTotalprice(int totalprice) { this.totalprice = totalprice; }
}
