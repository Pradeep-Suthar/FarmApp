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

import com.example.farmapp.Activity.BankRequestActivity;
import com.example.farmapp.Activity.GovtServicesActivity;
import com.example.farmapp.R;

public class ServicesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_services, container, false);
        CardView cardServices=view.findViewById(R.id.cardServices);
        CardView cardBank=view.findViewById(R.id.cardBank);

        cardBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), BankRequestActivity.class);
                startActivity(intent);
            }
        });

        cardServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), GovtServicesActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }
}
