package com.example.easyorder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.text.IDNA;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.preference.PowerPreference;
import com.preference.Preference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    Context context=this;
    Button register;
    Button Login;
    Switch Remember1;
    TextView getInfo;
    TextView getPassword;
    String StrInfo;
    String StrPassword;
    Context contxt=this;

    String result3="";

    String login_url = "http://192.168.43.221/EasyOrder/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register=findViewById(R.id.login_activity_register_button);
        Login=findViewById(R.id.login_activity_login_button);
        Remember1=findViewById(R.id.remember_me);
        getInfo=findViewById(R.id.login_activity_info);
        getPassword= findViewById(R.id.login_activity_password);







        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrInfo=getInfo.getText().toString();
                StrPassword=getPassword.getText().toString();
                PowerPreference.getDefaultFile().put("key",StrInfo);

                new Login().execute();








            }
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder alert= new AlertDialog.Builder(context);
            alert.setTitle("Çıkmak istediğinizden emin misiniz?")
                    .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create().show();


        }
        return super.onKeyDown(keyCode, event);
    }


    class Login extends AsyncTask<String, String,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {

                URL url= new URL(login_url);
                URLConnection conn= url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
                String post_data = URLEncoder.encode("user_tc", "UTF-8") + "=" + URLEncoder.encode(StrInfo, "UTF-8") + "&" +
                        URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(StrPassword, "UTF-8");

                outputStreamWriter.write(post_data);
                outputStreamWriter.flush();

                InputStream inputStream = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                StringBuilder stringBuilder= new StringBuilder();
                String line= null;
                while ((line= bufferedReader.readLine())!=null ){
                    stringBuilder.append(line);
                    break;
                }

                result3= stringBuilder.toString();



            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result3;
        }
        @Override
        protected void onPostExecute(String result){
            if (result.equals("login success")){

                Intent i= new Intent(getApplicationContext(),OrderActivity.class);
                startActivity(i);
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Tc yada şifre hatalı!",Toast.LENGTH_SHORT).show();
            }

        }

    }





}
