package com.example.farmapp.Adepter;

import android.content.Context;
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

public class GovtCustomAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<CropPojo> arrayList;
    public GovtCustomAdapter(Context context, int resource, ArrayList<CropPojo> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, null,false);
        ImageView imageGovt = view.findViewById(R.id.imageGovt);
        TextView textGovtName = view.findViewById(R.id.textGovtName);
        TextView textGovtDecription=view.findViewById(R.id.textGovtDecription);

        final CropPojo cropPojo = arrayList.get(position);
        textGovtName.setText(cropPojo.getName());
        textGovtDecription.setText(cropPojo.getDescription());

        Glide.with(context).load(cropPojo.getImgUrl()).into(imageGovt);
        return view;

    }
}
