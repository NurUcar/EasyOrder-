package com.example.easyorder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {
    FloatingActionButton back;
    Button submit;
    EditText AdSoyad;
    EditText Tc;
    EditText PhoneNumber;
    EditText Address;
    EditText Password;
    EditText Confirm;
    Context mContext= this;

    String register_url = "http://192.168.43.221/EasyOrder/register.php";
    String result3="";

    String StrAdSoyad, StrTc, StrPhoneNumber, StrAddress, StrPassword,StrConfirm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        back = findViewById(R.id.register_activity_floating_button);
        submit = findViewById(R.id.register_activity_button);
        AdSoyad= findViewById(R.id.register_activity_name);
        Tc=findViewById(R.id.register_activity_tc);
        PhoneNumber=findViewById(R.id.register_activity_phonenumber);
        Address=findViewById(R.id.register_activity_address);
        Password=findViewById(R.id.register_activity_password);
        Confirm= findViewById(R.id.register_activity_passwordCon);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(k);
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StrAdSoyad=AdSoyad.getText().toString();
                StrTc=Tc.getText().toString();
                if (StrTc.length()!=11){
                    Toast.makeText(getApplicationContext(),"Tc Kimlik Numarası 11 Haneli Olmak Zorundadır!.", Toast.LENGTH_SHORT).show();

                }
                else{
                    StrPhoneNumber=PhoneNumber.getText().toString();
                    if (StrPhoneNumber.length()!=10){
                        Toast.makeText(getApplicationContext(),"Telefon Numarası 10 Haneli Olmak Zorundadır!.", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        StrAddress=Address.getText().toString();
                        StrPassword=Password.getText().toString();
                        StrConfirm=Confirm.getText().toString();
                        if (StrPassword.equals(StrConfirm)) {
                            //for insert data to MySql database

                            new Register().execute();

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Şifreler Eşleşmemektedir!", Toast.LENGTH_SHORT).show();


                        }
                    }


                }






            }
        });


    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent k = new Intent(getApplicationContext(),
                    LoginActivity.class);
            startActivity(k);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    class Register extends AsyncTask<String, String,String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(register_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_AdSoyad", "UTF-8") + "=" + URLEncoder.encode(StrAdSoyad, "UTF-8") + "&"
                        +URLEncoder.encode("user_Tc", "UTF-8") + "=" + URLEncoder.encode(StrTc, "UTF-8") + "&"
                        +URLEncoder.encode("user_Tel", "UTF-8") + "=" + URLEncoder.encode(StrPhoneNumber, "UTF-8") + "&"
                        +URLEncoder.encode("user_Adres", "UTF-8") + "=" + URLEncoder.encode(StrAddress, "UTF-8") + "&"
                        +URLEncoder.encode("user_Password", "UTF-8") + "=" + URLEncoder.encode(StrPassword, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result3;

        }



        @Override
        protected void onPostExecute(String result){
            if (result.equals("Insert Successful")){

                Toast.makeText(getApplicationContext(), "Kayıt İşleminiz Tamamlanmıştır.", Toast.LENGTH_SHORT).show();

                Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }

        }

    }
}
