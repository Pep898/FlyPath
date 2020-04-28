package com.example.flypath;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.example.flypath.BD.PostRegistreUsuari;


public class Registrar extends AppCompatActivity {
    PostRegistreUsuari PRU;
    EditText editEmail, editUsername, editPassword;
    Button btnRegistrar;
    ProgressBar progressBar;

    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);

        editEmail = (EditText) findViewById(R.id.editEmail);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                String email=editEmail.getText().toString();
                String username =editUsername.getText().toString();
                String password=editPassword.getText().toString();
                if(!(email.equals("") && username.equals("") && password.equals(""))){
                    String[] parametres = {email, username, password};
                    PRU = new PostRegistreUsuari();
                    PRU.execute(parametres);
                    try{
                        startActivity(new Intent(Registrar.this,Login.class));
                    }catch (Exception ex){
                        Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Les dades no son valides",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(getApplicationContext(),"Versio no actualitzada!",Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    public void consumirServicio(){
        // ahora ejecutaremos el hilo creado
        String email= editEmail.getText().toString();
        String nom= editUsername.getText().toString();
        String password= editPassword.getText().toString();
    }

    public boolean validacio(){
        boolean valid=true;
        String email = editEmail.toString();
        String nom = editUsername.toString();
        String password = editPassword.toString();
        if(email.equals("")){
            editEmail.setError("Required");
            valid=false;
        }else if(nom.equals("")){
            editUsername.setError("Required");
            valid=false;
        }else if(password.equals("")){
            editPassword.setError("Required");
            valid=false;
        }
        return  valid;
    }
    }
