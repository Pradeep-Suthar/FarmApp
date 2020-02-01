package com.example.farmapp.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.farmapp.Activity.HomeActivity;
import com.example.farmapp.Pojo.UserPojo;
import com.example.farmapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

import static android.content.Context.MODE_PRIVATE;

public class WeatherFragment extends Fragment {

    StringRequest request;
    RequestQueue requestQueue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_weather, container, false);

        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("myprf",MODE_PRIVATE);
        String pincode=sharedPreferences.getString("pin",null);

        Calendar calendar=Calendar.getInstance();
        int Day=calendar.get(Calendar.DAY_OF_MONTH);
        int Month=calendar.get(Calendar.MONTH);
        int Year=calendar.get(Calendar.YEAR);

        final String Date=Day+"/"+(Month+1)+"/"+Year;

        final String url = "http://openweathermap.org/data/2.5/weather?q="+pincode+"&appid=b6907d289e10d714a6e88b30761fae22";

        requestQueue= Volley.newRequestQueue(view.getContext());
        final TextView textViewCity=view.findViewById(R.id.textViewCity);
        final TextView textViewDate=view.findViewById(R.id.textViewDate);
        final TextView textViewTemp=view.findViewById(R.id.textViewTemp);
        final TextView textViewType=view.findViewById(R.id.textViewType);
        final TextView textViewHumidity=view.findViewById(R.id.textViewHumidity);
        final TextView textViewPressure=view.findViewById(R.id.textViewPressure);
        final TextView textViewMaxTemp=view.findViewById(R.id.textViewMaxTemp);
        final TextView textViewWind_Speed=view.findViewById(R.id.textViewWind_Speed);
        final TextView textViewMinTemp=view.findViewById(R.id.textViewMinTemp);
        final ImageView imageViewIcon=view.findViewById(R.id.imageViewIcon);

        Log.d("123456", "onCreateView:volly requsted ");

         request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("12345", "onResponse: "+response);
                try {
                    Log.d("12345", "onTry: "+response);
                    JSONObject object = new JSONObject(response);

                    JSONArray weather = object.getJSONArray("weather");
                    JSONObject jsonObject = weather.getJSONObject(0);
                    String main=jsonObject.getString("main");
                    String des=jsonObject.getString("description");
                    String icon=jsonObject.getString("icon");

                    String iconUrl="http://openweathermap.org/img/w/"+icon+".png";

                    Log.d("123456", "onCreateView:volly requsted "+des+" "+main);

                    JSONObject maintemp=object.getJSONObject("main");
                    int temp=maintemp.getInt("temp");
                    String pressure=maintemp.getString("pressure");
                    String humidity=maintemp.getString("humidity");
                    String maxTemp=maintemp.getString("temp_max");
                    String minTemp=maintemp.getString("temp_min");

                    JSONObject wind=object.getJSONObject("wind");
                    String speed=wind.getString("speed");

                    Log.d("123456", "onCreateView:volly requsted "+wind);

                    String cname=object.getString("name");

                    Log.d("12345", "onResponse: temp"+temp);

                    textViewCity.setText(cname);
                    textViewTemp.setText(temp+"\u00b0C");
                    textViewType.setText(main);
                    textViewDate.setText(Date);
                    textViewHumidity.setText("Humidity :"+humidity+"%");
                    textViewPressure.setText("Pressure :"+pressure+"hpa");
                    textViewMaxTemp.setText("Max Temp. :"+maxTemp+"\u00b0C");
                    textViewMinTemp.setText("Min Temp. :"+minTemp+"\u00b0C");
                    textViewWind_Speed.setText("Wind Speed :"+speed+"m/s");
                    Glide.with(view.getContext()).load(iconUrl).into(imageViewIcon);

                    Log.d("12345", "onResponse: data all set");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("1234", "onErrorResponse: "+error.getMessage());

            Toast.makeText(view.getContext(), "Network Error", Toast.LENGTH_SHORT).show();

        }
    });
        requestQueue.add(request);
        return view;
    }
}
