package com.example.farmapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.farmapp.Activity.CropDetailsActivity;
import com.example.farmapp.Activity.CropPriceActivity;
import com.example.farmapp.Activity.FertilizerActivity;
import com.example.farmapp.Activity.HomeActivity;
import com.example.farmapp.Activity.TechnologyActivity;
import com.example.farmapp.R;

public class CropFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_crop, container, false);
        CardView cardCropDetails=view.findViewById(R.id.cardCropDetails);
        CardView cardCropPrice=view.findViewById(R.id.cardCropPrice);
        CardView cardFertilizer=view.findViewById(R.id.cardFertilizer);
        CardView cardTechnology=view.findViewById(R.id.cardTechnology);

        cardCropDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CropDetailsActivity.class);
                startActivity(intent);
            }
        });

        cardCropPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CropPriceActivity.class);
                startActivity(intent);
            }
        });

        cardFertilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FertilizerActivity.class);
                startActivity(intent);
            }
        });

        cardTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), TechnologyActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}


