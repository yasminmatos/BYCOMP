package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
//import android.graphics.Bitmap;
import android.os.Bundle;
//import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetofinal.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import classesmodelos.BCDlocal;

//import java.io.ByteArrayOutputStream;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class Login extends AppCompatActivity {

    EditText user, senha;
    TextView Criarconta;
    Button btLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //funções de tela login para tela

        Criarconta= findViewById(R.id.Criarconta);
        btLogar = findViewById(R.id.btLogar);
        user = findViewById(R.id.inputUserL);
        senha = findViewById(R.id.inputSenhaL);


        //ir oara tela de cadastro
        Criarconta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Login.this,Cadastro.class)
                );
            }
        });

        //entrar na tela do aplicativo

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login.this, Bycomp.class));

                //Indicando que irá utilizar o webservice rodando no localhost do computador
                String url = "http://10.0.2.2:5000/api/Usuario";



                try {
                    //Criar um objeto que irá transformar os dados preenchidos na tela em JSON
                    JSONObject dadosEnvio = new JSONObject();
                    //O nome dos parâmetros precisam ser iguais ao que o webservice espera receber
                    dadosEnvio.put("user", user.getText().toString());
                    dadosEnvio.put("senha", senha.getText().toString());

                    //Configurar a requisição que será enviada ao webservice
                    JsonObjectRequest configRequisicao = new JsonObjectRequest(Request.Method.POST,
                            url, dadosEnvio,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if (response.getInt("status") == 200) {
                                            BCDlocal bd =  null;
                                           // bd.Logar(user, senha);
                                            Toast.makeText(Login.this, "Bem-vindo", Toast.LENGTH_SHORT).show();
                                            Intent it = new Intent(Login.this, HomeFragment.class); //activity para um fragmento
                                            startActivity(it);
                                        } else {
                                            Toast.makeText(Login.this, "Verifque se os dados estão corretos", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(Login.this, "Erro do JSON:" + e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    Toast.makeText(Login.this, "Erro resposta: " + error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    );

                    RequestQueue requisicao = Volley.newRequestQueue(Login.this);
                    requisicao.add(configRequisicao);

                } catch (Exception exc) {
                    exc.printStackTrace();
                    Toast.makeText(Login.this, "Erro do envio de dados: " + exc.toString(), Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}