package com.patelheggere.beneficiarieslist.model;

public class DetailsModel {
    private String benefit;
    private String enteredBy;
    private String enteredMobile;
    private String village;
    private String ward;
    private String name;
    private String mobile;
    private String epic;
    private String date;

    public DetailsModel() {
    }

    public DetailsModel(String date, String benefit, String enteredBy, String enteredMobile, String village, String ward, String name, String mobile, String epic) {
        this.benefit = benefit;
        this.enteredBy = enteredBy;
        this.enteredMobile = enteredMobile;
        this.village = village;
        this.ward = ward;
        this.name = name;
        this.mobile = mobile;
        this.epic = epic;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getEnteredMobile() {
        return enteredMobile;
    }

    public void setEnteredMobile(String enteredMobile) {
        this.enteredMobile = enteredMobile;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEpic() {
        return epic;
    }

    public void setEpic(String epic) {
        this.epic = epic;
    }
}
