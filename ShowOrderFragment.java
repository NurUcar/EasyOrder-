package com.example.easyorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.preference.PowerPreference;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ShowOrderFragment extends Fragment{
    String jsonResult;
    String showOrder_url =  "http://192.168.43.221/EasyOrder/getOrders.php";
    ListView mListView;
    String Tc_user;
    ArrayAdapter<String> adapter;
    TextView tv;
    String line;
    String result2="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_order,container,false);
        mListView=view.findViewById(R.id.list);
        tv=view.findViewById(R.id.textView2);
        Tc_user=  PowerPreference.getDefaultFile().getString("key", "null");

        adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        mListView.setAdapter(adapter);
        new ShowOrder().execute();










        return view;
    }

    class  ShowOrder extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {


            try {
                URL url= new URL(showOrder_url);
                URLConnection conn= url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
                String post_data = URLEncoder.encode("user_Tc", "UTF-8") + "=" + URLEncoder.encode(Tc_user, "UTF-8");


                outputStreamWriter.write(post_data);
                outputStreamWriter.flush();

                InputStream inputStream = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                StringBuilder stringBuilder= new StringBuilder();

                while ((line= bufferedReader.readLine())!=null ){
                    stringBuilder.append(line);
                    break;
                }

                result2= stringBuilder.toString();



            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result2;
        }
        @Override
        protected void onPostExecute(String result){
            try {
                JSONObject jsonResult= new JSONObject(result);
                int success= jsonResult.getInt("success");
                if (success==1){
                    JSONArray orders=jsonResult.getJSONArray("orders");
                    for (int i=0;i<orders.length();i++){
                        JSONObject order=orders.getJSONObject(i);
                        String tarih=order.getString("Tarih");
                        String saat= order.getString("Saat");
                        line= tarih+"-"+saat;
                        adapter.add(line);


                    }



                }
                else
                    Toast.makeText(getActivity(),"Kayıtlı randevu bulunmamaktadır.",Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
