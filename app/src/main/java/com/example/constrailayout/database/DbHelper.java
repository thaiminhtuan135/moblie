package com.example.constrailayout.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.constrailayout.model.Lop;
import com.example.constrailayout.model.SinhVien;
import com.example.constrailayout.model.SinhVienLop;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "thicuoiki";
    public static final String TABLE_NAME1 = "sinhvien";
    public static final String TABLE_NAME2 = "lop";
    public static final String TABLE_NAME3 = "sinhvien_lophoc";
    private Context context;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME1 + "(id integer primary key autoincrement, ten text, namsinh integer, quequan text, namhoc text) ";
        db.execSQL(query);
        query = "create table " + TABLE_NAME2 + " (id integer primary key autoincrement, tenLop text, moTa text)";
        db.execSQL(query);
        query = "create table " + TABLE_NAME3 + " (id_sinhvien integer, id_lophoc integer, kyHoc int, soTinChi int, foreign key(id_sinhvien) references sinhvien(id), foreign key(id_lophoc) references lop(id))";
        db.execSQL(query);
        Log.e("DB", "DB");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("drop table if exists " + TABLE_NAME2);
        db.execSQL("drop table if exists " + TABLE_NAME3);
        onCreate(db);
    }

    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> list = new ArrayList<>();
        String sqlQuery = "Select * from " + TABLE_NAME1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        while (cursor.moveToNext()) {
            list.add(new SinhVien
                    (
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    )
            );
        }
        cursor.close();
        db.close();
        return list;
    }


    public int addSinhVien(SinhVien sinhVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", sinhVien.getTen());
        values.put("namsinh", sinhVien.getNamSinh());
        values.put("quequan", sinhVien.getQueQuan());
        values.put("namhoc", sinhVien.getNamHoc());
        return (int) db.insert(TABLE_NAME1, null, values);
    }

    public List<SinhVien> readSinhVienFromFile() {
        List<SinhVien> list = new ArrayList<>();

        try {
            FileInputStream inputStream = context.openFileInput("sv.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
//                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                int namSinh = Integer.parseInt(data[2].trim());
                String queQuan = data[3].trim();
                String namHoc = data[4].trim();

                SinhVien sinhVien = new SinhVien(name, namSinh, queQuan, namHoc);
                list.add(sinhVien);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Lop> getAllLop() {
        List<Lop> list = new ArrayList<>();
        String sqlQuery = "Select * from " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        while (cursor.moveToNext()) {
            list.add(new Lop
                    (
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2)
                    )
            );
        }
        cursor.close();
        db.close();
        return list;
    }

    public void addLop(Lop lop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLop", lop.getTenLop());
        values.put("moTa", lop.getMoTa());
        db.insert(TABLE_NAME2, null, values);
        db.close();
    }


    public List<SinhVien> findNameNam2() {
        List<SinhVien> list = new ArrayList<>();
        String sqlQuery = "Select * from " + TABLE_NAME1 + " where ten like '%nam%' and namHoc like '%2%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        while (cursor.moveToNext()) {
            list.add(new SinhVien
                    (
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    )
            );
        }
        cursor.close();
        db.close();
        return list;
    }

    public SinhVien laySinhVien(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "Select * from sinhvien where id = " + id;
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        SinhVien sinhVien = new SinhVien(cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getString(3),
                cursor.getString(4));
        cursor.close();
        db.close();
        return sinhVien;
    }

    public Lop layLopHoc(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "Select * from lophoc where id = " + id;
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Lop lophoc = new Lop(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2));
        cursor.close();
        db.close();
        return lophoc;
    }
//    public List<ThongKe> layDanhSachThongKe() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String sqlQuery = "Select lophoc.id, lophoc.tenLop, count(*) as soSinhVien from sinhvien_lophoc " +
//                "inner join lophoc on sinhvien_lophoc.id_lophoc = lophoc.id " +
//                "group by id_lophoc order by soSinhVien desc";
//        Cursor cursor = db.rawQuery(sqlQuery, null);
//        List<ThongKe> listThongKe = new ArrayList<>();
//        if(cursor.moveToFirst()) {
//            do {
//                ThongKe thongKe = new ThongKe();
//                thongKe.setIdLop(cursor.getInt(0));
//                thongKe.setTenLop(cursor.getString(1));
//                thongKe.setSoSinhVien(cursor.getInt(2));
//                listThongKe.add(thongKe);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return listThongKe;
//    }

    public void DangKy(SinhVienLop svl) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_sinhvien", svl.getIdmsv());
        values.put("id_lophoc", svl.getIdmalop());
        values.put("kyHoc", svl.getKyhoc());
        values.put("soTinChi", svl.getSotinchi());
        db.insert("sinhvien_lophoc", null, values);
        db.close();
    }

    public List<SinhVienLop> getAllDangKy() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM sinhvien_lophoc";
        List<SinhVienLop> sinhVienLopList = new ArrayList<>();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                SinhVienLop sinhVienLop = new SinhVienLop();
                sinhVienLop.setIdmsv(cursor.getInt(0));
                sinhVienLop.setIdmalop(cursor.getInt(1));
                sinhVienLop.setKyhoc(cursor.getInt(2));
                sinhVienLop.setSotinchi(cursor.getInt(3));

                sinhVienLopList.add(sinhVienLop);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sinhVienLopList;
    }

    public <T> List<T> readFromFile(String filename) {
        List<T> list = new ArrayList<>();

        try {
            FileInputStream fileInputStream = context.openFileInput(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            while (true) {
                try {
                    T t = (T) objectInputStream.readObject();
                    list.add(t);
                } catch (EOFException e) {
                    break;
                }
            }
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    public <T> void addToFile(T t, String filename) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_APPEND);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(t);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}