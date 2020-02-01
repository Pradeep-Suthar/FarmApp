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

public class TechnologyCustomAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<CropPojo> arrayList;
    public TechnologyCustomAdapter(Context context, int resource, ArrayList<CropPojo> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, null,false);
        ImageView imageTech = view.findViewById(R.id.imageTech);
        TextView textTechName = view.findViewById(R.id.textTechName);
        TextView textTechDecription=view.findViewById(R.id.textTechDecription);

        final CropPojo cropPojo = arrayList.get(position);
        textTechName.setText(cropPojo.getName());
        textTechDecription.setText(cropPojo.getDescription());

        Glide.with(context).load(cropPojo.getImgUrl()).into(imageTech);
        return view;

    }
}


