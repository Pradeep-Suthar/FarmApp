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
import com.example.farmapp.Adepter.GovtCustomAdapter;
import com.example.farmapp.Adepter.TechnologyCustomAdapter;
import com.example.farmapp.Pojo.CropPojo;
import com.example.farmapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GovtServicesActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<CropPojo> userPojos = new ArrayList<>();
    public static final String url = "https://api.myjson.com/bins/hkv41";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govt_services);
        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("Govt. Services");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        requestQueue = Volley.newRequestQueue(GovtServicesActivity.this);


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("1234", "onResponse: "+response);

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("govt_services");

                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        CropPojo cropPojo = new CropPojo();
                        cropPojo.setImgUrl(jsonObject.getString("img_url"));
                        cropPojo.setName(jsonObject.getString("govt_name"));
                        cropPojo.setDescription(jsonObject.getString("description"));
                        userPojos.add(cropPojo);
                    }

                    listView = findViewById(R.id.listViewGovt);
                    Log.d("12345", "onResponse: getview ");
                    GovtCustomAdapter govtCustomAdapter = new GovtCustomAdapter(GovtServicesActivity.this, R.layout.govt_listview_item,userPojos);
                    listView.setAdapter(govtCustomAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("1234", "onErrorResponse: "+error.getMessage());

                Toast.makeText(GovtServicesActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
        Log.d("1234", "onCreate: 123");
        requestQueue.add(request);

    }
}
