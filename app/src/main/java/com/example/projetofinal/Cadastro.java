package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import classesmodelos.BCDlocal;

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

        RequestQueue queue = Volley.newRequestQueue(Cadastro.this);
        String url = "http://10.0.2.2:5000/api/Usuario";

        txtentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cadastro.this, Login.class));
            }

        });


        //metodo de cadastrar
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Indicando que irá utilizar o webservice rodando no localhost do computador

                try {

                    //Criar um objeto que irá transformar os dados preenchidos na tela em JSON
                    JSONObject dadosEnvio = new JSONObject();

                    //parametros que ele espera receber
                    dadosEnvio.put("senha", inputSenha.getText().toString());
                    dadosEnvio.put("email", inputEmail.getText().toString());
                    dadosEnvio.put("user", inputUser.getText().toString());



                    //Configurar a requisição que será enviada ao webservice
                    JsonObjectRequest configRequisicao = new JsonObjectRequest(Request.Method.POST,
                            url, dadosEnvio,
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {

                                    Toast.makeText(Cadastro.this, "feito1", Toast.LENGTH_SHORT).show();

                                    try {

                                        if (response.getInt("status") == 200) {
                                            startActivity(new Intent(Cadastro.this, Bycomp.class));

                                            Toast.makeText(Cadastro.this, "deu certo ", Toast.LENGTH_SHORT).show();
                                            // Toast.makeText(Cadastro.this, Cadastro.CadastroUsuarioBCDLocal(inputUser.getText().toString(), inputSenha.getText().toString(),inputEmail.getText().toString()), Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(Cadastro.this, "Verifique se os dados estão corretos", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {

                                        Toast.makeText(Cadastro.this, "nao feito" + e.getMessage(), Toast.LENGTH_SHORT).show();

                                        e.printStackTrace();
                                        //Snackbar.make(findViewById(R.id.telaLogin), R.string.avisoErro, Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    Toast.makeText(Cadastro.this, "Erro de resposta: " + error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    );


                    queue.add(configRequisicao);

                } catch (Exception exc) {
                    exc.printStackTrace();
                    Toast.makeText(Cadastro.this, "Erro de conexao: " + exc, Toast.LENGTH_SHORT).show();
                }


            }

        });

        //metodo para cadastrar o usuario no banco de dados local, deve ser puchado em um toast
        // para aparacer a mensagem em questao se deu certo ou nao

    }

    @SuppressWarnings("serial")
    public class ServerError extends VolleyError {
        public ServerError(NetworkResponse networkResponse) {
            super(networkResponse);
        }

        public ServerError() {
            super();
        }
    }
}
      //private static String CadastroUsuarioBCDLocal(String u, String s, String e){


         // Cadastro cadastro = new Cadastro();


        //criar objeto da classe do banco dedados local
         // BCDlocal bcd = new BCDlocal(cadastro,1);

          //if(bcd.cadastrarUsuario(u,s,e)){
           //   return "Usuario cadastrado com sucesso (Local)";


       //   }else{
              //return  "Erro ao cadastrar (Local)";
         // }


     // }

   // }
