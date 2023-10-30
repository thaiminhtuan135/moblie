package com.example.constrailayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.constrailayout.database.DbHelper;
import com.example.constrailayout.model.SinhVien;

import java.io.IOException;
import java.util.List;

public class SinhVienDetail extends AppCompatActivity {
    EditText edtTenSV;
    EditText edtQue;
    Spinner spinnerNamHoc;
    EditText edtNamSinh;
    Button btnThemSV;
    DbHelper db = new DbHelper(getBaseContext());
    Button btnLK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien_detail);
        edtTenSV = findViewById(R.id.editTenSV);
        edtQue = findViewById(R.id.editQueQuan);
        spinnerNamHoc = findViewById(R.id.spinnerNamHoc);
        edtNamSinh = findViewById(R.id.editNamSinh);

        btnThemSV = findViewById(R.id.btnThemSV);
        btnLK = findViewById(R.id.btnLK);

        btnThemSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper(getBaseContext());
                String ten = edtTenSV.getText().toString();
                int namsinh = Integer.parseInt(edtNamSinh.getText().toString());
                String que = edtQue.getText().toString();
                String namhoc = spinnerNamHoc.getSelectedItem().toString();
                SinhVien sinhVien = new SinhVien(ten, namsinh, que, namhoc);
//                db.addToFile(sinhVien,"sv.txt");
                db.addSinhVien(sinhVien);
                Toast.makeText(getBaseContext(), "Add Success", Toast.LENGTH_LONG).show();
                reset();
                setResult(RESULT_OK, null);
                finish();
            }
        });
        loadListSinhVien();
        btnLK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lietKe();
            }
        });
    }

    private void loadListSinhVien() {
        DbHelper db = new DbHelper(getBaseContext());
        List<SinhVien> list = db.getAllSinhVien();
        ArrayAdapter<SinhVien> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        ListView listView = findViewById(R.id.listThongTinSV);
        listView.setAdapter(adapter);
    }

    private void lietKe() {
        DbHelper db = new DbHelper(getBaseContext());
        List<SinhVien> sinhVienList = db.findNameNam2();
        ArrayAdapter<SinhVien> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sinhVienList);
        ListView listView = findViewById(R.id.listThongTinSV);
        listView.setAdapter(adapter);
    }

    protected void reset() {
        edtTenSV.setText("");
        edtQue.setText("");
        edtNamSinh.setText("");
        spinnerNamHoc.setSelection(0);
    }
}