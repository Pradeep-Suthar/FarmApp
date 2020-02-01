package com.example.farmapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.farmapp.R;


public class ChatFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_chat, container, false);
        Button btnExpertChat=view.findViewById(R.id.btnExpertChat);
        Button btnGenralChat=view.findViewById(R.id.btnGenralChat);

        FragmentTransaction ft=getChildFragmentManager().beginTransaction();
        GenrelChatFragment gf=new GenrelChatFragment();
        ft.replace(R.id.chatContainFrgment,gf);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        btnGenralChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft=getChildFragmentManager().beginTransaction();
                GenrelChatFragment gf=new GenrelChatFragment();
                ft.replace(R.id.chatContainFrgment,gf);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });


        btnExpertChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft=getChildFragmentManager().beginTransaction();
                ExpertChatFragment gf=new ExpertChatFragment();
                ft.replace(R.id.chatContainFrgment,gf);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });

        return view;
    }
}
