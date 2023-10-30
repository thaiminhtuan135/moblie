package com.example.constrailayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.constrailayout.database.DbHelper;
import com.example.constrailayout.model.SinhVienLop;

public class DanhKyDetail extends AppCompatActivity {
    EditText edtidMSV;
    EditText edtidMaLop;
    EditText edtKyHoc;
    EditText edtSTC;
    Button btnDK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ky_detail);
        edtidMSV = findViewById(R.id.edtidMSV);
        edtidMaLop = findViewById(R.id.edtidMaLop);
        edtKyHoc = findViewById(R.id.edtKyHoc);
        edtSTC = findViewById(R.id.edtSTC);
        btnDK = findViewById(R.id.btnDK);

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper(getBaseContext());
                int idMSV = Integer.parseInt(edtidMSV.getText().toString());
                int idML = Integer.parseInt(edtidMaLop.getText().toString());
                int kh = Integer.parseInt(edtKyHoc.getText().toString());
                int stc = Integer.parseInt(edtSTC.getText().toString());
                SinhVienLop svl = new SinhVienLop(idMSV, idML, kh, stc);
                db.DangKy(svl);

                Toast.makeText(getBaseContext(), "Đăng Ký Thành Công!", Toast.LENGTH_LONG).show();
                reset();
                setResult(RESULT_OK);
                finish();

            }
        });
    }
    private void reset() {
        edtidMSV.setText("");
        edtidMaLop.setText("");
        edtKyHoc.setText("");
        edtSTC.setText("");
    }
}