package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

public class Cadastro extends AppCompatActivity {

    TextView txtentrar;
    Button btCadastrar;

    EditText inputUser;
    EditText inputSenha;
    EditText inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inputUser = findViewById(R.id.inputUser);
        inputSenha = findViewById(R.id.inputSenha);
        inputEmail = findViewById(R.id.inputEmail);

        txtentrar = findViewById(R.id.txtEntrar);

        btCadastrar = findViewById(R.id.btCadastrar);





        txtentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( Cadastro.this, Login.class));
            }

        });


        //metodo de cadastrar
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Indicando que irá utilizar o webservice rodando no localhost do computador
                String url = "http://10.0.2.2:5000/api/Usuario";

                try {

                    //Criar um objeto que irá transformar os dados preenchidos na tela em JSON
                    JSONObject dadosEnvio = new JSONObject();

                    //O nome dos parâmetros precisam ser iguais ao que o webservice espera receber
                    //no nosso caso são "nome", "email", "senha", "fotoPerfil" e "dataNascimento"
                    dadosEnvio.put("nome", inputUser.getText().toString());
                    dadosEnvio.put("senha", inputSenha.getText().toString());
                    dadosEnvio.put("email", inputEmail.getText().toString());



                    //Configurar a requisição que será enviada ao webservice
                    JsonObjectRequest configRequisicao = new JsonObjectRequest(Request.Method.POST,
                            url, dadosEnvio,
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(Cadastro.this, "feito1", Toast.LENGTH_SHORT).show();

                                    try {

                                        if(response.getInt("status") == 200){
                                            startActivity(new Intent(Cadastro.this,Bycomp.class));

                                            Toast.makeText(Cadastro.this, "feito", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Snackbar.make(findViewById(R.id.telaLogin), "Verifique se os dados estão corretos", Snackbar.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        //Snackbar.make(findViewById(R.id.telaLogin), R.string.avisoErro, Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    // Snackbar.make(findViewById(R.id.telaLogin), R.string.avisoErro, Snackbar.LENGTH_SHORT).show();
                                }
                            }
                    );

                    RequestQueue requisicao = Volley.newRequestQueue(Cadastro.this);
                    requisicao.add(configRequisicao);

                }catch (Exception exc){
                    exc.printStackTrace();
                }



            }


        });


    }
}