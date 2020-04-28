package com.example.flypath;

import android.os.AsyncTask;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.flypath.BD.GetObtenirUsuari;
import java.sql.Connection;
import java.sql.DriverManager;

public class Login extends AppCompatActivity {
    Usuari usuari;
    GetObtenirUsuari GOU;
    EditText editUsername, editPassword;
    Button btnLogin,btnRegistrar;
    ProgressBar progressBar;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =editUsername.getText().toString();
                String password=editPassword.getText().toString();
                if(!(username.equals("") && password.equals(""))){
                    String[] parametres = {username,password};
                    GOU = new GetObtenirUsuari();
                    GOU.execute(parametres);
                    usuari=GOU.obtenirJsonUser();
                    boolean a=GOU.getTrobat();
                    if(GOU.getTrobat()){
                        try{
                            startActivity(new Intent(Login.this,MainActivity.class));
                        }catch (Exception ex){
                            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Les dades no son valides",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Les dades no son valides",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(Login.this,Registrar.class));
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    class TaskLogin extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            btnLogin.setEnabled(false);
            btnRegistrar.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.INVISIBLE);
            btnLogin.setEnabled(true);
            btnRegistrar.setEnabled(true);
            //Intent intent = new Intent(Login.this, MainActivity.class);
            //intent.putExtra("nom",editNom.getText().toString());
            //intent.putExtra("password",editPassword.getText().toString());
            //startActivity(intent);
        }
    }

    public Connection conDB(){
        Connection conexio=null;
        try{
            StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexio= DriverManager.getConnection("jdbc:jtds:sqlserver://localhost://DatabaseName=FlyPath;user=sa;password=;");
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexio;
    }
}
