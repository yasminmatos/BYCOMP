package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Cadastro extends AppCompatActivity {

    TextView txtentrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtentrar = findViewById(R.id.txtEntrar);

        txtentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( Cadastro.this, Login.class));
            }
        });




    }
}