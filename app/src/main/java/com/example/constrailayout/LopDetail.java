package com.example.constrailayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.constrailayout.R;
import com.example.constrailayout.database.DbHelper;
import com.example.constrailayout.model.Lop;

import java.util.List;

public class LopDetail extends AppCompatActivity {
    EditText edtTenLop;
    EditText edtMota;
    Button btnThemLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop_detail);
        edtTenLop = findViewById(R.id.editTenLopHoc);
        edtMota = findViewById(R.id.editMoTa);
        btnThemLop = findViewById(R.id.btnThemLopHoc);
        btnThemLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper(getBaseContext());
                String tenlop = edtTenLop.getText().toString();
                String mota = edtMota.getText().toString();
                Lop lop = new Lop(tenlop, mota);
                db.addLop(lop);

                Toast.makeText(getBaseContext(), "Add success", Toast.LENGTH_LONG).show();
                reset();
                setResult(RESULT_OK);
                finish();
            }
        });
        loadListLop();
    }

    private void loadListLop() {
        DbHelper db = new DbHelper(getBaseContext());
        List<Lop> list = db.getAllLop();
        ArrayAdapter<Lop> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        ListView listView = findViewById(R.id.listLopHoc);
        listView.setAdapter(adapter);
    }

    private void reset() {
        edtMota.setText("");
        edtTenLop.setText("");
    }
}