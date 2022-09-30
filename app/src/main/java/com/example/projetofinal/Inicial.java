package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicial extends AppCompatActivity {

    Button butcomecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        butcomecar = findViewById(R.id.butComecar);

        butcomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Inicial.this, Login.class));
            }
        });


        //metodos iniciais

    }
}