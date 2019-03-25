package com.example.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText etnombre, etusuario, etpassword, etemail;
    Button  btn_registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etnombre = (EditText) findViewById(R.id.EditT_nombre);
        etusuario= findViewById(R.id.EditT_usuario);
        etpassword= findViewById(R.id.EditT_password);
        etemail= findViewById(R.id.EditT_email);

        btn_registrar= findViewById(R.id.Btn_registrar);

        btn_registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String name=etnombre.getText().toString();
        final String username=etusuario.getText().toString();
        final String password=etpassword.getText().toString();
        final String email=etemail.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean  succes = jsonResponse.getBoolean("succes");
                if(succes){
                    Intent intent= new Intent(Registro.this,MainActivity.class);
                    Registro.this.startActivity(intent);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                    builder.setMessage("error en el restristo")
                            .setNegativeButton("Retry",null)
                            .create().show();


                }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };


        RegisterRequest registerRequest= new RegisterRequest(name, username, email, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Registro.this);
        queue.add(registerRequest);
    }
}

