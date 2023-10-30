package com.example.constrailayout.model;

import java.io.Serializable;

public class Lop implements Serializable{

    private int id;
    private String tenLop;
    private String moTa;

    public Lop() {

    }

    public Lop(int id, String tenLop, String moTa) {
        this.id = id;
        this.tenLop = tenLop;
        this.moTa = moTa;
    }

    public Lop(String tenLop, String moTa) {
        this.tenLop = tenLop;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return
                "tên lớp = " + tenLop +
                ", mô tả = " + moTa;
    }
}
