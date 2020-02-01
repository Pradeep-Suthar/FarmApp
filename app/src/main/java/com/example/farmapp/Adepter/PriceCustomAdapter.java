package com.example.farmapp.Adepter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.farmapp.Pojo.PricePojo;
import com.example.farmapp.R;

import java.util.ArrayList;

public class PriceCustomAdapter extends RecyclerView.Adapter<PriceCustomAdapter.ViewHolder>{

        private ArrayList<PricePojo> values;
        private Context context;

        public PriceCustomAdapter(Context context, ArrayList<PricePojo> myDataset) {
            values = myDataset;
            this.context = context;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.price_list_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final PricePojo myPojo = values.get(position);
            holder.name.setText(myPojo.getName());
            holder.price.setText("Rs."+myPojo.getPrice());
            Glide.with(context).load(myPojo.getImgUrl()).into(holder.imagePrice);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return values.size();
        }


public class ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView name;
    public TextView price;
    public ImageView imagePrice;
    public CardView cardViewPrice;
    public ViewHolder(View v) {
        super(v);
        name = v.findViewById(R.id.textViewPriceName);
        price =v.findViewById(R.id.textViewPrice);
        imagePrice = v.findViewById(R.id.imagePrice);
        cardViewPrice = v.findViewById(R.id.cardViewPrice);

    }
}


}