package com.knights.hotelmanagementsystem;

public class hall_model {

    private String numpeople;
    private String hallType;
    private String date;
    private String time;
    private String fullName;
    private String email;
    private String phone;
    private int noOfHours;
    private int total;

    public hall_model() {

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNoOfHours() {
        return noOfHours;
    }

    public void setNoOfHours(int noOfHours) {
        this.noOfHours = noOfHours;
    }

    public void setNumpeople(String numpeople) { this.numpeople = numpeople;}

    public void setHallType(String hallType) {
        this.hallType = hallType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNumpeople() {
        return numpeople;
    }

    public String getHallType() {
        return hallType;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
