package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
//import android.graphics.Bitmap;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.util.Base64;
import android.text.TextUtils;
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

import classesmodelos.Usuario;

//import java.io.ByteArrayOutputStream;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class Login extends AppCompatActivity {

    //criando chaves constantes para o shared preferences
    public static final String SHARED_PREFS = "shared_prefs";

    //chave para guardar o user
    public static final String USUARIO_KEY = "usuario_key";

    //chave para guardar a senha
    public static final String SENHA_KEY = "senha_key";

    // variaveis para o shared preferences
    SharedPreferences sharedpreferences;

    EditText userEd, senhaEd;
    TextView Criarconta;
    Button btLogar;
    String user, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //pegando os ids
        Criarconta= findViewById(R.id.Criarconta);
        btLogar = findViewById(R.id.btLogar);
        userEd = findViewById(R.id.inputUserL);
        senhaEd = findViewById(R.id.inputSenhaL);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Cadastro.MODE_PRIVATE);
        user = sharedpreferences.getString(USUARIO_KEY, null);
        senha = sharedpreferences.getString(SENHA_KEY, null);

        //ir para tela de cadastro
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

                //verifica se os campos estão preenchidos
                if(TextUtils.isEmpty(userEd.getText().toString().trim()) && TextUtils.isEmpty(senhaEd.getText().toString().trim())){
                    Toast.makeText(Login.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    //implementando firebase





                    //faz o login
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    // pega os valores e coloca no shared preferences
                    editor.putString(USUARIO_KEY, userEd.getText().toString());
                    editor.putString(SENHA_KEY, senhaEd.getText().toString());

                    editor.apply();
                }



            }
        });
 /*

                //metodo do conecn=çao como mysql
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
                                        //verifica se existe aquele user no banco
                                        if (response.getInt("status") == 200) {
                                            //cadastrar usuatio no banco de dados local
                                            //variaveis a serem utilizadas
                                            BCDlocal bd =  null;
                                            Usuario u = null;
                                            String resultado;

                                            //pega os dados do input e coloca na variavel do tipo Usuario
                                            u.setNome(user.getText().toString());
                                            u.setSenha(user.getText().toString());

                                            //coloca os dados no método que verifica os dados no banco local
                                            resultado = bd.Logar(u);
                                            Toast.makeText(Login.this, "Bem-vindo", Toast.LENGTH_SHORT).show();
                                            //vai para a tela inicial
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
                }*/


    }
}