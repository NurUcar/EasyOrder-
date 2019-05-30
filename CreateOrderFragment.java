package com.example.easyorder;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.preference.PowerPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CreateOrderFragment extends Fragment {
    Button save,save2;
    LinearLayout mCalendarLayout;
    CalendarView mCalendar;
    String value, mDate=null,today,mHour=null;
    Button hour_1,hour_2,hour_3,hour_4,hour_5,hour_6,hour_7,hour_8,hour_9,
            hour_10,hour_11,hour_12,hour_13,hour_14,hour_15,hour_16,hour_17,hour_18,hour_19,hour_20,hour_21;
    ScrollView mScroll;
    String Tc_user,line="";
    String result2;
    String showOrder_url =  "http://192.168.43.221/EasyOrder/getAllOrder.php";
    String createOrder_url = "http://192.168.43.221/EasyOrder/createOrder.php";
    String saat;
    int hour_1_color=0,hour_2_color=0,hour_3_color=0,hour_4_color=0,hour_5_color=0,hour_6_color=0
            ,hour_7_color=0,hour_8_color=0,hour_9_color=0,hour_10_color=0,hour_11_color=0,
            hour_12_color=0,hour_13_color=0,hour_14_color=0,hour_15_color=0,hour_16_color=0,
            hour_17_color=0,hour_18_color=0,hour_19_color=0,hour_20_color=0,hour_21_color=0;

    ArrayList<String> order_list= new ArrayList<String>();

    public CreateOrderFragment(){

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_order,container,false);
        save=view.findViewById(R.id.btn_kaydet);
        save2=view.findViewById(R.id.btn_kaydet2);
        mCalendarLayout=view.findViewById(R.id.CalenderLayout);
        mCalendar=view.findViewById(R.id.calendarView);
        mScroll=view.findViewById(R.id.Scroll);
        hour_1=view.findViewById(R.id.hour1);
        hour_2=view.findViewById(R.id.hour2);
        hour_3=view.findViewById(R.id.hour3);
        hour_4=view.findViewById(R.id.hour4);
        hour_5=view.findViewById(R.id.hour5);
        hour_6=view.findViewById(R.id.hour6);
        hour_7=view.findViewById(R.id.hour7);
        hour_8=view.findViewById(R.id.hour8);
        hour_9=view.findViewById(R.id.hour9);
        hour_10=view.findViewById(R.id.hour10);
        hour_11=view.findViewById(R.id.hour11);
        hour_12=view.findViewById(R.id.hour12);
        hour_13=view.findViewById(R.id.hour13);
        hour_14=view.findViewById(R.id.hour14);
        hour_15=view.findViewById(R.id.hour15);
        hour_16=view.findViewById(R.id.hour16);
        hour_17=view.findViewById(R.id.hour17);
        hour_18=view.findViewById(R.id.hour18);
        hour_19=view.findViewById(R.id.hour19);
        hour_20=view.findViewById(R.id.hour20);
        hour_21=view.findViewById(R.id.hour21);
        Tc_user=  PowerPreference.getDefaultFile().getString("key", "null");






        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month=month+1;
                mDate=String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                today= df.format(c);
                if (mDate==null){
                    mDate=today;
                }

                if (mDate.equals(today)){
                    Toast.makeText(getActivity(),"Aynı güne randevu alınamamaktadır.",Toast.LENGTH_SHORT).show();

                }else{
                    mCalendarLayout.setVisibility(View.INVISIBLE);
                    mScroll.setVisibility(View.VISIBLE);
                    save.setVisibility(View.INVISIBLE);
                    save2.setVisibility(View.VISIBLE);
                    new ShowOrder().execute();

                }







            }


        });

        hour_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_1_color==0){
                    hour_1.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_1.getText().toString();
                }

            }
        });
        hour_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_2_color==0){
                    hour_2.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_2.getText().toString();
                }


            }
        });
        hour_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_2_color==0){
                    hour_2.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_2.getText().toString();
                }


            }
        });
        hour_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_4_color==0){
                    hour_4.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_4.getText().toString();
                }

            }
        });
        hour_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_5_color==0){
                    hour_5.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_5.getText().toString();
                }


            }
        });
        hour_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_6_color==0){
                    hour_6.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_6.getText().toString();
                }

            }
        });
        hour_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_7_color==0){
                    hour_7.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_7.getText().toString();
                }

            }
        });
        hour_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_8_color==0){
                    hour_8.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_8.getText().toString();
                }

            }
        });
        hour_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_9_color==0){
                    hour_9.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_9.getText().toString();
                }


            }
        });
        hour_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_10_color==0){
                    hour_10.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_10.getText().toString();
                }

            }
        });
        hour_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_11_color==0){
                    hour_11.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_11.getText().toString();
                }

            }
        });
        hour_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_12_color==0){
                    hour_12.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_12.getText().toString();
                }

            }
        });
        hour_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_13_color==0){
                    hour_13.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_13.getText().toString();
                }

            }
        });
        hour_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_14_color==0){
                    hour_14.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_14.getText().toString();
                }

            }
        });
        hour_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_15_color==0){
                    hour_15.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_15.getText().toString();
                }

            }
        });
        hour_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_16_color==0){
                    hour_16.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_16.getText().toString();
                }

            }
        });
        hour_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_17_color==0){
                    hour_17.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_17.getText().toString();
                }

            }
        });
        hour_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_18_color==0){
                    hour_18.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_18.getText().toString();
                }

            }
        });
        hour_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_19_color==0){
                    hour_19.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_19.getText().toString();
                }

            }
        });
        hour_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_20_color==0){
                    hour_20.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_20.getText().toString();
                }

            }
        });
        hour_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hour_21_color==0){
                    hour_21.setBackgroundColor(Color.parseColor("#878fff"));
                    mHour=hour_21.getText().toString();
                }

            }
        });

        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hour_1_color==0&&mHour.equals("09:00"))
                    Save_order();
                else if (hour_2_color==0&&mHour.equals("09:20"))
                    Save_order();
                else if (hour_3_color==0&&mHour.equals("09:40"))
                    Save_order();
                else if (hour_4_color==0&&mHour.equals("10:00"))
                    Save_order();
                else if (hour_5_color==0&&mHour.equals("10:20"))
                    Save_order();
                else if (hour_6_color==0&&mHour.equals("10:40"))
                    Save_order();
                else if (hour_7_color==0&&mHour.equals("11:00"))
                    Save_order();
                else if (hour_8_color==0&&mHour.equals("11:20"))
                    Save_order();
                else if (hour_9_color==0&&mHour.equals("11:40"))
                    Save_order();
                else if (hour_10_color==0&&mHour.equals("13:00"))
                    Save_order();
                else if (hour_11_color==0&&mHour.equals("13:20"))
                    Save_order();
                else if (hour_12_color==0&&mHour.equals("13:40"))
                    Save_order();
                else if (hour_13_color==0&&mHour.equals("14:00"))
                    Save_order();
                else if (hour_14_color==0&&mHour.equals("14:20"))
                    Save_order();
                else if (hour_15_color==0&&mHour.equals("14:40"))
                    Save_order();
                else if (hour_16_color==0&&mHour.equals("15:00"))
                    Save_order();
                else if (hour_17_color==0&&mHour.equals("15:20"))
                    Save_order();
                else if (hour_18_color==0&&mHour.equals("15:40"))
                    Save_order();
                else if (hour_19_color==0&&mHour.equals("16:00"))
                    Save_order();
                else if (hour_19_color==0&&mHour.equals("16:20"))
                    Save_order();
                else if (hour_19_color==0&&mHour.equals("16:40"))
                    Save_order();
                else
                    Toast.makeText(getActivity(),"Randevu Dolu!",Toast.LENGTH_SHORT).show();
            }




        });



        return view;
    }

    private void Save_order() {
        File file =null;
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            if (mHour!=null&&mDate!=null) {

                try {
                    URL url = new URL(createOrder_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data = URLEncoder.encode("user_Tc", "UTF-8") + "=" + URLEncoder.encode(Tc_user, "UTF-8") + "&"
                            + URLEncoder.encode("order_Tarih", "UTF-8") + "=" + URLEncoder.encode(mDate, "UTF-8") + "&"
                            + URLEncoder.encode("order_Saat", "UTF-8") + "=" + URLEncoder.encode(mHour, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    inputStream.close();
                    Toast.makeText(getActivity(), "Randevunuz Alınmıştır.", Toast.LENGTH_SHORT).show();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(getActivity(),"Randevu Dolu!",Toast.LENGTH_SHORT).show();
            }
        }

    }


    class  ShowOrder extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {


            try {
                URL url= new URL(showOrder_url);
                URLConnection conn= url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
                String post_data = URLEncoder.encode("order_date", "UTF-8") + "=" + URLEncoder.encode(mDate, "UTF-8");


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
                        saat= order.getString("Saat");
                        order_list.add(saat);


                    }


                }
                for(int z=0;z<order_list.size();z++){
                    String val_order_hour=order_list.get(z);
                    if (val_order_hour.equals("09:00")){
                        hour_1.setBackgroundColor(Color.parseColor("#663399"));
                        hour_1_color=1;
                    }else if (val_order_hour.equals("09:20")){
                        hour_2.setBackgroundColor(Color.parseColor("#663399"));
                        hour_2_color=1;
                    }else if (val_order_hour.equals("09:40")){
                        hour_3.setBackgroundColor(Color.parseColor("#663399"));
                        hour_3_color=1;
                    }else if (val_order_hour.equals("10:00")){
                        hour_4.setBackgroundColor(Color.parseColor("#663399"));
                        hour_4_color=1;
                    }else if (val_order_hour.equals("10:20")){
                        hour_5.setBackgroundColor(Color.parseColor("#663399"));
                        hour_5_color=1;
                    }else if (val_order_hour.equals("10:40")){
                        hour_6.setBackgroundColor(Color.parseColor("#663399"));
                        hour_6_color=1;
                    }else if (val_order_hour.equals("11:00")){
                        hour_7.setBackgroundColor(Color.parseColor("#663399"));
                        hour_7_color=1;
                    }else if (val_order_hour.equals("11:20")){
                        hour_8.setBackgroundColor(Color.parseColor("#663399"));
                        hour_8_color=1;
                    }else if (val_order_hour.equals("11:40")){
                        hour_9.setBackgroundColor(Color.parseColor("#663399"));
                        hour_9_color=1;
                    }else if (val_order_hour.equals("13:00")){
                        hour_10.setBackgroundColor(Color.parseColor("#663399"));
                        hour_10_color=1;
                    }else if (val_order_hour.equals("13:20")){
                        hour_11.setBackgroundColor(Color.parseColor("#663399"));
                        hour_11_color=1;
                    }else if (val_order_hour.equals("13:40")){
                        hour_12.setBackgroundColor(Color.parseColor("#663399"));
                        hour_12_color=1;
                    }else if (val_order_hour.equals("14:00")){
                        hour_13.setBackgroundColor(Color.parseColor("#663399"));
                        hour_13_color=1;
                    }else if (val_order_hour.equals("14:20")){
                        hour_14.setBackgroundColor(Color.parseColor("#663399"));
                        hour_14_color=1;
                    }else if (val_order_hour.equals("14:40")){
                        hour_15.setBackgroundColor(Color.parseColor("#663399"));
                        hour_15_color=1;
                    }else if (val_order_hour.equals("15:00")){
                        hour_16.setBackgroundColor(Color.parseColor("#663399"));
                        hour_16_color=1;
                    }else if (val_order_hour.equals("15:20")){
                        hour_17.setBackgroundColor(Color.parseColor("#663399"));
                        hour_17_color=1;
                    }else if (val_order_hour.equals("15:40")){
                        hour_18.setBackgroundColor(Color.parseColor("#663399"));
                        hour_18_color=1;
                    }else if (val_order_hour.equals("16:00")){
                        hour_19.setBackgroundColor(Color.parseColor("#663399"));
                        hour_19_color=1;
                    }else if (val_order_hour.equals("16:20")){
                        hour_20.setBackgroundColor(Color.parseColor("#663399"));
                        hour_20_color=1;
                    }else if (val_order_hour.equals("16:40")){
                        hour_21.setBackgroundColor(Color.parseColor("#663399"));
                        hour_21_color=1;
                    }



                }





            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
