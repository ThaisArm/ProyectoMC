package com.example.project4sw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {
    Button boton1, boton2, boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        boton1 = findViewById(R.id.btnCambioContraseña);
        boton2 = findViewById(R.id.btnRegistrarActividad);
        boton3 = findViewById(R.id.btnConsulta);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrincipalActivity.this, CambioContrasenaActivity.class));
            }
        });
    }
}