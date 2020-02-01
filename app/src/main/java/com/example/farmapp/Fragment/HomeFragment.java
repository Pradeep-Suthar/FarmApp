package com.example.farmapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.farmapp.Adepter.NewsCustomAdapter;
import com.example.farmapp.Adepter.SliderViewAdapter;
import com.example.farmapp.Pojo.CropPojo;
import com.example.farmapp.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ListView listView;
    ArrayList<CropPojo> userPojos = new ArrayList<>();
    public static final String url = "https://api.myjson.com/bins/9xx1r";
    RequestQueue requestQueue;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewPager);

        SliderViewAdapter adapter = new SliderViewAdapter(this.getContext());
        Log.d("12345", "onCreateView: "+adapter.toString());
        viewPager.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this.getContext());


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("1234", "onResponse: "+response);

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("news");

                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        CropPojo cropPojo = new CropPojo();
                        cropPojo.setName(jsonObject.getString("name"));
                        cropPojo.setDescription(jsonObject.getString("description"));
                        cropPojo.setImgUrl(jsonObject.getString("img_url"));
                        userPojos.add(cropPojo);
                    }

                    listView = view.findViewById(R.id.listViewNews);
                    Log.d("12345", "onResponse: getview ");
                    NewsCustomAdapter customAdapter = new NewsCustomAdapter(getContext(), R.layout.news_list_item,userPojos);
                    listView.setAdapter(customAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("1234", "onErrorResponse: "+error.getMessage());

                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
        Log.d("1234", "onCreate: 123");
        requestQueue.add(request);







        return view;
    }

}
