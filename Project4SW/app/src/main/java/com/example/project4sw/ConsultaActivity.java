package com.example.project4sw;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

import java.util.Calendar;

public class ConsultaActivity extends AppCompatActivity implements View.OnClickListener {
    private TableLayout tbReg;
    private EditText etFecCons;
    private Button btFecCons;
    private Button btCons;
    private String[] header = {"Tip. Actividad", "Actividad", "Observación"};
    private int day, month, year;
    BaseData b = new BaseData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        tbReg = (TableLayout) findViewById(R.id.tbRegistros);
        etFecCons = (EditText) findViewById(R.id.etFechaCons);
        btFecCons = (Button) findViewById(R.id.bFecCons);
        btFecCons.setOnClickListener(this);
        btCons = (Button) findViewById(R.id.btConsultar);
        Table table = new Table(tbReg, this.getApplicationContext());

        btCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etFecCons.getText().toString().isEmpty()) {
                    Toast.makeText(ConsultaActivity.this, "SELECCIONE UNA FECHA", Toast.LENGTH_LONG).show();
                } else {
                    cleanTable();
                    table.addHeader(header);
                    b.getData(etFecCons, table);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btFecCons) {
            final Calendar c = Calendar.getInstance();
            day = c.get(Calendar.DAY_OF_MONTH);
            month = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    etFecCons.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                }
            }
                    , 2022, month, day);
            datePickerDialog.show();
        }
    }

    public void cleanTable() {
        System.out.println(tbReg.getChildCount());
        int x = 0;
        do {
            System.out.println(tbReg.getChildCount());
            View v = tbReg.getChildAt(x);
            tbReg.removeView(v);
        } while (tbReg.getChildCount() != 0);
    }
}
