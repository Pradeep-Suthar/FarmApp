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

public class FertilizerCustomAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<CropPojo> arrayList;
    public FertilizerCustomAdapter(Context context, int resource, ArrayList<CropPojo> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, null,false);
        CardView cardView=view.findViewById(R.id.cardViewFertilizer);
        TextView textCropName = view.findViewById(R.id.textFertilizerName);

        final CropPojo cropPojo = arrayList.get(position);
        textCropName.setText("Name : "+cropPojo.getName());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.crop_des_layout, null, false);
                alertDialogBuilder.setView(view);
                AlertDialog dialog=alertDialogBuilder.create();

                TextView textDescription=view.findViewById(R.id.textDescription);
                textDescription.setText(cropPojo.getDescription());

                dialog.show();
            }
        });

        return view;

    }
}


