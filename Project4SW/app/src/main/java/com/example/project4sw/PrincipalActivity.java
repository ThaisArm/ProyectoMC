package com.example.project4sw;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class PrincipalActivity extends AppCompatActivity {
    Button boton1, boton2, boton3;
    TextView tvBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Intent i = getIntent();
        final String usuario = i.getStringExtra("usuario");

        boton1 = findViewById(R.id.btnCambioPass);
        boton2 = findViewById(R.id.btnRegistrarActividad);
        boton3 = findViewById(R.id.btnConsulta);
        tvBienvenida = findViewById(R.id.tvBienvenida);

        tvBienvenida.setText("¡Bienvenido" + " " + getIntent().getExtras().getString("usuario") + "!");

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View cabioContrasenaLayout = LayoutInflater.from(PrincipalActivity.this).inflate(R.layout.activity_cambio_contrasena, null);
                View cabioContrasenaLayoutUser = LayoutInflater.from(PrincipalActivity.this).inflate(R.layout.activity_login, null);
                EditText edtClaveActual = cabioContrasenaLayout.findViewById(R.id.pwdContrasActual);
                EditText edtClave1 = cabioContrasenaLayout.findViewById(R.id.pwdNuevaContras);
                EditText edtClave2 = cabioContrasenaLayout.findViewById(R.id.pwdNuevaContras2);
                EditText edtUser = cabioContrasenaLayoutUser.findViewById(R.id.txtUsuario);
                edtUser.setText(usuario);


                AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalActivity.this);
                builder.setTitle("CAMBIO DE CONTRASEÑA");
                builder.setView(cabioContrasenaLayout);
                builder.setPositiveButton("GUARDAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String oldPass = edtClaveActual.getText().toString().trim();
                        String newPass = edtClave1.getText().toString().trim();
                        String newPass2 = edtClave2.getText().toString().trim();
                        String user = edtUser.getText().toString().trim();

                        if (oldPass.isEmpty() || newPass.isEmpty() || newPass2.isEmpty()) {
                            message("CAMPOS VACÍOS");
                        } else {
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.8.143/empresati/cambio_contraseña.php",
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            message(response);
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    message(error.getMessage());
                                }
                            }) {

                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("oldPass", oldPass);
                                    params.put("newPass", newPass);
                                    params.put("newPass2", newPass2);
                                    params.put("user", user);
                                    return params;
                                }
                            };
                            RequestQueue queue = Volley.newRequestQueue(PrincipalActivity.this);
                            queue.add(stringRequest);
                        }
                    }
                });

                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ACTU
                Intent intent = new Intent(PrincipalActivity.this, RegistroActivity.class);

                intent.putExtra("usuario", getIntent().getExtras().getString("usuario"));
                startActivity(intent);
                //FIN_ACTU

                //startActivity(new Intent(PrincipalActivity.this, RegistroActivity.class));
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrincipalActivity.this, ConsultaActivity.class));
            }
        });
    }

    public void message(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}