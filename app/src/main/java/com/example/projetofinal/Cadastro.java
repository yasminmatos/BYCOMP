package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
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
import classesmodelos.Usuario;

public class Cadastro extends AppCompatActivity {

    TextView txtentrar;
    Button btCadastrar;

    EditText inputUser;
    EditText inputSenha;
    EditText inputEmail;
    String url = "http://10.0.2.2:5001/api/Usuario";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inputUser = findViewById(R.id.inputUser);
        inputSenha = findViewById(R.id.inputSenha);
        inputEmail = findViewById(R.id.inputEmail);

        txtentrar = findViewById(R.id.txtEntrar);

        btCadastrar = findViewById(R.id.btCadastrar);



        RequestQueue requisicao = Volley.newRequestQueue(Cadastro.this);

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
                enviarDadosWebService();
            }
        });


   // @SuppressWarnings("serial")
    //public class ServerError extends VolleyError {
      //  public ServerError(NetworkResponse networkResponse) {
      //      super(networkResponse);
       // }

       // public ServerError() {
      //      super();
        //}
  //  }
//}


    }
    private void enviarDadosWebService(){
        try {
            //Criar um objeto que irá transformar os dados preenchidos na tela em JSON
            JSONObject dadosEnvio = new JSONObject();

            //parametros que vão ser passados
            dadosEnvio.put("senha", inputSenha.getText().toString());
            dadosEnvio.put("email", inputEmail.getText().toString());
            dadosEnvio.put("user", inputUser.getText().toString());

            JsonObjectRequest configReq = new JsonObjectRequest(Request.Method.POST, url, dadosEnvio,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try{
                                if(response.getInt("status") == 200){
                                    startActivity(new Intent(Cadastro.this, Bycomp.class));
                                    Toast.makeText(Cadastro.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Cadastro.this, "Verifique se os dados estão corretos", Toast.LENGTH_SHORT).show();
                                }
                            }catch (JSONException e){
                                Toast.makeText(Cadastro.this, "Erro JSON: " + e.getMessage(), Toast.LENGTH_SHORT);
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(Cadastro.this, "Erro de resposta: " + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            );
            RequestQueue requisicao = Volley.newRequestQueue(Cadastro.this);
            requisicao.add(configReq);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("serial")
    public class NoConnectionError extends NetworkError {
        public NoConnectionError() {
            super();
        }

        public NoConnectionError(Throwable reason) {
            super(reason);
        }
    }
}

