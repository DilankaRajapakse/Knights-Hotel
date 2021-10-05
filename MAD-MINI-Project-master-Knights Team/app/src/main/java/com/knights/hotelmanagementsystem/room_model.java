package com.knights.hotelmanagementsystem;

public class room_model {

    private String checkIn;
    private String checkOut;
    private String roomlist;
    private String numofRooms;
    private String numofAdults;
    private String numofChildren;
    private String fullnin;
    private String emin;
    private String phonein;
    private int total;

    public room_model() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getNumofAdults() {
        return numofAdults;
    }

    public void setNumofAdults(String numofAdults) {
        this.numofAdults = numofAdults;
    }

    public String getNumofChildren() {
        return numofChildren;
    }

    public void setNumofChildren(String numofChildren) {
        this.numofChildren = numofChildren;
    }

    public String getRoomlist() {
        return roomlist;
    }

    public void setRoomlist(String roomlist) {
        this.roomlist = roomlist;
    }

    public String getNumofRooms() {
        return numofRooms;
    }

    public void setNumofRooms(String numofRooms) {
        this.numofRooms = numofRooms;
    }


    public String getFullnin() {
        return fullnin;
    }

    public void setFullnin(String fullnin) {
        this.fullnin = fullnin;
    }

    public String getEmin() {
        return emin;
    }

    public void setEmin(String emin) {
        this.emin = emin;
    }

    public String getPhonein() {
        return phonein;
    }

    public void setPhonein(String phonein) {
        this.phonein = phonein;
    }
}
