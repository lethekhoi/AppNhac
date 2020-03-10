package com.example.appnhac.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TheLoai implements Serializable {

    @SerializedName("IdTheLoai")
    @Expose
    private String idTheLoai;
    @SerializedName("IdKeyChuDe")
    @Expose
    private String idKeyChuDe;
    @SerializedName("TenTheLoai")
    @Expose
    private String tenTheLoai;
    @SerializedName("HinhTheLoai")
    @Expose
    private String hinhTheLoai;

    protected TheLoai(Parcel in) {
        idTheLoai = in.readString();
        idKeyChuDe = in.readString();
        tenTheLoai = in.readString();
        hinhTheLoai = in.readString();
    }


    public String getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(String idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getIdKeyChuDe() {
        return idKeyChuDe;
    }

    public void setIdKeyChuDe(String idKeyChuDe) {
        this.idKeyChuDe = idKeyChuDe;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getHinhTheLoai() {
        return hinhTheLoai;
    }

    public void setHinhTheLoai(String hinhTheLoai) {
        this.hinhTheLoai = hinhTheLoai;
    }


}
