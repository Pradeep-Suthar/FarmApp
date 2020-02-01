package com.example.farmapp.Adepter;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.farmapp.Pojo.CropPojo;
import com.example.farmapp.R;

import java.util.ArrayList;

public class NewsCustomAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<CropPojo> arrayList;
    public NewsCustomAdapter(Context context, int resource, ArrayList<CropPojo> arrayList) {
        super(context, resource, arrayList);
        Log.d("12345", "NewsCustomAdapter:"+context.toString());
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, null,false);
        CardView cardView=view.findViewById(R.id.cardViewNews);
        ImageView imageNews = view.findViewById(R.id.imageNews);
        TextView textNewsName = view.findViewById(R.id.textNews);

        final CropPojo cropPojo = arrayList.get(position);
        textNewsName.setText(cropPojo.getName());

        Log.d("12345", "getView: "+cropPojo.getImgUrl());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.news_onclick_item, null, false);
                alertDialogBuilder.setView(view);
                AlertDialog dialog=alertDialogBuilder.create();

                TextView textDescription=view.findViewById(R.id.textDescription);
                TextView textViewHeading=view.findViewById(R.id.textHeading1);
                VideoView videoView=view.findViewById(R.id.videoViewNews);

                MediaController mediaController=new MediaController(context);
                mediaController.setAnchorView(videoView);


                Log.d("12345", "onClick:des "+cropPojo.getDescription());

                textDescription.setText(cropPojo.getDescription());
                textViewHeading.setText(cropPojo.getName());

                switch (position){
                    case 0:
                        videoView.setMediaController(mediaController);
                        videoView.setVideoURI(Uri.parse("android.resource://"+ getContext().getPackageName()+"/"+R.raw.bhopal1));
                        videoView.start();
                        break;
                    case 1:
                        videoView.setMediaController(mediaController);
                        videoView.setVideoURI(Uri.parse("android.resource://"+ getContext().getPackageName()+"/"+R.raw.muzzaffarnagar2));
                        videoView.start();
                    case 2:
                        videoView.setMediaController(mediaController);
                        videoView.setVideoURI(Uri.parse("android.resource://"+ getContext().getPackageName()+"/"+R.raw.debt3));
                        videoView.start();
                    case 3:
                        videoView.setMediaController(mediaController);
                        videoView.setVideoURI(Uri.parse("android.resource://"+ getContext().getPackageName()+"/"+R.raw.lakh4));
                        videoView.start();
                        break;
                    case 4:
                        videoView.setMediaController(mediaController);
                        videoView.setVideoURI(Uri.parse("android.resource://"+ getContext().getPackageName()+"/"+R.raw.odisha));
                        videoView.start();
                    case 5:
                        videoView.setMediaController(mediaController);
                        videoView.setVideoURI(Uri.parse("android.resource://"+ getContext().getPackageName()+"/"+R.raw.pm7));
                        videoView.start();
                    case 6:
                        videoView.setMediaController(mediaController);
                        videoView.setVideoURI(Uri.parse("android.resource://"+ getContext().getPackageName()+"/"+R.raw.pm6));
                        videoView.start();
                        break;
                    case 7:
                        videoView.setMediaController(mediaController);
                        videoView.setVideoURI(Uri.parse("android.resource://"+ getContext().getPackageName()+"/"+R.raw.odisha));
                        videoView.start();
                    case 8:
                        videoView.setMediaController(mediaController);
                        videoView.setVideoURI(Uri.parse("android.resource://"+ getContext().getPackageName()+"/"+R.raw.pm6));
                        videoView.start();


                }



                dialog.show();
            }
        });

        Glide.with(context).load(cropPojo.getImgUrl()).into(imageNews);
        return view;

    }
}