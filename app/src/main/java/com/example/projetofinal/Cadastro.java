package com.example.projetofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

import classesmodelos.BCDlocal;
import classesmodelos.Usuario;

public class Cadastro extends AppCompatActivity {



    private FirebaseAuth mAuth;

    TextView txtentrar;
    Button btCadastrar;

    EditText inputUser;
    EditText inputSenha;
    EditText inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mAuth = FirebaseAuth.getInstance();

        inputUser = findViewById(R.id.inputUser);
        inputSenha = findViewById(R.id.inputSenha);
        inputEmail = findViewById(R.id.inputEmail);


        txtentrar = findViewById(R.id.txtEntrar);
        btCadastrar = findViewById(R.id.btCadastrar);

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
//implementando firebase

                String usuario, senha, email;

                usuario = inputUser.getText().toString();
                senha= inputSenha.getText().toString();
                email= inputEmail.getText().toString();

//pegando as strigs e as colocando como parametro, é criado um metodo do firebase
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //pega uma instancia do usuario em questao ao salvar
                            FirebaseFirestore ff = FirebaseFirestore.getInstance();

                            //usado com lista definidora para salvamentod de várias informações
                            Map<String, Object> mp = new HashMap<>();

                            mp.put("nome usuario", usuario);
                            mp.put("senha", senha);
                            mp.put("email", email);

                            //o qeu sera colocado no firebase, expecificando as coleçoes (usuarios no caso)
                            DocumentReference documentReference = ff.collection("Usuarios").document(email);

                            //aplica no firebase as informaçoes montadas antes


                            try {
                                documentReference.set(mp).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                        Log.e("TAGGGGG", "Cadastro efetivado");


                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Log.e("TAGGGGG", "---->" + e);

                                    }
                                });

                            }
                            catch (Exception e){


                                Log.e("ERROOOO", "------>" + e);


                            }
                            }

                    }
                });



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


    /*private void enviarDadosWebservice(){
        String url = "http://10.0.2.2:5000/api/Usuario";

        try {
            JSONObject dadosEnvio = new JSONObject();
            dadosEnvio.put("user", inputUser.getText().toString());
            dadosEnvio.put("email", inputEmail.getText().toString());
            dadosEnvio.put("senha", inputSenha.getText().toString());

            //Configurar a requisição que será enviada ao webservice
            JsonObjectRequest configRequisicao = new JsonObjectRequest(Request.Method.POST,
                    url, dadosEnvio,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if(response.getInt("status") == 200){
                                    Snackbar.make(findViewById(R.id.actyCadastro),"Cadastrado com sucesso ", Snackbar.LENGTH_SHORT).show();
                                }else{
                                    Snackbar.make(findViewById(R.id.actyCadastro), "Erro ao cadastrar", Snackbar.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Snackbar.make(findViewById(R.id.actyCadastro), "JsonException: " + e.toString(), Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Snackbar.make(findViewById(R.id.actyCadastro), "Erro de resposta: " + error.toString(), Snackbar.LENGTH_SHORT).show();
                        }
                    }
            );
            RequestQueue requisicao = Volley.newRequestQueue(Cadastro.this);
            requisicao.add(configRequisicao);

        }catch (Exception exc){
            exc.printStackTrace();
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
    }*/


}

