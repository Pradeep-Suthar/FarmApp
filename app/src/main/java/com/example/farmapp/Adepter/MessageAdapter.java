package com.example.farmapp.Adepter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.farmapp.Activity.HomeActivity;
import com.example.farmapp.Pojo.MessagePojo;
import com.example.farmapp.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private ArrayList<MessagePojo> values;
    private Context context;

    public MessageAdapter(Context context, ArrayList<MessagePojo> myDataset) {
        values = myDataset;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recyle_msg_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d("12345", "onBindViewHolder: enter");
        final MessagePojo myPojo = values.get(position);
        holder.textDate.setText(myPojo.getDate());
        holder.textTime.setText(myPojo.getTime());
        Glide.with(context).load(myPojo.getImg_url()).into(holder.msgProfile);
        holder.textMessage.setText(myPojo.getMessage());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView textMessage;
        public TextView textTime;
        public TextView textDate;
        public ImageView msgProfile;
        public LinearLayout linearLayout;

        public ViewHolder(View v) {
            super(v);
            textTime=v.findViewById(R.id.textTime);
            msgProfile=v.findViewById(R.id.msgProfile);
            textDate=v.findViewById(R.id.textDate);
            textMessage = v.findViewById(R.id.message);
            linearLayout = v.findViewById(R.id.linearLayout1);

        }
    }


}
