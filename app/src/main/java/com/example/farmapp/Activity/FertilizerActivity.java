package com.example.farmapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.farmapp.Adepter.CropCustomAdapter;
import com.example.farmapp.Adepter.FertilizerCustomAdapter;
import com.example.farmapp.Pojo.CropPojo;
import com.example.farmapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FertilizerActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<CropPojo> userPojos = new ArrayList<>();
    public static final String url = "https://api.myjson.com/bins/q60qp";
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizer);
        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("Fertilizer Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        Log.d("1234", "onCreate: ");
        requestQueue = Volley.newRequestQueue(FertilizerActivity.this);


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("1234", "onResponse: "+response);

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("fertilizer");

                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        CropPojo cropPojo = new CropPojo();
                        cropPojo.setName(jsonObject.getString("fertilizer_name"));
                        cropPojo.setDescription(jsonObject.getString("description"));
                        userPojos.add(cropPojo);
                    }

                    listView = findViewById(R.id.listViewFertilizer);
                    Log.d("12345", "onResponse: getview ");
                    FertilizerCustomAdapter fertilizerCustomAdapter = new FertilizerCustomAdapter(FertilizerActivity.this, R.layout.fertilizer_listview_item,userPojos);
                    listView.setAdapter(fertilizerCustomAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("1234", "onErrorResponse: "+error.getMessage());

                Toast.makeText(FertilizerActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
        Log.d("1234", "onCreate: 123");
        requestQueue.add(request);

    }
}