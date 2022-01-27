package com.example.project4sw;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ComboBoxData {
    private AsyncHttpClient cliente = new AsyncHttpClient();
    public ArrayList<Data> lista = new ArrayList<Data>();

    public String idByName(String name) {
        for (Data d : this.lista) {
            if (d.getName().equals(name)) {
                return d.getId();
            }
        }
        return null;
    }

    public void llenarSpinner(RegistroActivity r, Spinner spinner, String url, String columName, String columID) {
        this.cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    cargarSpinner(r, new String(responseBody), spinner, columName, columID);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void llenarSpinnerByID(RegistroActivity r, Spinner spinner, String url, String columName, String columID, String columID_VER, String ID) {
        this.cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    cargarSpinnerById(r, new String(responseBody), spinner, columName, columID, columID_VER, ID);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void cargarSpinner(RegistroActivity r, String respuesta, Spinner spinner, String columName, String columID) {
        try {
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for (int i = 0; i < jsonArreglo.length(); i++) {
                Data tp = new Data();
                tp.setName(jsonArreglo.getJSONObject(i).getString(columName));
                tp.setId(jsonArreglo.getJSONObject(i).getString(columID));
                this.lista.add(tp);
            }
            ArrayAdapter<Data> t = new ArrayAdapter<Data>(r, android.R.layout.simple_dropdown_item_1line, lista);
            spinner.setAdapter(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarSpinnerById(RegistroActivity r, String respuesta, Spinner spinner, String columName, String columID, String columID_VER, String ID) {
        this.lista.clear();
        try {
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for (int i = 0; i < jsonArreglo.length(); i++) {
                Data tp = new Data();
                tp.setName(jsonArreglo.getJSONObject(i).getString(columName));
                tp.setId(jsonArreglo.getJSONObject(i).getString(columID));
                if (jsonArreglo.getJSONObject(i).getString(columID_VER).equals(ID)) {
                    this.lista.add(tp);
                }
            }
            ArrayAdapter<Data> t = new ArrayAdapter<Data>(r, android.R.layout.simple_dropdown_item_1line, lista);
            spinner.setAdapter(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
