package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView Criarconta;
    Button btLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //funções de tela login para tela

        Criarconta= findViewById(R.id.Criarconta);
        btLogar = findViewById(R.id.btLogar);

        //ir oara tela de cadastro
        Criarconta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Login.this,Cadastro.class)




                );
            }
        });

        // botao logar

        //entrar na tela do aplicativo

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Bycomp.class));

            }
        });








    }
}