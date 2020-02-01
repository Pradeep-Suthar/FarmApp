package com.example.farmapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.farmapp.Adepter.PriceCustomAdapter;
import com.example.farmapp.Pojo.PricePojo;
import com.example.farmapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CropPriceActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<PricePojo> userPojos = new ArrayList<>();
    public static final String url = "https://api.myjson.com/bins/18f8jr";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_price);
        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("Crop Market Price");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        recyclerView = findViewById(R.id.recycleViewPrice);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        Log.d("1234", "onCreate: ");
        requestQueue = Volley.newRequestQueue(CropPriceActivity.this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("1234", "onResponse: "+response);

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("crops_price");

                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        PricePojo pricePojo = new PricePojo();
                        pricePojo.setName(jsonObject.getString("crop_name"));
                        pricePojo.setPrice(jsonObject.getString("price"));
                        pricePojo.setImgUrl(jsonObject.getString("image_url"));
                        userPojos.add(pricePojo);
                    }

                    Log.d("12345", "onResponse: getview ");
                    PriceCustomAdapter priceCustomAdapter = new PriceCustomAdapter(CropPriceActivity.this, userPojos);
                    recyclerView.setAdapter(priceCustomAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("1234", "onErrorResponse: "+error.getMessage());

                Toast.makeText(CropPriceActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
        Log.d("1234", "onCreate: 123");
        requestQueue.add(request);

    }
}


