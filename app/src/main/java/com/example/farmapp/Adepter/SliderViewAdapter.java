package com.example.farmapp.Adepter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.farmapp.R;


public class SliderViewAdapter extends PagerAdapter {
    private Context mcontext;
    private int arr [] = {R.drawable.slider1,R.drawable.slider2,R.drawable.bg2};

    public SliderViewAdapter(Context mcontext){
        Log.d("12345", "sliderCustomAdapter:"+mcontext.toString());
        this.mcontext = mcontext;
    }
    @Override
    public int getCount() {
        return arr.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mcontext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(arr[position]);
        container.addView(imageView,0);
        return imageView;
    }
}

