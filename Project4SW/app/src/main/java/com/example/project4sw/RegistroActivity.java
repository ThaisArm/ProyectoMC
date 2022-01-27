package com.example.project4sw;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    private AsyncHttpClient cliente;
    private Spinner spTipAct;
    private Spinner spAct;
    private Spinner spEst;
    private EditText edFecha;
    private Button bFecha;
    private EditText etPerReg;
    private Spinner spPerSol;
    private EditText edHor;
    private EditText edObs;
    private Button bRegistro;
    private String url1;
    private String url2;
    private String url3;
    private String url4;
    private int day, month, year;
<<<<<<< HEAD
    private MainActivity m = new MainActivity();
    private TextView tvPrueba;
=======
>>>>>>> develop

    ComboBoxData cbxTypesActivity = new ComboBoxData();
    ComboBoxData cbxActivities = new ComboBoxData();
    ComboBoxData cbxStates = new ComboBoxData();
    ComboBoxData cbxPerSol = new ComboBoxData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        spTipAct = (Spinner) findViewById(R.id.spTipAct);
        spAct = (Spinner) findViewById(R.id.spAct);
        spEst = (Spinner) findViewById(R.id.spEstado);
        edFecha = (EditText) findViewById(R.id.etFecha);
        bFecha = (Button) findViewById(R.id.btFecha);
        bFecha.setOnClickListener(this);
        etPerReg = (EditText) findViewById(R.id.etPerReg);
        spPerSol = (Spinner) findViewById(R.id.spPerSol);
        edHor = (EditText) findViewById(R.id.etHoras);
        edObs = (EditText) findViewById(R.id.etObs);
        bRegistro = (Button) findViewById(R.id.btRegistrar);
        cliente = new AsyncHttpClient();
<<<<<<< HEAD
        tvPrueba = (TextView) findViewById(R.id.tvPrueba);
=======

        etPerReg.setText(getIntent().getExtras().getString("usuario"));
>>>>>>> develop

        url1 = "http://192.168.8.143/empresati/listar_tip_act.php";
        url2 = "http://192.168.8.143/empresati/listar_act.php";
        url3 = "http://192.168.8.143/empresati/listar_empleados.php";
        url4 = "http://192.168.8.143/empresati/listar_est.php";

        cbxTypesActivity.llenarSpinner(this, spTipAct, url1, "NOM_TIP", "ID_TIP");
        cbxStates.llenarSpinner(this, spEst, url4, "NOM_EST", "ID_EST");
        cbxPerSol.llenarSpinner(this, spPerSol, url3, "NOM_EMP", "CED_EMP");

        spTipAct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                String msupplier = spTipAct.getSelectedItem().toString();
                String id = cbxTypesActivity.idByName(msupplier);
                llenarCbx(id);
                if (id == null) {

                } else {
                    //Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        botonAlmacenar();
    }

    private void llenarCbx(String ID) {
        cbxActivities.llenarSpinnerByID(this, this.spAct, this.url2, "NOM_ACT", "ID_ACT", "ID_TIP_PER", ID);
    }

    @Override
    public void onClick(View v) {
        if (v == bFecha) {
            final Calendar c = Calendar.getInstance();
            day = c.get(Calendar.DAY_OF_MONTH);
            month = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    edFecha.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                }
            }
                    , 2022, month, day);
            datePickerDialog.show();
        }
    }

    private void botonAlmacenar() {
        bRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spTipAct.getSelectedItem().toString().isEmpty() ||
                        spAct.getSelectedItem().toString().isEmpty() ||
                        edFecha.getText().toString().isEmpty() ||
                        etPerReg.getText().toString().isEmpty() ||
                        spPerSol.getSelectedItem().toString().isEmpty() ||
                        spEst.getSelectedItem().toString().isEmpty() ||
                        edHor.getText().toString().isEmpty() ||
                        edObs.getText().toString().isEmpty()
                ) {
                    Toast.makeText(RegistroActivity.this, "EXISTEN CAMPOS EN BLANCO", Toast.LENGTH_LONG).show();
                } else {
                    Registros r = new Registros();
                    r.setTip(spTipAct.getSelectedItem().toString().replaceAll(" ","%20"));
                    r.setAct(spAct.getSelectedItem().toString().replaceAll(" ","%20"));
                    r.setFec(edFecha.getText().toString());
                    r.setPerReg(etPerReg.getText().toString());
                    r.setPerSol(spPerSol.getSelectedItem().toString().replaceAll(" ","%20"));
                    r.setEst(spEst.getSelectedItem().toString().replaceAll(" ","%20"));
                    r.setHor(edHor.getText().toString());
                    r.setObs(edObs.getText().toString().replaceAll(" ","%20"));
                    agregarProducto(r);
                }
            }
        });
    }

    private void agregarProducto(Registros r) {
        String url = "http://192.168.8.143/empresati/agregar.php?";
        String parametros = "Tipo=" + r.getTip() + "&Actividad=" + r.getAct() + "&Fecha=" + r.getFec()
                + "&PerReg=" + r.getPerReg() + "&PerSol=" + r.getPerSol() + "&Estado=" + r.getEst()
                + "&Horas=" + r.getHor() + "&Obs=" + r.getObs();
        cliente.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    edFecha.setText("");
                    etPerReg.setText("");
                    edHor.setText("");
                    edObs.setText("");
                    Toast.makeText(RegistroActivity.this, "REGISTRO EXITOSO", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
