package com.example.farmapp.Adepter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.farmapp.Pojo.CropPojo;
import com.example.farmapp.R;


import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class CropCustomAdapter extends ArrayAdapter {

        Context context;
        int resource;
        ArrayList<CropPojo> arrayList;
        public CropCustomAdapter(Context context, int resource, ArrayList<CropPojo> arrayList) {
            super(context, resource, arrayList);
            this.context = context;
            this.resource = resource;
            this.arrayList = arrayList;

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(resource, null,false);
            CardView cardView=view.findViewById(R.id.cardViewCrop);
            ImageView imageCrop = view.findViewById(R.id.imageCrop);
            TextView textCropName = view.findViewById(R.id.textCropName);
            TextView textSoilType = view.findViewById(R.id.textSoilType);
            TextView textTemperature = view.findViewById(R.id.textTemperature);
            TextView textRainFall = view.findViewById(R.id.textRainFall);

            final CropPojo cropPojo = arrayList.get(position);
            textCropName.setText("Name : "+cropPojo.getName());
            textRainFall.setText("Rain Fall : "+cropPojo.getRainFall());
            textSoilType.setText("Soil Type : "+cropPojo.getSoilType());
            textTemperature.setText("Temperature : "+cropPojo.getTemp()+"\u00b0C");

            Log.d("12345", "getView: "+cropPojo.getImgUrl());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view = layoutInflater.inflate(R.layout.crop_des_layout, null, false);
                    alertDialogBuilder.setView(view);
                    AlertDialog dialog=alertDialogBuilder.create();

                    TextView textDescription=view.findViewById(R.id.textDescription);

                    Log.d("12345", "onClick:des "+cropPojo.getDescription());

                    textDescription.setText(cropPojo.getDescription());

                    dialog.show();
                }
            });

            Glide.with(context).load(cropPojo.getImgUrl()).into(imageCrop);
            return view;

        }
}


