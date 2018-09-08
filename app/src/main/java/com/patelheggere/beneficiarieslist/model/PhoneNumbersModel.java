package com.patelheggere.beneficiarieslist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PhoneNumbersModel implements Parcelable{

    private String name;
    private String phone;
    private String id;
    private boolean cp, p, lm;
    private String pwd;

    public PhoneNumbersModel() {
    }

    public PhoneNumbersModel(String name, String phone, String id, boolean cp, boolean p, boolean lm, String pwd) {
        this.name = name;
        this.phone = phone;
        this.id = id;
        this.cp = cp;
        this.p = p;
        this.lm = lm;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCp() {
        return cp;
    }

    public void setCp(boolean cp) {
        this.cp = cp;
    }

    public boolean isP() {
        return p;
    }

    public void setP(boolean p) {
        this.p = p;
    }

    public boolean isLm() {
        return lm;
    }

    public void setLm(boolean lm) {
        this.lm = lm;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public static Creator<PhoneNumbersModel> getCREATOR() {
        return CREATOR;
    }

    protected PhoneNumbersModel(Parcel in) {
        name = in.readString();
        phone = in.readString();
        id = in.readString();
        cp = in.readByte() != 0;
        p = in.readByte() != 0;
        lm = in.readByte() != 0;
        pwd = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(id);
        dest.writeByte((byte) (cp ? 1 : 0));
        dest.writeByte((byte) (p ? 1 : 0));
        dest.writeByte((byte) (lm ? 1 : 0));
        dest.writeString(pwd);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhoneNumbersModel> CREATOR = new Creator<PhoneNumbersModel>() {
        @Override
        public PhoneNumbersModel createFromParcel(Parcel in) {
            return new PhoneNumbersModel(in);
        }

        @Override
        public PhoneNumbersModel[] newArray(int size) {
            return new PhoneNumbersModel[size];
        }
    };
}
