package com.example.constrailayout.model;

public class SinhVienLop {
    private int idmsv;
    private int idmalop;
    private int kyhoc;
    private int sotinchi;

    private SinhVien sv;
    private Lop lop;


    public SinhVienLop(){}
    public SinhVienLop(int idmsv, int idmalop, int kyhoc, int sotinchi) {
        this.idmsv = idmsv;
        this.idmalop = idmalop;
        this.kyhoc = kyhoc;
        this.sotinchi = sotinchi;
    }

    public int getIdmsv() {
        return idmsv;
    }

    public void setIdmsv(int idmsv) {
        this.idmsv = idmsv;
    }

    public int getIdmalop() {
        return idmalop;
    }

    public void setIdmalop(int idmalop) {
        this.idmalop = idmalop;
    }

    public int getKyhoc() {
        return kyhoc;
    }

    public void setKyhoc(int kyhoc) {
        this.kyhoc = kyhoc;
    }

    public int getSotinchi() {
        return sotinchi;
    }

    public void setSotinchi(int sotinchi) {
        this.sotinchi = sotinchi;
    }

    @Override
    public String toString() {
        return "Mã Sinh Viên: "+ idmsv + " - Mã Lớp: " + idmalop + " - Kỳ học: " + kyhoc + " - STC: " + sotinchi;
    }

}


