package com.example.project4sw;

import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class BaseData {
    private AsyncHttpClient cliente = new AsyncHttpClient();
    private String url = "http://192.168.8.143/empresati/buscar.php";
    private String col1 = "TIP_ACT_REG";
    private String col2 = "ACT_REG";
    private String col3 = "OBS_REG";
    private ArrayList<Registros> dato = new ArrayList<>();

    public void voidData() {
        if (!this.dato.isEmpty()) {
            this.dato.clear();
        }
    }

    public void getData(EditText etFecCons, Table table) {
        voidData();
        String fec = etFecCons.getText().toString();
        String urlDate = url + "?fecha=" + fec;
        String[] item = {"A", "B", "C"};

        this.cliente.post(urlDate, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    try {
                        JSONArray jsonArreglo = new JSONArray(new String(responseBody));
                        for (int i = 0; i < jsonArreglo.length(); i++) {
                            Registros r = new Registros();
                            r.setTip(jsonArreglo.getJSONObject(i).getString(col1));
                            r.setAct(jsonArreglo.getJSONObject(i).getString(col2));
                            r.setObs(jsonArreglo.getJSONObject(i).getString(col3));
                            dato.add(r);
                        }
                        for (Registros r : dato) {
                            item[0] = r.getTip();
                            item[1] = r.getAct();
                            item[2] = r.getObs();
                            table.addHeader(item);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
