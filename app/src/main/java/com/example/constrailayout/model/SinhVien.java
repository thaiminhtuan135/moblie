package com.example.constrailayout.model;

import java.io.Serializable;

public class SinhVien implements Serializable {

    private int id;
    private String ten;
    private int namSinh;
    private String queQuan;
    private String namHoc;

    public SinhVien() {

    }

    public SinhVien(int id, String ten, int namSinh, String queQuan, String namHoc) {
        this.id = id;
        this.ten = ten;
        this.namSinh = namSinh;
        this.queQuan = queQuan;
        this.namHoc = namHoc;
    }

    public SinhVien(String ten, int namSinh, String queQuan, String namHoc) {
        this.ten = ten;
        this.namSinh = namSinh;
        this.queQuan = queQuan;
        this.namHoc = namHoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    @Override
    public String toString() {
        return
                "tên= " + ten +
                ", năm sinh= " + namSinh +
                ", quê quán= " + queQuan +
                ", năm học= " + namHoc;
    }
}
