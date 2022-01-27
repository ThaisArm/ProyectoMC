package com.example.project4sw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText edtUsuario;
    EditText edtClave;
    Button boton;
    public String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.txtUsuario);
        edtClave = findViewById(R.id.pwdPass);
        boton = findViewById(R.id.btnNotificar);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = edtUsuario.getText().toString();
                validarUsuario("http://192.168.8.143/empresati/validar_usuario.php");
            }
        });
    }

    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest (Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    intent.putExtra("usuario", edtUsuario.getText().toString());
                    startActivity(intent);
                    edtUsuario.setText("");
                    edtClave.setText("");
                    Toast.makeText(MainActivity.this, "INGRESO EXITOSO", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "USUARIO Y/O CONTRASEÑA SON INCORRECTOS", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("usuario", edtUsuario.getText().toString());
                parametros.put("password", edtClave.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}