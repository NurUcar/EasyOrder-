package com.example.easyorder;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.preference.PowerPreference;

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

public class SetUserFragment extends Fragment{
    EditText adSoyad,telNo,aboneNo,adres,sifre;
    Button Kaydet;
    String Str_adSoyad,Str_telNo,Str_aboneNo,Str_adres,Str_sifre;

    String result3="";
    String update_url = "http://192.168.43.221/EasyOrder/update.php";
    String Tc_user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_user,container,false);
        adSoyad=view.findViewById(R.id.et_name);
        telNo=view.findViewById(R.id.et_tel);
        aboneNo=view.findViewById(R.id.et_abone);
        adres=view.findViewById(R.id.et_address);
        sifre=view.findViewById(R.id.et_password);
        Kaydet=view.findViewById(R.id.login_activity_set_button);
        Tc_user=  PowerPreference.getDefaultFile().getString("key", "null");
        Kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Str_adSoyad= adSoyad.getText().toString();
                Str_telNo= telNo.getText().toString();
                Str_aboneNo=aboneNo.getText().toString();
                Str_adres=adres.getText().toString();
                Str_sifre=sifre.getText().toString();
                new Update_user().execute();

            }
        });

        return view;
    }
    class Update_user extends AsyncTask<String, String,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {

                URL url= new URL(update_url);
                URLConnection conn= url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
                String post_data = URLEncoder.encode("user_AdSoyad", "UTF-8") + "=" + URLEncoder.encode(Str_adSoyad, "UTF-8") + "&"
                        + URLEncoder.encode("user_AboneNo", "UTF-8") + "=" + URLEncoder.encode(Str_aboneNo, "UTF-8") + "&"
                        + URLEncoder.encode("user_Tel", "UTF-8") + "=" + URLEncoder.encode(Str_telNo, "UTF-8") + "&"
                        + URLEncoder.encode("user_Adres", "UTF-8") + "=" + URLEncoder.encode(Str_adres, "UTF-8") + "&"
                        + URLEncoder.encode("user_Password", "UTF-8") + "=" + URLEncoder.encode(Str_sifre, "UTF-8")+ "&"
                        + URLEncoder.encode("user_Tc", "UTF-8") + "=" + URLEncoder.encode(Tc_user, "UTF-8");

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
            Toast.makeText(getActivity(),"Kullanıcı Bilgileriniz Güncellenmiştir.",Toast.LENGTH_SHORT).show();

        }

    }
}
