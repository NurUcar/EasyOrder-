package com.example.easyorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.preference.PowerPreference;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class DeleteOrderFragment extends Fragment {


    String showOrder_url =  "http://192.168.43.221/EasyOrder/getOrders.php";
    String deleteOrder_url =  "http://192.168.43.221/EasyOrder/deleteOrder.php";
    String line;
    ListView dList;
    String oTarih=null;
    String oSaat=null;
    ArrayAdapter<String> adapter;
    Button delete_selected_order;
    String result2="";
    String val_selected_item;
    int pos_selected_item;
    String Tc_user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_delete_order,container,false);
        dList=view.findViewById(R.id.Dlist);
        Tc_user=  PowerPreference.getDefaultFile().getString("key", "null");

        delete_selected_order=view.findViewById(R.id.delete_button);
        adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        dList.setAdapter(adapter);
        new ShowUser().execute();


        dList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                myAdapter.setSelection(1);
                pos_selected_item=myItemInt;
                val_selected_item =(String) (dList.getItemAtPosition(myItemInt));
                String[] Separeted=val_selected_item.split("-");
                oTarih=Separeted[0];
                oSaat=Separeted[1];


            }
        });
        delete_selected_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oTarih!=null&&oSaat!=null){
                    new DeleteOrder().execute();
                    adapter.remove(adapter.getItem(pos_selected_item));
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getActivity(),"Silmek için bir randevu seçiniz!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }


    class  DeleteOrder extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {


            try {
                URL url= new URL(deleteOrder_url);
                URLConnection conn= url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
                String post_data = URLEncoder.encode("order_tarih", "UTF-8") + "=" + URLEncoder.encode(oTarih, "UTF-8") + "&" +
                        URLEncoder.encode("order_saat", "UTF-8") + "=" + URLEncoder.encode(oSaat, "UTF-8");


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
            Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();

        }
    }




    class  ShowUser extends AsyncTask<String,String,String> {
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